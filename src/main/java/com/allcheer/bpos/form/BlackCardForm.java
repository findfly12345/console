package com.allcheer.bpos.form;

public class BlackCardForm extends BaseForm<BlackCardForm> {
	
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	private String cardNo;
	private String createTime;

}
