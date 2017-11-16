package com.allcheer.bpos.form;

/**
 * Created by LiuBin on 2017/1/17.
 */
public class SubBranchInfoForm extends BaseForm{

    private String subbranchId;

    private String subbranchName;

    private String bankName;

    private String province;

    private String city;

    public String getSubbranchId() {
        return subbranchId;
    }

    public void setSubbranchId(String subbranchId) {
        this.subbranchId = subbranchId;
    }

    public String getSubbranchName() {
        return subbranchName;
    }

    public void setSubbranchName(String subbranchName) {
        this.subbranchName = subbranchName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
