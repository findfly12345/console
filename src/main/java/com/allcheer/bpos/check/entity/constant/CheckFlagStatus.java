package com.allcheer.bpos.check.entity.constant;

public enum CheckFlagStatus {
	//成功
	S,
	//对帐文件中不存在
	M,
	//银行成功，我方失败
	R,
	//银行成功，我方失败
	Q,
	//金额不对
	A
}
