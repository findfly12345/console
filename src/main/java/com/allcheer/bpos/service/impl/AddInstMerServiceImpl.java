package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.constant.NotifyException;
import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.domain.Minsheng.IntoPicAddressRepuest;
import com.allcheer.bpos.domain.Minsheng.IntoPiecesRepuest;
import com.allcheer.bpos.entity.Enum.ErrorRespEnum;
import com.allcheer.bpos.entity.Enum.MerTypeEnum;
import com.allcheer.bpos.entity.Enum.TermTypeEnum;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.mapper.*;
import com.allcheer.bpos.service.AddInstMerService;
import com.allcheer.bpos.service.AddressService;
import com.allcheer.bpos.service.OpenLoginUserService;
import com.allcheer.bpos.service.QrcodeService;
import com.allcheer.bpos.util.*;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by APPLE on 16/10/19.
 */
@Service("addInstMerService")
public class AddInstMerServiceImpl implements AddInstMerService {

	private final static Logger logger = LoggerFactory.getLogger(AddInstMerServiceImpl.class);

	@Autowired
	private SeqMapper seqMapper;

	@Autowired
	private TblInstMerAddBatchInfoDOMapper tblInstMerAddBatchInfoDOMapper;

	@Autowired
	private TblInstMerAddDetailInfoDOMapper tblInstMerAddDetailInfoDOMapper;

	@Autowired
	private TblMerInfoDOMapper tblMerInfoDOMapper;

	@Autowired
	private TblMerBankInfoDOMapper tblMerBankInfoDOMapper;

	@Autowired
	private TblMerFeeInfoDOMapper tblMerFeeInfoDOMapper;

	@Autowired
	private TblInstMerTermRelDOMapper tblInstMerTermRelDOMapper;

	@Autowired
	private TblTermInfoDOMapper tblTermInfoDOMapper;

	@Autowired
	private TblSubbranchInfoDOMapper tblSubbranchInfoDOMapper;

	@Autowired
	private TblBtsInstMccFeeInfoDOMapper tblBtsInstMccFeeInfoDOMapper;

	@Autowired
	private TblMerFileInfoDOMapper tblMerFileInfoDOMapper;

	@Autowired
	OpenLoginUserService openLoginUserService;

	@Autowired
	TblMerCoreMccDoMapper tblMerCoreMccDoMapper;

	@Autowired
	TblMerRelevanceMccDoMapper tblMerRelevanceMccDoMapper;

	@Autowired
	TblMerInfoDOMapper merInfoDOMapper;

	@Autowired
	TblProvDOMapper tblProvDOMapper;

	@Autowired
	TblCityDOMapper tblCityDOMapper;

	@Autowired
	TblBankInfoMapper tblBankInfoMapper;
	
    @Autowired
    QrcodeService qrcodeService;
    
    @Autowired
    TblAreaCodeMapper AreaCodeMapper;
    
	@Autowired
	AddressService addressService;
	
	@Autowired
	TblCustAddressDoMapper CustAddressDoMapper;
    
	@Autowired 
	TblRouteControlMapper RouteControlMapper;
	
	@Autowired
	TblInstRouteControlMapper InstRouteControlMapper;
	
	@SuppressWarnings({ "unchecked", "finally" })
	@Override
	public String addInstMerData(String instId, String dataFile, String userName) {
		
		logger.info("机构商户进件（数据文件）开始");
		String batchNo = getBatchNo();
		TblInstMerAddBatchInfoDO tblInstMerAddBatchInfoDO = new TblInstMerAddBatchInfoDO();
		tblInstMerAddBatchInfoDO.setBatchNo(batchNo);
		tblInstMerAddBatchInfoDO.setInstId(instId);
		tblInstMerAddBatchInfoDO.setFilePath(dataFile);
		tblInstMerAddBatchInfoDO.setFileType("0");
		tblInstMerAddBatchInfoDO.setResultFlag("I");
		tblInstMerAddBatchInfoDO.setResultDesc("初始化");
		tblInstMerAddBatchInfoDO.setUserName(userName);
		String resultFilePath = SystemConstant.ADD_INST_MER_RESULT_FILE + batchNo + ".xls";
		tblInstMerAddBatchInfoDO.setResultFilePath(resultFilePath);
		tblInstMerAddBatchInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblInstMerAddBatchInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblInstMerAddBatchInfoDOMapper.insertSelective(tblInstMerAddBatchInfoDO);

		logger.debug("机构手续费校验");
		TblBtsInstMccFeeInfoDOExample tblBtsInstMccFeeInfoDOExample = new TblBtsInstMccFeeInfoDOExample();
		tblBtsInstMccFeeInfoDOExample.createCriteria().andInstIdEqualTo(instId);
		List<TblBtsInstMccFeeInfoDO> tblBtsInstMccFeeInfoDOList = tblBtsInstMccFeeInfoDOMapper
				.selectByExample(tblBtsInstMccFeeInfoDOExample);

		if (tblBtsInstMccFeeInfoDOList == null || tblBtsInstMccFeeInfoDOList.size() == 0) {
			tblInstMerAddBatchInfoDO.setResultFlag("F");
			tblInstMerAddBatchInfoDO.setResultDesc("机构基础费率还未配置");
			tblInstMerAddBatchInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddBatchInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddBatchInfoDO);
			return batchNo;
		}

		Map<String, String> instFeeMap = new HashMap<String, String>();
		// 机构借记卡基础费率
		String instPosDebitFeeRate = "0";
		String instPosDebitFeeMax = "0";
		instFeeMap = getInstFee(tblInstMerAddBatchInfoDO, instId, "01");
		if (instFeeMap.isEmpty()) {
			return batchNo;
		}
		instPosDebitFeeRate = instFeeMap.get("instFeeRate");
		instPosDebitFeeMax = instFeeMap.get("instFeeMax");
		instFeeMap.clear();

		// 机构贷记卡基础费率
		String instPosCreditFeeRate = "0";
		instFeeMap = getInstFee(tblInstMerAddBatchInfoDO, instId, "02");

		if (instFeeMap.isEmpty()) {
			return batchNo;
		}
		instPosCreditFeeRate = instFeeMap.get("instFeeRate");
		instFeeMap.clear();

		// 微信T0交易费率
		String instWechatFeeRateT0 = "0";
		instFeeMap = getInstFee(tblInstMerAddBatchInfoDO, instId, "03");

		if (instFeeMap.isEmpty()) {
			return batchNo;
		}
		instWechatFeeRateT0 = instFeeMap.get("instFeeRate");
		instFeeMap.clear();

		// 微信T1交易费率
		String instWechatFeeRateT1 = "0";
		instFeeMap = getInstFee(tblInstMerAddBatchInfoDO, instId, "05");

		if (instFeeMap.isEmpty()) {
			return batchNo;
		}
		instWechatFeeRateT1 = instFeeMap.get("instFeeRate");
		instFeeMap.clear();

		// 支付宝T0交易费率
		String instAliPayFeeRateT0 = "0";
		instFeeMap = getInstFee(tblInstMerAddBatchInfoDO, instId, "04");

		if (instFeeMap.isEmpty()) {
			return batchNo;
		}
		instAliPayFeeRateT0 = instFeeMap.get("instFeeRate");
		instFeeMap.clear();

		// 支付宝T1交易费率
		String instAliPayFeeRateT1 = "0";
		instFeeMap = getInstFee(tblInstMerAddBatchInfoDO, instId, "06");

		if (instFeeMap.isEmpty()) {
			return batchNo;
		}
		instAliPayFeeRateT1 = instFeeMap.get("instFeeRate");
		instFeeMap.clear();

		// 提现手续费-单笔
		String instWithdrawalFee = "0";
		instFeeMap = getInstFee(tblInstMerAddBatchInfoDO, instId, "07");

		if (instFeeMap.isEmpty()) {
			return batchNo;
		}
		instWithdrawalFee = instFeeMap.get("instFeeRate");
		instFeeMap.clear();

		// 提现手续费- 比例 - 垫资
		String instWithdrawalFeeRate = "0";
		instFeeMap = getInstFee(tblInstMerAddBatchInfoDO, instId, "08");

		if (instFeeMap.isEmpty()) {
			return batchNo;
		}
		instWithdrawalFeeRate = instFeeMap.get("instFeeRate");
		instFeeMap.clear();

		logger.debug("机构商户进件（数据文件），读取文件内容");
		ExcelReader excelReader = new ExcelReader();
		
		String platMerId = "";
		String platTermId = "";
		
		try {
			List<List<Object>> rows = excelReader.read2003Excel(new File(dataFile));
			for (List<Object> cells : rows) {
				String instIdFile = ((String) cells.get(0)).trim();// 合作机构号
				String merId = ((String) cells.get(1)).trim();// 商户号
				String merName = ((String) cells.get(2)).trim();// 商户名
				String merType = ((String) cells.get(3)).trim();// 商户类型
				String legalPersonCertType = ((String) cells.get(12)).trim();// 法人代表证件类型
				String legalPersonCertExpire = ((String) cells.get(14)).trim();// 法人代表证件号有效期
				String bankName = ((String) cells.get(18)).trim();// 开户行
				String bankProv = ((String) cells.get(19)).trim();// 开户行省
				String bankCity = ((String) cells.get(20)).trim();// 开户行市
				String bankBranchName = ((String) cells.get(21)).trim();// 开户支行
				String cnaps = ((String) cells.get(22)).trim();// 联行号
				String acctNo = ((String) cells.get(25)).trim();// 账户号
				String posDebitFeeRate = ((String) cells.get(26)).trim();// POS借记卡-比例
				String posDebitFeeMax = ((String) cells.get(27)).trim();// POS借记卡-封顶
				String posCreditFeeRate = ((String) cells.get(28)).trim();// POS贷记卡-比例
				String wechatFeeRateT0 = ((String) cells.get(29)).trim();// 微信T0交易费率
				String wechatFeeRateT1 = ((String) cells.get(30)).trim();// 微信T1交易费率
				String aliPayFeeRateT0 = ((String) cells.get(31)).trim();// 微信T0交易费率
				String aliPayFeeRateT1 = ((String) cells.get(32)).trim();// 微信T1交易费率
				String withdrawalFeeType = ((String) cells.get(33)).trim();// 提现手续费类型
				String withdrawalFee = ((String) cells.get(34)).trim();// 提现手续费
				String withdrawalFeeRate = ((String) cells.get(35)).trim();// 垫资手续费
				String termId = ((String) cells.get(36)).trim();// 终端号
				String termName = ((String) cells.get(37)).trim();// 终端名称
				String termType = ((String) cells.get(38)).trim();// 终端类型
				String termCounty = ((String) cells.get(41)).trim();// 终端安装县
				String termAddress = ((String) cells.get(42)).trim();// 终端安装详细地址
				String termSn = ((String) cells.get(43)).trim();// 终端SN号

				
				String merProvinceCode = "";// 商户地址省份编码
				String merCityCode = "";// 商户地址城市编码
				String merAreaCode = "";// 商户地址区/县编码
				if(cells.size() >= 47){
					merProvinceCode = ((String) cells.get(46)).trim();// 商户地址省份编码
				}
				if(cells.size() >= 48){
					merCityCode = ((String) cells.get(47)).trim();// 商户地址城市编码
				}
				if(cells.size() >= 49){
					merAreaCode = ((String) cells.get(48)).trim();// 商户地址区/县编码
				}				

				if (StringUtils.isBlank(instIdFile) || StringUtils.isBlank(merId) || StringUtils.isBlank(termId)) {
					logger.error("instIdFile:{},merId:{},termId:{}都不能为空", instIdFile, merId, termId);
					continue;
				}

				logger.debug("机构商户进件（数据文件），初始化详细表，instId{},merId{},termId{}", instId, merId, termId);

				TblInstMerAddDetailInfoDO tblInstMerAddDetailInfoDO = new TblInstMerAddDetailInfoDO();
				tblInstMerAddDetailInfoDO.setBatchNo(batchNo);
				tblInstMerAddDetailInfoDO.setInstId(instIdFile);
				tblInstMerAddDetailInfoDO.setInstMerId(merId);
				tblInstMerAddDetailInfoDO.setInstTermId(termId);
				tblInstMerAddDetailInfoDO.setResultFlag("I");
				tblInstMerAddDetailInfoDO.setResultDesc("初始");
				tblInstMerAddDetailInfoDO.setUserName(userName);
				tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
				tblInstMerAddDetailInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
				tblInstMerAddDetailInfoDOMapper.insert(tblInstMerAddDetailInfoDO);

				// 检验机构商户是否曾经入驻过
				logger.debug("检验机构商户是否曾经入驻过");
				Map<String, String> mer_already_in = new HashMap<String, String>();
				mer_already_in = merAlreadyExist(instId, merId, termId, acctNo);

				if (mer_already_in.containsKey("MER_EXISTING") && mer_already_in.get("MER_EXISTING").equals("Y")) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("此机构商户已经在系统中存在");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				if (mer_already_in.containsKey("BANK_ACCT_ALREADY_USED") && mer_already_in.get("BANK_ACCT_ALREADY_USED").equals("Y")) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("此机构商户银行账号已经被使用");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				// 检验商户录入信息是否正确
				logger.debug("检验录入商户信息");
				if (!instIdFile.equals(instId)) {
					logger.debug("机构号错误instIdFile{},instId{}", instIdFile, instId);
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("机构号错误");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				if (StringUtils.isBlank(merName)) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("商户名不能为空");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				if (StringUtils.isBlank(MerTypeEnum.getMessage(merType))) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("商户类型不存在");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				if (legalPersonCertType.equals("身份证")) {
					legalPersonCertType = "0";
				}
				if (legalPersonCertType.equals("护照")) {
					legalPersonCertType = "1";
				}
				if (!legalPersonCertType.equals("0") && !legalPersonCertType.equals("1")) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("法人代表证件类型不存在");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				if (!isValidDate(legalPersonCertExpire)) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("法人证件有效期错误");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				TblSubbranchInfoDOExample tblSubbranchInfoDOExample = new TblSubbranchInfoDOExample();
				tblSubbranchInfoDOExample.createCriteria().andSubbranchIdEqualTo(cnaps);

				List<TblSubbranchInfoDO> tblSubbranchInfoDOList = tblSubbranchInfoDOMapper
						.selectByExample(tblSubbranchInfoDOExample);
				if (tblSubbranchInfoDOList == null || tblSubbranchInfoDOList.size() == 0) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("结算分行不存在");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				String sysBankProv = tblSubbranchInfoDOList.get(0).getProvince();
				String sysBankCity = tblSubbranchInfoDOList.get(0).getCity();
				String sysBankName = tblSubbranchInfoDOList.get(0).getBankName();
				String sysSubBranchName = tblSubbranchInfoDOList.get(0).getSubbranchName();
				
				if (StringUtils.isBlank(bankName)) {
					bankName = sysBankName;
					cells.set(18, bankName);
				}
				if (StringUtils.isBlank(bankProv)) {
					bankProv = sysBankProv;
					cells.set(19, bankProv);
				}
				if (StringUtils.isBlank(bankCity)) {
					bankCity = sysBankCity;
					cells.set(20, bankCity);
				}
				if (StringUtils.isBlank(bankBranchName)) {
					bankBranchName = sysSubBranchName;
					cells.set(21, bankBranchName);
				}				
				
				if (StringUtils.isBlank(posDebitFeeRate)) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("POS借记卡-比例不能为空");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}
		
				if (StringUtils.isBlank(posDebitFeeMax)) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("POS借记卡-封顶不能为空");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				if (StringUtils.isBlank(posCreditFeeRate)) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("POS贷记卡-比例不能为空");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}
				 
				if(!StringUtil.isBlank(wechatFeeRateT0) && wechatFeeRateT0.equals("0")) {
					wechatFeeRateT0 = "";
				}
				if(!StringUtil.isBlank(wechatFeeRateT1) && wechatFeeRateT1.equals("0")) {
					wechatFeeRateT1 = "";
				}
				if(!StringUtil.isBlank(aliPayFeeRateT0) && aliPayFeeRateT0.equals("0")) {
					aliPayFeeRateT0 = "";
				}
				if(!StringUtil.isBlank(aliPayFeeRateT1) && aliPayFeeRateT1.equals("0")) {
					aliPayFeeRateT1 = "";
				}
				if(!StringUtil.isBlank(withdrawalFee) && withdrawalFee.equals("0")) {
					withdrawalFee = "";
				}
				if(!StringUtil.isBlank(withdrawalFeeRate) && withdrawalFeeRate.equals("0")) {
					withdrawalFeeRate = "";
				}	
				 
				if (!StringUtils.isBlank(withdrawalFeeType)) {
					if (!withdrawalFeeType.equals("1") && !withdrawalFeeType.equals("0") && !withdrawalFeeType.equals("2")) {
						tblInstMerAddDetailInfoDO.setResultFlag("F");
						tblInstMerAddDetailInfoDO.setResultDesc("提现手续费类型不存在");
						tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
						tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
						continue;
					}
					if (withdrawalFeeType.equals("0") && StringUtil.isBlank(withdrawalFee)) {
						tblInstMerAddDetailInfoDO.setResultFlag("F");
						tblInstMerAddDetailInfoDO.setResultDesc("未提供提现手续费");
						tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
						tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
						continue;
					}

					if (withdrawalFeeType.equals("1") && StringUtil.isBlank(withdrawalFeeRate)) {
						tblInstMerAddDetailInfoDO.setResultFlag("F");
						tblInstMerAddDetailInfoDO.setResultDesc("未提供垫资手续费");
						tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
						tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
						continue;
					}
					if (withdrawalFeeType.equals("2") && StringUtil.isBlank(withdrawalFeeRate) && StringUtil.isBlank(withdrawalFee)) {
						tblInstMerAddDetailInfoDO.setResultFlag("F");
						tblInstMerAddDetailInfoDO.setResultDesc("未提供提现和垫资手续费");
						tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
						tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
						continue;
					}
				}

				// 比较费率设置
				logger.debug("校验费率是否大于机构费率");
				
				Boolean feeError = false; 
				feeError = compareFees(tblInstMerAddDetailInfoDO, posDebitFeeRate, instPosDebitFeeRate, posDebitFeeMax,
						instPosDebitFeeMax, posCreditFeeRate, instPosCreditFeeRate, wechatFeeRateT0,
						instWechatFeeRateT0, wechatFeeRateT1, instWechatFeeRateT1, aliPayFeeRateT0, instAliPayFeeRateT0,
						aliPayFeeRateT1, instAliPayFeeRateT1, withdrawalFeeType, withdrawalFee, instWithdrawalFee,
						withdrawalFeeRate, instWithdrawalFeeRate);
				if (feeError) {
					continue;
				}
					
				if (StringUtils.isBlank(TermTypeEnum.getMessage(termType))) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("终端类型不存在");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				if (StringUtils.isBlank(termId)) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("终端号不能为空");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				if (StringUtils.isBlank(termName)) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("终端名称不能为空");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				if (StringUtils.isBlank(termCounty)) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("终端安装县市不能为空");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				if (StringUtils.isBlank(termAddress)) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("终端地址不能为空");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}

