/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dbutil;



import java.sql.*;

/**
 *
 * @author asus
 */
public class DBConnection {
    
    private static Connection conn = null;
    
    static
    {
        try
            
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver loaded successfully");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","evoting","evoting");
            System.out.println("Connection opened successfully");
            
        
        }
        
        catch(ClassNotFoundException cnfe)
        {
            System.out.println("Connection not opened");
            cnfe.printStackTrace();
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        
    }
    
    public static Connection getConnection()
    {
        return conn;
    }
    
    public static void closeConnection()
    {
        
        try{
            if(conn!=null)
            {
                conn.close();
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
}
