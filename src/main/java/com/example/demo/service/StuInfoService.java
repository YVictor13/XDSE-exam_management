package com.example.demo.service;

import com.example.demo.dto.ImportGradeDto;
import com.example.demo.dto.KeyInfoChangeApply;
import com.example.demo.model.Account;
import com.example.demo.model.ExamInfo;
import com.example.demo.model.InfoChangeApply;
import com.example.demo.model.StudentInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author:guan
 * @2020/8/31 11:31
 * 文件信息：
 */
public interface StuInfoService {
    List<StudentInfo> countStuInfoByDis(String cityCode);
    StudentInfo queryUnCriticalInfo(String studentId);
    Integer updateUnCriticalInfo(StudentInfo studentInfo);

    List<String> queryAllCity();

    //查询考次
    int setCurrExamNum(ExamInfo examInfo, Account account);

    //导入成绩单
    boolean ImportGrade(String fileName, MultipartFile file) throws Exception;

    //查询所有的成绩
    List<ImportGradeDto> selectAllGrade(String studentId);

    //检查设置考次是否重复
    boolean HasExamInfo(String examNum);


    //用户信息修改审核记录
    List<InfoChangeApply> selectAllApply();

    //用户关键信息修改
    String KeyModify(KeyInfoChangeApply apply,Account account);

    //准考证号修改
    int ECNModify(String changeReason,Account account);

    //获取当前用户的所有用户修改信息
    boolean HasModifyApply(Account account);


    //获取用户修改记录信息
    InfoChangeApply FindChangeApplyByAccount(Account account, String fieldName);

    //获取用户当前关键信息
    KeyInfoChangeApply FindKeyInfoByAccount(Account account);

    //查询用户的学生ID
    String FindIdByAccount(Account account);

    Integer updatePicPathById(String stuId, String filename);
}
