/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.services.notification;

import java.util.List;

import edu.eci.arsw.webstore.model.Notification;

/**
 *
 * @author jmvillatei
 */
public interface NotificationServices {
    
    /**
     * Permite consultar todas las notificaciones de un usuario
     * @param nickname nickname del usuario
     * @return  Retorna una lista con todas las notificaciones.
     */
    public List<Notification> getNotificationsByNickname(String nickname);
    
    /**
     * Metodo que permite consultar una notificacion por su id.
     * @param notificationId    Es el id de la notificacion
     * @return  Retorna una notificacion.
     */
    public Notification getNotificationsById(String notificationId);
    
    /**
     * este metodo permite crear una notificacion.
     * 
     * @param notification Es notificacion a guardar
     */
    public void createNewNotification(Notification notification);

    /**
     * este metodo permite Actualizar la notificacion si ya fue leida
     * 
     * @param viewed nuevo estado
     * @param notificationId id de la notificacion a modificar
     */
    public void changeNotificationStatus(boolean viewed, String notificationId);

    /**
     * Este metodo permite eliminar una notificacion
     * 
     * @param notificationId
     */
    public void deleteNotificationById(String notificationId);
    
}
