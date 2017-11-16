package com.allcheer.bpos.entity;

public class GateBankCheckLog {
    private String gateId;

    private String bankCheckDate;

    private String bankCheckResult;

    private String bankCheckTimes;

    private String lastBankCheckTime;

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId == null ? null : gateId.trim();
    }

    public String getBankCheckDate() {
        return bankCheckDate;
    }

    public void setBankCheckDate(String bankCheckDate) {
        this.bankCheckDate = bankCheckDate == null ? null : bankCheckDate.trim();
    }

    public String getBankCheckResult() {
        return bankCheckResult;
    }

    public void setBankCheckResult(String bankCheckResult) {
        this.bankCheckResult = bankCheckResult == null ? null : bankCheckResult.trim();
    }

    public String getBankCheckTimes() {
        return bankCheckTimes;
    }

    public void setBankCheckTimes(String bankCheckTimes) {
        this.bankCheckTimes = bankCheckTimes == null ? null : bankCheckTimes.trim();
    }

    public String getLastBankCheckTime() {
        return lastBankCheckTime;
    }

    public void setLastBankCheckTime(String lastBankCheckTime) {
        this.lastBankCheckTime = lastBankCheckTime == null ? null : lastBankCheckTime.trim();
    }
}