package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.domain.AgentFeeBO;
import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.util.Pagination;

import java.util.Map;

/**
 * Created by peng.ll on 2017/3/7.
 */
public interface InstBtsService {

    Pagination<TblBtsInstDO> queryInstList(TblBtsInstDO tblBtsInstDO);

    AgentFeeBO getInstFeeInfo(String instId);

    Map updateFeeSetttingsForInst(AgentFeeBO agentFeeBO, String instId);
}
