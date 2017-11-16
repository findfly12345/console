package com.allcheer.bpos.domain.Minsheng;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import javax.xml.bind.annotation.XmlRootElement;


import lombok.Data;

@Data
@XmlRootElement(name="intoPiecesRepuest")
public class IntoPiecesRepuest {
    @NotNull(message="合作机构号不能为空")
    @Length(max=8, message="合作机构号长度超限")
    private String instId;

    @NotNull(message="商户号不能为空")
    @Length(max=18, message="商户号长度超限")
    private String merId;

    @NotNull(message="商户名不能为空")
    @Length(max=64, message="商户名长度超限")
    private String merName;

    @NotNull(message="商户类型 不能为空")
    @Length(max=1, message="商户类型 长度超限")
    @Pattern(regexp="1|2|0", message="商户类型非法")
    private String merType;

    @NotNull(message="注册名不能为空")
    @Length(max=64, message="注册名长度超限")
    private String regName;

    @NotNull(message="注册名简称不能为空")
    @Length(max=64, message="注册名简称长度超限")
    private String regShortName;

    @NotNull(message="商户注册地址不能为空")
    @Length(max=256, message="商户注册地址长度超限")
    private String regAddress;

    @NotNull(message="商户注册资本不能为空")
    @Length(max=256, message="商户注册资本长度超限")
    private String regFunds;

    @NotNull(message="营业执照不能为空")
    @Length(max=256, message="营业执照长度超限")
    private String busLicNm;

    @NotNull(message="营业执照有效期不能为空")
    @Length(max=256, message="营业执照有效期长度超限")
    private String busLicExpire;

    @NotNull(message="税务登记证不能为空")
    @Length(max=256, message="税务登记证长度超限")
    private String taxRegCert;

    @NotNull(message="法人代表不能为空")
    @Length(max=64, message="法人代表长度超限")
    private String legalPerson;

    @NotNull(message="法人证件类型不能为空")
    @Length(max=1, message="法人证件类型长度超限")
    @Pattern(regexp="0", message="法人证件类型非法")
    private String legalPersonCertType;

    @NotNull(message="法人证件号不能为空")
    @Length(max=64, message="法人证件号长度超限")
    private String legalPersonCertNm;

    @NotNull(message="法人证件有效期不能为空")
    @Length(min=8, max=8, message="法人证件有效期长度为8位")
    @Pattern(regexp="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)", message="法人证件有效期非法")
    private String legalPersonCertExpire;

    @NotNull(message="联系人不能为空")
    @Length(max=64, message="联系人长度超限")
    private String contactPerson;

    @NotNull(message="联系电话不能为空")
    @Length(max=256, message="联系电话长度超限")
    private String contactMobile;

    @NotNull(message="联系人邮箱不能为空")
    @Length(max=256, message="联系人邮箱长度超限")
    private String contactEmail;

    @NotNull(message="开卡行不能为空")
    @Length(max=64, message="开卡行长度超限")
    private String bankName;

    @NotNull(message="开卡省不能为空")
    @Length(max=64, message="开卡省长度超限")
    private String bankProv;

    @NotNull(message="开卡城市不能为空")
    @Length(max=64, message="开卡城市长度超限")
    private String bankCity;

    @NotNull(message="开卡支行不能为空")
    @Length(max=256, message="开卡支行长度超限")
    private String bankBranchName;

    @NotNull(message="联行号不能为空")
    @Length(max=256, message="联行号长度超限")
    private String cnaps;

    @NotNull(message="结算类型不能为空")
    @Length(max=1, message="结算类型长度超限")
    @Pattern(regexp="Y|N", message="结算类型非法")
    private String isPrivate;

    @NotNull(message="账户名不能为空")
    @Length(max=256, message="账户名长度超限")
    private String acctName;

    @NotNull(message="账户号不能为空")
    @Length(max=256, message="账户号长度超限")
    private String acctNo;

    @NotNull(message="POS借记卡-比例不能为空")
    @Length(max=256, message="POS借记卡-比例长度超限")
    private String posDebitFeeRate;

    @NotNull(message="POS借记卡-封顶不能为空")
    @Length(max=256, message="POS借记卡-封顶长度超限")
    private String posDebitFeeMax;

    @NotNull(message="POS贷记卡-比例不能为空")
    @Length(max=256, message="POS贷记卡-比例长度超限")
    private String posCreditFeeRate;

    @NotNull(message="微信T0比例不能为空")
    @Length(max=12, message="微信T0比例长度超限")
    private String wechatFeeRateT0;

    @NotNull(message="微信T1比例不能为空")
    @Length(max=12, message="微信T1比例长度超限")
    private String wechatFeeRateT1;

    @NotNull(message="微信T0比例不能为空")
    @Length(max=12, message="微信T0比例长度超限")
    private String aliPayFeeRateT0;

    @NotNull(message="微信T0比例不能为空")
    @Length(max=12, message="微信T0比例长度超限")
    private String aliPayFeeRateT1;

    @NotNull(message="提现手续费类型不能为空")
    @Length(max=1, message="提现手续费类型 长度超限")
    @Pattern(regexp="1|0", message="提现手续费类型 非法")
    private String withdrawalFeeType;

    @Length(max=200, message="提现手续费")
    private String withdrawalFee;

    @Length(max=200, message="垫资手续费")
    private String withdrawalFeeRate;

    @NotNull(message="终端号不能为空")
    @Length(max=26, message="终端号长度超限")
    private String termId;

    @NotNull(message="终端名称不能为空")
    @Length(max=12, message="终端名称长度超限")
    private String termName;

    @NotNull(message="终端类型不能为空")
    @Length(max=12, message="终端类型长度超限")
    @Pattern(regexp="1|2|0", message="终端类型非法")
    private String termType;

