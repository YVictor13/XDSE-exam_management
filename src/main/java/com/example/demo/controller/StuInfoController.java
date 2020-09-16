package com.example.demo.controller;

import com.example.demo.Interceptor.annotation.LoginRequire;
import com.example.demo.dto.ECNList;
import com.example.demo.dto.QuitManageSearch;
import com.example.demo.dto.ImportGradeDto;
import com.example.demo.dto.KeyInfoChangeApply;
import com.example.demo.dto.StuBasicInfo;
import com.example.demo.model.*;
import com.example.demo.model.Account;
import com.example.demo.model.ExamInfo;
import com.example.demo.model.StudentInfo;
import com.example.demo.model.TimeManage;
import com.example.demo.service.ExamInfoService;
import com.example.demo.service.SignUpInfoService;
import com.example.demo.service.StuInfoService;
import com.example.demo.service.TimeManageService;
import com.example.demo.util.FilePathUtil;
import com.example.demo.util.UniversalResponseBody;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linxin
 * @date 8/30/2020 - 8:24 AM
 * @file ExamCardNumController.java
 * @function    跳转到考生准考证号修改申请页面
 */
@Controller
@RequestMapping("/stuInfo")
public class StuInfoController {
    @Resource
    private StuInfoService stuInfoService;
    @Resource
    private SignUpInfoService signUpInfoService;
    @Resource
    private TimeManageService timeManageService;
    @Resource
    private ExamInfoService examInfoService;

    // 本地文件存储路径，在application.yml中配置
    @Value("${file-store-path}")
    private String basePath ;

    // 关键信息修改审核
    @GetMapping("/criticalInfoModifyCheck")
    @LoginRequire("1")
    public String getCriticalInfoModifyCheck(Model model){
        return "stuInfo/criticalInfoModifyCheck";
    }

    // 照片修改
    @GetMapping("/photoModify")
    @LoginRequire("1")
    public String getPhotoModify(Model model){
        StuBasicInfo stuBasicInfo = new StuBasicInfo();
        model.addAttribute("stuBasicInfo",stuBasicInfo);
        return "stuInfo/photoModify";
    }
    @PostMapping("/photoModify")
    @LoginRequire("1")
    public String postPhotoModify1(Model model, String ecn){
        StuBasicInfo stuBasicInfo = signUpInfoService.getStuBasicInfoByECN(ecn);
        if(stuBasicInfo==null)
            stuBasicInfo = new StuBasicInfo();
        model.addAttribute("stuBasicInfo",stuBasicInfo);
        return "stuInfo/photoModify";
    }

    // 准考证号修改审核
    @GetMapping("/ECNModifyCheck")
    @LoginRequire("1")
    public String getECNModifyCheck(Model model){
        // 暂无页面
        return "index";
    }
    @PostMapping("/photoModify/upload")
    @ResponseBody
    public UniversalResponseBody<?> postPhotoModify2(@RequestParam("ECN")String ecn,
                                               @RequestParam("file") MultipartFile file) {

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            System.out.println("不属于图片类型");
            return new UniversalResponseBody<>(1, "文件不属于图片类型");
        }


        String extension = FilePathUtil.getExtension(file.getOriginalFilename());
        String stuId = signUpInfoService.getStuIdByECN(ecn);
        if (stuId.equals("")) {
            return new UniversalResponseBody<>(2, "不存在该考生");
        }
        String newFileName = stuId + extension;

