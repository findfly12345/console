package com.allcheer.bpos.entity.Enum;

/**
 * Created by fireWorks on 2016/10/26.
 */
public enum FeeTypeEnum {

    X01     ("01", "借记卡费率(%)"),
    X01L    ("01L", "借记卡封顶(元)"),
    X02     ("02", "贷记卡费率(%)"),
    X03     ("03", "微信T0"),
    X04     ("04", "支付宝T0"),
    X04L    ("04L", "提现单笔金额(元)"),
    X05     ("05", "微信T1"),
    X06     ("06", "支付宝T1"),
    X07     ("07", "微信提现手续费"),
    X08     ("08", "支付宝提现手续费");

    private String code;
    private String name;

    private FeeTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        for (FeeTypeEnum e : FeeTypeEnum.values()) {
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
