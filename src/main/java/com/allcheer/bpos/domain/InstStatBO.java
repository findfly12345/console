package com.allcheer.bpos.domain;

/**
 * Created by fireWorks on 2016/2/29.
 */
public class InstStatBO {
    private String instCode;

    private String instName;

    private String instHsmIndex;

    private String instPrimKey;

    private String instPinKey;

    private String instMacKey;

    private String instTdKey;

    private String instStat;

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode == null ? null : instCode.trim();
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName == null ? null : instName.trim();
    }


    public String getInstHsmIndex() {
        return instHsmIndex;
    }

    public void setInstHsmIndex(String instHsmIndex) {
        this.instHsmIndex = instHsmIndex == null ? null : instHsmIndex.trim();
    }

    public String getInstPrimKey() {
        return instPrimKey;
    }

    public void setInstPrimKey(String instPrimKey) {
        this.instPrimKey = instPrimKey == null ? null : instPrimKey.trim();
    }

    public String getInstPinKey() {
        return instPinKey;
    }

    public void setInstPinKey(String instPinKey) {
        this.instPinKey = instPinKey == null ? null : instPinKey.trim();
    }

    public String getInstMacKey() {
        return instMacKey;
    }

    public void setInstMacKey(String instMacKey) {
        this.instMacKey = instMacKey == null ? null : instMacKey.trim();
    }

    public String getInstTdKey() {
        return instTdKey;
    }

    public void setInstTdKey(String instTdKey) {
        this.instTdKey = instTdKey == null ? null : instTdKey.trim();
    }

    public String getInstStat() {
        return instStat;
    }

    public void setInstStat(String instStat) {
        this.instStat = instStat == null ? null : instStat.trim();
    }
}
