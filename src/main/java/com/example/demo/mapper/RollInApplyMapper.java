package com.example.demo.mapper;

import com.example.demo.model.RollInApply;
import com.example.demo.model.helper.StuInfoInOutHelper;

import java.util.List;

public interface RollInApplyMapper {
    int deleteByPrimaryKey(Integer applyId);

    int insert(RollInApply record);

    int insertSelective(RollInApply record);

    RollInApply selectByPrimaryKey(Integer applyId);

    int updateByPrimaryKeySelective(RollInApply record);

    int updateByPrimaryKey(RollInApply record);

    List<StuInfoInOutHelper> queryAllStuRollInByCityCode(String cityCode,String examNum);

    List<RollInApply> queryAllRollInByState(Integer applyState);

    void updateRollInState(Integer applyId, Integer applyState, String checker, String endDate);

    void updateRollInStateEnd(Integer applyId, Integer applyState, Integer result, String endDate);

}