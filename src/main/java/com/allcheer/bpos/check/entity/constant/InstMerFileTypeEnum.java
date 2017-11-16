package com.allcheer.bpos.check.entity.constant;

/**
 * Created by APPLE on 16/10/19.
 */
public enum InstMerFileTypeEnum {
    DATA       ("data", "商户数据"),
    ATTACHMENT ("attachment", "商户附件");


    private String code;
    private String name;

    private InstMerFileTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        for (InstMerFileTypeEnum e : InstMerFileTypeEnum.values()) {
            if (code.equals(e.getCode()))
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
