package com.allcheer.bpos.entity.dao;

/**
 */
public class AddInstMerBO {

	private String instId;                          // 合作机构号                                          
	private String merId;                          // 商户号                                               
	private String merName;                          // 商户名                                             
	private String merType;                          // 商户类型                                           
	private String regName;                           // 注册名                                            
	private String regShortName;                          // 注册简称                                      
	private String regAddress;                          // 注册地址                                        
	private String regFunds;                          // 注册资本                                          
	private String busLicNm;                          // 营业执照编号                                      
	private String busLicExpire;                          // 营业执照有效期                                
	private String taxRegCert;                           // 税务登记证                                     
	private String legalPerson;                           // 法人代表                                      
	private String legalPersonCertType;                           // 法人代表证件类型                      
	private String legalPersonCertNm;                           // 法人代表证件号                          
	private String legalPersonCertExpire;                           // 法人代表证件号有效期                
	private String contactPerson;                           // 联系人                                      
	private String contactMobile;                           // 联系号码                                    
	private String contactEmail;                            // 联系邮箱                                    
	private String bankName;                           // 开户行                                           
	private String bankProv;                           // 开户行省                                         
	private String bankCity;                           // 开户行市                                         
	private String bankBranchName;                           // 开户支行                                   
	private String cnaps;                           // 联行号                                              
	private String isPrivate;                           // 是否对私账户                                    
	private String acctName;                           // 账户名                                           
	private String acctNo;                           // 账户号                                             
	private String posDebitFeeRate;                           // POS借记卡-比例                            
	private String posDebitFeeMax;                           // POS借记卡-封顶                             
	private String posCreditFeeRate;                           // POS贷记卡-比例                           
	private String wechatFeeRateT0;                           // 微信T0交易费率                            
	private String wechatFeeRateT1;                           // 微信T1交易费率                            
	private String aliPayFeeRateT0;                           // 微信T0交易费率                            
	private String aliPayFeeRateT1;                           // 微信T1交易费率                            
	private String withdrawalFeeType;                           // 提现手续费类型                          
	private String withdrawalFee;                           // 提现手续费                                  
	private String withdrawalFeeRate;                           // 垫资手续费                              
	private String termId;                           // 终端号                                             
	private String termName;                           // 终端名称                                         
	private String termType;                           // 终端类型                                         
	private String termProv;                           // 终端安装省                                       
	private String termCity;                           // 终端安装市                                       
	private String termCounty;                           // 终端安装县                                     
	private String termAddress;                           // 终端安装详细地址                              
	private String termSn;                           // 终端SN号                                           
	private String mccCode;                           // MCC码                                             
	private String settlePeriod;                           // 结算周期                                     
	private String merProvinceCode;                           // 商户地址省份编码                          
	private String merCityCode;                           // 商户地址城市编码                              
	private String merAreaCode;                            // 商户地址区/县编码          
	
