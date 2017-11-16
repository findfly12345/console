package com.allcheer.bpos.form;

public class InstProfitInfoForm extends BaseForm<InstProfitInfoForm> {

	private String startTransDateTime;
	private String endTransDateTime;
	private String instId;
	private String transDate;
	private String transType;
	private String transSum;
	private String merTransFee;
	private String instTransFee;
	private String amtSum;

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

	public String getInstTransFee() {
		return instTransFee;
	}

	public void setInstTransFee(String instTransFee) {
		this.instTransFee = instTransFee;
	}

	public String getAmtSum() {
		return amtSum;
	}

	public void setAmtSum(String amtSum) {
		this.amtSum = amtSum;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

}
