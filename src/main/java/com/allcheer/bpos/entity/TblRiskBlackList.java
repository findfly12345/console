package com.allcheer.bpos.entity;

public class TblRiskBlackList {
    private String priCardNo;

    private String instCode;

    private String merchId;

    private String termCode;

    private String errRule;

    private String riskFlag;

    private String createDate;

    private String createTime;

    private String remark;

    public String getPriCardNo() {
        return priCardNo;
    }

    public void setPriCardNo(String priCardNo) {
        this.priCardNo = priCardNo == null ? null : priCardNo.trim();
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode == null ? null : instCode.trim();
    }

    public String getMerchId() {
        return merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId == null ? null : merchId.trim();
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode == null ? null : termCode.trim();
    }

    public String getErrRule() {
        return errRule;
    }

    public void setErrRule(String errRule) {
        this.errRule = errRule == null ? null : errRule.trim();
    }

    public String getRiskFlag() {
        return riskFlag;
    }

    public void setRiskFlag(String riskFlag) {
        this.riskFlag = riskFlag == null ? null : riskFlag.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}