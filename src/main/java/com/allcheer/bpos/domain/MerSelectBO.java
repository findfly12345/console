package com.allcheer.bpos.domain;

/**
 * Created by fireWorks on 2016/3/9.
 */
public class MerSelectBO {

    private String merId;

    private String checked;

    public String getMerId(){return this.merId;}

    public void setMerId(String merId){this.merId = merId == null ? null : merId.trim();}

    public String getChecked(){return this.checked;}

    public void setChecked(String checked){this.checked = checked == null ? null : checked.trim();}

}
