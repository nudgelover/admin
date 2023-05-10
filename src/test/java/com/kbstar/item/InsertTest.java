package com.kbstar.item;

import com.kbstar.Service.ItemService;
import com.kbstar.dto.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class InsertTest {
    @Autowired
    ItemService service;

    @Test
    void contextLoads() {
        Item item = new Item(0,"원피스",1000,"e.jpe",null,null);
        try {
            service.register(item);
            service.get();
            log.info("등록 정상");
        } catch (Exception e) {
                log.info("시스템 장애입니다.---------------------------------------------");
        }
    }

}
