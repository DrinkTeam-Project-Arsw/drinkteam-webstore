/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence;

import edu.eci.arsw.webstore.model.Auction;
import edu.eci.arsw.webstore.model.Product;
import edu.eci.arsw.webstore.model.Transaction;
import edu.eci.arsw.webstore.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase WebStoreDB que permite realizar consultas sobre la base de datos.
 * @author Juan David
 */
public class WebStoreDB {

    //Atributos
    private static final String urlDb = "jdbc:postgresql://ec2-34-200-116-132.compute-1.amazonaws.com:5432/dc5qsgdq0jgp20?user=gpyoydzjumspiy&password=a92e5891a1f575b00c5319227c7f2acbadf68c4ef2dc9e1d35e76aab02c4a277";
    private Connection c;
    private User u;
    private Product p;
    private Auction a;
    private Transaction t;
    
    /**
     * Metodo que permite generar la conexión con la base de datos.
     */
    public void getConnection() {
        try {
            c = DriverManager.getConnection(urlDb);
        } catch (SQLException e) {
        }
    }

    //Consultas de Usuario
    
    /**
     * Metodo que permite consultar una lista con todos los usarios que estan en la base de datos.
     * @return  Retorna una lista de usuarios registrados en la base de datos.
     */
    public List<User> getAllUsers(){
        List<User> allUsers = new ArrayList<User>();
        Statement stmt = null;
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
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsers;
    }
    
    /**
     * Metodo que permite realizar la adición de un nuevo usario en la base de datos.
     * @param us    Es el usuario el cual se quiere agregar a la base de datos.
     */
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
            String sql = "INSERT INTO usr (userid,usertype,username,userlastname,useremail,usserpassword,usserimage,ussernickname,ussercode,userphone,userbalance,userfeedback) "
                    + "VALUES ('"+us.getIdUser()+"','user','null','null','"+us.getUserEmail()+"','"+us.getUserPassword()+"','null','"+us.getUserNickname()+"','+57','0','0','0');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que permite la consulta de un usuario por su userNickName.
     * @param userNickname  Es el Nickname del usuario.
     * @return  Retorna el usuario correspondiente con el NickName o null si no encuentra un usuario con ese nickName.
     */
    public User getUserByUserNickname(String userNickname) {
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from usr where ussernickname = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, userNickname);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            u = new User(rs.getString("useremail"), rs.getString("usserpassword"), rs.getString("ussernickname"));
            u.setIdUser(rs.getString("userid"));
            u.setUserType(rs.getString("usertype"));
            u.setUserName(rs.getString("username"));
            u.setUserLastName(rs.getString("userlastname"));
            u.setUserImage(rs.getString("usserimage"));
            u.setCodeCountry(rs.getString("ussercode"));
            u.setUserPhone(rs.getInt("userphone"));
            u.setUserBalance(rs.getDouble("userbalance"));
            u.setUserFeedback(rs.getInt("userfeedback"));
            getAllProductsOfUserNickname(userNickname);
            c.close();
            pstmt.close();
            rs.close();
            return u;
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String getUserNicknameByUserId(String userId) {
        PreparedStatement pstmt = null;
        String ans = "";
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from usr where userid = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            ans = rs.getString("ussernickname");
            c.close();
            pstmt.close();
            rs.close();
            return ans;
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }
    
    /**
     * Metodo que permite consultar un usuario por email.
     * @param email Es el email del usuario.
     * @return  Retorna el usuario correspondiente al email.
     */
    public User getUserByEmail(String email){
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from usr where useremail = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            u = new User(rs.getString("useremail"), rs.getString("usserpassword"), rs.getString("ussernickname"));
            u.setIdUser(rs.getString("userid"));
            u.setIdUser(rs.getString("userid"));
            u.setUserType(rs.getString("usertype"));
            u.setUserName(rs.getString("username"));
            u.setUserLastName(rs.getString("userlastname"));
            u.setUserImage(rs.getString("usserimage"));
            u.setCodeCountry(rs.getString("ussercode"));
            u.setUserPhone(rs.getInt("userphone"));
            u.setUserBalance(rs.getDouble("userbalance"));
            u.setUserFeedback(rs.getInt("userfeedback"));
            getAllProductsOfUserNickname(rs.getString("ussernickname"));
            c.close();
            pstmt.close();
            rs.close();
            return u;
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Metodo que permite eliminar un usuario de la base de datos.
     * @param userNickname  Es el nickName del usuario el cual se quiere eliminar.
     */
    public void deleteUserByUserNickname(String userNickname) {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "DELETE FROM usr WHERE ussernickname = '" + userNickname + "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
            u = getUserByUserNickname(userNickname);
            deleteAllProductByIdOfUserNickname(u.getIdUser());
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Consultas de Producto
    
    /**
     * Metodo que permite consular una lista con todos los productos registrados en el webStore.
     * @return  Retorna una lista de productos.
     */
    public List<Product> getAllProducts(){
        List<Product> allProduct = new ArrayList<Product>();
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product;");
            while ( rs.next() ) {
                p = new Product(rs.getInt("productid"), rs.getString("productname"), rs.getString("productdescription"), rs.getDouble("productprice"));
                allProduct.add(p);
                p.setProductUser(getUserNicknameByUserId(rs.getString("productuser")));
            }
            c.close();
            stmt.close();
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allProduct;
    }
    
    /**
     * Metodo que permite consultar todos los productos relacionados a un usuario.
     * @param userNickname  Es el nickName del usario.
     * @return  Retorna una lista de productos relacionados al usario dado.
     */
    public List<Product> getAllProductsOfUserNickname(String userNickname){
        String SQL = "SELECT * FROM product WHERE productuser = ?";
        List<Product> allProductUser = new ArrayList<Product>();
        try {
            if(u == (null)){
                u = getUserByUserNickname(userNickname);
            }
            getConnection();
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1,u.getIdUser());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                p = new Product(rs.getInt("productid"), rs.getString("productname"), rs.getString("productdescription"), rs.getDouble("productprice"));
                allProductUser.add(p);
            }
            //Se Agregan todos los productos al usuario.
            u.setProducts(allProductUser);
            c.close();
            pstmt.close();
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allProductUser;
    }
    
    private void deleteAllProductByIdOfUserNickname(String idUser) {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            //select * from product where productuser = '3' AND productid = '5';
            String sql1 = "DELETE FROM product WHERE productuser = '" + idUser+ "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
            System.out.println("Borre productos");
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
