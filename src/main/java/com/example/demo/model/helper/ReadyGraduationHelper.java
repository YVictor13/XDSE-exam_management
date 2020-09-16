package com.example.demo.model.helper;

/**
 * @author:guan
 * @2020/9/9 22:02
 * 文件信息：
 */
public class ReadyGraduationHelper {
    private Integer id;
    private String examCardNum;
    private String examNum;

    private String majorNum;

    private String cityCode;

    private String regionCode;

    private String signUpSchool;

    private Integer signUpType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExamCardNum() {
        return examCardNum;
    }

    public void setExamCardNum(String examCardNum) {
        this.examCardNum = examCardNum;
    }

    public String getExamNum() {
        return examNum;
    }

    public void setExamNum(String examNum) {
        this.examNum = examNum;
    }

    public String getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(String majorNum) {
        this.majorNum = majorNum;
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

    public String getSignUpSchool() {
        return signUpSchool;
    }

    public void setSignUpSchool(String signUpSchool) {
        this.signUpSchool = signUpSchool;
    }

    public Integer getSignUpType() {
        return signUpType;
    }

    public void setSignUpType(Integer signUpType) {
        this.signUpType = signUpType;
    }

    @Override
    public String toString() {
        return "readyGraduationHelper{" +
                "id=" + id +
                ", examCardNum='" + examCardNum + '\'' +
                ", examNum='" + examNum + '\'' +
                ", majorNum='" + majorNum + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", signUpSchool='" + signUpSchool + '\'' +
                ", signUpType=" + signUpType +
                '}';
    }
}
