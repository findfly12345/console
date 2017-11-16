package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.entity.vo.LocalTxEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblBtsTransLogDOMapper {
    int countByExample(TblBtsTransLogDOExample example);

    int deleteByExample(TblBtsTransLogDOExample example);

    int insert(TblBtsTransLogDO record);

    int insertSelective(TblBtsTransLogDO record);

    List<TblBtsTransLogDO> selectByExampleWithBLOBs(TblBtsTransLogDOExample example);

    List<TblBtsTransLogDO> selectByExample(TblBtsTransLogDOExample example);

    int updateByExampleSelective(@Param("record") TblBtsTransLogDO record, @Param("example") TblBtsTransLogDOExample example);

    int updateByExampleWithBLOBs(@Param("record") TblBtsTransLogDO record, @Param("example") TblBtsTransLogDOExample example);

    int updateByExample(@Param("record") TblBtsTransLogDO record, @Param("example") TblBtsTransLogDOExample example);

    List<InstAccountingSummaryDO> rumGroupByInstId(@Param("transdate") String transDate, @Param("instid") String instid);

    List<InstProfitSummaryDO> rumGroupByAgentId(@Param("agentId") String agentId, @Param("acctDate") String acctDate, @Param("transStat") String transStat);

    List<InstProfitSummaryDO> rumGroupByInstInstId(@Param("instId") String instId, @Param("acctDate") String acctDate, @Param("transStat") String transStat);

    int selectInstByFeeAmmtAndAccoDateAndGageIdAndTransStat(@Param("acctDate") String acctDate);

    List<InstProfitSummaryDO> rumByInstInstId(@Param("instId") String instId, @Param("acctDate") String acctDate, @Param("transStat") String transStat);

    int saveTransLog(@Param("ordId") String ordId, @Param("refAmt") String refAmt);

    List<InstProfitDO> rumGroupCardFlagByInstInstId(@Param("instId") String instId, @Param("statDate") String statDate, @Param("endDate") String endDate);

    List<InstProfitDO> rumGroupChannelReport(@Param("statDate") String statDate, @Param("endDate") String endDate);

    List<LocalTxEntity> localSingleTx(@Param("merDate") String date, @Param("ord_id") String ord_id);
}