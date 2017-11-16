package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblRiskCtrlRegl;
import com.allcheer.bpos.form.RiskCtrlReglListForm;
import com.allcheer.bpos.service.MonitorRealm;
import com.allcheer.bpos.service.RiskCtrlReglService;
import com.allcheer.bpos.util.BjuiAjaxReturnStatusUtil;
import com.allcheer.bpos.util.BposException;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("riskctrlregl")
public class RiskCtrlReglController {


	@Autowired
	RiskCtrlReglService riskCtrlReglService;

	@RequestMapping("page")
	@RequiresAuthentication
	public String doPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		RiskCtrlReglListForm rout = new RiskCtrlReglListForm();

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = riskCtrlReglService.countByExample(rout);

		Pagination<TblRiskCtrlRegl> page = new Pagination<TblRiskCtrlRegl>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblRiskCtrlRegl> userList = riskCtrlReglService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);
		request.setAttribute("rout", rout);

		return "risk/riskCtrlRegl";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("rout") RiskCtrlReglListForm rout, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = riskCtrlReglService.countByExample(rout);

		Pagination<TblRiskCtrlRegl> page = new Pagination<TblRiskCtrlRegl>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblRiskCtrlRegl> userList = riskCtrlReglService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);

		return "risk/riskCtrlRegl";
	}

	@RequestMapping("addpage")
	@RequiresAuthentication
	public String doAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		TblRiskCtrlRegl rout = new TblRiskCtrlRegl();
		request.setAttribute("rout", rout);
		return "risk/addRiskCtrlRegl";
	}

	@RequestMapping("delete/{riskNum:.*}/{paramNum:.*}")
	@RequiresAuthentication
	public String deleteUser(@PathVariable String riskNum, @PathVariable String paramNum, HttpServletRequest request,
			HttpServletResponse response) throws BposException {
		RiskCtrlReglListForm form = new RiskCtrlReglListForm();
		form.setRiskNum(riskNum);
		form.setParamNum(paramNum);

		riskCtrlReglService.deleteByExample(form);
		String message = BjuiAjaxReturnStatusUtil.succeedDel();
		BjuiAjaxReturnStatusUtil.retJson(response, message);
		return null;
	}

	@RequestMapping("add")
	@RequiresAuthentication
	public String doAdd(@ModelAttribute("rout") TblRiskCtrlRegl rout, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws BposException {
		// 获取session信息
		Subject currentUser = SecurityUtils.getSubject();
		MonitorRealm.ShiroUser shiroUser = (MonitorRealm.ShiroUser) currentUser.getPrincipal();
		String userName = shiroUser.getUser().getUsrName();

		rout.setModOper(userName);
		rout.setModDate(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());

		riskCtrlReglService.insert(rout);
		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		return null;
	}

	@RequestMapping("updatepage/{riskNum:.*}/{paramNum:.*}")
	@RequiresAuthentication
	public String doUpdateUserPage(@PathVariable String riskNum, @PathVariable String paramNum, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		RiskCtrlReglListForm form = new RiskCtrlReglListForm();
		form.setRiskNum(riskNum);
		form.setParamNum(paramNum);

		List<TblRiskCtrlRegl> gaterout = riskCtrlReglService.selectByExample(form);
		request.setAttribute("rout", gaterout.get(0));
		return "risk/updateRiskCtrlRegl";
	}

	@RequestMapping("update")
	@RequiresAuthentication
	public String doUpdate(@ModelAttribute("rout") TblRiskCtrlRegl rout, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws BposException {
		// 获取session信息
		Subject currentUser = SecurityUtils.getSubject();
		MonitorRealm.ShiroUser shiroUser = (MonitorRealm.ShiroUser) currentUser.getPrincipal();
		String userName = shiroUser.getUser().getUsrName();

		rout.setModOper(userName);
		rout.setModDate(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());

		riskCtrlReglService.updateByExample(rout);
		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		return null;
	}

}
