package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.form.MerInfoForm;
import com.allcheer.bpos.form.TerminalForm;
import com.allcheer.bpos.mapper.*;
import com.allcheer.bpos.service.TerminalService;
import com.allcheer.bpos.util.BposException;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiuBin on 2017/2/8.
 */
@Service("terminalService")
public class TerminalServiceImpl implements TerminalService{

    private final static Logger logger = LoggerFactory.getLogger(TerminalServiceImpl.class);

    @Autowired
    TblTermInfoDOMapper termInfoDOMapper;
    @Autowired
    TblMerInfoDOMapper tblMerInfoDOMapper;
    @Autowired
    TblAgentMerTermDoMapper tblAgentMerTermDoMapper;
    @Autowired
    TblInstMerKeyInfoDOMapper tblInstMerKeyInfoDOMapper;
    @Autowired
    SeqMapper seqMapper;

    @Override
    public Pagination<TblTermInfoDO> queryTermInfoList(TerminalForm terminalForm){
        TblTermInfoDOExample tblTermInfoDOExample = new TblTermInfoDOExample();
        TblTermInfoDOExample.Criteria criteria = tblTermInfoDOExample.createCriteria();
        if(StringUtils.isNotBlank(terminalForm.getTermName())) {
            criteria.andTermNameLike("%"+terminalForm.getTermName()+"%");
        }
        if(StringUtils.isNotBlank(terminalForm.getTermStat())) {
            criteria.andTermStatEqualTo(terminalForm.getTermStat());
        }
        if(StringUtils.isNotBlank(terminalForm.getTermId())) {
            criteria.andTermIdEqualTo(terminalForm.getTermId());
        }
        int count = termInfoDOMapper.countByExample(tblTermInfoDOExample);
        int pageCurrent = Integer.parseInt(terminalForm.getPageCurrent() == ""?"0":terminalForm.getPageCurrent());
        int pageSize = Integer.parseInt(terminalForm.getPageSize() == ""?"0":terminalForm.getPageSize());
        Pagination pagination = new Pagination(count,pageCurrent , pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblTermInfoDO> tblTermInfoDOS = termInfoDOMapper.selectByExample(tblTermInfoDOExample);
        pagination.addResult(tblTermInfoDOS);
        return pagination;
    }

    @Override
    public String queryAgetIdByMer(String merId) {
        String agentId = "";
        TblAgentMerTermDoExample tblAgentMerTermDoExample = new TblAgentMerTermDoExample();
        tblAgentMerTermDoExample.createCriteria().andMerIdEqualTo(merId);
        List<TblAgentMerTermDo> tblAgentMerTermDos = tblAgentMerTermDoMapper.selectByExample(tblAgentMerTermDoExample);
        if(tblAgentMerTermDos != null && tblAgentMerTermDos.size() > 0) {
            agentId = tblAgentMerTermDos.get(0).getAgentId();
        }
        return agentId;
    }

    @Override
//    @Transactional
    public Map insertTblTermInfo(TblTermInfoDO tblTermInfoDO){
        Map resultMap = new HashMap();
        try{
            int i = termInfoDOMapper.insertSelective(tblTermInfoDO);
            resultMap.put("statusCode", 200);
            resultMap.put("message", "新增成功！");
        }catch (Exception e){
            logger.error(e.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "新增失败!");
            throw new BposException("新增失败!");
        }
        return resultMap;
    }

    @Override
    public TblTermInfoDO queryTermById (String termId) {
        TblTermInfoDOExample tblTermInfoDOExample = new TblTermInfoDOExample();
        tblTermInfoDOExample.createCriteria().andTermIdEqualTo(termId);
        List<TblTermInfoDO> tblTermInfoDOS = termInfoDOMapper.selectByExample(tblTermInfoDOExample);
        if(tblTermInfoDOS.size() == 0) {
            return new TblTermInfoDO();
        }
        return tblTermInfoDOS.get(0);
    }

    @Override
//    @Transactional
    public Map updateTblTermInfo (TblTermInfoDO tblTermInfoDO) {
        Map resultMap = new HashMap();
        try {
            int i = termInfoDOMapper.updateByPrimaryKeySelective(tblTermInfoDO);
            resultMap.put("statusCode", 200);
            resultMap.put("message", "更改已保存!");
        }catch (Exception e){
            logger.error(e.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "更新失败!");
            throw new BposException("更新失败!");
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Map updateTblAgentMerTerm (TblAgentMerTermDo tblAgentMerTermDo){
        Map resultMap = new HashMap();
        try {
            TblAgentMerTermDoExample tblAgentMerTermDoExample = new TblAgentMerTermDoExample();
            tblAgentMerTermDoExample.createCriteria().andTermIdEqualTo(tblAgentMerTermDo.getTermId());
            tblAgentMerTermDo.setTermId("");
            int i = tblAgentMerTermDoMapper.updateByExampleSelective(tblAgentMerTermDo,tblAgentMerTermDoExample);
            resultMap.put("statusCode", 200);
            resultMap.put("message", "更改已保存!");
        }catch (Exception e){
            logger.error(e.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "更新失败!");
            throw new BposException("更新失败!");
        }
        return resultMap;
    }

    @Override
//    @Transactional
    public Map bindTblAgentMerTerm (TblAgentMerTermDo tblAgentMerTermDo){
        Map resultMap = new HashMap();
        String agentId = "";
        try {
            TblAgentMerTermDoExample tblAgentMerTermDoExample = new TblAgentMerTermDoExample();
            TblAgentMerTermDoExample.Criteria criteria = tblAgentMerTermDoExample.createCriteria();
            criteria.andTermIdEqualTo(tblAgentMerTermDo.getTermId());
            List<TblAgentMerTermDo> tblAgentMerTermDos = tblAgentMerTermDoMapper.selectByExample(tblAgentMerTermDoExample);
            if(tblAgentMerTermDos.size() > 0) {
                resultMap.put("statusCode", 300);
                resultMap.put("message", "已绑定过，不能重复绑定");
                return resultMap;
            }
            TblAgentMerTermDoExample tblAgentMerTermDoExample2 = new TblAgentMerTermDoExample();
            TblAgentMerTermDoExample.Criteria criteria2 = tblAgentMerTermDoExample2.createCriteria();
            criteria2.andMerIdEqualTo(tblAgentMerTermDo.getMerId());
            List<TblAgentMerTermDo> tblAgentMerTermDos2 = tblAgentMerTermDoMapper.selectByExample(tblAgentMerTermDoExample2);
            if(tblAgentMerTermDos2.size() == 0) {
                resultMap.put("statusCode", 300);
                resultMap.put("message", "查找不到商户");
                return resultMap;
            }else {
                agentId = tblAgentMerTermDos2.get(0).getAgentId();
            }
            if(StringUtils.isNotBlank(tblAgentMerTermDo.getAgentId()) && !agentId.equals(tblAgentMerTermDo.getAgentId())) {
                resultMap.put("statusCode", 300);
                resultMap.put("message", "该终端已被其他代理商使用");
                return resultMap;
            }
            criteria2.andTermIdIsNull();
            List<TblAgentMerTermDo> tblAgentMerTermDos3 = tblAgentMerTermDoMapper.selectByExample(tblAgentMerTermDoExample2);
            if(tblAgentMerTermDos2.size() == 0) {
                tblAgentMerTermDo.setAgentId(agentId);
                tblAgentMerTermDo.setUpdateTime(DateUtil.date2String(new Date(),"yyyyMMddHHmmss"));
                tblAgentMerTermDo.setCreateTime(DateUtil.date2String(new Date(),"yyyyMMddHHmmss"));
                tblAgentMerTermDoMapper.insertSelective(tblAgentMerTermDo);
            }else {
                TblAgentMerTermDo tblAgentMerTermDo1 = tblAgentMerTermDos3.get(0);
                tblAgentMerTermDo1.setTermId(tblAgentMerTermDo.getTermId());
                tblAgentMerTermDoMapper.updateByExampleSelective(tblAgentMerTermDo1,tblAgentMerTermDoExample2);
            }
            resultMap.put("agentId",agentId);
            resultMap.put("statusCode", 200);
            resultMap.put("message", "更改已保存!");
        }catch (Exception e){
            logger.error(e.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "更新失败!");
            throw new BposException("更新失败!");
        }
        return resultMap;
    }

    @Override
    public Pagination<TblMerInfoDO> queryMerInfoList(MerInfoForm merInfoForm){
        TblMerInfoDOExample tblMerInfoDOExample = new TblMerInfoDOExample();
        TblMerInfoDOExample.Criteria criteria = tblMerInfoDOExample.createCriteria();
        if(StringUtils.isNotBlank(merInfoForm.getMerId())) {
            criteria.andMerIdEqualTo(merInfoForm.getMerId());
        }
        if(StringUtils.isNotBlank(merInfoForm.getMerName())) {
            criteria.andMerNameLike("%"+merInfoForm.getMerName()+"%");
        }
        int count = tblMerInfoDOMapper.countByExample(tblMerInfoDOExample);
        int pageCurrent = Integer.parseInt(merInfoForm.getPageCurrent() == ""?"0":merInfoForm.getPageCurrent());
        int pageSize = Integer.parseInt(merInfoForm.getPageSize() == ""?"0":merInfoForm.getPageSize());
        Pagination pagination = new Pagination(count,pageCurrent , pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblMerInfoDO> tblMerInfoDOS = tblMerInfoDOMapper.selectByExample(tblMerInfoDOExample);
        pagination.addResult(tblMerInfoDOS);
        return pagination;
    }

    @Override
//    @Transactional
    public int insertInstMerKey(TblInstMerKeyInfoDO tblInstMerKeyInfoDO) {
        int i = tblInstMerKeyInfoDOMapper.insertSelective(tblInstMerKeyInfoDO);
        return i;
    }

    @Override
    public String getPlatFormTermId() {
        long platFormTermIdSeq = seqMapper.getSequenceNextVal("SEQ_PLATFORM_TERM_ID");
        return StringUtils.leftPad(String.valueOf(platFormTermIdSeq), 8, '0');
    }
}
