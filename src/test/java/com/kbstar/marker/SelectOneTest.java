package com.kbstar.marker;

import com.kbstar.Service.MarkerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SelectOneTest {
    @Autowired
    MarkerService service;
    //102	양꼬치집	http://www.google.com	37.638346	127.012602	yang.jpg	S
    @Test
    void contextLoads() throws Exception {
        try {
            service.get(100);
            log.info(getClass().toString());
        } catch (Exception e) {
            log.info("셀렉 에러..");
        }
    }
}
