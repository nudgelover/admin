package com.kbstar.controller;

import com.kbstar.Service.ChartService;
import com.kbstar.Service.SalesService;
import com.kbstar.dto.Adm;
import com.kbstar.dto.Chart;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
public class ChartImplController {
    @Autowired
    SalesService salesService;
    @Autowired
    ChartService chartService;

    @RequestMapping("/managerchart")
    public Object managerchart() {
        // [{name: 'Chrome', y: 70.67,}, {name: 'Edge', y: 14.77}....]
        //        JSONArray branch = new JSONArray();
        //        branch.add("Korea");
        //        branch.add("Japan");
        //        branch.add("USA");

        JSONArray array = new JSONArray(); // []

        ArrayList<String> list = new ArrayList<>();
        list.add("Korea");
        list.add("Japan");
        list.add("USA");
        list.add("Singapore");
        log.info("list={}", list); // list=[Korea, Japan, USA, kiwi]

        for (int i = 0; i < 4; i++) {
            JSONObject manCnt = new JSONObject(); // {}
            log.info("list.get(i)={}", list.get(i));
            Random r = new Random();
            int cnt = r.nextInt(1000) + 1;
            manCnt.put("name", list.get(i));
            manCnt.put("y", cnt);
            array.add(manCnt);
        }


        //        object.put("name",branch);
        //        object.put("y", manCnt);


        //배열이 아닌, json object 리턴!  // 정정 => 반대로 되어야 합니다.
        return array;
    }

    @RequestMapping("/monthlychart")
    public Object monthlychart() {
        JSONObject monthlycnt = new JSONObject();
        monthlycnt.put("mcnt", salesService.mmonthly());
        monthlycnt.put("wcnt", salesService.wmonthly());
//평균 이랑 합계 넣어보기!!!!!!!!
        return monthlycnt;
    }

    @RequestMapping("/monthlychart1")
    public Object monthlychart1() throws Exception {

        List<Chart> list = chartService.getMonthlyTotal();
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
        fmo.put("name", "Fmale");
        fmo.put("data", fma);
        mo.put("name", "Male");
        mo.put("data", ma);

        JSONArray data = new JSONArray();
        data.add(fmo);
        data.add(mo);

        return data;
    }
    @RequestMapping("/monthlychart2")
    public Object monthlychart2() throws Exception {

        List<Chart> list = chartService.getMonthlyTotal();
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
        fmo.put("type", "column");
        fmo.put("name", "Fmale");
        fmo.put("data", fma);

        mo.put("type", "column");
        mo.put("name", "Male");
        mo.put("data", ma);

        JSONArray data = new JSONArray();
        data.add(fmo);
        data.add(mo);

        return data;
    }
}
