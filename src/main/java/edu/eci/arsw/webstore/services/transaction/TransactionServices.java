/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.services.transaction;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import edu.eci.arsw.webstore.model.Transaction;
import java.util.List;

/**
 *
 * @author jmvillatei
 */

public interface TransactionServices {

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
     * Este metodo permite obtener las transacciones de un usuario
     *
     * @param userId id del usuario
     * @return lista de transacciones
     */
    public List<Transaction> getTransactionsOfUserById(String userId);

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

    /**
     * Este metodo permite obtener la fecha y hora actual de Colombia
     *
     * @return datos de tiempo zona horaria, etc de Colombia
     * @throws java.net.MalformedURLException
     * @throws java.net.ProtocolException
     */
    public String getDateColombia() throws MalformedURLException, ProtocolException, IOException;
}
