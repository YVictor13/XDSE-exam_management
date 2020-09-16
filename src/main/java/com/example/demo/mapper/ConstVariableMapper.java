package com.example.demo.mapper;

import com.example.demo.model.ConstVariable;

public interface ConstVariableMapper {
    int deleteByPrimaryKey(String name);

    int insert(ConstVariable record);

    int insertSelective(ConstVariable record);

    ConstVariable selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(ConstVariable record);

    int updateByPrimaryKey(ConstVariable record);

    String queryCurrentExamNum(String name);

}