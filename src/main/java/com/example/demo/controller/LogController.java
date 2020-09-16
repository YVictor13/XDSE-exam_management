package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.InfoChangeLog;
import com.example.demo.model.InfoOperateLog;
import com.example.demo.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/logManagement")
public class LogController {

    /**
     * @date 9/09/2020 - 8:24 AM
     * @function    1、日志考藉业务操作管理
     */
    @Resource
    private LogService logService;

    @GetMapping("/stuInfoUpdateLog")
    public String operationLog(HttpSession session,Model model){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<InfoOperateLog> infoOperateLogList = logService.selectAllInfoOperateLog();
        model.addAttribute("infoOperateLogList",infoOperateLogList);

        return "logManagement/stuInfoUpdateLog";
    }

    @PostMapping("/stuInfoUpdateLog")
    public String OperationLog(HttpSession session,Model model,@RequestParam("name") String name,@RequestParam("date")String date) throws ParseException {
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        if(name.equals("")&&date.equals("")){
            model.addAttribute("status",0);//输入为空
            return "logManagement/stuInfoUpdateLog";
        }
        List<InfoOperateLog> infoOperateLogList = logService.selectInfoByNDOperateLog(name,date);
        if(infoOperateLogList.size()==0){
            model.addAttribute("status",1);
            return "logManagement/stuInfoUpdateLog";
        }
        model.addAttribute("infoOperateLogList",infoOperateLogList);
        model.addAttribute("status",2);
        return "logManagement/stuInfoUpdateLog";
    }

    /**
     * @date 9/09/2020 - 8:24 AM
     * @function    1、考藉修改日志管理
     */
    @GetMapping("/stuInfoModifyLog")
    public  String modifyLog(Model model, HttpSession session){
        Account account =(Account)session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<InfoChangeLog> infoChangeLogList = logService.selectAllInfoChangeLog();
        model.addAttribute("infoChangeLogList",infoChangeLogList);

        return "logManagement/stuInfoModifyLog";
    }

    @PostMapping("/stuInfoModifyLog")
    public  String ModifyLog(Model model,HttpSession session,@RequestParam("examCardNum") String examCardNum,@RequestParam("IDCard")String IDCard,@RequestParam("date")String date) throws ParseException {
        Account account =(Account)session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }

        if(date.equals("")&&examCardNum.equals("")&&IDCard.equals("")){
            model.addAttribute("status",0);//输入为空
            return "logManagement/stuInfoModifyLog";
        }

        List<InfoChangeLog> infoChangeLogList = logService.selectByEINDInfoChangeLog(examCardNum,IDCard,date);

        if(infoChangeLogList.size()==0){
            model.addAttribute("status",1);//查不到内容
            return "logManagement/stuInfoModifyLog";
        }
        model.addAttribute("infoChangeLogList",infoChangeLogList);
        model.addAttribute("status",2);
        return "logManagement/stuInfoModifyLog";
    }
}