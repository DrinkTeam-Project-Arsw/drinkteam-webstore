package edu.eci.arsw.webstore.persistence;

import edu.eci.arsw.webstore.model.Notification;
import java.util.List;

public interface NotificationPersistence {
    
     /**
     * Permite consultar todas las notificaciones de un usuario
     * @param nickname nickname del usuario
     * @return  Retorna una lista con todas las notificaciones.
     */
    public List<Notification> getNotificationsByNickname(String nickname);
    
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