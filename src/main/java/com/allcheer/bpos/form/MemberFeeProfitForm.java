package com.allcheer.bpos.form;

public class MemberFeeProfitForm extends BaseForm<MemberFeeProfitForm> {
	private String memberId;
	private String transDate;
	private String transType;
	private String transSum;
	private String merTransFee;
	private String memberTransFee;
	private String profitAmt;

	private String startDate;
	private String endDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
	public String getTransSum() {
		return transSum;
	}
	public void setTransSum(String transSum) {
		this.transSum = transSum;
	}
	public String getMerTransFee() {
		return merTransFee;
	}
	public void setMerTransFee(String merTransFee) {
		this.merTransFee = merTransFee;
	}
	public String getMemberTransFee() {
		return memberTransFee;
	}
	public void setMemberTransFee(String memberTransFee) {
		this.memberTransFee = memberTransFee;
	}
	public String getProfitAmt() {
		return profitAmt;
	}
	public void setProfitAmt(String profitAmt) {
		this.profitAmt = profitAmt;
	}

}
