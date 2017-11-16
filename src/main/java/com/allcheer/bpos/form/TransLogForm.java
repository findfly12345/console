package com.allcheer.bpos.form;

public class TransLogForm extends BaseForm<TransLogForm> {

	private String startTransDateTime;
	private String endTransDateTime;
	private String cardNo;
	private String instId;
	private String gateId;
	private String bigMerId;
	private String transStat;
	private String refNum;
	private String posSeqId;
	private String agentId;
	private String cardFlag;
	private String ordId;
	private String merId;
	private String merName;
	private String posMerId;
	private String sysMerName;
	private int month;

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getBigMerId() {
		return bigMerId;
	}

	public void setBigMerId(String bigMerId) {
		this.bigMerId = bigMerId;
	}

	public String getTransStat() {
		return transStat;
	}

	public void setTransStat(String transStat) {
		this.transStat = transStat;
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getPosSeqId() {
		return posSeqId;
	}

	public void setPosSeqId(String posSeqId) {
		this.posSeqId = posSeqId;
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

	/**
	 * @return the merId
	 */
	public String getMerId() {
		return merId;
	}

	/**
	 * @param merId the merId to set
	 */
	public void setMerId(String merId) {
		this.merId = merId;
	}

	/**
	 * @return the merName
	 */
	public String getMerName() {
		return merName;
	}

	/**
	 * @param merName the merName to set
	 */
	public void setMerName(String merName) {
		this.merName = merName;
	}

	/**
	 * @return the posMerId
	 */
	public String getPosMerId() {
		return posMerId;
	}

	/**
	 * @param posMerId the posMerId to set
	 */
	public void setPosMerId(String posMerId) {
		this.posMerId = posMerId;
	}

	/**
	 * @return the sysMerName
	 */
	public String getSysMerName() {
		return sysMerName;
	}

	/**
	 * @param sysMerName the sysMerName to set
	 */
	public void setSysMerName(String sysMerName) {
		this.sysMerName = sysMerName;
	}
}
