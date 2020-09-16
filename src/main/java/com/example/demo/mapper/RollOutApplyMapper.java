package com.example.demo.mapper;

import com.example.demo.model.RollInApply;
import com.example.demo.model.RollOutApply;
import com.example.demo.model.helper.InfoOutApplyHelper;
import com.example.demo.model.helper.RollOutApplyHelper;
import com.example.demo.model.helper.StuInfoInOutHelper;

import java.util.List;

public interface RollOutApplyMapper {
    int deleteByPrimaryKey(Integer applyId);

    int insert(RollOutApply record);

    int insertSelective(RollOutApply record);

    RollOutApply selectByPrimaryKey(Integer applyId);

    int updateByPrimaryKeySelective(RollOutApply record);

    int updateByPrimaryKey(RollOutApply record);

    List<StuInfoInOutHelper> queryAllStuRollOutByCityCode(String cityCode, String examNum);

    List<StuInfoInOutHelper> queryAllStuRollOut();

    List<StuInfoInOutHelper> queryAllStuRollOutByDate(String startDate, String endDate);

    List<String> queryAllRollOutCity();

    InfoOutApplyHelper queryStuInfoByEcn(String examCardNum);

    List<RollOutApplyHelper> queryAllRollOutApplyByState(Integer applyState);

    void updateRollOutState(Integer applyId, Integer applyState, String checker, String endDate);

    RollInApply queryRollOutApplyById(Integer applyId);

    String queryMajorByApplyId(Integer applyId);

    void updateStuInfoStateByApplyId(Integer applyId, Integer infoState);
}