package com.allcheer.bpos.entity.Enum;

public enum UserStatusEnum {

	/*
	 * 用户状态
	 * S:正常
	 * F:冻结（不可登入）
	 * D:删除
	 */
	S("S","0"),
	F("F","1"),
	D("D","9");
	private String Key;
	private String value;
	
	UserStatusEnum(String key,String value){
		this.Key = key;
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public boolean equalsValue(UserStatusEnum en){
		return 	this.value.equals(en.value)?true:false;
	}
	
	public String convertValue(UserStatusEnum en){
		return en.value;
	}
	
	public static UserStatusEnum convertEn(String value){
		UserStatusEnum[] values = UserStatusEnum.values();
		for(UserStatusEnum en : values){
			if(en.value.equals(value)){
				return en;
			}
		}
		return null;
	}
}
