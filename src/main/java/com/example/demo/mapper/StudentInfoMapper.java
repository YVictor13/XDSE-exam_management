package com.example.demo.mapper;

import com.example.demo.model.StudentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentInfoMapper {
    int deleteByPrimaryKey(String studentId);

    int insert(StudentInfo record);

    int insertSelective(StudentInfo record);

    StudentInfo selectByPrimaryKey(String studentId);

    int updateByPrimaryKeySelective(StudentInfo record);

    int updateByPrimaryKey(StudentInfo record);
    List<StudentInfo> countStuInfoByDis(String cityCode);

    Integer updateUnCriticalInfo(StudentInfo studentInfo);

    Integer updatePicPathById(@Param("stuId") String stuId, @Param("newPath") String filename);

    // 通过学生ID列表退考学生
    Integer quitStuById(List<String> stuIdList);

    List<String> queryAllCity();
}