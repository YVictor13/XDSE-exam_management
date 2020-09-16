package com.example.demo.mapper;

import com.example.demo.model.InfoChangeLog;

public interface InfoChangeLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InfoChangeLog record);

    int insertSelective(InfoChangeLog record);

    InfoChangeLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InfoChangeLog record);

    int updateByPrimaryKey(InfoChangeLog record);
}