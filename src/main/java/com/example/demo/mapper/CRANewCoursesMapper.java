package com.example.demo.mapper;

import com.example.demo.model.CRANewCoursesKey;

public interface CRANewCoursesMapper {
    int deleteByPrimaryKey(CRANewCoursesKey key);

    int insert(CRANewCoursesKey record);

    int insertSelective(CRANewCoursesKey record);
}