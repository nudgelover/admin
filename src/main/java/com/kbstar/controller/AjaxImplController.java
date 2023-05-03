package com.kbstar.controller;

import com.kbstar.Service.AdmService;
import com.kbstar.dto.Adm;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
public class AjaxImplController {
    @Autowired
    AdmService admservice;
//    RestController  : Json 형태로 객체 데이터를 반환
    @RequestMapping("/checkid")
    public Object checkid(String id) throws Exception {
        int result = 0;

        Adm adm = null;
        adm = admservice.get(id);
        if(adm!= null){
            result = 1;
        }
        return result;
    }
}
