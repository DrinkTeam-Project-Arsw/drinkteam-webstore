/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.services.notification;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.webstore.model.Notification;
import edu.eci.arsw.webstore.persistence.NotificationPersistence;

/**
 *
 * @author jmvillatei
 */
@Service
public class NotificationServicesStub implements NotificationServices {

    @Autowired
    NotificationPersistence nPersistence;

    @Override
    public List<Notification> getNotificationsByNickname(String nickname) {
        return nPersistence.getNotificationsByNickname(nickname);
    }

    @Override
    public void createNewNotification(Notification notification) {
        nPersistence.createNewNotification(notification);

    }

    @Override
    public void changeNotificationStatus(boolean viewed, String notificationId) {
        nPersistence.changeNotificationStatus(viewed, notificationId);

    }

    @Override
    public void deleteNotificationById(String notificationId) {
        nPersistence.deleteNotificationById(notificationId);

    }

    @Override
    public Notification getNotificationsById(String notificationId) {
        return nPersistence.getNotificationsById(notificationId);
    }
    
    

    
}
