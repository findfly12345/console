package com.allcheer.bpos.entity.Enum;

/**
 * Created by fireWorks on 2016/3/3.
 */
public enum InstTypeEnum {
    INST("0", "收单机构"), CHANNEL("1", "渠道");

    // 成员变量
    private String code;
    private String message;

    // 构造方法
    private InstTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 普通方法
    public static String getMessage(String code) {
        for (InstTypeEnum instTypeEnum : InstTypeEnum.values()) {
            if (instTypeEnum.getCode().equals(code)) {
                return instTypeEnum.getMessage();
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
