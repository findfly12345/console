package com.allcheer.bpos.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.domain.InstBO;
import com.allcheer.bpos.domain.InstChannelMapBO;
import com.allcheer.bpos.domain.InstMerBO;
import com.allcheer.bpos.domain.InstMerKeyBO;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.TblAgentInfoDo;
import com.allcheer.bpos.entity.TblAiLoginUsrDo;
import com.allcheer.bpos.entity.TblBTSSysRoleDO;
import com.allcheer.bpos.entity.TblBTSSysRoleDOExample;
import com.allcheer.bpos.entity.TblBTSSysUsrDO;
import com.allcheer.bpos.entity.TblBTSSysUsrDOExample;
import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblBtsInstDOExample;
import com.allcheer.bpos.entity.TblBtsInstMerDO;
import com.allcheer.bpos.entity.TblBtsInstMerDOExample;
import com.allcheer.bpos.entity.TblChannelMerKeyInfoDO;
import com.allcheer.bpos.entity.TblChannelMerKeyInfoDOExample;
import com.allcheer.bpos.entity.TblInstChannelMapDO;
import com.allcheer.bpos.entity.TblInstChannelMapDOExample;
import com.allcheer.bpos.entity.TblInstKeyInfo;
import com.allcheer.bpos.entity.TblInstMerKeyInfoDO;
import com.allcheer.bpos.entity.TblInstMerKeyInfoDOExample;
import com.allcheer.bpos.entity.TblInstTransAuth;
import com.allcheer.bpos.entity.TblMerBankInfoDO;
import com.allcheer.bpos.entity.TblMerBankInfoDOExample;
import com.allcheer.bpos.entity.TblMerFeeInfoDO;
import com.allcheer.bpos.entity.TblMerFeeInfoDOExample;
import com.allcheer.bpos.entity.TblMerInfoDO;
import com.allcheer.bpos.entity.TblMerRelevanceMccDo;
import com.allcheer.bpos.entity.TblMerRelevanceMccDoExample;
import com.allcheer.bpos.entity.Enum.InstTypeEnum;
import com.allcheer.bpos.entity.vo.TblAgentMerInfoVO;
import com.allcheer.bpos.entity.vo.TblMerInfoVO;
import com.allcheer.bpos.mapper.MerInfoCustVOMapper;
import com.allcheer.bpos.mapper.SeqMapper;
import com.allcheer.bpos.mapper.TblAiLoginUsrDoMapper;
import com.allcheer.bpos.mapper.TblBTSInstStatDOMapper;
import com.allcheer.bpos.mapper.TblBTSSysRoleDOMapper;
import com.allcheer.bpos.mapper.TblBTSSysUsrDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstMerDOMapper;
import com.allcheer.bpos.mapper.TblChannelMerKeyInfoDOMapper;
import com.allcheer.bpos.mapper.TblInstChannelMapDOMapper;
import com.allcheer.bpos.mapper.TblInstKeyInfoMapper;
import com.allcheer.bpos.mapper.TblInstMerKeyInfoDOMapper;
import com.allcheer.bpos.mapper.TblInstTransAuthMapper;
import com.allcheer.bpos.mapper.TblMerBankInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerFeeInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerRelevanceMccDoMapper;
import com.allcheer.bpos.service.InstitutionService;
import com.allcheer.bpos.service.MonitorRealm;
import com.allcheer.bpos.service.UserService;
import com.allcheer.bpos.service.WechatRegisterService;
import com.allcheer.bpos.shiro.CustomCredentialsMatcher;
import com.allcheer.bpos.util.BposException;
import com.allcheer.bpos.util.CalcModeUtil;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.RandomString;
import com.allcheer.bpos.util.StringUtils;
import com.allcheer.bpos.util.SystemConstant;
import com.allcheer.bpos.util.constant.CommonConstants;
import com.github.pagehelper.PageHelper;

/**
 * Created by fireWorks on 2016/2/27.
 */
@Service("institutionService")
public class InstitutionServiceImpl implements InstitutionService {

	@Autowired
	TblBtsInstDOMapper tblBtsInstDOMapper;

	@Autowired
	TblBTSSysUsrDOMapper tblBTSSysUsrDOMapper;

	@Autowired
	SeqMapper seqMapper;

	@Autowired
	UserService userService;

	@Autowired
	TblBTSSysRoleDOMapper roleDOMapper;

	@Autowired
	TblBTSInstStatDOMapper instStatDOMapper;

	@Autowired
	TblBtsInstMerDOMapper instMerDOMapper;

	@Autowired
	TblInstMerKeyInfoDOMapper instMerKeyInfoDOMapper;

	@Autowired
	TblChannelMerKeyInfoDOMapper channelMerKeyInfoDOMapper;

	@Autowired
	TblInstChannelMapDOMapper instChannelMapDOMapper;

	@Autowired
	TblInstKeyInfoMapper tblInstKeyInfoMapper;

	@Autowired
	TblInstTransAuthMapper tblInstTransAuthMapper;

    @Autowired
    private TblAiLoginUsrDoMapper tblAiLoginUsrDoMapper;
    
    @Autowired
    private MerInfoCustVOMapper merInfoCustVOMapper;
    
	@Autowired
	WechatRegisterService wechatRegisterService;

	@Autowired
	TblMerFeeInfoDOMapper tblMerFeeInfoDOMapper;
	
	@Autowired
	TblMerBankInfoDOMapper tblMerBankInfoDOMapper;
	
	@Autowired
	private TblMerRelevanceMccDoMapper tblMerRelevanceMccDoMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(InstitutionServiceImpl.class);

	private String getInstCode() {
		int codeSize = 8;
		int codelen = 0;
		String fill = "0";
		String code = tblBtsInstDOMapper.instCodeNext();
		if (code != null && (!"".equals(code) && code.length() <= codeSize)) {
			codelen = code.length();
			for (; codeSize > codelen; codelen++) {
				code = fill + code;
			}
		}
		return code;
	}

	public List<TblBtsInstDO> getALLInst() {
		TblBtsInstDOExample instDOExample = new TblBtsInstDOExample();
		List<TblBtsInstDO> instDOList = tblBtsInstDOMapper.selectByExample(instDOExample);
		return instDOList;
	}

	public TblBtsInstDO getInstById(String instCode) {
		TblBtsInstDOExample instDOExample = new TblBtsInstDOExample();
		instDOExample.createCriteria().andInstCodeEqualTo(instCode);

		List<TblBtsInstDO> instDOList = tblBtsInstDOMapper.selectByExample(instDOExample);
		if (instDOList == null || instDOList.size() == 0) {
			return null;
		} else {
			return instDOList.get(0);
		}
	}

