package com.allcheer.bpos.form;

public class OnlineRouteControlForm extends BaseForm<OnlineRouteControlForm> {
	public String getOnlineRouteSeq() {
		return onlineRouteSeq;
	}
	public void setOnlineRouteSeq(String onlineRouteSeq) {
		this.onlineRouteSeq = onlineRouteSeq;
	}
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getGateLevel() {
		return gateLevel;
	}
	public void setGateLevel(String gateLevel) {
		this.gateLevel = gateLevel;
	}
	public String getDestGateId() {
		return destGateId;
	}
	public void setDestGateId(String destGateId) {
		this.destGateId = destGateId;
	}
	public String getDestMerId() {
		return destMerId;
	}
	public void setDestMerId(String destMerId) {
		this.destMerId = destMerId;
	}
	public String getFeeRate() {
		return feeRate;
	}
	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	private String onlineRouteSeq;
	private String instId;
	private String merId;
	private String gateLevel;
	private String destGateId;
	private String destMerId;
	private String feeRate;
	private String stat;
}
