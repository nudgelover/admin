package com.kbstar.cart;

import com.kbstar.Service.CartService;
import com.kbstar.dto.Cart;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UpdateTest {

    @Autowired
    CartService service;
    @Test
    void contextLoads() {
//        Cart obj = new Cart(101,"id01",100, 5,null);
        try {
            service.modify(new Cart(105,"id01",100,19));
            log.info("update OK--------------------------------");
            service.get();
        } catch (Exception e) {
            log.info("update error-----------------------------");
        }
    }

}
