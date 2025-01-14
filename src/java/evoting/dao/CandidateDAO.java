/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateDTO;
import evoting.dto.CandidateDetails;
import evoting.dto.CandidateInfo;
import evoting.dto.UpdateCandidateDTO;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.* ;
import java.util.*;

/**
 *
 * @author asus
 */
public class CandidateDAO {
    private static PreparedStatement ps,ps1,ps2,ps3, ps4, ps5,ps6, ps7, ps8;
    private static Statement st ; 
    static{
        
        try{
            st = DBConnection.getConnection().createStatement();
            ps=DBConnection.getConnection().prepareStatement("select max(candidate_id) from candidate");
           ps1 = DBConnection.getConnection().prepareStatement("select username from user_details where adhar_no=? ");
           ps2 = DBConnection.getConnection().prepareStatement("select distinct city from user_details");
            ps3 = DBConnection.getConnection().prepareStatement("insert into candidate values(?,?,?,?,?)");
            ps4 = DBConnection.getConnection().prepareStatement("select * from candidate where candidate_id=?");
            ps5 = DBConnection.getConnection().prepareStatement("select candidate_id, username, party, symbol from candidate, user_details where candidate.user_id=user_details.adhar_no and candidate.city=(select city from user_details where adhar_no=?)");
            ps6 = DBConnection.getConnection().prepareStatement("delete from candidate where user_id=? ");
            ps7 = DBConnection.getConnection().prepareStatement("select username, adhar_no from user_details where adhar_no=(select user_id from candidate where candidate_id=?)");
            ps8 = DBConnection.getConnection().prepareStatement("update candidate set city=?, party=?, symbol=? where candidate_id=? ");
           }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        
        
    }
    
    public static String getNewId() throws SQLException
    {
        ResultSet rs = ps.executeQuery() ;
        rs.next();
        String cid = rs.getString(1);
        
        if(cid==null)
            return"C101";
        else{
            int id = Integer.parseInt(cid.substring(1));
            return"C"+(id+1);
        }
    }
    
    
    public static String getUserNameById(String uid) throws SQLException
    {
        ps1.setString(1,uid) ;
        ResultSet rs = ps1.executeQuery() ;
        if(rs.next())
        {
            return rs.getString(1) ;
        }
        else
        {
            return null ;
        }
    }
    
    public static ArrayList<String> getCity() throws SQLException
    {
        ArrayList  <String> cityList = new ArrayList<>() ;
        ResultSet rs = ps2.executeQuery() ;
        while(rs.next())
        {
            cityList.add(rs.getString(1)) ;
        }
        return cityList ; 
    }
    
    public static boolean addCandidate(CandidateDTO obj)throws SQLException
    {
        ps3.setString(1,obj.getCandidateId());
        ps3.setString(2,obj.getParty());
        ps3.setString(3,obj.getUserid());
        ps3.setBinaryStream(4,obj.getSymbol());
        ps3.setString(5,obj.getCity()) ;
        
        return ps3.executeUpdate()!=0 ;
       
       
    }
    
    public static ArrayList<String> getCandidateId() throws SQLException
    {
         ArrayList <String> candidateIdList = new ArrayList<>() ;
        ResultSet rs = st.executeQuery("select candidate_id from candidate"); ;
        while(rs.next())
        {
            candidateIdList.add(rs.getString(1)) ;
        }
        return candidateIdList ; 
    }
    
    public static CandidateDetails getDetailsById(String cid) throws Exception
    {
        ps4.setString(1, cid);
        ResultSet rs = ps4.executeQuery() ;
        CandidateDetails cd = new CandidateDetails();
        Blob blob ;
        InputStream inputStream ; 
        byte[] buffer ;
        byte [] imageBytes ;
        int bytesRead ;
        String base64Image ;
        ByteArrayOutputStream outputStream ;
        
        
        // Image is converted from Blob to BASE64 Encoding 
        if(rs.next())
        {
            blob = rs.getBlob(4) ;
            inputStream = blob.getBinaryStream();
            outputStream = new ByteArrayOutputStream();
            buffer = new byte[4096] ;
            bytesRead = -1;
            while((bytesRead = inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer,0,bytesRead);
            }  
            imageBytes = outputStream.toByteArray();
            Base64.Encoder en = Base64.getEncoder();
            base64Image = en.encodeToString(imageBytes);
            cd.setSymbol(base64Image);
            cd.setCandidateId(cid) ;
            cd.setCandidateName(getUserNameById(rs.getString(3)));
            cd.setParty(rs.getString(2));
            cd.setCity(rs.getString(5));
            cd.setUserId(rs.getString(3));
           
         }
         return cd ; 
    }
    
    public static ArrayList<CandidateInfo> viewCandidate(String adhar_id) throws Exception
    {
      ArrayList<CandidateInfo> candidateList = new ArrayList<>();
      ps5.setString(1,adhar_id);
      ResultSet rs = ps5.executeQuery();
      Blob blob ;
      InputStream inputStream ; 
      byte[] buffer ;
      byte [] imageBytes ;
      int bytesRead ;
      String base64Image ;
      ByteArrayOutputStream outputStream ;        
        System.out.println("In view Candidate");
              
      while(rs.next())
      {
        blob = rs.getBlob(4) ;
        inputStream = blob.getBinaryStream();
        outputStream = new ByteArrayOutputStream();
        buffer = new byte[4096] ;
        bytesRead = -1;
        while((bytesRead = inputStream.read(buffer)) != -1)
        {
            outputStream.write(buffer,0,bytesRead);
        }  
        imageBytes = outputStream.toByteArray();
        Base64.Encoder en = Base64.getEncoder();
        base64Image = en.encodeToString(imageBytes);
        CandidateInfo cd = new CandidateInfo();
        cd.setSymbol(base64Image);
        cd.setCandidateId(rs.getString(1)) ;
        cd.setCandidateName(rs.getString(2));
        cd.setParty(rs.getString(3));
        candidateList.add(cd);
          System.out.println("Candidate details from loop : "+cd);
      
      }
    
      return candidateList ; 
    
    }  
    
    public static boolean removeCandidate(String uid) throws SQLException
    {
        ps6.setString(1,uid);
        
        return(ps6.executeUpdate() != 0 ) ; 
        
        
    }
    
    public static ArrayList<String> getCandidateName(String cid) throws SQLException
    {
       ps7.setString(1,cid) ;
       ResultSet rs = ps7.executeQuery() ;
       ArrayList<String> arr = new ArrayList<>() ; 
       
       if(rs.next())
       {
           System.out.println("Fetching candidate name "+rs.getString(1) +" , "+ rs.getString(2)); 
           arr.add(rs.getString(1));
           arr.add(rs.getString(2));
           return arr ; 
           
       }
           
       return null ; 
    }
    
    public static boolean updateCandidate(UpdateCandidateDTO obj) throws SQLException
    {
       ps8.setString(1, obj.getCity());
       ps8.setString(2,obj.getParty());
       ps8.setBinaryStream(3, obj.getSymbol());
       ps8.setString(4, obj.getCid());
       
       return ps8.executeUpdate() != 0 ;
    }
            
}
