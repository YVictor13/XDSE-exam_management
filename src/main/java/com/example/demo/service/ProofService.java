package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.model.helper.GradeProof;
import com.example.demo.model.helper.ZhEnGradesProof;

import java.text.ParseException;
import java.util.List;


public interface ProofService {


    //通过准考证号查找考生身份证号
    SignUpInfo FindStuId(String examCardNum);
    //通过考生身份证号查找考生信息
    StudentInfo FindStuInfo(String studentId);
    //根据准考证号查找是否毕业
    GraduateCert FindGraduateCert(String examCardNum);
    //查找考生的专业信息
    Major FindMajor(String majorNum);
    //获取证明信息
    GradeProof getGradeProof(String examCardNum) throws ParseException;
    //获取中中英文成绩单证明信息
    ZhEnGradesProof getZhEnGradeProof(String examCardNum);
    //获取所有的课程-专业表信息
    List<MajorCourse> getAllMajorCourses();

}
