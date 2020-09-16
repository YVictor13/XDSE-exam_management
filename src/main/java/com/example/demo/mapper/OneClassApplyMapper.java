package com.example.demo.mapper;

import com.example.demo.model.OneClassApply;

import java.util.List;

public interface OneClassApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OneClassApply record);

    int insertSelective(OneClassApply record);

    OneClassApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OneClassApply record);

    int updateByPrimaryKey(OneClassApply record);

    int updateApplyState(Integer id, Integer stateCode);

    List<OneClassApply> queryAllApplyByState(Integer state);

    int endOneAply(Integer id, Integer stateCode, Integer resCode, String endDate);
}