package com.example.demo.model.helper;

import com.example.demo.dto.CourseNameAndGrade;
import com.example.demo.model.TwoClassApply;

/**
 * @author:guan
 * @2020/9/3 9:23
 * 文件信息：
 */
public class TwoClassApplyHelper {
    private Integer id;
    private String graduateId;
    private String examCardNum;
    private String courseCode;
    private String courseName;
    private String cityCode;
    private String regionCode;
    private String grade;
    private Integer valid;
    private String name;


    public String getGraduateId() {
        return graduateId;
    }

    public void setGraduateId(String graduateId) {
        this.graduateId = graduateId;
    }

    public String getExamCardNum() {
        return examCardNum;
    }

    public void setExamCardNum(String examCardNum) {
        this.examCardNum = examCardNum;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    @Override
    public String toString() {
        return "TwoClassApplyHelper{" +
                "id=" + id +
                ", graduateId='" + graduateId + '\'' +
                ", examCardNum='" + examCardNum + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", grade='" + grade + '\'' +
                ", valid=" + valid +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TwoClassApplyHelper(TwoClassApply twoClassApply, CourseNameAndGrade courseNameAndGrade) {

        this.id = twoClassApply.getId();
        this.graduateId = twoClassApply.getGraduateId();
        this.examCardNum = twoClassApply.getExamCardNum();
        this.courseCode = twoClassApply.getNewCourseCode();
        this.courseName = courseNameAndGrade.getCourseName();
        this.grade = courseNameAndGrade.getGrade();
    }

    public TwoClassApplyHelper(String graduateId, String examCardNum, String courseCode, String courseName, String cityCode, String regionCode, String grade, Integer valid) {
        this.graduateId = graduateId;
        this.examCardNum = examCardNum;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.cityCode = cityCode;
        this.regionCode = regionCode;
        this.grade = grade;
        this.valid = valid;
    }

    public TwoClassApplyHelper( TwoClassApply twoClassApply,String name) {
        this.id = twoClassApply.getId();
        this.graduateId = twoClassApply.getGraduateId();
        this.examCardNum = twoClassApply.getExamCardNum();
        this.courseCode = twoClassApply.getOldCourseCode();
        this.name = name;
    }
}
