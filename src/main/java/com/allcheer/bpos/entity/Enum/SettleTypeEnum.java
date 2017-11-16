package com.allcheer.bpos.entity.Enum;

/**
 * Created by fireWorks on 2016/10/26.
 */
public enum SettleTypeEnum {

    ENTERPRISE ("N", "对公"),
    PERSON     ("Y", "对私");

    private String code;
    private String name;

    private SettleTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        for (SettleTypeEnum e : SettleTypeEnum.values()) {
            if (e.getCode().equals(code))
                return e.getName();
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
