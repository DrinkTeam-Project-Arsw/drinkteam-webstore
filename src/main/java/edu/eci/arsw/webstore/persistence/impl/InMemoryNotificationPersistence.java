package edu.eci.arsw.webstore.persistence.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.webstore.persistence.WebStoreDB;
import edu.eci.arsw.webstore.model.Notification;
import edu.eci.arsw.webstore.persistence.NotificationPersistence;

@Service
public class InMemoryNotificationPersistence implements NotificationPersistence {
    
    //Atributos
    WebStoreDB wsdb;

    private void newDb() {
        wsdb = new WebStoreDB();
    }

    @Override
    public List<Notification> getNotificationsByNickname(String nickname) {
        newDb();
        return wsdb.getNotificationsByNickname(nickname);
    }

    @Override
    public void createNewNotification(Notification notification) {
        newDb();
        wsdb.createNewNotification(notification);
    }

    @Override
    public void changeNotificationStatus(boolean viewed, String notificationId) {
        newDb();
        wsdb.changeNotificationStatus(viewed, notificationId);
    }

    @Override
    public void deleteNotificationById(String notificationId) {
        newDb();
        wsdb.deleteNotificationById(notificationId);

    }

    @Override
    public Notification getNotificationsById(String notificationId) {
        newDb();
        return wsdb.getNotificationsById(notificationId);
    }
    
}