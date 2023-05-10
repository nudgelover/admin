package com.kbstar.sales;


import com.kbstar.Service.SalesService;
import com.kbstar.dto.Sales;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class monthlyTest {
    @Autowired
    SalesService service;

    @Test
    void contextLoads() {
        List<Integer> list = null;
        try {
            list = service.wmonthly();
            log.info(list.toString());


        } catch (Exception e) {
            log.info("시스템 장애입니다.---------------------------------------------");
            e.printStackTrace();
        }
    }

}
