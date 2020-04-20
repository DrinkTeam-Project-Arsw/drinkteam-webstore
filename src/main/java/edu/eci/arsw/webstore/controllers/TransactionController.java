/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.controllers;

import edu.eci.arsw.webstore.model.Transaction;
import edu.eci.arsw.webstore.services.transaction.TransactionServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
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
public class TransactionController {

    @Autowired
    TransactionServices tService; 

    @RequestMapping(method = RequestMethod.GET, path = "transactions")
    public ResponseEntity<?> getAllTransactions() {
        try {
            List<Transaction> transactions = new ArrayList<>();
            System.out.println("entro a transacciones ");

            String data = new Gson().toJson(tService.getAllTransactions());

            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido retornar las Transacciones", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = {"transactions/{transactionid}"})
    public ResponseEntity<?> getTransactionById(@PathVariable("transactionid") String id) {
        try {
            System.out.println("transaccion: "+id);
            Transaction t = tService.getTransactionById(id);
            System.out.println(t +"  OMG PERO ES IGUAL");
            String data = new Gson().toJson(t);

            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido retornar la transaccion: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "transactions")
    public ResponseEntity<?> createNewTransaction(@RequestBody String transaction) {
        //Formato de json {"1":{"transactionId":"5","transactionPrice":"2000","transactionDate":"2020-04-19 08:22:23","transactionDateEnd":"2020-04-19 08:22:23","transactionActive":"true","buyer":"0","seller":"2","product":"4"}}
        try {
            //Pasar el String JSON a un Map
            Type listType = new TypeToken<Map<Integer, Transaction>>() {
            }.getType();
            Map<String, Transaction> result = new Gson().fromJson(transaction, listType);
            //Obtener las llaves del Map
            Object[] nameKeys = result.keySet().toArray();
            Transaction tr = result.get(nameKeys[0]);
            ObjectId newObjectIdUser = new ObjectId(new Date());

            tr.setTransactionId(newObjectIdUser.toHexString());

            tService.createNewTransaction(tr);
               
            return new ResponseEntity<>(HttpStatus.CREATED);
            
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido registrar la transaction", HttpStatus.FORBIDDEN);
        }
    }
    
    @RequestMapping(method = RequestMethod.DELETE, path = {"transactions/{transactionid}"})
    public ResponseEntity<?> deleteTransactionById(@PathVariable("transactionid") String transactionid) {
        try {
            System.out.println("transaccion: "+transactionid);
            tService.deleteTransactionById(transactionid);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido eliminar la transaccion: " + transactionid, HttpStatus.NOT_FOUND);
        }
    }
}
