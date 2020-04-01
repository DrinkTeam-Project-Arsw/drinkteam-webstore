/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.model;

import org.springframework.data.annotation.Id;

/**
 *
 * @author jmvillatei
 */
public class Product {   
    
    
    @Id
    public String productId;
    
    private String productName;
    private String productDescription;
    private double productPrice;
    private String productUser;
    private Auction productAuction;

    public Product( String productName, String productDescription, double productPrice) {
        
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productAuction = null;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    
    public String getProductUser() {
        return productUser;
    }

    public void setProductUser(String productUser) {
        this.productUser = productUser;
    }    
}