        File f = new File(basePath, newFileName);
        try {
            file.transferTo(f);
            // 更新数据库
            if (stuInfoService.updatePicPathById(stuId, newFileName) != 0) {
                return new UniversalResponseBody<>(3, "数据库修改失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回文件名，即访问url
        return new UniversalResponseBody<>(0, "success", newFileName);
    }
    @GetMapping("/search")
    public String stuInfoSearch(Model model){
        return "stuInfo/search";
    }
//    根据地区统计考生数量
    @GetMapping("/statistics")
    @LoginRequire("2")
    public String stuInfoStatistics(Model model, String search_type,String search_city){
//        查询报名生所有城市
        List<String> citys = stuInfoService.queryAllCity();
        model.addAttribute("citys",citys);
        List<StudentInfo> studentInfos = new ArrayList<>();
        if (search_type!=null&&search_city!=null){
            if (search_type.equals("1")){
                studentInfos = stuInfoService.countStuInfoByDis(search_city);
                System.out.println(studentInfos);
            }

        }

        if (!studentInfos.isEmpty()){
            int stuNum = studentInfos.size();
            model.addAttribute("studentsList",studentInfos);
            model.addAttribute("stuNum",stuNum);
        }

        return "stuInfo/statistics";
    }
    @GetMapping("/timeManage")
    @LoginRequire("1")
    public String TimeManage(Model model){
        List<TimeManage> timeManages = timeManageService.queryAllTimeSet();
        for (int i=0;i<=4;i++){
            model.addAttribute("timeManage"+i,timeManages.get(i));
        }
        return "stuInfo/timeManage";
    }
    @PostMapping("/timeManage")
    public String updateTime(Model model, HttpServletRequest req){
        ArrayList<TimeManage> timeManages = new ArrayList<>();
        for (int i=0;i<=4;i++){
            Integer userId = Integer.valueOf(req.getParameter("userId"+i));
            String beginTime = req.getParameter("beginTime"+i);
            String endTime = req.getParameter("endTime"+i);
            if (!(beginTime.equals(""))&&!(endTime.equals(""))){
                timeManages.add(new TimeManage(userId,beginTime,endTime));
            }
        }
        for (TimeManage timeManage:timeManages){
            timeManageService.updateTimeManage(timeManage);
        }

        return "redirect:timeManage";
    }

    // 非关键信息修改
    @GetMapping("/nonCriticalInfoModify")
    @LoginRequire("1") // TODO:未实现考生登录，暂时改为1
    public String getNonCriticalInfoModify(Model model, HttpSession session){

        String studentId = (String)session.getAttribute("studentId");
        StudentInfo studentInfo = stuInfoService.queryUnCriticalInfo(studentId);
        if(studentInfo==null){
            studentInfo = new StudentInfo();
        }
        model.addAttribute("studentInfo",studentInfo);
        return "stuInfo/nonCriticalInfoModify";
    }
    @PostMapping("/nonCriticalInfoModify")
    public String postNonCriticalInfoModify(Model model, HttpSession session, StudentInfo studentInfo){
        String studentId = (String)session.getAttribute("studentId");
        studentInfo.setStudentId(studentId);

        stuInfoService.updateUnCriticalInfo(studentInfo);
        return "redirect:nonCriticalInfoModify";
    }

    //关键信息修改申请
    @GetMapping("/criticalInfoModifyApply")
    public String KeyModify(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            return "redirect:/login";
        }
        KeyInfoChangeApply keyInfo = stuInfoService.FindKeyInfoByAccount(account);
        model.addAttribute("KeyInfo", keyInfo);
        if (stuInfoService.HasModifyApply(account)) {
            model.addAttribute("error", 1);
            return "stuInfo/CriticalInfoModify";
        }

        return "stuInfo/CriticalInfoModify";
    }


    @PostMapping("/criticalInfoModifyApply")
    public String KeyModifyApply(Model model, @RequestParam("name") String name, @RequestParam("sex") String sex,
                                 @RequestParam("nation") String nation, @RequestParam("majorNum") String majorNum,
                                 @RequestParam(
                                         "idCardNum") String idCardNum
            , @RequestParam("changeReason") String changeReason
            , HttpSession session) {
        Account account = (Account) session.getAttribute("account");

        KeyInfoChangeApply keyInfo = stuInfoService.FindKeyInfoByAccount(account);
        if (keyInfo == null) {
            System.out.println("Log:" + System.currentTimeMillis() + "用户信息错误！！！");
            return "index";
        }

        model.addAttribute("KeyInfo", keyInfo);
        if (changeReason.equals("")) {
            model.addAttribute("error", 0);
            return "stuInfo/CriticalInfoModify";
        }
        KeyInfoChangeApply keyInfoChangeApply = new KeyInfoChangeApply();
        keyInfoChangeApply.setName(name);
        if (!nation.equals("")) {
            keyInfoChangeApply.setNation(Integer.parseInt(nation));
        }
        if (!sex.equals("")) {
            keyInfoChangeApply.setSex(Integer.parseInt(sex));
        }
        keyInfoChangeApply.setMajorNum(majorNum);
        keyInfoChangeApply.setChangeReason(changeReason);

        String str = stuInfoService.KeyModify(keyInfoChangeApply, account);
        if (str == null) {
            model.addAttribute("error", 2);
            return "stuInfo/CriticalInfoModify";
        }

        InfoChangeApply infoChangeApply = stuInfoService.FindChangeApplyByAccount(account, str);
        model.addAttribute("infoChangeApply", infoChangeApply);
        model.addAttribute("error", 3);
        return "stuInfo/CriticalInfoModify";
    }


    //    准考证号修改
    @GetMapping("/ECNModifyApply")
    public String EcnModify(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("account", account);
        if (account == null) {
            return "redirect:/login";
        }

        if (stuInfoService.HasModifyApply(account)) {
            model.addAttribute("error", 1);
            return "stuInfo/ECNModifyApply";
        }

        return "stuInfo/ECNModifyApply";
    }

    @PostMapping("/ECNModifyApply")
    public String EcnModifyApply(Model model, @RequestParam("changeReason") String changeReason, HttpSession session) {
        Account account = (Account) session.getAttribute("account");

        model.addAttribute("account", account);
        if(stuInfoService.HasModifyApply(account)){
            model.addAttribute("error", 1);
            return "stuInfo/ECNModifyApply";
        }

        if (changeReason == null || changeReason.equals("")) {
            model.addAttribute("error", 2);
            return "stuInfo/ECNModifyApply";
        }

        stuInfoService.ECNModify(changeReason, account);
        InfoChangeApply infoChangeApply = stuInfoService.FindChangeApplyByAccount(account, "准考证号");
        model.addAttribute("infoChangeApply", infoChangeApply);
        model.addAttribute("error", 3);
        return "stuInfo/ECNModifyApply";

    }

    //设置考次管理
    @GetMapping("/examManager")
    public String CurrExamNo(HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }
        return "stuInfo/examManager";
    }

