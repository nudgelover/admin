package com.kbstar.adm;

import com.kbstar.Service.AdmService;
import com.kbstar.dto.Adm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class selectTest {
    @Autowired
    AdmService service;

    @Test
    void contextLoads() {
        List<Adm> list = null;
        try {
            list = service.get();
            for(Adm Adm:list){
            log.info(Adm.toString());}

        } catch (Exception e) {
                log.info("시스템 장애입니다.---------------------------------------------");
                e.printStackTrace();
        }
    }

}
