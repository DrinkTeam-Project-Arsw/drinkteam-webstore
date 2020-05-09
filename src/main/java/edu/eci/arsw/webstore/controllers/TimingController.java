package edu.eci.arsw.webstore.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import edu.eci.arsw.webstore.model.timing.Timing;
import edu.eci.arsw.webstore.model.timing.ResponseTiming;

@Controller
public class TimingController {

    @MessageMapping("/update")
    @SendTo("/topic/synchronization")
    public Timing synUp(ResponseTiming content) throws Exception {
        System.out.println(">>> 3) suscripto...");
        Thread.sleep(1000); // simulated delay
        return new Timing("Servidor recibio: usuario: " + HtmlUtils.htmlEscape(content.getUserNickname()) + ", tabla: "
        + HtmlUtils.htmlEscape(content.getTableName()) + ", funcion: "+ HtmlUtils.htmlEscape(content.getFunction())+"!");
    }

}
