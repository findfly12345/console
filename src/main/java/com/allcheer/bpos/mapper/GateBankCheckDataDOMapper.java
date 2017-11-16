package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.GateBankCheckDataDO;
import com.allcheer.bpos.entity.GateBankCheckDataDOExample;
import com.allcheer.bpos.entity.GateBankCheckDataDOKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GateBankCheckDataDOMapper {
    int countByExample(GateBankCheckDataDOExample example);

    int deleteByExample(GateBankCheckDataDOExample example);

    int deleteByPrimaryKey(GateBankCheckDataDOKey key);

    int insert(GateBankCheckDataDO record);

    int insertSelective(GateBankCheckDataDO record);

    List<GateBankCheckDataDO> selectByExample(GateBankCheckDataDOExample example);

    GateBankCheckDataDO selectByPrimaryKey(GateBankCheckDataDOKey key);

    int updateByExampleSelective(@Param("record") GateBankCheckDataDO record, @Param("example") GateBankCheckDataDOExample example);

    int updateByExample(@Param("record") GateBankCheckDataDO record, @Param("example") GateBankCheckDataDOExample example);

    int updateByPrimaryKeySelective(GateBankCheckDataDO record);

    int updateByPrimaryKey(GateBankCheckDataDO record);
}