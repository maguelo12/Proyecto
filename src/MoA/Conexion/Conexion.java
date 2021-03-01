/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoA.Conexion;

import java.sql.*;


/**
 *
 * @author elmej
 */
public class Conexion {
    
    
    public static Connection getConexion() throws ClassNotFoundException, SQLException{
        
        
       Class.forName("com.mysql.jdbc.Driver");
       
       return DriverManager.getConnection("jdbc:mysql://localhost:3306/restassistan", "root", "retrato1203mamaguelo");
        
        
    }
    
    public static void close(ResultSet rs) throws SQLException{
        
        rs.close();
        
    }
    
    public static void close(PreparedStatement stmt) throws SQLException{
        
        stmt.close();
        
        
    }
    
    public static void close(Connection cnn) throws SQLException{
        
        cnn.close();
        
    }
    
    public static void close(Statement stmt) throws SQLException{
        
        stmt.close();
        
    }
    
}
