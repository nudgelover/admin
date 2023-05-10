package com.kbstar.controller;


import com.kbstar.Service.ItemService;
import com.kbstar.dto.*;
import com.kbstar.utill.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/item")

public class ltemController {
    String dir = "item/";

    @Autowired
    ItemService service;

    @Value("${uploadimgdir}")
    String imgdir;
//    application.properties에 imgdir 설정되어있음.

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("center", dir + "add");
        return "index";
    }

    @RequestMapping("/addimpl")
    public String addimpl(Model model, Item item) throws Exception {
        MultipartFile mf = item.getImg();
        String imgname = mf.getOriginalFilename();
        item.setImgname(imgname);
        try {
            service.register(item);
            FileUploadUtil.saveFile(mf, imgdir);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return "redirect:/item/all";
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
    public String detail(Model model, int id) throws Exception {
        Item item = null;
        try {
            item = service.get(id);
        } catch (Exception e) {
            throw new Exception("시스템 장애");
        }
        model.addAttribute("iteminfo", item);

        model.addAttribute("center", dir + "item_detail");
        return "index";
    }


    @RequestMapping("/updateimpl")
    public String updateimpl(Model model, Item item) throws Exception {
        MultipartFile mf = item.getImg();
        String imgname = mf.getOriginalFilename();
//        log.info("-------------test----------------");
//        log.info(imgname);
        if (imgname.equals("") || imgname == null) {
            service.modify(item);
        } else {
            item.setImgname(imgname);
            service.modify(item);
            FileUploadUtil.saveFile(mf, imgdir);
        }

        return "redirect:/item/detail?id=" + item.getId();
    }

    @RequestMapping("/deleteimpl")
    public String deleteimpl(Model model, int id) throws Exception {
        try {
            service.remove(id);
        } catch (Exception e) {
            throw new Exception("삭제오류");
        }
        return "redirect:/item/all";

    }

    @RequestMapping("/search")
    public String search(Model model, ItemSearch is) throws Exception {
        List<Item> list = service.search(is);
        model.addAttribute("is",is);
        //검색한걸 다시보내서 value에 입력시켜서 검색한 내용이 안지워지게 해주자!
        model.addAttribute("ilist",list);
        model.addAttribute("center",dir+"all");
//        log.info("------------------------------test");
//        log.info(is.getStartdate());
//        log.info(is.getEnddate());

        return "index";
    }

}