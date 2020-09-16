package com.example.demo.mapper;

import com.example.demo.model.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MyOperatieMapper {
    //查询所有修改申请的数据
    @Select("select * from info_change_apply where result_state = 0")
    List<InfoChangeApply> selectAll();

    //查询对应用户的修改信息
    @Select("select * from info_change_apply where exam_card_num=#{exam_card_num} and field_name=#{field_name} and result_state = 0")
    InfoChangeApply selectByAccount(@Param("exam_card_num") String exam_card_num, @Param("field_name") String field_name);

    //查询成绩单中的准考证号对应的考生
    @Select("select * from sign_up_info where exam_card_num=#{exam_card_num}")
    SignUpInfo selectByExamCardNum(@Param("exam_card_num") String exam_card_num);

    //查询成绩单中的课程编号对应的课程
    @Select("select * from course where course_code=#{course_code}")
    Course selectByCourseCode(@Param("course_code") String course_code);

    //查询所有成绩
    @Select("select * from grade where valid = 1")
    List<Grade> selectAllGrade();


    //更新注册考生的考次
    @Update("update sign_up_info set exam_num =#{exam_num} where exam_card_num = #{exam_card_num}")
    int updateSignInfoExamNum(@Param("exam_num") String exam_num, @Param("exam_card_num") String exam_card_num);

    @Select("select * from sign_up_info where student_id=#{student_id}")
    List<SignUpInfo> selectSignUpInfoById(@Param("student_id") String student_id);

    @Select("select * from sign_up_info where exam_card_num=#{exam_card_num}")
    SignUpInfo selectExamNumByExamNum(@Param("exam_card_num") String exam_card_num);

    //查询考生毕业表单
    @Select("select * from graduate_cert")
    List<GraduateCert> selectAllGraduateCert();

    @Select("select * from major_course")
    List<MajorCourse> selectAllMajorCourse();

    @Select("select * from graduate_apply")
    List<GraduateApply> selectAllGraduateApply();

    @Select("select * from major")
    List<Major> selectAllMajor();

    //查询所有前置学历申请
    @Select("select * from graduate_pre_apply")
    List<GraduatePreApply> selectAllGraduatePreApply();

    //更新审核状态
    @Update("update graduate_pre_apply set apply_state = #{apply_state} where exam_card_num=#{exam_card_num}")
    void updateByExamCardNum(@Param("exam_card_num") String exam_card_num, @Param("apply_state") int apply_state);

    //更新审核结果
    @Update("update graduate_pre_apply set result = #{result} where exam_card_num=#{exam_card_num} ")
    void updateByExamCardNumChangeResult(@Param("exam_card_num") String exam_card_num, @Param("result") int result);

    @Select("select * from info_operate_log")
    List<InfoOperateLog> selectAllInfoOperateLog();

    @Select("select * from info_change_log")
    List<InfoChangeLog> selectAllInfoChangeLog();
}
