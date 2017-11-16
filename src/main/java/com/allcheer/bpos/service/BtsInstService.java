package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblBtsInstDO;

import java.util.List;


public interface BtsInstService {

	List<TblBtsInstDO> selectAllInstInfo();
	
}
