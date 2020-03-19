/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.controllers;

import com.google.gson.Gson;
import edu.eci.arsw.webstore.model.Product;
import edu.eci.arsw.webstore.services.product.ProductServices;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jmvillatei
 */
@RestController
@RequestMapping(value = "/webstoreProduct")
public class ProductController {
    
    @Autowired
    ProductServices pService;
    
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
    
    @RequestMapping(method = RequestMethod.GET, path = {"products/{username}"})
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
        try {
            List<Product> products = new ArrayList<>();

            products = pService.getAllProductsOfUser(username);

            

            String data = new Gson().toJson(products);

            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido retornar los productos del usuario: " + username, HttpStatus.NOT_FOUND);
        }
    }
}
