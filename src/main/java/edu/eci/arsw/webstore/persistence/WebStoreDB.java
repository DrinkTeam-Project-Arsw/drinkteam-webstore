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

    /****/
    //// BASE DE DATOS - Consultas de Usuario
    /****/
    
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
        System.out.println("enviado4");
        try {
            Class.forName("org.postgresql.Driver"); 
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            System.out.println("Se totio ?");
            String sql = "INSERT INTO usr (userid,usertype,username,userlastname,useremail,usserpassword,usserimage,ussernickname,ussercode,userphone,userbalance,userfeedback,useractive) "
                    + "VALUES ('"+us.getIdUser()+"','user','','','"+us.getUserEmail()+"','"+us.getUserPassword()+"','','"+us.getUserNickname()+"','','0','0','0',false);";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            System.out.println("COrrecto");
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
            u.setUserActive(rs.getBoolean("useractive"));
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
     * @param user Es el objeto del usuario.
     */
    public void updateUser(User user){
        Statement stmt  = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "UPDATE usr SET username = '" + user.getUserName() + 
                "', userlastname = '" + user.getUserLastName() +
                "', usserimage = '" + user.getUserImage() +
                "', ussercode = '" + user.getCodeCountry() +
                "', userphone = '" + user.getUserPhone() +
                "', userbalance = '" + user.getUserBalance() +
                "', userfeedback = '" + user.getUserFeedback() +
                "'  WHERE userid = '" + user.getIdUser() + "' ";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    /****/
    //// BASE DE DATOS - Consultas de Producto
    /****/
    
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
                p = new Product( rs.getString("productname"), rs.getString("productdescription"), rs.getDouble("productprice"), rs.getString("productImage"));
                p.setProductId(rs.getString("productid"));
                p.setProductUser(getUserNicknameByUserId(rs.getString("productuser")));
                allProduct.add(p);
                
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
                p = new Product(rs.getString("productname"), rs.getString("productdescription"), rs.getDouble("productprice"), rs.getString("productImage"));
                p.setProductId(rs.getString("productid"));
                
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
    
    /**
     * Metodo que permite registrar un nuevo producto en la base de datos. 
     * @param pd    Es el producto que se agregara a la base de datos.
     * 
     */
    public void createNewProduct(Product pd) {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO product (productid,productname,productdescription,productprice,productuser,productauction,productImage) "
			+ "VALUES ('"+pd.getProductId()+"','"+pd.getProductName()+"','"+pd.getProductDescription()+"','"+pd.getProductPrice()+"','"+pd.getProductUser()+"',null,'"+pd.getProductImage()+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que edita un producto asociado al usuario.
     * @param productId    Id del producto
     * @param pd    Es el producto.
     */
    public void editProductById(String productId, Product pd) {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "UPDATE product SET productname = '" + pd.getProductName() + "', productdescription = '" + pd.getProductDescription() + "' , productprice = '" + pd.getProductPrice() + "' , productImage = '" + pd.getProductImage() + "' WHERE productid = '" + productId + "' ";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que elimina un producto asociado al usuario.
     * @param id    Id del producto
     * @param idUser    Id del usuario.
     */
    public void deleteProductByIdOfUserNickname(String id, String idUser) {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "DELETE FROM product WHERE productuser = '" + idUser + "' AND productid = '" + id + "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que elimina todos los productos cuando un usuario es eliminado.
     * @param idUser    Es el id del usuario
     */
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

    /****/
    //// BASE DE DATOS - Consultas de Transaacciones
    /****/

    /**
     * Metodo que permite consultar una lista con todos las transacciones que estan en la base de datos.
     * @return  Retorna una lista de transacciones registradas en la base de datos.
     */
    public List<Transaction> getAllTransactions(){
        List<Transaction> allTransactions = new ArrayList<Transaction>();
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transaction;");
            while ( rs.next() ) {
                t = new Transaction(rs.getString("transactionid"), rs.getInt("transactionprice"), rs.getTimestamp("transactiondate"), rs.getBoolean("transactionactive"), rs.getString("buyer"), rs.getString("seller"), rs.getString("product"));
                allTransactions.add(t);
            }
            c.close();
            stmt.close();
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allTransactions;
    }

     /**
     * Metodo que permite la consulta de un transaccion por su Id.
     * @param transactionId  Es el Id de la transaccion.
     * @return  Retorna la tracsaccion correspondiente con el iD o null si no encuentra la transaccion con ese Id.
     */
    public Transaction getTransactionById(String transactionId) {
        System.out.println("En DB");
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            System.out.println("conecction");
            c.setAutoCommit(false);
            String sql = "Select * from transaction where transactionid = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, transactionId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            System.out.println("va a ser T");
            t = new Transaction(rs.getString("transactionid"), rs.getInt("transactionprice"), rs.getTimestamp("transactiondate"),rs.getBoolean("transactionactive"), rs.getString("buyer"), rs.getString("seller"), rs.getString("product"));
            System.out.println(t+"   <----- T mk");
            c.close();
            pstmt.close();
            rs.close();
            return t;
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Metodo que permite realizar la adición de una nueva transaccion en la base de datos.
     * @param tr    Es la transaccion el cual se quiere agregar a la base de datos.
     */
    public void createNewtransaction(Transaction tr) {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver"); 
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO transaction (transactionid,transactionprice,transactiondate,transactiondateend,transactionactive,buyer,seller,product) "
                    + "VALUES ('"+tr.getTransactionId()+"','"+tr.getTransactionPrice()+"','"+tr.getTransactionDate()+"','"+tr.getTransactionDateEnd()+"','"+tr.getTransactionActive()+"', '"+tr.getBuyerId()+"','"+tr.getSellerId()+"','"+tr.getProduct()+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteTransactionById(String transactionId) {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "DELETE FROM transaction WHERE transactionid = '" + transactionId + "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
