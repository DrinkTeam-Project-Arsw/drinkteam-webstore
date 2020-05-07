/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence;

import edu.eci.arsw.webstore.model.Message;
import java.util.List;

/**
 *
 * @author Juan David
 */
public interface MessagePersistence {
    
    /**
     * Permite consultar todos los mensajes de una transaccion.
     * @param transactionId Es el id de la transaccion de la cual se quiere tener sus mensajes.
     * @return  Retorna una lista con todos los mensajes de la transaccion.
     */
    public List<Message> getMessagesByTransactionId(String transactionId);
 
    /**
     * este metodo permite crear un mensanje de un transaccion en especifico.
     * 
     * @param msg Es el mensaje de la transaccion.
     */
    public void createNewMessage(Message msg);
    
}
