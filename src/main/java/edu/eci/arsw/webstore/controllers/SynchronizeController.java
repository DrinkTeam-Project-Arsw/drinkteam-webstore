package edu.eci.arsw.webstore.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

import edu.eci.arsw.webstore.model.Notification;
import edu.eci.arsw.webstore.services.notification.NotificationServices;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

@Controller
public class SynchronizeController {

    @Autowired
    NotificationServices nService;

    @MessageMapping("/upgrade")
    @SendTo("/topic/syncup")
    public Notification synchronize(Notification notification) throws Exception {
        System.out.println(">>> 3) suscripto...");
        Thread.sleep(1000); // simulated delay
        // recibe: envio, destino, funcion, fecha, url, visto:falso, mensaje

        // Guardar notificacion en base de datos


        return new Notification(HtmlUtils.htmlEscape(notification.getNotificationMessage()),
        HtmlUtils.htmlEscape(notification.getNotificationDate()), HtmlUtils.htmlEscape(notification.getNotificationDestination()),
        HtmlUtils.htmlEscape(notification.getNotificationSend()), HtmlUtils.htmlEscape(notification.getNotificationUrl()),
        HtmlUtils.htmlEscape(notification.getNotificationFunction()), false);

    }

    @RequestMapping(method = RequestMethod.GET, path = { "notifications/{nickname}" })
    public ResponseEntity<?> getNotificationsByNickname(@PathVariable("nickname") String nickname) {
        try {
            System.out.println("Consultando Notificaciones del usuario "+nickname+ "...");
            List<Notification> notifications = new ArrayList<>();

            String data = new Gson().toJson(nService.getNotificationsByNickname(nickname));

            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido retornar las notificaciones", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "notifications")
    public ResponseEntity<?> createNewNotification(@RequestBody String notification) {
        // Formato de json
        // {"1":{}}
        try {
            System.out.println("Creando una nueva notificacion...");
            // Pasar el String JSON a un Map
            Type listType = new TypeToken<Map<Integer, Notification>>() {
            }.getType();
            Map<String, Notification> result = new Gson().fromJson(notification, listType);

            // Obtener las llaves del Map
            Object[] nameKeys = result.keySet().toArray();

            Notification noti = result.get(nameKeys[0]);
            ObjectId newObjectIdUser = new ObjectId(new Date());
            noti.setNotificationId(newObjectIdUser.toHexString());

            // Usuario nuevo Inactivo
            noti.setNotificationViewed(false);
            nService.createNewNotification(noti);
            return new ResponseEntity<>(HttpStatus.CREATED);
            
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido guardar la notificacion", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "notifications")
    public ResponseEntity<?> changeNotificationStatus(@RequestBody String notification) {
        try {
            System.out.println("Actualizando estado de notificacion: " + notification);
            Type listType = new TypeToken<Map<Integer, Notification>>() {
            }.getType();
            Map<String, Notification> result = new Gson().fromJson(notification, listType);

            // Obtener las llaves del Map
            Object[] nameKeys = result.keySet().toArray();

            Notification noti = result.get(nameKeys[0]);

            nService.changeNotificationStatus(noti.isNotificationViewed(), noti.getNotificationId());

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se puede actualizar el estado de la notificacion", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = { "notification/{notificationId}" })
    public ResponseEntity<?> deleteNotificationById(@PathVariable("notificationId") String notificationId) {
        try {
            System.out.println("Eliminando Notificacion: " + notificationId);
            nService.deleteNotificationById(notificationId);

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido eliminar la notificacion: " + notificationId,
                    HttpStatus.FORBIDDEN);
        }
    }
}
