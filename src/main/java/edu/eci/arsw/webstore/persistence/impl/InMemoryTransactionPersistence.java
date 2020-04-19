/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.webstore.model.Transaction;
import edu.eci.arsw.webstore.persistence.TransactionPersistence;
import edu.eci.arsw.webstore.persistence.WebStoreDB;

/**
 *
 * @author jmvillatei
 */
@Service
public class InMemoryTransactionPersistence implements TransactionPersistence {

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createNewTransaction(Transaction tr) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTransaction(Transaction tr) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTransactionById(String transactionId) {
        // TODO Auto-generated method stub

    }
    
}
