package com.allcheer.bpos.entity;

public class TblInstTransAuth {
    private String instCode;

    private String posStat;

    private String chatStat;

    private String allipayStat;

    private String authStat;

    private String remark;

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode == null ? null : instCode.trim();
    }

    public String getPosStat() {
        return posStat;
    }

    public void setPosStat(String posStat) {
        this.posStat = posStat == null ? null : posStat.trim();
    }

    public String getChatStat() {
        return chatStat;
    }

    public void setChatStat(String chatStat) {
        this.chatStat = chatStat == null ? null : chatStat.trim();
    }

    public String getAllipayStat() {
        return allipayStat;
    }

    public void setAllipayStat(String allipayStat) {
        this.allipayStat = allipayStat == null ? null : allipayStat.trim();
    }

    public String getAuthStat() {
        return authStat;
    }

    public void setAuthStat(String authStat) {
        this.authStat = authStat == null ? null : authStat.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}