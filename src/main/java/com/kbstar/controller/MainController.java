package com.kbstar.controller;

import com.kbstar.dao.Manager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @RequestMapping("/")
    public String main() {
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
}
