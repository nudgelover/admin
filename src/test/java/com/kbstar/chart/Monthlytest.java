package com.kbstar.chart;


import com.kbstar.Service.ChartService;
import com.kbstar.dto.Chart;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class Monthlytest {

    @Autowired
    ChartService service;
    //[{}]

    @Test
    void contextLoads() {
        try {
//            service.getMonthlyTotal();
            List<Chart> list = service.getMonthlyTotal();
            JSONArray fma = new JSONArray();
            JSONArray ma = new JSONArray();
            for (Chart c : list) {
                if (c.getGender().toUpperCase().equals("F")) {
                    fma.add(c.getTotal());
                } else {
                    ma.add(c.getTotal());
                }
            }
            JSONObject fmo = new JSONObject();
            JSONObject mo = new JSONObject();
            fmo.put("name","Fmale");
            fmo.put("data",fma);
            mo.put("name","Male");
            mo.put("data",ma);

            JSONArray data = new JSONArray();
            data.add(fmo);
            data.add(mo);

            log.info(data.toJSONString());

        } catch (Exception e) {
            log.info("error-----------------------------");
            e.printStackTrace();
        }


    }
}

