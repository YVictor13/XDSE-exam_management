package com.example.demo.mapper;

import com.example.demo.model.ECNChangeLog;

public interface ECNChangeLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(ECNChangeLog record);

    int insertSelective(ECNChangeLog record);

    ECNChangeLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ECNChangeLog record);

    int updateByPrimaryKey(ECNChangeLog record);
}