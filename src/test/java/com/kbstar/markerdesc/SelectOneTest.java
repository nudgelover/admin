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
class SelectOneTest {

    @Autowired
    MarkerDescService service;
    @Test
    void contextLoads() {
        List<MarkerDesc> list = null;

        try {
            service.get(1006);


        } catch(Exception e) {
                log.info("시스템 장애입니다.");
                e.printStackTrace();    }

        }
    }

