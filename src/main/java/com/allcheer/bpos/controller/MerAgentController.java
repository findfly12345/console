package com.allcheer.bpos.controller;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.entity.vo.TblAgentMerInfoVO;
import com.allcheer.bpos.form.AddBatchAgentMerFileForm;
import com.allcheer.bpos.form.AgentMerFeeForm;
import com.allcheer.bpos.form.MerAddressForm;
import com.allcheer.bpos.form.MerInfoForm;
import com.allcheer.bpos.form.SubBranchInfoForm;
import com.allcheer.bpos.mapper.SeqMapper;
import com.allcheer.bpos.service.AddBatchAgentMerService;
import com.allcheer.bpos.service.MerAddressService;
import com.allcheer.bpos.service.MerAgentService;
import com.allcheer.bpos.service.OpenLoginUserService;
import com.allcheer.bpos.service.QrcodeService;
import com.allcheer.bpos.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiuBin on 2017/1/12.
 */
@Transactional
@Controller
@RequestMapping(value = "/mer")
public class MerAgentController {

	private final static Logger logger = LoggerFactory.getLogger(MerAgentController.class);

	@Autowired
	private SeqMapper seqMapper;

	@Autowired
	private MerAgentService merAgentService;

	@Autowired
	private AddBatchAgentMerService addBatchAgentMerService;
	@Autowired
	private OpenLoginUserService openLoginUserService;

	@Autowired
	private QrcodeService qrcodeService;

	@Autowired
	private MerAddressService merAddressService;

