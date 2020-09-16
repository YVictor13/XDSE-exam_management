package com.example.demo.dto;

public class StuBasicInfo {
    private String examCardNum;
    // StudentInfo 表
    private String name;
    private Integer sex;
    private String photoPath;
    private String studentId;
    private Integer political;
    private Integer nation;
    private Integer censusPlace;
    private String phoneNumber;
    private String email;
    // Major 表
    private String majorName;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getPolitical() {
        return political;
    }

    public void setPolitical(Integer political) {
        this.political = political;
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public Integer getCensusPlace() {
        return censusPlace;
    }

    public void setCensusPlace(Integer censusPlace) {
        this.censusPlace = censusPlace;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "StuBasicInfo{" +
                "examCardNum='" + examCardNum + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", photoPath='" + photoPath + '\'' +
                ", studentId='" + studentId + '\'' +
                ", political=" + political +
                ", nation=" + nation +
                ", censusPlace=" + censusPlace +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}
