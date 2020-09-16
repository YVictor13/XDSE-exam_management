package com.example.demo.dto;

public class KeyInfoChangeApply {
    private String name;
    private int sex;
    private String idCardNum;
    private int nation;
    private String majorNum;
    private String imgPath;
    private String changeReason;
    private String cityOpinion;
    private String rootOpinion;
    private String examCardNum;

    public String getExamCardNum() {
        return examCardNum;
    }

    public void setExamCardNum(String examCardNum) {
        this.examCardNum = examCardNum;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getCityOpinion() {
        return cityOpinion;
    }

    public void setCityOpinion(String cityOpinion) {
        this.cityOpinion = cityOpinion;
    }

    public String getRootOpinion() {
        return rootOpinion;
    }

    public void setRootOpinion(String rootOpinion) {
        this.rootOpinion = rootOpinion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public int getNation() {
        return nation;
    }

    public void setNation(int nation) {
        this.nation = nation;
    }

    public String getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(String majorNum) {
        this.majorNum = majorNum;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}