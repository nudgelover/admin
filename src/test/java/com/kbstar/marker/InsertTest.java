package com.kbstar.marker;

import com.kbstar.Service.MarkerService;
import com.kbstar.dto.Marker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
@SpringBootTest
class InsertTest {
    @Autowired
    MarkerService service;

    @Test
    void contextLoads() throws Exception {
        Marker obj = new Marker(0, "조조칼국수", "http://www.naver.com", 37.635327, 127.010507, "yang.jpg", "S");
        try {
            service.register(obj);
            log.info("등록 정상");
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                log.info("이미 사용 중인 ID입니다!----------------------------------------");
            } else {
                log.info("등록에러..");
            }
        }
    }
}
