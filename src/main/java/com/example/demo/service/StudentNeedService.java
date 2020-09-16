package com.example.demo.service;

import com.example.demo.model.GraduateApply;
import com.example.demo.model.GraduatePreApply;
import com.example.demo.model.Major;

import java.util.List;

public interface StudentNeedService {

    //判断考生是否具有毕业资格
    boolean isGraduation(String account);

    //判断考生是否已经申请过了
    boolean isHasGraduationApply(String account);

    //添加毕业申请记录
    void AddGraduationApply(String account);

    //查询毕业证状态
    int FindResult(String examCardNum);

    //查询所有的专业信息
    List<Major> selectAllMajor();
    //添加专业前置毕业申请记录
    void AddGraduationPreApply(String examCardNum);

    //判断是否存在前置申请
    boolean isHasGraduationPreApply(String examCardNum);

    //查询所有的前置申请记录
    List<GraduatePreApply> selectAllGraduatePreApply();

    //设置申请为已审核，且待打印毕业证
    void UpdatePreApplyState(String examCardId);

    //设置审核为驳回
    void RollBackApply(String examCardId);

    //判断前置申请是否已经审核
    boolean isHasGraduationPreQualification(String examCardNum);
}
