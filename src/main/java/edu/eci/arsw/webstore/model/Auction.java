/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class Auction {
    private int auctionId;
    private int sellerId;
    private List buyers;
    private int productoId;
    private double auctionInitPrice;
    private double auctionCurrentPrice;
    private double auctionFinalPrice;
    private Date auctionDate;
    private Date auctionDateFinal;
    private int auctionTimeToWait;
    private String AuctionType;

    public Auction(int auctionId, int sellerId, List buyers, int productoId, double auctionInitPrice, Date auctionDate, String AuctionType) {
        this.auctionId = auctionId;
        this.sellerId = sellerId;
        this.buyers = buyers;
        this.productoId = productoId;
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

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public List getBuyers() {
        return buyers;
    }

    public void setBuyers(List buyers) {
        this.buyers = buyers;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
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
