/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence;

import edu.eci.arsw.webstore.model.Product;
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

    //Consultas de Usuario
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
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql1 = "SELECT MAX(userid) FROM usr";
            pstmt = c.prepareStatement(sql1, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int newid = rs.getInt("max")+1;
            String sql = "INSERT INTO usr (userid,usertype,username,userlastname,useremail,usserpassword,usserimage,ussernickname,ussercode,userphone,userbalance,userfeedback) "
                    + "VALUES ('"+newid+"','2','null','null','"+us.getUserEmail()+"','"+us.getUserPassword()+"','null','"+us.getUserNickname()+"','123','0','0','0');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
        } catch (Exception e) {
        }
    }

    public User getUserByUsername(String username) {
        PreparedStatement pstmt = null;
        User u = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from usr where ussernickname = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            u = new User(rs.getString("useremail"), rs.getString("usserpassword"), rs.getString("ussernickname"));
            u.setIdUser(rs.getString("userid"));
            c.close();
            pstmt.close();
            rs.close();
            return u;
        } catch (Exception e) {
        }
        return null;
    }
    
    public void deleteUserByUsername(String username) {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "DELETE FROM usr WHERE ussernickname = '" + username + "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (Exception e) {
        }
    }
    
    //Consultas de Producto
    
    public List<Product> getAllProducts(){
        List<Product> allProduct = new ArrayList<Product>();
        Statement stmt = null;
        Product p = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product;");
            while ( rs.next() ) {
                p = new Product(rs.getInt("productid"), rs.getString("productname"), rs.getString("productdescription"), rs.getDouble("productprice"));
                allProduct.add(p);
            }
            c.close();
            stmt.close();
            rs.close();
        } catch (Exception e) {
        }
        return allProduct;
    }
    
    public List<Product> getAllProductsOfUser(String username){
        String SQL = "SELECT * FROM product WHERE productuser = ?";
        List<Product> allProductUser = new ArrayList<Product>();
        Product p = null;
        try {
            User u = getUserByUsername(username);
            int idUser = Integer.parseInt(u.getIdUser());
            getConnection();
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1,idUser);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                p = new Product(rs.getInt("productid"), rs.getString("productname"), rs.getString("productdescription"), rs.getDouble("productprice"));
                allProductUser.add(p);
            }
            c.close();
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allProductUser;
    }
}
