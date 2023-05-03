package com.kbstar.controller;


import com.kbstar.Service.CustService;
import com.kbstar.dto.Adm;
import com.kbstar.dto.Cust;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/cust")

public class CustController {

    String dir = "cust/";
    @Autowired
    CustService service;

    @Autowired
    BCryptPasswordEncoder encoder;

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("center", dir + "add");
        return "index";
    }


    @RequestMapping("/all")
    public String all(Model model) throws Exception {
        List<Cust> list = null;
        try {
            list = service.get();

        } catch (Exception e) {

            throw new Exception("시스템장애: ER0001");
        }

        model.addAttribute("clist", list);
        model.addAttribute("center", dir + "all");
        return "index";
    }

    @RequestMapping("/addimpl")
    public String addimpl(Model model, @Validated Cust cust, Errors errors) throws Exception {
        if (errors.hasErrors()) {
            List<ObjectError> es = errors.getAllErrors();
            String msg = "";
            for (ObjectError e : es) {
                msg += "<h4>";
                msg += e.getDefaultMessage();
                msg += "</h4>";
            }
            throw new Exception(msg);
        }

        cust.setPwd(encoder.encode(cust.getPwd()));
        service.register(cust);
        return "redirect:/cust/all";
    }

    @RequestMapping("/detail")
    public String cust_detail(Model model, String id) throws Exception {
        Cust cust = null;
        try {
            cust = service.get(id);
        } catch (Exception e) {
            throw new Exception("시스템 장애");
        }
        model.addAttribute("custinfo", cust);
        model.addAttribute("center", dir + "cust_detail");
        return "index";
    }

    @RequestMapping("/updateimpl")
    public String updateimpl(Model model, @Validated Cust cust, Errors errors) throws Exception {
        if (errors.hasErrors()) {
            List<ObjectError> es = errors.getAllErrors();
            String msg = "";
            for (ObjectError e : es) {
                msg += "<h4>";
                msg += e.getDefaultMessage();
                msg += "</h4>";
            }
            throw new Exception(msg);
        }
        cust.setPwd(encoder.encode(cust.getPwd()));
        service.modify(cust);
        return "redirect:/cust/detail?id=" + cust.getId();
    }

    @RequestMapping("/deleteimpl")
    public String deleteimpl(Model model, String id) throws Exception {
        try {
            service.remove(id);
        } catch (Exception e) {
            throw new Exception("삭제오류");
        }
        return "redirect:/cust/all";

    }


}

