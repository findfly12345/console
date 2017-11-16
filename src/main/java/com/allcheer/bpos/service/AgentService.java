package com.allcheer.bpos.service;

import com.allcheer.bpos.domain.AgentDetailInfoBO;
import com.allcheer.bpos.domain.AgentFeeBO;
import com.allcheer.bpos.entity.TblAgentFeeInfoDo;
import com.allcheer.bpos.util.Pagination;

import javax.servlet.ServletOutputStream;
import java.util.Map;

/**
 * Created by LiuBin on 2017/1/17.
 */
public interface AgentService {

    public Map openNewAgent(AgentDetailInfoBO agentDetailInfoBO, String userName);

    public Pagination<AgentDetailInfoBO> queryAgentList (AgentDetailInfoBO agentDetailInfoBO);

    public void exportAgentList(AgentDetailInfoBO agentDetailInfoBO, ServletOutputStream outputStream) throws Exception;

    public AgentDetailInfoBO getAgtDetailInfoById(String memberId);

    public Map updateFeeSetttingsForAgent(AgentFeeBO agentFeeBO,String agentId,String userName);

    public AgentFeeBO getFeeForAgent(String memberId);

    public Map updateAgentDetails(AgentDetailInfoBO agentDetailInfoBO);

    public String getAgentIdSeq();

    public String getUserNameSeq();

    TblAgentFeeInfoDo queryAgentFeeList(TblAgentFeeInfoDo tblAgentFeeInfoDo);

}
