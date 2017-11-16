package com.allcheer.bpos.entity;

public class InstProfitSummaryDO {

	private String agentId;

	private String instId;

	private String transDate;

	private String transType;

	private String transSumAmt;

	private double transFeeSumAmt;

	private String refAmt;

	public String getRefAmt() {
		return refAmt;
	}

	public void setRefAmt(String refAmt) {
		this.refAmt = refAmt;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getTransSumAmt() {
		return transSumAmt;
	}

	public void setTransSumAmt(String transSumAmt) {
		this.transSumAmt = transSumAmt;
	}

	public double getTransFeeSumAmt() {
		return transFeeSumAmt;
	}

	public void setTransFeeSumAmt(double transFeeSumAmt) {
		this.transFeeSumAmt = transFeeSumAmt;
	}
}