package com.allcheer.bpos.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.constant.NotifyException;
import com.allcheer.bpos.domain.MerFee;
import com.allcheer.bpos.domain.PhotoBO;
import com.allcheer.bpos.domain.Minsheng.EnterRepuest;
import com.allcheer.bpos.entity.TBLCoreCardBinDO;
import com.allcheer.bpos.entity.TBLCoreCardBinDOExample;
import com.allcheer.bpos.entity.TblAgentInfoDo;
import com.allcheer.bpos.entity.TblAgentMerTermDo;
import com.allcheer.bpos.entity.TblAgentMerTermDoExample;
import com.allcheer.bpos.entity.TblBankInfo;
import com.allcheer.bpos.entity.TblBankInfoExample;
import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblCityDO;
import com.allcheer.bpos.entity.TblCityDOExample;
import com.allcheer.bpos.entity.TblCustAddressDo;
import com.allcheer.bpos.entity.TblInstMerTermRelDO;
import com.allcheer.bpos.entity.TblInstMerTermRelDOExample;
import com.allcheer.bpos.entity.TblMerBankInfoDO;
import com.allcheer.bpos.entity.TblMerChannelPreInfoDO;
import com.allcheer.bpos.entity.TblMerChannelPreInfoDOExample;
import com.allcheer.bpos.entity.TblMerFeeInfoDO;
import com.allcheer.bpos.entity.TblMerFeeInfoDOExample;
import com.allcheer.bpos.entity.TblMerFileInfoDO;
import com.allcheer.bpos.entity.TblMerFileInfoDOExample;
import com.allcheer.bpos.entity.TblMerInfoDO;
import com.allcheer.bpos.entity.TblOnlineUserMapDO;
import com.allcheer.bpos.entity.TblOnlineUserMapDOExample;
import com.allcheer.bpos.entity.TblProvDO;
import com.allcheer.bpos.entity.TblProvDOExample;
import com.allcheer.bpos.entity.TblSubbranchInfoDO;
import com.allcheer.bpos.entity.TblSubbranchInfoDOExample;
import com.allcheer.bpos.entity.TblTermInfoDO;
import com.allcheer.bpos.entity.Enum.ErrorRespEnum;
import com.allcheer.bpos.entity.Enum.FeeTypeEnum;
import com.allcheer.bpos.entity.Enum.MerStatusEnum;
import com.allcheer.bpos.entity.Enum.MerTypeEnum;
import com.allcheer.bpos.entity.Enum.PhotoTypeEnum;
import com.allcheer.bpos.entity.Enum.SettleTypeEnum;
import com.allcheer.bpos.entity.vo.MerDetailInfo;
import com.allcheer.bpos.form.MerBankInfoForm;
import com.allcheer.bpos.form.MerDetailInfoForm;
import com.allcheer.bpos.form.MerFeeForm;
import com.allcheer.bpos.mapper.SeqMapper;
import com.allcheer.bpos.mapper.TBLCoreCardBinDOMapper;
import com.allcheer.bpos.mapper.TblAgentInfoDoMapper;
import com.allcheer.bpos.mapper.TblAgentMerTermDoMapper;
import com.allcheer.bpos.mapper.TblBankInfoMapper;
import com.allcheer.bpos.mapper.TblBtsInstDOMapper;
import com.allcheer.bpos.mapper.TblCityDOMapper;
import com.allcheer.bpos.mapper.TblCustAddressDoMapper;
import com.allcheer.bpos.mapper.TblInstMerTermRelDOMapper;
import com.allcheer.bpos.mapper.TblMerBankInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerChannelPreInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerFeeInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerFileInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerInfoDOMapper;
import com.allcheer.bpos.mapper.TblOnlineUserLoginDOMapper;
import com.allcheer.bpos.mapper.TblOnlineUserMapDOMapper;
import com.allcheer.bpos.mapper.TblProvDOMapper;
import com.allcheer.bpos.mapper.TblSubbranchInfoCustDOMapper;
import com.allcheer.bpos.mapper.TblSubbranchInfoDOMapper;
import com.allcheer.bpos.mapper.TblTermInfoDOMapper;
import com.allcheer.bpos.service.AddressService;
import com.allcheer.bpos.service.MerchInfoService;
import com.allcheer.bpos.service.OpenLoginUserService;
import com.allcheer.bpos.service.QuickMerService;
import com.allcheer.bpos.util.CalcModeUtil;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.StringUtils;

import jodd.util.StringUtil;

/**
 * Created by fireWorks on 2016/10/25.
 */
@Service("merchInfoService")
public class MerchInfoServiceImpl implements MerchInfoService {

	private final static Logger logger = LoggerFactory.getLogger(MerchInfoServiceImpl.class);
	@Autowired
	SeqMapper seqMapper;

	@Autowired
	TblMerInfoDOMapper merInfoDOMapper;

	@Autowired
	TblMerBankInfoDOMapper merBankInfoDOMapper;

	@Autowired
	TblMerFeeInfoDOMapper merFeeInfoDOMapper;

	@Autowired
	TblBankInfoMapper bankInfoMapper;

	@Autowired
	TblProvDOMapper provDOMapper;

	@Autowired
	TblCityDOMapper cityDOMapper;

	@Autowired
	TblSubbranchInfoCustDOMapper subbranchInfoCustDOMapper;
	
	@Autowired
	TblSubbranchInfoDOMapper SubbranchInfoDOMapper;

	@Autowired
	TblMerChannelPreInfoDOMapper merChannelPreInfoDOMapper;

	@Autowired
	TblInstMerTermRelDOMapper tblInstMerTermRelDOMapper;
	@Autowired
	TblMerFileInfoDOMapper tblMerFileInfoDOMapper;
	@Autowired
	TblAgentMerTermDoMapper tblAgentMerTermDoMapper;

	@Autowired
	TblBtsInstDOMapper tblBtsInstDOMapper;

	@Autowired
	TblAgentInfoDoMapper tblAgentInfoDoMapper;

	@Autowired
	TblCustAddressDoMapper tblCustAddressDoMapper;
	
	@Autowired
	OpenLoginUserService openLoginUserService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	QuickMerService QuickMerService;
	
	@Autowired
	TblOnlineUserLoginDOMapper OnlineUserLoginDOMapper;
	
	@Autowired
	TblOnlineUserMapDOMapper   OnlineUserMapDOMapper;
	
	@Autowired
	TBLCoreCardBinDOMapper CoreCardBinDOMapper;
	
	@Autowired
	TblTermInfoDOMapper TermInfoDOMapper;

	@Override
	public MerDetailInfoForm queryMerDetailInfo2(MerDetailInfoForm merDetailInfoForm) {
		String merId = merDetailInfoForm.getMerId();

		List<MerFee> merFeeList = new ArrayList<>();
		MerDetailInfo merDetailInfo = new MerDetailInfo();

		TblMerInfoDO merInfoDO = merInfoDOMapper.selectByPrimaryKey(merId);

		TblMerFeeInfoDOExample merFeeInfoDOExample = new TblMerFeeInfoDOExample();
		merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId);
		List<TblMerFeeInfoDO> merFeeInfoDOList = merFeeInfoDOMapper.selectByExample(merFeeInfoDOExample);

