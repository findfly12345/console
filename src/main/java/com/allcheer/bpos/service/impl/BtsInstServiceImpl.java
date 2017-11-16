package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblBtsInstDOExample;
import com.allcheer.bpos.mapper.TblBtsInstDOMapper;
import com.allcheer.bpos.service.BtsInstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BtsInstServiceImpl implements BtsInstService {

	@Autowired
	TblBtsInstDOMapper tblBtsInstDOMapper;

	@Override
	public List<TblBtsInstDO> selectAllInstInfo() {

		TblBtsInstDOExample tblBtsInstDOExample = new TblBtsInstDOExample();
		tblBtsInstDOExample.createCriteria();

		List<TblBtsInstDO> tblBtsInstDOList = tblBtsInstDOMapper.selectByExample(tblBtsInstDOExample);

		return tblBtsInstDOList;
	}

}
