package com.example.demo.mapper;

import com.example.demo.model.ExamInfo;

import java.util.List;

public interface ExamInfoMapper {
    int deleteByPrimaryKey(String examNum);

    int insert(ExamInfo record);

    int insertSelective(ExamInfo record);

    ExamInfo selectByPrimaryKey(String examNum);

    int updateByPrimaryKeySelective(ExamInfo record);

    int updateByPrimaryKey(ExamInfo record);

    List<ExamInfo> selectAllExamInfo();
}