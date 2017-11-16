package com.allcheer.bpos.entity;

public class InstAccountingSummaryDOKey {
    private String instId;

    private String accountingDate;

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId == null ? null : instId.trim();
    }

    public String getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(String accountingDate) {
        this.accountingDate = accountingDate == null ? null : accountingDate.trim();
    }
}