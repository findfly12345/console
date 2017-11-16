package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.domain.AgentFeeBO;
import com.allcheer.bpos.entity.Enum.AccTypeEnum;
import com.allcheer.bpos.entity.Enum.MerTypeEnum;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.form.AgentMerFeeForm;
import com.allcheer.bpos.mapper.*;
import com.allcheer.bpos.service.AddBatchAgentMerService;
import com.allcheer.bpos.service.AgentService;
import com.allcheer.bpos.service.MerAgentService;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.ExcelReader;
import com.allcheer.bpos.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by LiuBin on 2017/2/14.
 */
@Service("addBatchAgentMerService")
public class AddBatchAgentMerServiceImpl implements AddBatchAgentMerService{

    private final static Logger logger = LoggerFactory.getLogger(AddBatchAgentMerServiceImpl.class);

    @Autowired
    AgentService agentService;
    @Autowired
    MerAgentService merAgentService;
    @Autowired
    private SeqMapper seqMapper;
    @Autowired
    private TblAgentMerAddBatchDOMapper tblAgentMerAddBatchDOMapper;
    @Autowired
    private TblAgentMerAddDetailInfoDOMapper tblAgentMerAddDetailInfoDOMapper;
    @Autowired
    private TblAgentFeeInfoDoMapper tblAgentFeeInfoDoMapper;
    @Autowired
    private TblAgentMerTermDoMapper tblAgentMerTermDoMapper;
    @Autowired
    private TblSubbranchInfoDOMapper tblSubbranchInfoDOMapper;
    @Autowired
    private TblMerInfoDOMapper tblMerInfoDOMapper;
    @Autowired
    private TblMerBankInfoDOMapper tblMerBankInfoDOMapper;

