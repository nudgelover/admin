package com.kbstar.markerdesc;

import com.kbstar.Service.MarkerDescService;
import com.kbstar.dto.MarkerDesc;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class InsertTest {
    @Autowired
    MarkerDescService service;

    @Test
    void contextLoads() {
        MarkerDesc markerdesc = new MarkerDesc(0,106,"떡볶이",3500,"yang9.jpg");
        try {
            service.register(markerdesc);
            service.get();
            log.info("등록 정상");
        } catch (Exception e) {
                log.info("시스템 장애입니다.---------------------------------------------");
        }
    }

}
