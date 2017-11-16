package com.allcheer.bpos.domain.Minsheng;

/**
 * Created by fireWorks on 2016/11/29.
 */
public class MinshengHeader {

    String version;
    String msgType;
    String smzfMsgId;
    String reqDate;
    String respDate;
    String respType;
    String respCode;
    String respMsg;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSmzfMsgId() {
        return smzfMsgId;
    }

    public void setSmzfMsgId(String smzfMsgId) {
        this.smzfMsgId = smzfMsgId;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getRespDate() {
        return respDate;
    }

    public void setRespDate(String respDate) {
        this.respDate = respDate;
    }

    public String getRespType() {
        return respType;
    }

    public void setRespType(String respType) {
        this.respType = respType;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
}
