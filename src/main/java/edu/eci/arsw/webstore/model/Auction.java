/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.model;

import java.util.List;

/**
 *
 * @author estudiante
 */
public class Auction {
    
    //Atributos
    private String auctionId;
    private double auctionInitPrice;
    private double auctionCurrentPrice;
    private double auctionFinalPrice;
    private String auctionDate;
    private String auctionDateFinal;
    private int auctionTimeToWait;
    private int auctionType; 
    private boolean auctionActive;    
    private String sellerId;   
    private String productId;
    private String auctionStatus;
    private List buyers;

    public Auction(double auctionInitPrice, String auctionDate, String auctionDateFinal, int auctionTimeToWait, int auctionType, boolean auctionActive, String sellerId, String productId, String auctionStatus) {
        this.auctionInitPrice = auctionInitPrice;
        this.auctionDate = auctionDate;
        this.auctionDateFinal = auctionDateFinal;
        this.auctionTimeToWait = auctionTimeToWait;
        this.auctionType = auctionType;
        this.auctionActive = auctionActive;
        this.sellerId = sellerId;
        this.productId = productId;
        this.auctionStatus = auctionStatus;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public double getAuctionInitPrice() {
        return auctionInitPrice;
    }

    public void setAuctionInitPrice(double auctionInitPrice) {
        this.auctionInitPrice = auctionInitPrice;
    }

    public double getAuctionCurrentPrice() {
        return auctionCurrentPrice;
    }

    public void setAuctionCurrentPrice(double auctionCurrentPrice) {
        this.auctionCurrentPrice = auctionCurrentPrice;
    }

    public double getAuctionFinalPrice() {
        return auctionFinalPrice;
    }

    public void setAuctionFinalPrice(double auctionFinalPrice) {
        this.auctionFinalPrice = auctionFinalPrice;
    }

    public String getAuctionDate() {
        return auctionDate;
    }

    public void setAuctionDate(String auctionDate) {
        this.auctionDate = auctionDate;
    }

    public String getAuctionDateFinal() {
        return auctionDateFinal;
    }

    public void setAuctionDateFinal(String auctionDateFinal) {
        this.auctionDateFinal = auctionDateFinal;
    }

    public int getAuctionTimeToWait() {
        return auctionTimeToWait;
    }

    public void setAuctionTimeToWait(int auctionTimeToWait) {
        this.auctionTimeToWait = auctionTimeToWait;
    }

    public int getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(int auctionType) {
        this.auctionType = auctionType;
    }

    public boolean isAuctionActive() {
        return auctionActive;
    }

    public void setAuctionActive(boolean auctionActive) {
        this.auctionActive = auctionActive;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(String auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public List getBuyers() {
        return buyers;
    }

    public void setBuyers(List buyers) {
        this.buyers = buyers;
    }
    
}