				if (StringUtils.isBlank(termSn)) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("终端SN号不能为空");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;
				}
				
				// 检验注册城市编码
				logger.info("检验注册城市编码");
				if(StringUtils.isBlank(merProvinceCode) && StringUtils.isBlank(merCityCode) && StringUtils.isBlank(merAreaCode)) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("商户地址省市/地区编码不能全为空");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;					
				}
				if(StringUtils.isBlank(merAreaCode)) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("商户地区编码不能全为空");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;					
				}
				
				Map<String, String> addressMap = addressService.getCorrectAddressCodes(merProvinceCode, merCityCode, merAreaCode);
	    		if(addressMap != null && addressMap.containsKey("ERROR")){
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc(addressMap.get("ERROR"));
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					continue;	
	    		}					
	    		merProvinceCode = addressMap.get("PROVINCE");
	    		merCityCode = addressMap.get("CITY");
	    		merAreaCode = addressMap.get("AREA");
	    		cells.set(46, merProvinceCode);
	    		cells.set(47, merCityCode);
	    		cells.set(48, merAreaCode);
	    		
				logger.info("机构商户进件（数据文件）,生成平台的相应数据");				
				try {

					logger.info("处理添加");
					Map<String, String> resultMap = addPlatFormMerData(mer_already_in, cells, userName);

					logger.info("处理添加返回");
					Boolean hasError = processReturnMessage(tblInstMerAddDetailInfoDO, resultMap);
					if (hasError) {
						continue;
					}

					platMerId = resultMap.get("platFormMerId");
					platTermId = resultMap.get("platFormTermId");
					
					tblInstMerAddDetailInfoDO.setMerId(platMerId);
					tblInstMerAddDetailInfoDO.setTermId(platTermId);
					
					logger.debug("为机构商户开通大众登陆账户");
					openLoginUserService.openNewGeneralMer((String) resultMap.get("platFormMerId"));
					tblInstMerAddDetailInfoDO.setMerId((String) resultMap.get("platFormMerId"));
					tblInstMerAddDetailInfoDO.setTermId((String) resultMap.get("platFormTermId"));
					tblInstMerAddDetailInfoDO.setResultFlag("S");
					tblInstMerAddDetailInfoDO.setResultDesc("成功");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddDetailInfoDO);
					
					logger.info("为机构商户生成二维码");
		        	String createOrNot = SystemConstant.TO_CREATE_QRCODE;
		    		Boolean toCreate = false;
		    		if (StringUtils.isNotBlank(createOrNot) && createOrNot.equals("Y")){
		    			toCreate = true; 
		    		}
		    		
		    		if (!toCreate) {
		               logger.info("二维码功能尚未打开");
		    		} else {
		    			Map<String, String> qrcodeMap = new HashMap<String, String>();       	
		            	qrcodeMap = qrcodeService.QrcodeCreate(instId, null, merId, null);
		            	
		            	if (qrcodeMap != null && qrcodeMap.containsKey("imageLocation")) {
		            		String qrcodeImagePath = qrcodeMap.get("imageLocation");
		            		Boolean status = qrcodeService.QrcodeSaving(merId, instId, null, null, qrcodeImagePath);
		            		if (!status) {
		    					tblInstMerAddDetailInfoDO.setResultFlag("F");
		    					tblInstMerAddDetailInfoDO.setResultDesc("保存二维码失败");
		    					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		    					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddDetailInfoDO);
		            		}
		            	} else {
	    					tblInstMerAddDetailInfoDO.setResultFlag("F");
	    					tblInstMerAddDetailInfoDO.setResultDesc("生成二维码失败");
	    					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
	    					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddDetailInfoDO);
		            	}     			
		    		}					
				} catch (Exception e) {
					logger.error("机构商户进件（数据文件）,instId{},merId{},termId{}生成平台的相应数据异常{}", instId, merId, termId, e);
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("机构商户进件（数据文件）,生成平台的相应数据异常");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddDetailInfoDO);
				}
			}
			
			TblInstMerAddDetailInfoDOExample tblInstMerAddDetailInfoDOExample = new TblInstMerAddDetailInfoDOExample();
			tblInstMerAddDetailInfoDOExample.createCriteria().andBatchNoEqualTo(batchNo);
			List<TblInstMerAddDetailInfoDO> tblInstMerAddDetailInfoDOList = tblInstMerAddDetailInfoDOMapper
					.selectByExample(tblInstMerAddDetailInfoDOExample);
			ExcelUtil excelUtil = new ExcelUtil();
			excelUtil.setSrcPath(SystemConstant.ADD_INST_MER_RESULT_FILE_TEMP);
			excelUtil.getSheetByIndex(0);

			for (int i = 0; i < tblInstMerAddDetailInfoDOList.size(); i++) {
				int rowNum = i + 1;
				TblInstMerAddDetailInfoDO tblInstMerAddDetailInfoDO = tblInstMerAddDetailInfoDOList.get(i);
				excelUtil.setCellStrValue(rowNum, 0, batchNo);
				excelUtil.setCellStrValue(rowNum, 1, tblInstMerAddDetailInfoDO.getInstId());
				excelUtil.setCellStrValue(rowNum, 2, tblInstMerAddDetailInfoDO.getInstMerId());
				excelUtil.setCellStrValue(rowNum, 3, tblInstMerAddDetailInfoDO.getInstTermId());
				excelUtil.setCellStrValue(rowNum, 4, tblInstMerAddDetailInfoDO.getMerId());
				excelUtil.setCellStrValue(rowNum, 5, tblInstMerAddDetailInfoDO.getTermId());
				excelUtil.setCellStrValue(rowNum, 6, tblInstMerAddDetailInfoDO.getResultFlag());
				excelUtil.setCellStrValue(rowNum, 7, tblInstMerAddDetailInfoDO.getResultDesc());
				
			}
			File resultFilePathFile = new File(SystemConstant.ADD_INST_MER_RESULT_FILE);
			if (!resultFilePathFile.exists()) {
				resultFilePathFile.mkdirs();
			}
			File resultFile = new File(resultFilePath);

			excelUtil.setDesPath(resultFile.getCanonicalPath());
			excelUtil.exportToNewFile();

			tblInstMerAddBatchInfoDO.setResultFlag("S");
			tblInstMerAddBatchInfoDO.setResultDesc("批量进件完成");
			tblInstMerAddBatchInfoDO.setResultFilePath(resultFilePath);
			tblInstMerAddBatchInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddBatchInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddBatchInfoDO);
		} catch (IOException e) {
			logger.error("机构商户进件（数据文件），读取文件异常{}", e);
			e.printStackTrace();
			tblInstMerAddBatchInfoDO.setResultFlag("F");
			tblInstMerAddBatchInfoDO.setResultDesc("机构商户进件（数据文件），读取文件异常");
			tblInstMerAddBatchInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddBatchInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddBatchInfoDO);
		} finally {
			return batchNo;
		}
	}

	/**
	 * 处理添加错误返回信息
	 */
	private Boolean processReturnMessage(TblInstMerAddDetailInfoDO tblInstMerAddDetailInfoDO,
			Map<String, String> resultMap) {

		Boolean hasError = false;
		
		if (resultMap.get("addStatus") == null) 
			return hasError;
		
		if (resultMap.get("addStatus").equals("FAIL_TO_ADD_MER")) {
			tblInstMerAddDetailInfoDO.setResultFlag("F");
			tblInstMerAddDetailInfoDO.setResultDesc("创建商户信息失败");
			tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
			hasError = true;
			return hasError;
		}

		if (resultMap.get("addStatus").equals("FAIL_TO_ADD_MER_BANK")) {
			tblInstMerAddDetailInfoDO.setResultFlag("F");
			tblInstMerAddDetailInfoDO.setResultDesc("创建商户结算信息失败");
			tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
			hasError = true;
			return hasError;
		}

		if (resultMap.get("addStatus").equals("FAIL_TO_ADD_MER_FEE")) {
			tblInstMerAddDetailInfoDO.setResultFlag("F");
			tblInstMerAddDetailInfoDO.setResultDesc("创建商户费率信息失败");
			tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
			hasError = true;
			return hasError;
		}
		if (resultMap.get("addStatus").equals("FAIL_TO_ADD_MER_TERM")) {
			tblInstMerAddDetailInfoDO.setResultFlag("F");
			tblInstMerAddDetailInfoDO.setResultDesc("创建商户终端信息失败");
			tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
			hasError = true;
			return hasError;
		}
		if (resultMap.get("addStatus").equals("FAIL_TO_ADD_MER_TERM_REL")) {
			tblInstMerAddDetailInfoDO.setResultFlag("F");
			tblInstMerAddDetailInfoDO.setResultDesc("创建商户终端关联信息失败");
			tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
			hasError = true;
			return hasError;
		}
		if (resultMap.get("addStatus").equals("FAIL_TO_ADD_MER_MCC")) {
			tblInstMerAddDetailInfoDO.setResultFlag("F");
			tblInstMerAddDetailInfoDO.setResultDesc("创建商户MCC信息失败");
			tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
			hasError = true;
			return hasError;
		}

		return hasError;

	}

	
	@SuppressWarnings("finally")
	@Transactional
	@Override
	public String addInstMerAttachment(String instId, String attachmentFile, String attachmentFileName,
			String userName) {
		logger.debug("机构商户进件（附件文件），初始化批次表{},{},{},{}", instId, attachmentFile, attachmentFileName, userName);
		String batchNo = getBatchNo();
		TblInstMerAddBatchInfoDO tblInstMerAddBatchInfoDO = new TblInstMerAddBatchInfoDO();
		tblInstMerAddBatchInfoDO.setBatchNo(batchNo);
		tblInstMerAddBatchInfoDO.setInstId(instId);
		tblInstMerAddBatchInfoDO.setFilePath(attachmentFile);
		tblInstMerAddBatchInfoDO.setFileType("1");
		tblInstMerAddBatchInfoDO.setResultFlag("I");
		tblInstMerAddBatchInfoDO.setResultDesc("初始化");
		tblInstMerAddBatchInfoDO.setUserName(userName);
		tblInstMerAddBatchInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblInstMerAddBatchInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblInstMerAddBatchInfoDOMapper.insertSelective(tblInstMerAddBatchInfoDO);
		logger.debug("机构商户进件（附件文件），初始化批次表成功");

		try {
			String attachmentFileType = attachmentFileName.split("\\.")[1];
			attachmentFileName = attachmentFileName.split("\\.")[0];
			if (!attachmentFileType.equals("zip")) {
				tblInstMerAddBatchInfoDO.setResultFlag("F");
				tblInstMerAddBatchInfoDO.setResultDesc("机构商户进件（附件文件)格式错误");
				tblInstMerAddBatchInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
				tblInstMerAddBatchInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddBatchInfoDO);
			}
			String instIdFile = attachmentFileName.split("\\_")[1];
			if (!instIdFile.equals(instId)) {
				tblInstMerAddBatchInfoDO.setResultFlag("F");
				tblInstMerAddBatchInfoDO.setResultDesc("机构号错误");
				tblInstMerAddBatchInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
				tblInstMerAddBatchInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddBatchInfoDO);
			}
			// String destFilePath = UnixUtil.doExecUnixUNZipCMD(attachmentFile,
			// SystemConstant.ADD_INST_MER_ATTACHMENT_FILE_PATH);
			String destFilePath = ZipUtil.unzip(attachmentFile, SystemConstant.ADD_INST_MER_ATTACHMENT_FILE_PATH);
			logger.debug("商户解压目录{}", destFilePath);
			File attachmentFileHome = new File(destFilePath + attachmentFileName);
			logger.debug("商户附件主目录{}", destFilePath + attachmentFileName);
			File[] files = attachmentFileHome.listFiles();
			TblInstMerAddDetailInfoDO tblInstMerAddDetailInfoDO = new TblInstMerAddDetailInfoDO();
			for (int i = 0; i < files.length; i++) {
				try {
					String merId = files[i].getName().split("_")[0];
					String termId = files[i].getName().split("_")[1];
					logger.debug("机构商户进件（附件文件），初始化详细表，instId{},merId{},termId{}", instId, merId, termId);
					tblInstMerAddDetailInfoDO.setBatchNo(batchNo);
					tblInstMerAddDetailInfoDO.setInstId(instId);
					tblInstMerAddDetailInfoDO.setInstMerId(merId);
					tblInstMerAddDetailInfoDO.setInstTermId(termId);
					tblInstMerAddDetailInfoDO.setResultFlag("I");
					tblInstMerAddDetailInfoDO.setResultDesc("初始");
					tblInstMerAddDetailInfoDO.setUserName(userName);
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.insertSelective(tblInstMerAddDetailInfoDO);
					TblInstMerTermRelDOExample tblInstMerTermRelDOExample = new TblInstMerTermRelDOExample();
					tblInstMerTermRelDOExample.createCriteria().andInstMerIdEqualTo(merId).andInstTermIdEqualTo(termId)
							.andInstIdEqualTo(instIdFile);
					List<TblInstMerTermRelDO> tblInstMerTermRelDOList = tblInstMerTermRelDOMapper
							.selectByExample(tblInstMerTermRelDOExample);
					if (tblInstMerTermRelDOList == null || tblInstMerTermRelDOList.size() == 0) {
						tblInstMerAddDetailInfoDO.setResultFlag("F");
						tblInstMerAddDetailInfoDO.setResultDesc("商户数据文件还未报备");
						tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
						tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
						continue;
					} else {

						TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper
								.selectByPrimaryKey(tblInstMerTermRelDOList.get(0).getMerId());
						if (!tblMerInfoDO.getMerStat().equals(Constant.AUDIT_REJECT)) {
							tblInstMerAddDetailInfoDO.setResultFlag("F");
							tblInstMerAddDetailInfoDO.setResultDesc("商户已完成进件");
							tblInstMerAddDetailInfoDO
									.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
							tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
							continue;
						}

						// todo 数据迁移
						String dest = SystemConstant.MER_ATTACHMENT_FILE_PATH + merId;
						File file = new File(dest);
						if (!file.exists()) {
							file.mkdirs();
						}
						try {
							UnixUtil.doExecUnixCPCMD(files[i].getCanonicalPath(), "/.", dest);
							TblInstMerTermRelDO tblInstMerTermRelDO = tblInstMerTermRelDOList.get(0);
							tblMerInfoDO.setAttachmentPath(dest);
							tblMerInfoDO.setMerStat(Constant.AUDIT_NONE);
							tblMerInfoDO.setUserName(userName);
							tblMerInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
							tblMerInfoDOMapper.updateByPrimaryKeySelective(tblMerInfoDO);

							tblInstMerAddDetailInfoDO.setMerId(tblInstMerTermRelDO.getMerId());
							tblInstMerAddDetailInfoDO.setTermId(tblInstMerTermRelDO.getTermId());
							tblInstMerAddDetailInfoDO.setResultFlag("S");
							tblInstMerAddDetailInfoDO.setResultDesc("成功");
							tblInstMerAddDetailInfoDO
									.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
							tblInstMerAddDetailInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddDetailInfoDO);
						} catch (IOException e) {
							e.printStackTrace();
							tblInstMerAddDetailInfoDO.setResultFlag("F");
							tblInstMerAddDetailInfoDO.setResultDesc("商户附件迁移异常");
							tblInstMerAddDetailInfoDO
									.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
							tblInstMerAddDetailInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddDetailInfoDO);
						}
					}
				} catch (Exception e) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("商户附件上传异常");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddDetailInfoDO);
				}
			}
			TblInstMerAddDetailInfoDOExample tblInstMerAddDetailInfoDOExample = new TblInstMerAddDetailInfoDOExample();
			tblInstMerAddDetailInfoDOExample.createCriteria().andBatchNoEqualTo(batchNo);
			List<TblInstMerAddDetailInfoDO> tblInstMerAddDetailInfoDOList = tblInstMerAddDetailInfoDOMapper
					.selectByExample(tblInstMerAddDetailInfoDOExample);
			ExcelUtil excelUtil = new ExcelUtil();
			excelUtil.setSrcPath(SystemConstant.ADD_INST_MER_RESULT_FILE_TEMP);
			excelUtil.getSheetByIndex(0);

			for (int i = 0; i < tblInstMerAddDetailInfoDOList.size(); i++) {
				int rowNum = i + 1;
				tblInstMerAddDetailInfoDO = tblInstMerAddDetailInfoDOList.get(i);
				excelUtil.setCellStrValue(rowNum, 0, batchNo);
				excelUtil.setCellStrValue(rowNum, 1, tblInstMerAddDetailInfoDO.getInstId());
				excelUtil.setCellStrValue(rowNum, 2, tblInstMerAddDetailInfoDO.getInstMerId());
				excelUtil.setCellStrValue(rowNum, 3, tblInstMerAddDetailInfoDO.getInstTermId());
				excelUtil.setCellStrValue(rowNum, 4, tblInstMerAddDetailInfoDO.getMerId());
				excelUtil.setCellStrValue(rowNum, 5, tblInstMerAddDetailInfoDO.getTermId());
				excelUtil.setCellStrValue(rowNum, 6, tblInstMerAddDetailInfoDO.getResultFlag());
				excelUtil.setCellStrValue(rowNum, 7, tblInstMerAddDetailInfoDO.getResultDesc());
			}
			String resultFilePath = SystemConstant.ADD_INST_MER_RESULT_FILE;
			File resultFilePathFile = new File(resultFilePath);
			if (!resultFilePathFile.exists()) {
				resultFilePathFile.mkdirs();
			}
			File resultFile = new File(resultFilePath + batchNo + ".xls");

			try {
				excelUtil.setDesPath(resultFile.getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			excelUtil.exportToNewFile();

			tblInstMerAddBatchInfoDO.setResultFlag("S");
			tblInstMerAddBatchInfoDO.setResultDesc("附件上传完成");
			tblInstMerAddBatchInfoDO.setResultFilePath(resultFilePath + batchNo + ".xls");
			tblInstMerAddBatchInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddBatchInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddBatchInfoDO);
		} catch (Exception e) {
			logger.error("机构商户进件（数据文件），读取文件异常{}", e);
			e.printStackTrace();
			tblInstMerAddBatchInfoDO.setResultFlag("F");
			tblInstMerAddBatchInfoDO.setResultDesc("机构商户进件（附件文件)异常");
			tblInstMerAddBatchInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddBatchInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddBatchInfoDO);
		} finally {
			return batchNo;
		}
	}

	@Override
	public TblInstMerAddBatchInfoDO selectTblInstMerAddBatchInfoDOByPk(String batchNo) {
		return tblInstMerAddBatchInfoDOMapper.selectByPrimaryKey(batchNo);
	}

	private String getBatchNo() {
		long batchSeq = seqMapper.getSequenceNextVal("SEQ_ADD_INST_MER_BATCH_NO");
		return DateUtil.getCurrentDate() + StringUtils.leftPad(String.valueOf(batchSeq), 8, '0');
	}

	private String getPlatFormMerId() {
		long platFormMerIdSeq = seqMapper.getSequenceNextVal("SEQ_PLATFORM_MER_ID");
		return DateUtil.getCurrentDate() + StringUtils.leftPad(String.valueOf(platFormMerIdSeq), 7, '0');
	}

	private String getPlatFormTermId() {
		long platFormTermIdSeq = seqMapper.getSequenceNextVal("SEQ_PLATFORM_TERM_ID");
		return StringUtils.leftPad(String.valueOf(platFormTermIdSeq), 8, '0');
	}

	@Transactional
	private Map<String, String> addPlatFormMerData(Map<String, String> verifyMap, List<Object> cells, String userName) {

		Map<String, String> resultMap = new HashMap<String, String>();
		String instId = ((String) cells.get(0)).trim();// 合作机构号
		String merId = ((String) cells.get(1)).trim();// 商户号
		String merName = ((String) cells.get(2)).trim();// 商户名
		String merType = ((String) cells.get(3)).trim();// 商户类型
		String regName = ((String) cells.get(4)).trim(); // 注册名
		String regShortName = ((String) cells.get(5)).trim();// 注册简称
		String regAddress = ((String) cells.get(6)).trim();// 注册地址
		String regFunds = ((String) cells.get(7)).trim();// 注册资本
		String busLicNm = ((String) cells.get(8)).trim();// 营业执照编号
		String busLicExpire = ((String) cells.get(9)).trim();// 营业执照有效期
		String taxRegCert = ((String) cells.get(10)).trim();// 税务登记证
		String legalPerson = ((String) cells.get(11)).trim();// 法人代表
		String legalPersonCertType = ((String) cells.get(12)).trim();// 法人代表证件类型
		String legalPersonCertNm = ((String) cells.get(13)).trim();// 法人代表证件号
		String legalPersonCertExpire = ((String) cells.get(14)).trim();// 法人代表证件号有效期
		String contactPerson = ((String) cells.get(15)).trim();// 联系人
		String contactMobile = ((String) cells.get(16)).trim();// 联系号码
		String contactEmail = ((String) cells.get(17)).trim(); // 联系邮箱
		String bankName = ((String) cells.get(18)).trim();// 开户行
		String bankProv = ((String) cells.get(19)).trim();// 开户行省
		String bankCity = ((String) cells.get(20)).trim();// 开户行市
		String bankBranchName = ((String) cells.get(21)).trim();// 开户支行
		String cnaps = ((String) cells.get(22)).trim();// 联行号
		String isPrivate = ((String) cells.get(23)).trim();// 是否对私账户
		String acctName = ((String) cells.get(24)).trim();// 账户名
		String acctNo = ((String) cells.get(25)).trim();// 账户号
		String posDebitFeeRate = ((String) cells.get(26)).trim();// POS借记卡-比例
		String posDebitFeeMax = ((String) cells.get(27)).trim();// POS借记卡-封顶
		String posCreditFeeRate = ((String) cells.get(28)).trim();// POS贷记卡-比例
		String wechatFeeRateT0 = ((String) cells.get(29)).trim();// 微信T0交易费率
		String wechatFeeRateT1 = ((String) cells.get(30)).trim();// 微信T1交易费率
		String aliPayFeeRateT0 = ((String) cells.get(31)).trim();// 微信T0交易费率
		String aliPayFeeRateT1 = ((String) cells.get(32)).trim();// 微信T1交易费率
		String withdrawalFeeType = ((String) cells.get(33)).trim();// 提现手续费类型
		String withdrawalFee = ((String) cells.get(34)).trim();// 提现手续费
		String withdrawalFeeRate = ((String) cells.get(35)).trim();// 垫资手续费
		String termId = ((String) cells.get(36)).trim();// 终端号
		String termName = ((String) cells.get(37)).trim();// 终端名称
		String termType = ((String) cells.get(38)).trim();// 终端类型
		String termProv = ((String) cells.get(39)).trim();// 终端安装省
		String termCity = ((String) cells.get(40)).trim();// 终端安装市
		String termCounty = ((String) cells.get(41)).trim();// 终端安装县
		String termAddress = ((String) cells.get(42)).trim();// 终端安装详细地址
		String termSn = ((String) cells.get(43)).trim();// 终端SN号
		String mccCode = ((String) cells.get(44)).trim();// MCC码
		String settlePeriod = ((String) cells.get(45)).trim();// 结算周期
		String merProvinceCode = ((String) cells.get(46)).trim();// 商户地址省份编码
		String merCityCode = ((String) cells.get(47)).trim();// 商户地址城市编码
		String merAreaCode = ((String) cells.get(48)).trim();// 商户地址区/县编码

		String platFormMerId = "";
		String platFormTermId = "";
		if(verifyMap.get("GO_UPDATE_MER") != null && verifyMap.get("GO_UPDATE_MER").equals("Y")) {
			platFormMerId = verifyMap.get("MER_ID");
			platFormTermId = verifyMap.get("TERM_ID");
		}else {
			platFormMerId = getPlatFormMerId();
			platFormTermId = getPlatFormTermId();
		}	 

		if(regShortName.indexOf("&") != -1){
			if(regShortName.indexOf("个人") == -1){
				regShortName = "个体户" + legalPerson;
			}
		}
		
		// 创建商户信息 - TBL_MER_INFO
		Boolean merUpdateResult = false;
		merUpdateResult = insertIntoMerInfo(verifyMap, userName, merName, merType, regName, regShortName, regAddress,
				regFunds, busLicNm, busLicExpire, taxRegCert, legalPerson, legalPersonCertType, legalPersonCertNm,
				legalPersonCertExpire, contactPerson, contactMobile, contactEmail, settlePeriod, platFormMerId);
		if (!merUpdateResult) {
			resultMap.put("addStatus", "FAIL_TO_ADD_MER");
			return resultMap;
		}

		// 创建商户结算信息- TBL_MER_BANK_INFO
		Boolean merBankUpdateResult = false;
		merBankUpdateResult = insertMerBankInfo(verifyMap, userName, bankName, bankProv, bankCity, bankBranchName,
				cnaps, isPrivate, acctName, acctNo, platFormMerId);
		if (!merBankUpdateResult) {
			resultMap.put("addStatus", "FAIL_TO_ADD_MER_BANK");
			return resultMap;
		}

		// 创建商户费率信息 -TBL_MER_FEE_INFO
		Boolean merFeeUpdateResult = false;
		merFeeUpdateResult = insertMerFeeInfo(userName, posDebitFeeRate, posDebitFeeMax, posCreditFeeRate,
				wechatFeeRateT0, wechatFeeRateT1, aliPayFeeRateT0, aliPayFeeRateT1, withdrawalFeeType, withdrawalFee,
				withdrawalFeeRate, platFormMerId, platFormTermId);
		if (!merFeeUpdateResult) {
			resultMap.put("addStatus", "FAIL_TO_ADD_MER_FEE");
			return resultMap;
		}

		// 创建终端信息表 - TBL_TERM_INFO
		Boolean merTermUpdateResult = false;
		merTermUpdateResult = insertTermInfo(userName, termName, termType, termProv, termCity, termCounty, termAddress,
				termSn, platFormMerId, platFormTermId);
		if (!merTermUpdateResult) {
			resultMap.put("addStatus", "FAIL_TO_ADD_MER_TERM");
			return resultMap;
		}

		// 创建机构商户终端关联表
		Boolean merTermRelUpdateResult = false;
		merTermRelUpdateResult = insertInstMerTermRel(userName, instId, merId, termId, platFormMerId, platFormTermId);
		if (!merTermRelUpdateResult) {
			resultMap.put("addStatus", "FAIL_TO_ADD_MER_TERM_REL");
			return resultMap;
		}

		// 创建MCC码记录
		Boolean merMCCUpdateResult = false;
		merMCCUpdateResult = insertMerRelevanceMcc(userName, mccCode, platFormMerId);
		if (!merMCCUpdateResult) {
			resultMap.put("addStatus", "FAIL_TO_ADD_MER_MCC");
			return resultMap;
		}
		
		//创建商户编码地址记录
		Boolean merAddressCodeResult = false;
		merAddressCodeResult = insertMerAddressCode(platFormMerId, regAddress, merProvinceCode, merCityCode, merAreaCode);
		if (!merAddressCodeResult) {
			resultMap.put("addStatus", "FAIL_TO_ADD_CUS_ADDRESS");
			return resultMap;
		}
		
		resultMap.put("addStatus", "SUCCESS");
		
		resultMap.put("platFormMerId", platFormMerId);
		resultMap.put("platFormTermId", platFormTermId);
		
		return resultMap;
	}

	/**
	 * 创建商户地址编码记录
	 * @param merId
	 */
	private Boolean insertMerAddressCode(String merId, String merAddress, String merProvince, String merCity, String merArea) {
		
		//clean 
		TblCustAddressDoExample CustAddressDoExample = new TblCustAddressDoExample();
		CustAddressDoExample.createCriteria().andMerIdEqualTo(merId);
		CustAddressDoMapper.deleteByExample(CustAddressDoExample);
		
		//新增
		if(StringUtils.isNotBlank(merProvince) && StringUtils.isNotBlank(merCity) && StringUtils.isNotBlank(merArea)){
			TblCustAddressDo tblCustAddressDo = new TblCustAddressDo();
			tblCustAddressDo.setMerId(merId);
			tblCustAddressDo.setMerAddress(merAddress);
			tblCustAddressDo.setProvinceCode(merProvince);
			tblCustAddressDo.setCityCode(merCity);
			tblCustAddressDo.setAreaCode(merArea);
			int insertCount  = CustAddressDoMapper.insert(tblCustAddressDo);		
			if(insertCount <= 0){
				return false;
			}
	
		} else {
			return false;
		}
		return true;
	}

	@Override
	public void newResultFile(String batchNo, ServletOutputStream outputStream) {

		TblInstMerAddDetailInfoDOExample tblInstMerAddDetailInfoDOExample = new TblInstMerAddDetailInfoDOExample();
		tblInstMerAddDetailInfoDOExample.createCriteria().andBatchNoEqualTo(batchNo);
		List<TblInstMerAddDetailInfoDO> tblInstMerAddDetailInfoDOList = tblInstMerAddDetailInfoDOMapper
				.selectByExample(tblInstMerAddDetailInfoDOExample);
		ExcelUtil excelUtil = new ExcelUtil();
		excelUtil.setSrcPath(SystemConstant.ADD_INST_MER_RESULT_FILE_TEMP);

		for (int i = 0; i < tblInstMerAddDetailInfoDOList.size(); i++) {
			int rowNum = i + 1;
			TblInstMerAddDetailInfoDO tblInstMerAddDetailInfoDO = tblInstMerAddDetailInfoDOList.get(i);
			excelUtil.setCellStrValue(rowNum, 0, batchNo);
			excelUtil.setCellStrValue(rowNum, 1, tblInstMerAddDetailInfoDO.getInstId());
			excelUtil.setCellStrValue(rowNum, 2, tblInstMerAddDetailInfoDO.getInstMerId());
			excelUtil.setCellStrValue(rowNum, 3, tblInstMerAddDetailInfoDO.getInstTermId());
			excelUtil.setCellStrValue(rowNum, 4, tblInstMerAddDetailInfoDO.getMerId());
			excelUtil.setCellStrValue(rowNum, 5, tblInstMerAddDetailInfoDO.getTermId());
			excelUtil.setCellStrValue(rowNum, 6, tblInstMerAddDetailInfoDO.getResultFlag());
			excelUtil.setCellStrValue(rowNum, 7, tblInstMerAddDetailInfoDO.getResultDesc());
		}
		String resultFilePath = SystemConstant.ADD_INST_MER_RESULT_FILE + batchNo + ".xls";
		File resultFile = new File(resultFilePath);
		if (!resultFile.exists()) {
			resultFile.mkdirs();
		}
		excelUtil.setDesPath(resultFilePath);
		excelUtil.exportToNewFile();
		excelUtil.exportToNewFile(outputStream);
	}

	@Override
	public List<TblInstMerAddDetailInfoDO> selectTblInstMerAddDetailInfoDOByBatchNo(String batchNo) {
		TblInstMerAddDetailInfoDOExample tblInstMerAddDetailInfoDOExample = new TblInstMerAddDetailInfoDOExample();
		tblInstMerAddDetailInfoDOExample.createCriteria().andBatchNoEqualTo(batchNo);
		return tblInstMerAddDetailInfoDOMapper.selectByExample(tblInstMerAddDetailInfoDOExample);
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
	public void instMerAddBatchInfo(IntoPiecesRepuest req) {

		String instId = req.getInstId();
		TblBtsInstMccFeeInfoDOExample tblBtsInstMccFeeInfoDOExample = new TblBtsInstMccFeeInfoDOExample();
		tblBtsInstMccFeeInfoDOExample.createCriteria().andInstIdEqualTo(instId);
		List<TblBtsInstMccFeeInfoDO> tblBtsInstMccFeeInfoDOList = tblBtsInstMccFeeInfoDOMapper
				.selectByExample(tblBtsInstMccFeeInfoDOExample);

		if (tblBtsInstMccFeeInfoDOList == null || tblBtsInstMccFeeInfoDOList.size() == 0) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "机构基础费率还未配置");

		}

		TblBtsInstMccFeeInfoDOExample posDebitFeeExample = new TblBtsInstMccFeeInfoDOExample();
		posDebitFeeExample.createCriteria().andInstIdEqualTo(instId).andMccTypeEqualTo("1");
		List<TblBtsInstMccFeeInfoDO> posDebitFeeList = tblBtsInstMccFeeInfoDOMapper.selectByExample(posDebitFeeExample);

		String instPosDebitFeeRate = "0";
		String instPosDebitFeeMax = "0";

		if (posDebitFeeList == null || posDebitFeeList.size() == 0) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "机构借记卡基础费率还未配置");
		} else {
			String posDebitFee = CalcModeUtil.splitCalcMode(posDebitFeeList.get(0).getCalcMode(), true);
			if (StringUtils.isBlank(posDebitFee)) {
				throw new NotifyException(ErrorRespEnum.RESP002035, "机构借记卡基础费率公式错误");
			} else {
				instPosDebitFeeRate = posDebitFee.split(",")[0];
				instPosDebitFeeMax = posDebitFee.split(",")[1];
			}
		}

		TblBtsInstMccFeeInfoDOExample posCreditFeeExample = new TblBtsInstMccFeeInfoDOExample();
		posCreditFeeExample.createCriteria().andInstIdEqualTo(instId).andMccTypeEqualTo("2");
		List<TblBtsInstMccFeeInfoDO> posCreditFeeList = tblBtsInstMccFeeInfoDOMapper
				.selectByExample(posCreditFeeExample);

		String instPosCreditFeeRate = "0";

		if (posCreditFeeList == null || posCreditFeeList.size() == 0) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "机构贷记卡基础费率还未配置");

		} else {
			String posCreditFee = CalcModeUtil.splitCalcMode(posCreditFeeList.get(0).getCalcMode(), true);
			if (StringUtils.isBlank(posCreditFee)) {
				throw new NotifyException(ErrorRespEnum.RESP002035, "机构贷记卡基础费率公式错误");
			} else {
				instPosCreditFeeRate = posCreditFee.split(",")[0];
			}
		}

		// 微信T0
		TblBtsInstMccFeeInfoDOExample wechatFeeExampleT0 = new TblBtsInstMccFeeInfoDOExample();
		wechatFeeExampleT0.createCriteria().andInstIdEqualTo(instId).andMccTypeEqualTo("3");
		List<TblBtsInstMccFeeInfoDO> wechatFeeListT0 = tblBtsInstMccFeeInfoDOMapper.selectByExample(wechatFeeExampleT0);

		String instWechatFeeRateT0 = "0";

		if (wechatFeeListT0 == null || wechatFeeListT0.size() == 0) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "机构微信T0基础费率还未配置");
		} else {
			String wechatFeeT0 = CalcModeUtil.splitCalcMode(wechatFeeListT0.get(0).getCalcMode(), true);
			if (StringUtils.isBlank(wechatFeeT0)) {
				throw new NotifyException(ErrorRespEnum.RESP002035, "机构微信T0基础费率公式错误");
			} else {
				instWechatFeeRateT0 = wechatFeeT0.split(",")[0];
			}
		}

		// 微信T1
		TblBtsInstMccFeeInfoDOExample wechatFeeExampleT1 = new TblBtsInstMccFeeInfoDOExample();
		wechatFeeExampleT0.createCriteria().andInstIdEqualTo(instId).andMccTypeEqualTo("5");
		List<TblBtsInstMccFeeInfoDO> wechatFeeListT1 = tblBtsInstMccFeeInfoDOMapper.selectByExample(wechatFeeExampleT1);

		String instWechatFeeRateT1 = "0";

		if (wechatFeeListT1 == null || wechatFeeListT1.size() == 0) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "机构微信T1基础费率还未配置");
		} else {
			String wechatFeeT1 = CalcModeUtil.splitCalcMode(wechatFeeListT1.get(0).getCalcMode(), true);
			if (StringUtils.isBlank(wechatFeeT1)) {
				throw new NotifyException(ErrorRespEnum.RESP002035, "机构微信T1基础费率公式错误");
			} else {
				instWechatFeeRateT1 = wechatFeeT1.split(",")[0];
			}
		}

		// 支付宝T0
		TblBtsInstMccFeeInfoDOExample aliPayFeeExampleT0 = new TblBtsInstMccFeeInfoDOExample();
		wechatFeeExampleT0.createCriteria().andInstIdEqualTo(instId).andMccTypeEqualTo("4");
		List<TblBtsInstMccFeeInfoDO> aliPayFeeListT0 = tblBtsInstMccFeeInfoDOMapper.selectByExample(aliPayFeeExampleT0);

		String instAliPayFeeRateT0 = "0";

		if (aliPayFeeListT0 == null || aliPayFeeListT0.size() == 0) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "机构支付宝T0基础费率还未配置");
		} else {
			String aliPayFeeT0 = CalcModeUtil.splitCalcMode(aliPayFeeListT0.get(0).getCalcMode(), true);
			if (StringUtils.isBlank(aliPayFeeT0)) {
				throw new NotifyException(ErrorRespEnum.RESP002035, "机构支付宝T0基础费率公式错误");
			} else {
				instWechatFeeRateT0 = aliPayFeeT0.split(",")[0];
			}
		}

		// 支付宝T1
		TblBtsInstMccFeeInfoDOExample aliPayFeeExampleT1 = new TblBtsInstMccFeeInfoDOExample();
		wechatFeeExampleT0.createCriteria().andInstIdEqualTo(instId).andMccTypeEqualTo("6");
		List<TblBtsInstMccFeeInfoDO> aliPayFeeListT1 = tblBtsInstMccFeeInfoDOMapper.selectByExample(aliPayFeeExampleT1);

		if (aliPayFeeListT1 == null || aliPayFeeListT1.size() == 0) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "机构支付宝T1基础费率还未配置");
		} else {
			String aliPayFeeT1 = CalcModeUtil.splitCalcMode(aliPayFeeListT1.get(0).getCalcMode(), true);
			if (StringUtils.isBlank(aliPayFeeT1)) {
				throw new NotifyException(ErrorRespEnum.RESP002035, "机构支付宝T1基础费率公式错误");
			} else {
				instWechatFeeRateT1 = aliPayFeeT1.split(",")[0];
			}
		}

		// 提现
		TblBtsInstMccFeeInfoDOExample withdrawalFeeExample = new TblBtsInstMccFeeInfoDOExample();
		withdrawalFeeExample.createCriteria().andInstIdEqualTo(instId).andMccTypeEqualTo("7");
		List<TblBtsInstMccFeeInfoDO> withdrawalFeeList = tblBtsInstMccFeeInfoDOMapper
				.selectByExample(withdrawalFeeExample);

		String instWithdrawalFee = "0";
		if (withdrawalFeeList == null || withdrawalFeeList.size() == 0) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "机构提现基础费率还未配置");
		} else {
			String withdrawalFee = CalcModeUtil.splitCalcMode(withdrawalFeeList.get(0).getCalcMode(), true);
			if (StringUtils.isBlank(withdrawalFee)) {
				throw new NotifyException(ErrorRespEnum.RESP002035, "机构提现基础费率公式错误");
			} else {
				instWithdrawalFee = withdrawalFee.split(",")[0];
			}
		}

		// 垫资
		TblBtsInstMccFeeInfoDOExample withdrawalFeeRateExample = new TblBtsInstMccFeeInfoDOExample();
		withdrawalFeeExample.createCriteria().andInstIdEqualTo(instId).andMccTypeEqualTo("8");
		List<TblBtsInstMccFeeInfoDO> withdrawalFeeRateList = tblBtsInstMccFeeInfoDOMapper
				.selectByExample(withdrawalFeeRateExample);

		String instWithdrawalFeeRate = "0";
		if (withdrawalFeeRateList == null || withdrawalFeeRateList.size() == 0) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "机构垫资基础费率还未配置");
		} else {
			String withdrawalFeeRate = CalcModeUtil.splitCalcMode(withdrawalFeeRateList.get(0).getCalcMode(), true);
			if (StringUtils.isBlank(withdrawalFeeRate)) {
				throw new NotifyException(ErrorRespEnum.RESP002035, "机构垫资基础费率公式错误");
			} else {
				instWithdrawalFeeRate = withdrawalFeeRate.split(",")[0];
			}
		}

		logger.debug("校验费率是否大于机构费率");
		compareFeesByReq(req, instPosDebitFeeRate, instPosDebitFeeMax, instPosCreditFeeRate, instWechatFeeRateT0,
				instWechatFeeRateT1, instAliPayFeeRateT0, instWithdrawalFee, instWithdrawalFeeRate);

		// 查看商户是否曾经入驻过
		TblInstMerTermRelDOExample tblInstMerTermRelDOExample = new TblInstMerTermRelDOExample();
		tblInstMerTermRelDOExample.createCriteria().andInstIdEqualTo(instId).andInstMerIdEqualTo(req.getMerId())
				.andInstTermIdEqualTo(req.getTermId());
		List<TblInstMerTermRelDO> tblInstMerTermRelDOList = tblInstMerTermRelDOMapper
				.selectByExample(tblInstMerTermRelDOExample);
		if (tblInstMerTermRelDOList != null && tblInstMerTermRelDOList.size() != 0) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "此机构商户已在系统中存在");
		}

		// 查看商户银行账号信息是否在系统中存在
		TblMerBankInfoDOExample tblMerBankInfoDOExample = new TblMerBankInfoDOExample();
		tblMerBankInfoDOExample.createCriteria().andAcctNoEqualTo(req.getAcctNo());
		List<TblMerBankInfoDO> TblMerBankInfoList = tblMerBankInfoDOMapper.selectByExample(tblMerBankInfoDOExample);
		if (TblMerBankInfoList != null && TblMerBankInfoList.size() != 0) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "此机构商户银行账号已被使用");
		}

		if (StringUtils.isBlank(MerTypeEnum.getMessage(req.getMerType()))) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "商户类型不存在");
		}

		if (!req.getLegalPersonCertType().equals("0")) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "法人代表证件类型不符");
		}

		if (!isValidDate(req.getLegalPersonCertExpire())) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "法人证件有效期错误");

		}

		TblSubbranchInfoDOExample tblSubbranchInfoDOExample = new TblSubbranchInfoDOExample();
		tblSubbranchInfoDOExample.createCriteria().andSubbranchIdEqualTo(req.getCnaps())
				.andSubbranchNameEqualTo(req.getBankBranchName()).andBankNameEqualTo(req.getBankName())
				.andProvinceEqualTo(req.getBankProv()).andCityEqualTo(req.getBankCity());

		List<TblSubbranchInfoDO> tblSubbranchInfoDOList = tblSubbranchInfoDOMapper
				.selectByExample(tblSubbranchInfoDOExample);
		if (tblSubbranchInfoDOList == null || tblSubbranchInfoDOList.size() == 0) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "结算分行不存在");
		}
		long platFormMerIdSeq = seqMapper.getSequenceNextVal("SEQ_PLATFORM_MER_ID");
		String platFormMerId = DateUtil.getCurrentDate()
				+ StringUtils.leftPad(String.valueOf(platFormMerIdSeq), 7, '0');

		long platFormTermIdSeq = seqMapper.getSequenceNextVal("SEQ_PLATFORM_TERM_ID");
		String platFormTermId = StringUtils.leftPad(String.valueOf(platFormTermIdSeq), 8, '0');

		try {

			// 创建商户信息
			insertMerInfoByReq(req, platFormMerId);

			// 创建商户结算信息
			insertMerBankByReq(req, platFormMerId);

			// 创建商户费率信息记录
			insertMerFeeInfoByReq(req, platFormMerId, platFormTermId);

			// 创建商户终端信息
			insertTermInfoByReq(req, platFormMerId, platFormTermId);

			// 创建商户终端管理信息
			insertMerTermRelByReq(req, instId, platFormMerId, platFormTermId);

			// 创建MCC码记录
			insertMerRelevanceMccByReq(req, platFormMerId);

			new HashMap<String, String>();
			openLoginUserService.openNewGeneralMer(platFormMerId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			new NotifyException(ErrorRespEnum.RESP009998, "系统错误");
		}

	}

	/**
	 * @param req
	 * @param platFormMerId
	 */
	private void insertMerRelevanceMccByReq(IntoPiecesRepuest req, String platFormMerId) {
		TblMerCoreMccDoExample tblMerCoreMccDoExample = new TblMerCoreMccDoExample();
		tblMerCoreMccDoExample.createCriteria().andMccValueEqualTo(req.getMccCode().trim());
		List<TblMerCoreMccDo> tblMerCoreMccDo = tblMerCoreMccDoMapper.selectByExample(tblMerCoreMccDoExample);
		String mccDescValue = tblMerCoreMccDo.get(0).getMccDesp();

		TblMerRelevanceMccDo tblMerRelevanceMccDo = new TblMerRelevanceMccDo();
		tblMerRelevanceMccDo.setMccValue(req.getMccCode());
		tblMerRelevanceMccDo.setMccDesp(mccDescValue);
		tblMerRelevanceMccDo.setMerId(platFormMerId);
		tblMerRelevanceMccDo.setMccId(String.valueOf(seqMapper.getSequenceNextVal("MER_MCC_SEQ")));
		tblMerRelevanceMccDo.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerRelevanceMccDo.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerRelevanceMccDo.setCreateUser("INTERFACE");
		tblMerRelevanceMccDoMapper.insert(tblMerRelevanceMccDo);
	}

	/**
	 * @param req
	 * @param instId
	 * @param platFormMerId
	 * @param platFormTermId
	 */
	private void insertMerTermRelByReq(IntoPiecesRepuest req, String instId, String platFormMerId,
			String platFormTermId) {
		TblInstMerTermRelDO tblInstMerTermRelDO = new TblInstMerTermRelDO();
		tblInstMerTermRelDO.setMerId(platFormMerId);
		tblInstMerTermRelDO.setTermId(platFormTermId);
		tblInstMerTermRelDO.setInstId(instId);
		tblInstMerTermRelDO.setInstMerId(req.getMerId());
		tblInstMerTermRelDO.setInstTermId(req.getTermId());
		tblInstMerTermRelDO.setUserName("INTERFACE");
		tblInstMerTermRelDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblInstMerTermRelDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblInstMerTermRelDOMapper.insertSelective(tblInstMerTermRelDO);
	}

	/**
	 * @param req
	 * @param platFormMerId
	 * @param platFormTermId
	 */
	private void insertTermInfoByReq(IntoPiecesRepuest req, String platFormMerId, String platFormTermId) {
		TblTermInfoDO tblTermInfoDO = new TblTermInfoDO();
		tblTermInfoDO.setMerId(platFormMerId);
		tblTermInfoDO.setTermId(platFormTermId);
		tblTermInfoDO.setTermType(req.getTermType());
		tblTermInfoDO.setTermStat("1");
		tblTermInfoDO.setTermName(req.getTermName());
		tblTermInfoDO.setTermSn(req.getTermSn());
		tblTermInfoDO.setTermInstallProv(req.getTermProv());
		tblTermInfoDO.setTermInstallCity(req.getTermCity());
		tblTermInfoDO.setTermInstallCounty(req.getTermCounty());
		tblTermInfoDO.setTermInstallAddress(req.getTermAddress());
		tblTermInfoDO.setUserName("INTERFACE");
		tblTermInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblTermInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblTermInfoDOMapper.insertSelective(tblTermInfoDO);
	}

	/**
	 * @param req
	 * @param platFormMerId
	 * @param platFormTermId
	 */
	private void insertMerFeeInfoByReq(IntoPiecesRepuest req, String platFormMerId, String platFormTermId) {
		TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
		tblMerPosDebitFeeInfoDO.setMerId(platFormMerId);
		tblMerPosDebitFeeInfoDO.setTermId(platFormTermId);
		tblMerPosDebitFeeInfoDO.setFeeType("01");
		tblMerPosDebitFeeInfoDO
				.setCalcMode(CalcModeUtil.genCalcMode(req.getPosDebitFeeRate(), req.getPosDebitFeeMax(), "0", false));
		tblMerPosDebitFeeInfoDO.setUserName("INTERFACE");
		tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);

		TblMerFeeInfoDO tblMerPosCreditFeeInfoDO = new TblMerFeeInfoDO();
		tblMerPosCreditFeeInfoDO.setMerId(platFormMerId);
		tblMerPosCreditFeeInfoDO.setTermId(platFormTermId);
		tblMerPosCreditFeeInfoDO.setFeeType("02");
		tblMerPosCreditFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(req.getPosCreditFeeRate(), null, null, false));
		tblMerPosCreditFeeInfoDO.setUserName("INTERFACE");
		tblMerPosCreditFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerPosCreditFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerFeeInfoDOMapper.insertSelective(tblMerPosCreditFeeInfoDO);

		TblMerFeeInfoDO tblMerWechatT0FeeInfoDO = new TblMerFeeInfoDO();
		tblMerWechatT0FeeInfoDO.setMerId(platFormMerId);
		tblMerWechatT0FeeInfoDO.setTermId(platFormTermId);
		tblMerWechatT0FeeInfoDO.setFeeType("03");
		tblMerWechatT0FeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(req.getWechatFeeRateT0(), null, null, false));
		tblMerWechatT0FeeInfoDO.setUserName("INTERFACE");
		tblMerWechatT0FeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerWechatT0FeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerFeeInfoDOMapper.insertSelective(tblMerWechatT0FeeInfoDO);

		TblMerFeeInfoDO tblMerWechatT1FeeInfoDO = new TblMerFeeInfoDO();
		tblMerWechatT1FeeInfoDO.setMerId(platFormMerId);
		tblMerWechatT1FeeInfoDO.setTermId(platFormTermId);
		tblMerWechatT1FeeInfoDO.setFeeType("05");
		tblMerWechatT1FeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(req.getWechatFeeRateT1(), null, null, false));
		tblMerWechatT1FeeInfoDO.setUserName("INTERFACE");
		tblMerWechatT1FeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerWechatT1FeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerFeeInfoDOMapper.insertSelective(tblMerWechatT1FeeInfoDO);

		TblMerFeeInfoDO tblMerAliPayT0FeeInfoDO = new TblMerFeeInfoDO();
		tblMerAliPayT0FeeInfoDO.setMerId(platFormMerId);
		tblMerAliPayT0FeeInfoDO.setTermId(platFormTermId);
		tblMerAliPayT0FeeInfoDO.setFeeType("04");
		tblMerAliPayT0FeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(req.getAliPayFeeRateT0(), null, null, false));
		tblMerAliPayT0FeeInfoDO.setUserName("INTERFACE");
		tblMerAliPayT0FeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerAliPayT0FeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerFeeInfoDOMapper.insertSelective(tblMerAliPayT0FeeInfoDO);

		TblMerFeeInfoDO tblMerAliPayT1FeeInfoDO = new TblMerFeeInfoDO();
		tblMerAliPayT1FeeInfoDO.setMerId(platFormMerId);
		tblMerAliPayT1FeeInfoDO.setTermId(platFormTermId);
		tblMerAliPayT1FeeInfoDO.setFeeType("05");
		tblMerAliPayT1FeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(req.getAliPayFeeRateT1(), null, null, false));
		tblMerAliPayT1FeeInfoDO.setUserName("INTERFACE");
		tblMerAliPayT1FeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerAliPayT1FeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerFeeInfoDOMapper.insertSelective(tblMerAliPayT1FeeInfoDO);

		if (req.getWithdrawalFeeType().equals("1")) {
			TblMerFeeInfoDO tblMerWithdrawalFeeRateInfoDO = new TblMerFeeInfoDO();
			tblMerWithdrawalFeeRateInfoDO.setMerId(platFormMerId);
			tblMerWithdrawalFeeRateInfoDO.setTermId(platFormTermId);
			tblMerWithdrawalFeeRateInfoDO.setFeeType("08");
			tblMerWithdrawalFeeRateInfoDO
					.setCalcMode(CalcModeUtil.genCalcMode(req.getWithdrawalFeeRate(), null, null, false));
			tblMerWithdrawalFeeRateInfoDO.setUserName("INTERFACE");
			tblMerWithdrawalFeeRateInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerWithdrawalFeeRateInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerFeeInfoDOMapper.insertSelective(tblMerWithdrawalFeeRateInfoDO);
		} else {
			TblMerFeeInfoDO tblMerWithdrawalFeeInfoDO = new TblMerFeeInfoDO();
			tblMerWithdrawalFeeInfoDO.setMerId(platFormMerId);
			tblMerWithdrawalFeeInfoDO.setTermId(platFormTermId);
			tblMerWithdrawalFeeInfoDO.setFeeType("07");
			tblMerWithdrawalFeeInfoDO
					.setCalcMode(CalcModeUtil.genCalcMode(req.getWithdrawalFee(), null, null, false));
			tblMerWithdrawalFeeInfoDO.setUserName("INTERFACE");
			tblMerWithdrawalFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerWithdrawalFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerFeeInfoDOMapper.insertSelective(tblMerWithdrawalFeeInfoDO);
		}
	}

	/**
	 * @param req
	 * @param platFormMerId
	 */
	private void insertMerBankByReq(IntoPiecesRepuest req, String platFormMerId) {
		TblMerBankInfoDO tblMerBankInfoDO = new TblMerBankInfoDO();
		tblMerBankInfoDO.setBankName(req.getBankName());
		tblMerBankInfoDO.setBankBranchName(req.getBankBranchName());
		tblMerBankInfoDO.setMerId(platFormMerId);
		tblMerBankInfoDO.setAcctName(req.getAcctName());
		tblMerBankInfoDO.setAcctNo(req.getAcctNo());
		tblMerBankInfoDO.setCnaps(req.getCnaps());
		tblMerBankInfoDO.setIsPrivate(req.getIsPrivate());
		tblMerBankInfoDO.setProvName(req.getBankProv());
		tblMerBankInfoDO.setCityName(req.getBankCity());
		tblMerBankInfoDO.setUserName("INTERFACE");
		tblMerBankInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerBankInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerBankInfoDOMapper.insertSelective(tblMerBankInfoDO);
	}

	/**
	 * 
	 * @param req
	 * @param platFormMerId
	 */
	private void insertMerInfoByReq(IntoPiecesRepuest req, String platFormMerId) {
		TblMerInfoDO tblMerInfoDO = new TblMerInfoDO();
		tblMerInfoDO.setMerId(platFormMerId);
		tblMerInfoDO.setMerName(req.getMerName());
		tblMerInfoDO.setMerType(req.getMerType());
		tblMerInfoDO.setRegName(req.getRegName());
		tblMerInfoDO.setRegShortName(req.getRegShortName());
		tblMerInfoDO.setMerAddress(req.getRegAddress());
		tblMerInfoDO.setRegFunds(req.getRegFunds());
		tblMerInfoDO.setBusLicNm(req.getBusLicNm());
		tblMerInfoDO.setBusLicExpire(req.getBusLicExpire());
		tblMerInfoDO.setTaxRegCert(req.getTaxRegCert());
		tblMerInfoDO.setLegalPerson(req.getLegalPerson());
		tblMerInfoDO.setLegalPersonCertType(req.getLegalPersonCertType());
		tblMerInfoDO.setLegalPersonCertNm(req.getLegalPersonCertNm());
		tblMerInfoDO.setLegalPersonCertExpire(req.getLegalPersonCertExpire());
		tblMerInfoDO.setContactPerson(req.getContactPerson());
		tblMerInfoDO.setContactMobile(req.getContactMobile());
		tblMerInfoDO.setContactEmail(req.getContactEmail());
		tblMerInfoDO.setFuncStat("NNNNNNNNNN");
		tblMerInfoDO.setMerStat(Constant.AUDIT_NONE);// 进件中, 未审核
		tblMerInfoDO.setUserName("INTERFACE");
		tblMerInfoDO.setSettlePeriod(req.getSettlePeriod());
		tblMerInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerInfoDOMapper.insertSelective(tblMerInfoDO);
	}

	/**
	 * 比较接口中的费率信息
	 * 
	 * @param req
	 * @param instPosDebitFeeRate
	 * @param instPosDebitFeeMax
	 * @param instPosCreditFeeRate
	 * @param instWechatFeeRateT0
	 * @param instWechatFeeRateT1
	 * @param instAliPayFeeRateT0
	 * @param instWithdrawalFee
	 * @param instWithdrawalFeeRate
	 */
	private void compareFeesByReq(IntoPiecesRepuest req, String instPosDebitFeeRate, String instPosDebitFeeMax,
			String instPosCreditFeeRate, String instWechatFeeRateT0, String instWechatFeeRateT1,
			String instAliPayFeeRateT0, String instWithdrawalFee, String instWithdrawalFeeRate) {
		if (AmtUtil.compareTo(req.getPosDebitFeeRate(), instPosDebitFeeRate) == -1) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "商户借记卡-比例费率小于机构借记卡-比例费率");
		}

		if (AmtUtil.compareTo(req.getPosDebitFeeMax(), instPosDebitFeeMax) == -1) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "商户借记卡-封顶费率小于机构借记卡-封顶费率");
		}

		if (AmtUtil.compareTo(req.getPosCreditFeeRate(), instPosCreditFeeRate) == -1) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "商户贷记卡-比例费率小于机构贷记卡-比例费率");
		}

		if (AmtUtil.compareTo(req.getWechatFeeRateT0(), instWechatFeeRateT0) == -1) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "商户微信T0费率小于机构微信费率");
		}

		if (AmtUtil.compareTo(req.getWechatFeeRateT1(), instWechatFeeRateT1) == -1) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "商户微信T1费率小于机构微信费率");
		}
		if (AmtUtil.compareTo(req.getAliPayFeeRateT0(), instAliPayFeeRateT0) == -1) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "商户支付宝T0费率小于机构微信费率");
		}
		if (AmtUtil.compareTo(req.getAliPayFeeRateT1(), instAliPayFeeRateT0) == -1) {
			throw new NotifyException(ErrorRespEnum.RESP002035, "商户支付宝T1费率小于机构微信费率");
		}

		if (req.getWithdrawalFeeType().equals("1")) {
			if (AmtUtil.compareTo(req.getWithdrawalFeeRate(), instWithdrawalFeeRate) == -1) {
				throw new NotifyException(ErrorRespEnum.RESP002035, "商户结算-比例费率小于机构结算-比例费率");
			}

		} else {
			if (AmtUtil.compareTo(req.getWithdrawalFee(), instWithdrawalFee) == -1) {
				throw new NotifyException(ErrorRespEnum.RESP002035, "商户结算-单笔小于机构结算-单笔");
			}
		}
	}

	@Override
	public void instMerPicAddressInfo(IntoPicAddressRepuest req) {
		TblInstMerTermRelDOExample tblInstMerTermRelDOExample = new TblInstMerTermRelDOExample();
		tblInstMerTermRelDOExample.createCriteria().andMerIdEqualTo(req.getMerId())
				.andInstMerIdEqualTo(req.getInstMerId()).andInstIdEqualTo(req.getInstId());
		List<TblInstMerTermRelDO> tblInstMerTermRelDOList = tblInstMerTermRelDOMapper
				.selectByExample(tblInstMerTermRelDOExample);
		if (tblInstMerTermRelDOList == null || tblInstMerTermRelDOList.size() == 0) {
			new NotifyException(ErrorRespEnum.RESP009998, "商户数据文件还未报备");
		} else {
			TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper
					.selectByPrimaryKey(tblInstMerTermRelDOList.get(0).getMerId());
			if (!tblMerInfoDO.getMerStat().equals(Constant.AUDIT_REJECT)) {
				new NotifyException(ErrorRespEnum.RESP009998, "商户已完成进件");
			}
		}
		File targetFile = new File(SystemConstant.ADD_INST_MER_FILE_PATH + DateUtil.getCurrentDate(), req.getMerId());
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		} else {
			new NotifyException(ErrorRespEnum.RESP009998, "系统错误");
		}

		ImgUtil.generateImage(req.getPicAddress1(), targetFile + "/picAddress1.jpg");
		ImgUtil.generateImage(req.getPicAddress2(), targetFile + "/picAddress2.jpg");
		ImgUtil.generateImage(req.getPicAddress3(), targetFile + "/picAddress3.jpg");
		ImgUtil.generateImage(req.getPicAddress4(), targetFile + "/picAddress4.jpg");
		ImgUtil.generateImage(req.getPicAddress5(), targetFile + "/picAddress5.jpg");
		ImgUtil.generateImage(req.getPicAddress6(), targetFile + "/picAddress6.jpg");
		ImgUtil.generateImage(req.getPicAddress7(), targetFile + "/picAddress7.jpg");
		ImgUtil.generateImage(req.getPicAddress8(), targetFile + "/picAddress8.jpg");
		ImgUtil.generateImage(req.getPicAddress9(), targetFile + "/picAddress9.jpg");
		ImgUtil.generateImage(req.getPicAddress10(), targetFile + "/picAddress10.jpg");
		ImgUtil.generateImage(req.getPicAddress11(), targetFile + "/picAddress11.jpg");
		ImgUtil.generateImage(req.getPicAddress12(), targetFile + "/picAddress12.jpg");
		ImgUtil.generateImage(req.getPicAddress13(), targetFile + "/picAddress13.jpg");
		ImgUtil.generateImage(req.getPicAddress15(), targetFile + "/picAddress15.jpg");
		TblMerFileInfoDO tblMerFileInfoDO = new TblMerFileInfoDO();
		tblMerFileInfoDO.setInstId(req.getInstId());
		tblMerFileInfoDO.setInstMerId(req.getInstMerId());
		tblMerFileInfoDO.setMerId(req.getMerId());
		tblMerFileInfoDO.setPicAddress1(targetFile + "\\picAddress1.jpg");
		tblMerFileInfoDO.setPicAddress2(targetFile + "\\picAddress2.jpg");
		tblMerFileInfoDO.setPicAddress3(targetFile + "\\picAddress3.jpg");
		tblMerFileInfoDO.setPicAddress4(targetFile + "\\picAddress4.jpg");
		tblMerFileInfoDO.setPicAddress5(targetFile + "\\picAddress5.jpg");
		tblMerFileInfoDO.setPicAddress6(targetFile + "\\picAddress6.jpg");
		tblMerFileInfoDO.setPicAddress7(targetFile + "\\picAddress7.jpg");
		tblMerFileInfoDO.setPicAddress8(targetFile + "\\picAddress8.jpg");
		tblMerFileInfoDO.setPicAddress9(targetFile + "\\picAddress9.jpg");
		tblMerFileInfoDO.setPicAddress10(targetFile + "\\picAddress10.jpg");
		tblMerFileInfoDO.setPicAddress11(targetFile + "\\picAddress11.jpg");
		tblMerFileInfoDO.setPicAddress12(targetFile + "\\picAddress12.jpg");
		tblMerFileInfoDO.setPicAddress13(targetFile + "\\picAddress13.jpg");
		tblMerFileInfoDO.setPicAddress15(targetFile + "\\picAddress15.jpg");
		tblMerFileInfoDOMapper.insert(tblMerFileInfoDO);

	}

	/**
	 * 获取机构费率
	 */
	public Map<String, String> getInstFee(TblInstMerAddBatchInfoDO tblInstMerAddBatchInfoDO, String instId,
			String feeType) {

		Boolean verifyStatus = false;
		Map<String, String> feeMap = new HashMap<String, String>();
		Map<String, String> feeDescMap = getFeeDesc(feeType);
		String feeDesc = feeDescMap.get("feeDesc");

		TblBtsInstMccFeeInfoDOExample feeExample = new TblBtsInstMccFeeInfoDOExample();
		feeExample.createCriteria().andInstIdEqualTo(instId).andMccTypeEqualTo(feeType);
		List<TblBtsInstMccFeeInfoDO> feeList = tblBtsInstMccFeeInfoDOMapper.selectByExample(feeExample);

		String instFeeRate = "0";
		String instFeeMax = "0";

		if (feeList == null || feeList.size() == 0) {
			tblInstMerAddBatchInfoDO.setResultFlag("F");
			tblInstMerAddBatchInfoDO.setResultDesc(feeDesc + "还未配置");
			tblInstMerAddBatchInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddBatchInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddBatchInfoDO);
		} else {
			String feeitem = CalcModeUtil.splitCalcMode(feeList.get(0).getCalcMode(), false);
			if (StringUtils.isBlank(feeitem)) {
				tblInstMerAddBatchInfoDO.setResultFlag("F");
				tblInstMerAddBatchInfoDO.setResultDesc(feeDesc + "公式错误");
				tblInstMerAddBatchInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
				tblInstMerAddBatchInfoDOMapper.updateByPrimaryKeySelective(tblInstMerAddBatchInfoDO);
			} else {
				instFeeRate = feeitem.split(",")[0];
				instFeeMax = feeitem.split(",")[1];
				verifyStatus = true;
			}
		}
		if (verifyStatus) {
			feeMap.put("instFeeRate", instFeeRate);
			feeMap.put("instFeeMax", instFeeMax);
		}
		return feeMap;
	}


	
	/**
	 * 写入商户信息
	 */
	private Boolean insertIntoMerInfo(Map<String, String> verifyMap, String userName, String merName, String merType,
			String regName, String regShortName, String regAddress, String regFunds, String busLicNm,
			String busLicExpire, String taxRegCert, String legalPerson, String legalPersonCertType,
			String legalPersonCertNm, String legalPersonCertExpire, String contactPerson, String contactMobile,
			String contactEmail, String settlePeriod, String platFormMerId) {

		int processOfCount = 0;
		Boolean insertSuccess = false;

		TblMerInfoDO tblMerInfoDO = new TblMerInfoDO();
		tblMerInfoDO.setMerId(platFormMerId);
		tblMerInfoDO.setMerName(merName);
		tblMerInfoDO.setMerType(merType);
		tblMerInfoDO.setRegName(regName);
		tblMerInfoDO.setRegShortName(regShortName);
		tblMerInfoDO.setMerAddress(regAddress);
		tblMerInfoDO.setRegFunds(regFunds);
		tblMerInfoDO.setBusLicNm(busLicNm);
		tblMerInfoDO.setBusLicExpire(busLicExpire);
		tblMerInfoDO.setTaxRegCert(taxRegCert);
		tblMerInfoDO.setLegalPerson(legalPerson);
		tblMerInfoDO.setLegalPersonCertType(legalPersonCertType);
		tblMerInfoDO.setLegalPersonCertNm(legalPersonCertNm);
		tblMerInfoDO.setLegalPersonCertExpire(legalPersonCertExpire);
		tblMerInfoDO.setContactPerson(contactPerson);
		tblMerInfoDO.setContactMobile(contactMobile);
		tblMerInfoDO.setContactEmail(contactEmail);
		tblMerInfoDO.setFuncStat("NNNNNNNNNN");
		tblMerInfoDO.setMerStat(Constant.AUDIT_NONE);// 进件-未审核
		tblMerInfoDO.setUserName(userName);
		tblMerInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerInfoDO.setSettlePeriod(settlePeriod);

		if (verifyMap.containsKey("GO_ADD_MER_INFO") && verifyMap.get("GO_ADD_MER_INFO").equals("Y")) {
			processOfCount = tblMerInfoDOMapper.insertSelective(tblMerInfoDO);
		}

		if (verifyMap.containsKey("GO_UPDATE_MER") && verifyMap.get("GO_UPDATE_MER").equals("Y")) {
			processOfCount = tblMerInfoDOMapper.updateByPrimaryKey(tblMerInfoDO);
		}
		if (processOfCount > 0) {
			insertSuccess = true;
		}
		return insertSuccess;
	}

	/**
	 * 写入结算信息
	 */
	private Boolean insertMerBankInfo(Map<String, String> verifyMap, String userName, String bankName, String bankProv,
			String bankCity, String bankBranchName, String cnaps, String isPrivate, String acctName, String acctNo,
			String platFormMerId) {

		int processOfCount = 0;
		Boolean insertSuccess = false;

		//clean 
		TblMerBankInfoDOExample MerBankInfoDOExampleForClean = new TblMerBankInfoDOExample();
		MerBankInfoDOExampleForClean.createCriteria().andMerIdEqualTo(platFormMerId);
		tblMerBankInfoDOMapper.deleteByExample(MerBankInfoDOExampleForClean);
		
		//insert
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
		tblMerBankInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerBankInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());

	    processOfCount = tblMerBankInfoDOMapper.insertSelective(tblMerBankInfoDO);
		if (processOfCount > 0) {
			insertSuccess = true;
		}
		return insertSuccess;
	}
	
	
	/**
	 * 写入手续费
	 */
	private Boolean insertMerFeeInfo(String userName, String posDebitFeeRate, String posDebitFeeMax,
			String posCreditFeeRate, String wechatFeeRateT0, String wechatFeeRateT1, String aliPayFeeRateT0,
			String aliPayFeeRateT1, String withdrawalFeeType, String withdrawalFee, String withdrawalFeeRate,
			String platFormMerId, String platFormTermId) {

		int processOfCount = 0;
		Boolean insertSuccess = false;

		if (StringUtils.isBlank(platFormMerId))
			return insertSuccess;
		
		//clean
		TblMerFeeInfoDOExample tblMerFeeInfoDOExample = new TblMerFeeInfoDOExample();
		tblMerFeeInfoDOExample.createCriteria().andMerIdEqualTo(platFormMerId);
		tblMerFeeInfoDOMapper.deleteByExample(tblMerFeeInfoDOExample);

		// 借记卡
		if (!StringUtil.isBlank(posDebitFeeRate) && !StringUtil.isBlank(posDebitFeeMax)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(platFormMerId);
			tblMerPosDebitFeeInfoDO.setTermId(platFormTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("01");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(posDebitFeeRate, posDebitFeeMax, "0", false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			processOfCount = tblMerFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (processOfCount < 0) {
				return insertSuccess;
			}
		}

		// 贷记卡
		if (!StringUtil.isBlank(posCreditFeeRate)) {
			TblMerFeeInfoDO tblMerPosCreditFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosCreditFeeInfoDO.setMerId(platFormMerId);
			tblMerPosCreditFeeInfoDO.setTermId(platFormTermId);
			tblMerPosCreditFeeInfoDO.setFeeType("02");
			tblMerPosCreditFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(posCreditFeeRate, null, null, false));
			tblMerPosCreditFeeInfoDO.setUserName(userName);
			tblMerPosCreditFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosCreditFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			processOfCount = tblMerFeeInfoDOMapper.insertSelective(tblMerPosCreditFeeInfoDO);
			if (processOfCount < 0) {
				return insertSuccess;
			}
		}

		// 微信T0
		if (!StringUtil.isBlank(wechatFeeRateT0)) {
			TblMerFeeInfoDO tblMerWechatT0FeeInfoDO = new TblMerFeeInfoDO();
			tblMerWechatT0FeeInfoDO.setMerId(platFormMerId);
			tblMerWechatT0FeeInfoDO.setTermId(platFormTermId);
			tblMerWechatT0FeeInfoDO.setFeeType("03");
			tblMerWechatT0FeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(wechatFeeRateT0, null, null, false));
			tblMerWechatT0FeeInfoDO.setUserName(userName);
			tblMerWechatT0FeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerWechatT0FeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			processOfCount = tblMerFeeInfoDOMapper.insertSelective(tblMerWechatT0FeeInfoDO);
			if (processOfCount < 0) {
				return insertSuccess;
			}
		}

		// 微信T1
		if (!StringUtil.isBlank(wechatFeeRateT1)) {
			TblMerFeeInfoDO tblMerWechatT1FeeInfoDO = new TblMerFeeInfoDO();
			tblMerWechatT1FeeInfoDO.setMerId(platFormMerId);
			tblMerWechatT1FeeInfoDO.setTermId(platFormTermId);
			tblMerWechatT1FeeInfoDO.setFeeType("05");
			tblMerWechatT1FeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(wechatFeeRateT1, null, null, false));
			tblMerWechatT1FeeInfoDO.setUserName(userName);
			tblMerWechatT1FeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerWechatT1FeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			processOfCount = tblMerFeeInfoDOMapper.insertSelective(tblMerWechatT1FeeInfoDO);
			if (processOfCount < 0) {
				return insertSuccess;
			}
		}

		// 支付宝T0
		if (!StringUtil.isBlank(aliPayFeeRateT0)) {
			TblMerFeeInfoDO tblMerAliPayT0FeeInfoDO = new TblMerFeeInfoDO();
			tblMerAliPayT0FeeInfoDO.setMerId(platFormMerId);
			tblMerAliPayT0FeeInfoDO.setTermId(platFormTermId);
			tblMerAliPayT0FeeInfoDO.setFeeType("04");
			tblMerAliPayT0FeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(aliPayFeeRateT0, null, null, false));
			tblMerAliPayT0FeeInfoDO.setUserName(userName);
			tblMerAliPayT0FeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerAliPayT0FeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			processOfCount = tblMerFeeInfoDOMapper.insertSelective(tblMerAliPayT0FeeInfoDO);
			if (processOfCount < 0) {
				return insertSuccess;
			}
		}

		// 支付宝T1
		if (!StringUtil.isBlank(aliPayFeeRateT1)) {
			TblMerFeeInfoDO tblMerAliPayT1FeeInfoDO = new TblMerFeeInfoDO();
			tblMerAliPayT1FeeInfoDO.setMerId(platFormMerId);
			tblMerAliPayT1FeeInfoDO.setTermId(platFormTermId);
			tblMerAliPayT1FeeInfoDO.setFeeType("06");
			tblMerAliPayT1FeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(aliPayFeeRateT1, null, null, false));
			tblMerAliPayT1FeeInfoDO.setUserName(userName);
			tblMerAliPayT1FeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerAliPayT1FeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			processOfCount = tblMerFeeInfoDOMapper.insertSelective(tblMerAliPayT1FeeInfoDO);
			if (processOfCount < 0) {
				return insertSuccess;
			}
		}

		// 提现手续费
		// 垫资手续费
		if (!StringUtil.isBlank(withdrawalFeeType)) {
			if (withdrawalFeeType.equals("0") || withdrawalFeeType.equals("2")) {
				TblMerFeeInfoDO tblMerWithdrawalFeeInfoDO = new TblMerFeeInfoDO();
				tblMerWithdrawalFeeInfoDO.setMerId(platFormMerId);
				tblMerWithdrawalFeeInfoDO.setTermId(platFormTermId);
				tblMerWithdrawalFeeInfoDO.setFeeType("07");
				tblMerWithdrawalFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(withdrawalFee, null, null, false));
				tblMerWithdrawalFeeInfoDO.setUserName(userName);
				tblMerWithdrawalFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
				tblMerWithdrawalFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
				processOfCount = tblMerFeeInfoDOMapper.insertSelective(tblMerWithdrawalFeeInfoDO);
				if (processOfCount < 0) {
					return insertSuccess;
				}
			} 			
			if (withdrawalFeeType.equals("1") || withdrawalFeeType.equals("2")) {
				TblMerFeeInfoDO tblMerWithdrawalFeeRateInfoDO = new TblMerFeeInfoDO();
				tblMerWithdrawalFeeRateInfoDO.setMerId(platFormMerId);
				tblMerWithdrawalFeeRateInfoDO.setTermId(platFormTermId);
				tblMerWithdrawalFeeRateInfoDO.setFeeType("08");
				tblMerWithdrawalFeeRateInfoDO
						.setCalcMode(CalcModeUtil.genCalcMode(withdrawalFeeRate, null, null, false));
				tblMerWithdrawalFeeRateInfoDO.setUserName(userName);
				tblMerWithdrawalFeeRateInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
				tblMerWithdrawalFeeRateInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
				processOfCount = tblMerFeeInfoDOMapper.insertSelective(tblMerWithdrawalFeeRateInfoDO);
				if (processOfCount < 0) {
					return insertSuccess;
				}
			}
		}
		if (processOfCount > 0) {
			insertSuccess = true;
		}
		return insertSuccess;
	}

	/**
	 * 写入关联表
	 */
	private Boolean insertInstMerTermRel(String userName, String instId, String merId, String termId,
			String platFormMerId, String platFormTermId) {

		int processOfCount = 0;
		Boolean insertSuccess = false;
		
		if (StringUtils.isBlank(platFormMerId))
			return insertSuccess;
		
		// clean term info
		TblInstMerTermRelDOExample tblInstMerTermRelDOExample = new TblInstMerTermRelDOExample();
		tblInstMerTermRelDOExample.createCriteria().andMerIdEqualTo(platFormMerId);
		tblInstMerTermRelDOMapper.deleteByExample(tblInstMerTermRelDOExample);

		TblInstMerTermRelDO tblInstMerTermRelDO = new TblInstMerTermRelDO();
		tblInstMerTermRelDO.setMerId(platFormMerId);
		tblInstMerTermRelDO.setTermId(platFormTermId);
		tblInstMerTermRelDO.setInstId(instId);
		tblInstMerTermRelDO.setInstMerId(merId);
		tblInstMerTermRelDO.setInstTermId(termId);
		tblInstMerTermRelDO.setUserName(userName);
		tblInstMerTermRelDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblInstMerTermRelDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		processOfCount = tblInstMerTermRelDOMapper.insertSelective(tblInstMerTermRelDO);

		if (processOfCount > 0) {
			insertSuccess = true;
		}
		return insertSuccess;

	}

	/**
	 * 写入终端表
	 */
	private Boolean insertTermInfo(String userName, String termName, String termType, String termProv, String termCity,
			String termCounty, String termAddress, String termSn, String platFormMerId, String platFormTermId) {

		int processOfCount = 0;
		Boolean insertSuccess = false;

		if (StringUtils.isBlank(platFormMerId))
			return insertSuccess;
		
		// clean term info
		TblTermInfoDOExample tblTermInfoDOExample = new TblTermInfoDOExample();
		tblTermInfoDOExample.createCriteria().andMerIdEqualTo(platFormMerId);
		tblTermInfoDOMapper.deleteByExample(tblTermInfoDOExample);

		TblTermInfoDO tblTermInfoDO = new TblTermInfoDO();
		tblTermInfoDO.setMerId(platFormMerId);
		tblTermInfoDO.setTermId(platFormTermId);
		tblTermInfoDO.setTermType(termType);
		tblTermInfoDO.setTermStat("1");
		tblTermInfoDO.setTermName(termName);
		tblTermInfoDO.setTermSn(termSn);

		// 查询省市信息
		Map<String, String> cityProvMap = new HashMap<String, String>();
		cityProvMap = getCityProviceInfo(termCounty);
		if (StringUtils.isBlank(termProv)) {
			termProv = cityProvMap.get("PROVINCE");
		}
		if (StringUtils.isBlank(termCity)) {
			termCity = cityProvMap.get("CITY");
		}

		tblTermInfoDO.setTermInstallProv(termProv);
		tblTermInfoDO.setTermInstallCity(termCity);
		tblTermInfoDO.setTermInstallCounty(termCounty);
		tblTermInfoDO.setTermInstallAddress(termAddress);
		tblTermInfoDO.setUserName(userName);
		tblTermInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblTermInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		processOfCount = tblTermInfoDOMapper.insertSelective(tblTermInfoDO);

		if (processOfCount > 0) {
			insertSuccess = true;
		}
		return insertSuccess;
	}

	
	/**
	 * 写入MCC表
	 */
	private Boolean insertMerRelevanceMcc(String userName, String mccCode, String platFormMerId) {

		int processOfCount = 0;
		Boolean insertSuccess = false;

		if (StringUtils.isBlank(platFormMerId))
			return insertSuccess;
		
		// clean
		TblMerRelevanceMccDoExample tblMerRelevanceMccDoExample = new TblMerRelevanceMccDoExample();
		tblMerRelevanceMccDoExample.createCriteria().andMerIdEqualTo(platFormMerId);
		tblMerRelevanceMccDoMapper.deleteByExample(tblMerRelevanceMccDoExample);

		// get MCC desc
		TblMerCoreMccDoExample tblMerCoreMccDoExample = new TblMerCoreMccDoExample();
		tblMerCoreMccDoExample.createCriteria().andMccValueEqualTo(mccCode);
		List<TblMerCoreMccDo> tblMerCoreMccDo = tblMerCoreMccDoMapper.selectByExample(tblMerCoreMccDoExample);
		String mccDescValue = tblMerCoreMccDo.get(0).getMccDesp();

		TblMerRelevanceMccDo tblMerRelevanceMccDo = new TblMerRelevanceMccDo();
		tblMerRelevanceMccDo.setMccDesp(mccDescValue);
		tblMerRelevanceMccDo.setMerId(platFormMerId);
		tblMerRelevanceMccDo.setMccValue(mccCode);
		tblMerRelevanceMccDo.setMccId(String.valueOf(seqMapper.getSequenceNextVal("MER_MCC_SEQ")));
		tblMerRelevanceMccDo.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerRelevanceMccDo.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblMerRelevanceMccDo.setCreateUser(userName);
		processOfCount = tblMerRelevanceMccDoMapper.insert(tblMerRelevanceMccDo);

		if (processOfCount > 0) {
			insertSuccess = true;
		}
		return insertSuccess;

	}

	/**
	 * 比较进件费率和机构费率设置
	 */
	private Boolean compareFees(TblInstMerAddDetailInfoDO tblInstMerAddDetailInfoDO, String posDebitFeeRate,
			String instPosDebitFeeRate, String posDebitFeeMax, String instPosDebitFeeMax, String posCreditFeeRate,
			String instPosCreditFeeRate, String wechatFeeRateT0, String instWechatFeeRateT0, String wechatFeeRateT1,
			String instWechatFeeRateT1, String aliPayFeeRateT0, String instAliPayFeeRateT0, String aliPayFeeRateT1,
			String instAliPayFeeRateT1, String withdrawalFeeType, String withdrawalFee, String instWithdrawalFee,
			String withdrawalFeeRate, String instWithdrawalFeeRate) {

		Boolean feeSettingHasError = false; 
		
		if (AmtUtil.compareTo(posDebitFeeRate, instPosDebitFeeRate) == -1) {
			tblInstMerAddDetailInfoDO.setResultFlag("F");
			tblInstMerAddDetailInfoDO.setResultDesc("商户借记卡-比例费率小于机构借记卡-比例费率");
			tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
			feeSettingHasError = true; 
			return feeSettingHasError;
		}

		if (AmtUtil.compareTo(posDebitFeeMax, instPosDebitFeeMax) == -1) {
			tblInstMerAddDetailInfoDO.setResultFlag("F");
			tblInstMerAddDetailInfoDO.setResultDesc("商户借记卡-封顶费率小于机构借记卡-封顶费率");
			tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
			feeSettingHasError = true;
			return feeSettingHasError;
		}

		if (AmtUtil.compareTo(posCreditFeeRate, instPosCreditFeeRate) == -1) {
			tblInstMerAddDetailInfoDO.setResultFlag("F");
			tblInstMerAddDetailInfoDO.setResultDesc("商户贷记卡-比例费率小于机构贷记卡-比例费率");
			tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
			feeSettingHasError = true;
			return feeSettingHasError;
		}

		if (!StringUtil.isBlank(wechatFeeRateT0)) {
			if (AmtUtil.compareTo(wechatFeeRateT0, instWechatFeeRateT0) == -1) {
				tblInstMerAddDetailInfoDO.setResultFlag("F");
				tblInstMerAddDetailInfoDO.setResultDesc("商户微信T0费率小于机构微信T0费率");
				tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
				tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
				feeSettingHasError = true;
				return feeSettingHasError;
			}
		}

		if (!StringUtil.isBlank(wechatFeeRateT1)) {
			if (AmtUtil.compareTo(wechatFeeRateT1, instWechatFeeRateT1) == -1) {
				tblInstMerAddDetailInfoDO.setResultFlag("F");
				tblInstMerAddDetailInfoDO.setResultDesc("商户微信T1费率小于机构微信T1费率");
				tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
				tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
				feeSettingHasError = true;
				return feeSettingHasError;
			}
		}

		if (!StringUtil.isBlank(aliPayFeeRateT0)) {
			if (AmtUtil.compareTo(aliPayFeeRateT0, instAliPayFeeRateT0) == -1) {
				tblInstMerAddDetailInfoDO.setResultFlag("F");
				tblInstMerAddDetailInfoDO.setResultDesc("商户支付宝T0费率小于机构支付宝T0费率");
				tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
				tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
				feeSettingHasError = true;
				return feeSettingHasError;
			}
		}

		if (!StringUtil.isBlank(aliPayFeeRateT1)) {
			if (AmtUtil.compareTo(aliPayFeeRateT1, instAliPayFeeRateT1) == -1) {
				tblInstMerAddDetailInfoDO.setResultFlag("F");
				tblInstMerAddDetailInfoDO.setResultDesc("商户支付宝T1费率小于机构支付宝T1费率");
				tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
				tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
				feeSettingHasError = true;
				return feeSettingHasError;
			}
		}

		if (!StringUtil.isBlank(withdrawalFeeType)) {
			if (withdrawalFeeType.equals("0")) {
				if (AmtUtil.compareTo(withdrawalFee, instWithdrawalFee) == -1) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("商户提现手续费小于机构提现手续费率");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					feeSettingHasError = true;
					return feeSettingHasError;
				}
			} else if (withdrawalFeeType.equals("1")) {
				if (AmtUtil.compareTo(withdrawalFeeRate, instWithdrawalFeeRate) == -1) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("商户垫资手续费率小于机构垫资手续费率");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					feeSettingHasError = true;
					return feeSettingHasError;
				}
			} else if (withdrawalFeeType.equals("2")) {
				
				if (AmtUtil.compareTo(withdrawalFee, instWithdrawalFee) == -1) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("提现手续费小于机构提现手续费率");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					feeSettingHasError = true;
					return feeSettingHasError;
				}
				if (AmtUtil.compareTo(withdrawalFeeRate, instWithdrawalFeeRate) == -1) {
					tblInstMerAddDetailInfoDO.setResultFlag("F");
					tblInstMerAddDetailInfoDO.setResultDesc("垫资手续费率小于机构垫资手续费率");
					tblInstMerAddDetailInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
					tblInstMerAddDetailInfoDOMapper.updateByPrimaryKey(tblInstMerAddDetailInfoDO);
					feeSettingHasError = true;
					return feeSettingHasError;
				}				
				
			}
		}
		return feeSettingHasError;
	}

	/**
	 * 检测该机构商户信息是否已经入库
	 **/
	private Map<String, String> merAlreadyExist(String instId, String merId, String termId, String acctNo) {

		Map<String, String> verifyMap = new HashMap<String, String>();
		TblInstMerTermRelDOExample tblInstMerTermRelDOExample = new TblInstMerTermRelDOExample();
		tblInstMerTermRelDOExample.createCriteria().andInstIdEqualTo(instId).andInstMerIdEqualTo(merId)
				.andInstTermIdEqualTo(termId);
		List<TblInstMerTermRelDO> tblInstMerTermRelDOList = tblInstMerTermRelDOMapper
				.selectByExample(tblInstMerTermRelDOExample);

		if (tblInstMerTermRelDOList != null && tblInstMerTermRelDOList.size() != 0) {
			for (TblInstMerTermRelDO tblInstMerTermRelDO : tblInstMerTermRelDOList) {
				// 检查商户入件状态
				TblMerInfoDO merInfoDO = merInfoDOMapper.selectByPrimaryKey(tblInstMerTermRelDO.getMerId());
				if (merInfoDO != null) {
					Boolean channelReported = CheckMerReportedToChannel(merId);
					if(channelReported) {
						verifyMap.put("MER_EXISTING", "Y");
						return verifyMap;
					} else {
						verifyMap.put("GO_UPDATE_MER", "Y");
					}
					verifyMap.put("MER_ID", merInfoDO.getMerId());
					verifyMap.put("TERM_ID", tblInstMerTermRelDO.getTermId());
					merInfoDO.getMerId();
				} else {
					verifyMap.put("GO_ADD_MER_INFO", "Y");
				}
			}
		}else {
			verifyMap.put("GO_ADD_MER_INFO", "Y");
		}

		logger.debug("查看商户银行账号信息是否在系统中存在	");
		TblMerBankInfoDOExample tblMerBankInfoDOExample = new TblMerBankInfoDOExample();
		tblMerBankInfoDOExample.createCriteria().andAcctNoEqualTo(acctNo);
		List<TblMerBankInfoDO> TblMerBankInfoList = tblMerBankInfoDOMapper.selectByExample(tblMerBankInfoDOExample);

		String merOfBankNo = "";
		if (TblMerBankInfoList != null && TblMerBankInfoList.size() != 0) {
			merOfBankNo = TblMerBankInfoList.get(0).getMerId();
			TblMerInfoDO checkMerOfBankAcct = merInfoDOMapper.selectByPrimaryKey(merOfBankNo);
			if (checkMerOfBankAcct != null) {
				Boolean channelReportedOfBank = CheckMerReportedToChannel(merId);
				if(channelReportedOfBank){
				    verifyMap.put("BANK_ACCT_ALREADY_USED", "Y");
					return verifyMap;
				}
			}
		}

		return verifyMap;
	}
	
	

	/**
	 * 对于机构商户, 检查是否成功报备过渠道, 如果报备过, 则认定为审核通过, 不允许再次入件
	 * @param merId
	 * @return
	 */
	public Boolean CheckMerReportedToChannel(String merId){
		
		logger.info("是否配置路由");
		TblRouteControlExample RouteControlExample = new TblRouteControlExample();
		RouteControlExample.createCriteria().andMerIdEqualTo(merId);
		List<TblRouteControl> RouteControlList = RouteControlMapper.selectByExample(RouteControlExample);
		if(RouteControlList != null && RouteControlList.size() > 0){
			return true;	
		}
		
		logger.info("是否报备翰银");
		TblInstRouteControlExample InstRouteControlExample = new TblInstRouteControlExample();
		InstRouteControlExample.createCriteria().andInstMerIdEqualTo(merId);
		List<TblInstRouteControl>  InstRouteControlList = InstRouteControlMapper.selectByExample(InstRouteControlExample);	
		if(InstRouteControlList != null && InstRouteControlList.size() > 0){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 获取城市信息
	 * @param termCounty
	 * @return
	 */
	private Map<String, String> getCityProviceInfo(String termCounty) {
		
		String province = "";
		String provinceId = "";

		Map<String, String> cityProv = new HashMap<>();
		TblCityDOExample tblCityDOExample = new TblCityDOExample();
		tblCityDOExample.createCriteria().andCityNameEqualTo(termCounty);
		List<TblCityDO> tblCityDOs = tblCityDOMapper.selectByExample(tblCityDOExample);

		if (tblCityDOs.isEmpty()) {
			cityProv.put("CITY", termCounty);
			cityProv.put("PROVINCE", termCounty);
			return cityProv;
		}

		provinceId = tblCityDOs.get(0).getProvinceId();
		TblProvDOExample tblProvDOExample = new TblProvDOExample();
		tblProvDOExample.createCriteria().andProvinceIdEqualTo(provinceId);
		List<TblProvDO> tblProvDOs = tblProvDOMapper.selectByExample(tblProvDOExample);

		if (tblProvDOs != null && tblProvDOs.size() > 0) {
			province = tblProvDOs.get(0).getProvinceName();

		} else {
			province = termCounty;
		}
		cityProv.put("CITY", termCounty);
		cityProv.put("PROVINCE", province);
		return cityProv;
	}

	
	/**
	 * 获取费率名称
	 */
	public Map<String, String> getFeeDesc(String feeType) {

		Map<String, String> feeDescMap = new HashMap<String, String>();
		switch (feeType) {
		case ("01"):
			feeDescMap.put("feeDesc", "借记卡T1手续费");
			feeDescMap.put("feeMaxDesc", "借记卡T1封顶手续费");
			break;
		case ("02"):
			feeDescMap.put("feeDesc", "贷记卡T1手续费");
			break;
		case ("03"):
			feeDescMap.put("feeDesc", "微信T0交易手续费");
			break;
		case ("04"):
			feeDescMap.put("feeDesc", "支付宝T0交易手续费");
			break;
		case ("05"):
			feeDescMap.put("feeDesc", "微信T1交易手续费");
			break;
		case ("06"):
			feeDescMap.put("feeDesc", "支付宝T1交易手续费");
			break;
		case ("07"):
			feeDescMap.put("feeDesc", "提现手续费");
			break;
		case ("08"):
			feeDescMap.put("feeDesc", "垫资手续费");
			break;
		default:
			break;
		}
		return feeDescMap;
	}

	
}