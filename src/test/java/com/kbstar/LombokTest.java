package com.kbstar;

import com.kbstar.dto.Cust;
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

	}

}
