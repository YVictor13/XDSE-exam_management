package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.model.helper.*;
import com.example.demo.mapper.GraduateApplyMapper;
import com.example.demo.model.Account;
import com.example.demo.model.GraduateCert;
import com.example.demo.model.TimeManage;
import com.example.demo.model.helper.GraduateApplyHelper;
import com.example.demo.model.helper.GraduateCancelHelper;
import com.example.demo.model.helper.GraduationStatisticHelper;
import com.example.demo.model.helper.ReadyGraduationHelper;
import com.example.demo.service.*;
import com.example.demo.util.DownloadUtil;
import com.example.demo.util.ToPinYinUtil;
import com.example.demo.util.WordUtil;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * @author:guan
 * @2020/9/4 9:07
 * 文件信息：
 */
@Controller
@RequestMapping("/graduation")
public class GraduationController {
    @Resource
    private GraduationService graduationService;
    @Resource
    private ProofService proofService;
    @Resource
    private StudentNeedService studentNeedService;

    @GetMapping("/statistic/beforeGraduationThesisStatistics")
    public String beforeGraduationStatistics(HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        return "graduation/statistic/beforeGraduationThesisStatistics";
    }

    @PostMapping("/statistic/beforeGraduationThesisStatistics")
    public String beforeGraduationQuery(Model model,String type,String code,HttpSession session){
        List<GraduationStatisticHelper> graduationStatisticHelpers = new ArrayList<>();
        int graCount = 0;
        if (type.equals("school")){
//            根据学校统计
            graduationStatisticHelpers = graduationService.queryAllGraduaterBySchool(code,"");

        }else if (type.equals("major")){
            graduationStatisticHelpers = graduationService.queryAllGraduaterByMajor(code,"");
        }
        graCount = graduationStatisticHelpers.size();
        model.addAttribute("graduation",graduationStatisticHelpers);
        model.addAttribute("graCount",graCount);
        session.setAttribute("graduation",graduationStatisticHelpers);
        return "graduation/statistic/beforeGraduationThesisStatistics";
    }


    /**
     * 本次毕业统计
     * @return
     */

    @GetMapping("/statistic/thisGraduationThesisStatistics")
    public String thisGraduationStatistics(HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        return "graduation/statistic/thisGraduationThesisStatistics";
    }

    @PostMapping("/statistic/thisGraduationThesisStatistics")
    public String thisGraduationQuery(Model model,String type,String code){
        String examNum = "202010";
        List<GraduationStatisticHelper> graduationStatisticHelpers = new ArrayList<>();
        int graCount = 0;
        if (type.equals("school")){
//            根据学校统计
            graduationStatisticHelpers = graduationService.queryAllGraduaterBySchool(code,examNum);

        }else if (type.equals("major")){
            graduationStatisticHelpers = graduationService.queryAllGraduaterByMajor(code,examNum);
        }
        graCount = graduationStatisticHelpers.size();
        model.addAttribute("graduation",graduationStatisticHelpers);
        model.addAttribute("graCount",graCount);
        return "graduation/statistic/thisGraduationThesisStatistics";
    }
    @PostMapping("/statistic/thisGraduationSearch")
    public String thisGraduationTypeSearch(Model model,String type){
        List<String> schoolOrCity = new ArrayList<>();
        if (type.equals("school")){
            schoolOrCity = graduationService.queryAllSchool();
        }else if (type.equals("major")){
            schoolOrCity = graduationService.queryAllMajor();
        }
        model.addAttribute("schoolOrCity",schoolOrCity);
        return "graduation/statistic/thisGraduationThesisStatistics";
    }

    /**
     * 毕业生查询
     * @return
     */
    @GetMapping("/statistic/graduationStatistics")
    public String graduationStatistics(HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        return "graduation/statistic/graduationStatistics";
    }

    @PostMapping("/statistic/graduationStatistics")
    public String graduationStatisticsQuery( Model model,String type,String  code,HttpSession session){
//        获取本考次
        String examNum = (String)session.getAttribute("examNum");
        List<GraduationStatisticHelper> graduationStatisticHelpers = new ArrayList<>();
        int graCount = 0;
        if (type.equals("area")){
            graduationStatisticHelpers = graduationService.queryAllGraduaterByArea(code);
            graCount = graduationStatisticHelpers.size();
        }else if (type.equals("major")){
             graduationStatisticHelpers = graduationService.queryAllGraduaterByMajor(code, "");
             graCount = graduationStatisticHelpers.size();
        }else if (type.equals("graduation_time")){
            graduationStatisticHelpers = graduationService.queryAllGraduateByDate(code+"%");
            graCount = graduationStatisticHelpers.size();
        }else if (type.equals("this_time")){
            graduationStatisticHelpers = graduationService.queryAllGraduaterByMajor("",examNum);
            graCount = graduationStatisticHelpers.size();

        }else if (type.equals("before")){
            graduationStatisticHelpers = graduationService.queryAllGraduateByDate("");
            graCount = graduationStatisticHelpers.size();
        }

        model.addAttribute("graduation",graduationStatisticHelpers);
        model.addAttribute("graCount",graCount);
        return "graduation/statistic/graduationStatistics";
    }

    @PostMapping("/statistic/GraduationSearch")
    public String GraduationTypeSearch(Model model,String type){
        List<String> majorOrCity = new ArrayList<>();
        if (type.equals("area")){
            majorOrCity = graduationService.queryAllCityCode();
        }else if (type.equals("major")){
            majorOrCity = graduationService.queryAllMajor();
        }else if (type.equals("graduation_time")){
            majorOrCity.add("2017");
            majorOrCity.add("2018");
            majorOrCity.add("2019");
            majorOrCity.add("2020");
        }else {
            majorOrCity.add("All");
        }
        model.addAttribute("majorOrCity",majorOrCity);
        return "graduation/statistic/graduationStatistics";
    }

