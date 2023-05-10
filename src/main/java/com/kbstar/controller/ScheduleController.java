package com.kbstar.controller;

import com.kbstar.Service.CartService;
import com.kbstar.dto.MsgAdm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class ScheduleController {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    @Autowired
    CartService service;

    @Scheduled(cron = "*/5 * * * * *")
    public void cronJobDailyUpdate() throws Exception {

//        log.info("----------- Scheduler start------------");
        //매 15초 마다 찍히는 게 확인됨.
        Random r = new Random();
//        int content1 = r.nextInt(100) + 1;
        int content1 = service.count();
//        int content2 = r.nextInt(100) + 1;
        int content2 = service.countprice();
        int content3 = r.nextInt(100) + 1;
        int content4 = r.nextInt(100) + 1;



        MsgAdm msg = new MsgAdm();
        msg.setContent1(content1);
        msg.setContent2(content2);
        msg.setContent3(content3);
        msg.setContent4(content4);

        messagingTemplate.convertAndSend("/sendadm", msg);
    }

    @Scheduled(cron = "*/3 * * * * *")
    public void cronJobWeeklyUpdate() {
//        Random r = new Random();
//        int num = r.nextInt(100)+1;
//        log.info(num+"");


    }

    @Scheduled(cron = "*/3 * * * * *")
    public void cartCount() throws Exception {
        int num = 0;
        num = service.count();
        log.info(num + "");
    }
}