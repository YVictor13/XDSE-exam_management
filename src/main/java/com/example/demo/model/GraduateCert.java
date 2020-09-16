package com.example.demo.model;

public class GraduateCert {
    private String graduateId;

    private String examCardNum;

    private String graduateDate;

    public String getGraduateId() {
        return graduateId;
    }

    public void setGraduateId(String graduateId) {
        this.graduateId = graduateId == null ? null : graduateId.trim();
    }

    public String getExamCardNum() {
        return examCardNum;
    }

    public void setExamCardNum(String examCardNum) {
        this.examCardNum = examCardNum == null ? null : examCardNum.trim();
    }

    public String getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(String graduateDate) {
        this.graduateDate = graduateDate == null ? null : graduateDate.trim();
    }
}