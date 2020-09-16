package com.example.demo.service.Impl;

import com.example.demo.dto.CourseNameAndGrade;
import com.example.demo.mapper.TwoClassApplyMapper;
import com.example.demo.model.TwoClassApply;
import com.example.demo.model.helper.TwoClassApplyHelper;
import com.example.demo.service.TwoClassApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:guan
 * @2020/9/3 9:33
 * 文件信息：
 */
@Service
public class TwoClassApplyServiceImpl implements TwoClassApplyService {
    @Resource
    private TwoClassApplyMapper twoClassApplyMapper;
    @Override
    public List<TwoClassApplyHelper> queryTwoClassInfo(String studentId) {
        return twoClassApplyMapper.queryTwoClassInfo(studentId);
    }

    @Override
    public void insertApply(TwoClassApply twoClassApply1) {
        twoClassApplyMapper.insertSelective(twoClassApply1);
    }

    @Override
    public List<TwoClassApply> queryTwoClassCheck(Integer applyState) {
        return twoClassApplyMapper.queryTwoClassCheck(applyState);
    }

    @Override
    public CourseNameAndGrade queryNameAndGrade(String graduateId, String courseId) {
        return twoClassApplyMapper.queryNameAndGrade(graduateId,courseId);
    }

    @Override
    public void updateApplyState(Integer id, Integer stateCode) {
        twoClassApplyMapper.updateApplyState(id,stateCode);
    }

    @Override
    public String queryStudentName(String examCardNum) {
        return twoClassApplyMapper.queryStudentName(examCardNum);
    }

    @Override
    public void endTwoApply(Integer id, Integer stateCode, Integer resCode, String endDate) {
        twoClassApplyMapper.endTwoApply(id, stateCode, resCode, endDate);
    }

    @Override
    public List<TwoClassApplyHelper> queryTwoClassInfoByGid(String Gid) {
        return twoClassApplyMapper.queryTwoClassInfoByGid(Gid);
    }


}
