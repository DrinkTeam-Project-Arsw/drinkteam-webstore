package edu.eci.arsw.webstore.persistence.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.webstore.persistence.WebStoreDB;
import edu.eci.arsw.webstore.model.Notification;
import edu.eci.arsw.webstore.persistence.NotificationPersistence;

@Service
public class InMemoryNotificationPersistence implements NotificationPersistence {

    WebStoreDB wsdb;

    @Override
    public List<Notification> getNotificationsByNickname(String nickname) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createNewNotification(Notification notification) {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeNotificationStatus(boolean viewed, String notificationId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteNotificationById(String notificationId) {
        // TODO Auto-generated method stub

    }
    
}