package com.example.demo.model.helper;

/**
 * @author:guan
 * @2020/9/5 9:21
 * 文件信息：
 */
public class GraduateApplyHelper {
    private Integer applyId;
    private String name;
    private Integer sex;
    private String idCardNum;
//    民族
    private Integer nation;
    private String majorNum;
    private String photoPath;

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
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

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public String getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(String majorNum) {
        this.majorNum = majorNum;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "GraduateApplyHelper{" +
                "applyId=" + applyId +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", idCardNum='" + idCardNum + '\'' +
                ", nation=" + nation +
                ", majorNum='" + majorNum + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
