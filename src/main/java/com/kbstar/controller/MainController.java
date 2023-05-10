package com.kbstar.controller;

import com.kbstar.Service.AdmService;
import com.kbstar.dto.Adm;
import com.kbstar.dto.Manager;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller

@Slf4j
public class MainController {
    @Value("${adminserver}")
    String adminserver;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    AdmService admservice;
    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @RequestMapping("/")
    public String main(Model model) {
        model.addAttribute("adminserver",adminserver);
        return "index";
    }

    @RequestMapping("/chart")
    public String chart(Model model) {
        model.addAttribute("center", "chart");
        return "index";
    }

    @RequestMapping("/table")
    public String table(Model model) {

        List<Manager> list = new ArrayList<>();
        list.add(new Manager(1, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(2, "Garrett Winters", "Accountant", "Tokyo", 63, "2011/07/25", 170750));
        list.add(new Manager(3, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(4, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(5, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(6, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(7, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(8, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(9, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(10, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(11, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(12, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(13, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(14, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(15, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(16, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(17, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(18, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(19, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));
        list.add(new Manager(20, "Tiger Nixon", " System Architect", "Edinburgh", 61, "2011/04/25", 320800));


        model.addAttribute("managerAll", list);
        model.addAttribute("center", "table");
        return "index";
    }
    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("center", "dashboard");
        return "index";
    }

    @RequestMapping("/livechart")
    public String livechart(Model model) {
        model.addAttribute("adminserver",adminserver);
        model.addAttribute("center", "livechart");
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("center", "login");
        return "index";
    }

    @RequestMapping("/loginimpl")
    public String loginimpl(Model model, String id, String pwd, HttpSession session) throws Exception {
        logger.info(id + " " + pwd+"-----------------------------------------------");
        Adm adm = null;
        String nextPage = "loginfail";

        try {
            adm = admservice.get(id);
            if(adm != null &&encoder.matches(pwd,adm.getPwd())){
                nextPage ="loginok";
                session.setMaxInactiveInterval(600000);
                session.setAttribute("loginadm",adm);
            }
        } catch (Exception e) {
            throw new Exception("시스템 장애, 잠시 후 다시 로그인 해주세요");
        }

        model.addAttribute("center", nextPage);
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session) {
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
        // spring security에서 logout을 사용하면 중복이 되서 logout시 자동으로 login 페이지로 간다
        //그래서 우리는 logoutimpl를 사용해서 홈으로 가게하자
    }

    @RequestMapping("/logoutimpl")
    public String logoutimpl(Model model, HttpSession session) {
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }


    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("center", "register");
        return "index";
    }
    @RequestMapping("/registerimpl")
    public String register(Model model, Adm adm, HttpSession session) throws Exception {
        try {
            adm.setPwd(encoder.encode(adm.getPwd()));
            admservice.register(adm);
            session.setAttribute("loginadm",adm);
            //이렇게 하면 가입하면 바로 로그인 됨!
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("가입오류");
        }

        model.addAttribute("adm", adm);
        model.addAttribute("center", "registerok");

        return "index";
    }


    @RequestMapping("/adminfo")
    public String adminfo(Model model, String id) throws Exception {
        Adm adm= null;
        try {
            adm = admservice.get(id);
        } catch (Exception e) {
            throw new Exception("시스템 장애");
        }
        model.addAttribute("adminfo",adm);
        model.addAttribute("center", "adminfo");
        return "index";
    }

    @RequestMapping("/adminfoimpl")
    public String adminfoimpl(Model model, Adm adm, HttpSession session) throws Exception {
        try {
            adm.setPwd(encoder.encode(adm.getPwd()));
            admservice.modify(adm);
            session.setAttribute("adminfo",adm);
        } catch (Exception e) {
            throw new Exception("가입오류");
        }

        //수정 후 다시 수정페이지로 이동 해 잘 수정됬는 지 확인, "redirect"를 통해서
        return "redirect:/adminfo?id="+adm.getId();
        //여기서는 url연결할 때 요렇게! ${} 이거 아니다!
    }

    @RequestMapping("/websocket")
    public String websocket(Model model) {
        model.addAttribute("adminserver",adminserver);
        model.addAttribute("center", "websocket");
        return "index";
    }
}

