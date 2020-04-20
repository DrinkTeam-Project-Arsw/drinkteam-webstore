/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.eci.arsw.webstore.model.Product;
import edu.eci.arsw.webstore.model.User;
import edu.eci.arsw.webstore.services.product.ProductServices;
import edu.eci.arsw.webstore.services.user.UserServices;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jmvillatei
 */
@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {
    
    @Autowired
    ProductServices pService;
    
    @Autowired
    UserServices uService;
    
    @RequestMapping(method = RequestMethod.GET, path = "products")
    public ResponseEntity<?> getAllProducts() {
        try {

            String data = new Gson().toJson(pService.getAllProducts());

            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido retornar los productos", HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, path = {"products/{usernickName}"})
    public ResponseEntity<?> getProductByUsername(@PathVariable("usernickName") String username) {
        try {
            List<Product> products = new ArrayList<>();

            products = pService.getAllProductsOfUserNickname(username);

            String data = new Gson().toJson(products);

            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido retornar los productos del usuario con nickname: " + username, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = {"products/{usernickName}/{productId}"})
    public ResponseEntity<?> getProductByNicknameById(@PathVariable("usernickName") String nickname, @PathVariable("productId") String producId) {
        try {
            List<Product> products = new ArrayList<>();

            products = pService.getAllProductsOfUserNickname(nickname);
            
            Product product = null;
            for(Product p:products){
                if(p.getProductId().equals(producId)){
                    product = p;
                    break;
                }
            }

            String data = new Gson().toJson(product);

            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido retornar el producto del usuario con nickname: " + nickname, HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "products")
    public ResponseEntity<?> createNewProduct(@RequestBody String product ) {
        //Formato de json {"1":{"productName":"PruebaOnline","productDescription":"pruebaOnlineeeee","productPrice":"20000","productUser":"david","productImage":""}}
        try {
            //Pasar el String JSON a un Map
            Type listType = new TypeToken<Map<Integer, Product>>() {
            }.getType();
            Map<String, Product> result = new Gson().fromJson(product, listType);
            
            //Obtener las llaves del Map
            Object[] nameKeys = result.keySet().toArray();
            
            Product pd = result.get(nameKeys[0]);
            
            User idUser = uService.getUserByUserNickname(pd.getProductUser());
            
            ObjectId newObjectIdProduct = new ObjectId(new Date());
            pd.setProductId(newObjectIdProduct.toHexString());
            pd.setProductUser(idUser.getIdUser());
            
            pService.createNewProduct(pd);
               
            return new ResponseEntity<>(HttpStatus.CREATED);
            
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido registrar el usuario", HttpStatus.FORBIDDEN);
        }
    }
    
    @RequestMapping(method = RequestMethod.PUT, path = {"products/{usernickName}/{productId}"})
    public ResponseEntity<?> editProductById(@RequestBody String product, @PathVariable("usernickName") String nickname, @PathVariable("productId") String producId) {
        try {
            //JSON {"1":{"productName":"xxxx","productDescription":"xxxx","productPrice":"xxxx","productImage":"xxxx"}}
            //Pasar el String JSON a un Map
            Type listType = new TypeToken<Map<Integer, Product>>() {
            }.getType();
            Map<String, Product> result = new Gson().fromJson(product, listType);
            
            //Obtener las llaves del Map
            Object[] nameKeys = result.keySet().toArray();
            
            Product pd = result.get(nameKeys[0]);
            
            User idUser = uService.getUserByUserNickname(nickname);
            
            ObjectId newObjectIdProduct = new ObjectId(new Date());
            pd.setProductId(newObjectIdProduct.toHexString());
            pd.setProductUser(idUser.getIdUser());            
            
            pService.editProductById(producId, pd);

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido editar el producto con el id: " + producId,
                    HttpStatus.FORBIDDEN);
        }
    }
    
    @RequestMapping(method = RequestMethod.DELETE, path = {"products/{usernickName}/{productId}"})
    public ResponseEntity<?> deleteProductById(@PathVariable("productId") String productId, @PathVariable("usernickName") String username) {
        try {
            User us = uService.getUserByUserNickname(username);

            pService.deleteProductByIdOfUserNickname(productId, us.getIdUser());

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido eliminar el producto con el id: " + productId,
                    HttpStatus.FORBIDDEN);
        }
    }
}
