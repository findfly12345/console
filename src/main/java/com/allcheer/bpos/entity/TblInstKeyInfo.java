package com.allcheer.bpos.entity;

public class TblInstKeyInfo {
    private String instCode;

    private String instMainKey;

    private String instPinKey;

    private String instMacKey;

    private String instTdKey;

    private String loginStat;

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode == null ? null : instCode.trim();
    }

    public String getInstMainKey() {
        return instMainKey;
    }

    public void setInstMainKey(String instMainKey) {
        this.instMainKey = instMainKey == null ? null : instMainKey.trim();
    }

    public String getInstPinKey() {
        return instPinKey;
    }

    public void setInstPinKey(String instPinKey) {
        this.instPinKey = instPinKey == null ? null : instPinKey.trim();
    }

    public String getInstMacKey() {
        return instMacKey;
    }

    public void setInstMacKey(String instMacKey) {
        this.instMacKey = instMacKey == null ? null : instMacKey.trim();
    }

    public String getInstTdKey() {
        return instTdKey;
    }

    public void setInstTdKey(String instTdKey) {
        this.instTdKey = instTdKey == null ? null : instTdKey.trim();
    }

    public String getLoginStat() {
        return loginStat;
    }

    public void setLoginStat(String loginStat) {
        this.loginStat = loginStat == null ? null : loginStat.trim();
    }
}