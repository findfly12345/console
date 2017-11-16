package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.GateBankCheckLog;
import com.allcheer.bpos.entity.GateBankCheckLogExample;
import org.apache.ibatis.annotations.Param;

public interface GateBankCheckLogMapper {
    int countByExample(GateBankCheckLogExample example);

    int deleteByExample(GateBankCheckLogExample example);

    int insert(GateBankCheckLog record);

    int insertSelective(GateBankCheckLog record);

    java.util.List<com.allcheer.bpos.entity.GateBankCheckLog> selectByExample(GateBankCheckLogExample example);

    int updateByExampleSelective(@Param("record") GateBankCheckLog record, @Param("example") GateBankCheckLogExample example);

    int updateByExample(@Param("record") GateBankCheckLog record, @Param("example") GateBankCheckLogExample example);
}