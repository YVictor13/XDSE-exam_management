package com.example.demo.service;

import com.example.demo.dto.QuitManageSearch;
import com.example.demo.dto.StuBasicInfo;
import com.example.demo.model.SignUpInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author:guan
 * @2020/9/2 9:16
 * 文件信息：
 */
public interface SignUpInfoService {
    List<String> queryExamCardNum(String studentId);

    String getStuIdByECN(String ecn);

    StuBasicInfo getStuBasicInfoByECN(String ecn);

    PageInfo<StuBasicInfo> getStuBasicInfoByQMS(QuitManageSearch quitManageSearch);

    // 将准考证号在列表中的考生退考
    void quitStu(List<String> ecnList);
}
