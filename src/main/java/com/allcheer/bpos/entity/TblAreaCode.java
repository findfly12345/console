package com.allcheer.bpos.entity;

public class TblAreaCode {
    private String areaCode;

    private String areaName;

    private String upAreaCode;

    private String areaType;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getUpAreaCode() {
        return upAreaCode;
    }

    public void setUpAreaCode(String upAreaCode) {
        this.upAreaCode = upAreaCode == null ? null : upAreaCode.trim();
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType == null ? null : areaType.trim();
    }
}