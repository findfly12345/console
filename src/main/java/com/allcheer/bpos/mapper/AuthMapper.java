package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.dao.AuthDo;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMapper {
	
	public AuthDo addAuth(AuthDo auth);
	
	public AuthDo updateAuth(AuthDo auth);
	
	public AuthDo findAuthByName(String name);
}
