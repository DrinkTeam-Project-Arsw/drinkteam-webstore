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
    
    //Atributos
    private static final Map<String, List<Product> > productsByUser = new HashMap <>();
    private static final Map<Integer, List<Product> > allProducts = new HashMap <>();
    
    /**
     * Metodo que permite obtener todos los productos de un usuario si se encuentran en cache
     * @param userNickname  Es el nickname del usuario
     * @return  Retorna una lista con todos los productos del usuario.
     */
    public List<Product> getAllProductsOfUserNicknameCache(String userNickname){
        synchronized (productsByUser){
            return productsByUser.get(userNickname);
        }
    }

    /**
     * Metodo que permite consultar si se encuntra en cache los productos del usuario.
     * @param userNickname  Es el nickname del usuario
     * @return  Retorna true si los productos se encuentran en cache, false de lo contrario.
     */
    public boolean containsAllProductsOfUserNicknameCache(String userNickname){
        synchronized (productsByUser) {
            return productsByUser.containsKey(userNickname);
        }
    }

    /**
     * Metodo que permite agregar la lista de productos de un usuario en cache
     * @param userNickname  Es el nickname del usuario.
     * @param pds   Es la lista de productos del usuario que se almacenara en cache.
     */
    public void putAllProductsOfUserNicknameCache(String userNickname, List<Product> pds){
        synchronized (productsByUser) {
            productsByUser.put(userNickname, pds);
        }
    }
    
    /**
     * Metodo que permite consultar todos los productos que se encuentran en cache
     * @param id    Es el id del cache
     * @return  Retorna una lista con todos los productos en cache.
     */
    public List<Product> getAllProductsCache(int id){
        synchronized (allProducts){
            return allProducts.get(id);
        }
    }

    /**
     * Metodo que permite consultar si se encuntra en cache la lista de productos disponible para comprar.
     * @param id    Es el id del cache
     * @return  Retorna true si los productos se encuentran en cache.
     */
    public boolean containsAllProductsCache(int id){
        synchronized (allProducts) {
            return allProducts.containsKey(id);
        }
    }

    /**
     * Metodo que permite agregar en cache los productos en venta.
     * @param id    Es el id del cache
     * @param pds   Son los productos que se almacenaran en cache.
     */
    public void putAllProductsCache(int id, List<Product> pds){
        synchronized (allProducts) {
            allProducts.put(id, pds);
        }
    }

    /**
     * Metodo que permite borrar todos los productos disponibles para la venta del cache.
     * @param id    Es el id del cache.
     */
    public void deleteAllProductsCache(int id){
        synchronized (allProducts) {
            allProducts.remove(id);
        }
    }    
}
