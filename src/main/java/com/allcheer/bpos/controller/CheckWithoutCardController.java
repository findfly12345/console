package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblBtsTransLogDO;
import com.allcheer.bpos.entity.TblMsCheckFileDo;
import com.allcheer.bpos.entity.TblT0CheckFileResponseDo;
import com.allcheer.bpos.entity.TblT1CheckFileRespondeDo;
import com.allcheer.bpos.entity.TblWechatIncomeCheckFileDo;
import com.allcheer.bpos.form.CheckFileForm;
import com.allcheer.bpos.form.TransLogForm;
import com.allcheer.bpos.service.CheckWithOutCardService;
import com.allcheer.bpos.service.InstitutionService;
import com.allcheer.bpos.service.impl.CheckWithOutCardServiceImpl;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiuBin on 2017/1/5.
 */
@Controller
@RequestMapping(value = "/checkWithOutCard")
public class CheckWithoutCardController {

	private final static Logger logger = LoggerFactory.getLogger(CheckWithOutCardServiceImpl.class);

	@Autowired
	private CheckWithOutCardService checkWithOutCardService;

	@Autowired
	private InstitutionService institutionService;

	@RequestMapping(value = "/get_channel_check_file", method = RequestMethod.GET)
	@RequiresAuthentication
	public ModelAndView goToGetChannnelCheckFilePage(ModelAndView modelAndView) {
		modelAndView.setViewName("/checkWithOutCard/get_channel_check_file");
		CheckFileForm checkFileForm = new CheckFileForm();
		modelAndView.getModel().put("checkFileForm", checkFileForm);

		return modelAndView;
	}

