package com.kbstar.markerdesc;


import com.kbstar.Service.MarkerDescService;
import com.kbstar.dto.MarkerDesc;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class UpdateTest {

    @Autowired
    MarkerDescService service;
    @Test
    void contextLoads() {
        List<MarkerDesc> list = null;

        try {
            service.modify(new MarkerDesc(1006,106,"라면",4000,"ramen.jpg"));
            service.get();


        } catch(Exception e) {
            log.info("시스템 장애입니다.");
            e.printStackTrace();    }

    }
        }


