package com.allcheer.bpos.domain.Minsheng;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "enterRepuest")
public class EnterRepuest {
	@NotNull(message = "商户号不能为空")
	@Length(max = 8, message = "商户名长度超限")
	private String insNum;

	@NotNull(message = "支付类型不能为空")
	@Length(max = 2, message = "支付类型长度超限")
	@Pattern(regexp = "01|02|03|04|05|99", message = "支付类型非法")
	private String payType;

	@NotNull(message = "商户名不能为空")
	@Length(max = 64, message = "商户名长度超限")
	private String merName;

	@NotNull(message = "商户简称不能为空")
	@Length(max = 64, message = "商户简称长度超限")
	private String regShortName;

	@NotNull(message = "商户注册地址不能为空")
	@Length(max = 256, message = "商户注册地址长度超限")
	private String merAddress;

	@NotNull(message = "商户省份代码不能为空")
	@Length(max = 10, message = "商户省份代码长度超限")
	private String merProvince;

	@NotNull(message = "商户城市代码不能为空")
	@Length(max = 10, message = "商户城市代码长度超限")
	private String merCity;

	@NotNull(message = "商户区代码不能为空")
	@Length(max = 10, message = "商户区代码长度超限")
	private String merArea;

	@NotNull(message = "商户状态 不能为空")
	@Length(max = 1, message = "商户状态 长度超限")
	@Pattern(regexp = "0|1", message = "商户状态非法")
	private String merStat;

	@NotNull(message = "功能状态 不能为空")
	@Length(max = 10, message = "功能状态 长度超限")
	@Pattern(regexp = "YYYYYYYYYY", message = "功能状态 非法")
	private String funcStat;

	@NotNull(message = "商户类型 不能为空")
	@Length(max = 1, message = "商户类型 长度超限")
	@Pattern(regexp = "1|2|0", message = "商户类型非法")
	private String merType;

	@NotNull(message = "法人代表不能为空")
	@Length(max = 64, message = "法人代表长度超限")
	private String legalPerson;

	@NotNull(message = "法人证件类型不能为空")
	@Length(max = 1, message = "法人证件类型长度超限")
	@Pattern(regexp = "0", message = "法人证件类型非法")
	private String legalPersonCertType;

	@NotNull(message = "法人证件号不能为空")
	@Length(max = 64, message = "法人证件号长度超限")
	private String legalPersonCertNm;

	@NotNull(message = "法人证件有效期不能为空")
	@Length(min = 8, max = 8, message = "法人证件有效期长度为8位")
	@Pattern(regexp = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)", message = "法人证件有效期非法")
	private String legalPersonCertExpire;

	@NotNull(message = "联系人不能为空")
	@Length(max = 64, message = "联系人长度超限")
	private String contactPerson;

	@NotNull(message = "联系人手机号不能为空")
	@Length(max = 20, message = "联系人手机号长度超限")
	private String contactMobile;

	@NotNull(message = "结算卡姓名不能为空")
	@Length(max = 64, message = "结算卡姓名长度超限")
	private String debitCardName;

	@NotNull(message = "结算卡联行号不能为空")
	@Length(max = 12, message = "结算卡联行号长度超限")
	private String debitCardLines;

	@NotNull(message = "结算卡卡号不能为空")
	@Length(max = 32, message = "结算卡卡号长度超限")
	private String debitCardNum;

	private String WXT0;

	private String ZFBT0;

	private String WXT1;

	private String ZFBT1;

	//T0提现金额不能为空
	private String factorageT0;

	//T1提现金额不能为空
	private String factorageT1;

	@NotNull(message = "开卡行不能为空")
	@Length(max = 64, message = "开卡行长度超限")
	private String bankName;

	@NotNull(message = "开卡省不能为空")
	@Length(max = 64, message = "开卡省长度超限")
	private String provName;

	@NotNull(message = "开卡城市不能为空")
	@Length(max = 64, message = "开卡城市长度超限")
	private String cityName;

