package com.allcheer.bpos.controller;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.TblAgentInfoDo;
import com.allcheer.bpos.entity.TblBankPosInfoDO;
import com.allcheer.bpos.entity.TblBtsInstRouteDO;
import com.allcheer.bpos.entity.TblGateRoute;
import com.allcheer.bpos.entity.vo.TblAgentMerInfoVO;
import com.allcheer.bpos.form.GateBankPosInfoForm;
import com.allcheer.bpos.form.GateRouteForm;
import com.allcheer.bpos.form.MerInfoForm;
import com.allcheer.bpos.service.GateRouteService;
import com.allcheer.bpos.service.MerAgentService;
import com.allcheer.bpos.util.*;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Controller
@RequestMapping("/gateroute")
public class GateRouteController {

	private Logger logger = LoggerFactory.getLogger(GateRouteController.class);

	@Autowired
	GateRouteService gateRouteService;
	@Autowired
	MerAgentService merAgentService;

	@RequestMapping("page")
	@RequiresAuthentication
	public String doPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("网关查询界面");
		GateRouteForm route = new GateRouteForm();

		int pageCurrent = Integer.parseInt(route.getPageCurrent());
		int pageSize = Integer.parseInt(route.getPageSize());
		int userSize = gateRouteService.countByExample(route);

		Pagination<TblGateRoute> page = new Pagination<TblGateRoute>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblGateRoute> userList = gateRouteService.selectByExample(route);
		page.addResult(userList);
		request.setAttribute("pageUser", page);
		request.setAttribute("route", route);

		return "gateroute/routePage";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("route") GateRouteForm route, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		int pageCurrent = Integer.parseInt(route.getPageCurrent());
		int pageSize = Integer.parseInt(route.getPageSize());
		int userSize = gateRouteService.countByExample(route);

		Pagination<TblGateRoute> page = new Pagination<TblGateRoute>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblGateRoute> userList = gateRouteService.selectByExample(route);
		page.addResult(userList);
		request.setAttribute("pageUser", page);

