package com.example.demo.mapper;

import com.example.demo.model.Course;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(String courseCode);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String courseCode);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> queryAllCourse();
}