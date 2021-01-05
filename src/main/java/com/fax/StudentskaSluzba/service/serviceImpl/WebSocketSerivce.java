package com.fax.StudentskaSluzba.service.serviceImpl;

import com.fax.StudentskaSluzba.modeldto.ExamTestDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@Controller
public class WebSocketSerivce {
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketSerivce(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate=simpMessagingTemplate;
    }

    public void sendMessages(ExamTestDTO examTestDTO) {
        System.out.println("Poruka poslata sa severskog Websocketa");
        this.simpMessagingTemplate.convertAndSend("/topic/examtest",examTestDTO);

    }
}
