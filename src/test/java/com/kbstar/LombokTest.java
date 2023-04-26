package com.kbstar;

import com.kbstar.dao.Cust;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LombokTest {
	Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	@Test
	void contextLoads() {
		Cust cust = new Cust("id01","01","wlsgml");
		logger.info(cust.toString());
		logger.error(cust.toString());
		// logback에 info라고 했다하더래도.. 여기서 error로 찍으면 안나와야하는 것 아닌가...?
		// info >>error 이니까....?
	}

}
