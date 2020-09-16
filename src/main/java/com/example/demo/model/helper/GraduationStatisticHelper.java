package com.example.demo.model.helper;

/**
 * @author:guan
 * @2020/9/4 10:27
 * 文件信息：
 */
public class GraduationStatisticHelper {
    private String examCardNum;
    private String name;
    private String majorNum;
    private String graduateDate;
    private String graduateId;

    public String getExamCardNum() {
        return examCardNum;
    }

    public void setExamCardNum(String examCardNum) {
        this.examCardNum = examCardNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(String majorNum) {
        this.majorNum = majorNum;
    }

    public String getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(String graduateDate) {
        this.graduateDate = graduateDate;
    }

    public String getGraduateId() {
        return graduateId;
    }

    public void setGraduateId(String graduateId) {
        this.graduateId = graduateId;
    }

    @Override
    public String toString() {
        return "GraduationStatisticHelper{" +
                "examCardNum='" + examCardNum + '\'' +
                ", name='" + name + '\'' +
                ", majorNum='" + majorNum + '\'' +
                ", graduateDate='" + graduateDate + '\'' +
                ", graduateId='" + graduateId + '\'' +
                '}';
    }

    public GraduationStatisticHelper(String examCardNum, String name, String majorNum, String graduateDate) {
        this.examCardNum = examCardNum;
        this.name = name;
        this.majorNum = majorNum;
        this.graduateDate = graduateDate;
    }

    public GraduationStatisticHelper(String examCardNum, String name, String majorNum, String graduateDate, String graduateId) {
        this.examCardNum = examCardNum;
        this.name = name;
        this.majorNum = majorNum;
        this.graduateDate = graduateDate;
        this.graduateId = graduateId;
    }
}
