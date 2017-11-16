package com.allcheer.bpos.entity.Enum;

import sun.security.util.PendingException;

/**
 * Created by fireWorks on 2016/10/26.
 */
public enum MerStatusEnum {

    FREEZE     ("0", "冻结"),
    NORMAL     ("1", "开通"),
    PENDING    ("2", "进件中");

    private String code;
    private String name;

    private MerStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        for (MerStatusEnum e : MerStatusEnum.values()) {
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
