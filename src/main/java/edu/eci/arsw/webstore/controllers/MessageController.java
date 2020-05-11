/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.eci.arsw.webstore.model.Message;
import edu.eci.arsw.webstore.services.message.MessageServices;
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
 * @author Juan David
 */
@RestController
@RequestMapping(value = "/api/v1")
public class MessageController {
    
    @Autowired
    MessageServices mService;
    
    @RequestMapping(method = RequestMethod.GET, path = {"messages/{transactionId}"})
    public ResponseEntity<?> getMessagesByTransactionId(@PathVariable("transactionId") String transactionId) {
        try {
            System.err.println("EN controller MK");
            List<Message> messages = new ArrayList<>();

            messages = mService.getMessagesByTransactionId(transactionId);
            
            String data = new Gson().toJson(messages);

            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
            
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido retornar los mensajes de la transacción: " + transactionId, HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "messages")
    public ResponseEntity<?> createNewMessage(@RequestBody String message ) {
        //Formato de json {"1":{"idTransaction":"5eb2fcac7db0440004cfd5a3","user":"2","data":"Respuesta"}}
        try {
            System.out.println("Consiltando mensaje: "+message);
            //Pasar el String JSON a un Map
            Type listType = new TypeToken<Map<Integer, Message>>() {
            }.getType();
            Map<String, Message> result = new Gson().fromJson(message, listType);
            
            //Obtener las llaves del Map
            Object[] nameKeys = result.keySet().toArray();
            
            Message msg = result.get(nameKeys[0]);
            
            ObjectId newObjectIdProduct = new ObjectId(new Date());
            msg.setId(newObjectIdProduct.toHexString());
            
            mService.createNewMessage(msg);
               
            return new ResponseEntity<>(HttpStatus.CREATED);
            
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido registrar el mensaje", HttpStatus.FORBIDDEN);
        }
    }
    
}
