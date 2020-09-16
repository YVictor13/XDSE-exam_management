package com.example.demo.controller;

import com.example.demo.dto.CourseNameAndGrade;
import com.example.demo.model.*;
import com.example.demo.model.helper.GradeForOneClass;
import com.example.demo.model.helper.ThreeClassApplyHelper;
import com.example.demo.model.helper.TwoClassApplyHelper;
import com.example.demo.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.tomcat.jni.Time;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping("/freeExam")
public class FreeExamController {
    @Resource
    private SignUpInfoService signUpInfoService;
    @Resource
    private GradeService gradeService;
    @Resource
    private OneClassApplyService oneClassApplyService;

//      一类免考申请
    @GetMapping("/oneClass/apply")
    public String oneClassApply(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        String studentId = (String)session.getAttribute("studentId");
//        根据学生id查准考证号
        List<String> examCardNums = signUpInfoService.queryExamCardNum(studentId);
        model.addAttribute("examCardNumList",examCardNums);
//        根据准考证查找课程
        ArrayList<GradeForOneClass> gradeList = new ArrayList<>();
        for (String examCardNum:examCardNums){
            List<GradeForOneClass> grades = gradeService.queryGradeByCardNum(examCardNum);
            for (GradeForOneClass grade:grades){
                gradeList.add(grade);
            }
        }
        model.addAttribute("gradeList",gradeList);
        return "freeExam/oneClass/apply";
    }
//    发出申请
    @PostMapping("/oneClass/apply")
    public String oneApply(String oneClassApply){
        if (oneClassApply!=null&&oneClassApply!="[]"){
            JSONArray jsonArray = JSONArray.fromObject(oneClassApply);
            List<OneClassApply> list = new ArrayList<>();
            for(int i = 0;i<jsonArray.size();i++) {
                JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
                OneClassApply oneClassApply1 = (OneClassApply) JSONObject.toBean(jsonObject, OneClassApply.class);
                Calendar calendar = Calendar.getInstance();
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                String format = formatter.format(date);
                oneClassApply1.setStartDate(format);
                oneClassApply1.setApplyState(1);
                list.add(oneClassApply1);
            }
            for (OneClassApply oneClassApply1:list){
                oneClassApplyService.insertApply(oneClassApply1);
            }
        }
        return "redirect:apply";
    }
    @PostMapping("/oneClass/reapply")
    public String oneReapply(Model model,String Ecn){
        List<GradeForOneClass> gradeList = gradeService.queryGradeByCardNum(Ecn);
        model.addAttribute("gradeList",gradeList);
        return "freeExam/oneClass/apply";
    }
//    一类免考审核
    @GetMapping("/oneClass/check")
    public String oneClassCheck(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
//        查找所有为审核记录，即审核状态为1的
        List<OneClassApply> oneClassApplies = oneClassApplyService.queryAllApplyByState(1);
        model.addAttribute("oneClassApplyList",oneClassApplies);
        return "freeExam/oneClass/check";
    }

    /**
     * 审核通过
     * @param ID
     * @return
     * @throws InterruptedException
     */
    @PostMapping("/oneClass/check")
    public String oneClassChecked(Integer ID) throws InterruptedException {
//        将审核状态设置为2
        oneClassApplyService.updateApplyState(ID,2);
        return "redirect:check";
    }

    /**
     * 审核驳回
     * @param ID
     * @return
     * @throws InterruptedException
     */
    @PostMapping("/oneClass/refuse")
    public String oneClassRefuse(Integer ID){
        oneClassApplyService.updateApplyState(ID,0);
        return "redirect:check";
    }

    /**
     * 一类申请确认入库
     * @param model
     * @return
     */
    @GetMapping("/oneClass/action")
    public String oneClassAction(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<OneClassApply> oneClassApplies = oneClassApplyService.queryAllApplyByState(2);
        model.addAttribute("oneClassApplyList",oneClassApplies);
        return "freeExam/oneClass/action";
    }

    /**
     * 同意一级免考，成绩入库，更新申请结果和结束时间
     * @param ID
     * @return
     */
    @PostMapping("/oneClass/pullIn")
    public String oneClassActioned(Integer ID,String examCardNum,String courseCode,String oldEcn){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String endDate = formatter.format(date);
        oneClassApplyService.endOneAply(ID,3,1,endDate);
//        更新成绩表状态
        if (examCardNum!=""&&courseCode!=""&&oldEcn!=""){
            String oldGarde = gradeService.queryGradeByPrimary(examCardNum, courseCode);
            String grade = gradeService.queryGradeByPrimary(oldEcn, courseCode);
            Grade grade1 = new Grade(examCardNum, courseCode, grade, 3, 1, 1);
            if (oldGarde==null){
//                主键不存在，直接插入
                gradeService.insertGrade(grade1);
            }else {
                //主键存在，更新成绩
                gradeService.updateGradeByPrimary(grade1);
            }


        }
        return "redirect:action";
    }

