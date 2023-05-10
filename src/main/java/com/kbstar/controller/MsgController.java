package com.kbstar.controller;

import com.kbstar.dto.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MsgController {
    @Autowired
    SimpMessagingTemplate template;

    @MessageMapping("/receiveall") // 모두에게 전송
    public void receiveall(Msg msg, SimpMessageHeaderAccessor headerAccessor) {
//        System.out.println(msg);
//        System.out.println("headerAccessor------------------------------------------");
//        System.out.println(headerAccessor);
//        어디에 쓰는 건지 몰라서 찍어보니 이런거 나옴
//      StompHeaderAccessor [headers={simpMessageType=MESSAGE, stompCommand=SEND, nativeHeaders={destination=[/receiveall], content-length=[39]}, simpSessionAttributes={}, simpHeartbeat=[J@4e65daa4, lookupDestination=/receiveall, simpSessionId=uunjqber, simpDestination=/receiveall}]
        System.out.println("headerAccessor------------------------------------------");
        template.convertAndSend("/send", msg);
    }

    @MessageMapping("/receiveme") // 나에게만 전송 ex)Chatbot
    public void receiveme(Msg msg, SimpMessageHeaderAccessor headerAccessor) {
//        System.out.println(msg);
        String id = msg.getSendid();
        template.convertAndSend("/send/" + id, msg);
    }

    @MessageMapping("/receiveto") // 특정 Id에게 전송
    public void receiveto(Msg msg, SimpMessageHeaderAccessor headerAccessor) {
//        String id = msg.getSendid();

        String target = msg.getReceiveid();
        template.convertAndSend("/send/to/" + target, msg);
    }
}