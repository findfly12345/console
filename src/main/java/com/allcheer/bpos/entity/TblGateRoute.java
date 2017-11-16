package com.allcheer.bpos.entity;

public class TblGateRoute {
    private String gateId;

    private String gateType;

    private String gateDesp;

    private String gateFee;

    private String gateStat;

    private String remark;

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId == null ? null : gateId.trim();
    }

    public String getGateType() {
        return gateType;
    }

    public void setGateType(String gateType) {
        this.gateType = gateType == null ? null : gateType.trim();
    }

    public String getGateDesp() {
        return gateDesp;
    }

    public void setGateDesp(String gateDesp) {
        this.gateDesp = gateDesp == null ? null : gateDesp.trim();
    }

    public String getGateFee() {
        return gateFee;
    }

    public void setGateFee(String gateFee) {
        this.gateFee = gateFee == null ? null : gateFee.trim();
    }

    public String getGateStat() {
        return gateStat;
    }

    public void setGateStat(String gateStat) {
        this.gateStat = gateStat == null ? null : gateStat.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}