    /**
     * 申请最终驳回，
     * @param ID
     * @return
     */
    @PostMapping("/oneClass/endRefuse")
    public String oneClassEndRefuse(Integer ID){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String endDate = formatter.format(date);
        oneClassApplyService.endOneAply(ID,0,0,endDate);
        return "redirect:action";
    }

    /**
     * 二类免考申请
     * @param model
     * @return
     */
    @Resource
    private TwoClassApplyService twoClassApplyService;
    @GetMapping("/twoClass/apply")
    public String twoClassApply(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        String studentId = (String)session.getAttribute("studentId");
        List<TwoClassApplyHelper> twoClassApplyHelpers = twoClassApplyService.queryTwoClassInfo(studentId);
        HashSet<String> sets = new HashSet<>();
        for (TwoClassApplyHelper twoClassApplyHelper:twoClassApplyHelpers){
            sets.add(twoClassApplyHelper.getGraduateId());

        }
        ArrayList<String> graduateIdList = new ArrayList<>(sets);
        model.addAttribute("graduateIdList",graduateIdList);
        model.addAttribute("twoClassApplyList",twoClassApplyHelpers);
        return "freeExam/twoClass/apply";
    }

    @PostMapping("/twoClass/apply")
    public String twoApply(String twoClassApply){
        if (twoClassApply!=null&&twoClassApply!="[]"){
            JSONArray jsonArray = JSONArray.fromObject(twoClassApply);
            List<TwoClassApply> list = new ArrayList<>();
            for(int i = 0;i<jsonArray.size();i++) {
                JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
                TwoClassApply twoClassApply1 = (TwoClassApply) JSONObject.toBean(jsonObject, TwoClassApply.class);
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                String format = formatter.format(date);
                twoClassApply1.setStartDate(format);
                twoClassApply1.setApplyState(1);
                list.add(twoClassApply1);
            }
            for (TwoClassApply twoClassApply1:list){
                twoClassApplyService.insertApply(twoClassApply1);
            }
        }
        return "redirect:apply";
    }

    @PostMapping("/twoClass/reapply")
    public String twoReapply(Model model,String Gid){
        List<TwoClassApplyHelper> twoClassApplyHelpers = twoClassApplyService.queryTwoClassInfoByGid(Gid);
        model.addAttribute("twoClassApplyList",twoClassApplyHelpers);
        return "freeExam/twoClass/apply";
    }

    @GetMapping("/twoClass/check")
    public String twoClassCheck(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        ArrayList<TwoClassApplyHelper> twoClassApplyHelpers = new ArrayList<>();
        List<TwoClassApply> twoClassApplies = twoClassApplyService.queryTwoClassCheck(1);
        for (TwoClassApply twoClassApply:twoClassApplies){
            String graduateId = twoClassApply.getGraduateId();
            String oldCourseCode = twoClassApply.getOldCourseCode();
            CourseNameAndGrade courseNameAndGrade = twoClassApplyService.queryNameAndGrade(graduateId,oldCourseCode);
            twoClassApplyHelpers.add(new TwoClassApplyHelper(twoClassApply,courseNameAndGrade));
        }
        model.addAttribute("twoClassApplyList",twoClassApplyHelpers);
        return "freeExam/twoClass/check";
    }
    @PostMapping("/twoClass/check")
    public String twoClassChecked(Integer ID){
//        System.out.println(ID);
        twoClassApplyService.updateApplyState(ID,2);
        return "redirect:check";
    }
    @PostMapping("/twoClass/refuse")
    public String twoClassRefuse(Integer ID){
        twoClassApplyService.updateApplyState(ID,0);
        return "redirect:check";
    }

