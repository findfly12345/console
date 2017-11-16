package com.allcheer.bpos.form;

import com.allcheer.bpos.entity.TblAgentInfoDo;
import com.allcheer.bpos.entity.TblBtsInstDO;
import java.util.List;

/**
 * Created by APPLE on 2016/10/21.
 */
public class MerInfoForm extends BaseForm {

	private String merId;

	private String merName;

	private String instId;

	private String instMerId;

	private String instMerName;

	private String instTermId;

	private String channelMerId;

	private String channelTermId;

	private String channelStat;

	private String startTime;

	private String endTime;

	private String channelId;

	private String merStat;

	private String agentId;

	private String agentShortName;

	private String registedMinSheng;

	private List<TblBtsInstDO> instDOList;

	private List<TblAgentInfoDo> tblAgentInfoDos;

	public List<TblAgentInfoDo> getTblAgentInfoDos() {
		return tblAgentInfoDos;
	}

	/**
	 * 无卡渠道报备状态
	 */
	private String quickStatus;

	/**
	 * 标记快捷
	 */
	private String quickMark;

	private String provinceCode;
	
	private String cityCode;
	
	private String areaCode;
	
	private String merType;	
	
	private String regName;	
	
	private String regShortName;	
	
	private String merAddress;
	
	private  String busLicNm;
	
	private  String busLicExpire;
	
	private  String legalPerson;
	
	private  String legalPersonCertType;
	
	private  String legalPersonCertNm;
	
	private  String legalPersonCertExpire;
	
	private  String contactPerson;
	
	private  String contactMobile;
	
	private  String contactEmail;
	
	private  String bankName;
	
	private  String provName;
	
	private  String cityName;
	
	private  String bankBranchName;
	
	private  String cnaps;
	
	private  String isPrivate;
	
	private  String acctName;
	
	private  String acctNo;
	
	private  String fee01;
	
	private  String fee01L;
	
	private  String fee02;
	
	private  String fee03;
	
	private  String fee04;
	
	private  String fee05;
	
	private  String fee06;
	
	private  String fee07;
	
	private  String fee08;
	
	private  String fee09;

    private String feeQ1;
    
    private  String feeQ2;
    
    private  String feeQ3;
    
    private  String feeQ4;    
	
	private  String userName;
		
