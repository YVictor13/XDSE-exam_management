package com.example.demo.model;

public class Course {
    private String courseCode;

    private String courseName;

    private String courseEnglishName;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getCourseEnglishName() {
        return courseEnglishName;
    }

    public void setCourseEnglishName(String courseEnglishName) {
        this.courseEnglishName = courseEnglishName == null ? null : courseEnglishName.trim();
    }
}