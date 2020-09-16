package com.example.demo.service;

import com.example.demo.model.RollInApply;
import com.example.demo.model.RollOutApply;
import com.example.demo.model.helper.InfoOutApplyHelper;
import com.example.demo.model.helper.RollOutApplyHelper;
import com.example.demo.model.helper.StuInfoInOutHelper;

import java.util.List;

/**
 * @author:guan
 * @2020/9/8 10:24
 * 文件信息：
 */
public interface StuInfoInOutService {
    List<StuInfoInOutHelper> queryAllStuRollInByCityCode(String cityCode,String examNum);
    List<StuInfoInOutHelper> queryAllStuRollOutByCityCode(String cityCode,String examNum);
    List<StuInfoInOutHelper> queryAllStuRollOut();
    List<StuInfoInOutHelper> queryAllStuRollOutByDate(String startDate,String endDate);

    List<String> queryAllRollOutCity();

    List<RollInApply> queryAllRollInByState(Integer applyState);
    void updateRollInState(Integer applyId,Integer applyState,String checker,String endDate);
    void updateRollInStateEnd(Integer applyId,Integer applyState,Integer result,String endDate);
    InfoOutApplyHelper queryStuInfoByEcn(String examCardNum);
    void insertRollOutApply(RollOutApply rollOutApply);
    List<RollOutApplyHelper> queryAllRollOutApplyByState(Integer applyState);

    void updateRollOutState(Integer applyId,Integer applyState,String checker,String endDate);

    RollInApply queryRollOutApplyById(Integer applyId);

    String queryMajorByApplyId(Integer applyId);
    void insertRollInApply(RollInApply rollInApply);
    void updateStuInfoStateByApplyId(Integer applyId,Integer infoState);

    String queryCurrentExamNum(String name);

}
