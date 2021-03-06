package com.allcheer.bpos.entity;

import com.allcheer.bpos.form.BaseForm;

public class TblBlackAccDo extends BaseForm {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_BLACK_ACC.MER_ID
     *
     * @mbggenerated
     */
    private String merId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_BLACK_ACC.SETTLEMENT_CARD
     *
     * @mbggenerated
     */
    private String settlementCard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_BLACK_ACC.BANK_CARD
     *
     * @mbggenerated
     */
    private String bankCard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_BLACK_ACC.CREATE_USER
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_BLACK_ACC.CREATE_TIME
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_BLACK_ACC.REMARK
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_BLACK_ACC.MER_ID
     *
     * @return the value of TBL_BLACK_ACC.MER_ID
     *
     * @mbggenerated
     */
    public String getMerId() {
        return merId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_BLACK_ACC.MER_ID
     *
     * @param merId the value for TBL_BLACK_ACC.MER_ID
     *
     * @mbggenerated
     */
    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_BLACK_ACC.SETTLEMENT_CARD
     *
     * @return the value of TBL_BLACK_ACC.SETTLEMENT_CARD
     *
     * @mbggenerated
     */
    public String getSettlementCard() {
        return settlementCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_BLACK_ACC.SETTLEMENT_CARD
     *
     * @param settlementCard the value for TBL_BLACK_ACC.SETTLEMENT_CARD
     *
     * @mbggenerated
     */
    public void setSettlementCard(String settlementCard) {
        this.settlementCard = settlementCard == null ? null : settlementCard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_BLACK_ACC.BANK_CARD
     *
     * @return the value of TBL_BLACK_ACC.BANK_CARD
     *
     * @mbggenerated
     */
    public String getBankCard() {
        return bankCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_BLACK_ACC.BANK_CARD
     *
     * @param bankCard the value for TBL_BLACK_ACC.BANK_CARD
     *
     * @mbggenerated
     */
    public void setBankCard(String bankCard) {
        this.bankCard = bankCard == null ? null : bankCard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_BLACK_ACC.CREATE_USER
     *
     * @return the value of TBL_BLACK_ACC.CREATE_USER
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_BLACK_ACC.CREATE_USER
     *
     * @param createUser the value for TBL_BLACK_ACC.CREATE_USER
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_BLACK_ACC.CREATE_TIME
     *
     * @return the value of TBL_BLACK_ACC.CREATE_TIME
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_BLACK_ACC.CREATE_TIME
     *
     * @param createTime the value for TBL_BLACK_ACC.CREATE_TIME
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_BLACK_ACC.REMARK
     *
     * @return the value of TBL_BLACK_ACC.REMARK
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_BLACK_ACC.REMARK
     *
     * @param remark the value for TBL_BLACK_ACC.REMARK
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}