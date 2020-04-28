/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence.impl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import edu.eci.arsw.webstore.model.Transaction;
import edu.eci.arsw.webstore.persistence.TransactionPersistence;
import edu.eci.arsw.webstore.persistence.WebStoreDB;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmvillatei
 */
@Service
public class InMemoryTransactionPersistence implements TransactionPersistence {

    private String uriTimeApi = "http://worldtimeapi.org/api/timezone/America/Bogota";

    //Atributos
    WebStoreDB wsdb;

    private void newDb(){
        wsdb = new WebStoreDB();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        newDb();
        return wsdb.getAllTransactions();
    }

    @Override
    public Transaction getTransactionById(String transactionId) {
        newDb();
        return wsdb.getTransactionById(transactionId);
    }

    @Override
    public void createNewTransaction(Transaction tr) {
        newDb();
        wsdb.createNewtransaction(tr);

    }

    @Override
    public void updateTransaction(Transaction tr) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTransactionById(String transactionId) {
        newDb();
        wsdb.deleteTransactionById(transactionId);

    }

    @Override
    public String getDateColombia() throws MalformedURLException, ProtocolException, IOException {

        try {
            HttpResponse<JsonNode> response = Unirest.get(uriTimeApi)
                .asJson();
            //System.out.println("Status: "+response.getStatusText());
            System.out.println("Body: "+response.getBody());
            JSONObject body = response.getBody().getObject();
            String fecha = body.getString("datetime");
            //System.out.println("fhecha: "+ fecha);
            
            return fecha;
            //return response.getBody();
        } catch (UnirestException ex) {
            Logger.getLogger(InMemoryTransactionPersistence.class.getName()).log(Level.SEVERE, null, ex);
            return ">>>error, no se puedo consultar el tiempo";
        }
    }
    
}
