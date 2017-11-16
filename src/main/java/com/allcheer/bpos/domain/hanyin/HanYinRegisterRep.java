package com.allcheer.bpos.domain.hanyin;

/**
 * Created by pengleilei on 2017/3/30.
 * <p>
 * 请求参数
 */
public class HanYinRegisterRep {

    private String insCode;//机构号
    private String insMerchantCode;//机构商户编号
    private String merCode;//商户号
    private String merName;//商户名称
    private String realName;//姓名
    private String merState;//商户所在省份
    private String merCity;//商户所在城市
    private String merAddress;//商户所在详细地址
    private String certType;//证件类型
    private String certId;//证件号
    private String mobile;//手机号
    private String accountId;//结算账号
    private String accountName;//结算户名
    private String signature;//签名信息

    private String bankName;//总行名称
    private String bankCode;//总行联行号
    private String openBankName;//开户行全称
    private String openBankState;//开户行省份
    private String openBankCity;//开户行城市
    private String signKey;//总行名称
    private String operFlag;//A：新增 M：修改

    private String t0drawFee;//单笔T0提现交易手续费
    private String t0drawRate;//T0提现交易手续费扣率
    private String t1consFee;//单笔消费交易手续费
    private String t1consRate;//消费交易手续费扣率

    public String getT0drawFee() {
        return t0drawFee;
    }

    public void setT0drawFee(String t0drawFee) {
        this.t0drawFee = t0drawFee;
    }

    public String getT0drawRate() {
        return t0drawRate;
    }

    public void setT0drawRate(String t0drawRate) {
        this.t0drawRate = t0drawRate;
    }

    public String getT1consFee() {
        return t1consFee;
    }

    public void setT1consFee(String t1consFee) {
        this.t1consFee = t1consFee;
    }

    public String getT1consRate() {
        return t1consRate;
    }

    public void setT1consRate(String t1consRate) {
        this.t1consRate = t1consRate;
    }

    public String getOperFlag() {
        return operFlag;
    }

    public void setOperFlag(String operFlag) {
        this.operFlag = operFlag;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getOpenBankName() {
        return openBankName;
    }

    public void setOpenBankName(String openBankName) {
        this.openBankName = openBankName;
    }

    public String getOpenBankState() {
        return openBankState;
    }

    public void setOpenBankState(String openBankState) {
        this.openBankState = openBankState;
    }

    public String getOpenBankCity() {
        return openBankCity;
    }

    public void setOpenBankCity(String openBankCity) {
        this.openBankCity = openBankCity;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public String getInsCode() {
        return insCode;
    }

    public void setInsCode(String insCode) {
        this.insCode = insCode;
    }

    public String getInsMerchantCode() {
        return insMerchantCode;
    }

    public void setInsMerchantCode(String insMerchantCode) {
        this.insMerchantCode = insMerchantCode;
    }

    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMerState() {
        return merState;
    }

    public void setMerState(String merState) {
        this.merState = merState;
    }

    public String getMerCity() {
        return merCity;
    }

    public void setMerCity(String merCity) {
        this.merCity = merCity;
    }

    public String getMerAddress() {
        return merAddress;
    }

    public void setMerAddress(String merAddress) {
        this.merAddress = merAddress;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSign() {
        return new StringBuffer().append(insCode).append("|").append(insMerchantCode).append("|")
                .append(merCode).append("|").append(merName).append("|")
                .append(realName).append("|").append(merState).append("|")
                .append(merCity).append("|").append(merAddress).append("|")
                .append(certType).append("|").append(certId).append("|")
                .append(mobile).append("|").append(accountId).append("|")
                .append(accountName).append("|").append(bankName).append("|")
                .append(bankCode).append("|").append(openBankName == null ? "" : openBankName).append("|")
                .append(openBankState == null ? "" : openBankState).append("|").append(openBankCity == null ? "" : openBankCity).append("|")
                .append(operFlag == null ? "" : operFlag).append("|")
                .append(t0drawFee).append("|")
                .append(t0drawRate).append("|")
                .append(t1consFee).append("|")
                .append(t1consRate).append("|")
                .append(signKey)
                .toString();
    }

    public String getSignature() {
        return signature;
    }
}
