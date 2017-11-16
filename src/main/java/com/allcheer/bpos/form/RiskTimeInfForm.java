package com.allcheer.bpos.form;

public class RiskTimeInfForm extends BaseForm<RiskTimeInfForm> {
	public String getRiskSeq() {
		return riskSeq;
	}

	public void setRiskSeq(String riskSeq) {
		this.riskSeq = riskSeq;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getRiskDetail() {
		return riskDetail;
	}

	public void setRiskDetail(String riskDetail) {
		this.riskDetail = riskDetail;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getRiskRemark() {
		return riskRemark;
	}

	public void setRiskRemark(String riskRemark) {
		this.riskRemark = riskRemark;
	}

	private String riskSeq;
	private String riskType;
	private String riskDetail;
	private String beginTime;
	private String endTime;
	private String stat;
	private String riskRemark;
}
