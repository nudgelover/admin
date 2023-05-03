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
class SelectTest {

    @Autowired
    MarkerDescService service;
    @Test
    void contextLoads() {
        List<MarkerDesc> list = null;

        try {
            list = service.get();
            for(MarkerDesc obj:list){
                log.info(obj.toString());
            }
        } catch (Exception e) {
                log.info("시스템 장애입니다.");
                e.printStackTrace();
            }

        }
    }

