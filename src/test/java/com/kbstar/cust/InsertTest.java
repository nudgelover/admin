package com.kbstar.cust;

import com.kbstar.Service.CustService;
import com.kbstar.dto.Cust;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
@SpringBootTest
class InsertTest {
    @Autowired
    CustService service;

    @Test
    void contextLoads() {
        Cust cust = new Cust("id21", "pwd01", "james");
        try {
            service.register(cust);
            log.info("등록 정상");
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                log.info("ID가 중복되었습니다.--------------------------------------------");
                //e.printStackTrace();
                //만약 이미 있는 아이디 insert하면,
                // org.springframework.dao.DuplicateKeyException:
                // ORA-00001: unique constraint (SHOP.SYS_C007389) violated
            } else
                log.info("시스템 장애입니다.---------------------------------------------");
        }
    }

}
