/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDetails;
import java.sql.*;

/**
 *
 * @author asus
 */
public class RegistrationDao {
    
    private static PreparedStatement ps,ps1;
    
    static{
        try{
        ps = DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
        ps1 = DBConnection.getConnection().prepareStatement("insert into user_details values(?,?,?,?,?,?,?,?)");
        
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
    }
    
    public static boolean registerUser(UserDetails user) throws SQLException
    {
        ps1.setString(1,user.getUserId());
        ps1.setString(2,user.getPassword());
        ps1.setString(3,user.getUsername());
        ps1.setString(4,user.getAddress());
        ps1.setString(5,user.getCity());
        ps1.setString(6,user.getEmail());
        ps1.setLong(7,user.getMobile());
        ps1.setString(8,"voter");
        
        return (ps1.executeUpdate()==1);
    }
    
    public static boolean searchUser(String userid) throws SQLException
    {
        ps.setString(1,userid);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            return true;
        }
        else
        return false ;
    }
    
}
