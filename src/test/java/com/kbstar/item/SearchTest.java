package com.kbstar.item;

import com.kbstar.Service.ItemService;
import com.kbstar.dto.ItemSearch;
import com.kbstar.dto.MarkerSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SearchTest {
    @Autowired
    ItemService service;

    @Test
    void contextLoads() throws Exception {
        try {
            ItemSearch is = new ItemSearch("휴",10000,"2023/04/01","2023/05/04");
          service.search(is);
        }catch (Exception e) {
            log.info("에러");
            e.printStackTrace();
        }
    }
}

