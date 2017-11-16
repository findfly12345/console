package com.allcheer.bpos.form;

/**
 * Created by fireWorks on 2016/3/9.
 */
public class MerRouteQueryForm extends BaseForm {
    private String sId;

    private String sMerId;

    private String sCurrentTradeCount;

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId == null ? null : sId.trim();
    }

    public String getsMerId() {
        return sMerId;
    }

    public void setsMerId(String sMerId) {
        this.sMerId = sMerId == null ? null : sMerId.trim();
    }

    public String getsCurrentTradeCount() {
        return sCurrentTradeCount;
    }

    public void setsCurrentTradeCount(String sCurrentTradeCount) {
        this.sCurrentTradeCount = sCurrentTradeCount == null ? null : sCurrentTradeCount.trim();
    }
}
