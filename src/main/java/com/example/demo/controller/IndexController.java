package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.StudentInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class IndexController {

    @GetMapping("/")
    public String defaultGet(Model model, HttpSession session){
        Account account = (Account)session.getAttribute("account");
        if(account==null)
            return "redirect:login";
        // 设置标志位，前端页面进行提示处理？
        return "index";
    }
    @GetMapping("/index")
    public String helloController(Model model, String name){
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/hello1")
    public String hello1Controller(Model model){
        ArrayList<StudentInfo> studentInfos = new ArrayList<>();
        StudentInfo studentInfo1 = new StudentInfo();
        studentInfo1.setName("张三");
        studentInfo1.setAddress("西安");
        StudentInfo studentInfo2 = new StudentInfo();
        studentInfo2.setName("李四");
        studentInfo2.setAddress("北京");
        studentInfos.add(studentInfo1);
        studentInfos.add(studentInfo2);
        model.addAttribute("students",studentInfos);
//        model.addAttribute("name", name);
        return "index";
    }
    @GetMapping("/demo")
    public String examController(Model model, HttpServletRequest request){
        return "demo";
    }
}
