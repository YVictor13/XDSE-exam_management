package com.example.demo.model;

public class RollOutApply {
    private Integer applyId;

    private String examCardNum;

    private String applyCode;

    private String rollOutTime;

    private String rollOutReason;

    private Integer applyState;

    private String checkPerson;

    private Integer result;

    private String startDate;

    private String endDate;

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getExamCardNum() {
        return examCardNum;
    }

    public void setExamCardNum(String examCardNum) {
        this.examCardNum = examCardNum == null ? null : examCardNum.trim();
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode == null ? null : applyCode.trim();
    }

    public String getRollOutTime() {
        return rollOutTime;
    }

    public void setRollOutTime(String rollOutTime) {
        this.rollOutTime = rollOutTime == null ? null : rollOutTime.trim();
    }

    public String getRollOutReason() {
        return rollOutReason;
    }

    public void setRollOutReason(String rollOutReason) {
        this.rollOutReason = rollOutReason == null ? null : rollOutReason.trim();
    }

    public Integer getApplyState() {
        return applyState;
    }

    public void setApplyState(Integer applyState) {
        this.applyState = applyState;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson == null ? null : checkPerson.trim();
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }
}