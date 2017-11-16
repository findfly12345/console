package com.allcheer.bpos.form;

import com.allcheer.bpos.domain.InstBO;
import com.allcheer.bpos.domain.MerSelectBO;

import java.util.List;

/**
 * Created by fireWorks on 2016/3/8.
 */
public class MerRouteForm extends BaseForm {

    private String id;

    private String merId;

    private String currentTradeCount;

    private List<InstBO> instBOList;

    private String instCode;

    private List<MerSelectBO> refsBigMers;

    private String refsBigMer;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    public String getCurrentTradeCount() {
        return currentTradeCount;
    }

    public void setCurrentTradeCount(String currentTradeCount) {
        this.currentTradeCount = currentTradeCount == null ? null : currentTradeCount.trim();
    }

    public List<InstBO> getInstBOList() {
        return instBOList;
    }

    public void setInstBOList(List<InstBO> instBOList) {
        this.instBOList = instBOList ;
    }


    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode == null ? null : instCode.trim();
    }

    public List<MerSelectBO> getRefsBigMers() {
        return refsBigMers;
    }

    public void setRefsBigMers(List<MerSelectBO> refsBigMers) {
        this.refsBigMers = refsBigMers;
    }

    public String getRefsBigMer() {
        return refsBigMer;
    }

    public void setRefsBigMer(String refsBigMer) {
        this.refsBigMer = refsBigMer == null ? null : refsBigMer.trim();
    }


}
