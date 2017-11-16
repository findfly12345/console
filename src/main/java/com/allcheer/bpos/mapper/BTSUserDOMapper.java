package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.dao.BTSUserDO;

import java.math.BigDecimal;
import java.util.Set;

public interface BTSUserDOMapper {
	int deleteByPrimaryKey(BigDecimal userId);

	int insert(BTSUserDO record);

	int insertSelective(BTSUserDO record);

	BTSUserDO selectByPrimaryKey(BigDecimal userId);

	BTSUserDO selectByUserCode(String userCode);

	Set<String> selectRolesByUserCode(String userCode);

	Set<String> selectFunctionsByUserCode(String userCode);

	int updateByPrimaryKeySelective(BTSUserDO record);

	int updateByPrimaryKey(BTSUserDO record);
}