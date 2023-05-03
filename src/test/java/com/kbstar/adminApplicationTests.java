package com.kbstar;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Slf4j
@SpringBootTest
class adminApplicationTests {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void contextLoads() {
        String rawPassword = "pwd01";
        String encPassword = encoder.encode(rawPassword);
        log.info(rawPassword);
        log.info("암호화 된 비번" + encPassword);
        //입력한 비번과 암호화된 비번이 일치하면 True
        boolean result = encoder.matches(rawPassword,encPassword);
        log.info(result+"");
    }

}
