/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence;

import edu.eci.arsw.webstore.model.Auction;
import edu.eci.arsw.webstore.model.Message;
import edu.eci.arsw.webstore.model.Notification;
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
 * 
 * @author Juan David
 */
public class WebStoreDB {

    // Atributos
    // Conexion Base de datos Heroku
    private static final String urlDb = "jdbc:postgresql://ec2-34-200-116-132.compute-1.amazonaws.com:5432/dc5qsgdq0jgp20?user=gpyoydzjumspiy&password=a92e5891a1f575b00c5319227c7f2acbadf68c4ef2dc9e1d35e76aab02c4a277";
    // Conexion Base de datos Aws
    // private static final String urlDb =
    // "jdbc:postgresql://arsw.cu0adiovages.us-east-1.rds.amazonaws.com:5432/arsw?user=arsw&password=arsw1234";
    private Connection c;
    private User u;
    private Product p;
    private Auction a;
    private Transaction t;
    private Message m;
    private Notification n;

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
     * Metodo que permite consultar una lista con todos los usarios que estan en la
     * base de datos.
     * 
     * @return Retorna una lista de usuarios registrados en la base de datos.
     */
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM usr;")) {
                c.close();
                while (rs.next()) {
                    u = new User(rs.getString("useremail"), rs.getString("usserpassword"), rs.getString("ussernickname"));
                    allUsers.add(u);
                }
                stmt.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsers;
    }

    /**
     * Metodo que permite realizar la adición de un nuevo usario en la base de
     * datos.
     * 
     * @param us Es el usuario el cual se quiere agregar a la base de datos.
     */
    public void createNewUser(User us) {
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO usr (userid,usertype,username,userlastname,useremail,usserpassword,usserimage,ussernickname,ussercode,userphone,userbalance,userfeedback,useractive) "
                    + "VALUES ('" + us.getIdUser() + "','user','','','" + us.getUserEmail() + "','"
                    + us.getUserPassword() + "','','" + us.getUserNickname() + "','','0','0','0',false);";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que permite la consulta de un usuario por su userNickName.
     * 
     * @param userNickname Es el Nickname del usuario.
     * @return Retorna el usuario correspondiente con el NickName o null si no
     *         encuentra un usuario con ese nickName.
     */
    public User getUserByUserNickname(String userNickname) {
        PreparedStatement pstmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from usr where ussernickname = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, userNickname);
            try (ResultSet rs = pstmt.executeQuery()) {
                c.close();
                if (rs.next()) {
                    u = new User(rs.getString("useremail"), rs.getString("usserpassword"), rs.getString("ussernickname"));
                    u.setIdUser(rs.getString("userid"));
                    u.setUserType(rs.getString("usertype"));
                    u.setUserName(rs.getString("username"));
                    u.setUserLastName(rs.getString("userlastname"));
                    u.setUserImage(rs.getString("usserimage"));
                    u.setCodeCountry(rs.getString("ussercode"));
                    u.setUserPhone(rs.getString("userphone"));
                    u.setUserBalance(rs.getDouble("userbalance"));
                    u.setUserFeedback(rs.getInt("userfeedback"));
                    u.setUserActive(rs.getBoolean("useractive"));
                    u.setUserAddress(rs.getString("useraddress"));
                    getAllProductsOfUserNickname(userNickname);
                }
                pstmt.close();
            }
            return u;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public User getUserByEmail(String email) {
        PreparedStatement pstmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from usr where useremail = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                c.close();
                if (rs.next()) {
                    u = new User(rs.getString("useremail"), rs.getString("usserpassword"), rs.getString("ussernickname"));
                    u.setIdUser(rs.getString("userid"));
                    u.setUserType(rs.getString("usertype"));
                    u.setUserName(rs.getString("username"));
                    u.setUserLastName(rs.getString("userlastname"));
                    u.setUserImage(rs.getString("usserimage"));
                    u.setCodeCountry(rs.getString("ussercode"));
                    u.setUserPhone(rs.getString("userphone"));
                    u.setUserBalance(rs.getDouble("userbalance"));
                    u.setUserFeedback(rs.getInt("userfeedback"));
                    u.setUserActive(rs.getBoolean("useractive"));
                    u.setUserAddress(rs.getString("useraddress"));
                    getAllProductsOfUserNickname(u.getUserNickname());
                }
                pstmt.close();
            }
            return u;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    private String getUserNicknameByUserId(String userId) {
        PreparedStatement pstmt;
        String ans = "";
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from usr where userid = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                ans = rs.getString("ussernickname");
                c.close();
                pstmt.close();
            }
            return ans;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }

    public User getUserById(String id) {
        PreparedStatement pstmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from usr where userid = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                c.close();
                u = new User(rs.getString("useremail"), rs.getString("usserpassword"), rs.getString("ussernickname"));
                u.setIdUser(rs.getString("userid"));
                u.setUserType(rs.getString("usertype"));
                u.setUserName(rs.getString("username"));
                u.setUserLastName(rs.getString("userlastname"));
                u.setUserImage(rs.getString("usserimage"));
                u.setCodeCountry(rs.getString("ussercode"));
                u.setUserPhone(rs.getString("userphone"));
                u.setUserBalance(rs.getDouble("userbalance"));
                u.setUserFeedback(rs.getInt("userfeedback"));
                u.setUserActive(rs.getBoolean("useractive"));
                u.setUserAddress(rs.getString("useraddress"));
                getAllProductsOfUserNickname(rs.getString("ussernickname"));
                pstmt.close();
            }
            return u;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * Metodo que permite consultar un usuario por email.
     * 
     * @param user Es el objeto del usuario.
     */
    public void updateUser(User user) {
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "UPDATE usr SET username = '" + user.getUserName() + "', userlastname = '"
                    + user.getUserLastName() + "', usserimage = '" + user.getUserImage() + "', ussercode = '"
                    + user.getCodeCountry() + "', userphone = '" + user.getUserPhone() + "', userbalance = '"
                    + user.getUserBalance() + "', useraddress = '" + user.getUserAddress() + "', useractive = '"
                    + user.getUserActive() + "', userfeedback = '" + user.getUserFeedback() + "'  WHERE userid = '"
                    + user.getIdUser() + "' ";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que permite eliminar un usuario de la base de datos.
     * 
     * @param userNickname Es el nickName del usuario el cual se quiere eliminar.
     */
    public void deleteUserByUserNickname(String userNickname) {
        Statement stmt;
        try {
            u = getUserByUserNickname(userNickname);
            deleteAllProductByIdOfUserNickname(u.getIdUser());
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            String sql1 = "DELETE FROM usr WHERE ussernickname = '" + userNickname + "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /****/
    //// BASE DE DATOS - Consultas de Producto
    /****/

    /**
     * Metodo que permite consular una lista con todos los productos registrados en
     * el webStore.
     * 
     * @return Retorna una lista de productos.
     */
    public List<Product> getAllProducts() {
        List<Product> allProduct = new ArrayList<>();
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM product where productauction is null ;")) {
                c.close();
                while (rs.next()) {
                    p = new Product(rs.getString("productname"), rs.getString("productdescription"),
                            rs.getDouble("productprice"), rs.getString("productImage"));
                    p.setProductId(rs.getString("productid"));
                    p.setProductUser(getUserNicknameByUserId(rs.getString("productuser")));
                    if (getTransactionByProductId(p.getProductId()) == null) {
                        allProduct.add(p);
                    }
                }
                stmt.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allProduct;
    }

    /**
     * Metodo que permite consultar todos los productos relacionados a un usuario.
     * 
     * @param userNickname Es el nickName del usario.
     * @return Retorna una lista de productos relacionados al usario dado.
     */
    public List<Product> getAllProductsOfUserNickname(String userNickname) {
        String SQL = "SELECT * FROM product WHERE productuser = ?";
        List<Product> allProductUser = new ArrayList<>();
        try {
            if (u == (null)) {
                u = getUserByUserNickname(userNickname);
            }
            getConnection();
            ResultSet rs;
            try (PreparedStatement pstmt = c.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)) {
                pstmt.setString(1, u.getIdUser());
                rs = pstmt.executeQuery();
                c.close();
                while (rs.next()) {
                    p = new Product(rs.getString("productname"), rs.getString("productdescription"),
                            rs.getDouble("productprice"), rs.getString("productImage"));
                    p.setProductId(rs.getString("productid"));
                    
                    allProductUser.add(p);
                }   // Se Agregan todos los productos al usuario.
                u.setProducts(allProductUser);
            }
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allProductUser;
    }

    public List<Product> getAllProductsOfUserNicknameWithoutAuction(String userNickname) {
        String SQL = "SELECT * FROM product WHERE productuser = ? and productauction is null";
        List<Product> allProductUser = new ArrayList<>();
        try {
            if (u == (null)) {
                u = getUserByUserNickname(userNickname);
            }
            getConnection();
            ResultSet rs;
            try (PreparedStatement pstmt = c.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)) {
                pstmt.setString(1, u.getIdUser());
                rs = pstmt.executeQuery();
                c.close();
                while (rs.next()) {
                    p = new Product(rs.getString("productname"), rs.getString("productdescription"),
                            rs.getDouble("productprice"), rs.getString("productImage"));
                    p.setProductId(rs.getString("productid"));
                    if (getTransactionByProductId(p.getProductId()) == null) {
                        allProductUser.add(p);
                    }
                }   // Se Agregan todos los productos al usuario.
                u.setProducts(allProductUser);
            }
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allProductUser;
    }

    public Product getProductByIdOfUserNickname(String id) {
        String SQL = "SELECT * FROM product WHERE productid = ?";
        Product product = null;
        try {

            getConnection();
            ResultSet rs;
            try (PreparedStatement pstmt = c.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)) {
                pstmt.setString(1, id);
                rs = pstmt.executeQuery();
                c.close();
                while (rs.next()) {
                    p = new Product(rs.getString("productname"), rs.getString("productdescription"),
                            rs.getDouble("productprice"), rs.getString("productImage"));
                    p.setProductId(rs.getString("productid"));
                    
                    product = p;
                }
            }
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    /**
     * Metodo que permite registrar un nuevo producto en la base de datos.
     * 
     * @param pd Es el producto que se agregara a la base de datos.
     * 
     */
    public void createNewProduct(Product pd) {
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO product (productid,productname,productdescription,productprice,productuser,productauction,productImage) "
                    + "VALUES ('" + pd.getProductId() + "','" + pd.getProductName() + "','" + pd.getProductDescription()
                    + "','" + pd.getProductPrice() + "','" + pd.getProductUser() + "',null,'" + pd.getProductImage()
                    + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que edita un producto asociado al usuario.
     * 
     * @param productId Id del producto
     * @param pd        Es el producto.
     */
    public void editProductById(String productId, Product pd) {
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "UPDATE product SET productname = '" + pd.getProductName() + "', productdescription = '"
                    + pd.getProductDescription() + "' , productprice = '" + pd.getProductPrice()
                    + "' , productImage = '" + pd.getProductImage() + "' WHERE productid = '" + productId + "' ";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que elimina un producto asociado al usuario.
     * 
     * @param id     Id del producto
     * @param idUser Id del usuario.
     */
    public void deleteProductByIdOfUserNickname(String id, String idUser) {
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "DELETE FROM product WHERE productuser = '" + idUser + "' AND productid = '" + id + "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que elimina todos los productos cuando un usuario es eliminado.
     * 
     * @param idUser Es el id del usuario
     */
    private void deleteAllProductByIdOfUserNickname(String idUser) {
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            // select * from product where productuser = '3' AND productid = '5';
            String sql1 = "DELETE FROM product WHERE productuser = '" + idUser + "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /****/
    //// BASE DE DATOS - Consultas de Transaacciones
    /****/

    /**
     * Metodo que permite consultar una lista con todos las transacciones que estan
     * en la base de datos.
     * 
     * @return Retorna una lista de transacciones registradas en la base de datos.
     */
    public List<Transaction> getAllTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM transaction;")) {
                c.close();
                while (rs.next()) {
                    t = new Transaction(rs.getString("buyer"), rs.getString("seller"), rs.getString("product"));
                    t.setTransactionId(rs.getString("transactionid"));
                    t.setTransactionPrice(rs.getDouble("transactionprice"));
                    t.setTransactionDate(rs.getString("transactiondate"));
                    t.setTransactionActive(rs.getBoolean("transactionactive"));
                    t.setTransactionDateEnd(rs.getString("transactiondateend"));
                    t.setTransactionState(rs.getString("transactionstate"));
                    allTransactions.add(t);
                }
                stmt.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allTransactions;
    }

    /**
     * Metodo que permite la consulta de un transaccion por su Id.
     * 
     * @param transactionId Es el Id de la transaccion.
     * @return Retorna la tracsaccion correspondiente con el iD o null si no
     *         encuentra la transaccion con ese Id.
     */
    public Transaction getTransactionById(String transactionId) {
        PreparedStatement pstmt;
        t = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from transaction where transactionid = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, transactionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                c.close();
                if (rs.next()) {
                    t = new Transaction(rs.getString("buyer"), rs.getString("seller"), rs.getString("product"));
                    t.setTransactionId(rs.getString("transactionid"));
                    t.setTransactionPrice(rs.getDouble("transactionprice"));
                    t.setTransactionDate(rs.getString("transactiondate"));
                    t.setTransactionActive(rs.getBoolean("transactionactive"));
                    t.setTransactionDateEnd(rs.getString("transactiondateend"));
                    t.setTransactionState(rs.getString("transactionstate"));
                }
                pstmt.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public Transaction getTransactionByProductId(String productId) {
        PreparedStatement pstmt;
        t = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from transaction where product = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                c.close();
                if (rs.next()) {
                    t = new Transaction(rs.getString("buyer"), rs.getString("seller"), rs.getString("product"));
                    t.setTransactionId(rs.getString("transactionid"));
                    t.setTransactionPrice(rs.getDouble("transactionprice"));
                    t.setTransactionDate(rs.getString("transactiondate"));
                    t.setTransactionActive(rs.getBoolean("transactionactive"));
                    t.setTransactionDateEnd(rs.getString("transactiondateend"));
                    t.setTransactionState(rs.getString("transactionstate"));
                }
                pstmt.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;

    }

    /**
     * Este metodo permite obtener las transacciones de un usuario
     *
     * @param userId id del usuario
     * @return lista de transacciones
     */
    public List<Transaction> getTransactionsOfUserById(String userId) {
        List<Transaction> allTransactions = new ArrayList<>();
        PreparedStatement pstmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from transaction where seller = ? OR buyer = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, userId);
            pstmt.setString(2, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                c.close();
                while (rs.next()) {
                    t = new Transaction(rs.getString("buyer"), rs.getString("seller"), rs.getString("product"));
                    t.setTransactionId(rs.getString("transactionid"));
                    t.setTransactionPrice(rs.getDouble("transactionprice"));
                    t.setTransactionDate(rs.getString("transactiondate"));
                    t.setTransactionActive(rs.getBoolean("transactionactive"));
                    t.setTransactionDateEnd(rs.getString("transactiondateend"));
                    t.setTransactionState(rs.getString("transactionstate"));
                    allTransactions.add(t);
                }
                pstmt.close();
            }
            return allTransactions;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * Metodo que permite realizar la adición de una nueva transaccion en la base de
     * datos.
     * 
     * @param tr Es la transaccion el cual se quiere agregar a la base de datos.
     */
    public void createNewtransaction(Transaction tr) {
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO transaction (transactionid,transactionprice,transactiondate,transactiondateend,transactionactive,buyer,seller,product,transactionstate) "
                    + "VALUES ('" + tr.getTransactionId() + "','" + tr.getTransactionPrice() + "','"
                    + tr.getTransactionDate() + "','" + tr.getTransactionDateEnd() + "','" + tr.getTransactionActive()
                    + "', '" + tr.getBuyer() + "','" + tr.getSeller() + "','" + tr.getProduct() + "','"
                    + tr.getTransactionState() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTransaction(Transaction tr){
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "UPDATE transaction SET transactionstate = '" + tr.getTransactionState() + "', transactionactive = '"
            + tr.getTransactionActive() +"' , transactionDateEnd = '"
            + tr.getTransactionDateEnd() +"'  WHERE transactionid = '" + tr.getTransactionId() + "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteTransactionById(String transactionId) {
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "DELETE FROM transaction WHERE transactionid = '" + transactionId + "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /****/
    //// BASE DE DATOS - Consultas de Mensajes
    /****/

    /**
     * 
     * @param transactionId
     * @return
     */
    public List<Message> getMessagesByTransactionId(String transactionId) {
        String SQL = "SELECT * FROM message WHERE messagetransaction = ?";
        List<Message> allMessageTransaction = new ArrayList<>();
        try {
            getConnection();
            ResultSet rs;
            try (PreparedStatement pstmt = c.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)) {
                pstmt.setString(1, transactionId);
                rs = pstmt.executeQuery();
                c.close();
                while (rs.next()) {
                    m = new Message(rs.getString("messagetransaction"), rs.getString("messageuser"),
                            rs.getString("messagedata"), rs.getString("messageuserimage"));
                    allMessageTransaction.add(m);
                }
            }
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allMessageTransaction;
    }

    /**
     * Metodo que permite registrar un nuevo mensaje en la base de datos.
     * 
     * @param msg Es el producto que se agregara a la base de datos.
     * 
     */
    public void createNewMessage(Message msg) {
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO message (messageid,messagetransaction,messageuser,messagedata,messageuserimage) "
                    + "VALUES ('" + msg.getId() + "','" + msg.getIdTransaction() + "','" + msg.getUser() + "','"
                    + msg.getData() + "','" + msg.getUserImage() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /****/
    //// BASE DE DATOS - Consultas de Subastas
    /****/

    /**
     * Metodo que permite consultar todas las subastas de la pagina web.
     * 
     * @return Retorna una lista con todas las subastas que hay en la pagina.
     */
    public List<Auction> getAllAuctions() {
        List<Auction> allAuctions = new ArrayList<>();
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM auction;")) {
                c.close();
                while (rs.next()) {
                    a = new Auction(rs.getDouble("auctioninitprice"), rs.getString("auctiondate"),
                            rs.getString("auctiondatefinal"), rs.getInt("auctiontimetowait"), rs.getInt("auctiontype"),
                            rs.getBoolean("auctionactive"), rs.getString("seller"), rs.getString("product"),
                            rs.getString("auctionstatus"), rs.getString("productname"));
                    a.setAuctionId(rs.getString("auctionid"));
                    a.setBuyers(getAllBuyersInAuction(a.getAuctionId()));
                    allAuctions.add(a);
                }
                stmt.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allAuctions;
    }

    public List<Auction> getAuctionsByUsername(String username) {
        List<Auction> allAuctionsByUsername = new ArrayList<>();
        PreparedStatement pstmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from auction where seller = ? ";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                c.close();
                while (rs.next()) {
                    a = new Auction(rs.getDouble("auctioninitprice"), rs.getString("auctiondate"),
                            rs.getString("auctiondatefinal"), rs.getInt("auctiontimetowait"), rs.getInt("auctiontype"),
                            rs.getBoolean("auctionactive"), rs.getString("seller"), rs.getString("product"),
                            rs.getString("auctionstatus"), rs.getString("productname"));
                    a.setAuctionId(rs.getString("auctionid"));
                    a.setBuyers(getAllBuyersInAuction(a.getAuctionId()));
                    allAuctionsByUsername.add(a);
                }
                pstmt.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allAuctionsByUsername;
    }

    /**
     * Metodo que permite realizar la consulade de subasta por id.
     * 
     * @param auctionId Es el id de la subasta.
     * @return Retorna la subasta correspondiente al id.
     */
    public Auction getAuctionById(String auctionId) {
        PreparedStatement pstmt;
        a = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from auction where auctionid = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, auctionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                c.close();
                if (rs.next()) {
                    a = new Auction(rs.getDouble("auctioninitprice"), rs.getString("auctiondate"),
                            rs.getString("auctiondatefinal"), rs.getInt("auctiontimetowait"), rs.getInt("auctiontype"),
                            rs.getBoolean("auctionactive"), rs.getString("seller"), rs.getString("product"),
                            rs.getString("auctionstatus"), rs.getString("productname"));
                    a.setAuctionId(rs.getString("auctionid"));
                    a.setBuyers(getAllBuyersInAuction(a.getAuctionId()));
                }
                pstmt.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;

    }

    /**
     * Metodo que permite crear la subasta en la base de datos.
     * 
     * @param au Es la subasta que se va a agregar a la base de datos.
     */
    public void createNewAuction(Auction au) {
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO auction (auctionid,auctioninitprice,auctioncurrentprice,auctionfinalprice,auctiondate,auctiondatefinal,auctiontimetowait,auctiontype,auctionactive,seller,product,auctionstatus,productname) "
                    + "VALUES ('" + au.getAuctionId() + "','" + au.getAuctionInitPrice() + "','"
                    + au.getAuctionCurrentPrice() + "','" + au.getAuctionFinalPrice() + "','" + au.getAuctionDate()
                    + "','" + au.getAuctionDateFinal() + "','" + au.getAuctionTimeToWait() + "','" + au.getAuctionType()
                    + "','" + au.isAuctionActive() + "','" + au.getSellerId() + "','" + au.getProductId() + "','"
                    + au.getAuctionStatus() + "','" + au.getProductName() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que permite eliminar una subasta de la base de datos.
     * 
     * @param auctionId Es el id de la subasta que se quiere eliminar.
     */
    public void deleteAuctionById(String auctionId) {
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "DELETE FROM auction WHERE auctionid = '" + auctionId + "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que permite eliminar todos los participantes en la subasta.
     * 
     * @param auctionId Es el id de la subasta.
     */
    public void deleteBuyersInAuction(String auctionId) {
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "DELETE FROM buyers WHERE auction = '" + auctionId + "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addUsersInAuction(String auctionId, String userId) {
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO buyers (auction,buyer) " + "VALUES ('" + auctionId + "','" + userId + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<User> getAllBuyersInAuction(String auctionId) {
        List<User> allUserInAuction = new ArrayList<>();
        PreparedStatement pstmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from buyers where auction = ?";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, auctionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                c.close();
                while (rs.next()) {
                    u = getUserById(rs.getString("buyer"));
                    allUserInAuction.add(u);
                }
                pstmt.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUserInAuction;
    }

    // #########################
    /// NOTIFICACIONES
    // ##########################

    /**
     * Metodo que permite realizar la adición de una nueva notificacion en la base de
     * datos.
     * 
     * @param noti Es la notificacion que se quiere agregar a la base de datos.
     */
    public void createNewNotification(Notification noti) {
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO notification "
                    + "VALUES ('" + noti.getNotificationId() + "','" + noti.getNotificationDestination() + "','" 
                    + noti.getNotificationMessage() + "','" + noti.getNotificationUrl() + "','" +
                    noti.getNotificationDate() + "','" + noti.getNotificationSend() + "', false ,'" + noti.getNotificationFunction() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que permite consultar todas las notificaciones de un usuario
     * @param nickname  Es el nickname del usario.
     * @return  Retorna una lista con las notificaciones del usuario.
     */
    public List<Notification> getNotificationsByNickname(String nickname) {
        ArrayList<Notification> allNotificationsByNickname= new ArrayList<>();
        PreparedStatement pstmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from notification where notificationdestination = ? ";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, nickname);
            try (ResultSet rs = pstmt.executeQuery()) {
                c.close();
                while (rs.next()) {
                    n = new Notification(rs.getString("notificationmessage"),rs.getString("norificationdate"), rs.getString("notificationdestination"),
                            rs.getString("notificationsend"), rs.getString("notificationurl"), rs.getString("notificationfunction"), rs.getBoolean("notificationviewed"));
                    n.setNotificationId(rs.getString("notificationid"));
                    allNotificationsByNickname.add(n);
                }
                pstmt.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allNotificationsByNickname;
    }
    
    /**
     * Metodo que permite realizar la consulta de una notificacion por ID
     * @param notificationId    Es el id de la notificacion.
     * @return  Retorna la notificacion correspondiente al id.
     */
    public Notification getNotificationsById(String notificationId) {
        PreparedStatement pstmt;
        n = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "Select * from notification where notificationid = ? ";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, notificationId);
            try (ResultSet rs = pstmt.executeQuery()) {
                c.close();
                if(rs.next()) {
                    n = new Notification(rs.getString("notificationmessage"),rs.getString("norificationdate"), rs.getString("notificationdestination"),
                            rs.getString("notificationsend"), rs.getString("notificationurl"), rs.getString("notificationfunction"), rs.getBoolean("notificationviewed"));
                    n.setNotificationId(rs.getString("notificationid"));
                }
                pstmt.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    /**
     * Metodo que permite cambiar el estado de la notificacion, solo debe cambiar el estado de notificationViewed
     * @param viewed    Es el nuevo valor de viewed
     * @param notificationId    Es el id de la notificacion.
     */
    public void changeNotificationStatus(boolean viewed, String notificationId){
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "UPDATE notification SET notificationviewed = '" + viewed + "'  WHERE notificationid = '" + notificationId + "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que permite qur permite eliminar una notificaion
     * @param notificationId    Es el id de la notificacion
     */
    public void deleteNotificationById(String notificationId){
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql1 = "DELETE FROM notification WHERE notificationid = '" + notificationId + "'";
            stmt = c.createStatement();
            stmt.executeUpdate(sql1);
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebStoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}