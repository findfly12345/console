package com.allcheer.bpos.entity.Enum;

/**
 * Created by APPLE on 2016/10/25.
 */
public enum MerTypeEnum {
    COMPANYMER("0", "公司商户"), PERSONMER("1", "个体商户"),NOBUSLICMER("2","无执照商户");

    // 成员变量
    private String code;
    private String message;

    // 构造方法
    private MerTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 普通方法
    public static String getMessage(String code) {
        for (MerTypeEnum merTypeEnum : MerTypeEnum.values()) {
            if (merTypeEnum.getCode().equals(code)) {
                return merTypeEnum.getMessage();
            }
        }
        return null;
    }

    // get set 方法
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
