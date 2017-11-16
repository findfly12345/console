package com.allcheer.bpos.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fireWorks on 2016/3/8.
 */
public class MerRouteBO extends BaseBO {

    private String id;

    private String merId;

//    private String mer;

    private String currentTradeCount;

    private List<String> refsBigMers;

    private List<MerSelectBO> merSelectBOList;


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

    public List<String> getRefsBigMers() {
        return refsBigMers;
    }

    public void setRefsBigMers(List<String> refsBigMers) {
        this.refsBigMers = refsBigMers;
    }

    public List<MerSelectBO> getMerSelectBOList() {
        return merSelectBOList;
    }

    public void setMerSelectBOList(List<MerSelectBO> merSelectBOList) {
        this.merSelectBOList = merSelectBOList;
    }
}
