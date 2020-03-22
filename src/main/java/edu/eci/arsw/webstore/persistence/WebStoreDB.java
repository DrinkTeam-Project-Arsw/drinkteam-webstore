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
import java.util.ArrayList;
import java.util.List;

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

    public List<User> getAllUsers(){
        List<User> allUsers = new ArrayList<User>();
        Statement stmt = null;
        User u = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usr;");
            while ( rs.next() ) {
                u = new User(rs.getString("useremail"), rs.getString("usserpassword"), rs.getString("ussernickname"));
                allUsers.add(u);
            }
            c.close();
            stmt.close();
            rs.close();
        } catch (Exception e) {
        }
        return allUsers;
    }
    
    public void createNewUser(User us) {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            System.out.println(us);
            System.out.println(us.getUserName() + " <--UserName");
            System.out.println(us.getUserLastName()+ " <--UserLastName");
            System.out.println(us.getUserEmail()+ " <--UserEmail");
            System.out.println(us.getUserPassword()+ " <--UserPassword");
            System.out.println(us.getUserName() + " <--UserName");
            String sql = "INSERT INTO usr (userid,username,userlastname,useremail,usserpassword,usserimage,ussernickname,ussercode,userphone,userbalance,userfeedback) "
                    + "VALUES ('10','"+us.getUserName()+"','"+us.getUserLastName()+"','"+us.getUserEmail()+"','"+us.getUserPassword()+"','"+us.getUserImage()+"','"+us.getUserNickname()+"','123','"+us.getUserPhone()+"','"+us.getUserBalance()+"','"+us.getUserFeedback()+"');";
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
            String sql = "Select * from usr where ussernickname = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            String rsl = null;
            User u = null;
            rs.next();
            rsl = rs.getString("usserpassword");
            u = new User(rs.getString("useremail"), rs.getString("usserpassword"), rs.getString("ussernickname"));
            c.close();
            pstmt.close();
            rs.close();
            return u;
        } catch (Exception e) {
        }
        return null;
    }
    
    public void deleteUserByUsername(String username) {
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "DELETE FROM usr WHERE ussernickname = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            c.close();
            pstmt.close();
            rs.close();
        } catch (Exception e) {
        }
    }
}