    /**
     * 毕业申请审核
     * @param model
     * @return
     */
    @GetMapping("/proManagement/graduationApplyCheck")
    public String graduationApplyCheck(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
//        查询所有毕业申请
        List<GraduateApplyHelper> graduateApplyHelpers = graduationService.queryAllGraduateApplyByState(1);
        model.addAttribute("graduateApplyList",graduateApplyHelpers);
        return "graduation/proManagement/graduationApplyCheck";
    }
    @Resource
    private StuInfoInOutService stuInfoInOutService;
    @PostMapping("/proManagement/graduationApplyCheck")
    public String graduationApplyChecked(Integer ID,HttpSession session){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String endDate = formatter.format(date);

        graduationService.updateGraduateApplyStateById(ID,2,endDate);
//        生成毕业证？？
//        获取学生id
        GraduateCertHelper graduateCertHelper = graduationService.queryStuIdAndEcnByApplyId(ID);
//        生成毕业证号，导入毕业生
        GraduateCert graduateCert = new GraduateCert();
        String graduateId = graduateCertHelper.getStudentId()+graduateCertHelper.getExamCardNum();



        graduateCert.setGraduateId(graduateId);
        graduateCert.setExamCardNum(graduateCertHelper.getExamCardNum());
        graduateCert.setGraduateDate(endDate);
        graduationService.insertNewGraduateCert(graduateCert);
//更新学生信息
        stuInfoInOutService.updateStuInfoStateByApplyId(ID,1);


        return "graduation/proManagement/graduationApplyCheck";

    }
    @PostMapping("/proManagement/graduationApplyRefuse")
    public String graduationApplyRefuse(Integer ID){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String endDate = formatter.format(date);

        graduationService.updateGraduateApplyStateById(ID,0,endDate);
        return "graduation/proManagement/graduationApplyCheck";

    }

    @GetMapping("/proManagement/graduationCancel")
    public String graduationCancel(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
//        根据studentId查找该考生所有准考证号和毕业证号，这里模拟
        String studentId = (String)session.getAttribute("studentId");
        List<GraduateCert> graduateCerts = graduationService.queryAllGraduateCertByStudentId(studentId);
        model.addAttribute("graduateCertList",graduateCerts);
        return "graduation/proManagement/graduationCancel";
    }

    /**
     * 根据毕业证号和学生id查找需要撤销毕业生的信息
     * @param examCardNum
     * @return
     */
    @PostMapping("/proManagement/graduationCancelSearch")
    public String graduationCancelSearch(Model model,String examCardNum){
        GraduateCancelHelper graduateCancelHelper = graduationService.queryGraduateInfoByEcn(examCardNum);
        model.addAttribute("graduateInfo",graduateCancelHelper);
        return "graduation/proManagement/graduationCancel";
    }

    @PostMapping("/proManagement/graduationCancelSure")
    public String graduationCancelSure(Model model,String examCardNum){
//        毕业证表删除记录，考生信息里面的信息状态改为在籍考生状态
//        1.更新考生状态为在籍考生
        graduationService.updateStudentInfo(examCardNum);
//        2.将毕业证表中的该毕业生信息删除
        graduationService.deleteGraduateCertByEcm(examCardNum);
        return "graduation/proManagement/graduationCancel";
    }

    @GetMapping("/proManagement/graduationCertificatePrint")
    public String graduationCertificatePrint(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        return "graduation/proManagement/graduationCertificatePrint";
    }
    @GetMapping("/proManagement/graduationCertificateReceivingIdentify")
    public String graduationCertificateReceivingIdentify(HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        return "graduation/proManagement/graduationCertificateReceivingIdentify";
    }

    @PostMapping("/proManagement/graduationCertificatePrint")
    public String graduationCertificatePrintSearch( Model model,String type,String code,HttpSession session){
        List<GraduationStatisticHelper> graduationStatisticHelpers = new ArrayList<>();
        int graCount = 0;
        if (type.equals("school")){
            graduationStatisticHelpers = graduationService.queryAllGraduaterBySchool(code,"");
            graCount = graduationStatisticHelpers.size();
        }else if (type.equals("major")){
            graduationStatisticHelpers = graduationService.queryAllGraduaterByMajor(code, "");
            graCount = graduationStatisticHelpers.size();
        }
        model.addAttribute("graduation",graduationStatisticHelpers);

        model.addAttribute("graCount",graCount);
        session.setAttribute("graduation",graduationStatisticHelpers);
        return "graduation/proManagement/graduationCertificatePrint";
    }

    //打印毕业证
    @ResponseBody
    @RequestMapping("/proManagement/graduationCertificatePrint/getWord")
    public String returnGraduationCertificatePrintWord(HttpServletResponse response,Model model,HttpSession session) throws Exception {
        Account account = (Account) session.getAttribute("account");

        if(account==null){
            return "redirect:/login";
        }

        List<GraduationStatisticHelper> graduationStatisticHelperList = (List<GraduationStatisticHelper>) session.getAttribute("graduation");
        Map<String,Object> map = new HashMap<>();
        List<Map> ls = new ArrayList<>();

        for(GraduationStatisticHelper graduationStatisticHelper:graduationStatisticHelperList){
            Map m = new HashMap();
            m.put("ExamCardNum",graduationStatisticHelper.getExamCardNum());
            m.put("name",graduationStatisticHelper.getName());
            m.put("majorNum",graduationStatisticHelper.getMajorNum());
            m.put("graduationDate",graduationStatisticHelper.getGraduateDate());
            m.put("graduationID",graduationStatisticHelper.getGraduateId());
            ls.add(m);
        }

        map.put("graduationCardlist",ls);
        String ftlName = "graduationCardListTemplate.ftl";

        //将参数写入模板文件中
        ByteArrayOutputStream outputStream = WordUtil.process(map, ftlName);
        //返回doc文档，并下载。
        SimpleDateFormat orderSystemDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String systemDate = orderSystemDate.format(new Date())+".doc";
        DownloadUtil.download(outputStream, response, systemDate);
        return "graduation/proManagement/graduationCertificatePrint";

    }
//    预提管理



    @GetMapping("/proManagement/readyGraduation")
    public String readyGraduation(HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }

