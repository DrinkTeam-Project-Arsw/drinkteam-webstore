/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.model;

import org.springframework.data.annotation.Id;

/**
 *
 * @author Juan David
 */
public class Message {
    //Atributos
    @Id
    private String id;
    private String idTransaction;
    private String user;
    private String data;
    private String userImage;

    public Message(String idTransaction, String user, String data, String userImage) {
        this.idTransaction = idTransaction;
        this.user = user;
        this.data = data;
        this.userImage = userImage;
    }
    
    public String getId() {
        return id;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }
    
    public String getUser() {
        return user;
    }

    public String getData() {
        return data;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
