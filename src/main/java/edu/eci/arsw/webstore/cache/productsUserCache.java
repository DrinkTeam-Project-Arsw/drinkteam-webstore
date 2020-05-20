/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.cache;

import edu.eci.arsw.webstore.model.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan David
 */
@Service
public class productsUserCache {
    
    //private static Map<String, Product> productMap = new HashMap<String, Product>();
    private static final Map<String, List<Product> > productsByUser = new HashMap <>();
    private static final Map<Integer, List<Product> > allProducts = new HashMap <>();
    
    public List<Product> getAllProductsOfUserNicknameCache(String userNickname){
        synchronized (productsByUser){
            return productsByUser.get(userNickname);
        }
    }

    public boolean containsAllProductsOfUserNicknameCache(String userNickname){
        synchronized (productsByUser) {
            return productsByUser.containsKey(userNickname);
        }
    }

    public void putAllProductsOfUserNicknameCache(String userNickname, List<Product> pds){
        synchronized (productsByUser) {
            productsByUser.put(userNickname, pds);
        }
    }
    
    public List<Product> getAllProductsCache(int id){
        synchronized (allProducts){
            return allProducts.get(id);
        }
    }

    public boolean containsAllProductsCache(int id){
        synchronized (allProducts) {
            return allProducts.containsKey(id);
        }
    }

    public void putAllProductsCache(int id, List<Product> pds){
        synchronized (allProducts) {
            allProducts.put(id, pds);
        }
    }

    public void deleteAllProductsCache(int id){
        synchronized (allProducts) {
            allProducts.remove(id);
        }
    }
    
    /*
    public void updateUsuarioSaldo(String email, float value){
        synchronized (productMap){
            float oldValue = productMap.get(email).getSaldo();
            productMap.get(email).setSaldo(oldValue + value);
        }
    }*/
    
}
