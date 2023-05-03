package com.kbstar.cust;

import com.kbstar.Service.CustService;
import com.kbstar.dto.Cust;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SelectOneTest {
    @Autowired
    CustService service;

    @Test
    void contextLoads() throws Exception {
        Cust cust = null;
        try {
            cust = service.get("id012222");
            log.info(cust.toString());
        } catch (Exception e) {
            log.info("시스템 장애입니다.---------------------------------------------");
        }
    }

}