	@NotNull(message = "开卡分行不能为空")
	@Length(max = 256, message = "开卡分行长度超限")
	private String bankBranchName;

	@NotNull(message = "结算类型不能为空")
	@Length(max = 1, message = "结算类型长度超限")
	@Pattern(regexp = "Y|N", message = "结算类型非法")
	private String isPrivate;
	
	//借记卡费率
	private String debitCardFee;
	
	//借记卡上限
	private String debitCardMax;
	
	//借记卡费率
	private String creditCardFee;

	//借记卡费率单笔
	private String creditCardFeeFixed;
	
	//营业执照号码
	private String busLincenNumber;
	
	
	//营业执照过期日期
	private String busLincenExpireDate;
	
	//税务注册号码
	private String taxRegCert;	
	
	//快捷支付 T1 交易费用/每笔
	private String qcPayFeeT1;		
	
	//快捷支付 T1 交易费用/比例
	private String qcPayFeeRateT1;		

	//快捷支付 T0 提现费用/每笔
	private String qcWithDrawFeeT0;		
	
	//快捷支付 T0 提现费用/比例
	private String qcWithDrawFeeRateT0;	
	
	//POS入件-机构商户号
	private String outMerId;	
	
	//POS入件-终端号
	private String outTermNo;
	
	//POS入件-终端名称
	private String outTermName;
	
	//POS入件-终端类型
	private String outTermType;
	
	//POS入件-终端安装省市
	private String outTermProv;
	
	//POS入件-终端号安装城市
	private String outTermCity;
	
	//POS入件-终端安装县区
	private String outTermArea;

	//POS入件-终端号详细地址
	private String outTermAddress;

	//POS入件-终端SN号
	private String outTermSN;
	
	/**
	 * @return the insNum
	 */
	public String getInsNum() {
		return insNum;
	}

	/**
	 * @param insNum
	 *            the insNum to set
	 */
	public void setInsNum(String insNum) {
		this.insNum = insNum;
	}

	/**
	 * @return the payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param payType
	 *            the payType to set
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * @return the merName
	 */
	public String getMerName() {
		return merName;
	}

	/**
	 * @param merName
	 *            the merName to set
	 */
	public void setMerName(String merName) {
		this.merName = merName;
	}

	/**
	 * @return the regShortName
	 */
	public String getRegShortName() {
		return regShortName;
	}

	/**
	 * @param regShortName
	 *            the regShortName to set
	 */
	public void setRegShortName(String regShortName) {
		this.regShortName = regShortName;
	}

	/**
	 * @return the merAddress
	 */
	public String getMerAddress() {
		return merAddress;
	}

	/**
	 * @param merAddress
	 *            the merAddress to set
	 */
	public void setMerAddress(String merAddress) {
		this.merAddress = merAddress;
	}

	/**
	 * @return the merProvince
	 */
	public String getMerProvince() {
		return merProvince;
	}

	/**
	 * @param merProvince
	 *            the merProvince to set
	 */
	public void setMerProvince(String merProvince) {
		this.merProvince = merProvince;
	}

	/**
	 * @return the merCity
	 */
	public String getMerCity() {
		return merCity;
	}

	/**
	 * @param merCity
	 *            the merCity to set
	 */
	public void setMerCity(String merCity) {
		this.merCity = merCity;
	}

	/**
	 * @return the merCity
	 */
	public String getMerArea() {
		return merArea;
	}

	/**
	 * @param merCity
	 *            the merCity to set
	 */
	public void setMerArea(String merArea) {
		this.merArea = merArea;
	}

	/**
	 * @return the merStat
	 */
	public String getMerStat() {
		return merStat;
	}

	/**
	 * @param merStat
	 *            the merStat to set
	 */
	public void setMerStat(String merStat) {
		this.merStat = merStat;
	}

	/**
	 * @return the funcStat
	 */
	public String getFuncStat() {
		return funcStat;
	}

