/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.model;


import java.sql.Timestamp;
import org.springframework.data.annotation.Id;


/**
 *
 * @author jmvillatei
 */ 
public class Transaction { 

    @Id
    private String transactionId;
    
    private int transactionPrice;
    private Timestamp transactionDate;
    private Timestamp transactionDateEnd;
    private boolean transactionActive;
    private String buyer;
    private String seller;
    private String product;
    
    public Transaction(String transactionId, int transactionPrice, boolean transactionActive, String buyer, String seller, String product){
        this.transactionId = transactionId;
        this.transactionPrice = transactionPrice;
        this.transactionActive = transactionActive;
        this.buyer = buyer;
        this.seller = seller;
        this.product = product;
    }

    public Transaction(String transactionId, int transactionPrice, Timestamp transactionDate, boolean transactionActive, String buyer, String seller, String product) {
        this.transactionId = transactionId;
        this.transactionPrice = transactionPrice;
        this.transactionDate = transactionDate;
        this.transactionActive = transactionActive;
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
    
    public boolean getTransactionActive(){
        return transactionActive;
    }
    
    public void setTransactionActive(boolean transactionActive){
        this.transactionActive = transactionActive;
    }
    
    public int getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(int transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Timestamp getTransactionDateEnd() {
        return transactionDateEnd;
    }

    public void setTransactionDateEnd(Timestamp transactionDateEnd) {
        this.transactionDateEnd = transactionDateEnd;
    }

    public String getBuyerId() {
        return buyer;
    }

    public void setBuyerId(String buyer) {
        this.buyer = buyer;
    }
    
    public String getSellerId() {
        return seller;
    }

    public void setSellerId(String seller) {
        this.seller = seller;
    }
    
    public String getProduct() {
        return product;
    }

    public void setProductId(String product) {
        this.product = product;
    }   
}
