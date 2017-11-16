package com.allcheer.bpos.entity;

public class TblInstProfitInfo {
    private String instId;

    private String transDate;

    private String transType;

    private String transSum;

    private String merTransFee;

    private String instTransFee;

    private String amtSum;

    private String instName;

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId == null ? null : instId.trim();
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate == null ? null : transDate.trim();
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType == null ? null : transType.trim();
    }

    public String getTransSum() {
        return transSum;
    }

    public void setTransSum(String transSum) {
        this.transSum = transSum == null ? null : transSum.trim();
    }

    public String getMerTransFee() {
        return merTransFee;
    }

    public void setMerTransFee(String merTransFee) {
        this.merTransFee = merTransFee == null ? null : merTransFee.trim();
    }

    public String getInstTransFee() {
        return instTransFee;
    }

    public void setInstTransFee(String instTransFee) {
        this.instTransFee = instTransFee == null ? null : instTransFee.trim();
    }

    public String getAmtSum() {
        return amtSum;
    }

    public void setAmtSum(String amtSum) {
        this.amtSum = amtSum == null ? null : amtSum.trim();
    }
}