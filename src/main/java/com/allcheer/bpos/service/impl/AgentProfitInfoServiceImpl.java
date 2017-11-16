package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.TblMemberFeeProfit;
import com.allcheer.bpos.entity.TblMemberFeeProfitExample;
import com.allcheer.bpos.mapper.TblMemberFeeProfitMapper;
import com.allcheer.bpos.service.AgentProfitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by peng.ll on 2017/3/8.
 */
@Service("agentProfitInfoService")
public class AgentProfitInfoServiceImpl implements AgentProfitInfoService {

    @Autowired
    private TblMemberFeeProfitMapper tblMemberFeeProfitMapper;

    @Override
    public int insert(TblMemberFeeProfit tblMemberFeeProfit) {
        return tblMemberFeeProfitMapper.insert(tblMemberFeeProfit);
    }

    @Override
    public int deleteTblMemberFeeProfit(String transDate) {
        TblMemberFeeProfitExample tblMemberFeeProfitExample = new TblMemberFeeProfitExample();
        tblMemberFeeProfitExample.createCriteria().andTransDateEqualTo(transDate);
        return tblMemberFeeProfitMapper.deleteByExample(tblMemberFeeProfitExample);
    }
}
