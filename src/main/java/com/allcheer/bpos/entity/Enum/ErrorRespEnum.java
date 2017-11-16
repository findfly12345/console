/**
 * 
 */
package com.allcheer.bpos.entity.Enum;

/**
 * @author Administrator
 *
 */
public enum ErrorRespEnum {
	RESP002008("002008"),
	RESP000002("000002"),
	RESP002035("002035"),
	RESP009998("009998"),
	RESP001006("001006"),
	RESP002005("002005"),
	RESP002004("002004"),
	RESP002006("002006"),
	RESP000000("000000"),
	RESP000001("000001"), //风控失败
	RESP009991("009991"), //WX 代理商信息错误
	RESP009992("009992"),
	RESP009993("009993"), //WX 路由信息不存在 add by thain li @20160706
	RESP009994("009994"), //WX 支付方式未开通 add by thain li @20160706
	RESP009995("009995"), //中信交易通知缺少参数 add by thain li @20160725
	RESP009996("009996"), //中信交易错误 add by thain li @20160725
	RESP009997("009997"), //乐刷交易错误 add by thain li @20160725
	RESP109998("109998"), //add by tonny 20160920
	RESP002001("002001"),
	RESP002002("002002"),
	RESP002058("002058"),
	RESP002042("002042"),
	RESP001002("001002"),
	RESP002044("002044"),
	RESP002045("002045"),
	RESP002046("002046"),
	RESP002047("002047"),
	RESP002048("002048"),
	RESP002049("002049"),
	RESP002090("002090"),
	RESP002036("002036"),
	RESP099999("099999"),
	RESP099990("099990"),
	RESP016045("016045"),
	RESP002100("002100"),
	RESP001007("001007"),
	RESP002101("002101"),
	RESP000029("000029"),
	RESP000034("000034"),
	RESP002010("002010"),
	RESP00029("00029"),
	RESPSUB01("-1"), //WX 支付页面生成错误 add by thain li @20160901
	;

	private String respCode;

	ErrorRespEnum(String respCode) {
		this.respCode = respCode;
	}

	public String getRespCode() {
		return this.respCode;
	}
}
