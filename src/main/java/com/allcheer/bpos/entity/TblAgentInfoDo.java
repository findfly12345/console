package com.allcheer.bpos.entity;

import com.allcheer.bpos.form.BaseForm;

public class TblAgentInfoDo extends BaseForm {

    private String auditId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGNET_AUDIT_RECORD.MEMBER_ID
     *
     * @mbggenerated
     */

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGNET_AUDIT_RECORD.CREATE_TIME
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGNET_AUDIT_RECORD.USER_NAME
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGNET_AUDIT_RECORD.ERROR_FIELD
     *
     * @mbggenerated
     */
    private String errorField;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGNET_AUDIT_RECORD.REMARK
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGNET_AUDIT_RECORD.AUDIT_RESULT
     *
     * @mbggenerated
     */
    private String auditResult;

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getErrorField() {
        return errorField;
    }

    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String fee01L;
    public String fee01;
    public String fee02;
    public String fee03;
    public String fee04;
    public String fee05;
    public String fee06;
    public String fee07;
    public String fee08;

    private TblAgentAuditRecordDo tblAgentAuditRecordDo;

    public TblAgentAuditRecordDo getTblAgentAuditRecordDo() {
        return tblAgentAuditRecordDo;
    }

    public void setTblAgentAuditRecordDo(TblAgentAuditRecordDo tblAgentAuditRecordDo) {
        this.tblAgentAuditRecordDo = tblAgentAuditRecordDo;
    }

    public String getFee08() {
        return fee08;
    }

    public void setFee08(String fee08) {
        this.fee08 = fee08;
    }

    public String getFee01L() {
        return fee01L;
    }

    public void setFee01L(String fee01L) {
        this.fee01L = fee01L;
    }

    public String getFee01() {
        return fee01;
    }

    public void setFee01(String fee01) {
        this.fee01 = fee01;
    }

    public String getFee02() {
        return fee02;
    }

    public void setFee02(String fee02) {
        this.fee02 = fee02;
    }

    public String getFee03() {
        return fee03;
    }

    public void setFee03(String fee03) {
        this.fee03 = fee03;
    }

    public String getFee04() {
        return fee04;
    }

    public void setFee04(String fee04) {
        this.fee04 = fee04;
    }

    public String getFee05() {
        return fee05;
    }

    public void setFee05(String fee05) {
        this.fee05 = fee05;
    }

    public String getFee06() {
        return fee06;
    }

    public void setFee06(String fee06) {
        this.fee06 = fee06;
    }

    public String getFee07() {
        return fee07;
    }

