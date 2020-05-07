/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;

/**
 *
 * @author jmvillatei
 */
public class Transaction {

    @Id
    private String transactionId;

    private double transactionPrice;
    private String transactionDate;
    private String transactionDateEnd;
    private boolean transactionActive;
    private String TransactionState;
    private String buyer;
    private String seller;
    private String product;
    
    private List messages;
    
    public Transaction(String buyer, String seller, String product) {
        
        this.transactionActive = true;
        this.buyer = buyer;
        this.seller = seller;
        this.product = product;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public boolean getTransactionActive() {
        return transactionActive;
    }

    public void setTransactionActive(boolean transactionActive) {
        this.transactionActive = transactionActive;
    }

    public double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionDateEnd() {
        return transactionDateEnd;
    }

    public void setTransactionDateEnd(String transactionDateEnd) {
        this.transactionDateEnd = transactionDateEnd;
    }

    public String getBuyer() {
        return buyer;
    }
    
    public boolean isTransactionActive() {
        return transactionActive;
    }

    public List getMessages() {
        return messages;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getProduct() {
        return product;
    }

    public void setProductId(String product) {
        this.product = product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setMessages(List messages) {
        this.messages = messages;
    }

    
    
    public Date getTransactionDateInFormatDate() {
        try {
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date convertido = fechaHora.parse(transactionDate);
            System.out.println(convertido);
            return convertido;
        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    
    public Date getTransactionDateEndInFormatDate() {
        try {
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date convertido = fechaHora.parse(transactionDateEnd);
            System.out.println(convertido);
            return convertido;
        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public String getTransactionState() {
        return TransactionState;
    }

    public void setTransactionState(String transactionState) {
        TransactionState = transactionState;
    }
}
