package com.example.demo.dto;

/**
 * @author:guan
 * @2020/9/3 11:14
 * 文件信息：
 */
public class CourseNameAndGrade {
    private String graduateId;
    private String courseName;
    private String grade;

    public String getGraduateId() {
        return graduateId;
    }

    public void setGraduateId(String graduateId) {
        this.graduateId = graduateId;
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

    @Override
    public String toString() {
        return "CourseNameAndGrade{" +
                "graduateId='" + graduateId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
