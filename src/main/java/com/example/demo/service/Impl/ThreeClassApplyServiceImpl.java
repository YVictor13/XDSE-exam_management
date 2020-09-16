package com.example.demo.service.Impl;

import com.example.demo.mapper.ThreeClassApplyMapper;
import com.example.demo.model.ThreeClassApply;
import com.example.demo.model.helper.ThreeClassApplyHelper;
import com.example.demo.service.ThreeClassApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:guan
 * @2020/9/9 10:45
 * 文件信息：
 */
@Service
public class ThreeClassApplyServiceImpl implements ThreeClassApplyService {
    @Resource
    private ThreeClassApplyMapper threeClassApplyMapper;
    @Override
    public void insertApply(ThreeClassApply threeClassApply) {
        threeClassApplyMapper.insertSelective(threeClassApply);
    }

    @Override
    public List<ThreeClassApplyHelper> queryCityAndRegionByEcn(String examCardNum) {
        return threeClassApplyMapper.queryCityAndRegionByEcn(examCardNum);
    }

    @Override
    public List<ThreeClassApply> queryAllByState(Integer state) {
        return threeClassApplyMapper.queryAllByState(state);
    }

    @Override
    public void updateThreeApplyState(Integer id, Integer stateCode) {
        threeClassApplyMapper.updateThreeApplyState(id,stateCode);
    }

    @Override
    public void endThreeApply(Integer id, Integer stateCode, Integer resCode, String endDate) {
        threeClassApplyMapper.endThreeApply(id,stateCode,resCode,endDate);
    }
}