	public void setTblAgentInfoDos(List<TblAgentInfoDo> tblAgentInfoDos) {
		this.tblAgentInfoDos = tblAgentInfoDos;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
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

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getInstMerId() {
		return instMerId;
	}

	public void setInstMerId(String instMerId) {
		this.instMerId = instMerId;
	}

	public String getInstMerName() {
		return instMerName;
	}

	public void setInstMerName(String instMerName) {
		this.instMerName = instMerName;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getInstTermId() {
		return instTermId;
	}

	public void setInstTermId(String instTermId) {
		this.instTermId = instTermId;
	}

	public List<TblBtsInstDO> getInstDOList() {
		return instDOList;
	}

	public void setInstDOList(List<TblBtsInstDO> instDOList) {
		this.instDOList = instDOList;
	}

	public String getChannelMerId() {
		return channelMerId;
	}

	public void setChannelMerId(String channelMerId) {
		this.channelMerId = channelMerId;
	}

	public String getChannelTermId() {
		return channelTermId;
	}

	public void setChannelTermId(String channelTermId) {
		this.channelTermId = channelTermId;
	}

	public String getChannelStat() {
		return channelStat;
	}

	public void setChannelStat(String channelStat) {
		this.channelStat = channelStat;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMerStat() {
		return merStat;
	}

	public void setMerStat(String merStat) {
		this.merStat = merStat;
	}

	/**
	 * @return the agentShortName
	 */
	public String getAgentShortName() {
		return agentShortName;
	}

	/**
	 * @param agentShortName
	 *            the agentShortName to set
	 */
	public void setAgentShortName(String agentShortName) {
		this.agentShortName = agentShortName;
	}

	/**
	 * @return the registedMinSheng
	 */
	public String getRegistedMinSheng() {
		return registedMinSheng;
	}

	/**
	 * @param registedMinSheng
	 *            the registedMinSheng to set
	 */
	public void setRegistedMinSheng(String registedMinSheng) {
		this.registedMinSheng = registedMinSheng;
	}

	/**
	 * @return the quickStatus
	 */
	public String getQuickStatus() {
		return quickStatus;
	}

	/**
	 * @param quickStatus
	 *            the quickStatus to set
	 */
	public void setQuickStatus(String quickStatus) {
		this.quickStatus = quickStatus;
	}

	/**
	 * @return the quickMark
	 */
	public String getQuickMark() {
		return quickMark;
	}

	/**
	 * @param quickMark
	 *            the quickMark to set
	 */
	public void setQuickMark(String quickMark) {
		this.quickMark = quickMark;
	}

	/**
	 * @return the provinceCode
	 */
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * @param provinceCode the provinceCode to set
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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
	 * @return the merAddress
	 */
	public String getMerAddress() {
		return merAddress;
	}

	/**
	 * @param merAddress the merAddress to set
	 */
	public void setMerAddress(String merAddress) {
		this.merAddress = merAddress;
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
	 * @return the provName
	 */
	public String getProvName() {
		return provName;
	}

	/**
	 * @param provName the provName to set
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
	 * @param cityName the cityName to set
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
	 * @return the fee01
	 */
	public String getFee01() {
		return fee01;
	}

	/**
	 * @param fee01 the fee01 to set
	 */
	public void setFee01(String fee01) {
		this.fee01 = fee01;
	}

	/**
	 * @return the fee01L
	 */
	public String getFee01L() {
		return fee01L;
	}

	/**
	 * @param fee01l the fee01L to set
	 */
	public void setFee01L(String fee01l) {
		fee01L = fee01l;
	}

	/**
	 * @return the fee02
	 */
	public String getFee02() {
		return fee02;
	}

	/**
	 * @param fee02 the fee02 to set
	 */
	public void setFee02(String fee02) {
		this.fee02 = fee02;
	}

	/**
	 * @return the fee03
	 */
	public String getFee03() {
		return fee03;
	}

	/**
	 * @param fee03 the fee03 to set
	 */
	public void setFee03(String fee03) {
		this.fee03 = fee03;
	}

	/**
	 * @return the fee04
	 */
	public String getFee04() {
		return fee04;
	}

	/**
	 * @param fee04 the fee04 to set
	 */
	public void setFee04(String fee04) {
		this.fee04 = fee04;
	}

	/**
	 * @return the fee05
	 */
	public String getFee05() {
		return fee05;
	}

	/**
	 * @param fee05 the fee05 to set
	 */
	public void setFee05(String fee05) {
		this.fee05 = fee05;
	}

	/**
	 * @return the fee06
	 */
	public String getFee06() {
		return fee06;
	}

	/**
	 * @param fee06 the fee06 to set
	 */
	public void setFee06(String fee06) {
		this.fee06 = fee06;
	}

	/**
	 * @return the fee07
	 */
	public String getFee07() {
		return fee07;
	}

	/**
	 * @param fee07 the fee07 to set
	 */
	public void setFee07(String fee07) {
		this.fee07 = fee07;
	}

	/**
	 * @return the fee08
	 */
	public String getFee08() {
		return fee08;
	}

	/**
	 * @param fee08 the fee08 to set
	 */
	public void setFee08(String fee08) {
		this.fee08 = fee08;
	}

	/**
	 * @return the fee09
	 */
	public String getFee09() {
		return fee09;
	}

	/**
	 * @param fee09 the fee09 to set
	 */
	public void setFee09(String fee09) {
		this.fee09 = fee09;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the feeQ1
	 */
	public String getFeeQ1() {
		return feeQ1;
	}

	/**
	 * @param feeQ1 the feeQ1 to set
	 */
	public void setFeeQ1(String feeQ1) {
		this.feeQ1 = feeQ1;
	}

	/**
	 * @return the feeQ2
	 */
	public String getFeeQ2() {
		return feeQ2;
	}

	/**
	 * @param feeQ2 the feeQ2 to set
	 */
	public void setFeeQ2(String feeQ2) {
		this.feeQ2 = feeQ2;
	}

	/**
	 * @return the feeQ3
	 */
	public String getFeeQ3() {
		return feeQ3;
	}

	/**
	 * @param feeQ3 the feeQ3 to set
	 */
	public void setFeeQ3(String feeQ3) {
		this.feeQ3 = feeQ3;
	}

	/**
	 * @return the feeQ4
	 */
	public String getFeeQ4() {
		return feeQ4;
	}

	/**
	 * @param feeQ4 the feeQ4 to set
	 */
	public void setFeeQ4(String feeQ4) {
		this.feeQ4 = feeQ4;
	}

	
	
}
