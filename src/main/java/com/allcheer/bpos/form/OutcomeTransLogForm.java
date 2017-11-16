package com.allcheer.bpos.form;

public class OutcomeTransLogForm extends BaseForm<OutcomeTransLogForm> {

    public String startTransDateTime;
    public String endTransDateTime;
    public String channelId;
    public String memberId;
    public String transStat;

    public String getStartTransDateTime() {
        return startTransDateTime;
    }

    public void setStartTransDateTime(String startTransDateTime) {
        this.startTransDateTime = startTransDateTime;
    }

    public String getEndTransDateTime() {
        return endTransDateTime;
    }

    public void setEndTransDateTime(String endTransDateTime) {
        this.endTransDateTime = endTransDateTime;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getTransStat() {
        return transStat;
    }

    public void setTransStat(String transStat) {
        this.transStat = transStat;
    }
}
