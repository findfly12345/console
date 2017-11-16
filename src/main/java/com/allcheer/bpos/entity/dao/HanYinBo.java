package com.allcheer.bpos.entity.dao;

/**
 * Created by peng.ll on 2017/3/28.
 * 翰银接口实体
 */
public class HanYinBo {

    private String hYinsCode;//机构号
    private String insMerchantCode;//机构商户编号
    private String hpMerCode;//瀚银商户号
    private String signature;//签名信息
    private String signKey;//签名key
    private String hyorderId;//流水号（16位随机数）
    private String transSeq;//翰银流水号
    private String statusCode;//转台
    private String statusMsg;//描述
    private String actualAmount;//到账金额
    private String transAmt;//提现金额
    private String merDate;//交易日期
    private String transStatus;//交易状态
    private String merId;//商户id

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public String getMerDate() {
        return merDate;
    }

    public void setMerDate(String merDate) {
        this.merDate = merDate;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    private String posSeqId;

    public String getPosSeqId() {
        return posSeqId;
    }

    public void setPosSeqId(String posSeqId) {
        this.posSeqId = posSeqId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    private String instId;

    public String gethYinsCode() {
        return hYinsCode;
    }

    public void sethYinsCode(String hYinsCode) {
        this.hYinsCode = hYinsCode;
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getInsMerchantCode() {
        return insMerchantCode;
    }

    public void setInsMerchantCode(String insMerchantCode) {
        this.insMerchantCode = insMerchantCode;
    }

    public String getHpMerCode() {
        return hpMerCode;
    }

    public void setHpMerCode(String hpMerCode) {
        this.hpMerCode = hpMerCode;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public String getHyorderId() {
        return hyorderId;
    }

    public void setHyorderId(String hyorderId) {
        this.hyorderId = hyorderId;
    }

    public String getTransSeq() {
        return transSeq;
    }

    public void setTransSeq(String transSeq) {
        this.transSeq = transSeq;
    }

    public String getSig() {
        return new StringBuffer().append(hYinsCode).append("|").append(insMerchantCode).append("|")
                .append(hpMerCode).append("|").append(hyorderId).append("|")
                .append(merDate).append("|").append(transAmt).append("|")
                .append(signKey)
                .toString();
    }

    public String respSign() {
        return new StringBuffer().append(hYinsCode).append("|").append(insMerchantCode).append("|")
                .append(hpMerCode).append("|").append(hyorderId).append("|")
                .append(merDate).append("|").append(transSeq).append("|")
                .append(signKey)
                .toString();
    }

}