		for (TblMerFeeInfoDO merFeeInfoDO : merFeeInfoDOList) {
			MerFee merFee = new MerFee();
			merFee.setFeeType(merFeeInfoDO.getFeeType());
			merFee.setFeeDesc(FeeTypeEnum.getNameByCode(merFeeInfoDO.getFeeType()));
			String feeRate = CalcModeUtil.splitCalcMode(merFeeInfoDO.getCalcMode(), true);
			merFee.setFeeRate(feeRate.substring(0, feeRate.indexOf(",")));
			merFeeList.add(merFee);
		}

		TblMerBankInfoDO merBankInfoDO = merBankInfoDOMapper.selectByPrimaryKey(merId);

		merDetailInfoForm.setMerId(merId);
		merDetailInfoForm.setMerName(merInfoDO.getMerName());
		merDetailInfoForm.setRegName(merInfoDO.getRegName());
		merDetailInfoForm.setRegShortName(merInfoDO.getRegShortName());
		// merDetailInfoForm.setMerStat(MerStatusEnum.getNameByCode(merInfoDO.getMerStat()));
		merDetailInfo.setMerStat(merInfoDO.getMerStat());
		merDetailInfoForm.setFuncStat(merInfoDO.getFuncStat());
		merDetailInfoForm.setMerType(MerTypeEnum.getMessage(merInfoDO.getMerType()));
		merDetailInfoForm.setMerAddress(merInfoDO.getMerAddress());
		merDetailInfoForm.setRegFunds(merInfoDO.getRegFunds());
		merDetailInfoForm.setBusLicNm(merInfoDO.getBusLicNm());
		merDetailInfoForm.setBusLicExpire(merInfoDO.getBusLicExpire());
		merDetailInfoForm.setTaxRegCert(merInfoDO.getTaxRegCert());
		merDetailInfoForm.setLegalPerson(merInfoDO.getLegalPerson());
		merDetailInfoForm.setLegalPersonCertType(merInfoDO.getLegalPersonCertType());
		merDetailInfoForm.setLegalPersonCertNm(merInfoDO.getLegalPersonCertNm());
		merDetailInfoForm.setLegalPersonCertExpire(merInfoDO.getLegalPersonCertExpire());
		merDetailInfoForm.setContactPerson(merInfoDO.getContactPerson());
		merDetailInfoForm.setContactMobile(merInfoDO.getContactMobile());
		merDetailInfoForm.setContactEmail(merInfoDO.getContactEmail());
		merDetailInfoForm.setAttachmentPath(merInfoDO.getAttachmentPath());
		merDetailInfoForm.setMerFeeList(merFeeList);
		merDetailInfoForm.setBankName(merBankInfoDO.getBankName());
		merDetailInfoForm.setProvName(merBankInfoDO.getProvName());
		merDetailInfoForm.setCityName(merBankInfoDO.getCityName());
		merDetailInfoForm.setCnaps(merBankInfoDO.getCnaps());
		merDetailInfoForm.setBankBranchName(merBankInfoDO.getBankBranchName());
		merDetailInfoForm.setIsPrivate(SettleTypeEnum.getNameByCode(merBankInfoDO.getIsPrivate()));
		merDetailInfoForm.setAcctName(merBankInfoDO.getAcctName());
		merDetailInfoForm.setAcctNo(merBankInfoDO.getAcctNo());

