package com.example.demo.service;

import com.example.demo.model.OneClassApply;

import java.util.List;

/**
 * @author:guan
 * @2020/9/2 15:10
 * 文件信息：
 */
public interface OneClassApplyService {
    int insertApply(OneClassApply oneClassApply);
    List<OneClassApply> queryAllApplyByState(Integer state);
    int updateApplyState(Integer id,Integer stateCode);
    int endOneAply(Integer id,Integer stateCode,Integer resCode,String endDate);
}