    @GetMapping("/twoClass/action")
    public String twoClassAction(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        ArrayList<TwoClassApplyHelper> twoClassApplyHelpers = new ArrayList<>();
        List<TwoClassApply> twoClassApplies = twoClassApplyService.queryTwoClassCheck(2);
        for (TwoClassApply twoClassApply:twoClassApplies){
            String examCardNum = twoClassApply.getExamCardNum();
            String name = twoClassApplyService.queryStudentName(examCardNum);
            twoClassApplyHelpers.add(new TwoClassApplyHelper(twoClassApply,name));
        }
        model.addAttribute("twoClassApplyList",twoClassApplyHelpers);
        return "freeExam/twoClass/action";
    }
    @PostMapping("/twoClass/pullIn")
    public String twoClassActioned(Integer ID,String graduateId,String courseCode,String examCardNum){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String endDate = formatter.format(date);
        twoClassApplyService.endTwoApply(ID,3,1,endDate);
        String grade = gradeService.queryGradeForTwoClass(graduateId, courseCode);
        Grade grade1 = new Grade(examCardNum, courseCode, grade, 3, 1, 1);
//        更新成绩表状态
        if (graduateId!="" && courseCode!="" && examCardNum!=""){
            String s = gradeService.queryGradeByPrimary(examCardNum, courseCode);
            if (s==null){
                gradeService.insertGrade(grade1);
            }else {
                gradeService.updateGradeByPrimary(grade1);
            }

        }
        return "redirect:action";
    }

    @PostMapping("/twoClass/endRefuse")
    public String twoClassEndRefuse(Integer ID){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String endDate = formatter.format(date);
        twoClassApplyService.endTwoApply(ID,0,0,endDate);
        return "redirect:action";
    }

    @Resource
    private CourseService courseService;
    @GetMapping("/threeClass/apply")
    public String threeClassApply(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<Course> courses = courseService.queryAllCourse();
        model.addAttribute("courseList",courses);
        return "freeExam/threeClass/apply";
    }
    @Resource
    private ThreeClassApplyService threeClassApplyService;
    @PostMapping("/threeClass/apply")
    public String threeClassApplySure(Model model,Integer type,String cardNum,String courseCode,HttpSession session){
//        获取准考证号
        Account account = (Account) session.getAttribute("account");
        String examCardNum = account.getAccount();
        List<ThreeClassApplyHelper> cityAndRegion = threeClassApplyService.queryCityAndRegionByEcn(examCardNum);
        ThreeClassApply threeClassApply = new ThreeClassApply();
        threeClassApply.setCardNum(cardNum);
        threeClassApply.setCardType(type);
        threeClassApply.setNewCourseCode(courseCode);
        threeClassApply.setCityCode(cityAndRegion.get(0).getCityCode());
        threeClassApply.setRegionCode(cityAndRegion.get(0).getRegionCode());
        threeClassApply.setExamCardNum(examCardNum);
        threeClassApply.setApplyState(1);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String format = formatter.format(date);

        threeClassApply.setStartDate(format);

        threeClassApplyService.insertApply(threeClassApply);
        return "freeExam/threeClass/apply";
    }


    @GetMapping("/threeClass/check")
    public String threeClassCheck(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<ThreeClassApply> threeClassApplies = threeClassApplyService.queryAllByState(1);
        model.addAttribute("threeApplyList",threeClassApplies);
        return "freeExam/threeClass/check";
    }


    @PostMapping("/threeClass/check")
    public String threeClassCheckSure(Model model,Integer ID){
        threeClassApplyService.updateThreeApplyState(ID,2);
        return "freeExam/threeClass/check";
    }

    @PostMapping("/threeClass/refuse")
    public String threeClassRefuse(Model model,Integer ID){
        threeClassApplyService.updateThreeApplyState(ID,0);
        return "freeExam/threeClass/check";
    }

    @GetMapping("/threeClass/action")
    public String threeClassAction(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return "redirect:/login";
        }
        List<ThreeClassApply> threeClassApplies = threeClassApplyService.queryAllByState(2);
        model.addAttribute("threeApplyList",threeClassApplies);
        return "freeExam/threeClass/action";
    }

    @PostMapping("/threeClass/pullIn")
    public String threeClassActioned(Integer ID,String courseCode,String examCardNum,String grade){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String endDate = formatter.format(date);
        threeClassApplyService.endThreeApply(ID,3,1,endDate);

//        更新成绩表状态
        if (grade!="" && courseCode!="" && examCardNum!=""){
            String s = gradeService.queryGradeByPrimary(examCardNum, courseCode);
            if (s==null||s==""){
                Grade grade1 = new Grade(examCardNum, courseCode, grade, 3, 1, 1);
                gradeService.insertGrade(grade1);
            }

        }
        return "redirect:action";
    }

}
