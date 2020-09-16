package com.example.demo.mapper;

import com.example.demo.model.GraduatePreApply;

public interface GraduatePreApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GraduatePreApply record);

    int insertSelective(GraduatePreApply record);

    GraduatePreApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GraduatePreApply record);

    int updateByPrimaryKey(GraduatePreApply record);
}