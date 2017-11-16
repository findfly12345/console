package com.allcheer.bpos.form;

/**
 * Created by LiuBin on 2017/1/10.
 */
public class CheckFileForm extends BaseForm<CheckFileForm> {
	private String filterDate;
	private String merchantCode;
	private String chekcResp;
	private String accNo;
	private String accName;
	private String instCode;

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getChekcResp() {
		return chekcResp;
	}

	public void setChekcResp(String chekcResp) {
		this.chekcResp = chekcResp;
	}

	public String getFilterDate() {
		return filterDate;
	}

	public void setFilterDate(String filterDate) {
		this.filterDate = filterDate;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getInstCode() {
		return instCode;
	}

	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}
}
