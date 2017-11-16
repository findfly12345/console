package com.allcheer.bpos.form;

public class WCTransLogForm extends BaseForm<WCTransLogForm> {

	private String startTransDateTime;
	private String endTransDateTime;
	private String memberId;
	private String instId;
	private String mobileNo;
	private String transStat;
	private String incomePlatform;
	private String agentId;
	private String cardFlag;
	private String incomeId;

	public String getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(String incomeId) {
		this.incomeId = incomeId;
	}

	public String getIncomePlatform() {
		return incomePlatform;
	}

	public void setIncomePlatform(String incomePlatform) {
		this.incomePlatform = incomePlatform;
	}

	public String getStartTransDateTime() {
		return startTransDateTime;
	}

	public void setStartTransDateTime(String startTransDateTime) {
		this.startTransDateTime = startTransDateTime;
	}

	public String getEndTransDateTime() {
		return endTransDateTime;
	}

	public void setEndTransDateTime(String endTransDateTime) {
		this.endTransDateTime = endTransDateTime;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getTransStat() {
		return transStat;
	}

	public void setTransStat(String transStat) {
		this.transStat = transStat;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getCardFlag() {
		return cardFlag;
	}

	public void setCardFlag(String cardFlag) {
		this.cardFlag = cardFlag;
	}
}
