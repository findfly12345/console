package com.allcheer.bpos.entity.Enum;

/**
 * Created by LiuBin on 2017/2/15.
 */
public enum AccTypeEnum {
    COMPANY("N","对公"),PRIVATE("Y","对私");

    // 成员变量
    private String code;
    private String message;

    // 构造方法
    private AccTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 普通方法
    public static String getMessage(String code) {
        for (AccTypeEnum accTypeEnum : AccTypeEnum.values()) {
            if (accTypeEnum.getCode().equals(code)) {
                return accTypeEnum.getMessage();
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
