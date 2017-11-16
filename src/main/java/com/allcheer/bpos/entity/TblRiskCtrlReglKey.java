package com.allcheer.bpos.entity;

public class TblRiskCtrlReglKey {
    private String riskNum;

    private String paramNum;

    public String getRiskNum() {
        return riskNum;
    }

    public void setRiskNum(String riskNum) {
        this.riskNum = riskNum == null ? null : riskNum.trim();
    }

    public String getParamNum() {
        return paramNum;
    }

    public void setParamNum(String paramNum) {
        this.paramNum = paramNum == null ? null : paramNum.trim();
    }
}