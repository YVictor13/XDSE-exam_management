package com.example.demo.mapper;

import com.example.demo.model.InfoChangeApply;

public interface InfoChangeApplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(InfoChangeApply record);

    int insertSelective(InfoChangeApply record);

    InfoChangeApply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InfoChangeApply record);

    int updateByPrimaryKey(InfoChangeApply record);
}