package com.example.demo.controller;

import com.example.demo.mapper.AccountMapper;
import com.example.demo.model.Account;
import com.example.demo.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author yangyang
 * @date 8/27/2020 - 10:01 AM
 * @file SignController.java
 * @function   交互式注册/登录页面 的Controller
 */

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private UserService userService;

    @GetMapping ("")
    public String login(){
        return "login";
    }

    @PostMapping("")
    public String post(Model model, HttpSession session, Account account){
        Account realAccount = userService.getAccountById(account.getAccount());
        if(realAccount!=null && account.getPassword().equals(realAccount.getPassword())){
            session.setAttribute("auth", realAccount.getRole().toString());
            session.setAttribute("account", account);
            // TODO: 实现不同的页面，根据账户角色跳转到不同页面
            return "redirect:index";
        }
        // 登陆失败，跳转回登录界面
        return "login";
    }

    @PostMapping("/admin")
    public String login(Model model, Account account,HttpServletResponse response,
                        HttpServletRequest request){
        int code = userService.Login(account);
        if(code==1||code==2||code==0){
            response.addCookie(new Cookie("token", account.getAccount()));
            request.getSession().setAttribute("account",account);
            // 设置登录类型为管理员
            request.getSession().setAttribute("auth", String.valueOf(code));
            if (code==0){
                String studentId = userService.queryStudentByAccount(account.getAccount());
                request.getSession().setAttribute("studentId",studentId);
            }
            return "redirect:/index";
        }
        model.addAttribute("errorCode",code);
        return "login";
    }

}