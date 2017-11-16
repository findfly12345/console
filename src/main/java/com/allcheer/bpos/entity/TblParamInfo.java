package com.allcheer.bpos.entity;

public class TblParamInfo {
    private String paramNum;

    private String paramName;

    private String paramValue;

    private String paramStat;

    public String getParamNum() {
        return paramNum;
    }

    public void setParamNum(String paramNum) {
        this.paramNum = paramNum == null ? null : paramNum.trim();
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    public String getParamStat() {
        return paramStat;
    }

    public void setParamStat(String paramStat) {
        this.paramStat = paramStat == null ? null : paramStat.trim();
    }
}