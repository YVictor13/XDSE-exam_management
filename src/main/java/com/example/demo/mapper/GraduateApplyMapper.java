package com.example.demo.mapper;

import com.example.demo.model.GraduateApply;
import com.example.demo.model.GraduateCert;
import com.example.demo.model.helper.GraduateApplyHelper;
import com.example.demo.model.helper.GraduateCertHelper;
import com.example.demo.model.helper.GraduationStatisticHelper;
import com.example.demo.model.helper.ReadyGraduationHelper;

import java.util.List;

public interface GraduateApplyMapper {
    int deleteByPrimaryKey(Integer applyId);

    int insert(GraduateApply record);

    int insertSelective(GraduateApply record);

    GraduateApply selectByPrimaryKey(Integer applyId);

    int updateByPrimaryKeySelective(GraduateApply record);

    int updateByPrimaryKey(GraduateApply record);

    List<GraduateApplyHelper> queryAllGraduateApplyByState(Integer applyState);

    Integer updateGraduateApplyStateById(Integer applyId, Integer applyState, String endDate);

    List<String> queryAllSchool();

    List<String> queryAllMajor();

    List<String> queryAllCityCode();

    List<ReadyGraduationHelper> queryAllReadyGra();

    GraduateCertHelper queryStuIdAndEcnByApplyId(Integer applyId);
}