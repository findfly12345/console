package com.allcheer.bpos.controller;

import com.allcheer.bpos.domain.AgentDetailInfoBO;
import com.allcheer.bpos.domain.AgentFeeBO;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.form.AgentDetailForm;
import com.allcheer.bpos.form.AgentFeeForm;
import com.allcheer.bpos.form.AgentQueryForm;
import com.allcheer.bpos.service.AgentService;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuBin on 2017/1/17.
 * 代理商费率设置
 */
@Transactional
@Controller
@RequestMapping(value = "/agtInfo")
public class AgtInfoController {
	private final String PAGE_PRE = "agt/";

	private static final Logger logger = LoggerFactory.getLogger(AgtInfoController.class);

	@Autowired
	AgentService agentService;

	@RequestMapping(value = "/new_agent_page", method = RequestMethod.GET)
	public String addNewAgentPage(@ModelAttribute("agentDetailForm") AgentDetailForm agentDetailForm) {
		return PAGE_PRE + "add_agent_detail";
	}

	@ResponseBody
	@RequestMapping(value = "/add_new_agent", method = RequestMethod.POST)
	public Map addNewAgent(@ModelAttribute("agentDetailForm") AgentDetailForm agentDetailForm, HttpSession session,
			HttpServletRequest request) {

		UserBO user = (UserBO) session.getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
		String userName = user.getUsrName();

		Map resultMap = new HashMap();

		AgentDetailInfoBO agentDetailInfoBO = new AgentDetailInfoBO();
		agentDetailInfoBO.setAgentName(agentDetailForm.getAgentName());
		agentDetailInfoBO.setAgentShortName(agentDetailForm.getAgentShortName());
		agentDetailInfoBO.setRegName(agentDetailForm.getRegName());
		agentDetailInfoBO.setRegCode(agentDetailForm.getRegCode());
		agentDetailInfoBO.setTaxCode(agentDetailForm.getTaxCode());
		agentDetailInfoBO.setRegAddr(agentDetailForm.getRegAddr());
		agentDetailInfoBO.setLicType(agentDetailForm.getLicType());
		agentDetailInfoBO.setLicAmt(agentDetailForm.getLicAmt());
		agentDetailInfoBO.setLegalName(agentDetailForm.getLegalName());
		agentDetailInfoBO.setIdno(agentDetailForm.getIdno());
		agentDetailInfoBO.setContactName(agentDetailForm.getContactName());
		agentDetailInfoBO.setContactMobile(agentDetailForm.getContactMobile());
		agentDetailInfoBO.setContactAddr(agentDetailForm.getContactAddr());
		agentDetailInfoBO.setEmail(agentDetailForm.getEmail());
		agentDetailInfoBO.setTellerId(agentDetailForm.getTellerId());

		// 新增代理商
		resultMap = agentService.openNewAgent(agentDetailInfoBO, userName);
		
		return resultMap;
	}

	@RequestMapping(value = "/showAgtQueryPage", method = RequestMethod.GET)
	public String showAgtQueryPage(HttpServletRequest request,
			@ModelAttribute("agentQueryForm") AgentQueryForm agentQueryForm) {

		HttpSession session = request.getSession();
		String showOemSelect = "0";
		agentQueryForm.setShowOemSelect(showOemSelect);

		AgentDetailInfoBO agentDetailInfoBO = new AgentDetailInfoBO();
		if (!StringUtils.isBlank(agentQueryForm.getAgentShortName())) {
			agentDetailInfoBO.setAgentShortName(agentQueryForm.getAgentShortName());
		}
		if (!StringUtils.isBlank(agentQueryForm.getMemberId())) {
			agentDetailInfoBO.setMemberId(agentQueryForm.getMemberId());
		}
		if (!StringUtils.isBlank(agentQueryForm.getAgentLevel())) {
			agentDetailInfoBO.setAgentLevel(agentQueryForm.getAgentLevel());
		}
		if (!StringUtils.isBlank(agentQueryForm.getMemberStat())) {
			agentDetailInfoBO.setMemberStat(agentQueryForm.getMemberStat());
		}
		String pageCurrent = agentQueryForm.getPageCurrent();
		String pageSize = agentQueryForm.getPageSize();

		agentDetailInfoBO.setPageCurrent(Integer.valueOf(pageCurrent));
		agentDetailInfoBO.setPageSize(Integer.valueOf(pageSize));

		Pagination<AgentDetailInfoBO> agentDetailInfoBOPagination = agentService.queryAgentList(agentDetailInfoBO);
		agentQueryForm.setPagination(agentDetailInfoBOPagination);

		return PAGE_PRE + "query_agent_list";
	}

