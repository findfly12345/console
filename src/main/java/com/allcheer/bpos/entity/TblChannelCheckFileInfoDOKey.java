package com.allcheer.bpos.entity;

public class TblChannelCheckFileInfoDOKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_CHANNEL_CHECK_FILE_INFO.TRANS_DATE
     *
     * @mbggenerated
     */
    private String transDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_CHANNEL_CHECK_FILE_INFO.GATE_ID
     *
     * @mbggenerated
     */
    private String gateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_CHANNEL_CHECK_FILE_INFO.FILE_TYPE
     *
     * @mbggenerated
     */
    private String fileType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_CHANNEL_CHECK_FILE_INFO.FILE_PATH
     *
     * @mbggenerated
     */
    private String filePath;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_CHANNEL_CHECK_FILE_INFO.TRANS_DATE
     *
     * @return the value of TBL_CHANNEL_CHECK_FILE_INFO.TRANS_DATE
     *
     * @mbggenerated
     */
    public String getTransDate() {
        return transDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_CHANNEL_CHECK_FILE_INFO.TRANS_DATE
     *
     * @param transDate the value for TBL_CHANNEL_CHECK_FILE_INFO.TRANS_DATE
     *
     * @mbggenerated
     */
    public void setTransDate(String transDate) {
        this.transDate = transDate == null ? null : transDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_CHANNEL_CHECK_FILE_INFO.GATE_ID
     *
     * @return the value of TBL_CHANNEL_CHECK_FILE_INFO.GATE_ID
     *
     * @mbggenerated
     */
    public String getGateId() {
        return gateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_CHANNEL_CHECK_FILE_INFO.GATE_ID
     *
     * @param gateId the value for TBL_CHANNEL_CHECK_FILE_INFO.GATE_ID
     *
     * @mbggenerated
     */
    public void setGateId(String gateId) {
        this.gateId = gateId == null ? null : gateId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_CHANNEL_CHECK_FILE_INFO.FILE_TYPE
     *
     * @return the value of TBL_CHANNEL_CHECK_FILE_INFO.FILE_TYPE
     *
     * @mbggenerated
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_CHANNEL_CHECK_FILE_INFO.FILE_TYPE
     *
     * @param fileType the value for TBL_CHANNEL_CHECK_FILE_INFO.FILE_TYPE
     *
     * @mbggenerated
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_CHANNEL_CHECK_FILE_INFO.FILE_PATH
     *
     * @return the value of TBL_CHANNEL_CHECK_FILE_INFO.FILE_PATH
     *
     * @mbggenerated
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_CHANNEL_CHECK_FILE_INFO.FILE_PATH
     *
     * @param filePath the value for TBL_CHANNEL_CHECK_FILE_INFO.FILE_PATH
     *
     * @mbggenerated
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }
}