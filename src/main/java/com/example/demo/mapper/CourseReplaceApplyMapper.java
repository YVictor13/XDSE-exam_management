package com.example.demo.mapper;

import com.example.demo.model.CourseReplaceApply;

public interface CourseReplaceApplyMapper {
    int deleteByPrimaryKey(Integer applyId);

    int insert(CourseReplaceApply record);

    int insertSelective(CourseReplaceApply record);

    CourseReplaceApply selectByPrimaryKey(Integer applyId);

    int updateByPrimaryKeySelective(CourseReplaceApply record);

    int updateByPrimaryKey(CourseReplaceApply record);
}