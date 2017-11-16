package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.entity.vo.TblAgentMerInfoVO;
import com.allcheer.bpos.form.AgentMerFeeForm;
import com.allcheer.bpos.form.SubBranchInfoForm;
import com.allcheer.bpos.util.Pagination;

import javax.servlet.ServletOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by LiuBin on 2017/1/13.
 */
public interface MerAgentService {

    public List<TblAgentInfoDo> findAgentInfos(TblAgentInfoDo tblAgentInfoDo);

    public Pagination<TblSubbranchInfoDO> findBankInfos(SubBranchInfoForm subBranchInfoForm);
    public Pagination<TblMerCoreMccDo> findMerMccs(TblMerCoreMccDo tblMerCoreMccDo);

    //新增
    public int insertTblMerInfo(TblMerInfoDO tblMerInfoDO);

    public int insertTblMerBankInfo(TblMerBankInfoDO tblMerBankInfoDO);

    public int insertTblMerFeeInfo(AgentMerFeeForm agentMerFeeForm, String merId, String termId, String userName);

    public int insertTblTermInfo(TblTermInfoDO tblTermInfoDO);

    public int insertTblAgentMerTerm(TblAgentMerTermDo tblAgentMerTermDo);

    //查询
    public TblAgentInfoDo queryAgentById(String merId);

    public TblMerInfoDO queryMerById(String merId);

    public TblMerBankInfoDO queryMerBankById(String merId);

    public List<TblMerFeeInfoDO> queryMerFeeById(String merId);

    public TblTermInfoDO queryTermById(String merId);

    //修改
    public int updateTblMerInfo(TblMerInfoDO tblMerInfoDO);

    public int updateTblMerBankInfo(TblMerBankInfoDO tblMerBankInfoDO);

    public int updateTblTermInfo(TblTermInfoDO tblTermInfoDO);

    public Map updateTblMerFeeInfo(AgentMerFeeForm agentMerFeeForm, String merId, String termId, String userName);

    //其他
    public Map deleteImage(String merId, String path, String colName);

    public Map insertTblFileMer(TblMerFileInfoDO tblMerFileInfoDO);

    public TblMerFileInfoDO queryFilesByMerId(String merId);

    public String getPlatFormMerId();

    public String getPlatFormTermId();

    public Pagination<TblAgentMerInfoVO> queryAgentMerInfoList(Map map);

    public TblMerAuditRecordDO queryMerAuditReocrdById(String recordId);

    int auditOK(String merId, String errorFields, String remark, String inRemark, String userName);

    int auditReject(String merId, String errorFields, String remark, String inRemark, String userName);

    Pagination<Map<String,Object>> queryMerAuditRecordList(Map<String,String> queryMap);

    TblMerAuditRecordDO queryMerAuditReocrdByMerId(String merId);

    int insettTblMerRelevanceMccDo(TblMerRelevanceMccDo tblMerRelevanceMccDo);

    TblMerRelevanceMccDo queryTblMerRelevanceMccDoById(String merId);

    int updateMerMcc(TblMerRelevanceMccDo tblMerRelevanceMccDo);
    //导出代理商户信息
	public void exportAgentMerList(List<TblAgentMerInfoVO> tblAgentMerList, ServletOutputStream outputStream) throws Exception;

	public List<TblAgentMerInfoVO> queryAgentMerExportList(Map map);
	
	//标注是否报备过
	Map<String, Object> reportMerAgent(String merId);

	Map<String, Object> commitNewAgentReq(String merId, String userName);	
}
