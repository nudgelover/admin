package com.kbstar.adm;

import com.kbstar.Service.AdmService;
import com.kbstar.dto.Adm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
@SpringBootTest
class InsertTest {
    @Autowired
    AdmService service;

    @Test
    void contextLoads() {
        Adm adm = new Adm("admin04", "pwd04", 4);
        try {
            service.register(adm);
            service.get();
            log.info("등록 정상");
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                log.info("ID가 중복되었습니다.--------------------------------------------");
            } else
                log.info("시스템 장애입니다.---------------------------------------------");
        }
    }

}
