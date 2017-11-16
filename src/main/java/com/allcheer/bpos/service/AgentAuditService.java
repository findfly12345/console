package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblAgentAuditRecordDo;
import com.allcheer.bpos.entity.TblAgentFeeInfoDo;
import com.allcheer.bpos.entity.TblAgentInfoDo;
import com.allcheer.bpos.util.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Created by peng.ll on 2017/3/1.
 */
public interface AgentAuditService {

    Pagination<TblAgentInfoDo> queryAgentInfoList(Map map,TblAgentInfoDo tblAgentInfoDo) ;

    TblAgentInfoDo queryAgentById(String memberId);

    int auditAgent(String memberId, String errorFields, String remark, String userName,String autid);

    Pagination<Map<String,Object>> queryAgentAuditRecordList(Map<String,String> queryMap);

    List<TblAgentFeeInfoDo> queryAgentFeeById(String memberId);

    TblAgentAuditRecordDo queryAgentAuditReocrdById(String memberId);

    List<TblAgentInfoDo> selectAllAgentInfo();




}
