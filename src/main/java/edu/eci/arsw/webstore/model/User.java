/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.model;

/**
 *
 * @author jmvillatei
 */
public class User {
    
    private String idUser;
    private String nameUser;
    private String lastnameUser;
    private String emailUser;
    private String passwordUser;
    private String imageUser;
    private String username;
    private String codeCountry;
    private int phoneUser;
    private double balanceUser;
    private int feedbackUser;
    

    public User(String emailUser, String passwordUser, String username) {
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.username = username;
        
        
        this.balanceUser = 0.0;
        this.feedbackUser = 0;
        
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getLastnameUser() {
        return lastnameUser;
    }

    public void setLastnameUser(String lastnameUser) {
        this.lastnameUser = lastnameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }

    public int getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(int phoneUser) {
        this.phoneUser = phoneUser;
    }

    public double getBalanceUser() {
        return balanceUser;
    }

    public void setBalanceUser(double balanceUser) {
        this.balanceUser = balanceUser;
    }

    public int getFeedbackUser() {
        return feedbackUser;
    }

    public void setFeedbackUser(int feedbackUser) {
        this.feedbackUser = feedbackUser;
    }

    
}
