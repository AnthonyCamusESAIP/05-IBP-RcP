/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import MyExceptions.AuthException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mohamedyassin
 */
public class Auth {
   
    public static boolean authenticate (String login, String pwd) throws SQLException, ClassNotFoundException, AuthException{
        Connection conn = Connector.getConnection();
        if (conn != null) {
            System.out.println("connected to bdd");
            Statement st = conn.createStatement();
            ResultSet rs ;
            rs = st.executeQuery("SELECT * FROM admin WHERE login = \'"+login+"\'");
            if (rs.next()){
                if((rs.getString("pwd")).equals(pwd)){
                    System.out.println("USER AUTHENTICATED");
                } else {
                    throw(new AuthException("login et/ou mot de passe incorrectes"));
                }
            } else {
                throw(new AuthException("login et/ou mot de passe incorrectes"));
            }
            rs.close();
            st.close();
           
        }
        return true;
    }
}
