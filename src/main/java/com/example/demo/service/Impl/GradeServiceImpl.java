package com.example.demo.service.Impl;

import com.example.demo.model.Grade;
import com.example.demo.model.helper.GradeForOneClass;
import com.example.demo.mapper.GradeMapper;
import com.example.demo.service.GradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:guan
 * @2020/9/2 10:06
 * 文件信息：
 */
@Service
public class GradeServiceImpl implements GradeService {
    @Resource
    private GradeMapper gradeMapper;

    @Override
    public List<GradeForOneClass> queryGradeByCardNum(String examCardNum) {
        return gradeMapper.queryGradeByCardNum(examCardNum);
    }

    @Override
    public int updateGradeAfterPullIn(String examCardNum, String courseCode) {
        return gradeMapper.updateGradeAfterPullIn(examCardNum,courseCode);
    }

    @Override
    public String queryGradeByPrimary(String examCardNum, String courseCode) {
        return gradeMapper.queryGradeByPrimary(examCardNum,courseCode);
    }

    @Override
    public int insertGrade(Grade grade) {
        return gradeMapper.insert(grade);
    }

    @Override
    public String queryGradeForTwoClass(String graduateId, String courseCode) {
        return gradeMapper.queryGradeForTwoClass(graduateId,courseCode);
    }

    @Override
    public void updateGradeByPrimary(Grade grade) {
        gradeMapper.updateByPrimaryKey(grade);
    }
}
