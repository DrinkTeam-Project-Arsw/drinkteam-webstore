/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.services.message;

import edu.eci.arsw.webstore.model.Message;
import edu.eci.arsw.webstore.persistence.MessagePersistence;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan David
 */
@Service
public class MessageServicesStub implements MessageServices{

    @Autowired
    MessagePersistence mPersistence;
    
    @Override
    public List<Message> getMessagesByTransactionId(String transactionId) {
        System.err.println("EN service MK");
        return mPersistence.getMessagesByTransactionId(transactionId);
    }

    @Override
    public void createNewMessage(Message msg) {
        mPersistence.createNewMessage(msg);
    }

    
}
