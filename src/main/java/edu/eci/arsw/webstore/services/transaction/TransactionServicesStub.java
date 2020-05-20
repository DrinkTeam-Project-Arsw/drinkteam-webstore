package edu.eci.arsw.webstore.services.transaction;

import java.util.List;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import edu.eci.arsw.webstore.model.Transaction;
import edu.eci.arsw.webstore.model.User;
import edu.eci.arsw.webstore.persistence.TransactionPersistence;
import edu.eci.arsw.webstore.services.product.ProductServicesStub;
import edu.eci.arsw.webstore.services.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServicesStub implements TransactionServices {

    @Autowired
    TransactionPersistence tPersistence;
    
    @Autowired
    ProductServicesStub pServices;
    
    @Autowired
    UserServices uService;

    @Override
    public List<Transaction> getAllTransactions() {
        return tPersistence.getAllTransactions();
    }

    @Override
    public Transaction getTransactionById(String transactionId) {
        return tPersistence.getTransactionById(transactionId);
    }

    @Override
    public List<Transaction> getTransactionsOfUserById(String userId) {
        return tPersistence.getTransactionsOfUserById(userId);
    }

    @Override
    public void createNewTransaction(Transaction tr) {
       tPersistence.createNewTransaction(tr);
       User us = uService.getUserById(tr.getSeller());
       pServices.updateCacheProduct(us.getUserNickname());
    }

    @Override
    public void updateTransaction(Transaction tr) {
        tPersistence.updateTransaction(tr);

    }

    @Override
    public void deleteTransactionById(String transactionId) {
        tPersistence.deleteTransactionById(transactionId);

    }

    @Override
    public String getDateColombia() throws MalformedURLException, ProtocolException, IOException {
        return tPersistence.getDateColombia();
    }

}