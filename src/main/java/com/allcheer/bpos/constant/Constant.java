package com.allcheer.bpos.constant;

/**
 * Created by APPLE on 2016/10/20.
 */
public class Constant {
    /**
     * 金额
     */
    public static final String AMT_DEFAULT = "0.00";
    public static final String AMT_FORMAT = "0.0000####";
    public static final String AMT_INT = "0";

    /**
     * 常用字符串
     */
    public static final String STRING_EMPTY = "";


    /**
     * 数字
     */
    public static final String STRING_100 = "100";
    public static final String STRING_0 = "0";

    public static final String INCOME_WECHAT_TYPE = "03"; //微信
    public static final String INCOME_ALLIPAY_TYPE = "04"; //支付宝

    /** 商户状态 - 审核通过    **/
    public static final String AUDIT_OK = "0";     //审核成功
    /** 商户状态 - 未审核    **/
    public static final String AUDIT_NONE = "1";   //未审核
    /** 商户状态 - 审核失败    **/
    public static final String AUDIT_REJECT = "2"; //审核失败
    /** 商户状态 - 未提交审核    **/
    public static final String AUDIT_AWAY = "3";   //未提交审核
    /** 商户状态 - 审核中    **/
    public static final String AUDIT_IN = "4";     //审核中
    

    /** 登陆账户启用 **/
    public static final String USER_ACTIVATED = "1";     //启用
    /** 登陆账户禁用**/
    public static final String USER_DISABLED = "0";     //禁用
    
    
    
    /** 登陆账户类型 - 大众商户**/
    public static final String GENERAL_USER = "3";     //启用
    /** 登陆账户类型 - 机构用户**/
    public static final String INST_USER = "2";     //禁用   
    /** 登陆账户类型 - 代理商用户**/
    public static final String AGENT_USER = "1";     //禁用       
    
    /**
     * Address Level
     * Country  = 1
     * province  = 2
     * City = 3
     * area = 4
     */
    public static final String ADDRESS_COUNTRY = "1";     
    public static final String ADDRESS_PROVINCE = "2"; 
    public static final String ADDRESS_CITY = "3"; 
    public static final String ADDRESS_AREA = "4"; 
    
    public static final String NO_CARD_GATE_ID = "U5";
    
}
