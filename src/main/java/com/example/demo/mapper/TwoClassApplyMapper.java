package com.example.demo.mapper;

import com.example.demo.dto.CourseNameAndGrade;
import com.example.demo.model.TwoClassApply;
import com.example.demo.model.helper.TwoClassApplyHelper;

import java.util.List;

public interface TwoClassApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TwoClassApply record);

    int insertSelective(TwoClassApply record);

    TwoClassApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TwoClassApply record);

    int updateByPrimaryKey(TwoClassApply record);

    List<TwoClassApplyHelper> queryTwoClassInfo(String studentId);

    List<TwoClassApply> queryTwoClassCheck(Integer applyState);

    CourseNameAndGrade queryNameAndGrade(String graduateId, String courseId);

    void updateApplyState(Integer id, Integer stateCode);

    String queryStudentName(String examCardNum);

    void endTwoApply(Integer id, Integer stateCode, Integer resCode, String endDate);

    List<TwoClassApplyHelper> queryTwoClassInfoByGid(String Gid);
}