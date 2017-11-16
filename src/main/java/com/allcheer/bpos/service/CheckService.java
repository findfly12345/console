package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblBtsTransLogDO;
import com.allcheer.bpos.entity.TblChannelCheckFileInfoDO;
import com.allcheer.bpos.entity.TblU1CheckFileDetailDO;
import com.allcheer.bpos.util.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Created by APPLE on 2016/11/16.
 */
public interface CheckService {

    List<TblChannelCheckFileInfoDO> getChannelCheckFile(String transDate, String gateId, String userName);

    List<TblU1CheckFileDetailDO> getU1ChannelCheckFileDetail(String fileNo);

    Map checkTrade(String transDate,String gateId,String userName);

    Pagination<TblU1CheckFileDetailDO>  queryU1ErrorTradeByPage(Map<String,String> map);

    List<TblBtsTransLogDO> queryU1PlatTrade(String termtrc,String refnum);

    Map u1ManualCheck(String manualCheckStat,String ordId,String posSeqId,String refNum);
}
