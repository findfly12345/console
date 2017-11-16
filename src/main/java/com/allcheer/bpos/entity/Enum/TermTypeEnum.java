package com.allcheer.bpos.entity.Enum;

/**
 * Created by APPLE on 2016/10/25.
 */
public enum TermTypeEnum {
    YIDONG("0", "移动"), GUDING("1", "固定"),MPOS("2","MPOS");

    // 成员变量
    private String code;
    private String message;

    // 构造方法
    private TermTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 普通方法
    public static String getMessage(String code) {
        for (TermTypeEnum termTypeEnum : TermTypeEnum.values()) {
            if (termTypeEnum.getCode().equals(code)) {
                return termTypeEnum.getMessage();
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
