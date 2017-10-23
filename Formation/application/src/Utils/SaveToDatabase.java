/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import MyExceptions.AddAdminException;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamedyassin
 */
public class SaveToDatabase {
    
    private static Connection conn;
    public static boolean addAdmin(String login, String pwd, String email, String Fname, String Lname) throws  ClassNotFoundException, SQLException, AddAdminException  {
        if (conn == null){
            conn = Connector.getConnection();
        }
        Statement st = conn.createStatement();
      
       
       try {
            int rs = st.executeUpdate("INSERT INTO admin(login, email, Fname, Lname, pwd)" +
                    "VALUES (\'"+login+"\',\'"+email+"\',\'"+Fname+"\',\'"+Lname+"\',\'"+pwd+"\');");
            if (rs == 0);
        } catch (SQLException ex) {
            System.out.println (ex.getMessage());
            throw new AddAdminException("login existe déjà !!");
         
                    
          
        }
        st.close();
        return true;
    }
    
    
}
