package com.allcheer.bpos.entity;

public class TblRiskCtrlRegl extends TblRiskCtrlReglKey {
    private String paramVal;

    private String riskFlag;

    private String modOper;

    private String modDate;

    private String remark;

    public String getParamVal() {
        return paramVal;
    }

    public void setParamVal(String paramVal) {
        this.paramVal = paramVal == null ? null : paramVal.trim();
    }

    public String getRiskFlag() {
        return riskFlag;
    }

    public void setRiskFlag(String riskFlag) {
        this.riskFlag = riskFlag == null ? null : riskFlag.trim();
    }

    public String getModOper() {
        return modOper;
    }

    public void setModOper(String modOper) {
        this.modOper = modOper == null ? null : modOper.trim();
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate == null ? null : modDate.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}