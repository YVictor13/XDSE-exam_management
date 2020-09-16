package com.example.demo.service.Impl;

import com.example.demo.mapper.*;
import com.example.demo.model.*;
import com.example.demo.service.StudentNeedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentNeedServiceImpl implements StudentNeedService {
    @Resource
    private GraduateApplyMapper graduateApplyMapper;
    @Resource
    private MyOperatieMapper myOperatieMapper;
    @Resource
    private SignUpInfoMapper signUpInfoMapper;
    @Resource
    private GradeMapper gradeMapper;
    @Resource
    private GraduatePreApplyMapper graduatePreApplyMapper;

    @Override
    public boolean isGraduation(String account) {
        SignUpInfo signUpInfo = signUpInfoMapper.selectByPrimaryKey(account);
        List<MajorCourse> majorCourseList = myOperatieMapper.selectAllMajorCourse();
        List<String> courseCodeList = new ArrayList<>();

        for(MajorCourse majorCourse:majorCourseList){
            if(majorCourse.getMajorNum().equals(signUpInfo.getMajorNum())){
                courseCodeList.add(majorCourse.getCourseCode());
            }
        }

        for(String courseCode:courseCodeList){
            GradeKey gradeKey = new GradeKey(account,courseCode);
            Grade grade =gradeMapper.selectByPrimaryKey(gradeKey);
            if(grade==null||grade.getPutinState()==0||grade.getValid()==0){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isHasGraduationApply(String account) {
        List<GraduateApply> graduateApplyList = myOperatieMapper.selectAllGraduateApply();
        for(GraduateApply graduateApply:graduateApplyList){
            if(graduateApply.getExamCardNum().equals(account)&&graduateApply.getApplyState()==0){
                return true;
            }
        }
        return false;
    }


    @Override
    public void AddGraduationApply(String account) {

        GraduateApply graduateApply = new GraduateApply();
        graduateApply.setExamCardNum(account);
        graduateApply.setResult(1);
        graduateApply.setApplyState(0);
        SimpleDateFormat orderSystemDate = new SimpleDateFormat("yyyyMMdd");
        String systemDate = orderSystemDate.format(new Date());
        graduateApply.setStartDate(systemDate);
        graduateApplyMapper.insert(graduateApply);

    }

    @Override
    public int FindResult(String examCardNum) {
        List<GraduateApply> graduateApplyList = myOperatieMapper.selectAllGraduateApply();
        for(GraduateApply graduateApply:graduateApplyList){
            if(graduateApply.getExamCardNum().equals(examCardNum)){
                if(graduateApply.getApplyState()==0){
                    return 1;
                }
                if(graduateApply.getEndDate()!=null){
                    return 0;
                }
                if(graduateApply.getApplyState()==1&&graduateApply.getEndDate()==null){
                    return graduateApply.getResult();
                }
            }
        }
        return -2;
    }

    @Override
    public List<Major> selectAllMajor() {
        return myOperatieMapper.selectAllMajor();
    }

    @Override
    public void AddGraduationPreApply(String examCardNum) {
        GraduatePreApply graduatePreApply = new GraduatePreApply();
        graduatePreApply.setExamCardNum(examCardNum);
        graduatePreApply.setResult(1);
        graduatePreApply.setApplyState(0);
        SimpleDateFormat orderSystemDate = new SimpleDateFormat("yyyyMMdd");
        String systemDate = orderSystemDate.format(new Date());
        graduatePreApply.setStartDate(systemDate);
        graduatePreApplyMapper.insert(graduatePreApply);
    }

    @Override
    public boolean isHasGraduationPreApply(String examCardNum) {
        List<GraduatePreApply> graduatePreApplyList = myOperatieMapper.selectAllGraduatePreApply();
        for(GraduatePreApply graduatePreApply:graduatePreApplyList){
            if(graduatePreApply.getExamCardNum().equals(examCardNum)&&graduatePreApply.getApplyState()==0){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<GraduatePreApply> selectAllGraduatePreApply() {
        List<GraduatePreApply> graduatePreApplyList = myOperatieMapper.selectAllGraduatePreApply();
        List<GraduatePreApply> list =new ArrayList<>();
        int i= 1;
        for(GraduatePreApply g:graduatePreApplyList){
            if(g.getApplyState()==0){
                GraduatePreApply graduatePreApply = new GraduatePreApply();
                graduatePreApply.setId(i++);
                graduatePreApply.setExamCardNum(g.getExamCardNum());
                graduatePreApply.setApplyState(0);
                graduatePreApply.setStartDate(g.getStartDate());
                list.add(graduatePreApply);
            }
        }
        return list;
    }

    @Override
    public void UpdatePreApplyState(String examCardId) {
       myOperatieMapper.updateByExamCardNum(examCardId,1);
       myOperatieMapper.updateByExamCardNumChangeResult(examCardId,2);
    }

    @Override
    public void RollBackApply(String examCardId) {
        myOperatieMapper.updateByExamCardNum(examCardId,-1);
    }

    @Override
    public boolean isHasGraduationPreQualification(String examCardNum) {
        List<GraduatePreApply> graduatePreApplyList = myOperatieMapper.selectAllGraduatePreApply();
        for(GraduatePreApply graduatePreApply:graduatePreApplyList){
            if(graduatePreApply.getExamCardNum().equals(examCardNum)&&graduatePreApply.getApplyState()==1){
                return true;
            }
        }
        return false;
    }
}