	@RequestMapping(value = "/query_agents", method = RequestMethod.POST)
	public String QueryAgents(HttpServletRequest request,
			@ModelAttribute("agentQueryForm") AgentQueryForm agentQueryForm) {

		agentQueryForm.setShowOemSelect("0");
		AgentDetailInfoBO agentDetailInfoBO = new AgentDetailInfoBO();

		if (!StringUtils.isBlank(agentQueryForm.getAgentShortName())) {
			agentDetailInfoBO.setAgentShortName(agentQueryForm.getAgentShortName());
		}
		if (!StringUtils.isBlank(agentQueryForm.getMemberId())) {
			agentDetailInfoBO.setMemberId(agentQueryForm.getMemberId());
		}
		if (!StringUtils.isBlank(agentQueryForm.getAgentLevel())) {
			agentDetailInfoBO.setAgentLevel(agentQueryForm.getAgentLevel());
		}
		if (!StringUtils.isBlank(agentQueryForm.getMemberStat())) {
			agentDetailInfoBO.setMemberStat(agentQueryForm.getMemberStat());
		}
		String pageCurrent = agentQueryForm.getPageCurrent();
		String pageSize = agentQueryForm.getPageSize();
		agentDetailInfoBO.setPageCurrent(Integer.valueOf(pageCurrent));
		agentDetailInfoBO.setPageSize(Integer.valueOf(pageSize));

		Pagination<AgentDetailInfoBO> agentDetailInfoBOPagination = agentService.queryAgentList(agentDetailInfoBO);
		agentQueryForm.setPagination(agentDetailInfoBOPagination);

		return PAGE_PRE + "query_agent_list";
	}

