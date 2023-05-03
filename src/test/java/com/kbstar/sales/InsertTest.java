package com.kbstar.sales;

import com.kbstar.Service.SalesService;
import com.kbstar.dto.Sales;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class InsertTest {
    @Autowired
    SalesService service;

    @Test
    void contextLoads() {
        Sales sales = new Sales("2023-04-27", 40000, "M");
        try {
            service.register(sales);
            log.info("등록 정상");
        } catch (Exception e) {

            log.info("시스템 장애입니다.---------------------------------------------");
        }
    }

}