	public Pagination<InstBO> getTheInst(InstBO instBO) {

		TblBtsInstDOExample tblBtsInstDOExample = new TblBtsInstDOExample();
		TblBtsInstDOExample.Criteria cri = tblBtsInstDOExample.createCriteria();
		if (instBO.getInstCode() != null && !instBO.getInstCode().trim().equals("")) {
			cri.andInstCodeEqualTo(instBO.getInstCode());
		}
		if (instBO.getInstName() != null && !instBO.getInstName().trim().equals("")) {
			//cri.andInstNameEqualTo(instBO.getInstName());
			cri.andInstNameLike("%"+instBO.getInstName()+"%");
		}
		if (instBO.getInstType() != null && !instBO.getInstType().trim().equals("")) {
			cri.andInstTypeEqualTo(instBO.getInstType());

		}

		int count = tblBtsInstDOMapper.countByExample(tblBtsInstDOExample);
		Pagination<InstBO> pagination = new Pagination<InstBO>(count, instBO.getPageCurrent(), instBO.getPageSize());
		PageHelper.startPage(instBO.getPageCurrent(), instBO.getPageSize());
		List<TblBtsInstDO> instDOList = tblBtsInstDOMapper.selectByExample(tblBtsInstDOExample);

		List<InstBO> instBOList = new ArrayList<>();
		InstBO instBO1 = null;
		for (TblBtsInstDO instDO : instDOList) {
			instBO1 = new InstBO();
			instBO1.setInstCode(instDO.getInstCode());
			instBO1.setInstName(instDO.getInstName());
			if (InstTypeEnum.INST.getCode().equals(instDO.getInstType())) {
				instBO1.setInstType(InstTypeEnum.INST.getMessage());
			} else if (InstTypeEnum.CHANNEL.getCode().equals(instDO.getInstType())) {
				instBO1.setInstType(InstTypeEnum.CHANNEL.getMessage());
			}
			instBO1.setCreateTime(instDO.getCreateTime().substring(0, 8));
			instBO1.setInstEmail(instDO.getInstEmail());
			instBO1.setInstTelphome(instDO.getInstTelphome());
			instBOList.add(instBO1);
		}
		pagination.addResult(instBOList);

		return pagination;

	}

	public int addNewInst(InstBO instBO) {

		TblBtsInstDO tblBtsInstDO = new TblBtsInstDO();
		tblBtsInstDO.setInstCode(instBO.getInstId());
		tblBtsInstDO.setInstType(instBO.getInstType());
		tblBtsInstDO.setInstName(instBO.getInstName());
		tblBtsInstDO.setInstEmail(instBO.getInstEmail());
		tblBtsInstDO.setInstTelphome(instBO.getInstTelphome());
		tblBtsInstDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblBtsInstDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
		tblBtsInstDO.setCreateById(instBO.getUsrCreateBy());
		tblBtsInstDO.setUpdateById(instBO.getUsrCreateBy());
		int rt = tblBtsInstDOMapper.insert(tblBtsInstDO);

		return rt;
	}

	public Map<String, Object> addMerForInst(InstMerBO instMerBO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		TblBtsInstMerDO instMerDO = new TblBtsInstMerDO();
		instMerDO.setInstId(instMerBO.getInstId());
		instMerDO.setMerId(instMerBO.getInstMerId());
		instMerDO.setTermId(instMerBO.getInstMerTermId());
		instMerDO.setInstType(instMerBO.getInstType());
		instMerDO.setEnableFlag(instMerBO.getStatusFlag());
		instMerDO.setInstName(instMerBO.getInstName());

		try {
			instMerDOMapper.insert(instMerDO);
			resultMap.put("statusCode", 200);
			resultMap.put("message", "商户添加成功：");
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			resultMap.put("statusCode", 300);
			resultMap.put("message", "添加商户出错：" + ex.getCause());
		}

		return resultMap;
	}

	public int updateInstInfo(InstBO instBO) {

		TblBtsInstDO tblBtsInstDO = new TblBtsInstDO();
		tblBtsInstDO.setInstType(instBO.getInstType());
		tblBtsInstDO.setInstCode(instBO.getInstCode());
		tblBtsInstDO.setInstName(instBO.getInstName());
		tblBtsInstDO.setUpdateById(instBO.getUsrCreateBy());
		tblBtsInstDO.setInstEmail(instBO.getInstEmail());
		tblBtsInstDO.setInstTelphome(instBO.getInstTelphome());
		tblBtsInstDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());

		int updateByPrimaryKeySelective = tblBtsInstDOMapper.updateByPrimaryKeySelective(tblBtsInstDO);

