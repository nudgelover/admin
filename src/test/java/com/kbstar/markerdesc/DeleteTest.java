package com.kbstar.markerdesc;

import com.kbstar.Service.MarkerDescService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class DeleteTest {

    @Autowired
    MarkerDescService service;

    @Test
    void contextLoads() {
        try {
            service.remove(1006);
            log.info("삭제완료");
            service.get();
        } catch (Exception e) {
            log.info("시스템 장애입니다.");
        }

    }
}

