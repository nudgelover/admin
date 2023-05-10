package com.kbstar.chart;

import com.kbstar.Service.ChartService;
import com.kbstar.dto.Chart;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
class sumtest {

    @Autowired
    ChartService service;
    //[{}]

    @Test
    void contextLoads() {
        try {
            List<Chart> list = service.getMonthlyTotal();
            JSONArray fma = new JSONArray();
            JSONArray ma = new JSONArray();
            JSONArray avg  = new JSONArray();

            for (Chart c : list) {
                if (c.getGender().toUpperCase().equals("F")) {
                    fma.add(c.getTotal());
                } else {
                    ma.add(c.getTotal());
                }
            }
            JSONObject fmo = new JSONObject();
            JSONObject mo = new JSONObject();
            JSONObject avgo = new JSONObject();
            fmo.put("type", "column");
            fmo.put("name", "Fmale");
            fmo.put("data", fma);

            mo.put("type", "column");
            mo.put("name", "Male");
            mo.put("data", ma);

            avgo.put("type","spline");
            avgo.put("name", "Average");
//            avgo.put("data", "평균데이터"),


            JSONArray data = new JSONArray();
            data.add(fmo);
            data.add(mo);
            data.add(avgo);

            log.info(data.toJSONString());


            int sum = 0;
            for (int i = 0; i < fma.size(); i++) {
                int value = Integer.parseInt(fma.get(i).toString());
                sum += value;
            }

            int sum2 = 0;
            for (int i = 0; i < ma.size(); i++) {
                int value = Integer.parseInt(ma.get(i).toString());
                sum2 += value;
            }

            log.info("Sum of female total: {}", sum);
            log.info("Sum of male total: {}", sum2);
        } catch (Exception e) {
            log.info("error-----------------------------");
            e.printStackTrace();
        }
    }
}