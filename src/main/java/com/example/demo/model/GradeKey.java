package com.example.demo.model;

public class GradeKey {
    private String examCardNum;

    private String courseCode;

    public GradeKey(String examCardNum, String courseCode) {
        this.examCardNum = examCardNum;
        this.courseCode = courseCode;
    }


    public String getExamCardNum() {
        return examCardNum;
    }

    public void setExamCardNum(String examCardNum) {
        this.examCardNum = examCardNum == null ? null : examCardNum.trim();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }
}