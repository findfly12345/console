package com.allcheer.bpos.form;

/**
 * Created by APPLE on 2016/11/17.
 */
public class TradeForm extends BaseForm {

    private String channelMerId;

    private String transBeginDate;

    private String transEndDate;

    private String gateId;

    private String transStat;

    private String checkStat;

    public String getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(String channelMerId) {
        this.channelMerId = channelMerId;
    }

    public String getTransBeginDate() {
        return transBeginDate;
    }

    public void setTransBeginDate(String transBeginDate) {
        this.transBeginDate = transBeginDate;
    }

    public String getTransEndDate() {
        return transEndDate;
    }

    public void setTransEndDate(String transEndDate) {
        this.transEndDate = transEndDate;
    }

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId;
    }

    public String getTransStat() {
        return transStat;
    }

    public void setTransStat(String transStat) {
        this.transStat = transStat;
    }

    public String getCheckStat() {
        return checkStat;
    }

    public void setCheckStat(String checkStat) {
        this.checkStat = checkStat;
    }
}
