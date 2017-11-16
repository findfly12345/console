package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.InstProfitSummaryDO;
import com.allcheer.bpos.entity.WCINCOMEDO;
import com.allcheer.bpos.entity.WCINCOMEDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WCINCOMEDOMapper {
    int countByExample(WCINCOMEDOExample example);

    int deleteByExample(WCINCOMEDOExample example);

    int deleteByPrimaryKey(String id);

    int insert(WCINCOMEDO record);

    int insertSelective(WCINCOMEDO record);

    List<WCINCOMEDO> selectByExample(WCINCOMEDOExample example);

    WCINCOMEDO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WCINCOMEDO record, @Param("example") WCINCOMEDOExample example);

    int updateByExample(@Param("record") WCINCOMEDO record, @Param("example") WCINCOMEDOExample example);

    int updateByPrimaryKeySelective(WCINCOMEDO record);

    int updateByPrimaryKey(WCINCOMEDO record);
    
    List<WCINCOMEDO> selectByExampleForExport(WCINCOMEDOExample example);

    List<InstProfitSummaryDO> rumGroupByInstId(@Param("instId") String instId, @Param("transDate") String transDate, @Param("incomeStatus") String incomeStatus);
    List<InstProfitSummaryDO> rumGroupByAgentId(@Param("agentId") String instId, @Param("transDate") String transDate, @Param("incomeStatus") String incomeStatus);
}