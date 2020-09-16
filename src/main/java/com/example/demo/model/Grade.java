package com.example.demo.model;

public class Grade extends GradeKey {
    private String grade;

    private Integer gradeType;

    private Integer valid;

    private Integer putinState;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public Integer getGradeType() {
        return gradeType;
    }

    public void setGradeType(Integer gradeType) {
        this.gradeType = gradeType;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Integer getPutinState() {
        return putinState;
    }

    public void setPutinState(Integer putinState) {
        this.putinState = putinState;
    }

    public Grade(String examCardNum, String courseCode,String grade, Integer gradeType, Integer valid, Integer putinState) {
        super(examCardNum,courseCode);
        this.grade = grade;
        this.gradeType = gradeType;
        this.valid = valid;
        this.putinState = putinState;
    }
}