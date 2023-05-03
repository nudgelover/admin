package com.kbstar.sales;


import com.kbstar.Service.SalesService;
import com.kbstar.dto.Sales;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class selectTest {
    @Autowired
    SalesService service;

    @Test
    void contextLoads() {
        List<Sales> list = null;
        try {
            list = service.get();
            for(Sales sales:list){
            log.info(sales.toString());}

        } catch (Exception e) {
                log.info("시스템 장애입니다.---------------------------------------------");
                e.printStackTrace();
        }
    }

}
