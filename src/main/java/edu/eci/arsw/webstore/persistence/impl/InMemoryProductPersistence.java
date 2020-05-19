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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmvillatei
 */
@Service
public class InMemoryProductPersistence implements ProductPersistence {

    WebStoreDB wsdb;

    private void newDb() {
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
        return wsdb.getAllProductsOfUserNicknameWithoutAuction(userNickname);
    }

    @Override
    public void createNewProduct(Product pr) {
        newDb();

        try {
            wsdb.createNewProduct(pr);

        } catch (Exception ex) {
            Logger.getLogger(InMemoryProductPersistence.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public Product getProductByIdOfUserNickname(String id) {
        newDb();
        try {
             return wsdb.getProductByIdOfUserNickname(id);

        } catch (Exception ex) {
            Logger.getLogger(InMemoryProductPersistence.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void deleteProductByIdOfUserNickname(String id, String idUser) {
        newDb();
        if(wsdb.getTransactionByProductId(id)==null){
            wsdb.deleteProductByIdOfUserNickname(id, idUser);
        }else{
            try {
                throw new ExcepcionWebStore("No se puede eleminar el producto hay una transacción en curso.");
            } catch (ExcepcionWebStore ex) {
                Logger.getLogger(InMemoryUserPersistence.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @Override
    public void editProductById(String productId, Product pd) {
        newDb();
        if(wsdb.getTransactionByProductId(productId)==null){
            wsdb.editProductById(productId, pd);
        }else{
            try {
                throw new ExcepcionWebStore("No se puede editar el producto hay una transacción en curso.");
            } catch (ExcepcionWebStore ex) {
                Logger.getLogger(InMemoryUserPersistence.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