	@RequestMapping(value = "/add_agent_mer", method = RequestMethod.GET)
	public ModelAndView goToAddAgentMerPage(ModelAndView modelAndView) {
		String platFormMerId = merAgentService.getPlatFormMerId();
		modelAndView.getModel().put("merId", platFormMerId);
		modelAndView.setViewName("/mer/add_agent_mer");
		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/add_agent_mer", method = RequestMethod.POST)
	@ResponseBody
	public Map addAgentMer(@ModelAttribute("merInfo") TblMerInfoDO merInfo,
			@ModelAttribute("bank") TblMerBankInfoDO tblMerBankInfoDO,
			@ModelAttribute("agentMerFeeForm") AgentMerFeeForm agentMerFeeForm,
			@ModelAttribute("tblAgentInfoDo") TblAgentInfoDo tblAgentInfoDo,
			@ModelAttribute("tblMerAddressDo") TblMerAddressDo tblMerAddressDo,
			@ModelAttribute("tblMerRelevanceMccDo") TblMerRelevanceMccDo tblMerRelevanceMccDo, HttpSession session,
			ModelAndView modelAndView, HttpServletResponse response) {
		UserBO user = (UserBO) session.getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
		String userName = user.getUsrName();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		String platFormMerId = merInfo.getMerId();
		String merId = merInfo.getMerId();
		String agentId = tblAgentInfoDo.getMemberId();

		if (!StringUtils.isNotBlank(tblAgentInfoDo.getMemberId())) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "请选择代理商!");
			return resultMap;
		}
		if (!StringUtils.isNotBlank(tblMerBankInfoDO.getCnaps())) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "请选择开户行信息");
			return resultMap;
		}
		if (StringUtils.isBlank(platFormMerId)) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "保存失败!");
			return resultMap;
		}

		if (StringUtils.isBlank(tblMerRelevanceMccDo.getMccValue())) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "请选择行业MCC信息");
			return resultMap;
		}
		String nowTime = DateUtil.getCurrentDate() + DateUtil.getCurrentTime();

		if (StringUtils.isNotBlank(merInfo.getLegalPersonCertExpire()))
			merInfo.setLegalPersonCertExpire(DateUtil.removeLineDateString(merInfo.getLegalPersonCertExpire()));
		if (StringUtils.isNotBlank(merInfo.getBusLicExpire()))
			merInfo.setBusLicExpire(DateUtil.removeLineDateString(merInfo.getBusLicExpire()));
		merInfo.setMerId(platFormMerId);
		merInfo.setUserName(userName);
		merInfo.setUpdateTime(nowTime);
		merInfo.setCreateTime(nowTime);
		merInfo.setMerStat(Constant.AUDIT_AWAY);
		merInfo.setFuncStat("NNNNNNNNNN");

		TblMerLeshuaAddressDo tblMerLeshuaAddressDo = new TblMerLeshuaAddressDo();
		tblMerLeshuaAddressDo.setMerId(merInfo.getMerId());
		tblMerLeshuaAddressDo.setProvinceId(tblMerAddressDo.getProvinceId());
		tblMerLeshuaAddressDo.setCityId(tblMerAddressDo.getCityId());
		tblMerLeshuaAddressDo.setAreaId(tblMerAddressDo.getAreaId());
		tblMerLeshuaAddressDo.setDetailAddress(merInfo.getMerAddress());

		int merAddresscount = merAddressService.insertMerAddress(tblMerLeshuaAddressDo);
		if (merAddresscount == 1)
			logger.info("添加商户地址成功");
		else
			logger.info("添加商户地址失败");

		logger.info("拼接商户地址");
		String address = tblMerAddressDo.getProvinceId() + tblMerAddressDo.getCityId() + tblMerAddressDo.getAreaId()
				+ merInfo.getMerAddress();
		logger.info("商户地址: " + address);
		merInfo.setMerAddress(address);

		int i = merAgentService.insertTblMerInfo(merInfo);
		if (i > 0) {
			tblMerBankInfoDO.setMerId(platFormMerId);
			tblMerBankInfoDO.setUserName(userName);
			tblMerBankInfoDO.setUpdateTime(nowTime);
			tblMerBankInfoDO.setCreateTime(nowTime);
			i = merAgentService.insertTblMerBankInfo(tblMerBankInfoDO);

			TblAgentMerTermDo tblAgentMerTermDo = new TblAgentMerTermDo();
			tblAgentMerTermDo.setMerId(platFormMerId);
			tblAgentMerTermDo.setAgentId(tblAgentInfoDo.getMemberId());
			tblAgentMerTermDo.setUserName(userName);
			tblAgentMerTermDo.setUpdateTime(nowTime);
			tblAgentMerTermDo.setCreateTime(nowTime);
			i = merAgentService.insertTblAgentMerTerm(tblAgentMerTermDo);

			i = merAgentService.insertTblMerFeeInfo(agentMerFeeForm, platFormMerId, "00000000", userName);

			tblMerRelevanceMccDo.setCreateTime(nowTime);
			tblMerRelevanceMccDo.setUpdateTime(nowTime);
			tblMerRelevanceMccDo.setCreateUser(userName);
			merAgentService.insettTblMerRelevanceMccDo(tblMerRelevanceMccDo);
		}

		// 开通普通账户 - 但是设置未激活状态
		logger.info("创建商户登陆账号");
		if (i > 0) {
			Map openMap = new HashMap();
			openMap = openLoginUserService.openNewGeneralMer(platFormMerId);
			if (openMap.get("statusCode").equals("300")) {
				resultMap.put("statusCode", 300);
				resultMap.put("message", "开通大众商户登陆账户失败");
				return resultMap;
			}
		}

		// 为商户生成二维码图片
		logger.info("更新商户二维码信息表");
		if (i > 0) {
			String createOrNot = SystemConstant.TO_CREATE_QRCODE;
			Boolean toCreate = false;
			if (StringUtils.isNotBlank(createOrNot) && createOrNot.equals("Y")) {
				toCreate = true;
			}

			if (!toCreate) {
				logger.info("二维码功能尚未打开");
			} else {
				Map<String, String> qrcodeMap = new HashMap<String, String>();
				qrcodeMap = qrcodeService.QrcodeCreate(null, agentId, merId, null);

				if (qrcodeMap != null && qrcodeMap.containsKey("imageLocation")) {
					String qrcodeImagePath = qrcodeMap.get("imageLocation");
					Boolean status = qrcodeService.QrcodeSaving(merId, null, agentId, null, qrcodeImagePath);
					if (!status) {
						resultMap.put("statusCode", 300);
						resultMap.put("message", "保存二维码失败");
						return resultMap;
					}
				} else {
					resultMap.put("statusCode", 300);
					resultMap.put("message", "生成二维码失败");
					return resultMap;
				}
			}
		}

		if (i > 0) {
			resultMap.put("statusCode", 200);
			resultMap.put("message", "保存成功");
		} else {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "保存失败");
		}
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "/upload_agent_mer_file")
	public Map uploadInstMerFile(HttpServletRequest request) {
		Map resultMap = new HashMap<>();
		String filePath = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("file");
		String fileName = multipartFile.getOriginalFilename();
		String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		File targetFile = new File(
				com.allcheer.bpos.constant.SystemConstant.ADD_AGENT_MER_FILE_PATH + DateUtil.getCurrentDate(),
				fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		} else {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "已存在同名文件!");
			return resultMap;
		}
		try {
			multipartFile.transferTo(targetFile);
			filePath = targetFile.getPath();
		} catch (Exception e) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "文件上传失败!");
			return resultMap;
		}
		resultMap.put("statusCode", 200);
		resultMap.put("message", "文件上传成功!");
		resultMap.put("filename", fileName);
		resultMap.put("filePath", filePath);
		resultMap.put("fileType", fileType);
		return resultMap;
	}

	@RequestMapping(value = "/add_batch_agent_mer", method = RequestMethod.GET)
	public ModelAndView goToAddInstMerPage(ModelAndView modelAndView) {
		List<TblAgentInfoDo> tblAgentInfoDos = merAgentService.findAgentInfos(new TblAgentInfoDo());
		modelAndView.getModel().put("tblAgentInfoDos", tblAgentInfoDos);
		modelAndView.setViewName("/mer/add_batch_agent_mer");
		return modelAndView;
	}

	@RequestMapping(value = "/add_batch_agent_mer", method = RequestMethod.POST)
	public ModelAndView addInstMer(
			@ModelAttribute("addBatchAgentMerFileForm") AddBatchAgentMerFileForm addBatchAgentMerFileForm,
			HttpSession session, ModelAndView modelAndView) {
		String agentId = addBatchAgentMerFileForm.getAgentId();
		String fileType = addBatchAgentMerFileForm.getUploadFileType();
		String uploadFile = addBatchAgentMerFileForm.getUploadFile();
		UserBO user = (UserBO) session.getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
		String userName = user.getUsrName();
		String batchNo;
		batchNo = addBatchAgentMerService.addBatchAgentMerData(agentId, uploadFile, fileType, userName);

		List<TblAgentMerAddDetailInfoDO> tblAgentMerAddDetailInfoDOList = addBatchAgentMerService
				.queryAddDetailInfoByBatchNo(batchNo);
		modelAndView.getModel().put("tblAgentMerAddDetailInfoDOList", tblAgentMerAddDetailInfoDOList);

		List<TblAgentInfoDo> tblAgentInfoDos = merAgentService.findAgentInfos(new TblAgentInfoDo());
		modelAndView.getModel().put("tblAgentInfoDos", tblAgentInfoDos);
		modelAndView.setViewName("/mer/add_batch_agent_mer");

		return modelAndView;
	}

	@RequestMapping(value = "/update_agent_mer", method = RequestMethod.POST)
	@ResponseBody
	public Map updateAgentMer(@ModelAttribute("merInfo") TblMerInfoDO merInfo,
			@ModelAttribute("bank") TblMerBankInfoDO tblMerBankInfoDO,
			@ModelAttribute("agentMerFeeForm") AgentMerFeeForm agentMerFeeForm,
			@ModelAttribute("tblAgentInfoDo") TblAgentInfoDo tblAgentInfoDo,
			@ModelAttribute("tblMerRelevanceMccDo") TblMerRelevanceMccDo tblMerRelevanceMccDo, HttpSession session,
			ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) {
		UserBO user = (UserBO) session.getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
		String userName = user.getUsrName();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String platFormMerId = merInfo.getMerId();
		if (!StringUtils.isNotBlank(tblAgentInfoDo.getMemberId())) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "请选择代理商!");
			return resultMap;
		}
		if (!StringUtils.isNotBlank(tblMerBankInfoDO.getCnaps())) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "请选择开户行信息");
			return resultMap;
		}

		if (StringUtils.isBlank(tblMerRelevanceMccDo.getMccDesp())) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "请选择MCC行业信息");
			return resultMap;
		}

		if (StringUtils.isBlank(platFormMerId)) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "保存失败!");
			return resultMap;
		}
		TblMerRelevanceMccDo tt = new TblMerRelevanceMccDo();
		tt = merAgentService.queryTblMerRelevanceMccDoById(merInfo.getMerId());
		String nowTime = DateUtil.getCurrentDate();

		if (StringUtils.isNotBlank(merInfo.getLegalPersonCertExpire()))
			merInfo.setLegalPersonCertExpire(DateUtil.removeLineDateString(merInfo.getLegalPersonCertExpire()));
		if (StringUtils.isNotBlank(merInfo.getBusLicExpire()))
			merInfo.setBusLicExpire(DateUtil.removeLineDateString(merInfo.getBusLicExpire()));
		merInfo.setMerId(platFormMerId);
		merInfo.setUserName(userName);
		merInfo.setUpdateTime(nowTime);
		// add by tonny 商户修改后改为为审核
		merInfo.setMerStat(Constant.AUDIT_AWAY);
		int i = merAgentService.updateTblMerInfo(merInfo);
		if (i > 0) {
			tblMerBankInfoDO.setMerId(platFormMerId);
			tblMerBankInfoDO.setUserName(userName);
			tblMerBankInfoDO.setUpdateTime(nowTime);
			i = merAgentService.updateTblMerBankInfo(tblMerBankInfoDO);

			if (tt != null) {
				tblMerRelevanceMccDo.setUpdateTime(nowTime);
				tblMerRelevanceMccDo.setCreateUser(userName);
				merAgentService.updateMerMcc(tblMerRelevanceMccDo);
			} else {
				tblMerRelevanceMccDo.setCreateTime(nowTime);
				tblMerRelevanceMccDo.setUpdateTime(nowTime);
				tblMerRelevanceMccDo.setCreateUser(userName);
				merAgentService.insettTblMerRelevanceMccDo(tblMerRelevanceMccDo);
			}

			resultMap = merAgentService.updateTblMerFeeInfo(agentMerFeeForm, platFormMerId, "00000000", userName);
		}
		if (i > 0 && resultMap.get("statusCode").toString().equals("200")) {
			resultMap.put("statusCode", 200);
			resultMap.put("message", "上传成功");
		} else {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "上传失败");
		}
		return resultMap;
	}

	@RequestMapping(value = "/go_to_select_agent_mer", method = RequestMethod.GET)
	public ModelAndView goToSelectAgertMerPage(ModelAndView modelAndView) {
		TblAgentInfoDo tblAgentInfoDo = new TblAgentInfoDo();
		modelAndView.getModel().put("tblAgentInfoDo", tblAgentInfoDo);
		modelAndView.setViewName("/mer/select_agent_info");

		return modelAndView;
	}

	@RequestMapping(value = "/go_to_select_agent_mer", method = RequestMethod.POST)
	public ModelAndView queryAgertMerPage(@ModelAttribute("tblAgentInfoDo") TblAgentInfoDo tblAgentInfoDo,
			ModelAndView modelAndView) {
		List<TblAgentInfoDo> tblAgentInfoDoList = merAgentService.findAgentInfos(tblAgentInfoDo);
		modelAndView.getModel().put("tblAgentInfoDoList", tblAgentInfoDoList);
		modelAndView.setViewName("/mer/select_agent_info");

		return modelAndView;
	}

	@RequestMapping(value = "/go_to_select_bank_info", method = RequestMethod.GET)
	public ModelAndView goToSelectBankInfoPage(ModelAndView modelAndView) {
		SubBranchInfoForm subBranchInfoForm = new SubBranchInfoForm();
		modelAndView.getModel().put("subBranchInfoForm", subBranchInfoForm);
		modelAndView.setViewName("/mer/select_bank_info");

		return modelAndView;
	}

	@RequestMapping(value = "/go_to_select_bank_info", method = RequestMethod.POST)
	@RequiresAuthentication
	public ModelAndView queryBankInfoPage(@ModelAttribute("subBranchInfoForm") SubBranchInfoForm subBranchInfoForm,
			ModelAndView modelAndView) {
		Pagination<TblSubbranchInfoDO> tblSubbranchInfoDoList = merAgentService.findBankInfos(subBranchInfoForm);
		subBranchInfoForm.setPagination(tblSubbranchInfoDoList);
		modelAndView.getModel().put("subBranchInfoForm", subBranchInfoForm);
		modelAndView.setViewName("/mer/select_bank_info");

		return modelAndView;
	}

	@RequestMapping(value = "/go_to_select_mer_mcc", method = RequestMethod.GET)
	public ModelAndView goToSelectMerMccPage(ModelAndView modelAndView) {
		TblMerCoreMccDo tblMerCoreMccDo = new TblMerCoreMccDo();
		modelAndView.getModel().put("tblMerCoreMccDo", tblMerCoreMccDo);
		modelAndView.setViewName("/mer/select_mer_mcc");

		return modelAndView;
	}

	@RequestMapping(value = "/go_to_select_mer_mcc", method = RequestMethod.POST)
	@RequiresAuthentication
	public ModelAndView queryMerMccPage(@ModelAttribute("tblMerCoreMccDo") TblMerCoreMccDo tblMerCoreMccDo,
			ModelAndView modelAndView) {
		Pagination<TblMerCoreMccDo> tblMerCoreMccDoPagination = merAgentService.findMerMccs(tblMerCoreMccDo);
		tblMerCoreMccDo.setPagination(tblMerCoreMccDoPagination);
		modelAndView.getModel().put("tblMerCoreMccDo", tblMerCoreMccDo);
		modelAndView.setViewName("/mer/select_mer_mcc");

		return modelAndView;
	}

	@RequestMapping(value = "/query_agent_mer_list")
	public String queryAgentMerList(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm, ModelAndView modelAndView) {
		Map<String, String> queryMap = new HashMap<>();
		queryMap = Bean2Map.beanToMapNoFill(merInfoForm);
		List<TblAgentInfoDo> tblAgentInfoDoList = merAgentService.findAgentInfos(new TblAgentInfoDo());

		Pagination<TblAgentMerInfoVO> tblAgentMerInfoVOPagination = merAgentService.queryAgentMerInfoList(queryMap);
		merInfoForm.setPagination(tblAgentMerInfoVOPagination);
		merInfoForm.setTblAgentInfoDos(tblAgentInfoDoList);

		return "/mer/mer_agent_list";
	}

	@RequestMapping(value = "/query_agent_mer_edit_list")
	public String queryAgentMerEditList(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm,
			ModelAndView modelAndView) {
		Map<String, String> queryMap = new HashMap<>();
		queryMap = Bean2Map.beanToMapNoFill(merInfoForm);
		List<TblAgentInfoDo> tblAgentInfoDoList = merAgentService.findAgentInfos(new TblAgentInfoDo());

		Pagination<TblAgentMerInfoVO> tblAgentMerInfoVOPagination = merAgentService.queryAgentMerInfoList(queryMap);
		merInfoForm.setPagination(tblAgentMerInfoVOPagination);
		merInfoForm.setTblAgentInfoDos(tblAgentInfoDoList);

		return "/mer/mer_agent_list_edit";
	}

	@RequestMapping(value = "/uploadAgentImage")
	@ResponseBody
	public Map uploadAgentImage(@RequestParam(value = "file", required = false) CommonsMultipartFile cmf,
			HttpSession session, HttpServletRequest request) {
		Map resultMap = new HashMap();
		if (cmf == null) {
			throw new BposException("图片为空!");
		}

		String fileName = cmf.getOriginalFilename();
		String type = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();

		long platFormMerIdSeq = seqMapper.getSequenceNextVal("file_name_seq");
		fileName = StringUtils.leftPad(String.valueOf(platFormMerIdSeq), 10, '0');

		String ym = DateUtil.date2String(new Date(), "yyyyMMdd");
		String ymd = DateUtil.date2String(new Date(), "yyyyMMddHHmmss");
		fileName = fileName + "_" + ymd + type;
		String path = com.allcheer.bpos.constant.SystemConstant.ADD_AGENT_MER_ATTACHMENT_FILE_PATH + ym + File.separator
				+ fileName;
		try {
			File f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}
			cmf.transferTo(f);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			resultMap.put("statusCode", 300);
			resultMap.put("message", "上传失败");
		}
		resultMap.put("statusCode", 200);
		resultMap.put("message", "上传成功");
		resultMap.put("filename", path);

		return resultMap;
	}

	@RequestMapping(value = "/showImageFile")
	public void showAgentMerImage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
		String filePath = request.getParameter("path");
		String path = filePath;
		FileInputStream in;
		try {
			in = new FileInputStream(path);
			int i = in.available();
			byte[] data = new byte[i];
			in.read(data);
			in.close();

			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			throw new BposException("找不到图片");
		}
	}

	@RequestMapping(value = "/delete_image_page")
	@ResponseBody
	public Map deleteImages(String merId, String path, String colName, ModelAndView modelAndView) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (StringUtils.isBlank(merId) || StringUtils.isBlank(path)) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "删除失败");
			return resultMap;
		}
		resultMap = merAgentService.deleteImage(merId, path, colName);
		return resultMap;
	}

	@RequestMapping(value = "/agent_mer_image_list")
	public String agentMerImageList(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm, ModelAndView modelAndView) {
		Map<String, String> queryMap = new HashMap<>();
		queryMap = Bean2Map.beanToMapNoFill(merInfoForm);
		List<TblAgentInfoDo> tblAgentInfoDoList = merAgentService.findAgentInfos(new TblAgentInfoDo());

		Pagination<TblAgentMerInfoVO> tblAgentMerInfoVOPagination = merAgentService.queryAgentMerInfoList(queryMap);
		merInfoForm.setPagination(tblAgentMerInfoVOPagination);
		merInfoForm.setTblAgentInfoDos(tblAgentInfoDoList);

		return "/mer/agent_mer_image_list";
	}

	@RequestMapping(value = "/query_mer_agent_detail")
	public ModelAndView agentMerDetail(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm,
			HttpServletRequest request, ModelAndView modelAndView) {
		String merId = request.getParameter("id");

		TblAgentInfoDo tblAgentInfoDo = merAgentService.queryAgentById(merId);
		TblMerInfoDO tblMerInfoDO = merAgentService.queryMerById(merId);
		TblMerBankInfoDO tblMerBankInfoDO = merAgentService.queryMerBankById(merId);
		List<TblMerFeeInfoDO> tblMerFeeInfoDOS = merAgentService.queryMerFeeById(merId);
		TblMerFileInfoDO tblMerFileInfoDO = merAgentService.queryFilesByMerId(merId);
		TblMerRelevanceMccDo tblMerRelevanceMccDo = merAgentService.queryTblMerRelevanceMccDoById(merId);

		AgentMerFeeForm agentMerFeeForm = new AgentMerFeeForm();
		String feeType = "";
		String[] calc;
		int num = 0;
		for (TblMerFeeInfoDO tblMerFeeInfoDO : tblMerFeeInfoDOS) {
			feeType = tblMerFeeInfoDO.getFeeType();
			feeType = feeType.replace("0", "");
			num = Integer.parseInt(feeType);
			calc = CalcModeUtil.splitCalcMode(tblMerFeeInfoDO.getCalcMode(), false).split(",");
			switch (num) {
			case 1:
				agentMerFeeForm.setFee01(calc[0]);
				agentMerFeeForm.setFee01L(calc[1]);
				break;
			case 2:
				agentMerFeeForm.setFee02(calc[0]);
				break;
			case 3:
				agentMerFeeForm.setFee03(calc[0]);
				break;
			case 4:
				agentMerFeeForm.setFee04(calc[0]);
				break;
			case 5:
				agentMerFeeForm.setFee05(calc[0]);
				break;
			case 6:
				agentMerFeeForm.setFee06(calc[0]);
				break;
			case 7:
				agentMerFeeForm.setFee07(calc[0]);
				break;
			case 8:
				agentMerFeeForm.setFee08(calc[0]);
				break;
			default:
				break;
			}
		}
		modelAndView.addObject("tblMerFileInfoDO", tblMerFileInfoDO);
		modelAndView.addObject("tblAgentInfoDo", tblAgentInfoDo);
		modelAndView.addObject("tblMerInfoDO", tblMerInfoDO);
		modelAndView.addObject("tblMerBankInfoDO", tblMerBankInfoDO);
		modelAndView.addObject("agentMerFeeForm", agentMerFeeForm);
		modelAndView.addObject("tblMerRelevanceMccDo", tblMerRelevanceMccDo);
		modelAndView.setViewName("/mer/agent_mer_detail");

		return modelAndView;
	}

	@RequestMapping(value = "/query_agent_mer_list_edit")
	public String queryAgentMerListForEdit(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm,
			ModelAndView modelAndView) {
		Map<String, String> queryMap = new HashMap<>();
		queryMap = Bean2Map.beanToMapNoFill(merInfoForm);
		List<TblAgentInfoDo> tblAgentInfoDoList = merAgentService.findAgentInfos(new TblAgentInfoDo());

		Pagination<TblAgentMerInfoVO> tblAgentMerInfoVOPagination = merAgentService.queryAgentMerInfoList(queryMap);
		merInfoForm.setPagination(tblAgentMerInfoVOPagination);
		merInfoForm.setTblAgentInfoDos(tblAgentInfoDoList);

		return "/mer/mer_agent_list_edit";
	}

	@RequestMapping(value = "/query_mer_agent_edit")
	public ModelAndView agentMerEdit(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm, HttpServletRequest request,
			ModelAndView modelAndView) {
		String merId = request.getParameter("id");

		TblAgentInfoDo tblAgentInfoDo = merAgentService.queryAgentById(merId);
		TblMerInfoDO tblMerInfoDO = merAgentService.queryMerById(merId);
		TblMerBankInfoDO tblMerBankInfoDO = merAgentService.queryMerBankById(merId);
		List<TblMerFeeInfoDO> tblMerFeeInfoDOS = merAgentService.queryMerFeeById(merId);
		AgentMerFeeForm agentMerFeeForm = new AgentMerFeeForm();
		TblMerFileInfoDO tblMerFileInfoDO = merAgentService.queryFilesByMerId(merId);
		TblMerRelevanceMccDo tblMerRelevanceMccDo = merAgentService.queryTblMerRelevanceMccDoById(merId);

		String feeType = "";
		String[] calc;
		int num = 0;
		for (TblMerFeeInfoDO tblMerFeeInfoDO : tblMerFeeInfoDOS) {
			feeType = tblMerFeeInfoDO.getFeeType();
			feeType = feeType.replace("0", "");
			num = Integer.parseInt(feeType);
			calc = CalcModeUtil.splitCalcMode(tblMerFeeInfoDO.getCalcMode(), false).split(",");
			switch (num) {
			case 1:
				agentMerFeeForm.setFee01(calc[0]);
				agentMerFeeForm.setFee01L(calc[1]);
				break;
			case 2:
				agentMerFeeForm.setFee02(calc[0]);
				break;
			case 3:
				agentMerFeeForm.setFee03(calc[0]);
				break;
			case 4:
				agentMerFeeForm.setFee04(calc[0]);
				break;
			case 5:
				agentMerFeeForm.setFee05(calc[0]);
				break;
			case 6:
				agentMerFeeForm.setFee06(calc[0]);
				break;
			case 7:
				agentMerFeeForm.setFee07(calc[0]);
				break;
			case 8:
				agentMerFeeForm.setFee08(calc[0]);
				break;
			default:
				break;
			}
		}
		modelAndView.addObject("tblMerFileInfoDO", tblMerFileInfoDO);
		modelAndView.addObject("tblAgentInfoDo", tblAgentInfoDo);
		modelAndView.addObject("tblMerInfoDO", tblMerInfoDO);
		modelAndView.addObject("tblMerBankInfoDO", tblMerBankInfoDO);
		modelAndView.addObject("agentMerFeeForm", agentMerFeeForm);
		modelAndView.addObject("tblMerRelevanceMccDo", tblMerRelevanceMccDo);

		TblMerAuditRecordDO tblMerAuditRecordDO = merAgentService.queryMerAuditReocrdByMerId(merId);
		if (tblMerAuditRecordDO == null) {
			tblMerAuditRecordDO = new TblMerAuditRecordDO();
		}
		modelAndView.addObject("tblMerAuditRecordDO", tblMerAuditRecordDO);
		modelAndView.setViewName("/mer/agent_mer_edit");

		return modelAndView;
	}

	@RequestMapping(value = "/go_to_upload_image_page")
	public ModelAndView goToUploadImagePage(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm,
			ModelAndView modelAndView, HttpServletRequest request) {
		String merId = request.getParameter("merId");
		String agentId = request.getParameter("agentId");

		if (StringUtils.isBlank(merId)) {
			throw new BposException("获得商户信息失败!");
		}
		TblMerFileInfoDO tblMerFileInfoDO = merAgentService.queryFilesByMerId(merId);
		modelAndView.getModel().put("tblMerFileInfoDO", tblMerFileInfoDO);
		modelAndView.getModel().put("merId", merId);
		modelAndView.getModel().put("agentId", agentId);
		modelAndView.setViewName("/mer/mer_images_detail");

		TblMerAuditRecordDO tblMerAuditRecordDO = merAgentService.queryMerAuditReocrdByMerId(merId);
		if (tblMerAuditRecordDO == null) {
			tblMerAuditRecordDO = new TblMerAuditRecordDO();
		}

		modelAndView.getModel().put("tblMerAuditRecordDO", tblMerAuditRecordDO);

		return modelAndView;
	}

	@RequestMapping(value = "/add_agent_mer_image")
	@ResponseBody
	public Map addAgentMerImage(@ModelAttribute("merFileForm") TblMerFileInfoDO tblMerFileInfoDO,
			ModelAndView modelAndView, HttpServletRequest request) {
		Map resultMap = new HashMap();
		if (StringUtils.isBlank(tblMerFileInfoDO.getMerId())) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "更新失败!");
			return resultMap;
		}
		TblMerInfoDO tblMerInfoDO = merAgentService.queryMerById(tblMerFileInfoDO.getMerId());
		if (tblMerInfoDO == null) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "查找不到相应商户");
			return resultMap;
		}
		tblMerInfoDO.setMerStat(Constant.AUDIT_NONE);
		merAgentService.updateTblMerInfo(tblMerInfoDO);
		resultMap = merAgentService.insertTblFileMer(tblMerFileInfoDO);
		return resultMap;
	}

	@RequestMapping(value = "/export_agent_mer_report")
	public String exportAgentMerList(HttpServletRequest request, @ModelAttribute("merInfoForm") MerInfoForm merInfoForm,
			HttpServletResponse response) throws Exception {
		Map<String, String> queryMap = new HashMap<>();
		queryMap = Bean2Map.beanToMapNoFill(merInfoForm);

		List<TblAgentMerInfoVO> tblAgentMerList = merAgentService.queryAgentMerExportList(queryMap);

		response.setContentType("application/binary;charset=ISO8859_1");

		try {
			String nowTime = DateUtil.date2String(new Date(), "yyyyMMddHHmmss");
			ServletOutputStream outputStream = response.getOutputStream();
			String fileName = new String(("商户信息列表").getBytes(), "ISO8859_1");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + "_" + nowTime + ".xls");// 组装附件名称和格式
			merAgentService.exportAgentMerList(tblAgentMerList, outputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "/report_mer_agent", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> reportMerAgent(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm,
			HttpServletRequest request, HttpSession session, ModelAndView modelAndView, HttpServletResponse response) {

		String merId = request.getParameter("merId");
		Map<String, Object> map = new HashMap<>();
		map = merAgentService.reportMerAgent(merId);

		return map;
	}

	// 提交商户
	@RequestMapping(value = "/commit_agent", method = RequestMethod.POST)
	@ResponseBody
	public Map commitAgent(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String merId = request.getParameter("merId");

		Map<String, Object> resultMap = new HashMap<>();
		UserBO user = (UserBO) session.getAttribute(SystemConstant.USER_SESSION_KEY);
		String userName = user.getUsrName();
		int res;

		TblMerInfoDO tblMerInfoDO = merAgentService.queryMerById(merId);

		if (!tblMerInfoDO.getMerStat().equals("3")) {

			throw new BposException("已经进入审核流程, 无法提交");
		}

		resultMap = merAgentService.commitNewAgentReq(merId, userName);

		return resultMap;
	}

	@RequestMapping(value = "/go_to_select_mer_address_info", method = RequestMethod.GET)
	public ModelAndView goToSelectMerAddressPage(ModelAndView modelAndView) {
		MerAddressForm merAddressForm = new MerAddressForm();
		modelAndView.getModel().put("merAddressForm", merAddressForm);
		modelAndView.setViewName("/mer/select_mer_address_info");

		return modelAndView;
	}

	@RequestMapping(value = "/go_to_select_mer_address", method = RequestMethod.POST)
	@RequiresAuthentication
	public ModelAndView queryMerAddressPage(@ModelAttribute("merAddressForm") MerAddressForm merAddressForm,
			ModelAndView modelAndView) {
		Pagination<TblMerAddressDo> tblMerAddressDooList = merAddressService.findMerAddressInfos(merAddressForm);
		merAddressForm.setPagination(tblMerAddressDooList);
		modelAndView.getModel().put("merAddressForm", merAddressForm);
		modelAndView.setViewName("/mer/select_mer_address_info");

		return modelAndView;
	}
}
