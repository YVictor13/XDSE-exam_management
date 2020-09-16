package com.example.demo.model;

public class Major {
    private String majorNum;

    private String majorName;

    private String majorLevel;

    private String majorType;

    private String majorDir;

    public String getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(String majorNum) {
        this.majorNum = majorNum == null ? null : majorNum.trim();
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    public String getMajorLevel() {
        return majorLevel;
    }

    public void setMajorLevel(String majorLevel) {
        this.majorLevel = majorLevel == null ? null : majorLevel.trim();
    }

    public String getMajorType() {
        return majorType;
    }

    public void setMajorType(String majorType) {
        this.majorType = majorType == null ? null : majorType.trim();
    }

    public String getMajorDir() {
        return majorDir;
    }

    public void setMajorDir(String majorDir) {
        this.majorDir = majorDir == null ? null : majorDir.trim();
    }
}