    @PostMapping("/examManager")
    public String setCurrExamNo(@RequestParam("examNum") String examNum, @RequestParam("startDate") String startDate,
                                @RequestParam("endDate") String endDate, Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (startDate.equals("") || endDate.equals("")) {
            model.addAttribute("error", 3);
            return "stuInfo/examManager";
        }
        if (!stuInfoService.HasExamInfo(examNum)) {
            model.addAttribute("error", 2);
            return "stuInfo/examManager";
        }
        ExamInfo examInfo = new ExamInfo();
        examInfo.setExamNum(examNum);
        examInfo.setStartDate(startDate);
        examInfo.setEndDate(endDate);
        stuInfoService.setCurrExamNum(examInfo, account);
        model.addAttribute("examInfo", examInfo);
        model.addAttribute("success", 1);
        return "stuInfo/examManager";
    }

    //    导入成绩
    @GetMapping("/importGrade")
    public String importGrade(Model model, HttpSession session) {
        Account account = (Account)session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        String studentId = (String) session.getAttribute("studentId");
        List<ImportGradeDto> list = stuInfoService.selectAllGrade(studentId);
        model.addAttribute("ImportGradeList", list);
        return "stuInfo/importGrade";
    }

    @PostMapping("/importGrade")
    public String ImportGrade(@RequestParam("file") MultipartFile file, Model model) {
        boolean isSuccess = false;
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            model.addAttribute("error", 0);
            return "stuInfo/importGrade";
        }
        try {
            isSuccess = stuInfoService.ImportGrade(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!isSuccess) {
            model.addAttribute("error", 1);
            return "stuInfo/importGrade";
        }

        model.addAttribute("error", 2);
        return "stuInfo/importGrade";
    }

    @GetMapping("/quitManage")
    public String getQuitManage(Model model){
        List<StuBasicInfo> stuBasicInfos = new ArrayList<>();
        model.addAttribute("stuBasicInfos",stuBasicInfos);
        List<ExamInfo> examInfos = examInfoService.getExamInfos();
        model.addAttribute("examInfos", examInfos);
        return "stuInfo/quitManage";
    }
    @PostMapping("/quitManage")
    public String postQuitManage(Model model, QuitManageSearch quitManageSearch){
        // TODO: 前端提交页数？
        if(quitManageSearch.getPageNum()==null){
            quitManageSearch.setPageNum(1);
        }

        List<ExamInfo> examInfos = examInfoService.getExamInfos();
        model.addAttribute("examInfos", examInfos);
        PageInfo<StuBasicInfo> stuBasicInfoPageInfos =
                signUpInfoService.getStuBasicInfoByQMS(quitManageSearch);
        List<StuBasicInfo> stuBasicInfoList = stuBasicInfoPageInfos.getList();
        model.addAttribute("stuBasicInfos", stuBasicInfoList);
        //参考：https://www.cnblogs.com/xifengxiaoma/p/11027551.html
        return "stuInfo/quitManage";
    }
    @PostMapping("/quitManage/quit")
    @ResponseBody
    public UniversalResponseBody<?> quitStus(@RequestBody ECNList ecnList){
        // 参考:https://stackoverflow.com/questions/47503568/how-to-pass-list-using-ajax-call-to-spring-mvc-controller
        assert ecnList!=null;
        signUpInfoService.quitStu(ecnList.getEcnList());
        return new UniversalResponseBody<>(0,"success");
    }
}
