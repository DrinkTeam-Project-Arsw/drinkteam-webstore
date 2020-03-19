/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.services.product;

import edu.eci.arsw.webstore.model.Product;
import java.util.List;

/**
 *
 * @author jmvillatei
 */

public interface ProductServices {
    
    /**
     * Este metodo permite obtener todos los productos de todos los usuarios
     *
     * @return Una lista con todos los productos
     */
    public List<Product> getAllProducts();
    
    /**
     * Este metodo permite obtener todos los productos de un usuario
     *
     * @param username nickname del usuario
     * @return  Una lista con todos los productos
     */
    public List<Product> getAllProductsOfUser(String username);
    
    /**
     * este metodo permite crear un producto de un usuario especifico
     * 
     * @param pr
     * @param username 
     */
    public void createNewProduct(Product pr, String username);

    /**
     * Este metodo permite obtener un producto por id de un usuario
     *
     * @param id id del producto a obtener
     * @param username nickname del usuario
     * @return El producto que pertenece a ese usuario
     */
    public Product getProductByIdOfUser(int id, String username);

    /**
     * Este metodo permite eliminar un producto de un usuario
     *
     * @param id
     * @param username
     */
    public void deleteProductByIdOfUser(int id, String username);
    
    
    
}