		return updateByPrimaryKeySelective;
	}

	public Pagination<UserBO> getInstUsr(UserBO userBO) {
		List<UserBO> userList = new ArrayList<>();
		TblBTSSysUsrDOExample tblBTSSysUsrDOExample = new TblBTSSysUsrDOExample();
		TblBTSSysUsrDOExample.Criteria cri = tblBTSSysUsrDOExample.createCriteria();
		cri.andUsrDisableTagEqualTo("1");
		if (userBO.getUsrName().trim().equals("admin")) {
			String userStart = "B000000000";
			String userEnd = "B999999999";
		} else {
			String userStart = userBO.getUsrName() + "00000";
			String userEnd = userBO.getUsrName() + "99999";
			cri.andUsrNameBetween(userStart, userEnd);
		}
		int count = tblBTSSysUsrDOMapper.countByExample(tblBTSSysUsrDOExample);
		Pagination pagination = new Pagination(count, userBO.getPageCurrent(), userBO.getPageSize());
		PageHelper.startPage(userBO.getPageCurrent(), userBO.getPageSize());

		List<TblBTSSysUsrDO> usrDOList = tblBTSSysUsrDOMapper.selectByExample(tblBTSSysUsrDOExample);
		for (TblBTSSysUsrDO usrDO : usrDOList) {
			UserBO user = new UserBO();
			user.setUsrId(usrDO.getUsrId());
			user.setUsrName(usrDO.getUsrName());
			user.setUsrRemark(usrDO.getUsrRemark());
			user.setUsrType(usrDO.getUsrType());

			userList.add(user);
		}
		pagination.addResult(userList);
		return pagination;

	}

	public Map resetPass(UserBO userBO) {
		Map resultMap = new HashMap();

		CustomCredentialsMatcher customCredentialsMatcher = new CustomCredentialsMatcher();
		String userPass = customCredentialsMatcher.encrypt("Changeme");
		TblBTSSysUsrDO refSysUsrDO = new TblBTSSysUsrDO();
		refSysUsrDO.setUsrPwd(userPass);
		refSysUsrDO.setUsrUpdateDate(DateUtil.currentTimestamp());
		refSysUsrDO.setUsrUpdateBy(userBO.getUsrUpdateBy());

		TblBTSSysUsrDOExample tblBTSSysUsrDOExample = new TblBTSSysUsrDOExample();
		tblBTSSysUsrDOExample.createCriteria().andUsrIdEqualTo(userBO.getUsrId());

		int rt = tblBTSSysUsrDOMapper.updateByExampleSelective(refSysUsrDO, tblBTSSysUsrDOExample);
		if (rt != 0) {
			resultMap.put("statusCode", 200);
			resultMap.put("message", "操作成功!");
		} else {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "操作失败!");
		}
		return resultMap;

	}

	public Map disableUser(UserBO userBO) {
		Map resultMap = new HashMap();
		TblBTSSysUsrDOExample usrDOExample = new TblBTSSysUsrDOExample();
		usrDOExample.createCriteria().andUsrIdEqualTo(userBO.getUsrId());
		TblBTSSysUsrDO usrDO = new TblBTSSysUsrDO();
		usrDO.setUsrDisableTag("0");
		usrDO.setUsrUpdateDate(DateUtil.currentTimestamp());
		usrDO.setUsrUpdateBy(userBO.getUsrUpdateBy());

		tblBTSSysUsrDOMapper.updateByExampleSelective(usrDO, usrDOExample);
		resultMap.put("statusCode", 200);
		resultMap.put("message", "操作成功!");

		return resultMap;

	}

	public Pagination<InstMerKeyBO> getInstSecKeyList(InstMerKeyBO instMerKeyBO) {

		List<InstMerKeyBO> instMerKeyBOList = new ArrayList<>();

		TblBtsInstDOExample tblBtsInstDOExample = new TblBtsInstDOExample();
		TblBtsInstDOExample.Criteria instCri = tblBtsInstDOExample.createCriteria();

		if (instMerKeyBO.getInstType() != null) {
			instCri.andInstTypeEqualTo(instMerKeyBO.getInstType());
		}
		// if(instMerKeyBO.getInstId() != null){
		// instCri.andInstIdEqualTo(instMerKeyBO.getInstId());
		// }
		List<TblBtsInstDO> instDOList = tblBtsInstDOMapper.selectByExample(tblBtsInstDOExample);
		if (instDOList == null || instDOList.size() == 0) {
			return null;
		}
		List<String> instCodeList = new ArrayList<>();
		for (TblBtsInstDO instDO : instDOList) {
			String instCode = instDO.getInstCode();
			instCodeList.add(instCode);
		}

		TblBtsInstMerDOExample instMerDOExample = new TblBtsInstMerDOExample();
		TblBtsInstMerDOExample.Criteria cri = instMerDOExample.createCriteria();

		if (instCodeList != null && instCodeList.size() != 0) {
			cri.andInstIdIn(instCodeList);
		}

		instMerDOExample.setOrderByClause("inst_id asc");
		int count = 0;
		count = instMerDOMapper.countByExample(instMerDOExample);
		Pagination pagination = new Pagination(count, instMerKeyBO.getPageCurrent(), instMerKeyBO.getPageSize());
		PageHelper.startPage(instMerKeyBO.getPageCurrent(), instMerKeyBO.getPageSize());
		List<TblBtsInstMerDO> instMerDOList = instMerDOMapper.selectByExample(instMerDOExample);
		if (instMerDOList == null || instMerDOList.size() == 0) {
			return null;
		}
		for (TblBtsInstMerDO instMerDO : instMerDOList) {
			InstMerKeyBO instMerKeyBO1 = new InstMerKeyBO();
			instMerKeyBO1.setInstId(instMerDO.getInstId());
			// int rt = instDOList.indexOf(instMerDO.getInstId());
			TblBtsInstDO tblBtsInstDO = getInstById(instMerDO.getInstId());
			// instMerKeyBO1.setInstType(tblBtsInstDO.getInstType());
			if (tblBtsInstDO.getInstType().trim().equals(InstTypeEnum.INST.getCode())) {
				instMerKeyBO1.setInstType(InstTypeEnum.INST.getMessage());
			} else if (tblBtsInstDO.getInstType().trim().equals(InstTypeEnum.CHANNEL.getCode())) {
				instMerKeyBO1.setInstType(InstTypeEnum.CHANNEL.getMessage());
			}

			// instMerKeyBO1.setInstName(tblBtsInstDO.getInstName());
			instMerKeyBO1.setInstMerId(instMerDO.getMerId());
			instMerKeyBO1.setInstMerTermId(instMerDO.getTermId());
			instMerKeyBO1.setPrimaryKey("");
			instMerKeyBO1.setPinKey("");
			instMerKeyBO1.setMacKey("");
			instMerKeyBO1.setTdKey("");
			instMerKeyBO1.setCheckStatus("N");

			if (tblBtsInstDO.getInstType().trim().equals(InstTypeEnum.INST.getCode())) {
				TblInstMerKeyInfoDOExample instMerKeyInfoDOExample = new TblInstMerKeyInfoDOExample();
				TblInstMerKeyInfoDOExample.Criteria cri2 = instMerKeyInfoDOExample.createCriteria();
				cri2.andInstIdEqualTo(instMerDO.getInstId()).andInstMerIdEqualTo(formatMerId(instMerDO.getMerId()))
						.andInstMerTermIdEqualTo(instMerDO.getTermId());
				List<TblInstMerKeyInfoDO> instMerKeyInfoDOList = instMerKeyInfoDOMapper
						.selectByExample(instMerKeyInfoDOExample);
				if (instMerKeyInfoDOList != null && instMerKeyInfoDOList.size() != 0) {
					instMerKeyBO1.setPrimaryKey(instMerKeyInfoDOList.get(0).getPrimaryKey());
					instMerKeyBO1.setPinKey(instMerKeyInfoDOList.get(0).getPinKey());
					instMerKeyBO1.setMacKey(instMerKeyInfoDOList.get(0).getMacKey());
					instMerKeyBO1.setTdKey(instMerKeyInfoDOList.get(0).getTdKey());
					instMerKeyBO1.setCheckStatus(instMerKeyInfoDOList.get(0).getCheckStatus());
				}

			} else if (tblBtsInstDO.getInstType().trim().equals(InstTypeEnum.CHANNEL.getCode())) {
				TblChannelMerKeyInfoDOExample channelMerKeyInfoDOExample = new TblChannelMerKeyInfoDOExample();
				TblChannelMerKeyInfoDOExample.Criteria cri3 = channelMerKeyInfoDOExample.createCriteria();
				cri3.andChannelIdEqualTo(instMerDO.getInstId())
						.andChannelMerIdEqualTo(formatMerId(instMerDO.getMerId()))
						.andChannelMerTermIdEqualTo(instMerDO.getTermId());
				List<TblChannelMerKeyInfoDO> channelMerKeyInfoDOList = channelMerKeyInfoDOMapper
						.selectByExample(channelMerKeyInfoDOExample);
				if (channelMerKeyInfoDOList != null && channelMerKeyInfoDOList.size() != 0) {
					instMerKeyBO1.setPinKey(channelMerKeyInfoDOList.get(0).getPinKey());
					instMerKeyBO1.setPinKey(channelMerKeyInfoDOList.get(0).getPinKey());
					instMerKeyBO1.setMacKey(channelMerKeyInfoDOList.get(0).getMacKey());
					instMerKeyBO1.setTdKey(channelMerKeyInfoDOList.get(0).getTdKey());
					instMerKeyBO1.setCheckStatus(channelMerKeyInfoDOList.get(0).getCheckStatus());
				}
			}
			instMerKeyBOList.add(instMerKeyBO1);

		}
		pagination.addResult(instMerKeyBOList);
		return pagination;

	}

	@Transactional(rollbackFor = Exception.class)
	public Map addInstMerKeyList(List<List<Object>> uploadFileList, String instType) {
		Map resultMap = new HashMap();

		if (instType.equals(InstTypeEnum.INST.getCode())) {
			TblInstMerKeyInfoDOExample instMerKeyInfoDOExample = new TblInstMerKeyInfoDOExample();
			instMerKeyInfoDOExample.createCriteria().andInstIdEqualTo(uploadFileList.get(0).get(0).toString())
					.andInstMerIdEqualTo(formatMerId(uploadFileList.get(0).get(1).toString().split("\\.")[0]))
					.andInstMerTermIdEqualTo(uploadFileList.get(0).get(2).toString().split("\\.")[0]);
			List<TblInstMerKeyInfoDO> instMerKeyInfoDOList = instMerKeyInfoDOMapper
					.selectByExample(instMerKeyInfoDOExample);
			if (instMerKeyInfoDOList != null && instMerKeyInfoDOList.size() != 0) {
				resultMap.put("statusCode", 300);
				resultMap.put("message", "记录已经存在");
				return resultMap;
			}

			for (List<Object> linked : uploadFileList) {
				InstMerKeyBO instMerKeyBO = new InstMerKeyBO();
				TblInstMerKeyInfoDO tblInstMerKeyInfoDO = new TblInstMerKeyInfoDO();
				tblInstMerKeyInfoDO.setInstId(linked.get(0).toString().split("\\.")[0]);
				tblInstMerKeyInfoDO.setInstMerId(linked.get(1).toString().split("\\.")[0]);
				tblInstMerKeyInfoDO.setInstMerTermId(linked.get(2).toString().split("\\.")[0]);
				if (linked.size() >= 4) {
					tblInstMerKeyInfoDO.setPrimaryKey(linked.get(3).toString().split("\\.")[0]);
				}
				if (linked.size() >= 5) {
					tblInstMerKeyInfoDO.setPinKey(linked.get(4).toString().split("\\.")[0]);
				}
				if (linked.size() >= 6) {
					tblInstMerKeyInfoDO.setMacKey(linked.get(5).toString().split("\\.")[0]);
				}
				if (linked.size() >= 7) {
					tblInstMerKeyInfoDO.setTdKey(linked.get(6).toString().split("\\.")[0]);
				}
				if (linked.size() >= 8) {
					tblInstMerKeyInfoDO.setCheckStatus(linked.get(7).toString());
				}

				instMerKeyInfoDOMapper.insert(tblInstMerKeyInfoDO);
				// InstBO instBO = new InstBO();
				// instBO.setInstCode(tblInstMerKeyInfoDO.getInstId());
				// if(!isInstExit(instBO)){
				// instBO.setInstId(String.valueOf(seqMapper.getTblBTSInstIdSeq()));
				// instBO.setInstType(InstTypeEnum.INST.getCode());
				// instBO.setInstName();
				InstMerBO instMerBO = new InstMerBO();
				instMerBO.setInstId(tblInstMerKeyInfoDO.getInstId());
				instMerBO.setInstMerId(tblInstMerKeyInfoDO.getInstMerId());
				if (!isMerBindToInst(instMerBO)) {
					instMerBO.setInstMerTermId(tblInstMerKeyInfoDO.getInstMerTermId());
					instMerBO.setStatusFlag(CommonConstants.INST_MER_ENABLE);
					addMerForInst(instMerBO);
				}

				// }
			}
		}
		// else if(instType.equals(InstTypeEnum.CHANNEL.getCode()))
		// {
		// TblChannelMerKeyInfoDOExample channelMerKeyInfoDOExample = new
		// TblChannelMerKeyInfoDOExample();
		// channelMerKeyInfoDOExample.createCriteria().andChannelIdEqualTo(uploadFileList.get(0).get(0).toString()).andChannelMerIdEqualTo(formatMerId(uploadFileList.get(0).get(1).toString())).andChannelMerTermIdEqualTo(uploadFileList.get(0).get(2).toString());
		// List<TblChannelMerKeyInfoDO> channelMerKeyInfoDOList =
		// channelMerKeyInfoDOMapper.selectByExample(channelMerKeyInfoDOExample);
		// if (channelMerKeyInfoDOList != null && channelMerKeyInfoDOList.size()
		// != 0) {
		// resultMap.put("statusCode", 300);
		// resultMap.put("message", "记录已经存在");
		// return resultMap;
		// }
		//
		// for (List<Object> linked : uploadFileList) {
		//// InstMerKeyBO channelMerKeyBO = new InstMerKeyBO();
		// TblChannelMerKeyInfoDO tblChannelMerKeyInfoDO = new
		// TblChannelMerKeyInfoDO();
		// tblChannelMerKeyInfoDO.setChannelId(linked.get(0).toString().split("\\.")[0]);
		// tblChannelMerKeyInfoDO.setChannelMerId(linked.get(1).toString().split("\\.")[0]);
		// tblChannelMerKeyInfoDO.setChannelMerTermId(linked.get(2).toString().split("\\.")[0]);
		// tblChannelMerKeyInfoDO.setPinKey(linked.get(3).toString().split("\\.")[0]);
		// tblChannelMerKeyInfoDO.setMacKey(linked.get(4).toString().split("\\.")[0]);
		// tblChannelMerKeyInfoDO.setTdKey(linked.get(5).toString().split("\\.")[0]);
		// tblChannelMerKeyInfoDO.setCheckStatus(linked.get(6).toString());
		// channelMerKeyInfoDOMapper.insert(tblChannelMerKeyInfoDO);
		// }
		// }
		resultMap.put("statusCode", 200);
		resultMap.put("message", "导入成功!");
		return resultMap;

		// instMerKeyBO.setInstId(linked.get(0).toString());
		// instMerKeyBO.setInstMerId(linked.get(1).toString());
		// instMerKeyBO.setInstMerTermId(linked.get(2).toString());
		// instMerKeyBO.setPinKey(linked.get(3).toString());
		// instMerKeyBO.setMacKey(linked.get(4).toString());
		// instMerKeyBO.setTdKey(linked.get(5).toString());
		// instMerKeyBO.setInstType(instType);

		// Boolean result = addInstMerKey(instMerKeyBO);
		// if(!result){
		// resultMap.put("statusCode", 300);
		// resultMap.put("message", "添加失败!");
		// return resultMap;
		// }

	}

	// private Boolean addInstMerKey(InstMerKeyBO instMerKeyBO){
	//
	// }

	private String formatMerId(String str) {
		String padSpace = StringUtils.repeat(" ", 15 - str.length());
		str = str + padSpace;
		return str;
	}

	public Pagination<InstMerBO> getMerByInst(InstMerBO instMerBO) {
		List<InstMerBO> instMerBOList = new ArrayList<>();

		TblBtsInstMerDOExample instMerDOExample = new TblBtsInstMerDOExample();
		TblBtsInstMerDOExample.Criteria cri = instMerDOExample.createCriteria().andInstIdEqualTo(instMerBO.getInstId());
		cri.andEnableFlagEqualTo(CommonConstants.INST_MER_ENABLE);
		if (instMerBO.getInstMerId() != null && !instMerBO.getInstMerId().trim().equals("")) {
			String padSpace = StringUtils.repeat(" ", 15 - instMerBO.getInstMerId().length());
			cri.andMerIdEqualTo(instMerBO.getInstMerId() + padSpace);
		}
		if (instMerBO.getInstMerTermId() != null && !instMerBO.getInstMerTermId().trim().equals("")) {
			cri.andTermIdEqualTo(instMerBO.getInstMerTermId());
		}

		// instMerDOExample.setOrderByClause("status_flag desc");
		int count = instMerDOMapper.countByExample(instMerDOExample);

		Pagination pagination = new Pagination(count, instMerBO.getPageCurrent(), instMerBO.getPageSize());
		PageHelper.startPage(instMerBO.getPageCurrent(), instMerBO.getPageSize());
		List<TblBtsInstMerDO> instMerDOList = instMerDOMapper.selectByExample(instMerDOExample);

		for (TblBtsInstMerDO instMerDO : instMerDOList) {
			InstMerBO instMerBO1 = new InstMerBO();
			instMerBO1.setInstId(instMerDO.getInstId());
			instMerBO1.setInstMerId(instMerDO.getMerId());
			instMerBO1.setInstMerTermId(instMerDO.getTermId());
			if (instMerDO.getInstId().trim().equals(CommonConstants.INST_MER_ENABLE)) {
				instMerBO1.setStatusFlag("启用");
			} else if (instMerDO.getInstId().trim().equals(CommonConstants.INST_MER_DISABLE)) {
				instMerBO1.setStatusFlag("禁用");
			}

			instMerBOList.add(instMerBO1);

		}
		pagination.addResult(instMerBOList);

		return pagination;
	}

	@Override
	public Pagination<InstChannelMapBO> getInstChannelMapList(InstChannelMapBO instChannelMapBO) {
		List<InstChannelMapBO> instChannelMapBOList = new ArrayList<>();
		TblInstChannelMapDOExample instChannelMapDOExample = new TblInstChannelMapDOExample();
		TblInstChannelMapDOExample.Criteria cri = instChannelMapDOExample.createCriteria();

		if (instChannelMapBO.getInstId() != null) {
			cri.andInstIdEqualTo(instChannelMapBO.getInstId());
		}
		if (instChannelMapBO.getChannelId() != null) {
			cri.andChannelIdEqualTo(instChannelMapBO.getChannelId());
		}
		instChannelMapDOExample.setOrderByClause("status_flag desc");
		int count;
		count = instChannelMapDOMapper.countByExample(instChannelMapDOExample);
		Pagination pagination = new Pagination(count, instChannelMapBO.getPageCurrent(),
				instChannelMapBO.getPageSize());
		PageHelper.startPage(instChannelMapBO.getPageCurrent(), instChannelMapBO.getPageSize());
		List<TblInstChannelMapDO> instChannelMapDOList = instChannelMapDOMapper
				.selectByExample(instChannelMapDOExample);

		for (TblInstChannelMapDO instChannelMapDO : instChannelMapDOList) {
			InstChannelMapBO instChannelMapBO1 = new InstChannelMapBO();
			instChannelMapBO1.setInstId(instChannelMapDO.getInstId());
			instChannelMapBO1.setInstMerId(instChannelMapDO.getInstMerId());
			instChannelMapBO1.setInstMerTermId(instChannelMapDO.getInstMerTermId());
			instChannelMapBO1.setChannelId(instChannelMapDO.getChannelId());
			instChannelMapBO1.setChannelMerId(instChannelMapDO.getChannelMerId());
			instChannelMapBO1.setChannelMerTermId(instChannelMapDO.getChannelMerTermId());
			if (instChannelMapDO.getStatusFlag().equals(CommonConstants.INST_MER_ENABLE)) {
				instChannelMapBO1.setStatusFlag("禁用");
			} else if (instChannelMapDO.getStatusFlag().equals(CommonConstants.INST_MER_DISABLE)) {
				instChannelMapBO1.setStatusFlag("启用");
			}

			instChannelMapBOList.add(instChannelMapBO1);
		}

		pagination.addResult(instChannelMapBOList);
		return pagination;
	}

	@Override
	public Map addInstChannelMap(InstChannelMapBO instChannelMapBO) {
		Map resultMap = new HashMap();

		String instId = instChannelMapBO.getInstId();
		String padSpace = StringUtils.repeat(" ", 15 - instChannelMapBO.getInstMerId().length());
		String instMerId = instChannelMapBO.getInstMerId() + padSpace;
		String instTermId = instChannelMapBO.getInstMerTermId();

		String channelId = instChannelMapBO.getChannelId();
		String padSpace2 = StringUtils.repeat(" ", 15 - instChannelMapBO.getChannelMerId().length());
		String channeMerId = instChannelMapBO.getChannelMerId() + padSpace2;
		String channeTermId = instChannelMapBO.getChannelMerTermId();

		TblBtsInstMerDOExample tblInstMerDOExample = new TblBtsInstMerDOExample();
		tblInstMerDOExample.createCriteria().andInstIdEqualTo(instId).andMerIdEqualTo(instMerId)
				.andTermIdEqualTo(instTermId).andInstTypeEqualTo(InstTypeEnum.INST.getCode());
		List<TblBtsInstMerDO> instMerDOList = instMerDOMapper.selectByExample(tblInstMerDOExample);
		if (instMerDOList == null || instMerDOList.size() == 0) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "收单机构或商户不在!");
			return resultMap;
		}

		TblBtsInstMerDOExample tblInstMerDOExample1 = new TblBtsInstMerDOExample();
		tblInstMerDOExample1.createCriteria().andInstIdEqualTo(channelId).andMerIdEqualTo(channeMerId)
				.andTermIdEqualTo(channeTermId).andInstTypeEqualTo(InstTypeEnum.CHANNEL.getCode());
		List<TblBtsInstMerDO> instMerDOList1 = instMerDOMapper.selectByExample(tblInstMerDOExample1);
		if (instMerDOList1 == null || instMerDOList1.size() == 0) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "收单机构或商户不在!");
			return resultMap;
		}

		TblInstChannelMapDO instChannelMapDO = new TblInstChannelMapDO();
		instChannelMapDO.setInstId(instChannelMapBO.getInstId());
		instChannelMapDO.setInstMerId(instChannelMapBO.getInstMerId());
		instChannelMapDO.setInstMerTermId(instChannelMapBO.getInstMerTermId());
		instChannelMapDO.setChannelId(instChannelMapBO.getChannelId());
		instChannelMapDO.setChannelMerId(instChannelMapBO.getChannelMerId());
		instChannelMapDO.setChannelMerTermId(instChannelMapBO.getChannelMerTermId());
		instChannelMapDO.setStatusFlag("Y");

		try {
			instChannelMapDOMapper.insert(instChannelMapDO);
			resultMap.put("statusCode", 200);
			resultMap.put("message", "添加成功!");
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			resultMap.put("statusCode", 300);
			resultMap.put("message", "添加匹配关系失败!");
		}
		return resultMap;
	}

	@Override
	public Map toggleMapStatus(InstChannelMapBO instChannelMapBO) {
		Map resultMap = new HashMap();

		String instId = instChannelMapBO.getInstId();
		String padSpace = StringUtils.repeat(" ", 15 - instChannelMapBO.getInstMerId().length());
		String instMerId = instChannelMapBO.getInstMerId() + padSpace;
		String instTermId = instChannelMapBO.getInstMerTermId();

		TblInstChannelMapDOExample tblInstChannelMapDOExample = new TblInstChannelMapDOExample();
		tblInstChannelMapDOExample.createCriteria().andInstIdEqualTo(instId).andInstMerIdEqualTo(instMerId)
				.andInstMerTermIdEqualTo(instTermId);

		List<TblInstChannelMapDO> instChannelMapDOList = instChannelMapDOMapper
				.selectByExample(tblInstChannelMapDOExample);

		if (instChannelMapDOList == null && instChannelMapDOList.size() == 0) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "Record not found!");
			return resultMap;
		}

		TblInstChannelMapDO instChannelMapDO = new TblInstChannelMapDO();
		if (instChannelMapDOList.get(0).getStatusFlag().equals(CommonConstants.INST_MER_ENABLE)) {
			instChannelMapDO.setStatusFlag(CommonConstants.INST_MER_DISABLE);
		} else if (instChannelMapDOList.get(0).getStatusFlag().equals(CommonConstants.INST_MER_DISABLE)) {
			instChannelMapDO.setStatusFlag(CommonConstants.INST_MER_ENABLE);
		}
		try {
			instChannelMapDOMapper.updateByExampleSelective(instChannelMapDO, tblInstChannelMapDOExample);
			resultMap.put("statusCode", 200);
			resultMap.put("message", "状态变更成功!");
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			resultMap.put("statusCode", 300);
			resultMap.put("message", "状态变更失败!");
		}

		return resultMap;

	}

	@Override
	public boolean isInstExit(InstBO instBO) {
		TblBtsInstDOExample instDOExample = new TblBtsInstDOExample();
		instDOExample.createCriteria().andInstCodeEqualTo(instBO.getInstCode());
		List<TblBtsInstDO> instDOList = tblBtsInstDOMapper.selectByExample(instDOExample);
		if (instDOList == null || instDOList.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map addInstChannelMapList(List<List<Object>> uploadFileList) {
		Map resultMap = new HashMap();

		TblInstChannelMapDOExample instChannelMapDOExample = new TblInstChannelMapDOExample();
		instChannelMapDOExample.createCriteria().andInstIdEqualTo(uploadFileList.get(0).get(0).toString())
				.andInstMerTermIdEqualTo(uploadFileList.get(0).get(2).toString())
				.andInstMerIdEqualTo(uploadFileList.get(0).get(1).toString());
		List<TblInstChannelMapDO> instChannelMapDOList = instChannelMapDOMapper
				.selectByExample(instChannelMapDOExample);
		if (instChannelMapDOList != null && instChannelMapDOList.size() != 0) {
			resultMap.put("statusCode", 200);
			resultMap.put("message", "记录已经存在!");
			return resultMap;
		}

		for (List<Object> linked : uploadFileList) {
			TblInstChannelMapDO instChannelMapDO = new TblInstChannelMapDO();
			instChannelMapDO.setInstId(linked.get(0).toString().split("\\.")[0]);
			instChannelMapDO.setInstMerId(linked.get(1).toString().split("\\.")[0]);
			instChannelMapDO.setInstMerTermId(linked.get(2).toString().split("\\.")[0]);
			instChannelMapDO.setChannelId(linked.get(3).toString().split("\\.")[0]);
			instChannelMapDO.setChannelMerId(linked.get(4).toString().split("\\.")[0]);
			instChannelMapDO.setChannelMerTermId(linked.get(5).toString().split("\\.")[0]);
			instChannelMapDO.setStatusFlag(linked.get(6).toString());
			instChannelMapDOMapper.insert(instChannelMapDO);
		}

		resultMap.put("statusCode", 200);
		resultMap.put("message", "导入成功!");
		return resultMap;
	}

	@Override
	public Map deleteInstById(String id) {
		Map resultMap = new HashMap();

		try {
			tblBtsInstDOMapper.deleteByPrimaryKey(id);
			resultMap.put("statusCode", 200);
			resultMap.put("message", "删除成功!");
		} catch (Exception ex) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "删除失败!");
		}

		return resultMap;
	}

	@Override
	public Map deleteMerForInst(String instCode, String merId) {
		Map resultMap = new HashMap();

		TblBtsInstMerDOExample instMerDOExample = new TblBtsInstMerDOExample();
		instMerDOExample.createCriteria().andInstIdEqualTo(instCode).andMerIdEqualTo(formatMerId(merId));

		try {
			instMerDOMapper.deleteByExample(instMerDOExample);

			resultMap.put("statusCode", 200);
			resultMap.put("message", "删除成功!");
		} catch (Exception ex) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "删除失败!");
		}

		return resultMap;
	}

	@Override
	public Map deleteInstChannelMap(String instCode, String merId, String termId) {
		Map resultMap = new HashMap();

		TblInstChannelMapDOExample instChannelMapDOExample = new TblInstChannelMapDOExample();
		instChannelMapDOExample.createCriteria().andInstIdEqualTo(instCode).andInstMerIdEqualTo(formatMerId(merId))
				.andInstMerTermIdEqualTo(termId);

		try {

			instChannelMapDOMapper.deleteByExample(instChannelMapDOExample);
			resultMap.put("statusCode", 200);
			resultMap.put("message", "删除成功!");
		} catch (Exception ex) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "删除失败!");
		}

		return resultMap;
	}

	private Boolean isMerBindToInst(InstMerBO instMerBO) {
		TblBtsInstMerDOExample instMerDOExample = new TblBtsInstMerDOExample();
		instMerDOExample.createCriteria().andInstIdEqualTo(instMerBO.getInstId())
				.andInstIdEqualTo(formatMerId(instMerBO.getInstMerId()));
		List<TblBtsInstMerDO> instMerDOList = instMerDOMapper.selectByExample(instMerDOExample);
		if (instMerDOList == null || instMerDOList.size() == 0) {
			return false;
		}
		return true;
	}

	public Map openNewInst(InstBO instBo, String userNum) {
		Map resultMap = new HashMap();
		int addresult = 0;

		String instLoginUser="";
		instLoginUser = StringUtils.leftPad(userNum, 8, '0');		
		
		try {
			String instId = instBo.getInstId();
			addNewInst(instBo);
			// 添加机构主秘钥
			TblInstKeyInfo tblInstKeyInfo = new TblInstKeyInfo();
			tblInstKeyInfo.setInstCode(instId);
			tblInstKeyInfo.setInstMainKey(RandomString.getRandomString(32));
			tblInstKeyInfo.setInstPinKey("");
			tblInstKeyInfo.setInstMacKey("");
			tblInstKeyInfo.setInstTdKey("");
			tblInstKeyInfo.setLoginStat("N");
			tblInstKeyInfoMapper.insert(tblInstKeyInfo);

			// 添加机构交易权限默认开通POS和微信
			TblInstTransAuth tblInstTransAuth = new TblInstTransAuth();
			tblInstTransAuth.setInstCode(instId);
			tblInstTransAuth.setPosStat("Y");
			tblInstTransAuth.setChatStat("Y");
			tblInstTransAuth.setAllipayStat("N");
			tblInstTransAuth.setAuthStat("");
			tblInstTransAuth.setRemark("");
			tblInstTransAuthMapper.insert(tblInstTransAuth);

			// 添加机构登录账户
			UserBO userBO = new UserBO();
			userBO.setUsrId(userNum);			
			String userName = "INST_" + instId;				 
			userBO.setUsrName(userName);

			CustomCredentialsMatcher customCredentialsMatcher = new CustomCredentialsMatcher();
			String userPass = customCredentialsMatcher.encrypt("111111");
			userBO.setUsrPwd(userPass);
			userBO.setUsrDisableTag(Constant.USER_DISABLED);
			userBO.setUsrType(Constant.INST_USER);
			Subject currentUser = SecurityUtils.getSubject();
			MonitorRealm.ShiroUser shiroUser = (MonitorRealm.ShiroUser) currentUser.getPrincipal();
			userBO.setUsrCreateBy(shiroUser.getLoginName());
			userBO.setUsrUpdateBy(shiroUser.getLoginName());
			Map addMap = userService.addNewUsr(userBO);

			if ("操作成功!".equals(addMap.get("message")))
				addresult = 1;
			else
				addresult = 0;

            //添加角色关联
            TblAiLoginUsrDo tblAiLoginUsrDo = new TblAiLoginUsrDo();
            tblAiLoginUsrDo.setInstId(instId);
            tblAiLoginUsrDo.setLoginUsr(instLoginUser);
            tblAiLoginUsrDo.setLoginUsrType("2");
            int insertResult = 0;
            insertResult = tblAiLoginUsrDoMapper.insert(tblAiLoginUsrDo);

            if (insertResult > 0){
            	addresult = 1;
            }
            
            //添加机构商户权限
            userService.addAcctAuthority("2", userBO.getUsrId());			
				
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new BposException("新增机构异常!");
		}

        if (addresult == 1) {
            resultMap.put("statusCode", 200);
            resultMap.put("message", "新增机构商户成功!");
        } else {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "新增机构商户失败!");
        }
        return resultMap;		
        
	}
	
    @Override
    public List<TblMerInfoVO> queryInstMerList(Map map) {

        List<TblMerInfoVO> tblMerInfoVOList = merInfoCustVOMapper.queryMerInfo(map);
        
		Boolean isRegMinSheng = false; 
		Boolean isRegMinShengZf = false; 
		for (TblMerInfoVO merInfoList: tblMerInfoVOList){
			
			isRegMinSheng = IsRegisted(merInfoList.getMerId());
			if (isRegMinSheng) {
				merInfoList.setRegistedMinSheng("1");
			} else {
				merInfoList.setRegistedMinSheng("0");
			}
			
			isRegMinShengZf = IsRegistedZf(merInfoList.getMerId());
			if (isRegMinShengZf) {
				merInfoList.setRegistedMinShengZf("1");
			} else {
				merInfoList.setRegistedMinShengZf("0");
			}
			
		}	            
		
        return tblMerInfoVOList;
    }	

	//是否注册微信
	public Boolean IsRegisted(String merId){
		
		Boolean isR = false;
		String memberId = merId; 
		Map resultMap = new HashMap();
		
		Boolean webXinisRegisted = wechatRegisterService.isRegisted(memberId, WechatRegisterServiceImpl.weiXinChannel);
		
		if (webXinisRegisted) {
           isR = true;
		} else {
		   isR = false;
		}
		return isR;
	}

	//是否注册支付宝
	public Boolean IsRegistedZf(String merId){
		
		Boolean isR = false;
		String memberId = merId; 
		Map resultMap = new HashMap();
		
		Boolean zhiFuBaoRegisted = wechatRegisterService.isRegisted(memberId, WechatRegisterServiceImpl.zhiFuBaoChannel);
			
		if (zhiFuBaoRegisted) {
           isR = true;
		} else {
		   isR = false;
		}
		return isR;
	}
	
	// 导出机构商户信息
	@Override
	public void exportInstMerList(List<TblMerInfoVO> tblMerInfoVOList, ServletOutputStream outputStream)
			throws Exception {

		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("机构商户信息表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setWrapText(false);
		String[] titles = { "商户号", "商户名", "商户状态", "申请日期", "终端号",
				"法人代表", "法人代表证件类型","法人代表证件号码","注册地址","联系人","联系电话",
				"开户行", "开户行省", "开户行市", "开户支行","联行号","MCC码", "结算周期", 
				"借记卡T1手续费", "借记卡T1封顶", "贷记卡T1手续费", "T0提现手续费", "T0垫资手续费", "微信T0交易手续费", "支付宝T0交易手续费",
				"微信T1交易手续费", "支付宝T1交易手续费", "加急结算费率", "固定加急手续费", "封顶金额", "所属机构", "上报机构", "机构终端号"};
				
                /*
				"商户号", "商户名称", "商户类型", "注册号", "注册简称", "注册地址", "注册资本", "营业执照编号", "营业执照有效期", "税务登记证", "法人代表", "法人代表证件类型",
				"法人代表证件号码", "法人代表证件有效期", "联系人", "联系号码", "联系邮箱",

				"开户行", "开户行省", "开户行市", "开户支行", "联行号", "账户类型", "账户名", "账户号",

				"MCC描述", "借记卡T1手续费", "借记卡T1封顶", "贷记卡T1手续费", "T0提现手续费", "T0垫资手续费", "微信T0交易手续费", "支付宝T0交易手续费",
				"微信T1交易手续费", "支付宝T1交易手续费", "报备状态", "MCC码" };
				*/

		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row.createCell((short) i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style);
		}

		if (tblMerInfoVOList == null || tblMerInfoVOList.size() == 0) {

		}

		int i = 0;
		Map<String, String> feemap = new HashMap<>();
		for (TblMerInfoVO instMerVO : tblMerInfoVOList) {
            
			TblMerBankInfoDO tblMerBankInfoDO = queryMerBankById(instMerVO.getMerId());

			if (tblMerBankInfoDO == null) {
				tblMerBankInfoDO = new TblMerBankInfoDO();
			}
          
			List<TblMerFeeInfoDO> tblMerFeeInfoDOS = queryMerFeeById(instMerVO.getMerId());

			if (tblMerFeeInfoDOS != null) {
				String feeType = "";
				int num = 0;
				for (TblMerFeeInfoDO tblMerFeeInfoDO : tblMerFeeInfoDOS) {
				    feeType = tblMerFeeInfoDO.getFeeType();
					feeType = feeType.replace("0", "");
					num = Integer.parseInt(feeType);
					String[] calc = CalcModeUtil.splitCalcMode(tblMerFeeInfoDO.getCalcMode(), false).split(",");
					switch (num) {
					case 1:
						feemap.put("借记卡T1手续费", calc[0]);
						feemap.put("借记卡T1-封顶", calc[1]);
						break;
					case 2:
						feemap.put("贷记卡T1手续费", calc[0]);
						break;
					case 3:
						feemap.put("微信T0交易手续费", calc[0]);
						break;
					case 4:
						feemap.put("支付宝T0交易手续费", calc[0]);
						break;
					case 5:
						feemap.put("微信T1交易手续费", calc[0]);
						break;
					case 6:
						feemap.put("支付宝T1交易手续费", calc[0]);
						break;
					case 7:
						feemap.put("T0提现手续费", calc[0]);
						break;
					case 8:
						feemap.put("T0垫资手续费", calc[0]);
						break;
					default:
						break;
					}
				}
			}

			TblMerRelevanceMccDo tblMerRelevanceMccDo = queryTblMerRelevanceMccDoById(instMerVO.getMerId());

			if (tblMerRelevanceMccDo == null) {

				tblMerRelevanceMccDo = new TblMerRelevanceMccDo();
			}

			// 可以用来补充Mer/bank/fee info

			row = sheet.createRow((int) i + 1);

			// 商户号
			HSSFCell cell0 = row.createCell((short) 0);
			cell0.setCellStyle(style);
			cell0.setCellValue(instMerVO.getMerId());

			// 商户名
			HSSFCell cell1 = row.createCell((short) 1);
			cell1.setCellStyle(style);
			cell1.setCellValue(instMerVO.getMerName());
			// 商户状态
			HSSFCell cell2 = row.createCell((short) 2);
			cell2.setCellStyle(style);
			String merStat ="";
			if(instMerVO.getMerStat().equals("0")) {
				merStat = "审核成功";
			} else if(instMerVO.getMerStat().equals("1")) {
				merStat = "未审核";
			} else if(instMerVO.getMerStat().equals("2")) {
				merStat = "审核失败";
			} else if(instMerVO.getMerStat().equals("3")) {
				merStat = "未提交";
			} else if(instMerVO.getMerStat().equals("4")) {
				merStat = "审核中";
			}	
			cell2.setCellValue(merStat);
			// 申请日期
			HSSFCell cell3 = row.createCell((short) 3);
			cell3.setCellStyle(style);
			cell3.setCellValue(instMerVO.getCreateTime());
			// 终端号
			HSSFCell cell4 = row.createCell((short) 4);
			cell4.setCellStyle(style);
			cell4.setCellValue(instMerVO.getTermId());
			// 法人代表
			HSSFCell cell5 = row.createCell((short) 5);
			cell5.setCellStyle(style);
			cell5.setCellValue(instMerVO.getLegalPerson());
			// 法人代表证件类型
			HSSFCell cell6 = row.createCell((short) 6);
			cell6.setCellStyle(style);
            String legalPersonCertType = "";
			if (instMerVO.getLegalPersonCertType().equals("0")){
				legalPersonCertType = "身份证";
			} 
			if (instMerVO.getLegalPersonCertType().equals("1")){
				legalPersonCertType = "护照";
			}			
			cell6.setCellValue(legalPersonCertType);
			// 法人代表证件号码
			HSSFCell cell7 = row.createCell((short) 7);
			cell7.setCellStyle(style);
			cell7.setCellValue(instMerVO.getLegalPersonCertNm());			
			// 注册地址
			HSSFCell cell8 = row.createCell((short) 8);
			cell8.setCellStyle(style);
			cell8.setCellValue(instMerVO.getMerAddress());			
			// 联系人
			HSSFCell cell9 = row.createCell((short) 9);
			cell9.setCellStyle(style);
			cell9.setCellValue(instMerVO.getContactPerson());
			// 联系号码			
			HSSFCell cell10 = row.createCell((short) 10);
			cell10.setCellStyle(style);
			cell10.setCellValue(instMerVO.getContactMobile());			
			// 开户行
			HSSFCell cell11 = row.createCell((short) 11);
			cell11.setCellStyle(style);
			cell11.setCellValue(tblMerBankInfoDO.getBankName());
			// 开户行省
			HSSFCell cell12 = row.createCell((short) 12);
			cell12.setCellStyle(style);
			cell12.setCellValue(tblMerBankInfoDO.getProvName());
			// 开户行市
			HSSFCell cell13 = row.createCell((short) 13);
			cell13.setCellStyle(style);
			cell13.setCellValue(tblMerBankInfoDO.getCityName());
			// 开户支行
			HSSFCell cell14 = row.createCell((short) 14);
			cell14.setCellStyle(style);
			cell14.setCellValue(tblMerBankInfoDO.getBankBranchName());
			// 联行号
			HSSFCell cell15 = row.createCell((short) 15);
			cell15.setCellStyle(style);
			cell15.setCellValue(tblMerBankInfoDO.getCnaps());
			// MCC 
            HSSFCell cell16 = row.createCell((short) 16);
			cell16.setCellStyle(style);
			cell16.setCellValue(tblMerRelevanceMccDo.getMccValue());			
			// 结算周期
            HSSFCell cell17 = row.createCell((short) 17);
			cell17.setCellStyle(style);
			cell17.setCellValue("");			
			// 借记卡T1手续费 
			HSSFCell cell18 = row.createCell((short) 18);
			cell18.setCellStyle(style);
			cell18.setCellValue(feemap.get("借记卡T1手续费"));
			
			// 借记卡T1封顶 
			HSSFCell cell19 = row.createCell((short) 19);
			cell19.setCellStyle(style);
			cell19.setCellValue(feemap.get("借记卡T1-封顶"));;

			// 贷记卡T1手续费
			HSSFCell cell20 = row.createCell((short) 20);
			cell20.setCellStyle(style);
			cell20.setCellValue(feemap.get("贷记卡T1手续费"));
			// T0提现手续费
			HSSFCell cell21 = row.createCell((short) 21);
			cell21.setCellStyle(style);
			cell21.setCellValue(feemap.get("T0提现手续费"));
			// T0垫资手续费 
			HSSFCell cell22 = row.createCell((short) 22);
			cell22.setCellStyle(style);
			cell22.setCellValue(feemap.get("T0垫资手续费"));
			// 微信T0交易手续费 
			HSSFCell cell23 = row.createCell((short) 23);
			cell23.setCellStyle(style);
			cell23.setCellValue(feemap.get("微信T0交易手续费"));
			// 支付宝T0交易手续费
			HSSFCell cell24 = row.createCell((short) 24);
			cell24.setCellStyle(style);
			cell24.setCellValue(feemap.get("支付宝T0交易手续费"));
			// 微信T1交易手续费 
			HSSFCell cell25 = row.createCell((short) 25);
			cell25.setCellStyle(style);
			cell25.setCellValue(feemap.get("微信T1交易手续费"));
			// 支付宝T1交易手续费
            HSSFCell cell26 = row.createCell((short) 26);
			cell26.setCellStyle(style);
			cell26.setCellValue(feemap.get("支付宝T1交易手续费"));
			feemap.clear();
			
			// 加急结算费率
            HSSFCell cell27 = row.createCell((short) 27);
			cell27.setCellStyle(style);
			cell27.setCellValue("");
			// 固定加急手续费
            HSSFCell cell28 = row.createCell((short) 28);
			cell28.setCellStyle(style);
			cell28.setCellValue("");
			// 封顶金额
            HSSFCell cell29 = row.createCell((short) 29);
			cell29.setCellStyle(style);
			cell29.setCellValue("");			
			
			// 所属机构
            HSSFCell cell30 = row.createCell((short) 30);
			cell30.setCellStyle(style);
			cell30.setCellValue(instMerVO.getInstName());
			
			// 上报机构
            HSSFCell cell31 = row.createCell((short) 31);
			cell31.setCellStyle(style);
			cell31.setCellValue(instMerVO.getPosMerId());	
			
			// 机构终端号
            HSSFCell cell32 = row.createCell((short) 32);
            cell32.setCellStyle(style);
            cell32.setCellValue(instMerVO.getInstTermId());	
			
			i++;
		}
		wb.write(outputStream);
		outputStream.close();
	}
	
	@Override
	public List<TblMerFeeInfoDO> queryMerFeeById(String merId) {
		TblMerFeeInfoDOExample tblMerFeeInfoDOExample = new TblMerFeeInfoDOExample();
		tblMerFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId);
		List<TblMerFeeInfoDO> tblMerFeeInfoDOS = tblMerFeeInfoDOMapper.selectByExample(tblMerFeeInfoDOExample);
		if (tblMerFeeInfoDOS.size() == 0) {
			return new ArrayList<TblMerFeeInfoDO>();
		}
		return tblMerFeeInfoDOS;
	}	
	
	@Override
	public TblMerBankInfoDO queryMerBankById(String merId) {
		TblMerBankInfoDOExample tblMerBankInfoDOExample = new TblMerBankInfoDOExample();
		tblMerBankInfoDOExample.createCriteria().andMerIdEqualTo(merId);
		List<TblMerBankInfoDO> tblMerBankInfoDOS = tblMerBankInfoDOMapper.selectByExample(tblMerBankInfoDOExample);
		if (tblMerBankInfoDOS.size() == 0) {
			return new TblMerBankInfoDO();
		}
		return tblMerBankInfoDOS.get(0);
	}
	
	@Override
	public TblMerRelevanceMccDo queryTblMerRelevanceMccDoById(String merId) {
		TblMerRelevanceMccDoExample tblMerRelevanceMccDoExample = new TblMerRelevanceMccDoExample();
		tblMerRelevanceMccDoExample.createCriteria().andMerIdEqualTo(merId);
		List<TblMerRelevanceMccDo> tblMerRelevanceMccDos = tblMerRelevanceMccDoMapper
				.selectByExample(tblMerRelevanceMccDoExample);
		if (tblMerRelevanceMccDos != null && tblMerRelevanceMccDos.size() != 0) {
			return tblMerRelevanceMccDos.get(0);
		}
		return null;
	}	
}