        return "graduation/proManagement/readyGraduation";
    }
    @PostMapping("/proManagement/readyGraduation")
    public String readyGraduationSearch(Model model){
        List<ReadyGraduationHelper> readyGraduationHelpers = graduationService.queryAllReadyGra();
        int count = readyGraduationHelpers.size();
        model.addAttribute("readyGraList",readyGraduationHelpers);
        model.addAttribute("count",count);
        return "graduation/proManagement/readyGraduation";
    }


//    时间管理
    @Resource
    private TimeManageService timeManageService;
    @GetMapping("/proManagement/graduationTransactionTimeSet")
    public String graduationTransactionTimeSet(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<TimeManage> timeManages = timeManageService.queryAllTimeSet();
        for (int i=5;i<=11;i++){
            model.addAttribute("timeManage"+i,timeManages.get(i));
        }
        return "graduation/proManagement/graduationTransactionTimeSet";
    }
    @PostMapping("/proManagement/graduationTransactionTimeSet")
    public String updateTime(Model model, HttpServletRequest req){
        ArrayList<TimeManage> timeManages = new ArrayList<>();
        for (int i=5;i<=11;i++){
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

        return "redirect:graduationTransactionTimeSet";
    }



    /**
     * 1、中文英文成绩证明
     * @return
     */

    @GetMapping("/proof/chineseEnglishGradesProof")
    public String ZhEnGradesProof(HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }

        return "graduation/proof/chineseEnglishGradesProof";
    }
    @PostMapping("/proof/chineseEnglishGradesProof")
    public String QueryGradeProof(Model model,@RequestParam("ExamCardNum") String ExamCardNum,HttpSession session) throws ParseException {
        if(ExamCardNum.equals("")){
            model.addAttribute("status",3); //考生不存在，请核实后再查询
            return "graduation/proof/chineseEnglishGradesProof";
        }
        session.setAttribute("ExamCardNum",ExamCardNum);
        SignUpInfo signUpInfo = proofService.FindStuId(ExamCardNum);

        if(signUpInfo==null){
            model.addAttribute("status",1); //考生不存在，请核实后再查询
            return "graduation/proof/chineseEnglishGradesProof";
        }

        GraduateCert graduateCert =proofService.FindGraduateCert(ExamCardNum);
        StudentInfo studentInfo = proofService.FindStuInfo(signUpInfo.getStudentId());
        if(graduateCert==null){
            model.addAttribute("status",2); //考生存在，但是未毕业
            return "graduation/proof/chineseEnglishGradesProof";
        }

        ZhEnGradesProof zhEnGradeProof = proofService.getZhEnGradeProof(ExamCardNum);
        model.addAttribute("zhEnGradesProof",zhEnGradeProof);
        List<CourseHelper> list = new ArrayList<>();
        for(int i = 0 ; i<zhEnGradeProof.getGradeList().size();i++){
            CourseHelper courseHelper = new CourseHelper();
            courseHelper.setCourseCode(zhEnGradeProof.getCourseList().get(i).getCourseCode());
            courseHelper.setCourseName(zhEnGradeProof.getCourseList().get(i).getCourseCode());
            courseHelper.setGrades(zhEnGradeProof.getGradeList().get(i).getGrade());
            courseHelper.setGradeType(String.valueOf(zhEnGradeProof.getGradeList().get(i).getGradeType()));
            list.add(courseHelper);
        }
        model.addAttribute("list",list);
        model.addAttribute("studentInfo",studentInfo);
        model.addAttribute("status",4);
        return "graduation/proof/chineseEnglishGradesProof";
    }

    //打印中文成绩证明单
    @ResponseBody
    @RequestMapping("/proof/ZhGrade/getWord")
    public String returnZhGradesWord(HttpServletResponse response,Model model,HttpSession session) throws Exception {
        Account account = (Account) session.getAttribute("account");

        if(account==null){
            return "redirect:/login";
        }

        String ExamCardNum = (String) session.getAttribute("ExamCardNum");

        ZhEnGradesProof zhEnGradeProof = proofService.getZhEnGradeProof(ExamCardNum);

        Map<String,Object> map = new HashMap<>();
        map.put("gradeNum",zhEnGradeProof.getStudentId()+ExamCardNum);
        map.put("name",zhEnGradeProof.getName());
        map.put("studentId",zhEnGradeProof.getStudentId());
        map.put("Sex",zhEnGradeProof.getSex());
        map.put("birthday",zhEnGradeProof.getBirthday());
        map.put("signUpDate",zhEnGradeProof.getSignUpDate());
        map.put("majorType",zhEnGradeProof.getMajorType());
        map.put("majorName",zhEnGradeProof.getMajorName());
        map.put("majorDir",zhEnGradeProof.getMajorDir());
        map.put("SchoolName",zhEnGradeProof.getSchoolName());
        map.put("IDCard",zhEnGradeProof.getIDCard());
        List<Map> ls = new ArrayList<>();

        for(int i=0;i<zhEnGradeProof.getGradeList().size();i++){
            Map m = new HashMap();
            m.put("courseCode",zhEnGradeProof.getCourseList().get(i).getCourseCode());
            m.put("courseName",zhEnGradeProof.getCourseList().get(i).getCourseName());
            m.put("gradeType",zhEnGradeProof.getGradeList().get(i).getGradeType());
            m.put("grades",zhEnGradeProof.getGradeList().get(i).getGrade());
            ls.add(m);
        }

        map.put("gradesList",ls);
        map.put("Date","2020-09-07");
        map.put("gDate","2020-09-08");

        String ftlName = "ZhGradeTemplate.ftl";
        //将参数写入模板文件中
        ByteArrayOutputStream outputStream = WordUtil.process(map, ftlName);
        //返回doc文档，并下载。
        SimpleDateFormat orderSystemDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String systemDate = orderSystemDate.format(new Date())+".doc";
        DownloadUtil.download(outputStream, response, systemDate);
        model.addAttribute("statue",1);
        return "graduation/proof/chineseEnglishGradesProof";
    }

    //打印中文成绩证明单
    @ResponseBody
    @RequestMapping("/proof/EnGrade/getWord")
    public String returnEnGradesWord(HttpServletResponse response,Model model,HttpSession session) throws Exception {
        Account account = (Account) session.getAttribute("account");

        if(account==null){
            return "redirect:/login";
        }

        String ExamCardNum = (String) session.getAttribute("ExamCardNum");

        ZhEnGradesProof zhEnGradeProof = proofService.getZhEnGradeProof(ExamCardNum);

        Map<String,Object> map = new HashMap<>();
        map.put("gradeNum",zhEnGradeProof.getStudentId()+ExamCardNum);
        map.put("name",ToPinYinUtil.toPinyin(zhEnGradeProof.getName()));
        map.put("studentId",zhEnGradeProof.getStudentId());
        map.put("Sex",ToPinYinUtil.toPinyin(zhEnGradeProof.getSex()));
        map.put("birthday",zhEnGradeProof.getBirthday());
        map.put("signUpDate",zhEnGradeProof.getSignUpDate());
        map.put("majorType",ToPinYinUtil.toPinyin(zhEnGradeProof.getMajorType()));
        map.put("majorName",ToPinYinUtil.toPinyin(zhEnGradeProof.getMajorName()));
        map.put("majorDir",ToPinYinUtil.toPinyin(zhEnGradeProof.getMajorDir()));
        map.put("SchoolName",ToPinYinUtil.toPinyin(zhEnGradeProof.getSchoolName()));
        map.put("IDCard",zhEnGradeProof.getIDCard());
        List<Map> ls = new ArrayList<>();

        for(int i=0;i<zhEnGradeProof.getGradeList().size();i++){
            Map m = new HashMap();
            m.put("courseCode",zhEnGradeProof.getCourseList().get(i).getCourseCode());
            m.put("courseName",ToPinYinUtil.toPinyin(zhEnGradeProof.getCourseList().get(i).getCourseName()));
            m.put("gradeType",zhEnGradeProof.getGradeList().get(i).getGradeType());
            m.put("grades",zhEnGradeProof.getGradeList().get(i).getGrade());
            ls.add(m);

        }
        map.put("gradesList",ls);
        map.put("Date",zhEnGradeProof.getDate());
        map.put("gDate",zhEnGradeProof.getgDate());

        String ftlName = "EnGradeTemplate.ftl";
        //将参数写入模板文件中
        ByteArrayOutputStream outputStream = WordUtil.process(map, ftlName);
        //返回doc文档，并下载。
        SimpleDateFormat orderSystemDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String systemDate = orderSystemDate.format(new Date())+".doc";
        DownloadUtil.download(outputStream, response, systemDate);
        model.addAttribute("statue",1);
        return "graduation/proof/chineseEnglishGradesProof";
    }

    /**
     * 2、成绩合格证明
     * @return
     */

    @GetMapping("/proof/gradesQualified")
    public String gradeQualified(HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        return "graduation/proof/gradesQualified";
    }


    @PostMapping("/proof/gradesQualified")
    public String QueryGradesQualified(Model model,@RequestParam("ExamCardNum") String ExamCardNum,HttpSession session) throws ParseException {
        if(ExamCardNum.equals("")){
            model.addAttribute("status",3); //考生不存在，请核实后再查询
            return "graduation/proof/gradesQualified";
        }
        session.setAttribute("ExamCardNum",ExamCardNum);
        SignUpInfo signUpInfo = proofService.FindStuId(ExamCardNum);
        if(signUpInfo==null){
            model.addAttribute("status",1); //考生不存在，请核实后再查询
            return "graduation/proof/gradesQualified";
        }

        GraduateCert graduateCert =proofService.FindGraduateCert(ExamCardNum);
        if(graduateCert==null){
            model.addAttribute("status",2); //考生存在，但是未毕业
            return "graduation/proof/gradesQualified";
        }

        GradeProof zhEnGradesProof = proofService.getGradeProof(ExamCardNum);
        model.addAttribute("zhEnGradesProof",zhEnGradesProof);
        model.addAttribute("status",4);
        return "graduation/proof/gradesQualified";
    }

    //打印成绩证明单
    @ResponseBody
    @RequestMapping("/proof/SingleGrade/getWord")
    public String returnGradeWord(HttpServletResponse response,Model model,HttpSession session) throws Exception {
        Account account = (Account) session.getAttribute("account");

        if(account==null){
            return "redirect:/login";
        }
        String ExamCardNum = (String) session.getAttribute("ExamCardNum");
        GradeProof gradeProof = proofService.getGradeProof(ExamCardNum);
        Map<String,Object> map = new HashMap<>();
        map.put("studentName", gradeProof.getName());
        map.put("ExamCardNum", gradeProof.getExamCardNum());
        map.put("IdCard", gradeProof.getStudentId());
        map.put("City", gradeProof.getCity());
        map.put("Area", gradeProof.getRegion());
        map.put("GDate", gradeProof.getgDate());
        map.put("School", gradeProof.getSchool());
        map.put("MajorName", gradeProof.getMajor());
        map.put("GradeType", gradeProof.getGradeType());
        map.put("SchoolName", gradeProof.getSchool());
        map.put("gradeNum", gradeProof.getGradeId());
        map.put("Date", gradeProof.getDate());
        map.put("sex", gradeProof.getSex());

        String ftlName = "GradeProofTemplate.ftl";
        //将参数写入模板文件中
        ByteArrayOutputStream outputStream = WordUtil.process(map, ftlName);
        //返回doc文档，并下载。
        SimpleDateFormat orderSystemDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String systemDate = orderSystemDate.format(new Date())+".doc";
        DownloadUtil.download(outputStream, response, systemDate);
        model.addAttribute("statue",1);
        return "graduation/proof/gradesQualified";
    }


    /**
     * 3，毕业证明打印
     * @return
     */
    @GetMapping("/proof/graduationProof")
    public String graduationProof(HttpSession session){
        Account account =(Account)session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        return "graduation/proof/graduationProof";
    }

    @PostMapping("/proof/graduationProof")
    public String QueryGraduationProof(Model model,@RequestParam("ExamCardNum") String ExamCardNum,HttpSession session) throws ParseException {
        if(ExamCardNum.equals("")){
            model.addAttribute("status",3); //查询为空
            return "graduation/proof/graduationProof";
        }
        session.setAttribute("ExamCardNum",ExamCardNum);
        SignUpInfo signUpInfo = proofService.FindStuId(ExamCardNum);
        if(signUpInfo==null){
            model.addAttribute("status",1); //考生不存在，请核实后再查询
            return "graduation/proof/graduationProof";
        }

        GraduateCert graduateCert =proofService.FindGraduateCert(ExamCardNum);
        if(graduateCert==null){
            model.addAttribute("status",2); //考生存在，但是未毕业
            return "graduation/proof/graduationProof";
        }

        ZhEnGradesProof zhEnGradeProof = proofService.getZhEnGradeProof(ExamCardNum);
        GradeProof gradeProof = proofService.getGradeProof(ExamCardNum);

        model.addAttribute("zhEnGradesProof",zhEnGradeProof);
        model.addAttribute("gradeProof",gradeProof);
        model.addAttribute("graduateId",graduateCert.getGraduateId());
        model.addAttribute("status",4);
        return "graduation/proof/graduationProof";
    }

    //打印毕业证明单
    @ResponseBody
    @RequestMapping("/proof/graduation/getWord")
    public String returnGraduationWord(HttpServletResponse response,Model model,HttpSession session) throws Exception {
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }

        String ExamCardNum = (String) session.getAttribute("ExamCardNum");
        GradeProof gradeProof = proofService.getGradeProof(ExamCardNum);
        ZhEnGradesProof zhEnGradeProof = proofService.getZhEnGradeProof(ExamCardNum);
        GraduateCert graduateCert =proofService.FindGraduateCert(ExamCardNum);

        Map<String,Object> map = new HashMap<>();
        map.put("graduationId", graduateCert.getGraduateId());
        map.put("name", gradeProof.getName());
        map.put("IDCard", gradeProof.getStudentId());
        map.put("birthday",zhEnGradeProof.getBirthday());
        map.put("city", gradeProof.getCity());
        map.put("area", gradeProof.getRegion());
        map.put("gDate", gradeProof.getgDate());
        map.put("school", gradeProof.getSchool());
        map.put("majorName", gradeProof.getMajor());
        map.put("date", gradeProof.getDate());
        map.put("sex", gradeProof.getSex());

        String ftlName = "graduationProofTepmlate.ftl";
        //将参数写入模板文件中
        ByteArrayOutputStream outputStream = WordUtil.process(map, ftlName);
        //返回doc文档，并下载。
        SimpleDateFormat orderSystemDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String systemDate = orderSystemDate.format(new Date())+".doc";
        DownloadUtil.download(outputStream, response, systemDate);
        model.addAttribute("statue",1);
        return "graduation/proof/graduationProof";
    }



    /**
     * 4，研究生报考证明
     * @return
     */
    @GetMapping("/proof/postgraduateApplicationProof")
    public String postgraduateApplicationProof(HttpSession session){
        Account account =(Account)session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        return "graduation/proof/postgraduateApplicationProof";
    }

    @PostMapping("/proof/postgraduateApplicationProof")
    public String QueryPostgraduateApplicationProof(Model model,@RequestParam("ExamCardNum") String ExamCardNum,HttpSession session) throws ParseException {
        if(ExamCardNum.equals("")){
            model.addAttribute("status",3); //查询为空
            return "graduation/proof/postgraduateApplicationProof";
        }
        session.setAttribute("ExamCardNum",ExamCardNum);
        SignUpInfo signUpInfo = proofService.FindStuId(ExamCardNum);
        if(signUpInfo==null){
            model.addAttribute("status",1); //考生不存在，请核实后再查询
            return "graduation/proof/postgraduateApplicationProof";
        }
        StudentInfo studentInfo = proofService.FindStuInfo(signUpInfo.getStudentId());

        if(studentInfo.getDegree()==null||studentInfo.getDegree()>2){
            model.addAttribute("status",5); //考生未达到本科水平，不能报考
            return "graduation/proof/postgraduateApplicationProof";
        }
        GraduateCert graduateCert =proofService.FindGraduateCert(ExamCardNum);
        if(graduateCert==null){
            model.addAttribute("status",2); //考生存在，但是未毕业
            return "graduation/proof/postgraduateApplicationProof";
        }

        ZhEnGradesProof zhEnGradeProof = proofService.getZhEnGradeProof(ExamCardNum);

        GradeProof gradeProof = proofService.getGradeProof(ExamCardNum);

        model.addAttribute("zhEnGradesProof",zhEnGradeProof);
        model.addAttribute("gradeProof",gradeProof);
        model.addAttribute("graduateId",graduateCert.getGraduateId());
        model.addAttribute("status",4);
        return "graduation/proof/postgraduateApplicationProof";
    }

    //打印毕业证明单
    @ResponseBody
    @RequestMapping("/proof/postgraduateApplication/getWord")
    public String returnPostgraduateApplicationWord(HttpServletResponse response,Model model,HttpSession session) throws Exception {
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }

        String ExamCardNum = (String) session.getAttribute("ExamCardNum");
        GradeProof gradeProof = proofService.getGradeProof(ExamCardNum);
        ZhEnGradesProof zhEnGradeProof = proofService.getZhEnGradeProof(ExamCardNum);
        GraduateCert graduateCert =proofService.FindGraduateCert(ExamCardNum);

        Map<String,Object> map = new HashMap<>();
        map.put("graduationId", graduateCert.getGraduateId());
        map.put("name", gradeProof.getName());
        map.put("IDCard", gradeProof.getStudentId());
        map.put("birthday",zhEnGradeProof.getBirthday());
        map.put("city", gradeProof.getCity());
        map.put("area", gradeProof.getRegion());
        map.put("gDate", gradeProof.getgDate());
        map.put("school", gradeProof.getSchool());
        map.put("date", gradeProof.getDate());
        map.put("sex", gradeProof.getSex());

        String ftlName = "postgraduateApplicationTemplate.ftl";
        //将参数写入模板文件中
        ByteArrayOutputStream outputStream = WordUtil.process(map, ftlName);
        //返回doc文档，并下载。
        SimpleDateFormat orderSystemDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String systemDate = orderSystemDate.format(new Date())+".doc";
        DownloadUtil.download(outputStream, response, systemDate);
        model.addAttribute("statue",1);
        return "graduation/proof/graduationProof";
    }


    /**
     * 5，考籍表打印
     * @return
     */
    @GetMapping("/proof/stuInfoPrint")
    public String stuInfoPrint(HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        return "graduation/proof/stuInfoPrint";
    }

    @PostMapping("/proof/stuInfoPrint")
    public String QueryStuInfoPrint(Model model,@RequestParam("ExamCardNum") String ExamCardNum,HttpSession session) throws ParseException {
        if(ExamCardNum.equals("")){
            model.addAttribute("status",3); //查询为空
            return "graduation/proof/stuInfoPrint";
        }
        session.setAttribute("ExamCardNum",ExamCardNum);
        SignUpInfo signUpInfo = proofService.FindStuId(ExamCardNum);
        model.addAttribute("signUpInfo",signUpInfo);
        if(signUpInfo==null){
            model.addAttribute("status",1); //考生不存在，请核实后再查询
            return "graduation/proof/stuInfoPrint";
        }

        StudentInfo studentInfo = proofService.FindStuInfo(signUpInfo.getStudentId());

        GraduateCert graduateCert =proofService.FindGraduateCert(ExamCardNum);
        if(graduateCert==null){
            model.addAttribute("status",2); //考生存在，但是未毕业
            return "graduation/proof/stuInfoPrint";
        }

        ZhEnGradesProof zhEnGradeProof = proofService.getZhEnGradeProof(ExamCardNum);

        model.addAttribute("zhEnGradesProof",zhEnGradeProof);
        model.addAttribute("graduateCert",graduateCert);
        model.addAttribute("studentInfo",studentInfo);
        model.addAttribute("signUpInfo",signUpInfo);
        model.addAttribute("status",4);

        List<CourseHelper> list = new ArrayList<>();
        for(int i = 0 ; i < zhEnGradeProof.getGradeList().size();i++){
            CourseHelper courseHelper =new CourseHelper();
            courseHelper.setCourseName(zhEnGradeProof.getCourseList().get(i).getCourseName());
            courseHelper.setCourseCode(zhEnGradeProof.getCourseList().get(i).getCourseCode());
            courseHelper.setGrades(zhEnGradeProof.getGradeList().get(i).getGrade());
            courseHelper.setGradeType(String.valueOf(zhEnGradeProof.getGradeList().get(i).getGradeType()));
            courseHelper.setEnCourseName(ToPinYinUtil.toPinyin(zhEnGradeProof.getCourseList().get(i).getCourseName()));
            courseHelper.setIndex(i+1);
            list.add(courseHelper);
        }
        model.addAttribute("list",list);
        return "graduation/proof/stuInfoPrint";
    }

    //打印毕业证明单
    @ResponseBody
    @RequestMapping("/proof/stuInfoPrint/getWord")
    public String returnStuInfoPrintWord(HttpServletResponse response,Model model,HttpSession session) throws Exception {
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }

        String ExamCardNum = (String) session.getAttribute("ExamCardNum");
        ZhEnGradesProof zhEnGradeProof = proofService.getZhEnGradeProof(ExamCardNum);
        GraduateCert graduateCert =proofService.FindGraduateCert(ExamCardNum);
        SignUpInfo signUpInfo = proofService.FindStuId(ExamCardNum);
        StudentInfo studentInfo = proofService.FindStuInfo(signUpInfo.getStudentId());
        Map<String,Object> map = new HashMap<>();
        map.put("name", studentInfo.getName());
        map.put("EnName", studentInfo.getEnglishName());
        map.put("sex", studentInfo.getSex());
        map.put("birthday",studentInfo.getBirthday());
        map.put("nation", studentInfo.getNation());
        map.put("political", studentInfo.getPolitical());
        map.put("imgPath", studentInfo.getPhotoPath());
        map.put("job", studentInfo.getJob());
        map.put("censusPlace", studentInfo.getCensusPlace());
        map.put("postCode", studentInfo.getPostCode());
        map.put("ExamCardNum", signUpInfo.getExamCardNum());
        map.put("studentId",signUpInfo.getStudentId());
        map.put("IdCard", studentInfo.getIdCardNum());
        map.put("health", studentInfo.getHealth());
        map.put("degree", studentInfo.getDegree());
        map.put("studentType", studentInfo.getStudentType());
        map.put("address", studentInfo.getAddress());
        map.put("majorNum", signUpInfo.getMajorNum());
        map.put("majorName", zhEnGradeProof.getMajorName());
        map.put("examNum", signUpInfo.getExamNum());
        map.put("signUpDate", signUpInfo.getSignUpDate());
        map.put("Telephone", studentInfo.getPhoneNumber());
        map.put("email", studentInfo.getEmail());
        map.put("school", signUpInfo.getSignUpSchool());
        map.put("signUpType",signUpInfo.getSignUpType());
        map.put("graduationDate",graduateCert.getGraduateDate());
        map.put("graduationId",graduateCert.getGraduateId());
        map.put("date", zhEnGradeProof.getDate());
        map.put("index",0);
        List<Map> ls = new ArrayList<>();
        for(int i=0;i<zhEnGradeProof.getGradeList().size();i++){
            Map m = new HashMap();
            m.put("index",i+1);
            m.put("courseCode",zhEnGradeProof.getCourseList().get(i).getCourseCode());
            m.put("courseName",zhEnGradeProof.getCourseList().get(i).getCourseName());
            m.put("courseEnName",zhEnGradeProof.getGradeList().get(i).getGradeType());
            m.put("gradeType",zhEnGradeProof.getGradeList().get(i).getGradeType());
            m.put("grades",zhEnGradeProof.getGradeList().get(i).getGrade());
            ls.add(m);
        }

        map.put("List",ls);
        String ftlName = "stuInfoPrint.ftl";
        //将参数写入模板文件中
        ByteArrayOutputStream outputStream = WordUtil.process(map, ftlName);
        //返回doc文档，并下载。
        SimpleDateFormat orderSystemDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String systemDate = orderSystemDate.format(new Date())+".doc";
        DownloadUtil.download(outputStream, response, systemDate);
        model.addAttribute("statue",1);
        return "graduation/proof/stuInfoPrint";
    }

