package com.example.demo.service.Impl;

import com.example.demo.mapper.OneClassApplyMapper;
import com.example.demo.model.OneClassApply;
import com.example.demo.service.OneClassApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:guan
 * @2020/9/2 15:11
 * 文件信息：
 */
@Service
public class OneClassApplyServiceImpl implements OneClassApplyService {
    @Resource
    private OneClassApplyMapper oneClassApplyMapper;
    @Override
    public int insertApply(OneClassApply oneClassApply) {
        return oneClassApplyMapper.insertSelective(oneClassApply);
    }

    @Override
    public List<OneClassApply> queryAllApplyByState(Integer state) {
        return oneClassApplyMapper.queryAllApplyByState(state);
    }

    @Override
    public int updateApplyState(Integer id, Integer stateCode) {
        return oneClassApplyMapper.updateApplyState(id,stateCode);
    }

    @Override
    public int endOneAply(Integer id, Integer stateCode, Integer resCode, String endDate) {
        return oneClassApplyMapper.endOneAply(id,stateCode,resCode,endDate);
    }
}
