package com.kbstar.controller;


import com.kbstar.Service.ItemService;
import com.kbstar.dto.Cust;
import com.kbstar.dto.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/item")

public class ltemController {
    String dir = "item/";

    @Autowired
    ItemService service;

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("center", dir + "add");
        return "index";
    }

    @RequestMapping("/all")
    public String all(Model model) throws Exception {
        List<Item> list = null;
        try {
            list = service.get();
        } catch (Exception e) {
            throw new Exception("시스템 장애 : ER002");
        }

        model.addAttribute("ilist", list);
        model.addAttribute("center", dir + "all");
        return "index";
    }


    @RequestMapping("/detail")
    public String detail(Model model, String id) throws Exception {
//        Item item = null;
//        try {
//            item = service.get(id);
//        } catch (Exception e) {
//            throw new Exception("시스템 장애");
//        }
 //       model.addAttribute("iteminfo", item);

        model.addAttribute("center", dir + "detail");
        return "index";
    }
}