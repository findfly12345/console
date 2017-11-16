package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.GateParamConfigDO;
import com.allcheer.bpos.entity.GateParamConfigDOExample;
import org.apache.ibatis.annotations.Param;

public interface GateParamConfigDOMapper {
    int countByExample(GateParamConfigDOExample example);

    int deleteByExample(GateParamConfigDOExample example);

    int insert(GateParamConfigDO record);

    int insertSelective(GateParamConfigDO record);

    java.util.List<com.allcheer.bpos.entity.GateParamConfigDO> selectByExample(GateParamConfigDOExample example);

    int updateByExampleSelective(@Param("record") GateParamConfigDO record, @Param("example") GateParamConfigDOExample example);

    int updateByExample(@Param("record") GateParamConfigDO record, @Param("example") GateParamConfigDOExample example);
}