	/**
	 * @param funcStat
	 *            the funcStat to set
	 */
	public void setFuncStat(String funcStat) {
		this.funcStat = funcStat;
	}

	/**
	 * @return the merType
	 */
	public String getMerType() {
		return merType;
	}

	/**
	 * @param merType
	 *            the merType to set
	 */
	public void setMerType(String merType) {
		this.merType = merType;
	}

	/**
	 * @return the legalPerson
	 */
	public String getLegalPerson() {
		return legalPerson;
	}

	/**
	 * @param legalPerson
	 *            the legalPerson to set
	 */
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	/**
	 * @return the legalPersonCertType
	 */
	public String getLegalPersonCertType() {
		return legalPersonCertType;
	}

	/**
	 * @param legalPersonCertType
	 *            the legalPersonCertType to set
	 */
	public void setLegalPersonCertType(String legalPersonCertType) {
		this.legalPersonCertType = legalPersonCertType;
	}

	/**
	 * @return the legalPersonCertNm
	 */
	public String getLegalPersonCertNm() {
		return legalPersonCertNm;
	}

	/**
	 * @param legalPersonCertNm
	 *            the legalPersonCertNm to set
	 */
	public void setLegalPersonCertNm(String legalPersonCertNm) {
		this.legalPersonCertNm = legalPersonCertNm;
	}

	/**
	 * @return the legalPersonCertExpire
	 */
	public String getLegalPersonCertExpire() {
		return legalPersonCertExpire;
	}

	/**
	 * @param legalPersonCertExpire
	 *            the legalPersonCertExpire to set
	 */
	public void setLegalPersonCertExpire(String legalPersonCertExpire) {
		this.legalPersonCertExpire = legalPersonCertExpire;
	}

	/**
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return contactPerson;
	}

	/**
	 * @param contactPerson
	 *            the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	/**
	 * @return the contactMobile
	 */
	public String getContactMobile() {
		return contactMobile;
	}

	/**
	 * @param contactMobile
	 *            the contactMobile to set
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	/**
	 * @return the debitCardName
	 */
	public String getDebitCardName() {
		return debitCardName;
	}

	/**
	 * @param debitCardName
	 *            the debitCardName to set
	 */
	public void setDebitCardName(String debitCardName) {
		this.debitCardName = debitCardName;
	}

	/**
	 * @return the debitCardLines
	 */
	public String getDebitCardLines() {
		return debitCardLines;
	}

	/**
	 * @param debitCardLines
	 *            the debitCardLines to set
	 */
	public void setDebitCardLines(String debitCardLines) {
		this.debitCardLines = debitCardLines;
	}

	/**
	 * @return the debitCardNum
	 */
	public String getDebitCardNum() {
		return debitCardNum;
	}

	/**
	 * @param debitCardNum
	 *            the debitCardNum to set
	 */
	public void setDebitCardNum(String debitCardNum) {
		this.debitCardNum = debitCardNum;
	}

	/**
	 * @return the wXT0
	 */
	public String getWXT0() {
		return WXT0;
	}

	/**
	 * @param wXT0
	 *            the wXT0 to set
	 */
	public void setWXT0(String wXT0) {
		WXT0 = wXT0;
	}

	/**
	 * @return the zFBT0
	 */
	public String getZFBT0() {
		return ZFBT0;
	}

	/**
	 * @param zFBT0
	 *            the zFBT0 to set
	 */
	public void setZFBT0(String zFBT0) {
		ZFBT0 = zFBT0;
	}

	/**
	 * @return the wXT1
	 */
	public String getWXT1() {
		return WXT1;
	}

	/**
	 * @param wXT1
	 *            the wXT1 to set
	 */
	public void setWXT1(String wXT1) {
		WXT1 = wXT1;
	}

	/**
	 * @return the zFBT1
	 */
	public String getZFBT1() {
		return ZFBT1;
	}

