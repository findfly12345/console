package com.allcheer.bpos.form;

/**
 * Created by fireWorks on 2016/2/28.
 */
public class RateDetailForm extends BaseForm{
    private String rateName;

    private String rateValue;

    private String rateDesc;

    public String getRateName(){
        return this.rateName;
    }

    public void setRateName(String rateName){
        this.rateName = rateName;
    }

    public String getRateValue(){
        return this.rateValue;
    }

    public void setRateValue(String rateValue){
        this.rateValue = rateValue;
    }

    public String getRateDesc(){
        return this.rateDesc;
    }

    public void setRateDesc(String rateDesc){
        this.rateDesc = rateDesc;
    }
}
