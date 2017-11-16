package com.allcheer.bpos.mapper;

import com.allcheer.bpos.domain.AgentDetailInfoBO;

/**
 * Created by LiuBin on 2017/1/18.
 */
public interface AgentCustMapper {
    /**
     * 代理商详情查询
     */
    AgentDetailInfoBO selectAgentDetailInfoBO(String memberId);
}
