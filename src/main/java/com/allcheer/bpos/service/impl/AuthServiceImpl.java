package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.dao.AuthDo;
import com.allcheer.bpos.entity.util.BeanConvert;
import com.allcheer.bpos.entity.vo.AuthVo;
import com.allcheer.bpos.mapper.AuthMapper;
import com.allcheer.bpos.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authService")
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	AuthMapper authMapper;
	
	@Override
	@Transactional
	public AuthVo addAuth(AuthVo auth) {
		AuthDo authDo = convertDo(auth);
		authMapper.addAuth(authDo);
		authDo = authMapper.findAuthByName(auth.getName());
		return convertVo(authDo);
	}

	@Override
	@Transactional
	public AuthVo updateAuth(AuthVo auth) {
		AuthDo authDo = convertDo(auth);
		authMapper.updateAuth(authDo);
		authDo = authMapper.findAuthByName(auth.getName());
		return convertVo(authDo);
	}

	@Override
	public AuthVo findAuthByName(String name) {
		AuthDo authDo = authMapper.findAuthByName(name);
		return convertVo(authDo);
	}
	
	public AuthDo convertDo(AuthVo auth){
		AuthDo dao = new AuthDo();
		BeanConvert.convertTo(auth , dao);
		return dao;
	}
	
	public AuthVo convertVo(AuthDo auth){
		AuthVo vo = new AuthVo();
		BeanConvert.convertTo(auth , vo);
		return vo;
	}
}
