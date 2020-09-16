package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.dto.IDHelper;
import com.example.demo.model.Account;
import com.example.demo.model.RollInApply;
import com.example.demo.model.RollOutApply;
import com.example.demo.model.TimeManage;
import com.example.demo.model.helper.InfoOutApplyHelper;
import com.example.demo.model.helper.RollOutApplyHelper;
import com.example.demo.model.helper.StuInfoInOutHelper;
import com.example.demo.service.GraduationService;
import com.example.demo.service.StuInfoInOutService;
import com.example.demo.service.TimeManageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author:guan
 * @2020/9/6 10:41
 * 文件信息：
 */
@Controller
@RequestMapping("/stuInfoInOut")
public class StuInfoInOutController {
    @Resource
    private TimeManageService timeManageService;

    /**
     * 考籍转入办理时间设置
     * @param model
     * @return
     */
    @GetMapping("/timeManagement/stuInfoInTimeManagement")
    public String stuInfoInTimeManagement(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        TimeManage timeManage = timeManageService.queryTimeById(12);
        model.addAttribute("timeManage",timeManage);
        return "stuInfoInOut/timeManagement/stuInfoInTimeManagement";
    }

    @PostMapping("/timeManagement/stuInfoInTimeManagement")
    public String stuInfoInTimeManagementSure(String beginTime,String endTime){
        TimeManage timeManage = new TimeManage(12, beginTime, endTime);
        timeManageService.updateTimeManage(timeManage);
        return "redirect:stuInfoInTimeManagement";
    }

    @GetMapping("/timeManagement/stuInfoOutTimeManagement")
    public String stuInfoOutTimeManagement(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        TimeManage timeManage = timeManageService.queryTimeById(13);
        model.addAttribute("timeManage",timeManage);
        return "stuInfoInOut/timeManagement/stuInfoOutTimeManagement";
    }

    @PostMapping("/timeManagement/stuInfoOutTimeManagement")
    public String stuInfoOutTimeManagementSure(String beginTime,String endTime){
        TimeManage timeManage = new TimeManage(13, beginTime, endTime);
        timeManageService.updateTimeManage(timeManage);
        return "redirect:stuInfoOutTimeManagement";
    }

    /**
     *  1、合格考籍资料入库
     * @param model
     * @return
     */



    /**
     *  2、考籍转入资料审核
     * @param model
     * @return
     */

    /**
     *  3、考籍转出手续申请
     * @param model
     * @return
     */


    /**
     *  4、考籍转出资料审核
     * @param model
     * @return
     */



    /**
     *  5、考籍转出资料打包上传
     * @param model
     * @return
     */



    @Resource
    private GraduationService graduationService;
    @GetMapping("/statistic/stuInfoInBeforeStart")
    public String stuInfoInBeforeStart(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<String> city = graduationService.queryAllCityCode();
        model.addAttribute("citys",city);
        return "stuInfoInOut/statistic/stuInfoInBeforeStart";

    }
    @Resource
    private StuInfoInOutService stuInfoInOutService;
    @PostMapping("/statistic/stuInfoInBeforeStart")
    public String stuInfoInBeforeStartSearch(Model model,String cityCode){
//        查找转入前学籍所在城市为cityCode的学生信息
        List<StuInfoInOutHelper> stuInfoInOutHelpers = stuInfoInOutService.queryAllStuRollInByCityCode(cityCode,"");
        int count = stuInfoInOutHelpers.size();
        model.addAttribute("stuInOutList",stuInfoInOutHelpers);
        model.addAttribute("stuInfoCount",count);
        return "stuInfoInOut/statistic/stuInfoInBeforeStart";

    }


    @GetMapping("/statistic/stuInfoInThisStart")
    public String stuInfoInThisStart(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<String> city = graduationService.queryAllCityCode();
        model.addAttribute("citys",city);
        return "stuInfoInOut/statistic/stuInfoInThisStart";
    }

    @PostMapping("/statistic/stuInfoInThisStart")
    public String stuInfoInThisStartSearch(Model model,String cityCode){
//        查找转入前学籍所在城市为cityCode的学生信息
        String examNum = stuInfoInOutService.queryCurrentExamNum("cur_exam_num");
        List<StuInfoInOutHelper> stuInfoInOutHelpers = stuInfoInOutService.queryAllStuRollInByCityCode(cityCode,examNum);
        int count = stuInfoInOutHelpers.size();
        model.addAttribute("stuInOutList",stuInfoInOutHelpers);
        model.addAttribute("stuInfoCount",count);
        return "stuInfoInOut/statistic/stuInfoInThisStart";

    }


    @GetMapping("/statistic/stuInfoOutBeforeEnd")
    public String stuInfoOutBeforeEnd(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<String> city = stuInfoInOutService.queryAllRollOutCity();
        model.addAttribute("citys",city);
        return "stuInfoInOut/statistic/stuInfoOutBeforeEnd";
    }

