package com.allcheer.bpos.form;

public class RiskBlackListForm extends BaseForm<RiskBlackListForm> {

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
		this.priCardNo = priCardNo;
	}

	public String getInstCode() {
		return instCode;
	}

	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}

	public String getMerchId() {
		return merchId;
	}

	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}

	public String getTermCode() {
		return termCode;
	}

	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	public String getErrRule() {
		return errRule;
	}

	public void setErrRule(String errRule) {
		this.errRule = errRule;
	}

	public String getRiskFlag() {
		return riskFlag;
	}

	public void setRiskFlag(String riskFlag) {
		this.riskFlag = riskFlag;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