/**
 * 6，毕业申请
 * @return
 */
    @GetMapping("/studentNeed/graduationApplication")
    public String graduationApplication(HttpSession session){
        Account account = (Account)session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        return "graduation/studentNeed/graduationApplication";
    }

    @PostMapping("/studentNeed/graduationApplication")
    public String GraduationApplication(Model model,HttpSession session,@RequestParam("majorNum")String majorNum){
        Account account = (Account)session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        SignUpInfo signUpInfo = proofService.FindStuId(account.getAccount());
        if(!signUpInfo.getMajorNum().equals(majorNum)){
            model.addAttribute("status",0); //申请专业与本人所修专业不符合，请核实再申请
            return "graduation/studentNeed/graduationApplication";
        }

        boolean isGraduation = studentNeedService.isGraduation(account.getAccount());
        if(!isGraduation){
            model.addAttribute("status",1); //申请考生还有课程没有通过考核
            return "graduation/studentNeed/graduationApplication";
        }
        boolean isHasGraduationApply = studentNeedService.isHasGraduationApply(account.getAccount());
        if(isHasGraduationApply){
            model.addAttribute("status",2); //已经申请毕业，请等候审核
            return "graduation/studentNeed/graduationApplication";
        }
        studentNeedService.AddGraduationApply(account.getAccount());
        model.addAttribute("status",3); //申请成功，等待管理员审核
        return "graduation/studentNeed/graduationApplication";

    }


    /**
     * 7，毕业资格查询
     * @return
     */
    @GetMapping("/studentNeed/graduationQualificationInquiry")
    public String graduationQualificationInquiry(HttpSession session){
        Account account = (Account)session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        return "graduation/studentNeed/graduationQualificationInquiry";
    }


        @PostMapping("/studentNeed/graduationQualificationInquiry")
        public String GraduationQualificationInquiry(Model model,HttpSession session,@RequestParam("majorNum")String majorNum,@RequestParam("examCardNum") String examCardNum){
            Account account = (Account)session.getAttribute("account");
            if(account==null){
                return "redirect:/login";
            }
            SignUpInfo signUpInfo = proofService.FindStuId(account.getAccount());
            if(signUpInfo==null){
                model.addAttribute("status",-1);
                return "graduation/studentNeed/graduationQualificationInquiry";
            }
            if(!signUpInfo.getMajorNum().equals(majorNum)){
                model.addAttribute("status",0);
                return "graduation/studentNeed/graduationQualificationInquiry";
            }
            boolean isGraduation = studentNeedService.isGraduation(account.getAccount());
            if(!isGraduation){
                model.addAttribute("status",1);
                return "graduation/studentNeed/graduationQualificationInquiry";
            }
            boolean isHasGraduationApply = studentNeedService.isHasGraduationApply(account.getAccount());
            if(isHasGraduationApply){
                model.addAttribute("status",2);
                return "graduation/studentNeed/graduationQualificationInquiry";
            }
            model.addAttribute("status",3);
            return "graduation/studentNeed/graduationQualificationInquiry";
        }

    /**
     * 8，毕业证进度信息查询
     * @return
     */

    @GetMapping("/studentNeed/graduationCertificateProgressInquiry")
    public String graduationCertificateProgressInquiry(HttpSession session){
        Account account = (Account)session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        return "graduation/studentNeed/graduationCertificateProgressInquiry";
    }

    @PostMapping("/studentNeed/graduationCertificateProgressInquiry")
    public String GraduationCertificateProgressInquiry(Model model,HttpSession session,@RequestParam("examCardNum") String examCardNum){
        Account account = (Account)session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        if(examCardNum.equals("")){
            model.addAttribute("status",0);
            return "graduation/studentNeed/graduationCertificateProgressInquiry";
        }
        SignUpInfo signUpInfo = proofService.FindStuId(examCardNum);
        if(signUpInfo==null){
            model.addAttribute("status",-1);
            return "graduation/studentNeed/graduationCertificateProgressInquiry";
        }
        int status =studentNeedService.FindResult(examCardNum);
        model.addAttribute("status",status);
        return "graduation/studentNeed/graduationCertificateProgressInquiry";
    }

    /**
     * 9，毕业证学籍档案领取地点查询
     * @return
     */
    @GetMapping("/studentNeed/graduationCertificateReceivingInquiry")
    public String graduationCertificateReceivingInquiry(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        SignUpInfo signUpInfo = proofService.FindStuId(account.getAccount());
        model.addAttribute("signUpInfo",signUpInfo);

        StudentInfo studentInfo = proofService.FindStuInfo(signUpInfo.getStudentId());

        GraduateCert graduateCert =proofService.FindGraduateCert(account.getAccount());
        if(graduateCert==null){
            model.addAttribute("status",1); //考生存在，但是未毕业
            return "graduation/studentNeed/graduationCertificateReceivingInquiry";
        }

        ZhEnGradesProof zhEnGradeProof = proofService.getZhEnGradeProof(account.getAccount());

        model.addAttribute("zhEnGradesProof",zhEnGradeProof);
        model.addAttribute("graduateCert",graduateCert);
        model.addAttribute("studentInfo",studentInfo);
        model.addAttribute("signUpInfo",signUpInfo);

        List<CourseHelper> list = new ArrayList<>();
        for(int i = 0 ; i < zhEnGradeProof.getGradeList().size();i++){
            CourseHelper courseHelper =new CourseHelper();
            courseHelper.setCourseName(zhEnGradeProof.getCourseList().get(i).getCourseName());
            courseHelper.setCourseCode(zhEnGradeProof.getCourseList().get(i).getCourseCode());
            courseHelper.setGrades(zhEnGradeProof.getGradeList().get(i).getGrade());
            courseHelper.setGradeType(String.valueOf(zhEnGradeProof.getGradeList().get(i).getGradeType()));
            courseHelper.setEnCourseName(ToPinYinUtil.toPinyin(zhEnGradeProof.getCourseList().get(i).getCourseName()));
            courseHelper.setIndex(i+1);
            list.add(courseHelper);
        }
        model.addAttribute("list",list);

        return "graduation/studentNeed/graduationCertificateReceivingInquiry";
    }

    /**
     * 10，前置学历审核申请
     * @return
     */
    @GetMapping("/studentNeed/preQualificationReviewApplication")
    public String preQualificationReviewApplication(HttpSession session,Model model){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<Major> majorList = studentNeedService.selectAllMajor();
        model.addAttribute("majorList",majorList);
        return "graduation/studentNeed/preQualificationReviewApplication";
    }

    @PostMapping("/studentNeed/preQualificationReviewApplication")
    public String PreQualificationReviewApplication(HttpSession session,Model model,@RequestParam("MajorName") String MajorName,@RequestParam("ExamCardNum") String ExamCardNum){
            Account account = (Account) session.getAttribute("account");
            if(account==null){
                return "redirect:/login";
            }
            if(ExamCardNum.equals("")){
                model.addAttribute("status",-1);
                return "graduation/studentNeed/preQualificationReviewApplication";
            }
            SignUpInfo signUpInfo = proofService.FindStuId(ExamCardNum);
            if(signUpInfo==null){
                model.addAttribute("status",0);
                return "graduation/studentNeed/preQualificationReviewApplication";
            }
            Major major = proofService.FindMajor(signUpInfo.getMajorNum());
            if(!major.getMajorName().equals(MajorName)){
                model.addAttribute("status",1);
                return "graduation/studentNeed/preQualificationReviewApplication";
            }
            if(!major.getMajorType().equals("61")&&!major.getMajorType().equals("62")){
                model.addAttribute("status",2);
                return "graduation/studentNeed/preQualificationReviewApplication";
            }

            boolean isGraduation = studentNeedService.isGraduation(ExamCardNum);
            if(!isGraduation){
                model.addAttribute("status",3);
                return "graduation/studentNeed/preQualificationReviewApplication";
            }
            boolean isHasGraduationPreApply = studentNeedService.isHasGraduationPreApply(ExamCardNum);
            if(isHasGraduationPreApply){
                model.addAttribute("status",4);
                return "graduation/studentNeed/preQualificationReviewApplication";
            }
            boolean isHasGraduationPreQualification = studentNeedService.isHasGraduationPreQualification(ExamCardNum);
            if(isHasGraduationPreQualification){
                model.addAttribute("status",5);
                return "graduation/studentNeed/preQualificationReviewApplication";
            }
            studentNeedService.AddGraduationPreApply(ExamCardNum);
            model.addAttribute("status",6);
            return "graduation/studentNeed/preQualificationReviewApplication";
        }

    /**
     * 11，前置学历审核
     * @return
     */
    @GetMapping("/studentNeed/preQualificationReview")
    public String preQualificationReview(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<GraduatePreApply> graduatePreApplyList = studentNeedService.selectAllGraduatePreApply();
        List<GraduatePre> graduatePreList = new ArrayList<>();
        for(GraduatePreApply graduatePreApply:graduatePreApplyList){
            SignUpInfo signUpInfo = proofService.FindStuId(graduatePreApply.getExamCardNum());
            StudentInfo studentInfo = proofService.FindStuInfo(signUpInfo.getStudentId());
            GraduatePre graduatePre = new GraduatePre();
            graduatePre.setExamCardId(signUpInfo.getExamCardNum());
            graduatePre.setIndex(String.valueOf(graduatePreApply.getId()));
            graduatePre.setIDCard(studentInfo.getIdCardNum());
            graduatePre.setImgPath(studentInfo.getPhotoPath());
            graduatePre.setMajorNum(signUpInfo.getMajorNum());
            graduatePre.setName(studentInfo.getName());
            graduatePre.setNation(String.valueOf(studentInfo.getNation()));
            graduatePre.setSex( String.valueOf(studentInfo.getSex()));
            graduatePreList.add(graduatePre);
        }

        model.addAttribute("graduatePreList",graduatePreList);
        return "graduation/studentNeed/preQualificationReview";
    }

    @PostMapping("/studentNeed/preQualificationReview")
    public String PreQualificationReview(Model model,HttpSession session,@RequestParam("status") String status,@RequestParam("examCardId") String examCardId){
            Account account = (Account) session.getAttribute("account");
            if(account==null){
                return "redirect:/login";
            }
             if(status.equals("1")){
                 //确定审核
                 studentNeedService.UpdatePreApplyState(examCardId);
             }
             if(status.equals("0")){
                //驳回申请
                 studentNeedService.RollBackApply(examCardId);
             }

            List<GraduatePreApply> graduatePreApplyList = studentNeedService.selectAllGraduatePreApply();
            List<GraduatePre> graduatePreList = new ArrayList<>();
            for(GraduatePreApply graduatePreApply:graduatePreApplyList){
                SignUpInfo signUpInfo = proofService.FindStuId(graduatePreApply.getExamCardNum());
                StudentInfo studentInfo = proofService.FindStuInfo(signUpInfo.getStudentId());
                GraduatePre graduatePre = new GraduatePre();
                graduatePre.setIndex(String.valueOf(graduatePreApply.getId()));
                graduatePre.setIDCard(studentInfo.getIdCardNum());
                graduatePre.setImgPath(studentInfo.getPhotoPath());
                graduatePre.setMajorNum(signUpInfo.getMajorNum());
                graduatePre.setName(studentInfo.getName());
                graduatePre.setNation(String.valueOf(studentInfo.getNation()));
                graduatePre.setSex( String.valueOf(studentInfo.getSex()));
                graduatePreList.add(graduatePre);
            }
            model.addAttribute("graduatePreList",graduatePreList);
            return "graduation/studentNeed/preQualificationReview";
        }

}
