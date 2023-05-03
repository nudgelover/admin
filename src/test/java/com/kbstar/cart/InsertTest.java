package com.kbstar.cart;

import com.kbstar.Service.CartService;
import com.kbstar.dto.Cart;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class InsertTest {

    @Autowired
    CartService service;
    @Test
    void contextLoads() {
      //  Cart obj = new Cart("id03", 222, 7);
        try {
            service.register(new Cart("id20",101,7));
            log.info("register OK--------------------------------");
        } catch (Exception e) {

                log.info("시스템 장애--------------------------------");
            }
        }
    }


