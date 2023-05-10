package com.kbstar.controller;


import com.kbstar.Service.MarkerService;
import com.kbstar.dto.Marker;
import com.kbstar.dto.MarkerSearch;
import com.kbstar.utill.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/marker")

public class MarkController {
    String dir = "marker/";

    @Autowired
    MarkerService service;

    @Value("${uploadimgdir}")
    String imgdir;

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("center", dir + "add");
        return "index";
    }

    @RequestMapping("/addimpl")
    public String addimpl(Model model, Marker marker) throws Exception {
        MultipartFile mf = marker.getFile();
        String imgname = mf.getOriginalFilename();
        marker.setImg(imgname);
        try {
            service.register(marker);
            FileUploadUtil.saveFile(mf, imgdir);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return "redirect:/marker/all";
    }

    @RequestMapping("/all")
    public String all(Model model) throws Exception {
        List<Marker> list = null;
        try {
            list = service.get();
        } catch (Exception e) {
            throw new Exception("시스템 장애 : ER002");
        }

        model.addAttribute("mlist", list);
        model.addAttribute("center", dir + "all");
        return "index";
    }


    @RequestMapping("/detail")
    public String detail(Model model, int id) throws Exception {
        Marker marker = null;
        try {
            marker = service.get(id);
        } catch (Exception e) {
            throw new Exception("시스템 장애");
        }
        model.addAttribute("markerinfo", marker);

        model.addAttribute("center", dir + "marker_detail");
        return "index";
    }


    @RequestMapping("/updateimpl")
    public String updateimpl(Model model, Marker marker) throws Exception {
        MultipartFile mf = marker.getFile();
        String img = mf.getOriginalFilename();
//        log.info("-------------test----------------");
//        log.info(imgname);
        if (img.equals("") || img == null) {
            service.modify(marker);
        } else {
            marker.setImg(img);
            service.modify(marker);
            FileUploadUtil.saveFile(mf, imgdir);
        }
//        try {
//            marker.setImg(img);
//            service.modify(marker);
//            FileUploadUtil.saveFile(mf, imgdir);
//
//        } catch (Exception e) {
//            log.info("===========================수정실패");
//            e.printStackTrace();
//            throw new Exception("수정오류");
//        }

        return "redirect:/marker/detail?id=" + marker.getId();
    }

    @RequestMapping("/deleteimpl")
    public String deleteimpl(Model model, int id) throws Exception {
        try {
            service.remove(id);
        } catch (Exception e) {
//            log.info("===========================삭제실패");
//            e.printStackTrace();
            throw new Exception("삭제오류");
        }
        return "redirect:/marker/all";

    }

    @RequestMapping("/search")
    public String search(Model model, MarkerSearch ms) throws Exception {
        List<Marker> list = service.search(ms);
        model.addAttribute("ms",ms);
        //검색한걸 다시보내서 value에 입력시켜서 검색한 내용이 안지워지게 해주자!
        model.addAttribute("mlist",list);
        model.addAttribute("center",dir+"all");

        return "index";
    }


}