package com.allcheer.bpos.form;

public class InstBatchResultForm extends BaseForm<InstBatchResultForm> {

	private String instCode;
	/**
	 * @return the instCode
	 */
	public String getInstCode() {
		return instCode;
	}
	/**
	 * @param instCode the instCode to set
	 */
	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}
	private String batchNo;
	private String instMerId;
	private String operUser;
	private String batchDate;
	
	/**
	 * @return the batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}
	/**
	 * @param batchNo the batchNo to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	/**
	 * @return the instMerId
	 */
	public String getInstMerId() {
		return instMerId;
	}
	/**
	 * @param instMerId the instMerId to set
	 */
	public void setInstMerId(String instMerId) {
		this.instMerId = instMerId;
	}
	/**
	 * @return the operUser
	 */
	public String getOperUser() {
		return operUser;
	}
	/**
	 * @param operUser the operUser to set
	 */
	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}
	/**
	 * @return the batchDate
	 */
	public String getBatchDate() {
		return batchDate;
	}
	/**
	 * @param batchDate the batchDate to set
	 */
	public void setBatchDate(String batchDate) {
		this.batchDate = batchDate;
	}
	
}
