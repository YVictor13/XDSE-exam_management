package com.example.demo.dto;

public class ImportGradeDto {

    private String examNum;
    private String courseCode;
    private String grade;
    private String courseName;

    @Override
    public String toString() {
        return "ImportGradeDto{" +
                "examNum='" + examNum + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", grade='" + grade + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }

    public String getExamNum() {
        return examNum;
    }

    public void setExamNum(String examNum) {
        this.examNum = examNum;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}