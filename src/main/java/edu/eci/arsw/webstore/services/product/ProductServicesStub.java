/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.services.product;

import edu.eci.arsw.webstore.model.Product;
import edu.eci.arsw.webstore.cache.productsUserCache;
import edu.eci.arsw.webstore.model.User;
import edu.eci.arsw.webstore.persistence.ProductPersistence;
import edu.eci.arsw.webstore.services.user.UserServices;
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
    
    @Autowired
    productsUserCache pCache;
    
    @Autowired
    UserServices uService;
    
    public void updateCacheProduct(String userNickname){
        if(pCache.containsAllProductsOfUserNickname(userNickname)){
            //Actualizar
            pCache.putUsuarioCache(userNickname, pPersistence.getAllProductsOfUserNickname(userNickname));            
        }
    }
    
    @Override
    public List<Product> getAllProducts() {
        return pPersistence.getAllProducts();
    }

    @Override
    public List<Product> getAllProductsOfUserNickname(String userNickname) {
        List<Product> ans;
        if(pCache.containsAllProductsOfUserNickname(userNickname)){
            ans = pCache.getAllProductsOfUserNicknameCache(userNickname);
        }else{
            ans = pPersistence.getAllProductsOfUserNickname(userNickname);
            pCache.putUsuarioCache(userNickname, ans);
        }
        return ans;
    }

    @Override
    public void createNewProduct(Product pr) {        
        pPersistence.createNewProduct(pr);
        User us = uService.getUserById(pr.getProductUser());
        updateCacheProduct(us.getUserNickname());
    }

    @Override
    public Product getProductByIdOfUserNickname(String id) {
        return pPersistence.getProductByIdOfUserNickname(id);
    }

    @Override
    public void deleteProductByIdOfUserNickname(String id, String idUser) {
        pPersistence.deleteProductByIdOfUserNickname(id, idUser);
        User us = uService.getUserById(idUser);
        updateCacheProduct(us.getUserNickname());
    }

    @Override
    public void editProductById(String productId, Product pd) {
        pPersistence.editProductById(productId, pd);
        User us = uService.getUserById(pd.getProductUser());
        updateCacheProduct(us.getUserNickname());
    }
    
    
}
