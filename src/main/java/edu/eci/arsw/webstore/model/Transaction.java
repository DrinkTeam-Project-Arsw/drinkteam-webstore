/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.model;

import java.util.Date;

/**
 *
 * @author jmvillatei
 */
public class Transaction {    
    private int transactionId;
    
    private int transactionPrice;
    private Date transactionDate;
    private Date transactionDateEnd;
    private User buyer;
    private User seller;
    private Product product;
    

    public Transaction(int transactionId, int transactionPrice, Date transactionDate, Date transactionDateEnd, User seller, User buyer) {
        this.transactionId = transactionId;
        this.transactionPrice = transactionPrice;
        this.transactionDate = transactionDate;
        this.transactionDateEnd = transactionDateEnd;
        this.buyer = buyer;
        this.seller = seller;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
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

    public User getBuyerId() {
        return buyer;
    }

    public void setBuyerId(User buyer) {
        this.buyer = buyer;
    }
    
    public User getSellerId() {
        return seller;
    }

    public void setSellerId(User seller) {
        this.seller = seller;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProductId(Product product) {
        this.product = product;
    }   
}
