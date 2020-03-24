/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence.impl;

import edu.eci.arsw.webstore.model.Product;
import edu.eci.arsw.webstore.persistence.ProductPersistence;
import edu.eci.arsw.webstore.persistence.WebStoreDB;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmvillatei
 */
@Service
public class InMemoryProductPersistence implements ProductPersistence{
    
    WebStoreDB wsdb;
    
    private void newDb(){
        wsdb = new WebStoreDB();
    }
    
    @Override
    public List<Product> getAllProducts() {
        newDb();
        return wsdb.getAllProducts();
    }

    @Override
    public List<Product> getAllProductsOfUserNickname(String userNickname) {
        newDb();
        return wsdb.getAllProductsOfUserNickname(userNickname);
    }

    @Override
    public void createNewProduct(Product pr, String userNickname) {
        newDb();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getProductByIdOfUserNickname(int id, String userNickname) {
        newDb();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProductByIdOfUserNickname(int id, String userNickname) {
        newDb();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
