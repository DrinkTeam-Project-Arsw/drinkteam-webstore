/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.controllers;

import edu.eci.arsw.webstore.model.User;
import edu.eci.arsw.webstore.services.UserServices;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import com.google.gson.Gson;

/**
 *
 * @author jmvillatei
 */

@RestController
@RequestMapping( value = "/webstoreUser")
public class UserController {
    
    @Autowired
    UserServices uService;
    
     @RequestMapping(method = RequestMethod.GET, path = "users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = new ArrayList<>();
            
            String data = new Gson().toJson(uService.getAllUsers());

            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido retornar los usuarios", HttpStatus.NOT_FOUND);
        }
    }
}
