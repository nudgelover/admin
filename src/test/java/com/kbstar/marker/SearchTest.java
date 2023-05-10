package com.kbstar.marker;

import com.kbstar.Service.MarkerService;
import com.kbstar.dto.Marker;
import com.kbstar.dto.MarkerSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class SearchTest {
    @Autowired
    MarkerService service;

//102	양꼬치집	http://www.google.com	37.638346	127.012602	yang.jpg	S
    @Test
    void contextLoads() throws Exception {

//        List<Marker> list = null;
        try {
            MarkerSearch ms = new MarkerSearch("국","");
          service.search(ms);
        }catch (Exception e) {
            log.info("에러");
            e.printStackTrace();
        }
    }
}

