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
import java.util.HashMap;
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
    public ResponseEntity<?> getUserByUsername(@PathVariable("usernickName") String username) {
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
    
    @RequestMapping(method = RequestMethod.POST, path = "products")
    public ResponseEntity<?> createNewUser(@RequestBody String product ) {
        //Formato de json {"emailUser":email,"passwordUser":password,"username":username}
        try {
            //System.out.println("controller: "+user.getUserNickname());
            //uService.createNewUser(user);
            System.out.println("producto: "+product);
            //Pasar el String JSON a un Map
            Type listType = new TypeToken<Map<Integer, Product>>() {
            }.getType();
            Map<String, Product> result = new Gson().fromJson(product, listType);
            
            //Obtener las llaves del Map
            Object[] nameKeys = result.keySet().toArray();
            
            Product pd = result.get(nameKeys[0]);
            
            
            
            ObjectId newObjectIdProduct = new ObjectId(new Date());
            pd.setProductId(newObjectIdProduct.toHexString());
            //pd.setProductUser(user);
            System.out.println("Nombre producto: "+pd.getProductName());
            System.out.println("vendedor: "+pd.getProductUser());
            System.out.println("id: "+pd.getProductId());
            
            pService.createNewProduct(pd);
               
            return new ResponseEntity<>(HttpStatus.CREATED);
            
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido registrar el usuario", HttpStatus.FORBIDDEN);
        }
    }
}
