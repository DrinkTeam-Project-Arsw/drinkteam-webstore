/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.controllers;

import edu.eci.arsw.webstore.model.User;
import edu.eci.arsw.webstore.services.user.UserServices;
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
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author jmvillatei
 */
@RestController
@RequestMapping(value = "/webstoreUser")
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

    @RequestMapping(method = RequestMethod.POST, path = "users")
    public ResponseEntity<?> createNewUser(@RequestBody String user) {
        //Formato de json {"emailUser":email,"passwordUser":password,"username":username}
        try {
            //System.out.println("controller: "+user.getUserNickname());
            //uService.createNewUser(user);

            //Pasar el String JSON a un Map
            Type listType = new TypeToken<Map<Integer, User>>() {
            }.getType();
            Map<String, User> result = new Gson().fromJson(user, listType);

            //Obtener las llaves del Map
            Object[] nameKeys = result.keySet().toArray();
            
            User ur = result.get(nameKeys[0]);
            
            uService.createNewUser(ur);
               
            
            return new ResponseEntity<>(HttpStatus.CREATED);
            
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido registrar el usuario", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = {"users/{username}"})
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
        try {
            Map<String, User> user = new HashMap<>();

            User consulUser = uService.getUserByUsername(username);

            user.put(consulUser.getUserNickname(), consulUser); 

            String data = new Gson().toJson(consulUser);

            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido retornar el usuario con nickname: " + username, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"users/{username}"})
    public ResponseEntity<?> deleteUserByUsername(@PathVariable("username") String username) {
        try {
            uService.deleteUserByUsername(username);

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido eliminar el usuario con nickname: " + username,
                    HttpStatus.FORBIDDEN);
        }
    }
}
