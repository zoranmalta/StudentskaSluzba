package com.fax.StudentskaSluzba.rest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class WebSocketController {
    @MessageMapping("/chat")
    @SendTo("/topic/examtest")
    public String send(String message) throws Exception {
       return "websocket povezan";
    }
}
