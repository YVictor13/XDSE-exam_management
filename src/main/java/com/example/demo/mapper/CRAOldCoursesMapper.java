package com.example.demo.mapper;

import com.example.demo.model.CRAOldCoursesKey;

public interface CRAOldCoursesMapper {
    int deleteByPrimaryKey(CRAOldCoursesKey key);

    int insert(CRAOldCoursesKey record);

    int insertSelective(CRAOldCoursesKey record);
}