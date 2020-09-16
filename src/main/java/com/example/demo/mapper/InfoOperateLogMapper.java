package com.example.demo.mapper;

import com.example.demo.model.InfoOperateLog;

public interface InfoOperateLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InfoOperateLog record);

    int insertSelective(InfoOperateLog record);

    InfoOperateLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InfoOperateLog record);

    int updateByPrimaryKey(InfoOperateLog record);
}