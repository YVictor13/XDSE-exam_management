package com.example.demo.service;

import com.example.demo.model.ThreeClassApply;
import com.example.demo.model.helper.ThreeClassApplyHelper;

import java.util.List;

/**
 * @author:guan
 * @2020/9/9 10:34
 * 文件信息：
 */
public interface ThreeClassApplyService {
    void insertApply(ThreeClassApply threeClassApply);
    List<ThreeClassApplyHelper> queryCityAndRegionByEcn(String examCardNum);
    List<ThreeClassApply> queryAllByState(Integer integer);
    void updateThreeApplyState(Integer id,Integer stateCode);
    void endThreeApply(Integer id,Integer stateCode,Integer resCode,String endDate);
}
