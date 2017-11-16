package com.allcheer.bpos.domain.hanyin;

/**
 * Created by pengleilei on 2017/3/30.
 * 翰银响应
 */
public class HanYinRegisterResp {

    private  String  insCode;//机构号
    private  String  insMerchantCode;//机构商户编号
    private  String  merCode;//商户号
    private  String  hpMerCode;//瀚银商户号
    private  String  statusCode;//返回状态码
    private  String  statusMsg;//返回状态描述
    private  String  signature;//签名信息


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

    public String getHpMerCode() {
        return hpMerCode;
    }

    public void setHpMerCode(String hpMerCode) {
        this.hpMerCode = hpMerCode;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
