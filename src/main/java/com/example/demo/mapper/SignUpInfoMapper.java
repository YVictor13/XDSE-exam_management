package com.example.demo.mapper;

import com.example.demo.dto.QuitManageSearch;
import com.example.demo.dto.StuBasicInfo;
import com.example.demo.model.SignUpInfo;

import java.util.List;

public interface SignUpInfoMapper {
    int deleteByPrimaryKey(String examCardNum);

    int insert(SignUpInfo record);

    int insertSelective(SignUpInfo record);

    SignUpInfo selectByPrimaryKey(String examCardNum);

    int updateByPrimaryKeySelective(SignUpInfo record);

    int updateByPrimaryKey(SignUpInfo record);


    List<String> queryExamCardNumById(String studentId);

    StuBasicInfo getStuBasicInfoByECN(String ecn);

    // 通过准考证号获取学生基本信息
    List<StuBasicInfo> getStuBasicInfoByExamNum(String examNum);

    // 通过考次，准考证号，页数 获得指定数量的考生基本信息
    List<StuBasicInfo> getStuBasicInfoByQMS(QuitManageSearch quitManageSearch);

    // 根据准考证号列表 获取 学生ID列表
    List<String> getStuIdByECN(List<String> ecnList);
}