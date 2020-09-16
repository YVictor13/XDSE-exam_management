package com.example.demo.service.Impl;

import com.example.demo.mapper.ConstVariableMapper;
import com.example.demo.mapper.RollInApplyMapper;
import com.example.demo.mapper.RollOutApplyMapper;
import com.example.demo.model.RollInApply;
import com.example.demo.model.RollOutApply;
import com.example.demo.model.helper.InfoOutApplyHelper;
import com.example.demo.model.helper.RollOutApplyHelper;
import com.example.demo.model.helper.StuInfoInOutHelper;
import com.example.demo.service.StuInfoInOutService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:guan
 * @2020/9/8 10:29
 * 文件信息：
 */
@Service
public class StuInfoInOutServiceImpl implements StuInfoInOutService {
    @Resource
    private RollInApplyMapper rollInApplyMapper;
    @Resource
    private RollOutApplyMapper rollOutApplyMapper;
    @Override
    public List<StuInfoInOutHelper> queryAllStuRollInByCityCode(String cityCode,String examNum) {
        return rollInApplyMapper.queryAllStuRollInByCityCode(cityCode,examNum);
    }

    @Override
    public List<StuInfoInOutHelper> queryAllStuRollOutByCityCode(String cityCode, String examNum) {
        return rollOutApplyMapper.queryAllStuRollOutByCityCode(cityCode,examNum);
    }

    @Override
    public List<StuInfoInOutHelper> queryAllStuRollOut() {
        return rollOutApplyMapper.queryAllStuRollOut();
    }

    @Override
    public List<StuInfoInOutHelper> queryAllStuRollOutByDate(String startDate, String endDate) {
        return rollOutApplyMapper.queryAllStuRollOutByDate(startDate,endDate);
    }

    @Override
    public List<String> queryAllRollOutCity() {
        return rollOutApplyMapper.queryAllRollOutCity();
    }

    @Override
    public List<RollInApply> queryAllRollInByState(Integer applyState) {
        return rollInApplyMapper.queryAllRollInByState(applyState);
    }

    @Override
    public void updateRollInState(Integer applyId, Integer applyState,String checker,String endDate) {
        rollInApplyMapper.updateRollInState(applyId,applyState,checker,endDate);
    }

    @Override
    public void updateRollInStateEnd(Integer applyId, Integer applyState, Integer result, String endDate) {
        rollInApplyMapper.updateRollInStateEnd(applyId,applyState,result,endDate);
    }

    @Override
    public InfoOutApplyHelper queryStuInfoByEcn(String examCardNum) {
        return rollOutApplyMapper.queryStuInfoByEcn(examCardNum);
    }

    @Override
    public void insertRollOutApply(RollOutApply rollOutApply) {
        rollOutApplyMapper.insertSelective(rollOutApply);
    }

    @Override
    public List<RollOutApplyHelper> queryAllRollOutApplyByState(Integer applyState) {
        return rollOutApplyMapper.queryAllRollOutApplyByState(applyState);
    }

    @Override
    public void updateRollOutState(Integer applyId, Integer applyState, String checker,String endDate) {
        rollOutApplyMapper.updateRollOutState(applyId,applyState,checker,endDate);
    }

    @Override
    public RollInApply queryRollOutApplyById(Integer applyId) {
        return rollOutApplyMapper.queryRollOutApplyById(applyId);
    }

    @Override
    public String queryMajorByApplyId(Integer applyId) {
        return rollOutApplyMapper.queryMajorByApplyId(applyId);
    }

    @Override
    public void insertRollInApply(RollInApply rollInApply) {
        rollInApplyMapper.insertSelective(rollInApply);
    }

    @Override
    public void updateStuInfoStateByApplyId(Integer applyId, Integer infoState) {
        rollOutApplyMapper.updateStuInfoStateByApplyId(applyId,infoState);
    }

    @Resource
    private ConstVariableMapper constVariableMapper;
    @Override
    public String queryCurrentExamNum(String name) {
        return constVariableMapper.queryCurrentExamNum(name);
    }
}
