/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class Auction {
    private int auctionId;
    
    private double auctionInitPrice;
    private double auctionCurrentPrice;
    private double auctionFinalPrice;
    private Date auctionDate;
    private Date auctionDateFinal;
    private int auctionTimeToWait;
    private String AuctionType; 
    private ArrayList<User> buyers;    
    private User seller;   
    private Product product;

    public Auction(int auctionId, User seller, ArrayList<User> buyers, Product product, double auctionInitPrice, Date auctionDate, String AuctionType) {
        this.auctionId = auctionId;
        this.seller = seller;
        this.buyers = buyers;
        this.product = product;
        this.auctionInitPrice = auctionInitPrice;
        this.auctionDate = auctionDate;
        this.AuctionType = AuctionType;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public List getBuyers() {
        return buyers;
    }

    public void setBuyers(ArrayList<User> buyers) {
        this.buyers = buyers;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Date getAuctionDate() {
        return auctionDate;
    }

    public void setAuctionDate(Date auctionDate) {
        this.auctionDate = auctionDate;
    }

    public Date getAuctionDateFinal() {
        return auctionDateFinal;
    }

    public void setAuctionDateFinal(Date auctionDateFinal) {
        this.auctionDateFinal = auctionDateFinal;
    }

    public int getAuctionTimeToWait() {
        return auctionTimeToWait;
    }

    public void setAuctionTimeToWait(int auctionTimeToWait) {
        this.auctionTimeToWait = auctionTimeToWait;
    }

    public String getAuctionType() {
        return AuctionType;
    }

    public void setAuctionType(String AuctionType) {
        this.AuctionType = AuctionType;
    }
    
    
}
