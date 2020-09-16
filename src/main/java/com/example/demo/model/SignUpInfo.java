package com.example.demo.model;

public class SignUpInfo {
    private String examCardNum;

    private String studentId;

    private String examNum;

    private String majorNum;

    private String cityCode;

    private String regionCode;

    private String signUpSchool;

    private Integer signUpType;

    private String signUpDate;

    public String getExamCardNum() {
        return examCardNum;
    }

    public void setExamCardNum(String examCardNum) {
        this.examCardNum = examCardNum == null ? null : examCardNum.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getExamNum() {
        return examNum;
    }

    public void setExamNum(String examNum) {
        this.examNum = examNum == null ? null : examNum.trim();
    }

    public String getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(String majorNum) {
        this.majorNum = majorNum == null ? null : majorNum.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    public String getSignUpSchool() {
        return signUpSchool;
    }

    public void setSignUpSchool(String signUpSchool) {
        this.signUpSchool = signUpSchool == null ? null : signUpSchool.trim();
    }

    public Integer getSignUpType() {
        return signUpType;
    }

    public void setSignUpType(Integer signUpType) {
        this.signUpType = signUpType;
    }

    public String getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(String signUpDate) {
        this.signUpDate = signUpDate == null ? null : signUpDate.trim();
    }
}