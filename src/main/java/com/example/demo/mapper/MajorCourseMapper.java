package com.example.demo.mapper;

import com.example.demo.model.MajorCourse;

public interface MajorCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MajorCourse record);

    int insertSelective(MajorCourse record);

    MajorCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MajorCourse record);

    int updateByPrimaryKey(MajorCourse record);
}