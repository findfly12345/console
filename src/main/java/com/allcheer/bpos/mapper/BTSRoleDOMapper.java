package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.dao.BTSRoleDO;

import java.math.BigDecimal;

public interface BTSRoleDOMapper {
    int deleteByPrimaryKey(BigDecimal roleId);

    int insert(BTSRoleDO record);

    int insertSelective(BTSRoleDO record);

    BTSRoleDO selectByPrimaryKey(BigDecimal roleId);

    int updateByPrimaryKeySelective(BTSRoleDO record);

    int updateByPrimaryKey(BTSRoleDO record);
}