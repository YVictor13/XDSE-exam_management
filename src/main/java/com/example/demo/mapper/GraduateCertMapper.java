package com.example.demo.mapper;

import com.example.demo.model.GraduateCert;
import com.example.demo.model.helper.GraduateCancelHelper;
import com.example.demo.model.helper.GraduationStatisticHelper;

import java.util.List;

public interface GraduateCertMapper {
    int deleteByPrimaryKey(String graduateId);

    int insert(GraduateCert record);

    int insertSelective(GraduateCert record);

    GraduateCert selectByPrimaryKey(String graduateId);

    int updateByPrimaryKeySelective(GraduateCert record);

    int updateByPrimaryKey(GraduateCert record);

    List<GraduationStatisticHelper> queryAllGraduaterBySchool(String school,String examNum);

    List<GraduationStatisticHelper> queryAllGraduaterByMajor(String major,String examNum);

    List<GraduationStatisticHelper> queryAllGraduaterByArea(String area);

    List<GraduationStatisticHelper> queryAllGraduateByDate(String date);

    List<GraduateCert> queryAllGraduateCertByStudentId(String studentId);

    GraduateCancelHelper queryGraduateInfoByEcn(String examCardNum);

    void updateStudentInfo(String examCardNum);

    void deleteGraduateCertByEcm(String examCardNum);

    void insertNewGraduateCert(GraduateCert graduateCert);
}