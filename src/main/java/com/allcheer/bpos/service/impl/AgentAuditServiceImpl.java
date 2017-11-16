package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.mapper.SeqMapper;
import com.allcheer.bpos.mapper.TblAgentAuditRecordDoMapper;
import com.allcheer.bpos.mapper.TblAgentFeeInfoDoMapper;
import com.allcheer.bpos.mapper.TblAgentInfoDoMapper;
import com.allcheer.bpos.service.AgentAuditService;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by peng.ll on 2017/3/1.
 */
@Service("agentAuditService")
public class AgentAuditServiceImpl implements AgentAuditService {


    @Autowired
    private SeqMapper seqMapper;

    @Autowired
    private TblAgentInfoDoMapper tblAgentInfoDoMapper;

    @Autowired
    private TblAgentAuditRecordDoMapper tblAgentAuditRecordDoMapper;

    @Autowired
    private TblAgentFeeInfoDoMapper tblAgentFeeInfoDoMapper;

    @Override
    public Pagination<TblAgentInfoDo> queryAgentInfoList(Map map, TblAgentInfoDo tblAgentInfoDo) {
        TblAgentInfoDoExample tblAgentInfoDoExample = new TblAgentInfoDoExample();
        TblAgentInfoDoExample.Criteria criteria = tblAgentInfoDoExample.createCriteria();
        if (tblAgentInfoDo != null) {
            if (StringUtils.isNotBlank(tblAgentInfoDo.getAgentName())) {
                //criteria.andAgentNameEqualTo(tblAgentInfoDo.getAgentName());
                criteria.andAgentNameLike("%"+tblAgentInfoDo.getAgentName()+"%");
            }
            if (StringUtils.isNotBlank(tblAgentInfoDo.getIdno())) {
                criteria.andIdnoEqualTo(tblAgentInfoDo.getIdno());
            }
            if (StringUtils.isNotBlank(tblAgentInfoDo.getMemberId())) {
                criteria.andMemberIdEqualTo(tblAgentInfoDo.getMemberId());
            }
        }
        criteria.andAgentStatusEqualTo(tblAgentInfoDo.getAgentStatus());

        int count = tblAgentInfoDoMapper.countByExample(tblAgentInfoDoExample);
        int pageCurrent = Integer.valueOf((String) map.get("pageCurrent"));
        int pageSize = Integer.valueOf((String) map.get("pageSize"));

        Pagination pagination = new Pagination(count, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblAgentInfoDo> tblAgentInfoDoList = tblAgentInfoDoMapper.selectByExample(tblAgentInfoDoExample);
        pagination.addResult(tblAgentInfoDoList);

        return pagination;
    }

    @Override
    public TblAgentInfoDo queryAgentById(String memberId) {
        TblAgentInfoDoExample tblAgentInfoDoExample = new TblAgentInfoDoExample();
        tblAgentInfoDoExample.createCriteria().andMemberIdEqualTo(memberId);
        tblAgentInfoDoMapper.selectByExample(tblAgentInfoDoExample);
        List<TblAgentInfoDo> tblAgentInfoDoList = tblAgentInfoDoMapper.selectByExample(tblAgentInfoDoExample);
        if (tblAgentInfoDoList.size() == 0) {
            return new TblAgentInfoDo();
        }
        return tblAgentInfoDoList.get(0);
    }

    @Override
    public int auditAgent(String memberId, String errorFields, String remark, String userName, String audit) {
        TblAgentInfoDo tblAgentInfoDo = tblAgentInfoDoMapper.selectByPrimaryKey(memberId);
        if (tblAgentInfoDo == null) {
            return 0;
        }
        TblAgentInfoDoExample tblAgentInfoDoExample = new TblAgentInfoDoExample();
        tblAgentInfoDoExample.createCriteria().andMemberIdEqualTo(memberId).andAgentStatusEqualTo(Constant.AUDIT_NONE);
        if ("ok".equals(audit)) {
            tblAgentInfoDo.setAgentStatus(Constant.AUDIT_IN);
        } else {
            tblAgentInfoDo.setAgentStatus(Constant.AUDIT_REJECT);
        }
        int res = tblAgentInfoDoMapper.updateByExample(tblAgentInfoDo, tblAgentInfoDoExample);

        if (res != 0) {
            TblAgentAuditRecordDo tblAgentAuditRecordDo = new TblAgentAuditRecordDo();
            tblAgentAuditRecordDo.setAuditId(String.valueOf(seqMapper.getSequenceNextVal("AGENT_AUDIT_SEQ")));
            tblAgentAuditRecordDo.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
            tblAgentAuditRecordDo.setErrorField(errorFields);
            tblAgentAuditRecordDo.setRemark(remark);
            tblAgentAuditRecordDo.setUserName(userName);
            tblAgentAuditRecordDo.setMemberId(memberId);
            if ("ok".equals(audit)) {
                tblAgentAuditRecordDo.setAuditResult(Constant.AUDIT_IN);
            } else {
                tblAgentAuditRecordDo.setAuditResult(Constant.AUDIT_REJECT);
            }
            tblAgentAuditRecordDoMapper.insert(tblAgentAuditRecordDo);
        }
        return res;
    }

    @Override
    public Pagination<Map<String, Object>> queryAgentAuditRecordList(Map<String, String> queryMap) {
        int count = tblAgentInfoDoMapper.countByCondition(queryMap);
        int pageCurrent = Integer.parseInt(queryMap.get("pageCurrent"));
        int pageSize = Integer.parseInt(queryMap.get("pageSize"));
        Pagination pagination = new Pagination(count, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);
        List<TblAgentInfoDo> tblAgentAuditPagination = tblAgentInfoDoMapper.selectByCondition(queryMap);
        pagination.addResult(tblAgentAuditPagination);

        return pagination;

    }

    @Override
    public List<TblAgentFeeInfoDo> queryAgentFeeById(String memberId) {
        TblAgentFeeInfoDoExample tblAgentFeeInfoDoExample = new TblAgentFeeInfoDoExample();
        tblAgentFeeInfoDoExample.createCriteria().andAgentIdEqualTo(memberId);

        List<TblAgentFeeInfoDo> tblAgentFeeInfoDoList = tblAgentFeeInfoDoMapper.selectByExample(tblAgentFeeInfoDoExample);


        if (tblAgentFeeInfoDoList.size() == 0) {
            return new ArrayList<TblAgentFeeInfoDo>();
        }
        return tblAgentFeeInfoDoList;
    }

    @Override
    public TblAgentAuditRecordDo queryAgentAuditReocrdById(String memberId) {
        return tblAgentAuditRecordDoMapper.selectByPrimaryKey(memberId);
    }

    @Override
    public List<TblAgentInfoDo> selectAllAgentInfo() {
        TblAgentInfoDoExample tblAgentInfoDoExample = new TblAgentInfoDoExample();
        tblAgentInfoDoExample.createCriteria();
        List<TblAgentInfoDo> tblAgentInfoDoList = tblAgentInfoDoMapper.selectByExample(tblAgentInfoDoExample);
        return tblAgentInfoDoList;
    }

}
