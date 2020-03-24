/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.services.product;

import edu.eci.arsw.webstore.model.Product;
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
    public List<Product> getAllProductsOfUserNickname(String userNickname) {
        return pPersistence.getAllProductsOfUserNickname(userNickname);
    }

    @Override
    public void createNewProduct(Product pr, String userNickname) {
        pPersistence.createNewProduct(pr, userNickname);
    }

    @Override
    public Product getProductByIdOfUserNickname(int id, String userNickname) {
        return pPersistence.getProductByIdOfUserNickname(id, userNickname);
    }

    @Override
    public void deleteProductByIdOfUserNickname(int id, String userNickname) {
        pPersistence.deleteProductByIdOfUserNickname(id, userNickname);
    }
    
    
}
