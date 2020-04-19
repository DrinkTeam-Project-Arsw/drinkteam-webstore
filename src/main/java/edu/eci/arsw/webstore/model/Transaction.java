/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.model;


import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 *
 * @author jmvillatei
 */ 
public class Transaction { 

    @Id
    private String transactionId;
    
    private int transactionPrice;
    private Date transactionDate;
    private Date transactionDateEnd;
    private String buyer;
    private String seller;
    private String product;
    

    public Transaction(String transactionId, int transactionPrice, Date transactionDate, String seller, String buyer, String product) {
        this.transactionId = transactionId;
        this.transactionPrice = transactionPrice;
        this.transactionDate = transactionDate;
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
    
    public int getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(int transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Date getTransactionDateEnd() {
        return transactionDateEnd;
    }

    public void setTransactionDateEnd(Date transactionDateEnd) {
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
