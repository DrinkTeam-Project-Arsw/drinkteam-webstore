/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.services.product;

import edu.eci.arsw.webstore.model.Product;
import edu.eci.arsw.webstore.model.User;
import edu.eci.arsw.webstore.persistence.ProductPersistence;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmvillatei
 */
@Service
public class ProductServicesStub implements ProductServices{
    
    @Autowired
    ProductPersistence pPersistence;

    @Override
    public List<Product> getAllProducts() {
        return pPersistence.getAllProducts();
    }

    @Override
    public List<Product> getAllProductsOfUser(String username) {
        return pPersistence.getAllProductsOfUser(username);
    }

    @Override
    public void createNewProduct(Product pr, String username) {
        pPersistence.createNewProduct(pr, username);
    }

    @Override
    public Product getProductByIdOfUser(int id, String username) {
        return pPersistence.getProductByIdOfUser(id, username);
    }

    @Override
    public void deleteProductByIdOfUser(int id, String username) {
        pPersistence.deleteProductByIdOfUser(id, username);
    }
    
    
}
