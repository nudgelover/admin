package com.kbstar.adm;

import com.kbstar.Service.AdmService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SelectOneTest {
    @Autowired
    AdmService service;

    @Test
    void contextLoads() throws Exception{

        try {
            service.get("admin01");
            log.info(getClass().toString());
        } catch (Exception e) {
            log.info("시스템 장애입니다.---------------------------------------------");
        }
    }

}