    @PostMapping("/statistic/stuInfoOutBeforeEnd")
    public String stuInfoOutBeforeEndSearch(Model model,String cityCode){
//        查找转入前学籍所在城市为cityCode的学生信息
        List<StuInfoInOutHelper> stuInfoInOutHelpers = stuInfoInOutService.queryAllStuRollOutByCityCode(cityCode,"");
        int count = stuInfoInOutHelpers.size();
        model.addAttribute("stuInOutList",stuInfoInOutHelpers);
        model.addAttribute("stuInfoCount",count);
        return "stuInfoInOut/statistic/stuInfoOutBeforeEnd";

    }

    @GetMapping("/statistic/stuInfoOutThisEnd")
    public String stuInfoOutThisEnd(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<String> city = stuInfoInOutService.queryAllRollOutCity();
        model.addAttribute("citys",city);
        return "stuInfoInOut/statistic/stuInfoOutThisEnd";
    }


    @PostMapping("/statistic/stuInfoOutThisEnd")
    public String stuInfoOutThisEndSearch(Model model,String cityCode,HttpSession session){
//        查找转入前学籍所在城市为cityCode的学生信息
        String examNum = stuInfoInOutService.queryCurrentExamNum("cur_exam_num");
        List<StuInfoInOutHelper> stuInfoInOutHelpers = stuInfoInOutService.queryAllStuRollOutByCityCode(cityCode,examNum);
        int count = stuInfoInOutHelpers.size();
        model.addAttribute("stuInOutList",stuInfoInOutHelpers);
        model.addAttribute("stuInfoCount",count);
        return "stuInfoInOut/statistic/stuInfoOutThisEnd";

    }

    @GetMapping("/statistic/stuInfoOutThisName")
    public String stuInfoOutThisName(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<StuInfoInOutHelper> stuInfoInOutHelpers = stuInfoInOutService.queryAllStuRollOut();
        int stuNum = stuInfoInOutHelpers.size();
        model.addAttribute("stuCount",stuNum);
        model.addAttribute("stuInOutList",stuInfoInOutHelpers);
        return "stuInfoInOut/statistic/stuInfoOutThisName";
    }

    @GetMapping("/statistic/stuInfoOutTime")
    public String stuInfoOutTime(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        return "stuInfoInOut/statistic/stuInfoOutTime";
    }
    @PostMapping("/statistic/stuInfoOutTime")
    public String stuInfoOutTimeSearch(Model model,String startTime,String endTime) throws ParseException {

        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);

        String start = new SimpleDateFormat("yyyyMMdd").format(startDate);
        String end = new SimpleDateFormat("yyyyMMdd").format(endDate);
        List<StuInfoInOutHelper> stuInfoInOutHelpers = stuInfoInOutService.queryAllStuRollOutByDate(start, end);
        int size = stuInfoInOutHelpers.size();
        model.addAttribute("stuCount",size);
        model.addAttribute("stuInOutList",stuInfoInOutHelpers);
        return "stuInfoInOut/statistic/stuInfoOutTime";
    }

