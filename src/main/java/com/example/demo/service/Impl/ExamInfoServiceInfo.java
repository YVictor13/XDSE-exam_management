package com.example.demo.service.Impl;

import com.example.demo.mapper.ExamInfoMapper;
import com.example.demo.model.ExamInfo;
import com.example.demo.service.ExamInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExamInfoServiceInfo implements ExamInfoService {

    @Resource
    private ExamInfoMapper examInfoMapper;

    // TODO: 考虑限制结果个数/分页？
    @Override
    public List<ExamInfo> getExamInfos() {
        return examInfoMapper.selectAllExamInfo();
    }
}
