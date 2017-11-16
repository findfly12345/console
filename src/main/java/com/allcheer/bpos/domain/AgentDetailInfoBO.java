package com.allcheer.bpos.domain;

/**
 * Created by LiuBin on 2017/1/17.
 */
public class AgentDetailInfoBO extends BaseBO {

    private String memberId; // 代理商号
    private String opdate; // 入网时间
    private String agentName; // 代理商名称
    private String agentShortName; // 代理商简称
    private String regName; // 公司注册名
    private String regCode; // 营业执照号码
    private String taxCode; // 税务登记号
    private String regAddr; // 公司注册地址
    private String licType; // 公司性质
    private String licAmt; // 注册资本
    private String legalName; // 法人代表
    private String idno; // 法人代表身份证号码
    private String contactName; // 联系人姓名
    private String contactMobile; // 联系电话
    private String contactAddr; // 联系地址
    private String email; // 联系邮箱
    private String memberStat; // 代理商状态
    private String agentLevel; //代理商等级
    private String upAgentId; //上级代理商ID
    private String oemId; //机构商
    private String fatherAgentId; //1级代理商ID
    private String fatherAgentName; //1级代理商NAME

    private String tellerId;// 操作员号

    private String signDate;//入网日期

    private String recoCode;//推荐码

    public void AgentDetailInfoBO() {

    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOpdate() {
        return opdate;
    }

    public void setOpdate(String opdate) {
        this.opdate = opdate;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentShortName() {
        return agentShortName;
    }

    public void setAgentShortName(String agentShortName) {
        this.agentShortName = agentShortName;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }

    public String getLicType() {
        return licType;
    }

    public void setLicType(String licType) {
        this.licType = licType;
    }

    public String getLicAmt() {
        return licAmt;
    }

    public void setLicAmt(String licAmt) {
        this.licAmt = licAmt;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactAddr() {
        return contactAddr;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMemberStat() {
        return memberStat;
    }

    public void setMemberStat(String memberStat) {
        this.memberStat = memberStat;
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }

    public String getUpAgentId() {
        return upAgentId;
    }

    public void setUpAgentId(String upAgentId) {
        this.upAgentId = upAgentId;
    }

    public String getOemId() {
        return oemId;
    }

    public void setOemId(String oemId) {
        this.oemId = oemId;
    }

    public String getFatherAgentId() {
        return fatherAgentId;
    }

    public void setFatherAgentId(String fatherAgentId) {
        this.fatherAgentId = fatherAgentId;
    }

    public String getFatherAgentName() {
        return fatherAgentName;
    }

    public void setFatherAgentName(String fatherAgentName) {
        this.fatherAgentName = fatherAgentName;
    }

    public String getTellerId() {
        return tellerId;
    }

    public void setTellerId(String tellerId) {
        this.tellerId = tellerId;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getRecoCode() {
        return recoCode;
    }

    public void setRecoCode(String recoCode) {
        this.recoCode = recoCode;
    }
}