	/**
	 * @return the instId
	 */
	public String getInstId() {
		return instId;
	}
	/**
	 * @param instId the instId to set
	 */
	public void setInstId(String instId) {
		this.instId = instId;
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
	 * @return the merType
	 */
	public String getMerType() {
		return merType;
	}
	/**
	 * @param merType the merType to set
	 */
	public void setMerType(String merType) {
		this.merType = merType;
	}
	/**
	 * @return the regName
	 */
	public String getRegName() {
		return regName;
	}
	/**
	 * @param regName the regName to set
	 */
	public void setRegName(String regName) {
		this.regName = regName;
	}
	/**
	 * @return the regShortName
	 */
	public String getRegShortName() {
		return regShortName;
	}
	/**
	 * @param regShortName the regShortName to set
	 */
	public void setRegShortName(String regShortName) {
		this.regShortName = regShortName;
	}
	/**
	 * @return the regAddress
	 */
	public String getRegAddress() {
		return regAddress;
	}
	/**
	 * @param regAddress the regAddress to set
	 */
	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}
	/**
	 * @return the regFunds
	 */
	public String getRegFunds() {
		return regFunds;
	}
	/**
	 * @param regFunds the regFunds to set
	 */
	public void setRegFunds(String regFunds) {
		this.regFunds = regFunds;
	}
	/**
	 * @return the busLicNm
	 */
	public String getBusLicNm() {
		return busLicNm;
	}
	/**
	 * @param busLicNm the busLicNm to set
	 */
	public void setBusLicNm(String busLicNm) {
		this.busLicNm = busLicNm;
	}
	/**
	 * @return the busLicExpire
	 */
	public String getBusLicExpire() {
		return busLicExpire;
	}
	/**
	 * @param busLicExpire the busLicExpire to set
	 */
	public void setBusLicExpire(String busLicExpire) {
		this.busLicExpire = busLicExpire;
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
	 * @return the legalPerson
	 */
	public String getLegalPerson() {
		return legalPerson;
	}
	/**
	 * @param legalPerson the legalPerson to set
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
	 * @param legalPersonCertType the legalPersonCertType to set
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
	 * @param legalPersonCertNm the legalPersonCertNm to set
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
	 * @param legalPersonCertExpire the legalPersonCertExpire to set
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
	 * @param contactPerson the contactPerson to set
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
	 * @param contactMobile the contactMobile to set
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}
	/**
	 * @param contactEmail the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the bankProv
	 */
	public String getBankProv() {
		return bankProv;
	}
	/**
	 * @param bankProv the bankProv to set
	 */
	public void setBankProv(String bankProv) {
		this.bankProv = bankProv;
	}
	/**
	 * @return the bankCity
	 */
	public String getBankCity() {
		return bankCity;
	}
	/**
	 * @param bankCity the bankCity to set
	 */
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	/**
	 * @return the bankBranchName
	 */
	public String getBankBranchName() {
		return bankBranchName;
	}
	/**
	 * @param bankBranchName the bankBranchName to set
	 */
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	/**
	 * @return the cnaps
	 */
	public String getCnaps() {
		return cnaps;
	}
	/**
	 * @param cnaps the cnaps to set
	 */
	public void setCnaps(String cnaps) {
		this.cnaps = cnaps;
	}
	/**
	 * @return the isPrivate
	 */
	public String getIsPrivate() {
		return isPrivate;
	}
	/**
	 * @param isPrivate the isPrivate to set
	 */
	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}
	/**
	 * @return the acctName
	 */
	public String getAcctName() {
		return acctName;
	}
	/**
	 * @param acctName the acctName to set
	 */
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	/**
	 * @return the acctNo
	 */
	public String getAcctNo() {
		return acctNo;
	}
	/**
	 * @param acctNo the acctNo to set
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	/**
	 * @return the posDebitFeeRate
	 */
	public String getPosDebitFeeRate() {
		return posDebitFeeRate;
	}
	/**
	 * @param posDebitFeeRate the posDebitFeeRate to set
	 */
	public void setPosDebitFeeRate(String posDebitFeeRate) {
		this.posDebitFeeRate = posDebitFeeRate;
	}
	/**
	 * @return the posDebitFeeMax
	 */
	public String getPosDebitFeeMax() {
		return posDebitFeeMax;
	}
	/**
	 * @param posDebitFeeMax the posDebitFeeMax to set
	 */
	public void setPosDebitFeeMax(String posDebitFeeMax) {
		this.posDebitFeeMax = posDebitFeeMax;
	}
	/**
	 * @return the posCreditFeeRate
	 */
	public String getPosCreditFeeRate() {
		return posCreditFeeRate;
	}
	/**
	 * @param posCreditFeeRate the posCreditFeeRate to set
	 */
	public void setPosCreditFeeRate(String posCreditFeeRate) {
		this.posCreditFeeRate = posCreditFeeRate;
	}
	/**
	 * @return the wechatFeeRateT0
	 */
	public String getWechatFeeRateT0() {
		return wechatFeeRateT0;
	}
	/**
	 * @param wechatFeeRateT0 the wechatFeeRateT0 to set
	 */
	public void setWechatFeeRateT0(String wechatFeeRateT0) {
		this.wechatFeeRateT0 = wechatFeeRateT0;
	}
	/**
	 * @return the wechatFeeRateT1
	 */
	public String getWechatFeeRateT1() {
		return wechatFeeRateT1;
	}
	/**
	 * @param wechatFeeRateT1 the wechatFeeRateT1 to set
	 */
	public void setWechatFeeRateT1(String wechatFeeRateT1) {
		this.wechatFeeRateT1 = wechatFeeRateT1;
	}
	/**
	 * @return the aliPayFeeRateT0
	 */
	public String getAliPayFeeRateT0() {
		return aliPayFeeRateT0;
	}
	/**
	 * @param aliPayFeeRateT0 the aliPayFeeRateT0 to set
	 */
	public void setAliPayFeeRateT0(String aliPayFeeRateT0) {
		this.aliPayFeeRateT0 = aliPayFeeRateT0;
	}
	/**
	 * @return the aliPayFeeRateT1
	 */
	public String getAliPayFeeRateT1() {
		return aliPayFeeRateT1;
	}
	/**
	 * @param aliPayFeeRateT1 the aliPayFeeRateT1 to set
	 */
	public void setAliPayFeeRateT1(String aliPayFeeRateT1) {
		this.aliPayFeeRateT1 = aliPayFeeRateT1;
	}
	/**
	 * @return the withdrawalFeeType
	 */
	public String getWithdrawalFeeType() {
		return withdrawalFeeType;
	}
	/**
	 * @param withdrawalFeeType the withdrawalFeeType to set
	 */
	public void setWithdrawalFeeType(String withdrawalFeeType) {
		this.withdrawalFeeType = withdrawalFeeType;
	}
	/**
	 * @return the withdrawalFee
	 */
	public String getWithdrawalFee() {
		return withdrawalFee;
	}
	/**
	 * @param withdrawalFee the withdrawalFee to set
	 */
	public void setWithdrawalFee(String withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
	}
	/**
	 * @return the withdrawalFeeRate
	 */
	public String getWithdrawalFeeRate() {
		return withdrawalFeeRate;
	}
	/**
	 * @param withdrawalFeeRate the withdrawalFeeRate to set
	 */
	public void setWithdrawalFeeRate(String withdrawalFeeRate) {
		this.withdrawalFeeRate = withdrawalFeeRate;
	}
	/**
	 * @return the termId
	 */
	public String getTermId() {
		return termId;
	}
	/**
	 * @param termId the termId to set
	 */
	public void setTermId(String termId) {
		this.termId = termId;
	}
	/**
	 * @return the termName
	 */
	public String getTermName() {
		return termName;
	}
	/**
	 * @param termName the termName to set
	 */
	public void setTermName(String termName) {
		this.termName = termName;
	}
	/**
	 * @return the termType
	 */
	public String getTermType() {
		return termType;
	}
	/**
	 * @param termType the termType to set
	 */
	public void setTermType(String termType) {
		this.termType = termType;
	}
	/**
	 * @return the termProv
	 */
	public String getTermProv() {
		return termProv;
	}
	/**
	 * @param termProv the termProv to set
	 */
	public void setTermProv(String termProv) {
		this.termProv = termProv;
	}
	/**
	 * @return the termCity
	 */
	public String getTermCity() {
		return termCity;
	}
	/**
	 * @param termCity the termCity to set
	 */
	public void setTermCity(String termCity) {
		this.termCity = termCity;
	}
	/**
	 * @return the termCounty
	 */
	public String getTermCounty() {
		return termCounty;
	}
	/**
	 * @param termCounty the termCounty to set
	 */
	public void setTermCounty(String termCounty) {
		this.termCounty = termCounty;
	}
	/**
	 * @return the termAddress
	 */
	public String getTermAddress() {
		return termAddress;
	}
	/**
	 * @param termAddress the termAddress to set
	 */
	public void setTermAddress(String termAddress) {
		this.termAddress = termAddress;
	}
	/**
	 * @return the termSn
	 */
	public String getTermSn() {
		return termSn;
	}
	/**
	 * @param termSn the termSn to set
	 */
	public void setTermSn(String termSn) {
		this.termSn = termSn;
	}
	/**
	 * @return the mccCode
	 */
	public String getMccCode() {
		return mccCode;
	}
	/**
	 * @param mccCode the mccCode to set
	 */
	public void setMccCode(String mccCode) {
		this.mccCode = mccCode;
	}
	/**
	 * @return the settlePeriod
	 */
	public String getSettlePeriod() {
		return settlePeriod;
	}
	/**
	 * @param settlePeriod the settlePeriod to set
	 */
	public void setSettlePeriod(String settlePeriod) {
		this.settlePeriod = settlePeriod;
	}
	/**
	 * @return the merProvinceCode
	 */
	public String getMerProvinceCode() {
		return merProvinceCode;
	}
	/**
	 * @param merProvinceCode the merProvinceCode to set
	 */
	public void setMerProvinceCode(String merProvinceCode) {
		this.merProvinceCode = merProvinceCode;
	}
	/**
	 * @return the merCityCode
	 */
	public String getMerCityCode() {
		return merCityCode;
	}
	/**
	 * @param merCityCode the merCityCode to set
	 */
	public void setMerCityCode(String merCityCode) {
		this.merCityCode = merCityCode;
	}
	/**
	 * @return the merAreaCode
	 */
	public String getMerAreaCode() {
		return merAreaCode;
	}
	/**
	 * @param merAreaCode the merAreaCode to set
	 */
	public void setMerAreaCode(String merAreaCode) {
		this.merAreaCode = merAreaCode;
	}
}
