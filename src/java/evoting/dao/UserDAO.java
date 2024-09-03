/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dao;
import evoting.dbutil.DBConnection;
import evoting.dto.UserDTO;
import evoting.dto.UserInfo;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author asus
 */
public class UserDAO {
    
    private static PreparedStatement ps,ps1,ps3;
    private static Statement st ;
    static{
        
        try{
            
            ps=DBConnection.getConnection().prepareStatement("select user_type from user_details where adhar_no=? and password=?");
            st = DBConnection.getConnection().createStatement(); 
            ps1 = DBConnection.getConnection().prepareStatement("delete from user_details where adhar_no=?");
            ps3 = DBConnection.getConnection().prepareStatement("select adhar_no, username, address, city,email, mobile_no from user_details where adhar_no=?");
          
           }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        
        
    }
    
    public static String validateUser(UserDTO user)throws SQLException
    {
        ps.setString(1,user.getUserid());
        ps.setString(2,user.getPassword());
        
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            String str = rs.getString(1);
            return str;
        }
        return null;
    }
    
    public static ArrayList<UserInfo> getUserDetails()throws SQLException
    {
        ResultSet rs = st.executeQuery("select username,adhar_no,city,email from user_details");
        ArrayList<UserInfo> userList = new ArrayList<>() ;
        
        while(rs.next())
        {
           UserInfo user = new UserInfo() ; 
           user.setUsername(rs.getString(1));
           user.setUserid(rs.getString(2));
           user.setCity(rs.getString(3));
           user.setEmail(rs.getString(4));
           userList.add(user);
            System.out.println("Inside UserDAO users..."+user);
        }
        
        return userList ; 
        
    }
    
    public static ArrayList<String> getUserId() throws SQLException
    {
       ResultSet rs = st.executeQuery("select adhar_no from user_details ");
       ArrayList<String> userList = new ArrayList<>() ;
       
       while(rs.next())
       {
           userList.add(rs.getString(1));
       }
       System.out.println("USER ID : "+userList);
       return userList ; 
    
    }
    
    public static boolean removeUser(String usid)throws SQLException
    {
       ps1.setString(1,usid);
       
       return(ps1.executeUpdate()!= 0 );
    
    }
    
    public static ArrayList<String> getDetails(String usid) throws SQLException
    {
       ArrayList<String> details = new ArrayList<>() ;
       ps3.setString(1,usid);
       ResultSet rs = ps3.executeQuery();
       if(rs.next())
       {
         details.add(rs.getString(1));
         details.add(rs.getString(2));
         details.add(rs.getString(3));
         details.add(rs.getString(4));
         details.add(rs.getString(5));
         details.add(rs.getString(6));
         
         return details ; 
       }
       return null ;
    }
    
}
