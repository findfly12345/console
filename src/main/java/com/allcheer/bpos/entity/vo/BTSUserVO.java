package com.allcheer.bpos.entity.vo;

import java.math.BigDecimal;

public class BTSUserVO {
    private BigDecimal userId;

    private String userCode;

    private String userName;

    private String status;

    private String note;

    private String createDate;

    private BigDecimal createById;

    private BigDecimal lastUpdateId;

    private String lastUpdateTime;

    private BigDecimal loginFailCount;

    private String firstLoginFailTime;

    private String password;

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public BigDecimal getCreateById() {
        return createById;
    }

    public void setCreateById(BigDecimal createById) {
        this.createById = createById;
    }

    public BigDecimal getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(BigDecimal lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime == null ? null : lastUpdateTime.trim();
    }

    public BigDecimal getLoginFailCount() {
        return loginFailCount;
    }

    public void setLoginFailCount(BigDecimal loginFailCount) {
        this.loginFailCount = loginFailCount;
    }

    public String getFirstLoginFailTime() {
        return firstLoginFailTime;
    }

    public void setFirstLoginFailTime(String firstLoginFailTime) {
        this.firstLoginFailTime = firstLoginFailTime == null ? null : firstLoginFailTime.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}