    public void setFee07(String fee07) {
        this.fee07 = fee07;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.MEMBER_ID
     *
     * @mbggenerated
     */
    private String memberId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.AGENT_NAME
     *
     * @mbggenerated
     */
    private String agentName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.AGENT_SHORT_NAME
     *
     * @mbggenerated
     */
    private String agentShortName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.OEM_ID
     *
     * @mbggenerated
     */
    private String oemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.UP_AGENT_ID
     *
     * @mbggenerated
     */
    private String upAgentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.REG_NAME
     *
     * @mbggenerated
     */
    private String regName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.REG_CODE
     *
     * @mbggenerated
     */
    private String regCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.TAX_CODE
     *
     * @mbggenerated
     */
    private String taxCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.REG_ADDR
     *
     * @mbggenerated
     */
    private String regAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.LIC_TYPE
     *
     * @mbggenerated
     */
    private String licType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.LIC_AMT
     *
     * @mbggenerated
     */
    private String licAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.LEGAL_NAME
     *
     * @mbggenerated
     */
    private String legalName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.IDTYPE
     *
     * @mbggenerated
     */
    private String idtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.IDNO
     *
     * @mbggenerated
     */
    private String idno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.CONTACT_NAME
     *
     * @mbggenerated
     */
    private String contactName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.CONTACT_MOBILE
     *
     * @mbggenerated
     */
    private String contactMobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.CONTACT_ADDR
     *
     * @mbggenerated
     */
    private String contactAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.ZIP_CODE
     *
     * @mbggenerated
     */
    private String zipCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.EMAIL
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.TELENO
     *
     * @mbggenerated
     */
    private String teleno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.SIGN_NAME
     *
     * @mbggenerated
     */
    private String signName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.SIGN_DATE
     *
     * @mbggenerated
     */
    private String signDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.REC_CRET_DTTM
     *
     * @mbggenerated
     */
    private String recCretDttm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.LST_UPD_DTTM
     *
     * @mbggenerated
     */
    private String lstUpdDttm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_AGENT_INFO.AGENT_STATUS
     *
     * @mbggenerated
     */
    private String agentStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.MEMBER_ID
     *
     * @return the value of TBL_AGENT_INFO.MEMBER_ID
     *
     * @mbggenerated
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.MEMBER_ID
     *
     * @param memberId the value for TBL_AGENT_INFO.MEMBER_ID
     *
     * @mbggenerated
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.AGENT_NAME
     *
     * @return the value of TBL_AGENT_INFO.AGENT_NAME
     *
     * @mbggenerated
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.AGENT_NAME
     *
     * @param agentName the value for TBL_AGENT_INFO.AGENT_NAME
     *
     * @mbggenerated
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.AGENT_SHORT_NAME
     *
     * @return the value of TBL_AGENT_INFO.AGENT_SHORT_NAME
     *
     * @mbggenerated
     */
    public String getAgentShortName() {
        return agentShortName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.AGENT_SHORT_NAME
     *
     * @param agentShortName the value for TBL_AGENT_INFO.AGENT_SHORT_NAME
     *
     * @mbggenerated
     */
    public void setAgentShortName(String agentShortName) {
        this.agentShortName = agentShortName == null ? null : agentShortName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.OEM_ID
     *
     * @return the value of TBL_AGENT_INFO.OEM_ID
     *
     * @mbggenerated
     */
    public String getOemId() {
        return oemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.OEM_ID
     *
     * @param oemId the value for TBL_AGENT_INFO.OEM_ID
     *
     * @mbggenerated
     */
    public void setOemId(String oemId) {
        this.oemId = oemId == null ? null : oemId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.UP_AGENT_ID
     *
     * @return the value of TBL_AGENT_INFO.UP_AGENT_ID
     *
     * @mbggenerated
     */
    public String getUpAgentId() {
        return upAgentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.UP_AGENT_ID
     *
     * @param upAgentId the value for TBL_AGENT_INFO.UP_AGENT_ID
     *
     * @mbggenerated
     */
    public void setUpAgentId(String upAgentId) {
        this.upAgentId = upAgentId == null ? null : upAgentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.REG_NAME
     *
     * @return the value of TBL_AGENT_INFO.REG_NAME
     *
     * @mbggenerated
     */
    public String getRegName() {
        return regName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.REG_NAME
     *
     * @param regName the value for TBL_AGENT_INFO.REG_NAME
     *
     * @mbggenerated
     */
    public void setRegName(String regName) {
        this.regName = regName == null ? null : regName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.REG_CODE
     *
     * @return the value of TBL_AGENT_INFO.REG_CODE
     *
     * @mbggenerated
     */
    public String getRegCode() {
        return regCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.REG_CODE
     *
     * @param regCode the value for TBL_AGENT_INFO.REG_CODE
     *
     * @mbggenerated
     */
    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.TAX_CODE
     *
     * @return the value of TBL_AGENT_INFO.TAX_CODE
     *
     * @mbggenerated
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.TAX_CODE
     *
     * @param taxCode the value for TBL_AGENT_INFO.TAX_CODE
     *
     * @mbggenerated
     */
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode == null ? null : taxCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.REG_ADDR
     *
     * @return the value of TBL_AGENT_INFO.REG_ADDR
     *
     * @mbggenerated
     */
    public String getRegAddr() {
        return regAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.REG_ADDR
     *
     * @param regAddr the value for TBL_AGENT_INFO.REG_ADDR
     *
     * @mbggenerated
     */
    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr == null ? null : regAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.LIC_TYPE
     *
     * @return the value of TBL_AGENT_INFO.LIC_TYPE
     *
     * @mbggenerated
     */
    public String getLicType() {
        return licType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.LIC_TYPE
     *
     * @param licType the value for TBL_AGENT_INFO.LIC_TYPE
     *
     * @mbggenerated
     */
    public void setLicType(String licType) {
        this.licType = licType == null ? null : licType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.LIC_AMT
     *
     * @return the value of TBL_AGENT_INFO.LIC_AMT
     *
     * @mbggenerated
     */
    public String getLicAmt() {
        return licAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.LIC_AMT
     *
     * @param licAmt the value for TBL_AGENT_INFO.LIC_AMT
     *
     * @mbggenerated
     */
    public void setLicAmt(String licAmt) {
        this.licAmt = licAmt == null ? null : licAmt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.LEGAL_NAME
     *
     * @return the value of TBL_AGENT_INFO.LEGAL_NAME
     *
     * @mbggenerated
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.LEGAL_NAME
     *
     * @param legalName the value for TBL_AGENT_INFO.LEGAL_NAME
     *
     * @mbggenerated
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName == null ? null : legalName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.IDTYPE
     *
     * @return the value of TBL_AGENT_INFO.IDTYPE
     *
     * @mbggenerated
     */
    public String getIdtype() {
        return idtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.IDTYPE
     *
     * @param idtype the value for TBL_AGENT_INFO.IDTYPE
     *
     * @mbggenerated
     */
    public void setIdtype(String idtype) {
        this.idtype = idtype == null ? null : idtype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.IDNO
     *
     * @return the value of TBL_AGENT_INFO.IDNO
     *
     * @mbggenerated
     */
    public String getIdno() {
        return idno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.IDNO
     *
     * @param idno the value for TBL_AGENT_INFO.IDNO
     *
     * @mbggenerated
     */
    public void setIdno(String idno) {
        this.idno = idno == null ? null : idno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.CONTACT_NAME
     *
     * @return the value of TBL_AGENT_INFO.CONTACT_NAME
     *
     * @mbggenerated
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.CONTACT_NAME
     *
     * @param contactName the value for TBL_AGENT_INFO.CONTACT_NAME
     *
     * @mbggenerated
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.CONTACT_MOBILE
     *
     * @return the value of TBL_AGENT_INFO.CONTACT_MOBILE
     *
     * @mbggenerated
     */
    public String getContactMobile() {
        return contactMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.CONTACT_MOBILE
     *
     * @param contactMobile the value for TBL_AGENT_INFO.CONTACT_MOBILE
     *
     * @mbggenerated
     */
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.CONTACT_ADDR
     *
     * @return the value of TBL_AGENT_INFO.CONTACT_ADDR
     *
     * @mbggenerated
     */
    public String getContactAddr() {
        return contactAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.CONTACT_ADDR
     *
     * @param contactAddr the value for TBL_AGENT_INFO.CONTACT_ADDR
     *
     * @mbggenerated
     */
    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr == null ? null : contactAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.ZIP_CODE
     *
     * @return the value of TBL_AGENT_INFO.ZIP_CODE
     *
     * @mbggenerated
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.ZIP_CODE
     *
     * @param zipCode the value for TBL_AGENT_INFO.ZIP_CODE
     *
     * @mbggenerated
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.EMAIL
     *
     * @return the value of TBL_AGENT_INFO.EMAIL
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.EMAIL
     *
     * @param email the value for TBL_AGENT_INFO.EMAIL
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.TELENO
     *
     * @return the value of TBL_AGENT_INFO.TELENO
     *
     * @mbggenerated
     */
    public String getTeleno() {
        return teleno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.TELENO
     *
     * @param teleno the value for TBL_AGENT_INFO.TELENO
     *
     * @mbggenerated
     */
    public void setTeleno(String teleno) {
        this.teleno = teleno == null ? null : teleno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.SIGN_NAME
     *
     * @return the value of TBL_AGENT_INFO.SIGN_NAME
     *
     * @mbggenerated
     */
    public String getSignName() {
        return signName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.SIGN_NAME
     *
     * @param signName the value for TBL_AGENT_INFO.SIGN_NAME
     *
     * @mbggenerated
     */
    public void setSignName(String signName) {
        this.signName = signName == null ? null : signName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.SIGN_DATE
     *
     * @return the value of TBL_AGENT_INFO.SIGN_DATE
     *
     * @mbggenerated
     */
    public String getSignDate() {
        return signDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.SIGN_DATE
     *
     * @param signDate the value for TBL_AGENT_INFO.SIGN_DATE
     *
     * @mbggenerated
     */
    public void setSignDate(String signDate) {
        this.signDate = signDate == null ? null : signDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.REC_CRET_DTTM
     *
     * @return the value of TBL_AGENT_INFO.REC_CRET_DTTM
     *
     * @mbggenerated
     */
    public String getRecCretDttm() {
        return recCretDttm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.REC_CRET_DTTM
     *
     * @param recCretDttm the value for TBL_AGENT_INFO.REC_CRET_DTTM
     *
     * @mbggenerated
     */
    public void setRecCretDttm(String recCretDttm) {
        this.recCretDttm = recCretDttm == null ? null : recCretDttm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.LST_UPD_DTTM
     *
     * @return the value of TBL_AGENT_INFO.LST_UPD_DTTM
     *
     * @mbggenerated
     */
    public String getLstUpdDttm() {
        return lstUpdDttm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.LST_UPD_DTTM
     *
     * @param lstUpdDttm the value for TBL_AGENT_INFO.LST_UPD_DTTM
     *
     * @mbggenerated
     */
    public void setLstUpdDttm(String lstUpdDttm) {
        this.lstUpdDttm = lstUpdDttm == null ? null : lstUpdDttm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_AGENT_INFO.AGENT_STATUS
     *
     * @return the value of TBL_AGENT_INFO.AGENT_STATUS
     *
     * @mbggenerated
     */
    public String getAgentStatus() {
        return agentStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_AGENT_INFO.AGENT_STATUS
     *
     * @param agentStatus the value for TBL_AGENT_INFO.AGENT_STATUS
     *
     * @mbggenerated
     */
    public void setAgentStatus(String agentStatus) {
        this.agentStatus = agentStatus == null ? null : agentStatus.trim();
    }
}