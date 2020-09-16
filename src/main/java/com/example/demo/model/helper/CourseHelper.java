package com.example.demo.model.helper;

public class CourseHelper {
    private String courseCode;
    private String courseName;
    private String grades;
    private String gradeType;
    private int index;
    private String EnCourseName;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getEnCourseName() {
        return EnCourseName;
    }

    public void setEnCourseName(String enCourseName) {
        EnCourseName = enCourseName;
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

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

}