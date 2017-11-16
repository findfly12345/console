package com.allcheer.bpos.form;

public class BlackMerForm extends BaseForm<BlackMerForm> {
	
	public String getMerNo() {
		return merNo;
	}
	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	private String merNo;
	private String createTime;

}