		return "gateroute/routePage";
	}

	@RequestMapping("addpage")
	@RequiresAuthentication
	public String doAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		TblGateRoute route = new TblGateRoute();
		request.setAttribute("route", route);
		return "gateroute/addPage";
	}

	@RequestMapping("add")
	@RequiresAuthentication
	public String doAdd(@ModelAttribute("route") TblGateRoute route, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws BposException {
		logger.debug("新增网关： " + route.getGateId());

		int count = gateRouteService.insert(route);
		if (count == 1) {
			String succeed = BjuiAjaxReturnStatusUtil.succeed();
			BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		} else {
			String message = BjuiAjaxReturnStatusUtil.error("新增失败");
			BjuiAjaxReturnStatusUtil.retJson(response, message);

		}

		return null;
	}

	@RequestMapping("delete/{gateId:.*}")
	@RequiresAuthentication
	public String deleteUser(@PathVariable String gateId, HttpServletRequest request, HttpServletResponse response) {

		TblGateRoute gaterout = gateRouteService.selectByPrimaryKey(gateId);
		String message = "";
		if (gaterout != null) {
			gateRouteService.deleteByPrimaryKey(gateId);
			message = BjuiAjaxReturnStatusUtil.succeedDel();
		} else {
			message = BjuiAjaxReturnStatusUtil.error("未找到该记录");
		}
		BjuiAjaxReturnStatusUtil.retJson(response, message);
		return null;
	}

	@RequestMapping("updatepage/{gateId:.*}")
	@RequiresAuthentication
	public String doUpdateUserPage(@PathVariable String gateId, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		TblGateRoute gateroute = gateRouteService.selectByPrimaryKey(gateId);
		request.setAttribute("route", gateroute);
		return "gateroute/updatePage";
	}

	@RequestMapping("update")
	@RequiresAuthentication
	public String doUpdate(@ModelAttribute("route") TblGateRoute route, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws BposException {
		gateRouteService.updateByPrimaryKey(route);
		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		return null;
	}

	@RequestMapping(value = "/gate_route_setting_list")
	public ModelAndView gateRouteSetting(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm,
			ModelAndView modelAndView) {

		Map<String, String> queryMap = new HashMap<>();
		queryMap = Bean2Map.beanToMapNoFill(merInfoForm);
		queryMap.put("termIsNotNull", "123");
		queryMap.put("merStat", Constant.AUDIT_OK);

		List<TblAgentInfoDo> tblAgentInfoDoList = merAgentService.findAgentInfos(new TblAgentInfoDo());

		Pagination<TblAgentMerInfoVO> tblAgentMerInfoVOPagination = null;
		tblAgentMerInfoVOPagination = merAgentService.queryAgentMerInfoList(queryMap);

		queryMap.put("termIsNotNull", "123");
		queryMap.put("merStat", Constant.AUDIT_IN);

		Pagination<TblAgentMerInfoVO> tblAgentMerInfoVOPaginationAdd = null;

		tblAgentMerInfoVOPaginationAdd = merAgentService.queryAgentMerInfoList(queryMap);

		if (tblAgentMerInfoVOPaginationAdd.getList().size() > 0) {
			tblAgentMerInfoVOPagination.addResult(tblAgentMerInfoVOPaginationAdd.getList());
		}
		merInfoForm.setPagination(tblAgentMerInfoVOPagination);
		merInfoForm.setTblAgentInfoDos(tblAgentInfoDoList);
		modelAndView.setViewName("/gateroute/gate_route_setting_list");

		return modelAndView;
	}

	@RequestMapping(value = "/go_bind_gate_route")
	public String gotoBindTerminalInfo(HttpServletRequest request,
			@ModelAttribute("gateBankPosInfoForm") GateBankPosInfoForm gateBankPosInfoForm) {
		String instMerId = request.getParameter("instMerId").trim();
		String instMerTermId = request.getParameter("instMerTermId").trim();
		gateBankPosInfoForm.setInstMerId(instMerId);
		gateBankPosInfoForm.setInstMerTermId(instMerTermId);
		return "/gateroute/bind_gate_route";
	}

	@ResponseBody
	@RequestMapping(value = "/bind_gate_route")
	public Map bindTerminalMer(@ModelAttribute("gateBankPosInfoForm") GateBankPosInfoForm gateBankPosInfoForm,
			HttpSession session) {
		UserBO user = (UserBO) session.getAttribute(SystemConstant.USER_SESSION_KEY);
		String username = user.getUsrName();
		Map resultMap = new HashMap();

		if (StringUtils.isBlank(gateBankPosInfoForm.getInstMerId())
				&& StringUtils.isBlank(gateBankPosInfoForm.getInstMerTermId())) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "商户ID以及终端ID为空");
			return resultMap;
		}
		int instRouteSeq = gateRouteService.getPlatFormGateRouteId();
		int i = gateRouteService.updateAgentMerTermById(gateBankPosInfoForm.getInstMerId(),
				gateBankPosInfoForm.getInstMerTermId(), "1");
		if (i == 0) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "查找不到关联的商户及终端");
			return resultMap;
		}
		TblBtsInstRouteDO tblBtsInstRouteDO = new TblBtsInstRouteDO();
		tblBtsInstRouteDO.setRouteSeq(instRouteSeq);
		tblBtsInstRouteDO.setInstCode("00000000");
		tblBtsInstRouteDO.setInstMerId(gateBankPosInfoForm.getInstMerId());
		tblBtsInstRouteDO.setInstMerTermId(gateBankPosInfoForm.getInstMerTermId());
		tblBtsInstRouteDO.setGateId(gateBankPosInfoForm.getGateId());
		tblBtsInstRouteDO.setBankMerId(gateBankPosInfoForm.getPosMerId());
		tblBtsInstRouteDO.setBankTermId(gateBankPosInfoForm.getPosTermId());
		tblBtsInstRouteDO.setStat("Y");
		i = gateRouteService.insertInstRoute(tblBtsInstRouteDO);

		TblBankPosInfoDO tblBankPosInfoDO = new TblBankPosInfoDO();
		tblBankPosInfoDO.setGateId(gateBankPosInfoForm.getGateId());
		tblBankPosInfoDO.setPosMerId(gateBankPosInfoForm.getPosMerId());
		tblBankPosInfoDO.setPosTermId(gateBankPosInfoForm.getPosTermId());
		gateRouteService.insertBankPosInfo(tblBankPosInfoDO);

		Map<String, String> repMap = gateRouteService.auditPass(gateBankPosInfoForm.getInstMerId(), username);

		if ("MER NOT FOUND".equals(repMap.get("result"))) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "商户未找到");
		} else if ("SUCCESS".equals(repMap.get("result"))) {
			resultMap.put("statusCode", 200);
			resultMap.put("message", "商户网关路由配置成功");
		} else {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "商户网关路由配置失败");
		}

		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "/unbind_gate_route")
	public Map unbindGateRoute(HttpServletRequest request, HttpSession session) {
		Map resultMap = new HashMap();
		UserBO user = (UserBO) session.getAttribute(SystemConstant.USER_SESSION_KEY);
		String username = user.getUsrName();

		String instMerId = request.getParameter("instMerId").trim();
		String instMerTermId = request.getParameter("instMerTermId").trim();
		int i = gateRouteService.updateAgentMerTermById(instMerId, instMerTermId, "0");

		if (i == 0) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "查找不到关联的商户及终端");
			return resultMap;
		}
		resultMap = gateRouteService.unbindMerGateRoute(instMerId, instMerTermId);

		if (resultMap.get("statusCode").equals(200)) {
			Map<String, String> repMap = gateRouteService.auditRevert(instMerId, username);
			if ("MER NOT FOUND".equals(repMap.get("result"))) {
				resultMap.put("statusCode", 300);
				resultMap.put("message", "商户未找到");
			} else if ("SUCCESS".equals(repMap.get("result"))) {
				resultMap.put("statusCode", 200);
				resultMap.put("message", "商户网关路由解绑成功");
			} else {
				resultMap.put("statusCode", 300);
				resultMap.put("message", "商户网关路由解绑失败");
			}
		}

		return resultMap;
	}

	@RequestMapping(value = "/show_bind_route_detail")
	public String showBindRouteDetail(HttpServletRequest request,
			@ModelAttribute("gateBankPosInfoForm") GateBankPosInfoForm gateBankPosInfoForm) {
		Map resultmap = new HashMap();
		String instMerId = request.getParameter("instMerId").trim();
		String instMerTermId = request.getParameter("instMerTermId").trim();

		Map map = new HashMap();
		map = gateRouteService.getBindRouteDetail(instMerId, instMerTermId);
		gateBankPosInfoForm.setGateId(map.get("gateId").toString());
		gateBankPosInfoForm.setPosMerId(map.get("posMerId").toString());
		gateBankPosInfoForm.setPosTermId(map.get("posTermId").toString());

		return "/gateroute/show_gate_route";
	}

}
