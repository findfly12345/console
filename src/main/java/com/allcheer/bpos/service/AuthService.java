package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.vo.AuthVo;

public interface AuthService {
	
	public AuthVo addAuth(AuthVo auth);
	
	public AuthVo updateAuth(AuthVo auth);
	
	public AuthVo findAuthByName(String name);
	
}