    @NotNull(message="终端安装省不能为空")
    @Length(max=12, message="终端安装省长度超限")
    private String termProv;

    @NotNull(message="终端安装市不能为空")
    @Length(max=12, message="终端安装市长度超限")
    private String termCity;

    @Length(max=12, message="终端安装县长度超限")
    private String termCounty;

    @Length(max=200, message="终端安装地址长度超限")
    private String termAddress;

    @Length(max=200, message="终端SN号长度超限")
    private String termSn;

    @NotNull(message="MCC码不能为空")
    @Length(max=4, message="MCC码长度超限")
    private String mccCode;

    @NotNull(message="结算周期")
    @Length(max=4, message="结算周期长度超限")
    private String settlePeriod;

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

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getMerType() {
        return merType;
    }

    public void setMerType(String merType) {
        this.merType = merType;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getRegShortName() {
        return regShortName;
    }

    public void setRegShortName(String regShortName) {
        this.regShortName = regShortName;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public String getRegFunds() {
        return regFunds;
    }

    public void setRegFunds(String regFunds) {
        this.regFunds = regFunds;
    }

    public String getBusLicNm() {
        return busLicNm;
    }

    public void setBusLicNm(String busLicNm) {
        this.busLicNm = busLicNm;
    }

    public String getBusLicExpire() {
        return busLicExpire;
    }

    public void setBusLicExpire(String busLicExpire) {
        this.busLicExpire = busLicExpire;
    }

    public String getTaxRegCert() {
        return taxRegCert;
    }

    public void setTaxRegCert(String taxRegCert) {
        this.taxRegCert = taxRegCert;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalPersonCertType() {
        return legalPersonCertType;
    }

    public void setLegalPersonCertType(String legalPersonCertType) {
        this.legalPersonCertType = legalPersonCertType;
    }

    public String getLegalPersonCertNm() {
        return legalPersonCertNm;
    }

    public void setLegalPersonCertNm(String legalPersonCertNm) {
        this.legalPersonCertNm = legalPersonCertNm;
    }

    public String getLegalPersonCertExpire() {
        return legalPersonCertExpire;
    }

    public void setLegalPersonCertExpire(String legalPersonCertExpire) {
        this.legalPersonCertExpire = legalPersonCertExpire;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankProv() {
        return bankProv;
    }

    public void setBankProv(String bankProv) {
        this.bankProv = bankProv;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public String getCnaps() {
        return cnaps;
    }

    public void setCnaps(String cnaps) {
        this.cnaps = cnaps;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getPosDebitFeeRate() {
        return posDebitFeeRate;
    }

    public void setPosDebitFeeRate(String posDebitFeeRate) {
        this.posDebitFeeRate = posDebitFeeRate;
    }

    public String getPosDebitFeeMax() {
        return posDebitFeeMax;
    }

    public void setPosDebitFeeMax(String posDebitFeeMax) {
        this.posDebitFeeMax = posDebitFeeMax;
    }

    public String getPosCreditFeeRate() {
        return posCreditFeeRate;
    }

    public void setPosCreditFeeRate(String posCreditFeeRate) {
        this.posCreditFeeRate = posCreditFeeRate;
    }

    public String getWechatFeeRateT0() {
        return wechatFeeRateT0;
    }

    public void setWechatFeeRateT0(String wechatFeeRateT0) {
        this.wechatFeeRateT0 = wechatFeeRateT0;
    }

    public String getWechatFeeRateT1() {
        return wechatFeeRateT1;
    }

    public void setWechatFeeRateT1(String wechatFeeRateT1) {
        this.wechatFeeRateT1 = wechatFeeRateT1;
    }

    public String getAliPayFeeRateT0() {
        return aliPayFeeRateT0;
    }

    public void setAliPayFeeRateT0(String aliPayFeeRateT0) {
        this.aliPayFeeRateT0 = aliPayFeeRateT0;
    }

    public String getAliPayFeeRateT1() {
        return aliPayFeeRateT1;
    }

    public void setAliPayFeeRateT1(String aliPayFeeRateT1) {
        this.aliPayFeeRateT1 = aliPayFeeRateT1;
    }

    public String getWithdrawalFeeType() {
        return withdrawalFeeType;
    }

    public void setWithdrawalFeeType(String withdrawalFeeType) {
        this.withdrawalFeeType = withdrawalFeeType;
    }

    public String getWithdrawalFee() {
        return withdrawalFee;
    }

    public void setWithdrawalFee(String withdrawalFee) {
        this.withdrawalFee = withdrawalFee;
    }

    public String getWithdrawalFeeRate() {
        return withdrawalFeeRate;
    }

    public void setWithdrawalFeeRate(String withdrawalFeeRate) {
        this.withdrawalFeeRate = withdrawalFeeRate;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getTermProv() {
        return termProv;
    }

    public void setTermProv(String termProv) {
        this.termProv = termProv;
    }

    public String getTermCity() {
        return termCity;
    }

    public void setTermCity(String termCity) {
        this.termCity = termCity;
    }

    public String getTermCounty() {
        return termCounty;
    }

    public void setTermCounty(String termCounty) {
        this.termCounty = termCounty;
    }

    public String getTermAddress() {
        return termAddress;
    }

    public void setTermAddress(String termAddress) {
        this.termAddress = termAddress;
    }

    public String getTermSn() {
        return termSn;
    }

    public void setTermSn(String termSn) {
        this.termSn = termSn;
    }

    public String getMccCode() {
        return mccCode;
    }

    public void setMccCode(String mccCode) {
        this.mccCode = mccCode;
    }

    public String getSettlePeriod() {
        return settlePeriod;
    }

    public void setSettlePeriod(String settlePeriod) {
        this.settlePeriod = settlePeriod;
    }
}
