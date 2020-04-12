/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence;

import edu.eci.arsw.webstore.model.Product;
import java.util.List;

/**
 *
 * @author jmvillatei
 */
public interface ProductPersistence {
    
    /**
     * Este metodo permite obtener todos los productos de todos los usuarios
     *
     * @return Una lista con todos los productos
     */
    public List<Product> getAllProducts();
    
    /**
     * Este metodo permite obtener todos los productos de un usuario
     *
     * @param userNickname nickname del usuario
     * @return  Una lista con todos los productos
     */
    public List<Product> getAllProductsOfUserNickname(String userNickname);
    
    /**
     * este metodo permite crear un producto de un usuario especifico
     * 
     * @param pr
     */
    public void createNewProduct(Product pr);

    /**
     * Este metodo permite obtener un producto por id de un usuario
     *
     * @param id id del producto a obtener
     * @param idUser id del usuario
     * @return El producto que pertenece a ese usuario
     */
    public Product getProductByIdOfUserNickname(String id, String idUser);

    /**
     * Este metodo permite eliminar un producto de un usuario
     *
     * @param id
     * @param idUser
     */
    public void deleteProductByIdOfUserNickname(String id, String idUser);
    
    /**
     * Este metodo permite modificar un producto del usuario.
     * @param productId
     * @param pd
     */
    public void editProductById(String productId, Product pd);
}
