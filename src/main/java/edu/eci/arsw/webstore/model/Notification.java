package edu.eci.arsw.webstore.model;

import org.springframework.data.annotation.Id;

public class Notification {
    
    @Id
    public String notificationId;

    private String notificationMessage;
    private String notificationDate;
    private String notificationDestination;
    private String notificationSend;
    private String notificationUrl;
    private String notificationFunction;
    private boolean notificationViewed;

    public Notification(){}

    public Notification(String notificationMessage, String notificationDate,
            String notificationDestination, String notificationSend, String notificationUrl,
            String notificationFunction, boolean notificationViewed) {
        this.notificationMessage = notificationMessage;
        this.notificationDate = notificationDate;
        this.notificationDestination = notificationDestination;
        this.notificationSend = notificationSend;
        this.notificationUrl = notificationUrl;
        this.notificationFunction = notificationFunction;
        this.notificationViewed = notificationViewed;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getNotificationDestination() {
        return notificationDestination;
    }

    public void setNotificationDestination(String notificationDestination) {
        this.notificationDestination = notificationDestination;
    }

    public String getNotificationSend() {
        return notificationSend;
    }

    public void setNotificationSend(String notificationSend) {
        this.notificationSend = notificationSend;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public boolean isNotificationViewed() {
        return notificationViewed;
    }

    public void setNotificationViewed(boolean notificationViewed) {
        this.notificationViewed = notificationViewed;
    }

    public String getNotificationFunction() {
        return notificationFunction;
    }

    public void setNotificationFunction(String notificationFunction) {
        this.notificationFunction = notificationFunction;
    } 
}