	@RequestMapping(value = "/get_channel_check_file", method = RequestMethod.POST)
	@RequiresAuthentication
	public ModelAndView getChannnelCheckFile(@ModelAttribute("checkFileForm") CheckFileForm checkFileForm,
			HttpSession session, ModelAndView modelAndView) {
		modelAndView.setViewName("/checkWithOutCard/get_channel_check_file");
		String transDate = checkFileForm.getFilterDate();
		if (!StringUtils.isNotBlank(transDate)) {
			return modelAndView;
		}
		transDate = DateUtil.removeLineDateString(transDate);
		checkFileForm.setFilterDate(transDate);
		int pageCurrent = Integer.parseInt(checkFileForm.getPageCurrent());
		int pageSize = Integer.parseInt(checkFileForm.getPageSize());
		int fileSize = checkWithOutCardService.getMsCheckFileListCount(checkFileForm);

		Pagination<TblMsCheckFileDo> pagination = new Pagination<TblMsCheckFileDo>(fileSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);
		List<TblMsCheckFileDo> tblMsCheckFileDos = new ArrayList<>();

		if (fileSize == 0) {
			Map<String, String> msMap = checkWithOutCardService.getMs020(DateUtil.removeLineDateString(transDate));
			if (msMap.size() != 0) {
				tblMsCheckFileDos = checkWithOutCardService.getMsCheckFile(transDate);
			} else {
				return modelAndView;
			}
		} else {
			tblMsCheckFileDos = checkWithOutCardService.getMsCheckFile(transDate);
		}

		pagination.addResult(tblMsCheckFileDos);
		modelAndView.getModel().put("pageUser", pagination);
		modelAndView.getModel().put("tblMsCheckFileDoList", tblMsCheckFileDos);

		return modelAndView;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/check_trade", method = RequestMethod.GET)
	public ModelAndView goToCheckTradePage(ModelAndView modelAndView) {
		modelAndView.setViewName("/checkWithOutCard/check_trade");
		CheckFileForm checkFileForm = new CheckFileForm();
		modelAndView.getModel().put("checkFileForm", checkFileForm);

		return modelAndView;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/check_trade", method = RequestMethod.POST)
	public ModelAndView checkTrade(@ModelAttribute("checkFileForm") CheckFileForm checkFileForm, HttpSession session,
			ModelAndView modelAndView) {
		modelAndView.setViewName("/checkWithOutCard/check_trade");
		Map resultMap = new HashMap();
		String transDate = checkFileForm.getFilterDate();
		if (!StringUtils.isNotBlank(transDate)) {
			resultMap.put("success", false);
			resultMap.put("msg", "日期不能为空");
			return modelAndView;
		}
		transDate = DateUtil.removeLineDateString(transDate);
		checkFileForm.setFilterDate(transDate);
		int pageCurrent = Integer.parseInt(checkFileForm.getPageCurrent());
		int pageSize = Integer.parseInt(checkFileForm.getPageSize());
		int fileSize = checkWithOutCardService.getWeChatIncomeCheckFileListCount(checkFileForm);

		CheckFileForm checkFileForm1 = new CheckFileForm();
		checkFileForm1.setFilterDate(transDate);
		int flag = checkWithOutCardService.getWeChatIncomeCheckFileListCount(checkFileForm1);

		Pagination<TblWechatIncomeCheckFileDo> pagination = new Pagination<TblWechatIncomeCheckFileDo>(fileSize,
				pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);
		List<TblWechatIncomeCheckFileDo> tblWechatIncomeCheckFileDos = new ArrayList<>();
		if (flag == 0 && fileSize == 0) {
			resultMap = checkWithOutCardService.compareT1CheckFile(transDate);
			if (resultMap.size() != 0) {
				tblWechatIncomeCheckFileDos = checkWithOutCardService.getDifferenceWithCheckFile(checkFileForm);
			}
		} else {
			tblWechatIncomeCheckFileDos = checkWithOutCardService.getDifferenceWithCheckFile(checkFileForm);
		}

		pagination.addResult(tblWechatIncomeCheckFileDos);
		modelAndView.getModel().put("pageUser", pagination);
		modelAndView.getModel().put("tblWechatIncomeCheckFileDoList", tblWechatIncomeCheckFileDos);

		return modelAndView;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/get_t1_settlement", method = RequestMethod.GET)
	public ModelAndView goToGetT1SettlementPage(ModelAndView modelAndView) {
		modelAndView.setViewName("/checkWithOutCard/get_t1_settlement");
		CheckFileForm checkFileForm = new CheckFileForm();
		List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
		modelAndView.getModel().put("tblBtsInstDOList", tblBtsInstDOList);
		modelAndView.getModel().put("checkFileForm", checkFileForm);

		return modelAndView;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/get_t1_settlement", method = RequestMethod.POST)
	public ModelAndView getT1Settlement(@ModelAttribute("checkFileForm") CheckFileForm checkFileForm,
			HttpSession session, ModelAndView modelAndView) {

		modelAndView.setViewName("/checkWithOutCard/get_t1_settlement");
		Map resultMap = new HashMap();
		String transDate = checkFileForm.getFilterDate();
		if (!StringUtils.isNotBlank(transDate)) {
			return modelAndView;
		}
		transDate = DateUtil.removeLineDateString(transDate);
		checkFileForm.setFilterDate(transDate);
		int pageCurrent = Integer.parseInt(checkFileForm.getPageCurrent());
		int pageSize = Integer.parseInt(checkFileForm.getPageSize());
		int fileSize = checkWithOutCardService.geT1CheckFileListCount(checkFileForm);

		CheckFileForm checkFileForm1 = new CheckFileForm();
		checkFileForm1.setFilterDate(transDate);
		int flag = checkWithOutCardService.geT1CheckFileListCount(checkFileForm1);

		Pagination<TblT1CheckFileRespondeDo> pagination = new Pagination<TblT1CheckFileRespondeDo>(fileSize,
				pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);
		List<TblT1CheckFileRespondeDo> tblT1CheckFileRespondeDos = new ArrayList<>();
		if (flag == 0 && fileSize == 0) {
			resultMap = checkWithOutCardService.getMs025(transDate);
			if (resultMap.size() != 0) {
				tblT1CheckFileRespondeDos = checkWithOutCardService.getT1CheckFile(checkFileForm);
			}
		} else {
			tblT1CheckFileRespondeDos = checkWithOutCardService.getT1CheckFile(checkFileForm);
		}

		pagination.addResult(tblT1CheckFileRespondeDos);
		List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
		modelAndView.getModel().put("tblBtsInstDOList", tblBtsInstDOList);
		modelAndView.getModel().put("checkFileForm", checkFileForm);
		
		modelAndView.getModel().put("pageUser", pagination);
		modelAndView.getModel().put("tblT1CheckFileRespondeDoList", tblT1CheckFileRespondeDos);

		return modelAndView;
	}

	@RequestMapping(value = "/get_t0_settlement", method = RequestMethod.GET)
	public ModelAndView goToGetT0SettlementPage(ModelAndView modelAndView) {
		modelAndView.setViewName("/checkWithOutCard/get_t0_settlement");
		CheckFileForm checkFileForm = new CheckFileForm();
		modelAndView.getModel().put("checkFileForm", checkFileForm);
		List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
		modelAndView.getModel().put("tblBtsInstDOList", tblBtsInstDOList);

		return modelAndView;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/get_t0_settlement", method = RequestMethod.POST)
	public ModelAndView getT0Settlement(@ModelAttribute("checkFileForm") CheckFileForm checkFileForm,
			HttpSession session, ModelAndView modelAndView) {

		modelAndView.setViewName("/checkWithOutCard/get_t0_settlement");
		Map resultMap = new HashMap();
		String transDate = checkFileForm.getFilterDate();
		if (!StringUtils.isNotBlank(transDate)) {
			return modelAndView;
		}
		transDate = DateUtil.removeLineDateString(transDate);
		checkFileForm.setFilterDate(transDate);
		int pageCurrent = Integer.parseInt(checkFileForm.getPageCurrent());
		int pageSize = Integer.parseInt(checkFileForm.getPageSize());
		int fileSize = checkWithOutCardService.geT0CheckFileListCount(checkFileForm);

		CheckFileForm checkFileForm1 = new CheckFileForm();
		checkFileForm1.setFilterDate(transDate);
		int flag = checkWithOutCardService.geT0CheckFileListCount(checkFileForm1);
		Pagination<TblT0CheckFileResponseDo> pagination = new Pagination<TblT0CheckFileResponseDo>(fileSize,
				pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblT0CheckFileResponseDo> tblT0CheckFileResponseDos = new ArrayList<>();
		if (flag == 0 && fileSize == 0) {
			resultMap = checkWithOutCardService.getMs024(transDate);
			if (resultMap.size() != 0) {
				tblT0CheckFileResponseDos = checkWithOutCardService.getT0CheckFile(checkFileForm);
			}
		} else {
			tblT0CheckFileResponseDos = checkWithOutCardService.getT0CheckFile(checkFileForm);
		}

		pagination.addResult(tblT0CheckFileResponseDos);

		List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
		modelAndView.getModel().put("tblBtsInstDOList", tblBtsInstDOList);
		modelAndView.getModel().put("checkFileForm", checkFileForm);
		modelAndView.getModel().put("pageUser", pagination);
		modelAndView.getModel().put("tblT0CheckFileResponseDoList", tblT0CheckFileResponseDos);

		return modelAndView;
	}

	@RequestMapping("download")
	@RequiresAuthentication
	public String doDownload(@ModelAttribute("checkFileForm") CheckFileForm checkFileForm, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		String transDate = checkFileForm.getFilterDate();
		transDate = DateUtil.removeLineDateString(transDate);
		checkFileForm.setFilterDate(transDate);
		List<TblT1CheckFileRespondeDo> t1CheckFileList = checkWithOutCardService.getT1CheckFile(checkFileForm);

		String instCode = checkFileForm.getInstCode();
		logger.info("机构号: " + instCode);

		response.setContentType("application/binary;charset=ISO8859_1");
		ServletOutputStream outputStream = null;

		try {
			outputStream = response.getOutputStream();
			String fileName = new String(("Settle").getBytes(), "ISO8859_1");
			response.setHeader("Content-disposition",
					"attachment; filename=" + fileName + "_T1" + transDate + ".xls");// 组装附件名称和格式
			checkWithOutCardService.exportSettlementInfo(t1CheckFileList, outputStream, instCode);
		} catch (IOException ex) {
			logger.error("导出文件异常: ", ex);
		} catch (Exception ex) {
			logger.error("导出文件系统异常: ", ex);
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("关闭流异常: ", e);
			}
		}
		return null;
	}

	@RequestMapping("D0download")
	@RequiresAuthentication
	public String D0download(@ModelAttribute("checkFileForm") CheckFileForm checkFileForm, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		String transDate = checkFileForm.getFilterDate();
		transDate = DateUtil.removeLineDateString(transDate);
		checkFileForm.setFilterDate(transDate);
		List<TblT0CheckFileResponseDo> t1CheckFileList = checkWithOutCardService.getT0CheckFile(checkFileForm);

		String instCode = checkFileForm.getInstCode();
		logger.info("机构号: " + instCode);

		response.setContentType("application/binary;charset=ISO8859_1");
		ServletOutputStream outputStream = null;

		try {
			outputStream = response.getOutputStream();
			String fileName = new String(("Settle").getBytes(), "ISO8859_1");
			response.setHeader("Content-disposition",
					"attachment; filename=" + fileName + "_D0" + transDate + ".xls");// 组装附件名称和格式
			checkWithOutCardService.exportD0SettlementInfo(t1CheckFileList, outputStream, instCode);
		} catch (IOException ex) {
			logger.error("导出文件异常: ", ex);
		} catch (Exception ex) {
			logger.error("导出文件系统异常: ", ex);
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("关闭流异常: ", e);
			}
		}
		return null;
	}

}
