package com.kbstar.cust;

import com.kbstar.Service.CustService;
import com.kbstar.dto.Cust;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class selectTest {
    @Autowired
    CustService service;

    @Test
    void contextLoads() {
        List<Cust> list = null;
        try {
            list = service.get();
            for(Cust cust:list){
            log.info(cust.toString());}

        } catch (Exception e) {
                log.info("시스템 장애입니다.---------------------------------------------");
                e.printStackTrace();
        }
    }

}
