package com.allcheer.bpos.form;

public class InstTransAuthForm extends BaseForm<InstTransAuthForm> {

	public String getInstCode() {
		return instCode;
	}
	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}
	public String getPosStat() {
		return posStat;
	}
	public void setPosStat(String posStat) {
		this.posStat = posStat;
	}
	public String getChatStat() {
		return chatStat;
	}
	public void setChatStat(String chatStat) {
		this.chatStat = chatStat;
	}
	public String getAllipayStat() {
		return allipayStat;
	}
	public void setAllipayStat(String allipayStat) {
		this.allipayStat = allipayStat;
	}
	public String getAuthStat() {
		return authStat;
	}
	public void setAuthStat(String authStat) {
		this.authStat = authStat;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	private String instCode;
	private String posStat;
	private String chatStat;
	private String allipayStat;
	private String authStat;
	private String remark;

}
