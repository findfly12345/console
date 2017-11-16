package com.allcheer.bpos.entity;

/**
 * Created by pengleilei on 2017/4/7.
 */
public class InstProfitDO {

    private String cnt;
    private String cardFlag;
    private String ordAmtSum;
    private String feeAmtSum;
    private String refAmtSum;

    private String gateId;
    private String instId;


    private String profitAmt;



    public String getProfitAmt() {
        return profitAmt;
    }

    public void setProfitAmt(String profitAmt) {
        this.profitAmt = profitAmt;
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getCardFlag() {
        return cardFlag;
    }

    public void setCardFlag(String cardFlag) {
        this.cardFlag = cardFlag;
    }

    public String getOrdAmtSum() {
        return ordAmtSum;
    }

    public void setOrdAmtSum(String ordAmtSum) {
        this.ordAmtSum = ordAmtSum;
    }

    public String getFeeAmtSum() {
        return feeAmtSum;
    }

    public void setFeeAmtSum(String feeAmtSum) {
        this.feeAmtSum = feeAmtSum;
    }

    public String getRefAmtSum() {
        return refAmtSum;
    }

    public void setRefAmtSum(String refAmtSum) {
        this.refAmtSum = refAmtSum;
    }
}
