/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence;

import edu.eci.arsw.webstore.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Juan David
 */
public class WebStoreDB {

    //Atributos
    private static final String urlDb = "jdbc:postgresql://ec2-34-200-116-132.compute-1.amazonaws.com:5432/dc5qsgdq0jgp20?user=gpyoydzjumspiy&password=a92e5891a1f575b00c5319227c7f2acbadf68c4ef2dc9e1d35e76aab02c4a277";
    private Connection c = null;

    public void getConnection() {
        try {
            c = DriverManager.getConnection(urlDb);
        } catch (SQLException e) {
        }
    }

    public void createNewUser(User us) {
        System.out.println("WebStoreDB");
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO usr (userid,username,userlastname,useremail,usserpassword,usserimage,ussernickname,ussercode,userphone,userbalance,userfeedback) "
                    + "VALUES ('10','felipe','pelaez','dasdw.n@gmail.com','123','nose','juan43','123','123','2112','123');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
        } catch (Exception e) {
        }
    }

    public User getUserByUsername(String username) {
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from usr where username = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            String rsl = null;
            User u = null;
            rs.next();
            rsl = rs.getString("usserpassword");
            u = new User(rs.getString("useremail"), rs.getString("usserpassword"), rs.getString("username"));
            c.close();
            pstmt.close();
            rs.close();
            return u;
        } catch (Exception e) {
        }
        return null;
    }
}