    @Override
    public String addBatchAgentMerData(String agetId,String dataFile,String fileType,String userName) {

        logger.info("开始进行批量导入代理商商户信息");
        String batchNo = getBatchNo();
        TblAgentMerAddBatchDO tblAgentMerAddBatchDO = new TblAgentMerAddBatchDO();
        tblAgentMerAddBatchDO.setAgentId(agetId);
        tblAgentMerAddBatchDO.setBatchNo(batchNo);
        tblAgentMerAddBatchDO.setFilePath(dataFile);
        tblAgentMerAddBatchDO.setFileType("0");
        tblAgentMerAddBatchDO.setResultFlag("I");
        tblAgentMerAddBatchDO.setResultDesc("初始化");
        tblAgentMerAddBatchDO.setCreateTime(DateUtil.date2String(new Date(),"yyyyMMddHHmmss"));
        tblAgentMerAddBatchDO.setUpdateTime(DateUtil.date2String(new Date(),"yyyyMMddHHmmss"));
        tblAgentMerAddBatchDO.setUserName(userName);
        tblAgentMerAddBatchDOMapper.insertSelective(tblAgentMerAddBatchDO);

        logger.info("代理商信息校验");
        TblAgentFeeInfoDoExample tblAgentFeeInfoDoExample = new TblAgentFeeInfoDoExample();
        tblAgentFeeInfoDoExample.createCriteria().andAgentIdEqualTo(agetId);
        List<TblAgentFeeInfoDo> tblAgentFeeInfoDoList = tblAgentFeeInfoDoMapper.selectByExample(tblAgentFeeInfoDoExample);
        if(tblAgentFeeInfoDoList != null && tblAgentFeeInfoDoList.size() > 0) {
            TblAgentFeeInfoDo tblAgentFeeInfoDo = tblAgentFeeInfoDoList.get(0);
            AgentFeeBO agentFeeBO = agentService.getFeeForAgent(tblAgentFeeInfoDo.getAgentId());
            if(StringUtils.isBlank(agentFeeBO.getCode01()) || StringUtils.isBlank(agentFeeBO.getCode01L())
                    || StringUtils.isBlank(agentFeeBO.getCode02())
                    || StringUtils.isBlank(agentFeeBO.getCode07())
                    || StringUtils.isBlank(agentFeeBO.getCode08())) {
                return returnFalseBatchNo(tblAgentMerAddBatchDO,"代理商费率信息设置不完整");
            }
        }else {
            return returnFalseBatchNo(tblAgentMerAddBatchDO,"代理商费率信息未设置");
        }

        logger.info("读取Excel文件插入数据库");
        ExcelReader excelReader = new ExcelReader();
        List<List<Object>> rows = null;
        try{
            if (".xls".equals(fileType)) {
                rows = excelReader.read2003Excel(new File(dataFile));
            }else if(".xlsx".equals(fileType)) {
                rows = excelReader.read2007Excel(new File(dataFile));
            }else {
                return returnFalseBatchNo(tblAgentMerAddBatchDO,"文件类型不支持");
            }
            for (List<Object> cells : rows) {
                String fileAgentID =  cells.get(0)+"";//文件代理商ID
                if("代理商号".equals(fileAgentID)) {
                    continue;
                }
                String merId =  cells.get(1)+"";// 商户号
                String merName = (String) cells.get(2);// 商户名称
                String merType =  cells.get(3) +"";// 商户类型
                String regName = (String) cells.get(4); // 注册名
                String regShortName = (String) cells.get(5);// 注册简称
                String regAddress = (String) cells.get(6);// 注册地址
                String regFunds =  cells.get(7) +"";// 注册资本
                String busLicNm =  cells.get(8) +"";// 营业执照编号
                String busLicExpire =  cells.get(9) +"";// 营业执照有效期
                String taxRegCert =  cells.get(10) +"";// 税务登记证
                String legalPerson = (String) cells.get(11);// 法人代表
                String legalPersonCertType = cells.get(12) +"";// 法人代表证件类型
                String legalPersonCertNm =  cells.get(13) +"";// 法人代表证件号
                String legalPersonCertExpire =  cells.get(14) +"";// 法人代表证件号有效期
                String contactPerson = (String) cells.get(15);// 联系人
                String contactMobile =  cells.get(16)+"";// 联系号码
                String contactEmail = (String) cells.get(17); // 联系邮箱
                String bankName = (String) cells.get(18);// 开户行
                String bankProv = (String) cells.get(19);// 开户行省
                String bankCity = (String) cells.get(20);// 开户行市
                String bankBranchName = (String) cells.get(21);// 开户支行
                String cnaps =  cells.get(22) +"";// 联行号
                String isPrivate = (String) cells.get(23);// 是否对私账户（账户类型）
                String acctName = (String) cells.get(24);// 账户名
                String acctNo =  cells.get(25)+"";// 账户号
                String fee01 =  cells.get(26) +"";// 借记卡手续费（%）
                String fee01L =  cells.get(27) +"";// 借记卡-封顶(元)
                String fee02 =  cells.get(28) +"";// 贷记卡-手续费
                String fee03 =  cells.get(29) +"";// 微信T0交易手续费（%）
                String fee04 =  cells.get(30) +"";// 支付宝T0交易手续费（%）
                String fee05 =  cells.get(31) +"";// 微信T1交易手续费（%）
                String fee06 =  cells.get(32) +"";// 支付宝T1交易手续费（%）
                String fee07 =  cells.get(33) +"";// T0提现手续费（%）
                String fee08 =  cells.get(34) +"";// T1提现手续费（%）

                logger.info("每行记录参数校验开始");
                TblAgentMerAddDetailInfoDO tblAgentMerAddDetailInfoDO = new TblAgentMerAddDetailInfoDO();
                tblAgentMerAddDetailInfoDO.setBatchNo(batchNo);
                tblAgentMerAddDetailInfoDO.setMerId(merId);
                tblAgentMerAddDetailInfoDO.setResultFlag("I");
                tblAgentMerAddDetailInfoDO.setResultDesc("初始化");
                tblAgentMerAddDetailInfoDO.setUserName(userName);
                tblAgentMerAddDetailInfoDO.setCreateTime(DateUtil.date2String(new Date(),"yyyyMMddHHmmss"));
                tblAgentMerAddDetailInfoDO.setUpdateTime(DateUtil.date2String(new Date(),"yyyyMMddHHmmss"));
                tblAgentMerAddDetailInfoDOMapper.insertSelective(tblAgentMerAddDetailInfoDO);
                if (StringUtils.isBlank(merId) || StringUtils.isBlank(merName) || StringUtils.isBlank(merType)
                        || StringUtils.isBlank(legalPerson) || StringUtils.isBlank(legalPersonCertType)
                        || StringUtils.isBlank(legalPersonCertNm) || StringUtils.isBlank(legalPersonCertExpire)
                        || StringUtils.isBlank(contactPerson) || StringUtils.isBlank(contactMobile)){
                    logger.error("商户信息不完整，商户号："+merId);
                    recordDetailBatchNoFalse(tblAgentMerAddDetailInfoDO,"商户信息不完整");
                    continue;
                }
                if (!"0".equals(legalPersonCertType) ) {
                    logger.error("证件类型必须是身份证，商户号："+merId);
                    recordDetailBatchNoFalse(tblAgentMerAddDetailInfoDO,"证件类型必须是身份证");
                    continue;
                }
                if (StringUtils.isBlank(bankName) || StringUtils.isBlank(bankProv) ||StringUtils.isBlank(bankCity)
                        ||StringUtils.isBlank(bankBranchName) ||StringUtils.isBlank(cnaps)
                        ||StringUtils.isBlank(isPrivate) || StringUtils.isBlank(acctName)||StringUtils.isBlank(acctNo)) {
                    recordDetailBatchNoFalse(tblAgentMerAddDetailInfoDO,"开户行信息不完整");
                    logger.error("开户行信息不完整，商户号："+merId);
                    continue;
                }
                if (StringUtils.isBlank(fee01) || StringUtils.isBlank(fee01L) || StringUtils.isBlank(fee02)
                        ||StringUtils.isBlank(fee07)||StringUtils.isBlank(fee08)) {
                    recordDetailBatchNoFalse(tblAgentMerAddDetailInfoDO,"商户费率信息不完整");
                    logger.error("商户费率信息不完整，商户号："+merId);
                    continue;
                }
                TblAgentMerTermDoExample tblAgentMerTermDoExample = new TblAgentMerTermDoExample();
                tblAgentMerTermDoExample.createCriteria().andAgentIdEqualTo(agetId).andInputMerIdEqualTo(merId);
                List<TblAgentMerTermDo> tblAgentMerTermDoList = tblAgentMerTermDoMapper.selectByExample(tblAgentMerTermDoExample);
                if (tblAgentMerTermDoList != null && tblAgentMerTermDoList.size() > 0) {
                    recordDetailBatchNoFalse(tblAgentMerAddDetailInfoDO,"该代理商商户已完成商户进件");
                    logger.error("该代理商商户已完成商户进件，商户号："+merId);
                    continue;
                }
                if (!fileAgentID.equals(agetId)) {
                    recordDetailBatchNoFalse(tblAgentMerAddDetailInfoDO,"文件中代理商号与所选代理商号不一致");
                    logger.error("文件中代理商号与所选代理商号不一致，商户号："+merId);
                    continue;
                }
                if (StringUtils.isBlank(MerTypeEnum.getMessage(merType))) {
                    recordDetailBatchNoFalse(tblAgentMerAddDetailInfoDO,"商户类型不存在");
                    logger.error("商户类型不存在，商户号："+merId);
                    continue;
                }
                if (StringUtils.isBlank(AccTypeEnum.getMessage(isPrivate))) {
                    recordDetailBatchNoFalse(tblAgentMerAddDetailInfoDO,"账户类型不存在");
                    logger.error("账户类型不存在，商户号："+merId);
                    continue;
                }
                if (!isValidDate(legalPersonCertExpire)) {
                    recordDetailBatchNoFalse(tblAgentMerAddDetailInfoDO,"法人证件有效期格式错误");
                    logger.error("法人证件有效期格式错误，商户号："+merId);
                    continue;
                }
                TblSubbranchInfoDOExample tblSubbranchInfoDOExample = new TblSubbranchInfoDOExample();
                tblSubbranchInfoDOExample.createCriteria().andSubbranchIdEqualTo(cnaps)
                        .andSubbranchNameEqualTo(bankBranchName).andBankNameEqualTo(bankName)
                        .andProvinceEqualTo(bankProv).andCityEqualTo(bankCity);
                List<TblSubbranchInfoDO> tblSubbranchInfoDOList = tblSubbranchInfoDOMapper.selectByExample(tblSubbranchInfoDOExample);
                if (tblSubbranchInfoDOList == null || tblSubbranchInfoDOList.size() == 0) {
                    recordDetailBatchNoFalse(tblAgentMerAddDetailInfoDO,"结算分行不存在");
                    logger.error("结算分行不存在，商户号："+merId);
                    continue;
                }
                logger.info("开始代理商商户进件，生成相应数据");
                try{
                    String nowTime = DateUtil.getCurrentDate()+DateUtil.getCurrentTime();
                    String platFormMerId = getPlatFormMerId();

                    TblMerInfoDO tblMerInfoDO = new TblMerInfoDO();
                    tblMerInfoDO.setMerId(platFormMerId);
                    tblMerInfoDO.setMerName(merName);
                    tblMerInfoDO.setMerType(merType);
                    tblMerInfoDO.setRegName(regName);
                    tblMerInfoDO.setRegShortName(regShortName);
                    tblMerInfoDO.setMerAddress(regAddress);
                    tblMerInfoDO.setRegFunds(regFunds);
                    tblMerInfoDO.setBusLicNm(busLicNm);
                    tblMerInfoDO.setBusLicExpire(DateUtil.removeLineDateString(busLicExpire));
                    tblMerInfoDO.setTaxRegCert(taxRegCert);
                    tblMerInfoDO.setLegalPerson(legalPerson);
                    tblMerInfoDO.setLegalPersonCertType(legalPersonCertType);
                    tblMerInfoDO.setLegalPersonCertNm(legalPersonCertNm);
                    tblMerInfoDO.setLegalPersonCertExpire(DateUtil.removeLineDateString(legalPersonCertExpire));
                    tblMerInfoDO.setContactPerson(contactPerson);
                    tblMerInfoDO.setContactMobile(contactMobile);
                    tblMerInfoDO.setContactEmail(contactEmail);
                    tblMerInfoDO.setFuncStat("NNNNNNNNNN");                   
                    tblMerInfoDO.setMerStat(Constant.AUDIT_NONE);// 批量进件状态是未审核        
                    tblMerInfoDO.setUserName(userName);
                    tblMerInfoDO.setUpdateTime(nowTime);
                    tblMerInfoDO.setCreateTime(nowTime);
                    tblMerInfoDOMapper.insertSelective(tblMerInfoDO);
                    logger.info("商户信息插入完毕，商户号："+merId);
                    TblMerBankInfoDO tblMerBankInfoDO = new TblMerBankInfoDO();
                    tblMerBankInfoDO.setBankName(bankName);
                    tblMerBankInfoDO.setBankBranchName(bankBranchName);
                    tblMerBankInfoDO.setMerId(platFormMerId);
                    tblMerBankInfoDO.setAcctName(acctName);
                    tblMerBankInfoDO.setAcctNo(acctNo);
                    tblMerBankInfoDO.setCnaps(cnaps);
                    tblMerBankInfoDO.setIsPrivate(isPrivate);
                    tblMerBankInfoDO.setProvName(bankProv);
                    tblMerBankInfoDO.setCityName(bankCity);
                    tblMerBankInfoDO.setUserName(userName);
                    tblMerBankInfoDO.setUpdateTime(nowTime);
                    tblMerBankInfoDO.setCreateTime(nowTime);
                    tblMerBankInfoDOMapper.insertSelective(tblMerBankInfoDO);
                    logger.info("商户银行信息插入完毕，商户号："+merId);
                    AgentMerFeeForm agentMerFeeForm = new AgentMerFeeForm();
                    agentMerFeeForm.setFee01L(fee01L);
                    agentMerFeeForm.setFee01(fee01);
                    agentMerFeeForm.setFee02(fee02);
                    agentMerFeeForm.setFee03(fee03);
                    agentMerFeeForm.setFee04(fee04);
                    agentMerFeeForm.setFee05(fee05);
                    agentMerFeeForm.setFee06(fee06);
                    agentMerFeeForm.setFee07(fee07);
                    agentMerFeeForm.setFee08(fee08);
                    agentMerFeeForm.setMerId(platFormMerId);
                    merAgentService.insertTblMerFeeInfo(agentMerFeeForm,platFormMerId,"00000000",userName);
                    logger.info("商户费率信息插入完毕，商户号："+merId);
                    TblAgentMerTermDo tblAgentMerTermDo = new TblAgentMerTermDo();
                    tblAgentMerTermDo.setMerId(platFormMerId);
                    tblAgentMerTermDo.setAgentId(agetId);
                    tblAgentMerTermDo.setInputMerId(merId);
                    tblAgentMerTermDo.setUserName(userName);
                    tblAgentMerTermDo.setUpdateTime(nowTime);
                    tblAgentMerTermDo.setCreateTime(nowTime);
                    merAgentService.insertTblAgentMerTerm(tblAgentMerTermDo);
                    logger.info("代理商商户关联信息插入完毕，商户号："+merId);
                    tblAgentMerAddDetailInfoDO.setResultFlag("S");
//                    tblAgentMerAddDetailInfoDO.setMerId(platFormMerId);
                    tblAgentMerAddDetailInfoDO.setResultDesc("保存成功");
                    tblAgentMerAddDetailInfoDO.setUpdateTime(DateUtil.date2String(new Date(),"yyyyMMddHHmmss"));
                    tblAgentMerAddDetailInfoDOMapper.updateByPrimaryKeySelective(tblAgentMerAddDetailInfoDO);
                }catch (Exception e) {
                    recordDetailBatchNoFalse(tblAgentMerAddDetailInfoDO,"保存时出现异常");
                    logger.error("保存时出现异常，商户号："+merId);
                    continue;
                }
                tblAgentMerAddBatchDO.setResultFlag("S");
                tblAgentMerAddBatchDO.setResultDesc("批量保存代理商商户信息成功");
                tblAgentMerAddBatchDO.setUpdateTime(DateUtil.date2String(new Date(),"yyyyMMddHHmmss"));
                tblAgentMerAddBatchDOMapper.updateByPrimaryKeySelective(tblAgentMerAddBatchDO);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return returnFalseBatchNo(tblAgentMerAddBatchDO,"读取Excel文件异常");
        }finally {
            return batchNo;
        }
    }

    private String returnFalseBatchNo(TblAgentMerAddBatchDO tblAgentMerAddBatchDO,String msg) {
        tblAgentMerAddBatchDO.setResultFlag("F");
        tblAgentMerAddBatchDO.setResultDesc(msg);
        tblAgentMerAddBatchDO.setUpdateTime(DateUtil.date2String(new Date(),"yyyyMMddHHmmss"));
        tblAgentMerAddBatchDOMapper.updateByPrimaryKeySelective(tblAgentMerAddBatchDO);
        return tblAgentMerAddBatchDO.getBatchNo();
    }

    private void recordDetailBatchNoFalse(TblAgentMerAddDetailInfoDO tblAgentMerAddDetailInfoDO,String msg) {
        tblAgentMerAddDetailInfoDO.setResultFlag("F");
        tblAgentMerAddDetailInfoDO.setResultDesc(msg);
        tblAgentMerAddDetailInfoDO.setUpdateTime(DateUtil.date2String(new Date(),"yyyyMMddHHmmss"));
        tblAgentMerAddDetailInfoDOMapper.updateByPrimaryKeySelective(tblAgentMerAddDetailInfoDO);
//        return tblAgentMerAddDetailInfoDO.getBatchNo();
    }
    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }
    @Override
    public List<TblAgentMerAddDetailInfoDO> queryAddDetailInfoByBatchNo(String BatchNo) {
        TblAgentMerAddDetailInfoDOExample tblAgentMerAddDetailInfoDOExample = new TblAgentMerAddDetailInfoDOExample();
        tblAgentMerAddDetailInfoDOExample.createCriteria().andBatchNoEqualTo(BatchNo);
        List<TblAgentMerAddDetailInfoDO> tblAgentMerAddDetailInfoDOList = tblAgentMerAddDetailInfoDOMapper.selectByExample(tblAgentMerAddDetailInfoDOExample);
        return tblAgentMerAddDetailInfoDOList;
    }

    private String getBatchNo() {
        long batchSeq = seqMapper.getSequenceNextVal("ADD_BATCH_AGENT_MER_SEQ");
        return DateUtil.getCurrentDate() + StringUtils.leftPad(String.valueOf(batchSeq), 8, '0');
    }

    public String getPlatFormMerId() {
        long platFormMerIdSeq = seqMapper.getSequenceNextVal("SEQ_PLATFORM_MER_ID");
        return DateUtil.getCurrentDate() + StringUtils.leftPad(String.valueOf(platFormMerIdSeq), 7, '0');
    }

    public String getPlatFormTermId() {
        long platFormTermIdSeq = seqMapper.getSequenceNextVal("SEQ_PLATFORM_TERM_ID");
        return StringUtils.leftPad(String.valueOf(platFormTermIdSeq), 8, '0');
    }
}