//    考籍转入审核
    @GetMapping("/infoInCheck")
    public String infoInCheck(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<RollInApply> rollInApplies = stuInfoInOutService.queryAllRollInByState(1);
        model.addAttribute("rollInApplyList",rollInApplies);
        return "stuInfoInOut/infoInCheck";
    }
    @PostMapping("/infoInCheck")
    public String infoInCheckSure(Model model,Integer applyId,HttpSession session){
//        获取登录管理员用户名
        Account account = (Account) session.getAttribute("account");
        String admin = account.getAccount();
        stuInfoInOutService.updateRollInState(applyId,2,admin,"");
        return "stuInfoInOut/infoInCheck";
    }
    @PostMapping("/infoInCheckRefuse")
    public String infoInCheckRefuse(Model model,Integer applyId,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        String admin = account.getAccount();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String endDate = formatter.format(date);
        stuInfoInOutService.updateRollInState(applyId,0,admin,endDate);
        return "stuInfoInOut/infoInCheck";
    }

    @GetMapping("/infoEntryDatabase")
    public String infoEntryDatabase(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<RollInApply> rollInApplies = stuInfoInOutService.queryAllRollInByState(2);
        model.addAttribute("rollInApplyList",rollInApplies);
        return "stuInfoInOut/infoEntryDatabase";
    }

    @PostMapping("/infoEntryDatabase")
    public String infoEntryDatabaseSure(Model model,Integer applyId){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String endDate = formatter.format(date);
        //        修改转入表申请状态
        stuInfoInOutService.updateRollInStateEnd(applyId,3,1,endDate);
        //                更新学生学籍状态为转入态
        stuInfoInOutService.updateStuInfoStateByApplyId(applyId,4);
        return "stuInfoInOut/infoEntryDatabase";
    }
    @PostMapping("/infoEntryDatabaseRufuseEnd")
    public String infoEntryDatabaseRefise(Model model,Integer applyId){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String endDate = formatter.format(date);
        stuInfoInOutService.updateRollInStateEnd(applyId,0,0,endDate);
        return "stuInfoInOut/infoEntryDatabase";
    }

    @GetMapping("/infoOutApply")
    public String infoOutApply(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        String examCardNum = account.getAccount();
        InfoOutApplyHelper infoOutApplyHelper = stuInfoInOutService.queryStuInfoByEcn(examCardNum);
        model.addAttribute("infoOutApply",infoOutApplyHelper);
        return "stuInfoInOut/infoOutApply";
    }

    @PostMapping("/infoOutApply")
    public String infoOutApplySure(Model model,String Ecn,String applyCode,String reason,String rollOutTime) throws ParseException {
        RollOutApply rollOutApply = new RollOutApply();
        rollOutApply.setExamCardNum(Ecn);
        rollOutApply.setApplyCode(applyCode);
        rollOutApply.setRollOutReason(reason);
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(rollOutTime);
        String start = new SimpleDateFormat("yyyyMMdd").format(startDate);
        rollOutApply.setRollOutTime(start);
        rollOutApply.setStartDate(start);
        rollOutApply.setApplyState(1);
        rollOutApply.setResult(0);
        stuInfoInOutService.insertRollOutApply(rollOutApply);
        return "redirect:infoOutApply";
    }

    @GetMapping("/infoOutCheck")
    public String infoOutCheck(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<RollOutApplyHelper> rollOutApplyHelpers = stuInfoInOutService.queryAllRollOutApplyByState(1);
        model.addAttribute("rollOutApplyList",rollOutApplyHelpers);
        return "stuInfoInOut/infoOutCheck";
    }

    @PostMapping("/infoOutCheck")
    public String infoOutCheckSure(Model model,Integer applyId,HttpSession session){
        //        获取登录管理员用户名
        String admin =(String)session.getAttribute("auth");
        stuInfoInOutService.updateRollOutState(applyId,2,admin,"");
        return "stuInfoInOut/infoOutCheck";
    }
    @PostMapping("/infoOutCheckRefuse")
    public String infoOutCheckRefuse(Model model,Integer applyId,HttpSession session){
        //        获取登录管理员用户名
        Account account = (Account) session.getAttribute("account");
        String admin =account.getAccount();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String endDate = formatter.format(date);
        stuInfoInOutService.updateRollOutState(applyId,0,admin,endDate);
        return "stuInfoInOut/infoOutCheck";
    }

    @GetMapping("/infoOutAction")
    public String infoOutAction(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<RollOutApplyHelper> rollOutApplyHelpers = stuInfoInOutService.queryAllRollOutApplyByState(2);
        model.addAttribute("rollOutApplyList",rollOutApplyHelpers);
        return "stuInfoInOut/infoOutAction";
    }

    @PostMapping("/infoOutAction")
    public String infoOutActionSure(Model model,Integer applyId){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String endDate = formatter.format(date);
//        更新转出表申请状态
        stuInfoInOutService.updateRollOutState(applyId,3,"",endDate);
//        更新学生信息状态为转出态
        stuInfoInOutService.updateStuInfoStateByApplyId(applyId,2);
        return "stuInfoInOut/infoOutAction";
    }

    @GetMapping("/infoOutPackage")
    public String infoOutPackage(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<RollOutApplyHelper> rollOutApplyHelpers = stuInfoInOutService.queryAllRollOutApplyByState(3);
        model.addAttribute("rollOutApplyList",rollOutApplyHelpers);
        return "stuInfoInOut/infoOutPackage";
    }

    @PostMapping("/infoOutPackage")
    public String infoOutToIn(Model model,String idList){

        if (idList!=null){
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String startDate = formatter.format(date);
            JSONArray jsonArray = JSONArray.fromObject(idList);
            for(int i = 0;i<jsonArray.size();i++) {
                JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
                IDHelper applyId = (IDHelper) JSONObject.toBean(jsonObject, IDHelper.class);
                RollInApply rollInApply = stuInfoInOutService.queryRollOutApplyById(applyId.getId());
                String major = stuInfoInOutService.queryMajorByApplyId(applyId.getId());

                rollInApply.setRollInMajorNum(major);
                rollInApply.setRollInTime(startDate);
                rollInApply.setStartDate(startDate);
                rollInApply.setApplyState(1);
                //插入到转入表中
                stuInfoInOutService.insertRollInApply(rollInApply);
                //更新转出表状态
                stuInfoInOutService.updateRollOutState(applyId.getId(),4,"","");
            }
        }
        return "stuInfoInOut/infoOutPackage";
    }


}
