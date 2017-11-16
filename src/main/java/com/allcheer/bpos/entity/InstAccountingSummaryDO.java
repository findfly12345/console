package com.allcheer.bpos.entity;

public class InstAccountingSummaryDO extends InstAccountingSummaryDOKey {
    private String transSumAmt;

    private String transFeeSumAmt;

    private String transRefFeeAmt;

    private String transClearAmt;

    public String getTransSumAmt() {
        return transSumAmt;
    }

    public void setTransSumAmt(String transSumAmt) {
        this.transSumAmt = transSumAmt == null ? null : transSumAmt.trim();
    }

    public String getTransFeeSumAmt() {
        return transFeeSumAmt;
    }

    public void setTransFeeSumAmt(String transFeeSumAmt) {
        this.transFeeSumAmt = transFeeSumAmt == null ? null : transFeeSumAmt.trim();
    }

    public String getTransRefFeeAmt() {
        return transRefFeeAmt;
    }

    public void setTransRefFeeAmt(String transRefFeeAmt) {
        this.transRefFeeAmt = transRefFeeAmt == null ? null : transRefFeeAmt.trim();
    }

    public String getTransClearAmt() {
        return transClearAmt;
    }

    public void setTransClearAmt(String transClearAmt) {
        this.transClearAmt = transClearAmt == null ? null : transClearAmt.trim();
    }
}