	@RequestMapping(value = "/export_agent_report")
	public String exportAgentList(HttpServletRequest request,
			@ModelAttribute("agentQueryForm") AgentQueryForm agentQueryForm, HttpServletResponse response)
			throws Exception {
		AgentDetailInfoBO agentDetailInfoBO = new AgentDetailInfoBO();

		if (!StringUtils.isBlank(agentQueryForm.getAgentShortName())) {
			agentDetailInfoBO.setAgentShortName(agentQueryForm.getAgentShortName());
		}
		if (!StringUtils.isBlank(agentQueryForm.getMemberId())) {
			agentDetailInfoBO.setMemberId(agentQueryForm.getMemberId());
		}
		if (!StringUtils.isBlank(agentQueryForm.getAgentLevel())) {
			agentDetailInfoBO.setAgentLevel(agentQueryForm.getAgentLevel());
		}
		if (!StringUtils.isBlank(agentQueryForm.getMemberStat())) {
			agentDetailInfoBO.setMemberStat(agentQueryForm.getMemberStat());
		}
		response.setContentType("application/binary;charset=ISO8859_1");
		try {
			String nowTime = DateUtil.date2String(new Date(), "yyyyMMddHHmmss");
			ServletOutputStream outputStream = response.getOutputStream();
			String fileName = new String(("代理商列表").getBytes(), "ISO8859_1");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + "_" + nowTime + ".xls");// 组装附件名称和格式
			agentService.exportAgentList(agentDetailInfoBO, outputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

			return null;
	}

	@RequestMapping(value = "/query_agent_detail_show", method = RequestMethod.GET)
	public String QueryAgentDetailIno(HttpServletRequest request,
			@ModelAttribute("agentDetailForm") AgentDetailForm agentDetailForm) {
		String memberId = request.getParameter("memberId");

		if (StringUtils.isBlank(memberId)) {
			return PAGE_PRE + "query_agent_list";
		}

			AgentDetailInfoBO agentDetailInfoBO = agentService.getAgtDetailInfoById(memberId);
		if (agentDetailInfoBO == null) {
			return PAGE_PRE + "query_agent_list";
		}
		agentDetailForm.setMemberId(agentDetailInfoBO.getMemberId());
		agentDetailForm.setOpdate(agentDetailInfoBO.getOpdate());
		agentDetailForm.setAgentShortName(agentDetailInfoBO.getAgentShortName());
		agentDetailForm.setAgentName(agentDetailInfoBO.getAgentName());
		agentDetailForm.setRegName(agentDetailInfoBO.getRegName());
		agentDetailForm.setRegCode(agentDetailInfoBO.getRegCode());
		agentDetailForm.setTaxCode(agentDetailInfoBO.getTaxCode());
		agentDetailForm.setRegAddr(agentDetailInfoBO.getRegAddr());
		agentDetailForm.setLicType(agentDetailInfoBO.getLicType());
		agentDetailForm.setLicAmt(agentDetailInfoBO.getLicAmt());
		agentDetailForm.setLegalName(agentDetailInfoBO.getLegalName());
		agentDetailForm.setIdno(agentDetailInfoBO.getIdno());
		agentDetailForm.setContactName(agentDetailInfoBO.getContactName());
		agentDetailForm.setContactMobile(agentDetailInfoBO.getContactMobile());
		agentDetailForm.setContactAddr(agentDetailInfoBO.getContactAddr());
		agentDetailForm.setEmail(agentDetailInfoBO.getEmail());
		agentDetailForm.setMemberStat(agentDetailInfoBO.getMemberStat());

		return PAGE_PRE + "agent_detail_form";

	}

	@RequestMapping(value = "/query_agent_detail_edit", method = RequestMethod.GET)
	public String EditAgentDetailIno(HttpServletRequest request,
			@ModelAttribute("agentDetailForm") AgentDetailForm agentDetailForm) {
		String memberId = request.getParameter("memberId");
		if (StringUtils.isBlank(memberId)) {
			return PAGE_PRE + "query_agent_list";
		}
		AgentDetailInfoBO agentDetailInfoBO = agentService.getAgtDetailInfoById(memberId);
		if (agentDetailInfoBO == null) {
			return PAGE_PRE + "query_agent_list";
		}
		agentDetailForm.setMemberId(agentDetailInfoBO.getMemberId());
		agentDetailForm.setOpdate(agentDetailInfoBO.getOpdate());
		agentDetailForm.setAgentShortName(agentDetailInfoBO.getAgentShortName());
		agentDetailForm.setAgentName(agentDetailInfoBO.getAgentName());
		agentDetailForm.setRegName(agentDetailInfoBO.getRegName());
		agentDetailForm.setRegCode(agentDetailInfoBO.getRegCode());
		agentDetailForm.setTaxCode(agentDetailInfoBO.getTaxCode());
		agentDetailForm.setRegAddr(agentDetailInfoBO.getRegAddr());
		agentDetailForm.setLicType(agentDetailInfoBO.getLicType());
		agentDetailForm.setLicAmt(agentDetailInfoBO.getLicAmt());
		agentDetailForm.setLegalName(agentDetailInfoBO.getLegalName());
		agentDetailForm.setIdno(agentDetailInfoBO.getIdno());
		agentDetailForm.setContactName(agentDetailInfoBO.getContactName());
		agentDetailForm.setContactMobile(agentDetailInfoBO.getContactMobile());
		agentDetailForm.setContactAddr(agentDetailInfoBO.getContactAddr());
		agentDetailForm.setEmail(agentDetailInfoBO.getEmail());
		agentDetailForm.setMemberStat(agentDetailInfoBO.getMemberStat());

		return PAGE_PRE + "edit_agent_detail";

	}

	@ResponseBody
	@RequestMapping(value = "/update_agent_detail_info", method = RequestMethod.POST)
	public Map updateAgentDetailInfo(@ModelAttribute("agentDetailForm") AgentDetailForm agentDetailForm) {
		Map resultMap = new HashMap();

		AgentDetailInfoBO agentDetailInfoBO = new AgentDetailInfoBO();
		agentDetailInfoBO.setMemberId(agentDetailForm.getMemberId());
		agentDetailInfoBO.setOpdate(agentDetailForm.getOpdate());
		agentDetailInfoBO.setAgentShortName(agentDetailForm.getAgentShortName());
		agentDetailInfoBO.setAgentName(agentDetailForm.getAgentName());
		agentDetailInfoBO.setRegName(agentDetailForm.getRegName());
		agentDetailInfoBO.setRegCode(agentDetailForm.getRegCode());
		agentDetailInfoBO.setTaxCode(agentDetailForm.getTaxCode());
		agentDetailInfoBO.setRegAddr(agentDetailForm.getRegAddr());
		agentDetailInfoBO.setLicType(agentDetailForm.getLicType());
		agentDetailInfoBO.setLicAmt(agentDetailForm.getLicAmt());
		agentDetailInfoBO.setLegalName(agentDetailForm.getLegalName());
		agentDetailInfoBO.setIdno(agentDetailForm.getIdno());
		agentDetailInfoBO.setContactName(agentDetailForm.getContactName());
		agentDetailInfoBO.setContactMobile(agentDetailForm.getContactMobile());
		agentDetailInfoBO.setContactAddr(agentDetailForm.getContactAddr());
		agentDetailInfoBO.setEmail(agentDetailForm.getEmail());
		// agentDetailInfoBO.setMemberStat(agentDetailForm.getMemberStat());

		resultMap = agentService.updateAgentDetails(agentDetailInfoBO);
		return resultMap;
	}

	@RequestMapping(value = "/ShowAgentFeeEditPage", method = RequestMethod.GET)
	public String agentFeeEditPage(@ModelAttribute("agentQueryForm") AgentQueryForm agentQueryForm,
			HttpServletRequest request) {
		AgentDetailInfoBO agentDetailInfoBO = new AgentDetailInfoBO();
		if (!StringUtils.isBlank(agentQueryForm.getAgentShortName())) {
			agentDetailInfoBO.setAgentShortName(agentQueryForm.getAgentShortName());
		}
		if (!StringUtils.isBlank(agentQueryForm.getMemberId())) {
			agentDetailInfoBO.setMemberId(agentQueryForm.getMemberId());
		}
		if (!StringUtils.isBlank(agentQueryForm.getAgentLevel())) {
			agentDetailInfoBO.setAgentLevel(agentQueryForm.getAgentLevel());
		}
		if (!StringUtils.isBlank(agentQueryForm.getMemberStat())) {
			agentDetailInfoBO.setMemberStat(agentQueryForm.getMemberStat());
		}
		String pageCurrent = agentQueryForm.getPageCurrent();
		String pageSize = agentQueryForm.getPageSize();

		agentDetailInfoBO.setPageCurrent(Integer.valueOf(pageCurrent));
		agentDetailInfoBO.setPageSize(Integer.valueOf(pageSize));

		Pagination<AgentDetailInfoBO> agentDetailInfoBOPagination = agentService.queryAgentList(agentDetailInfoBO);

		agentQueryForm.setPagination(agentDetailInfoBOPagination);
		return PAGE_PRE + "agent_fee_list";
	}

	@RequestMapping(value = "/query_agents_fees", method = RequestMethod.POST)
	public String QueryAgentsFees(HttpServletRequest request,
			@ModelAttribute("agentQueryForm") AgentQueryForm agentQueryForm) {
		agentQueryForm.setShowOemSelect("0");
		AgentDetailInfoBO agentDetailInfoBO = new AgentDetailInfoBO();
		if (!StringUtils.isBlank(agentQueryForm.getAgentShortName())) {
			agentDetailInfoBO.setAgentShortName(agentQueryForm.getAgentShortName());
		}
		if (!StringUtils.isBlank(agentQueryForm.getMemberId())) {
			agentDetailInfoBO.setMemberId(agentQueryForm.getMemberId());
		}
		if (!StringUtils.isBlank(agentQueryForm.getAgentLevel())) {
			agentDetailInfoBO.setAgentLevel(agentQueryForm.getAgentLevel());
		}
		if (!StringUtils.isBlank(agentQueryForm.getMemberStat())) {
			agentDetailInfoBO.setMemberStat(agentQueryForm.getMemberStat());
		}
		String pageCurrent = agentQueryForm.getPageCurrent();
		String pageSize = agentQueryForm.getPageSize();

		agentDetailInfoBO.setPageCurrent(Integer.valueOf(pageCurrent));
		agentDetailInfoBO.setPageSize(Integer.valueOf(pageSize));

		Pagination<AgentDetailInfoBO> agentDetailInfoBOPagination = agentService.queryAgentList(agentDetailInfoBO);

		agentQueryForm.setPagination(agentDetailInfoBOPagination);

		return PAGE_PRE + "agent_fee_list";
	}

	@RequestMapping(value = "/query_agent_fee_edit")
	public String editMerFees(HttpServletRequest request, @ModelAttribute("agentFeeForm") AgentFeeForm agentFeeForm) {
		String memberId = request.getParameter("memberId").trim();
		AgentFeeBO agentFeeBO = agentService.getFeeForAgent(memberId);

		agentFeeForm.setAgentId(memberId);
		agentFeeForm.setFee01(agentFeeBO.getCode01());
		agentFeeForm.setFee01L(agentFeeBO.getCode01L());
		agentFeeForm.setFee02(agentFeeBO.getCode02());
		agentFeeForm.setFee03(agentFeeBO.getCode03());
		agentFeeForm.setFee04(agentFeeBO.getCode04());
		agentFeeForm.setFee05(agentFeeBO.getCode05());
		agentFeeForm.setFee06(agentFeeBO.getCode06());
		agentFeeForm.setFee07(agentFeeBO.getCode07());
		agentFeeForm.setFee08(agentFeeBO.getCode08());

		return PAGE_PRE + "edit_agent_fee";
	}

	@ResponseBody
	@RequestMapping(value = "/update_agtFee_setting", method = RequestMethod.POST)
	public Map updateMerFeeSetting(@ModelAttribute("agentFeeForm") AgentFeeForm agentFeeForm, HttpSession session,
			HttpServletRequest request) {
		Map resultMap = new HashMap();

		UserBO user = (UserBO) session.getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
		String userName = user.getUsrName();

		if (StringUtils.isBlank(agentFeeForm.getAgentId())) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "更新失败!");
			return resultMap;
		}
		if (StringUtils.isBlank(agentFeeForm.getFee01()) && StringUtils.isBlank(agentFeeForm.getFee01L())) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "请填写借记卡费率信息!");
			return resultMap;
		}
		if (StringUtils.isBlank(agentFeeForm.getFee02()) ) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "请填写贷记卡费率信息!");
			return resultMap;
		}
		if (StringUtils.isBlank(agentFeeForm.getFee07()) && StringUtils.isBlank(agentFeeForm.getFee08())) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "请填写提现费率信息!");
			return resultMap;
		}
		AgentFeeBO agentFeeBO = new AgentFeeBO();
		agentFeeBO.setAgentId(agentFeeForm.getAgentId());
		agentFeeBO.setCode01(agentFeeForm.getFee01());
		agentFeeBO.setCode01L(agentFeeForm.getFee01L());
		agentFeeBO.setCode02(agentFeeForm.getFee02());
		agentFeeBO.setCode03(agentFeeForm.getFee03());
		agentFeeBO.setCode04(agentFeeForm.getFee04());
		agentFeeBO.setCode05(agentFeeForm.getFee05());
		agentFeeBO.setCode06(agentFeeForm.getFee06());
		agentFeeBO.setCode07(agentFeeForm.getFee07());
		agentFeeBO.setCode08(agentFeeForm.getFee08());

		resultMap = agentService.updateFeeSetttingsForAgent(agentFeeBO, agentFeeForm.getAgentId(), userName);
		return resultMap;
	}

}
