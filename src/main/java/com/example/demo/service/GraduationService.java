package com.example.demo.service;

import com.example.demo.model.GraduateCert;
import com.example.demo.model.helper.*;

import java.util.List;

/**
 * @author:guan
 * @2020/9/4 10:25
 * 文件信息：
 */
public interface GraduationService {
    List<GraduationStatisticHelper> queryAllGraduaterBySchool(String school,String examNum);
    List<GraduationStatisticHelper> queryAllGraduaterByMajor(String major,String examNum);

    List<GraduationStatisticHelper> queryAllGraduaterByArea(String area);
    List<GraduationStatisticHelper> queryAllGraduateByDate(String date);

//    毕业申请审核
    List<GraduateApplyHelper> queryAllGraduateApplyByState(Integer appplyState);
//    审核通过，更新申请状态
    Integer updateGraduateApplyStateById(Integer applyId,Integer applyState,String endDate);
//    撤销毕业证，查询该考生所有准考证和毕业证关系
    List<GraduateCert> queryAllGraduateCertByStudentId(String studentId);
//    根据准考证号查毕业生
    GraduateCancelHelper queryGraduateInfoByEcn(String examCardNum);
//    确认撤销准考证，毕业生转为在籍生
    void updateStudentInfo(String examCardNum);
    void deleteGraduateCertByEcm(String examCardNum);

//    查找学校
    List<String> queryAllSchool();
//    查找所有城市
    List<String> queryAllMajor();
//    查找所有城市
    List<String> queryAllCityCode();

//    查找所有预提生
    List<ReadyGraduationHelper> queryAllReadyGra();

//    查找毕业申请通过之后的学生id和转考证号
    GraduateCertHelper queryStuIdAndEcnByApplyId(Integer applyId);
//    插入新准考证
    void insertNewGraduateCert(GraduateCert graduateCert);
}
