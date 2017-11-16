package com.allcheer.bpos.form;


/**
 * Created by fireWorks on 2016/2/26.
 */
public class TradeListForm extends BaseForm{
	private String id;

	private String sendDateStart;

	private String sendDateEnd;

	private String transStatus;

	private String acctNo;

	private String orderNumber;

	private String instCode;

	private String cardIssueBank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSendDateStart() {
        return sendDateStart;
    }

    public void setSendDateStart(String sendDateStart) {
        this.sendDateStart = sendDateStart;
    }

    public String getSendDateEnd() {
        return sendDateEnd;
    }

    public void setSendDateEnd(String sendDateEnd) {
        this.sendDateEnd = sendDateEnd;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus == null ? null : transStatus.trim();
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode == null ? null : instCode.trim();
    }

    public String getCardIssueBank() {
        return cardIssueBank;
    }

    public void setCardIssueBank(String cardIssueBank) {
        this.cardIssueBank = cardIssueBank == null ? null : cardIssueBank.trim();
    }


}
