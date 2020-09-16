package com.example.demo.service;

import com.example.demo.model.Grade;
import com.example.demo.model.helper.GradeForOneClass;

import java.util.List;

/**
 * @author:guan
 * @2020/9/2 10:03
 * 文件信息：
 */
public interface GradeService {
    List<GradeForOneClass> queryGradeByCardNum(String examCardNum);
    int updateGradeAfterPullIn(String examCardNum,String courseCode);
    String queryGradeByPrimary(String examCardNum,String courseCode);
    int insertGrade(Grade grade);
    String queryGradeForTwoClass(String graduateId,String courseCode);
    void updateGradeByPrimary(Grade grade);
}
