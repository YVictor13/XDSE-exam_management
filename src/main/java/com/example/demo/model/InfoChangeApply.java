package com.example.demo.model;

public class InfoChangeApply {
    private String id;

    private String examCardNum;

    private String fieldName;

    private String oldValue;

    private String newValue;

    private String changeReason;

    private Integer changeState;

    private String cityOpinion;

    private String rootOpinion;

    private String handleTime;

    private Integer resultState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getExamCardNum() {
        return examCardNum;
    }

    public void setExamCardNum(String examCardNum) {
        this.examCardNum = examCardNum == null ? null : examCardNum.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue == null ? null : oldValue.trim();
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue == null ? null : newValue.trim();
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason == null ? null : changeReason.trim();
    }

    public Integer getChangeState() {
        return changeState;
    }

    public void setChangeState(Integer changeState) {
        this.changeState = changeState;
    }

    public String getCityOpinion() {
        return cityOpinion;
    }

    public void setCityOpinion(String cityOpinion) {
        this.cityOpinion = cityOpinion == null ? null : cityOpinion.trim();
    }

    public String getRootOpinion() {
        return rootOpinion;
    }

    public void setRootOpinion(String rootOpinion) {
        this.rootOpinion = rootOpinion == null ? null : rootOpinion.trim();
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime == null ? null : handleTime.trim();
    }

    public Integer getResultState() {
        return resultState;
    }

    public void setResultState(Integer resultState) {
        this.resultState = resultState;
    }
}