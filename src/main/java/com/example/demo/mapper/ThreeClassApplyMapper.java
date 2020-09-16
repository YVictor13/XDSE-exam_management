package com.example.demo.mapper;

import com.example.demo.model.ThreeClassApply;
import com.example.demo.model.helper.ThreeClassApplyHelper;

import java.util.List;

public interface ThreeClassApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThreeClassApply record);

    int insertSelective(ThreeClassApply record);

    ThreeClassApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThreeClassApply record);

    int updateByPrimaryKey(ThreeClassApply record);

    List<ThreeClassApplyHelper> queryCityAndRegionByEcn(String examCardNum);

    List<ThreeClassApply> queryAllByState(Integer state);

    void updateThreeApplyState(Integer id, Integer stateCode);

    void endThreeApply(Integer id, Integer stateCode, Integer resCode, String endDate);
}