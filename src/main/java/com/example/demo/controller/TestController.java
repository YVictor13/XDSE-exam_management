package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author linxin
 * @date 8/29/2020 - 4:13 PM
 * @file TestController.java
 * @function    用于测试文件
 */
@Controller
@RequestMapping("/stuInfoInOut")
public class TestController {
    @GetMapping("/home")
    public String helloController(Model model){
        return "fragments/home";
    }
}
