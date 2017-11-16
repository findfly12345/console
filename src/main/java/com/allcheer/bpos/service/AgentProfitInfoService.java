package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblMemberFeeProfit;

/**
 * Created by peng.ll on 2017/3/8.
 */
public interface AgentProfitInfoService {

    int insert(TblMemberFeeProfit tblMemberFeeProfit);

    int deleteTblMemberFeeProfit(String transDate);
}
