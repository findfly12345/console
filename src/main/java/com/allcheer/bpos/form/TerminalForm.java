package com.allcheer.bpos.form;

/**
 * Created by LiuBin on 2017/2/8.
 */
public class TerminalForm extends BaseForm{
    private String termId; // 终端ID
    private String termName; // 终端名称
    private String termType; // 终端类型
    private String termStat; // 终端状态
    private String termInstallProv; // 终端安装省
    private String termInstallCity; // 终端安装市
    private String termInstallCounty; // 终端安装县
    private String termInstallAddress; // 终端安装地址
    private String termSn; // 终端SN号
    private String merId; // 商户ID
    private String userName; // 用户名
    private String bindStat; // 绑定状态

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

    public String getTermStat() {
        return termStat;
    }

    public void setTermStat(String termStat) {
        this.termStat = termStat;
    }

    public String getTermInstallProv() {
        return termInstallProv;
    }

    public void setTermInstallProv(String termInstallProv) {
        this.termInstallProv = termInstallProv;
    }

    public String getTermInstallCity() {
        return termInstallCity;
    }

    public void setTermInstallCity(String termInstallCity) {
        this.termInstallCity = termInstallCity;
    }

    public String getTermInstallCounty() {
        return termInstallCounty;
    }

    public void setTermInstallCounty(String termInstallCounty) {
        this.termInstallCounty = termInstallCounty;
    }

    public String getTermInstallAddress() {
        return termInstallAddress;
    }

    public void setTermInstallAddress(String termInstallAddress) {
        this.termInstallAddress = termInstallAddress;
    }

    public String getTermSn() {
        return termSn;
    }

    public void setTermSn(String termSn) {
        this.termSn = termSn;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBindStat() {
        return bindStat;
    }

    public void setBindStat(String bindStat) {
        this.bindStat = bindStat;
    }
}