	/**
	 * @param zFBT1
	 *            the zFBT1 to set
	 */
	public void setZFBT1(String zFBT1) {
		ZFBT1 = zFBT1;
	}

	/**
	 * @return the factorageT0
	 */
	public String getFactorageT0() {
		return factorageT0;
	}

	/**
	 * @param factorageT0
	 *            the factorageT0 to set
	 */
	public void setFactorageT0(String factorageT0) {
		this.factorageT0 = factorageT0;
	}

	/**
	 * @return the factorageT1
	 */
	public String getFactorageT1() {
		return factorageT1;
	}

	/**
	 * @param factorageT1
	 *            the factorageT1 to set
	 */
	public void setFactorageT1(String factorageT1) {
		this.factorageT1 = factorageT1;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the provName
	 */
	public String getProvName() {
		return provName;
	}

	/**
	 * @param provName
	 *            the provName to set
	 */
	public void setProvName(String provName) {
		this.provName = provName;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the bankBranchName
	 */
	public String getBankBranchName() {
		return bankBranchName;
	}

	/**
	 * @param bankBranchName
	 *            the bankBranchName to set
	 */
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	/**
	 * @return the isPrivate
	 */
	public String getIsPrivate() {
		return isPrivate;
	}

	/**
	 * @param isPrivate
	 *            the isPrivate to set
	 */
	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}

	/**
	 * @return the debitCardFee
	 */
	public String getDebitCardFee() {
		return debitCardFee;
	}

	/**
	 * @param debitCardFee the debitCardFee to set
	 */
	public void setDebitCardFee(String debitCardFee) {
		this.debitCardFee = debitCardFee;
	}

	/**
	 * @return the debitCardMax
	 */
	public String getDebitCardMax() {
		return debitCardMax;
	}

	/**
	 * @param debitCardMax the debitCardMax to set
	 */
	public void setDebitCardMax(String debitCardMax) {
		this.debitCardMax = debitCardMax;
	}

	/**
	 * @return the creditCardFee
	 */
	public String getCreditCardFee() {
		return creditCardFee;
	}

	/**
	 * @param creditCardFee the creditCardFee to set
	 */
	public void setCreditCardFee(String creditCardFee) {
		this.creditCardFee = creditCardFee;
	}

	/**
	 * @return the busLincenNumber
	 */
	public String getBusLincenNumber() {
		return busLincenNumber;
	}

	/**
	 * @param busLincenNumber the busLincenNumber to set
	 */
	public void setBusLincenNumber(String busLincenNumber) {
		this.busLincenNumber = busLincenNumber;
	}

	/**
	 * @return the busLincenExpireDate
	 */
	public String getBusLincenExpireDate() {
		return busLincenExpireDate;
	}

	/**
	 * @param busLincenExpireDate the busLincenExpireDate to set
	 */
	public void setBusLincenExpireDate(String busLincenExpireDate) {
		this.busLincenExpireDate = busLincenExpireDate;
	}

	/**
	 * @return the taxRegCert
	 */
	public String getTaxRegCert() {
		return taxRegCert;
	}

	/**
	 * @param taxRegCert the taxRegCert to set
	 */
	public void setTaxRegCert(String taxRegCert) {
		this.taxRegCert = taxRegCert;
	}

	/**
	 * @return the creditCardFeeFixed
	 */
	public String getCreditCardFeeFixed() {
		return creditCardFeeFixed;
	}

	/**
	 * @param creditCardFeeFixed the creditCardFeeFixed to set
	 */
	public void setCreditCardFeeFixed(String creditCardFeeFixed) {
		this.creditCardFeeFixed = creditCardFeeFixed;
	}

	/**
	 * @return the qcPayFeeT1
	 */
	public String getQcPayFeeT1() {
		return qcPayFeeT1;
	}

	/**
	 * @param qcPayFeeT1 the qcPayFeeT1 to set
	 */
	public void setQcPayFeeT1(String qcPayFeeT1) {
		this.qcPayFeeT1 = qcPayFeeT1;
	}

	/**
	 * @return the qcPayFeeRateT1
	 */
	public String getQcPayFeeRateT1() {
		return qcPayFeeRateT1;
	}

	/**
	 * @param qcPayFeeRateT1 the qcPayFeeRateT1 to set
	 */
	public void setQcPayFeeRateT1(String qcPayFeeRateT1) {
		this.qcPayFeeRateT1 = qcPayFeeRateT1;
	}

	/**
	 * @return the qcWithDrawFeeT0
	 */
	public String getQcWithDrawFeeT0() {
		return qcWithDrawFeeT0;
	}

	/**
	 * @param qcWithDrawFeeT0 the qcWithDrawFeeT0 to set
	 */
	public void setQcWithDrawFeeT0(String qcWithDrawFeeT0) {
		this.qcWithDrawFeeT0 = qcWithDrawFeeT0;
	}

	/**
	 * @return the qcWithDrawFeeRateT0
	 */
	public String getQcWithDrawFeeRateT0() {
		return qcWithDrawFeeRateT0;
	}

	/**
	 * @param qcWithDrawFeeRateT0 the qcWithDrawFeeRateT0 to set
	 */
	public void setQcWithDrawFeeRateT0(String qcWithDrawFeeRateT0) {
		this.qcWithDrawFeeRateT0 = qcWithDrawFeeRateT0;
	}

	/**
	 * @return the outTermNo
	 */
	public String getOutTermNo() {
		return outTermNo;
	}

	/**
	 * @param outTermNo the outTermNo to set
	 */
	public void setOutTermNo(String outTermNo) {
		this.outTermNo = outTermNo;
	}

	/**
	 * @return the outTermName
	 */
	public String getOutTermName() {
		return outTermName;
	}

	/**
	 * @param outTermName the outTermName to set
	 */
	public void setOutTermName(String outTermName) {
		this.outTermName = outTermName;
	}

	/**
	 * @return the outTermType
	 */
	public String getOutTermType() {
		return outTermType;
	}

	/**
	 * @param outTermType the outTermType to set
	 */
	public void setOutTermType(String outTermType) {
		this.outTermType = outTermType;
	}

	/**
	 * @return the outTermProv
	 */
	public String getOutTermProv() {
		return outTermProv;
	}

	/**
	 * @param outTermProv the outTermProv to set
	 */
	public void setOutTermProv(String outTermProv) {
		this.outTermProv = outTermProv;
	}

	/**
	 * @return the outTermCity
	 */
	public String getOutTermCity() {
		return outTermCity;
	}

	/**
	 * @param outTermCity the outTermCity to set
	 */
	public void setOutTermCity(String outTermCity) {
		this.outTermCity = outTermCity;
	}

	/**
	 * @return the outTermArea
	 */
	public String getOutTermArea() {
		return outTermArea;
	}

	/**
	 * @param outTermArea the outTermArea to set
	 */
	public void setOutTermArea(String outTermArea) {
		this.outTermArea = outTermArea;
	}

	/**
	 * @return the outTermAddress
	 */
	public String getOutTermAddress() {
		return outTermAddress;
	}

	/**
	 * @param outTermAddress the outTermAddress to set
	 */
	public void setOutTermAddress(String outTermAddress) {
		this.outTermAddress = outTermAddress;
	}

	/**
	 * @return the outTermSN
	 */
	public String getOutTermSN() {
		return outTermSN;
	}

	/**
	 * @param outTermSN the outTermSN to set
	 */
	public void setOutTermSN(String outTermSN) {
		this.outTermSN = outTermSN;
	}

	/**
	 * @return the outMerId
	 */
	public String getOutMerId() {
		return outMerId;
	}

	/**
	 * @param outMerId the outMerId to set
	 */
	public void setOutMerId(String outMerId) {
		this.outMerId = outMerId;
	}

	
}
