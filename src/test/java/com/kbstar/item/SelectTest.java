package com.kbstar.item;

import com.kbstar.Service.ItemService;
import com.kbstar.dto.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class SelectTest {

    @Autowired
    ItemService service;
    @Test
    void contextLoads() {
        List<Item> list = null;

        try {
            list = service.get();
            for(Item obj:list){
                log.info(obj.toString());
            }
        } catch (Exception e) {
                log.info("시스템 장애입니다.");
                e.printStackTrace();
            }

//            log.info("register error.......");
//            e.printStackTrace();
        }
    }

