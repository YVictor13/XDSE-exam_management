package com.example.demo.service.Impl;

import com.example.demo.mapper.GraduateApplyMapper;
import com.example.demo.mapper.GraduateCertMapper;
import com.example.demo.model.GraduateCert;
import com.example.demo.model.helper.*;
import com.example.demo.service.GraduationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:guan
 * @2020/9/4 10:31
 * 文件信息：
 */
@Service
public class GraduationServiceImpl implements GraduationService {
    @Resource
    private GraduateCertMapper graduateCertMapper;

    @Resource
    private GraduateApplyMapper graduateApplyMapper;

    @Override
    public List<GraduationStatisticHelper> queryAllGraduaterBySchool(String school, String examNum) {
        return graduateCertMapper.queryAllGraduaterBySchool(school,examNum);
    }

    @Override
    public List<GraduationStatisticHelper> queryAllGraduaterByMajor(String major,String examNum) {
        return graduateCertMapper.queryAllGraduaterByMajor(major,examNum);
    }

    @Override
    public List<GraduationStatisticHelper> queryAllGraduaterByArea(String area) {
        return graduateCertMapper.queryAllGraduaterByArea(area);
    }

    @Override
    public List<GraduationStatisticHelper> queryAllGraduateByDate(String date) {
        return graduateCertMapper.queryAllGraduateByDate(date);
    }

    @Override
    public List<GraduateApplyHelper> queryAllGraduateApplyByState(Integer appplyState) {
        return graduateApplyMapper.queryAllGraduateApplyByState(appplyState);
    }

    @Override
    public Integer updateGraduateApplyStateById(Integer applyId,Integer applyState,String endDate) {
        return graduateApplyMapper.updateGraduateApplyStateById(applyId,applyState,endDate);
    }


    @Override
    public List<GraduateCert> queryAllGraduateCertByStudentId(String studentId) {
        return graduateCertMapper.queryAllGraduateCertByStudentId(studentId);

    }

    @Override
    public GraduateCancelHelper queryGraduateInfoByEcn(String examCardNum) {
        return graduateCertMapper.queryGraduateInfoByEcn(examCardNum);
    }

    @Override
    public void updateStudentInfo(String examCardNum) {
        graduateCertMapper.updateStudentInfo(examCardNum);
    }

    @Override
    public void deleteGraduateCertByEcm(String examCardNum) {
        graduateCertMapper.deleteGraduateCertByEcm(examCardNum);
    }

    @Override
    public List<String> queryAllSchool() {
        return graduateApplyMapper.queryAllSchool();
    }

    @Override
    public List<String> queryAllMajor() {
        return graduateApplyMapper.queryAllMajor();
    }

    @Override
    public List<String> queryAllCityCode() {
        return graduateApplyMapper.queryAllCityCode();
    }

    @Override
    public List<ReadyGraduationHelper> queryAllReadyGra() {
        return graduateApplyMapper.queryAllReadyGra();
    }

    @Override
    public GraduateCertHelper queryStuIdAndEcnByApplyId(Integer applyId) {
        return graduateApplyMapper.queryStuIdAndEcnByApplyId(applyId);
    }

    @Override
    public void insertNewGraduateCert(GraduateCert graduateCert) {
        graduateCertMapper.insertSelective(graduateCert);
    }


}
