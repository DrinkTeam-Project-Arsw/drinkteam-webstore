package edu.eci.arsw.webstore.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import edu.eci.arsw.webstore.model.Synchronize;
import edu.eci.arsw.webstore.model.SynchronizeResponse;


@Controller
public class SynchronizeController {

    @MessageMapping("/upgrade")
    @SendTo("/topic/syncup")
    public Synchronize synchronize(SynchronizeResponse message) throws Exception {
        System.out.println(">>> 3) suscripto...");
        Thread.sleep(1000); // simulated delay
        return new Synchronize("enviando mensaaje desde la API: Hello, " + HtmlUtils.htmlEscape(message.getUserNickname()) + ", "
                + HtmlUtils.htmlEscape(message.getTableName()) + "!");
    }

}
