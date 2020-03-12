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
    private int sellerId;
    private int buyerId;
    private int productoId;
    private int transactionPrice;
    private Date transactionDate;
    private Date transactionDateEnd;
    

    public Transaction(int transactionId, int sellerId, int buyerId, int transactionPrice, Date transactionDate, Date transactionDateEnd) {
        this.transactionId = transactionId;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.transactionPrice = transactionPrice;
        this.transactionDate = transactionDate;
        this.transactionDateEnd = transactionDateEnd;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerID) {
        this.sellerId = sellerID;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
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
    
    
    
}
