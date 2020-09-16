package com.example.demo.mapper;

import com.example.demo.model.helper.GradeForOneClass;
import com.example.demo.model.Grade;
import com.example.demo.model.GradeKey;

import java.util.List;

public interface GradeMapper {
    int deleteByPrimaryKey(GradeKey key);

    int insert(Grade record);

    int insertSelective(Grade record);

    Grade selectByPrimaryKey(GradeKey key);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);

    List<GradeForOneClass> queryGradeByCardNum(String examCardNum);

    int updateGradeAfterPullIn(String examCardNum, String courseCode);

    String queryGradeByPrimary(String examCardNum, String courseCode);

    String queryGradeForTwoClass(String graduateId, String courseCode);
}