		return merDetailInfoForm;

	}

	public MerDetailInfo queryMerDetailInfo(String merId) {

		List<MerFee> merFeeList = new ArrayList<>();
		MerDetailInfo merDetailInfo = new MerDetailInfo();

		TblMerInfoDO merInfoDO = merInfoDOMapper.selectByPrimaryKey(merId);

		TblMerFeeInfoDOExample merFeeInfoDOExample = new TblMerFeeInfoDOExample();
		merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId);
		List<TblMerFeeInfoDO> merFeeInfoDOList = merFeeInfoDOMapper.selectByExample(merFeeInfoDOExample);

		for (TblMerFeeInfoDO merFeeInfoDO : merFeeInfoDOList) {
			// if(merFeeInfoDO.getFeeType().equals(FeeTypeEnum.X01.getCode())||merFeeInfoDO.getFeeType().equals(FeeTypeEnum.X04.getCode())){
			//
			// String fee01 =
			// CalcModeUtil.splitCalcMode(merFeeInfoDO.getCalcMode(),true);
			// String[] fee01s = fee01.split(",");
			//
			// MerFee merFee = new MerFee();
			// merFee.setFeeType(merFeeInfoDO.getFeeType());
			// merFee.setFeeDesc(FeeTypeEnum.getNameByCode(merFeeInfoDO.getFeeType()));
			// merFee.setFeeRate(fee01s[0]);
			// merFeeList.add(merFee);
			//
			// MerFee merFee2 = new MerFee();
			// merFee2.setFeeType(merFeeInfoDO.getFeeType()+"L");
			// merFee2.setFeeDesc(FeeTypeEnum.getNameByCode(merFee2.getFeeType()));
			// merFee2.setFeeRate(fee01s[1]);
			// merFeeList.add(merFee2);
			//
			// }else {
			MerFee merFee = new MerFee();
			merFee.setFeeType(merFeeInfoDO.getFeeType());
			merFee.setFeeDesc(FeeTypeEnum.getNameByCode(merFeeInfoDO.getFeeType()));
			String feeRate = CalcModeUtil.splitCalcMode(merFeeInfoDO.getCalcMode(), true);
			if (StringUtils.isNotBlank(feeRate)) {
				feeRate = feeRate.substring(0, feeRate.indexOf(","));
				merFee.setFeeRate(feeRate + "%");
			}
			merFeeList.add(merFee);
			// }
		}

		TblMerBankInfoDO merBankInfoDO = merBankInfoDOMapper.selectByPrimaryKey(merId);

		merDetailInfo.setMerId(merId);
		merDetailInfo.setMerName(merInfoDO.getMerName());
		merDetailInfo.setRegName(merInfoDO.getRegName());
		merDetailInfo.setRegShortName(merInfoDO.getRegShortName());
		merDetailInfo.setMerStat(MerStatusEnum.getNameByCode(merInfoDO.getMerStat()));
		merDetailInfo.setFuncStat(merInfoDO.getFuncStat());
		merDetailInfo.setMerType(MerTypeEnum.getMessage(merInfoDO.getMerType()));
		merDetailInfo.setMerAddress(merInfoDO.getMerAddress());
		merDetailInfo.setRegFunds(merInfoDO.getRegFunds());
		merDetailInfo.setBusLicNm(merInfoDO.getBusLicNm());
		merDetailInfo.setBusLicExpire(merInfoDO.getBusLicExpire());
		merDetailInfo.setTaxRegCert(merInfoDO.getTaxRegCert());
		merDetailInfo.setLegalPerson(merInfoDO.getLegalPerson());
		merDetailInfo.setLegalPersonCertType(merInfoDO.getLegalPersonCertType());
		merDetailInfo.setLegalPersonCertNm(merInfoDO.getLegalPersonCertNm());
		merDetailInfo.setLegalPersonCertExpire(merInfoDO.getLegalPersonCertExpire());
		merDetailInfo.setContactPerson(merInfoDO.getContactPerson());
		merDetailInfo.setContactMobile(merInfoDO.getContactMobile());
		merDetailInfo.setContactEmail(merInfoDO.getContactEmail());
		merDetailInfo.setAttachmentPath(merInfoDO.getAttachmentPath());
		merDetailInfo.setMerFeeList(merFeeList);
		merDetailInfo.setBankName(merBankInfoDO.getBankName());
		merDetailInfo.setProvName(merBankInfoDO.getProvName());
		merDetailInfo.setCityName(merBankInfoDO.getCityName());
		merDetailInfo.setCnaps(merBankInfoDO.getCnaps());
		merDetailInfo.setBankBranchName(merBankInfoDO.getBankBranchName());
		merDetailInfo.setIsPrivate(SettleTypeEnum.getNameByCode(merBankInfoDO.getIsPrivate()));
		merDetailInfo.setAcctName(merBankInfoDO.getAcctName());
		merDetailInfo.setAcctNo(merBankInfoDO.getAcctNo());

		if (StringUtils.isNotBlank(merInfoDO.getAttachmentPath())) {
			File filePath = new File(merInfoDO.getAttachmentPath());
			if (filePath.exists()) {
				String[] files = filePath.list();
				List<PhotoBO> photoBOList = new ArrayList<>();
				for (String file : files) {
					PhotoBO photoBO = getPhotoDesc(file);
					photoBO.setPhotoAddr(merInfoDO.getAttachmentPath() + "/" + file);
					photoBOList.add(photoBO);
				}
				merDetailInfo.setMerPhotos(photoBOList);
			}
		}

		return merDetailInfo;

	}

	private PhotoBO getPhotoDesc(String fileName) {
		PhotoBO photoBO = new PhotoBO();

		try {
			String[] fileNames = fileName.substring(0, fileName.lastIndexOf(".")).split("_");
			String fileDesc = PhotoTypeEnum.getNameByCode(fileNames[0]) + fileNames[1];
			photoBO.setPhotoDesc(fileDesc);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return photoBO;

	}

	@Override
	@Transactional
	public Map<String, String> updateMerBankInfo(MerBankInfoForm merBankInfoForm) throws Exception {
		Map<String, String> resultMap = new HashMap<>();

		int rt = 0;

		TblProvDOExample tblProvDOExample = new TblProvDOExample();
		tblProvDOExample.createCriteria().andProvinceIdEqualTo(merBankInfoForm.getProvName());
		List<TblProvDO> proList = provDOMapper.selectByExample(tblProvDOExample);
		String proName = "";
		if (proList != null && proList.size() > 0) {
			proName = proList.get(0).getProvinceName();
		}

		TblCityDOExample tblCityDOExample = new TblCityDOExample();
		tblCityDOExample.createCriteria().andCityIdEqualTo(merBankInfoForm.getCityName());
		List<TblCityDO> cityDOList = cityDOMapper.selectByExample(tblCityDOExample);
		String cityName = "";
		if (cityDOList != null && cityDOList.size() > 0) {
			cityName = cityDOList.get(0).getCityName();
		} else
			cityName = merBankInfoForm.getCityName();

		TblMerBankInfoDO merBankInfoDO = new TblMerBankInfoDO();
		merBankInfoDO.setMerId(merBankInfoForm.getMerId());
		merBankInfoDO.setBankName(merBankInfoForm.getBankName());
		merBankInfoDO.setProvName(proName);
		merBankInfoDO.setCityName(cityName);
		merBankInfoDO.setCnaps(merBankInfoForm.getCnaps());
		merBankInfoDO.setBankBranchName(merBankInfoForm.getBankBranchName());
		merBankInfoDO.setIsPrivate(merBankInfoForm.getIsPrivate());
		merBankInfoDO.setAcctName(merBankInfoForm.getAcctName());
		merBankInfoDO.setAcctNo(merBankInfoForm.getAcctNo());

		TblMerChannelPreInfoDO merChannelPreInfoDO = new TblMerChannelPreInfoDO();
		merChannelPreInfoDO.setMerId(merBankInfoForm.getMerId());
		merChannelPreInfoDO.setChannelStat(Constant.STRING_0);
		TblMerChannelPreInfoDOExample merChannelPreInfoDOExample = new TblMerChannelPreInfoDOExample();
		merChannelPreInfoDOExample.createCriteria().andMerIdEqualTo(merBankInfoForm.getMerId());

		rt = merBankInfoDOMapper.updateByPrimaryKeySelective(merBankInfoDO);
		rt = rt + merChannelPreInfoDOMapper.updateByExampleSelective(merChannelPreInfoDO, merChannelPreInfoDOExample);

		resultMap.put("statusCode", "200");
		resultMap.put("message", "更新已保存");

		return resultMap;

	}

	@Override
	@Transactional
	public Map<String, String> updateMerfee(MerFeeForm merFeeForm) throws Exception {

		Map<String, String> resultMap = new HashMap<>();
		String merId = merFeeForm.getMerId();
		String termId = merFeeForm.getTermId();
		String userName = merFeeForm.getUserName();
		String DateTime = DateUtil.getCurrentDate() + DateUtil.getCurrentTime();
		TblMerFeeInfoDOExample merFeeInfoDOExample = new TblMerFeeInfoDOExample();

		/**
		 * 机构商户费率 -不带%单位 代理商商户费率 - 带%单位
		 */
		Map<String, String> isMap = new HashMap<String, String>();
		isMap = InstOrAgent(merFeeForm.getMerId());
		Boolean percentFlag = false;
		if (isMap != null && isMap.containsKey("INSTMER") && isMap.get("INSTMER").equals("YES")) {
			percentFlag = false;
		}
		if (isMap != null && isMap.containsKey("AGENTMER") && isMap.get("AGENTMER").equals("YES")) {
			percentFlag = true;
		}

		
		// 借记卡费率和封顶
		if (StringUtils.isNotBlank(merFeeForm.getFee01())) {
			TblMerFeeInfoDO merFeeInfoDO01 = new TblMerFeeInfoDO();
			merFeeInfoDO01.setMerId(merId);
			merFeeInfoDO01.setFeeType("01");
			merFeeInfoDO01.setTermId(termId);
			merFeeInfoDO01.setUserName(userName);
			merFeeInfoDO01.setUpdateTime(DateTime);
			merFeeInfoDO01
					.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFee01(), merFeeForm.getFee01L(), "0", percentFlag));
			merFeeInfoDO01.setUpdateTime(DateTime);
			merFeeInfoDO01.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("01");
			updateFeeByExameple(merFeeInfoDO01, merFeeInfoDOExample);
		}
		
		// 贷记卡费率
		if (StringUtils.isNotBlank(merFeeForm.getFee02())) {
			TblMerFeeInfoDO merFeeInfoDO02 = new TblMerFeeInfoDO();
			merFeeInfoDO02.setMerId(merId);
			merFeeInfoDO02.setFeeType("02");
			merFeeInfoDO02.setTermId(termId);
			merFeeInfoDO02.setUserName(userName);
			merFeeInfoDO02.setUpdateTime(DateTime);
			merFeeInfoDO02.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFee02(), null, null, percentFlag));
			merFeeInfoDO02.setUpdateTime(DateTime);
			merFeeInfoDO02.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample = new TblMerFeeInfoDOExample();
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("02");
			updateFeeByExameple(merFeeInfoDO02, merFeeInfoDOExample);
		}

		// 微信T0交易费
		if (StringUtils.isNotBlank(merFeeForm.getFee03())) {
			TblMerFeeInfoDO merFeeInfoDO03 = new TblMerFeeInfoDO();
			merFeeInfoDO03.setMerId(merId);
			merFeeInfoDO03.setFeeType("03");
			merFeeInfoDO03.setTermId(termId);
			merFeeInfoDO03.setUserName(userName);
			merFeeInfoDO03.setUpdateTime(DateTime);
			merFeeInfoDO03.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFee03(), null, null, percentFlag));
			merFeeInfoDO03.setUpdateTime(DateTime);
			merFeeInfoDO03.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample = new TblMerFeeInfoDOExample();
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("03");
			updateFeeByExameple(merFeeInfoDO03, merFeeInfoDOExample);
		}

		// 支付宝T0交易费
		if (StringUtils.isNotBlank(merFeeForm.getFee04())) {
			TblMerFeeInfoDO merFeeInfoDO04 = new TblMerFeeInfoDO();
			merFeeInfoDO04.setMerId(merId);
			merFeeInfoDO04.setFeeType("04");
			merFeeInfoDO04.setTermId(termId);
			merFeeInfoDO04.setUserName(userName);
			merFeeInfoDO04.setUpdateTime(DateTime);
			merFeeInfoDO04.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFee04(), null, null, percentFlag));
			merFeeInfoDO04.setUpdateTime(DateTime);
			merFeeInfoDO04.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample = new TblMerFeeInfoDOExample();
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("04");
			updateFeeByExameple(merFeeInfoDO04, merFeeInfoDOExample);
		}

		// 微信T1交易费
		if (StringUtils.isNotBlank(merFeeForm.getFee04())) {
			TblMerFeeInfoDO merFeeInfoDO04 = new TblMerFeeInfoDO();
			merFeeInfoDO04.setMerId(merId);
			merFeeInfoDO04.setFeeType("04");
			merFeeInfoDO04.setTermId(termId);
			merFeeInfoDO04.setUserName(userName);
			merFeeInfoDO04.setUpdateTime(DateTime);
			merFeeInfoDO04.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFee04(), null, null, percentFlag));
			merFeeInfoDO04.setUpdateTime(DateTime);
			merFeeInfoDO04.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample = new TblMerFeeInfoDOExample();
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("04");
			updateFeeByExameple(merFeeInfoDO04, merFeeInfoDOExample);
		}
		
		// 微信T1
		if (StringUtils.isNotBlank(merFeeForm.getFee05())) {
			TblMerFeeInfoDO merFeeInfoDO05 = new TblMerFeeInfoDO();
			merFeeInfoDO05.setMerId(merId);
			merFeeInfoDO05.setFeeType("05");
			merFeeInfoDO05.setTermId(termId);
			merFeeInfoDO05.setUserName(userName);
			merFeeInfoDO05.setUpdateTime(DateTime);
			merFeeInfoDO05.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFee05(), null, null, percentFlag));
			merFeeInfoDO05.setUpdateTime(DateTime);
			merFeeInfoDO05.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample = new TblMerFeeInfoDOExample();
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("05");
			updateFeeByExameple(merFeeInfoDO05, merFeeInfoDOExample);
		}

		// 支付宝T1
		if (StringUtils.isNotBlank(merFeeForm.getFee06())) {
			TblMerFeeInfoDO merFeeInfoDO06 = new TblMerFeeInfoDO();
			merFeeInfoDO06.setMerId(merId);
			merFeeInfoDO06.setFeeType("06");
			merFeeInfoDO06.setTermId(termId);
			merFeeInfoDO06.setUserName(userName);
			merFeeInfoDO06.setUpdateTime(DateTime);
			merFeeInfoDO06.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFee06(), null, null, percentFlag));
			merFeeInfoDO06.setUpdateTime(DateTime);
			merFeeInfoDO06.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample = new TblMerFeeInfoDOExample();
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("06");
			updateFeeByExameple(merFeeInfoDO06, merFeeInfoDOExample);
		}

		// 提现单笔
		if (StringUtils.isNotBlank(merFeeForm.getFee07())) {
			TblMerFeeInfoDO merFeeInfoDO07 = new TblMerFeeInfoDO();
			merFeeInfoDO07.setMerId(merId);
			merFeeInfoDO07.setFeeType("07");
			merFeeInfoDO07.setTermId(termId);
			merFeeInfoDO07.setUserName(userName);
			merFeeInfoDO07.setUpdateTime(DateTime);
			merFeeInfoDO07.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFee07(), null, null, percentFlag));
			merFeeInfoDO07.setUpdateTime(DateTime);
			merFeeInfoDO07.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample = new TblMerFeeInfoDOExample();
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("07");
			updateFeeByExameple(merFeeInfoDO07, merFeeInfoDOExample);
		}

		// 提现费率
		if (StringUtils.isNotBlank(merFeeForm.getFee08())) {
			TblMerFeeInfoDO merFeeInfoDO08 = new TblMerFeeInfoDO();
			merFeeInfoDO08.setMerId(merId);
			merFeeInfoDO08.setFeeType("08");
			merFeeInfoDO08.setTermId(termId);
			merFeeInfoDO08.setUserName(userName);
			merFeeInfoDO08.setUpdateTime(DateTime);
			merFeeInfoDO08.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFee08(), null, null, percentFlag));
			merFeeInfoDO08.setUpdateTime(DateTime);
			merFeeInfoDO08.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample = new TblMerFeeInfoDOExample();
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("08");
			updateFeeByExameple(merFeeInfoDO08, merFeeInfoDOExample);
		}

		// 09 = T0 交易费每笔
		if (StringUtils.isNotBlank(merFeeForm.getFee09())) {
			TblMerFeeInfoDO merFeeInfoDO09 = new TblMerFeeInfoDO();
			merFeeInfoDO09.setMerId(merId);
			merFeeInfoDO09.setFeeType("09");
			merFeeInfoDO09.setTermId(termId);
			merFeeInfoDO09.setUserName(userName);
			merFeeInfoDO09.setUpdateTime(DateTime);
			merFeeInfoDO09.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFee09(), null, null, percentFlag));
			merFeeInfoDO09.setUpdateTime(DateTime);
			merFeeInfoDO09.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample = new TblMerFeeInfoDOExample();
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("09");
			updateFeeByExameple(merFeeInfoDO09, merFeeInfoDOExample);
		}
		
		// 快捷支付交易费率 T1 单笔 
		if (StringUtils.isNotBlank(merFeeForm.getFeeQ1())) {
			TblMerFeeInfoDO merFeeInfoDO09 = new TblMerFeeInfoDO();
			merFeeInfoDO09.setMerId(merId);
			merFeeInfoDO09.setFeeType("Q1");
			merFeeInfoDO09.setTermId(termId);
			merFeeInfoDO09.setUserName(userName);
			merFeeInfoDO09.setUpdateTime(DateTime);
			merFeeInfoDO09.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFeeQ1(), null, null, percentFlag));
			merFeeInfoDO09.setUpdateTime(DateTime);
			merFeeInfoDO09.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample = new TblMerFeeInfoDOExample();
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("Q1");
			updateFeeByExameple(merFeeInfoDO09, merFeeInfoDOExample);
		}		

		// 快捷支付交易费率T1 费率 
		if (StringUtils.isNotBlank(merFeeForm.getFeeQ2())) {
			TblMerFeeInfoDO merFeeInfoDO09 = new TblMerFeeInfoDO();
			merFeeInfoDO09.setMerId(merId);
			merFeeInfoDO09.setFeeType("Q2");
			merFeeInfoDO09.setTermId(termId);
			merFeeInfoDO09.setUserName(userName);
			merFeeInfoDO09.setUpdateTime(DateTime);
			merFeeInfoDO09.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFeeQ2(), null, null, percentFlag));
			merFeeInfoDO09.setUpdateTime(DateTime);
			merFeeInfoDO09.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample = new TblMerFeeInfoDOExample();
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("Q2");
			updateFeeByExameple(merFeeInfoDO09, merFeeInfoDOExample);
		}

		// 快捷支付提现费率T0 单笔 
		if (StringUtils.isNotBlank(merFeeForm.getFeeQ3())) {
			TblMerFeeInfoDO merFeeInfoDO09 = new TblMerFeeInfoDO();
			merFeeInfoDO09.setMerId(merId);
			merFeeInfoDO09.setFeeType("Q3");
			merFeeInfoDO09.setTermId(termId);
			merFeeInfoDO09.setUserName(userName);
			merFeeInfoDO09.setUpdateTime(DateTime);
			merFeeInfoDO09.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFeeQ3(), null, null, percentFlag));
			merFeeInfoDO09.setUpdateTime(DateTime);
			merFeeInfoDO09.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample = new TblMerFeeInfoDOExample();
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("Q3");
			updateFeeByExameple(merFeeInfoDO09, merFeeInfoDOExample);
		}

		// 快捷支付提现费率T0 费率
		if (StringUtils.isNotBlank(merFeeForm.getFeeQ4())) {
			TblMerFeeInfoDO merFeeInfoDO09 = new TblMerFeeInfoDO();
			merFeeInfoDO09.setMerId(merId);
			merFeeInfoDO09.setFeeType("Q4");
			merFeeInfoDO09.setTermId(termId);
			merFeeInfoDO09.setUserName(userName);
			merFeeInfoDO09.setUpdateTime(DateTime);
			merFeeInfoDO09.setCalcMode(CalcModeUtil.genCalcMode(merFeeForm.getFeeQ4(), null, null, percentFlag));
			merFeeInfoDO09.setUpdateTime(DateTime);
			merFeeInfoDO09.setUserName(merFeeForm.getUserName());
			merFeeInfoDOExample = new TblMerFeeInfoDOExample();
			merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("Q4");
			updateFeeByExameple(merFeeInfoDO09, merFeeInfoDOExample);
		}
		
		TblMerChannelPreInfoDO merChannelPreInfoDO = new TblMerChannelPreInfoDO();
		TblMerChannelPreInfoDOExample merChannelPreInfoDOExample = new TblMerChannelPreInfoDOExample();
		merChannelPreInfoDOExample.createCriteria().andMerIdEqualTo(merId);
		merChannelPreInfoDO.setMerId(merId);
		merChannelPreInfoDO.setTermId(termId);
		merChannelPreInfoDO.setUserName(userName);
		merChannelPreInfoDO.setUpdateTime(DateTime);
		merChannelPreInfoDO.setChannelStat(Constant.STRING_0);
		merChannelPreInfoDOMapper.updateByExampleSelective(merChannelPreInfoDO, merChannelPreInfoDOExample);

		resultMap.put("statusCode", "200");
		resultMap.put("message", "更新已保存");

		return resultMap;
	}

	/**
	 * 更新费率, 如果不存在, 则新增费率
	 * @param merFeeInfoDO
	 * @param merFeeInfoDOExample
	 * @throws Exception
	 */
	private void updateFeeByExameple(TblMerFeeInfoDO merFeeInfoDO, TblMerFeeInfoDOExample merFeeInfoDOExample)
			throws Exception {
		List<TblMerFeeInfoDO> merFeeInfoDOList = merFeeInfoDOMapper.selectByExample(merFeeInfoDOExample);
		if (merFeeInfoDOList.size() == 0) {
			String createTime = DateUtil.getCurrentDate() + DateUtil.getCurrentTime();
			merFeeInfoDO.setCreateTime(createTime);
			merFeeInfoDOMapper.insertSelective(merFeeInfoDO);

		} else {
			merFeeInfoDOMapper.updateByExampleSelective(merFeeInfoDO, merFeeInfoDOExample);
		}
	}

	/**
	 * 获取商户费率信息
	 */
	@Override
	public MerFeeForm getMerfee(MerFeeForm merFeeForm) {

		String merId = merFeeForm.getMerId();
		merFeeForm.setMerId(merId);

		/**
		 * 机构商户费率 -不带%单位 代理商商户费率 - 带%单位
		 */
		Map<String, String> isMap = new HashMap<String, String>();
		isMap = InstOrAgent(merFeeForm.getMerId());
		Boolean percentFlag = false;
		if (isMap != null && isMap.containsKey("INSTMER") && isMap.get("INSTMER").equals("YES")) {
			percentFlag = false;
		}
		if (isMap != null && isMap.containsKey("AGENTMER") && isMap.get("AGENTMER").equals("YES")) {
			percentFlag = true;
		}

		TblMerFeeInfoDOExample merFeeInfoDOExample = new TblMerFeeInfoDOExample();
		merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("01");
		List<TblMerFeeInfoDO> merFeeInfoDOList = merFeeInfoDOMapper.selectByExample(merFeeInfoDOExample);

		if (merFeeInfoDOList.size() != 0) {
			merFeeForm.setTermId(merFeeInfoDOList.get(0).getTermId());
		}

		if (merFeeInfoDOList.size() != 0) {
			String fee01 = CalcModeUtil.splitCalcMode(merFeeInfoDOList.get(0).getCalcMode(), percentFlag);
			String[] fee01s = fee01.split(",");
			merFeeForm.setFee01(fee01s[0]);
			merFeeForm.setFee01L(fee01s[1]);
		}

		merFeeInfoDOExample = new TblMerFeeInfoDOExample();
		merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("02");
		merFeeInfoDOList = merFeeInfoDOMapper.selectByExample(merFeeInfoDOExample);
		if (merFeeInfoDOList.size() != 0) {
			String fee02 = CalcModeUtil.splitCalcMode(merFeeInfoDOList.get(0).getCalcMode(), percentFlag);
			merFeeForm.setFee02(fee02.substring(0, fee02.indexOf(",")));
		}

		merFeeInfoDOExample = new TblMerFeeInfoDOExample();
		merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("03");
		merFeeInfoDOList = merFeeInfoDOMapper.selectByExample(merFeeInfoDOExample);
		if (merFeeInfoDOList.size() != 0) {
			String fee03 = CalcModeUtil.splitCalcMode(merFeeInfoDOList.get(0).getCalcMode(), percentFlag);
			merFeeForm.setFee03(fee03.substring(0, fee03.indexOf(",")));
		}

		merFeeInfoDOExample = new TblMerFeeInfoDOExample();
		merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("04");
		merFeeInfoDOList = merFeeInfoDOMapper.selectByExample(merFeeInfoDOExample);
		if (merFeeInfoDOList.size() != 0) {
			String fee04 = CalcModeUtil.splitCalcMode(merFeeInfoDOList.get(0).getCalcMode(), percentFlag);
			String[] fee04s = fee04.split(",");
			merFeeForm.setFee04(fee04s[0]);
		}

		merFeeInfoDOExample = new TblMerFeeInfoDOExample();
		merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("05");
		merFeeInfoDOList = merFeeInfoDOMapper.selectByExample(merFeeInfoDOExample);
		if (merFeeInfoDOList.size() != 0) {
			String fee05 = CalcModeUtil.splitCalcMode(merFeeInfoDOList.get(0).getCalcMode(), percentFlag);
			merFeeForm.setFee05(fee05.substring(0, fee05.indexOf(",")));
		}

		merFeeInfoDOExample = new TblMerFeeInfoDOExample();
		merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("06");
		merFeeInfoDOList = merFeeInfoDOMapper.selectByExample(merFeeInfoDOExample);
		if (merFeeInfoDOList.size() != 0) {
			String fee06 = CalcModeUtil.splitCalcMode(merFeeInfoDOList.get(0).getCalcMode(), percentFlag);
			merFeeForm.setFee06(fee06.substring(0, fee06.indexOf(",")));
		}

		merFeeInfoDOExample = new TblMerFeeInfoDOExample();
		merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("07");
		merFeeInfoDOList = merFeeInfoDOMapper.selectByExample(merFeeInfoDOExample);
		if (merFeeInfoDOList.size() != 0) {
			String fee07 = CalcModeUtil.splitCalcMode(merFeeInfoDOList.get(0).getCalcMode(), percentFlag);
			merFeeForm.setFee07(fee07.substring(0, fee07.indexOf(",")));
		}

		merFeeInfoDOExample = new TblMerFeeInfoDOExample();
		merFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andFeeTypeEqualTo("08");
		merFeeInfoDOList = merFeeInfoDOMapper.selectByExample(merFeeInfoDOExample);
		if (merFeeInfoDOList.size() != 0) {
			String fee08 = CalcModeUtil.splitCalcMode(merFeeInfoDOList.get(0).getCalcMode(), percentFlag);
			merFeeForm.setFee08(fee08.substring(0, fee08.indexOf(",")));
		}

		return merFeeForm;
	}

	@Override
	public MerBankInfoForm getMerBankInfo(MerBankInfoForm merBankInfoForm) {

		String merId = merBankInfoForm.getMerId();
		merBankInfoForm.setMerId(merId);

		TblMerBankInfoDO merBankInfoDO = merBankInfoDOMapper.selectByPrimaryKey(merId);

		List<String> bankInfoList = subbranchInfoCustDOMapper.selectAllBank();

		TblProvDOExample provDOExample = new TblProvDOExample();
		List<TblProvDO> provDOList = provDOMapper.selectByExample(provDOExample);
		if (merBankInfoDO != null) {
			try {
				BeanUtils.copyProperties(merBankInfoForm, merBankInfoDO);
				merBankInfoForm.setBankIdList(bankInfoList);
				merBankInfoForm.setBankProvNameList(provDOList);
			} catch (Exception ex) {
				logger.error("{}", ex.getMessage());

			}
		}

		return merBankInfoForm;

	}

	/**
	 * BPOS 入驻商户
	 */
	@Override
	@Transactional
	public Map<String, String> addMerInfo(EnterRepuest rep) {
		
		logger.info("商户入驻开始");
		
		Map<String, String> addResult = new HashMap<>();

		long platFormMerIdSeq = seqMapper.getSequenceNextVal("SEQ_PLATFORM_MER_ID");
		String merId = DateUtil.getCurrentDate() + StringUtils.leftPad(String.valueOf(platFormMerIdSeq), 7, '0');
		String userName = "MER_ENTER_API";
		String updateTime = DateUtil.getCurrentDate() + DateUtil.getCurrentTime();
		String payType = rep.getPayType();
		String merTermId = "00000000";

		logger.info("添加商户信息表 - TBL_MER_INFO");
		TblMerInfoDO tblMerInfoDO = new TblMerInfoDO();
		tblMerInfoDO.setMerId(merId);
		tblMerInfoDO.setMerName(rep.getMerName());
		tblMerInfoDO.setRegName(rep.getMerName());
		tblMerInfoDO.setMerType(rep.getMerType());
		tblMerInfoDO.setLegalPerson(rep.getLegalPerson());
		tblMerInfoDO.setLegalPersonCertType(rep.getLegalPersonCertType());
		tblMerInfoDO.setLegalPersonCertNm(rep.getLegalPersonCertNm());
		tblMerInfoDO.setLegalPersonCertExpire(rep.getLegalPersonCertExpire());
		tblMerInfoDO.setContactPerson(rep.getContactPerson());
		tblMerInfoDO.setContactMobile(rep.getContactMobile());
		tblMerInfoDO.setFuncStat(rep.getFuncStat());
		tblMerInfoDO.setMerStat(rep.getMerStat());
		tblMerInfoDO.setUserName(userName);
		tblMerInfoDO.setUpdateTime(updateTime);
		tblMerInfoDO.setCreateTime(updateTime);
		tblMerInfoDO.setRegShortName(rep.getRegShortName());
		tblMerInfoDO.setMerAddress(rep.getMerAddress());
		tblMerInfoDO.setBusLicNm(rep.getBusLincenNumber());
		tblMerInfoDO.setBusLicExpire(rep.getBusLincenExpireDate());
		int int1 = merInfoDOMapper.insert(tblMerInfoDO);
		if (int1 <= 0) {
			throw new NotifyException(ErrorRespEnum.RESP099999, "商户信息添加失败");
		}

		if("03".equals(payType) || "04".equals(payType) || "99".equals(payType)){
			logger.info("POS入件,添加终端 - TBL_TERM_INFO");
			
			merTermId = createMerTermId(); //创建新的终端号
			
			TblTermInfoDO tblTermInfoDO = new TblTermInfoDO();
			tblTermInfoDO.setMerId(merId);
			tblTermInfoDO.setTermId(merTermId);
			tblTermInfoDO.setTermType(rep.getOutTermType());
			tblTermInfoDO.setTermStat("1");
			tblTermInfoDO.setTermName(rep.getOutTermName());
			tblTermInfoDO.setTermSn(rep.getOutTermSN());
			tblTermInfoDO.setTermInstallProv(rep.getOutTermProv());
			tblTermInfoDO.setTermInstallCity(rep.getOutTermCity());
			tblTermInfoDO.setTermInstallCounty(rep.getOutTermArea());
			tblTermInfoDO.setTermInstallAddress(rep.getOutTermAddress());
			tblTermInfoDO.setUserName(userName);
			tblTermInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblTermInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int k = TermInfoDOMapper.insertSelective(tblTermInfoDO);
			if(k <= 0 ){
				throw new NotifyException(ErrorRespEnum.RESP099999, "POS入件-添加终端失败");
			}			
		}

		logger.info("添加商户信息关联表 - TBL_INST_MER_TERM_REL");
		TblInstMerTermRelDO instMer = new TblInstMerTermRelDO();
		instMer.setInstId(rep.getInsNum());
		instMer.setMerId(merId);
		instMer.setTermId(merTermId);
		
		String instMerId = rep.getOutMerId();
		if(StringUtils.isBlank(instMerId)){
			instMerId = rep.getContactMobile();
		}
		
		instMer.setInstMerId(instMerId);
		instMer.setInstTermId(rep.getOutTermNo());
		instMer.setUserName(userName);
		instMer.setCreateTime(updateTime);
		instMer.setUpdateTime(updateTime);
		int int2 = tblInstMerTermRelDOMapper.insert(instMer);
		if (int2 <= 0) {
			throw new NotifyException(ErrorRespEnum.RESP099999, "商户关联信息表添加失败");
		}

		logger.info("添加商户费率表 - TBL_MER_FEE_INFO");
		String WXT0 = rep.getWXT0();
		String WXT1 = rep.getWXT1();
		String ZFBT0 = rep.getZFBT0();
		String ZFBT1 = rep.getZFBT1();
		String debitCardFee = rep.getDebitCardFee();
		String debitCardMax = rep.getDebitCardMax();
		String creditCardFee = rep.getCreditCardFee();
		String factorageT0 = rep.getFactorageT0();
		String factorageT1 = rep.getFactorageT1();
		String creditCardFeeFixed = rep.getCreditCardFeeFixed();
		String qcPayFeeT1 = rep.getQcPayFeeT1();
		String qcPayFeeRateT1 = rep.getQcPayFeeRateT1();
		String qcWithDrawFeeT0 = rep.getQcWithDrawFeeT0();
		String qcWithDrawFeeRateT0 = rep.getQcWithDrawFeeRateT0();		

		// 借记卡
		if (!StringUtil.isBlank(debitCardFee) && !StringUtil.isBlank(debitCardMax)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("01");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(debitCardFee, debitCardMax, "0", false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "借记卡费率添加失败");
			}
		}

		// 贷记卡
		if (!StringUtil.isBlank(creditCardFee)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("02");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(creditCardFee, null, null, false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "贷记卡费率添加失败");
			}
		}

		// 微信T0
		if (!StringUtil.isBlank(WXT0)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("03");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(WXT0, null, null, false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "微信T0费率添加失败");
			}
		}

		// 微信T1
		if (!StringUtil.isBlank(WXT1)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("05");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(WXT1, null, null, false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "微信T1费率添加失败");
			}
		}

		// 支付宝T0
		if (!StringUtil.isBlank(ZFBT0)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("04");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(ZFBT0, null, null, false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "支付宝T0费率添加失败");
			}
		}

		// 支付宝T1
		if (!StringUtil.isBlank(ZFBT1)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("06");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(ZFBT1, null, null, false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "支付宝T1费率添加失败");
			}
		}

		// 提现手续费
		if (!StringUtil.isBlank(factorageT0)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("07");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(factorageT0, null, null, false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "提现手续费费添加失败");
			}
		}
		
		//垫资手续费
		if (!StringUtil.isBlank(factorageT1)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("08");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(factorageT1, null, null, false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "垫资手续费率添加失败");
			}
		}

		//贷记卡交易费 - 单笔
		if (!StringUtil.isBlank(creditCardFeeFixed)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("09");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(creditCardFeeFixed, null, null, false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "贷记卡交易费 单笔-添加失败");
			}
		}

		
		//贷记卡交易费 - 单笔
		if (!StringUtil.isBlank(creditCardFeeFixed)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("09");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(creditCardFeeFixed, null, null, false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "贷记卡交易费 单笔-添加失败");
			}
		}
		
		//快捷支付 - 交易T1每笔
		if (!StringUtil.isBlank(qcPayFeeT1)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("Q1");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(qcPayFeeT1, null, null, false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "快捷支付 -交易T1-单笔添加失败");
			}
		}
		
		//快捷支付 - 交易T1比例
		if (!StringUtil.isBlank(qcPayFeeRateT1)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("Q2");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(qcPayFeeRateT1, null, null, false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "快捷支付 -交易T1-比例添加失败");
			}
		}
		
		//快捷支付 - 提现T0-每笔
		if (!StringUtil.isBlank(qcWithDrawFeeT0)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("Q3");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(qcWithDrawFeeT0, null, null, false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "快捷支付 -提现T0-单笔添加失败");
			}
		}
		//快捷支付 - 提现T0-比例
		if (!StringUtil.isBlank(qcWithDrawFeeRateT0)) {
			TblMerFeeInfoDO tblMerPosDebitFeeInfoDO = new TblMerFeeInfoDO();
			tblMerPosDebitFeeInfoDO.setMerId(merId);
			tblMerPosDebitFeeInfoDO.setTermId(merTermId);
			tblMerPosDebitFeeInfoDO.setFeeType("Q4");
			tblMerPosDebitFeeInfoDO.setCalcMode(CalcModeUtil.genCalcMode(qcWithDrawFeeRateT0, null, null, false));
			tblMerPosDebitFeeInfoDO.setUserName(userName);
			tblMerPosDebitFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerPosDebitFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			int feeCount1 = merFeeInfoDOMapper.insertSelective(tblMerPosDebitFeeInfoDO);
			if (feeCount1 <= 0) {
				throw new NotifyException(ErrorRespEnum.RESP099999, "快捷支付 -提现T0-比例添加失败");
			}
		}		
		
		logger.info("BPOS商户入驻-添加结算信息");
		TblMerBankInfoDO merBankInfo = new TblMerBankInfoDO();
		merBankInfo.setMerId(merId);
		merBankInfo.setBankName(rep.getBankName());
		merBankInfo.setProvName(rep.getProvName());
		merBankInfo.setCityName(rep.getCityName());
		merBankInfo.setCnaps(rep.getDebitCardLines());
		merBankInfo.setBankBranchName(rep.getBankBranchName());
		merBankInfo.setIsPrivate(rep.getIsPrivate());
		merBankInfo.setAcctName(rep.getDebitCardName());
		merBankInfo.setAcctNo(rep.getDebitCardNum());
		merBankInfo.setUserName(userName);
		merBankInfo.setUpdateTime(updateTime);
		merBankInfo.setCreateTime(updateTime);
		int int3 = merBankInfoDOMapper.insert(merBankInfo);
		if(int3 <= 0){
			throw new NotifyException(ErrorRespEnum.RESP099999, "商户结算信息添加失败");
		}

		logger.info("BPOS商户入驻-添加地址信息信息");
		String provinceCode = rep.getMerProvince();
		String cityCode = rep.getMerCity();
		String areaCode = rep.getMerArea();
				
		if(StringUtils.isNotBlank(provinceCode) && StringUtils.isNotBlank(cityCode) && StringUtils.isNotBlank(areaCode)){
			TblCustAddressDo tblCustAddressDo = new TblCustAddressDo();
			tblCustAddressDo.setMerId(merId);
			tblCustAddressDo.setMerAddress(rep.getMerAddress());
			tblCustAddressDo.setProvinceCode(provinceCode);
			tblCustAddressDo.setCityCode(cityCode);
			tblCustAddressDo.setAreaCode(areaCode);
			int int4  =tblCustAddressDoMapper.insert(tblCustAddressDo);		
			if(int4 <= 0){
				throw new NotifyException(ErrorRespEnum.RESP099999, "商户地址代码添加失败");
			}
		}

		logger.info("为机构商户开通大众登陆账户");
		openLoginUserService.openNewGeneralMer(merId);
		
		//为快捷支付创建登陆账户
		if("5".equals(rep.getPayType()) || "99".equals(rep.getPayType())){
			Boolean s = QuickMerService.saveQuickMerIntoSystem(tblMerInfoDO, rep.getInsNum(), "");
			if(!s){
				throw new NotifyException(ErrorRespEnum.RESP099999, "快捷商户入库失败");
			}
		}
			
		addResult.put("MERID", merId);
		addResult.put("TERMID", merTermId);
		
		return addResult;
	}

	/**
	 * 检查商户是否已经入住
	 * @param rep
	 * @return
	 */
	@Override
	public String merExisted(EnterRepuest rep) {
		
		String payType = rep.getPayType();
		String returnStr = "";
		if("5".equals(payType) || "99".equals(payType)){
			//快捷支付入住,检查手机号是否已经注册过
			TblOnlineUserMapDOExample OnlineUserMapDOExample = new TblOnlineUserMapDOExample();
			OnlineUserMapDOExample.createCriteria().andUsrNameEqualTo(rep.getContactMobile());
			List<TblOnlineUserMapDO> OnlineUserMapDOList = OnlineUserMapDOMapper.selectByExample(OnlineUserMapDOExample);
			if (OnlineUserMapDOList != null && OnlineUserMapDOList.size() > 0) {
				returnStr += "【该手机号已经注册过快捷支付】";
			}
		}
		
		TblMerInfoDO tblMerInfoDO = new TblMerInfoDO();
		tblMerInfoDO.setLegalPersonCertNm(rep.getLegalPersonCertNm());
		int b = merInfoDOMapper.selectMerByCertNum(tblMerInfoDO);
		if(b >= 5){
			returnStr += "【该身份证已经注册5次】";
		}

		TblMerBankInfoDO merBankInfoDO = new TblMerBankInfoDO();
		merBankInfoDO.setAcctName(rep.getDebitCardName());
		merBankInfoDO.setAcctNo(rep.getDebitCardNum());
		int c = merInfoDOMapper.selectMerByBankAccountNo(merBankInfoDO);
		if(c >= 5){
			returnStr += "【该结算账户已经注册5次】";
		}
		
		return returnStr; 
	}

	@Override
	public List<TblMerFileInfoDO> getMerPhotosFileInfo(String merId) {
		TblMerFileInfoDOExample tblMerFileInfoDOExample = new TblMerFileInfoDOExample();
		tblMerFileInfoDOExample.createCriteria().andMerIdEqualTo(merId);
		List<TblMerFileInfoDO> tblMerFileInfoDOList = tblMerFileInfoDOMapper.selectByExample(tblMerFileInfoDOExample);
		return tblMerFileInfoDOList;
	}

	/**
	 * 判断商户是机构商户还是代理商商户
	 * 
	 * @param MerID
	 * @return
	 */
	@Override
	public Map<String, String> InstOrAgent(String merId) {

		Map<String, String> result = new HashMap<String, String>();

		// 检查是否是机构商户
		TblInstMerTermRelDOExample tblInstMerTermRelDOExample = new TblInstMerTermRelDOExample();
		tblInstMerTermRelDOExample.createCriteria().andMerIdEqualTo(merId);
		List<TblInstMerTermRelDO> tblInstMerTermRelDo = tblInstMerTermRelDOMapper
				.selectByExample(tblInstMerTermRelDOExample);

		if (tblInstMerTermRelDo != null && tblInstMerTermRelDo.size() > 0) {
			result.put("INSTMER", "YES");
			result.put("TERMID", tblInstMerTermRelDo.get(0).getTermId());
			result.put("INSTMERID", tblInstMerTermRelDo.get(0).getInstMerId());
			result.put("INSTID", tblInstMerTermRelDo.get(0).getInstId());
			return result;
		}

		TblAgentMerTermDoExample tblAgentMerTermDoExample = new TblAgentMerTermDoExample();
		tblAgentMerTermDoExample.createCriteria().andMerIdEqualTo(merId);
		List<TblAgentMerTermDo> tblAgentMerTermRelDo = tblAgentMerTermDoMapper
				.selectByExample(tblAgentMerTermDoExample);

		if (tblAgentMerTermRelDo != null && tblAgentMerTermRelDo.size() > 0) {
			result.put("AGENTID", tblAgentMerTermRelDo.get(0).getAgentId());
			result.put("TERMID", tblAgentMerTermRelDo.get(0).getTermId());
			result.put("AGENTMER", "YES");
		}

		return result;
	}

	/**
	 * 判断是机构,是商户,还是代理商
	 * 
	 * @param MerID
	 * @return
	 */
	@Override
	public Map<String, String> InstAgentMer(String merNumber) {

		Map<String, String> result = new HashMap<String, String>();

		if (StringUtils.isBlank(merNumber)) {
			return result;
		}

		if (merNumber.length() < 15) {
			logger.info("长度小于15,判断是否是机构:" + merNumber);
			TblBtsInstDO BtsInstDo = tblBtsInstDOMapper.selectByPrimaryKey(merNumber);
			if (BtsInstDo != null) {
				result.put("INST", "YES");
				return result;
			} else {
				return result;
			}
		}

		logger.info("判断是否是机构:" + merNumber);
		TblAgentInfoDo AgentInfoDo = tblAgentInfoDoMapper.selectByPrimaryKey(merNumber);
		if (AgentInfoDo != null) {
			result.put("AGENT", "YES");
			return result;
		}

		logger.info("判断是否是商户:" + merNumber);
		TblMerInfoDO merInfoDO = merInfoDOMapper.selectByPrimaryKey(merNumber);
		if (merInfoDO != null) {
			result.put("MER", "YES");
			return result;
		}

		return result;
	}

	
	/**
	 * 判断机构是否注册
	 * @param instId
	 * @return
	 */
	@Override
	public Boolean isInstExist (String instId){
			
		TblBtsInstDO BtsInstDo = tblBtsInstDOMapper.selectByPrimaryKey(instId);
		
		if(BtsInstDo !=null){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 验证结算信息
	 */
	@Override
	public String verifyBankInfo(String bankName, String subBankBranchNumber, String payType, String bankAcctNo) {

		String errorMessage = "";
		
		//验证开卡总行信息
		TblBankInfoExample BankInfoExample = new TblBankInfoExample();
		BankInfoExample.createCriteria().andBankNameLike("%" + bankName + "%");
		List<TblBankInfo> BankInfoList = bankInfoMapper.selectByExample(BankInfoExample);
		if (BankInfoList == null || BankInfoList.size() == 0) {
			errorMessage += "【开卡行总行未入库】";
		}
		String bankId = BankInfoList.get(0).getBankId();

        //验证开卡支行信息
		TblSubbranchInfoDO SubbranchInfoDO = SubbranchInfoDOMapper.selectByPrimaryKey(subBankBranchNumber);
		if(SubbranchInfoDO == null){
			errorMessage += "【开卡分行信息未入库】";
		}
		
		//对于无卡快捷支付, 只能入驻借记卡
		int len = bankAcctNo.length();
		TBLCoreCardBinDOExample CoreCardBinDOExample = new TBLCoreCardBinDOExample();
		CoreCardBinDOExample.createCriteria().andIssuerCodeEqualTo(bankId).andCardNoLenEqualTo(len).andDebitCreditEqualTo("1");
		List<TBLCoreCardBinDO> CoreCardBinDOList= CoreCardBinDOMapper.selectByExample(CoreCardBinDOExample);
		if(CoreCardBinDOList == null || CoreCardBinDOList.size() == 0){
			errorMessage += "【开卡行卡Bin未入库】";
		}
		
		//无卡快捷只允许借记卡
		if("05".equals(payType) || "99".equals(payType)){
			Boolean correctBin = false;
			for (TBLCoreCardBinDO CoreCardBinDO:CoreCardBinDOList){
				int binlen = CoreCardBinDO.getBinLen();
				String binnum = CoreCardBinDO.getBinNum();
				String inBinStr = bankAcctNo.substring(0, binlen);
				if(inBinStr.equals(binnum)){
					correctBin = true;
				}
			}
			if(!correctBin){
				errorMessage += "【快捷支付-只允许借记卡录入】";
			}
		}
		return errorMessage;
	}
	
	/**
	 * 获取终端号
	 * @return
	 */
	private String createMerTermId() {
		long platFormTermIdSeq = seqMapper.getSequenceNextVal("SEQ_PLATFORM_TERM_ID");
		return StringUtils.leftPad(String.valueOf(platFormTermIdSeq), 8, '0');
	}
}