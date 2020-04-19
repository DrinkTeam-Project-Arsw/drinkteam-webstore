package edu.eci.arsw.webstore.services.transaction;

import java.util.List;

import edu.eci.arsw.webstore.model.Transaction;
import edu.eci.arsw.webstore.persistence.TransactionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServicesStub implements TransactionServices {

    @Autowired
    TransactionPersistence tPersistence;

    @Override
    public List<Transaction> getAllTransactions() {
        return tPersistence.getAllTransactions();
    }

    @Override
    public Transaction getTransactionById(String transactionId) {
        return tPersistence.getTransactionById(transactionId);
    }

    @Override
    public void createNewTransaction(Transaction tr) {
       tPersistence.createNewTransaction(tr);

    }

    @Override
    public void updateTransaction(Transaction tr) {
        tPersistence.updateTransaction(tr);

    }

    @Override
    public void deleteTransactionById(String transactionId) {
        tPersistence.deleteTransactionById(transactionId);

    }

    

}