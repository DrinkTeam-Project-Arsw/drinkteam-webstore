/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence;

import edu.eci.arsw.webstore.model.Transaction;
import java.util.List;

/**
 *
 * @author jmvillatei
 */
public interface TransactionPersistence {

    /**
     * Este metodo permite obtener todos las transacciones
     *
     * @return Una lista con todas las transacciones
     */
    public List<Transaction> getAllTransactions();

    /**
     * Este metodo permite obtener una transaccion por su id
     *
     * @param transactionId nickname del usuario a obtener
     * @return El usuario que pertenece a ese nickname
     */
    public Transaction getTransactionById(String transactionId);

    /**
     * Este metodo permite la creacion de una nueva transaccion
     *
     * @param tr objeto transaccion
     */
    public void createNewTransaction(Transaction tr);

    /**
     * Este metodo permite Actualizar una transaccion
     *
     * @param tr objeto transaccion
     */
    public void updateTransaction(Transaction tr);

    /**
     * Este metodo permite eliminar una Transaccion
     *
     * @param transactionId
     */
    public void deleteTransactionById(String transactionId);
    
}
