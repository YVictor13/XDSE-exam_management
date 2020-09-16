package com.example.demo.mapper;

import com.example.demo.model.Major;

public interface MajorMapper {
    int deleteByPrimaryKey(String majorNum);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(String majorNum);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);
}