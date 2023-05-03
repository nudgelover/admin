package com.kbstar.adm;

import com.kbstar.Service.AdmService;
import com.kbstar.dto.Adm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UpdateTest {
    @Autowired
    AdmService service;

    @Test
    void contextLoads() {
        Adm adm = new Adm("id1", "pw",4);
        try {
            service.modify(adm);
            service.get();
            log.info("변경정상 정상");
            log.info(adm.toString());
        } catch (Exception e) {
            log.info("시스템 장애입니다.---------------------------------------------");
        }
    }

}
