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
import java.util.Date;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author jmvillatei
 */
@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    UserServices uService;

    @RequestMapping(method = RequestMethod.GET, path = "users")
    public ResponseEntity<?> getAllUsers() {
        try {
            System.out.println("Consultando Usuarios...");
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
        // Formato de json
        // {"1":{"userEmail":"webpostman@gmail.com","userPassword":"123","userNickname":"postmanweb"}}
        try {
            System.out.println("Creando nuevo usuario...");
            // Pasar el String JSON a un Map
            Type listType = new TypeToken<Map<Integer, User>>() {
            }.getType();
            Map<String, User> result = new Gson().fromJson(user, listType);

            // Obtener las llaves del Map
            Object[] nameKeys = result.keySet().toArray();

            User us = result.get(nameKeys[0]);
            ObjectId newObjectIdUser = new ObjectId(new Date());
            us.setIdUser(newObjectIdUser.toHexString());

            // Usuario nuevo Inactivo
            us.setUserActive(false);

            if (uService.getUserByUserNickname(us.getUserNickname()) == null
                    && uService.getUserByEmail(us.getUserEmail()) == null) {
                uService.createNewUser(us);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                System.out.println("Nickname o Email ya registrados en la plataforma.");
                return new ResponseEntity<>("Nickname o Email ya registrados en la plataforma",
                        HttpStatus.NOT_ACCEPTABLE);

            }

        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido registrar el usuario", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = { "users/{userNickname}" })
    public ResponseEntity<?> getUserByUsername(@PathVariable("userNickname") String username) {
        try {
            System.out.println("Consultando usuario: " + username);
            User consulUser = uService.getUserByUserNickname(username);

            String data = new Gson().toJson(consulUser);

            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido retornar el usuario con nickname: " + username,
                    HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "users")
    public ResponseEntity<?> updateUser(@RequestBody String user) {
        try {
            System.out.println("Actualizando usuario: " + user);
            Type listType = new TypeToken<Map<Integer, User>>() {
            }.getType();
            Map<String, User> result = new Gson().fromJson(user, listType);

            // Obtener las llaves del Map
            Object[] nameKeys = result.keySet().toArray();

            User us = result.get(nameKeys[0]);

            User userActual = uService.getUserByUserNickname(us.getUserNickname());

            if (us.getUserActive()) {
                us.setIdUser(userActual.getIdUser());
                us.setUserBalance(userActual.getUserBalance());
                us.setUserFeedback(userActual.getUserFeedback());
                System.out.println("Usuario a guardar activo: " + us.getUserActive());

                uService.updateUser(us);
            }else{
                
                userActual.setUserImage(us.getUserImage());
                System.out.println("Usuario a guardar img: " + us.getUserImage());
                uService.updateUser(userActual);
            }

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se puede guardar los cambios ", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = { "users/{userNickname}/{add}" })
    public ResponseEntity<?> updateBalanceByUser(@PathVariable("add") double add,
            @PathVariable("userNickname") String nickname) {
        try {
            System.out.println("Actualizando Saldo de Usuario: " + nickname);
            User userActual = uService.getUserByUserNickname(nickname);

            double balanceBefore = userActual.getUserBalance();
            userActual.setUserBalance(add + balanceBefore);

            uService.updateUser(userActual);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se puede guardar los cambios ", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = { "users/{userNickname}" })
    public ResponseEntity<?> deleteUserByUsername(@PathVariable("userNickname") String username) {
        try {
            System.out.println("Eliminando Usuario: " + username);
            uService.deleteUserByUserNickname(username);

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido eliminar el usuario con nickname: " + username,
                    HttpStatus.FORBIDDEN);
        }
    }
}
