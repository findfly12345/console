package com.allcheer.bpos.form;

public class InstAccountSumForm extends BaseForm<InstAccountSumForm>{

	private String instId;
	private String accountingDate;

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getAccountingDate() {
		return accountingDate;
	}

	public void setAccountingDate(String accountingDate) {
		this.accountingDate = accountingDate;
	}
}
