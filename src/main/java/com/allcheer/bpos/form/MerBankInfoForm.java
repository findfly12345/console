package com.allcheer.bpos.form;

import com.allcheer.bpos.entity.TblBankInfo;

import java.util.List;

/**
 * Created by fireWorks on 2016/10/25.
 */
public class MerBankInfoForm {

      private String provinceId;

    private String merId;

    private String bankName;

    private String provName;

    private String cityName;

    private String cnaps;

    private String bankBranchName;

    private String isPrivate;

    private String acctName;

    private String acctNo;

    private String userName;

    private String updateTime;

    private String createTime;

    private List<String> bankIdList;

    private List<MerBankInfoForm> bankProvNameList;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCnaps() {
        return cnaps;
    }

    public void setCnaps(String cnaps) {
        this.cnaps = cnaps;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<String> getBankIdList() {
        return bankIdList;
    }

    public void setBankIdList(List<String> bankIdList) {
        this.bankIdList = bankIdList;
    }

    public List getBankProvNameList() {
        return bankProvNameList;
    }

    public void setBankProvNameList(List bankProvNameList) {
        this.bankProvNameList = bankProvNameList;
    }
}
