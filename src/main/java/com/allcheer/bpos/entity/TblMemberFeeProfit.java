package com.allcheer.bpos.entity;

public class TblMemberFeeProfit {
    private String memberId;

    private String transDate;

    private String transType;

    private String transSum;

    private String merTransFee;

    private String memberTransFee;

    private String profitAmt;

    private String agentName;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
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

    public String getMemberTransFee() {
        return memberTransFee;
    }

    public void setMemberTransFee(String memberTransFee) {
        this.memberTransFee = memberTransFee == null ? null : memberTransFee.trim();
    }

    public String getProfitAmt() {
        return profitAmt;
    }

    public void setProfitAmt(String profitAmt) {
        this.profitAmt = profitAmt == null ? null : profitAmt.trim();
    }
}