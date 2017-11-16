package com.allcheer.bpos.domain;

/**
 * Created by fireWorks on 2016/3/4.
 */
public class InstChannelMapBO extends BaseBO{

    private String instId;

    private String instMerId;

    private String instMerTermId;

    private String channelId;

    private String channelMerId;

    private String channelMerTermId;

    private String statusFlag;

    private String resv;

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId == null ? null : instId.trim();
    }

    public String getInstMerId() {
        return instMerId;
    }

    public void setInstMerId(String instMerId) {
        this.instMerId = instMerId == null ? null : instMerId.trim();
    }

    public String getInstMerTermId() {
        return instMerTermId;
    }

    public void setInstMerTermId(String instMerTermId) {
        this.instMerTermId = instMerTermId == null ? null : instMerTermId.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(String channelMerId) {
        this.channelMerId = channelMerId == null ? null : channelMerId.trim();
    }

    public String getChannelMerTermId() {
        return channelMerTermId;
    }

    public void setChannelMerTermId(String channelMerTermId) {
        this.channelMerTermId = channelMerTermId == null ? null : channelMerTermId.trim();
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag == null ? null : statusFlag.trim();
    }

    public String getResv() {
        return resv;
    }

    public void setResv(String resv) {
        this.resv = resv == null ? null : resv.trim();
    }
}
