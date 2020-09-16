package com.example.demo.service.Impl;

import com.example.demo.mapper.*;
import com.example.demo.model.*;
import com.example.demo.model.helper.GradeProof;
import com.example.demo.model.helper.ZhEnGradesProof;
import com.example.demo.service.ProofService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProofServiceImpl implements ProofService {
    @Resource
    private SignUpInfoMapper signUpInfoMapper;
    @Resource
    private StudentInfoMapper studentInfoMapper;
    @Resource
    private MajorMapper majorMapper;
    @Resource
    private MyOperatieMapper myOperatieMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private GradeMapper gradeMapper;

    @Override
    public List<MajorCourse> getAllMajorCourses() {
        return myOperatieMapper.selectAllMajorCourse();
    }

    @Override
    public SignUpInfo FindStuId(String examCardNum) {
        return signUpInfoMapper.selectByPrimaryKey(examCardNum);
    }

    @Override
    public StudentInfo FindStuInfo(String studentId) {
        return studentInfoMapper.selectByPrimaryKey(studentId);
    }

    @Override
    public GraduateCert FindGraduateCert(String examCardNum) {

        List<GraduateCert> graduateCertList= myOperatieMapper.selectAllGraduateCert();

        GraduateCert graduateCert;
        for(GraduateCert g:graduateCertList){
            if(g.getExamCardNum().equals(examCardNum)){
                graduateCert = new GraduateCert();
                graduateCert.setGraduateId(g.getGraduateId());
                graduateCert.setGraduateDate(g.getGraduateDate());
                graduateCert.setExamCardNum(examCardNum);
                return graduateCert;
            }
        }
        return null;
    }

    @Override
    public Major FindMajor(String majorNum) {
        return majorMapper.selectByPrimaryKey(majorNum);
    }

    @Override
    public GradeProof getGradeProof(String examCardNum) throws ParseException {
        SignUpInfo signUpInfo = FindStuId(examCardNum);
        GraduateCert graduateCert =FindGraduateCert(examCardNum);
        StudentInfo studentInfo =FindStuInfo(signUpInfo.getStudentId());
        Major major = FindMajor(signUpInfo.getMajorNum());
        GradeProof gradeProof = new GradeProof();
        gradeProof.setName(studentInfo.getName());
        if(studentInfo.getSex()==1){
            gradeProof.setSex("男");
        }else{
            gradeProof.setSex("女");
        }
        gradeProof.setStudentId(studentInfo.getStudentId());
        gradeProof.setExamCardNum(examCardNum);
        gradeProof.setCity(signUpInfo.getCityCode());
        gradeProof.setRegion(signUpInfo.getRegionCode());
        String gDate = graduateCert.getGraduateDate();
        Date orderDateStart = new SimpleDateFormat("yyyyMMdd").parse(gDate);
        String DateStart = new SimpleDateFormat("yyyy-MM-dd").format(orderDateStart);
        gradeProof.setgDate(DateStart);
        gradeProof.setSchool(signUpInfo.getSignUpSchool());
        gradeProof.setMajor(major.getMajorName());
        gradeProof.setGradeType(String.valueOf(studentInfo.getStudentType()));
        gradeProof.setGradeId(signUpInfo.getStudentId()+signUpInfo.getExamCardNum());
        SimpleDateFormat orderSystemDate = new SimpleDateFormat("yyyy-MM-dd");
        String systemDate = orderSystemDate.format(new Date());
        gradeProof.setDate(systemDate);
        return gradeProof;
    }

    @Override
    public ZhEnGradesProof getZhEnGradeProof(String examCardNum) {
        ZhEnGradesProof zhEnGradesProof =new ZhEnGradesProof();

        SignUpInfo signUpInfo = FindStuId(examCardNum);
        zhEnGradesProof.setSignUpDate(signUpInfo.getSignUpDate());
        zhEnGradesProof.setSchoolName(signUpInfo.getSignUpSchool());
        zhEnGradesProof.setStudentId(signUpInfo.getStudentId());

        GraduateCert graduateCert =FindGraduateCert(examCardNum);
        zhEnGradesProof.setgDate(graduateCert.getGraduateDate());

        StudentInfo studentInfo =FindStuInfo(signUpInfo.getStudentId());
        zhEnGradesProof.setBirthday(studentInfo.getBirthday());
        if(studentInfo.getSex()==1){
            zhEnGradesProof.setSex("男");
        }else{
            zhEnGradesProof.setSex("女");
        }
        zhEnGradesProof.setIDCard(studentInfo.getIdCardNum());
        zhEnGradesProof.setName(studentInfo.getName());

        Major major = FindMajor(signUpInfo.getMajorNum());
        zhEnGradesProof.setMajorName(major.getMajorName());
        zhEnGradesProof.setMajorDir(major.getMajorDir());
        zhEnGradesProof.setMajorType(major.getMajorType());

        SimpleDateFormat orderSystemDate = new SimpleDateFormat("yyyy-MM-dd");
        String systemDate = orderSystemDate.format(new Date());
        zhEnGradesProof.setDate(systemDate);

        List<MajorCourse> majorCoursesList = getAllMajorCourses();
        List<Course> courseList = new ArrayList<>();
        for(MajorCourse ls:majorCoursesList){
            if(ls.getMajorNum().equals(major.getMajorNum())){
                Course course = courseMapper.selectByPrimaryKey(ls.getCourseCode());
                courseList.add(course);
            }
        }
        zhEnGradesProof.setCourseList(courseList);
        List<Grade> gradeList = new ArrayList<>();
        for(Course ls :courseList){
            Grade grade = gradeMapper.selectByPrimaryKey(new GradeKey(examCardNum,ls.getCourseCode()));
            gradeList.add(grade);
        }
        zhEnGradesProof.setGradeList(gradeList);
        return zhEnGradesProof;
    }
}