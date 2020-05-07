/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence.impl;

import edu.eci.arsw.webstore.model.Message;
import edu.eci.arsw.webstore.persistence.MessagePersistence;
import edu.eci.arsw.webstore.persistence.WebStoreDB;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan David
 */
@Service
public class InMemoryMessagePersistence implements MessagePersistence{

    WebStoreDB wsdb;

    private void newDb() {
        wsdb = new WebStoreDB();
    }
    
    @Override
    public List<Message> getMessagesByTransactionId(String transactionId) {
        System.err.println("EN persitence MK");
        newDb();
        return wsdb.getMessagesByTransactionId(transactionId);
    }

    @Override
    public void createNewMessage(Message msg) {
        wsdb.createNewMessage(msg);
    }
    
}
