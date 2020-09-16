package com.example.demo.service;

import com.example.demo.dto.CourseNameAndGrade;
import com.example.demo.model.TwoClassApply;
import com.example.demo.model.helper.TwoClassApplyHelper;

import java.util.List;

/**
 * @author:guan
 * @2020/9/3 9:32
 * 文件信息：
 */
public interface TwoClassApplyService {
    List<TwoClassApplyHelper> queryTwoClassInfo(String studentId);

    void insertApply(TwoClassApply twoClassApply1);
    List<TwoClassApply> queryTwoClassCheck(Integer applyState);

    CourseNameAndGrade queryNameAndGrade(String graduateId,String courseId);
    void updateApplyState(Integer id,Integer stateCode);

    String queryStudentName(String examCardNum);
    void endTwoApply(Integer id,Integer stateCode,Integer resCode,String endDate);

    List<TwoClassApplyHelper> queryTwoClassInfoByGid(String Gid);
}
