package com.allcheer.bpos.entity.Enum;

/**
 * Created by fireWorks on 2016/11/1.
 */
public enum  PhotoTypeEnum {
    X01     ("01", "营业执照"),
    X02     ("02", "税务登记证"),
    X03     ("03", "法人身份证"),
    X04     ("04", "钱宝特约商户登记审核表"),
    X05     ("05", "照片（门脸门牌地址、内部所售商品照"),
    X06     ("06", "房屋租赁合同"),
    X07     ("07", "组织机构代码证"),
    X08     ("08", "银行卡、开户许可证"),
    X09     ("09", "非法人授权书"),
    X10     ("10", "入账人身份证"),
    X11     ("11", "钱宝签约协议"),
    X12     ("12", "商户注册登记表"),
    X13     ("13", "商户信息调查表"),
    X14     ("14", "其他");

    private String code;
    private String name;

    private PhotoTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        for (PhotoTypeEnum e : PhotoTypeEnum.values()) {
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
