package com.allcheer.bpos.util;

/**
 * Created by peng.ll on 2017/3/7.
 * descript 交易类型
 */
public enum TranTypeEnum {
    ALIPAY("04"),WECHAT("03"),POS_DEBIT("01"),POS_CREDIT("02");//04 支付宝    03 微信     pos(1借记卡   2贷记卡)

    private String  status;

    TranTypeEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
