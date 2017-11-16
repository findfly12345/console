package com.allcheer.bpos.controller;

import com.allcheer.bpos.form.CheckConfigForm;
import com.allcheer.bpos.service.CheckConfigService;
import com.allcheer.bpos.util.BjuiAjaxReturnStatusUtil;
import com.allcheer.bpos.util.BposException;
import com.allcheer.bpos.util.Pagination;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
@RequestMapping("checkconfig")
public class CheckConfigController {


	@Autowired
	CheckConfigService checkConfigService;

	@RequestMapping("page")
	@RequiresAuthentication
	public String doPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		CheckConfigForm rout = new CheckConfigForm();

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = checkConfigService.count(rout);

		Pagination<CheckConfigForm> page = new Pagination<CheckConfigForm>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<CheckConfigForm> userList = checkConfigService.select(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);
		request.setAttribute("rout", rout);

		return "checkconfig/routPage";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("rout") CheckConfigForm rout, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = checkConfigService.count(rout);

		Pagination<CheckConfigForm> page = new Pagination<CheckConfigForm>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<CheckConfigForm> userList = checkConfigService.select(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);

		return "checkconfig/routPage";
	}

	@RequestMapping("addpage")
	@RequiresAuthentication
	public String doAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		CheckConfigForm rout = new CheckConfigForm();
		request.setAttribute("rout", rout);

		return "checkconfig/addPage";
	}

	@RequestMapping("delete/{id:.*}")
	@RequiresAuthentication
	public String deleteUser(@PathVariable String id, HttpServletRequest request, HttpServletResponse response)
			throws BposException {
		CheckConfigForm form = new CheckConfigForm();
		form.setGateId(id);

		checkConfigService.delete(form);

		String message = BjuiAjaxReturnStatusUtil.succeedDel();
		BjuiAjaxReturnStatusUtil.retJson(response, message);
		return null;
	}

	@RequestMapping("add")
	@RequiresAuthentication
	public String doAdd(@ModelAttribute("rout") CheckConfigForm rout, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws BposException {
		checkConfigService.insert(rout);

		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		return null;
	}

	@RequestMapping("updatepage/{name:.*}")
	@RequiresAuthentication
	public String doUpdateUserPage(@PathVariable String name,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		CheckConfigForm form = new CheckConfigForm();
		form.setGateId(name);

		List<CheckConfigForm> gaterout = checkConfigService.select(form);
		request.setAttribute("rout", gaterout.get(0));

		return "checkconfig/updatePage";
	}

	@RequestMapping("update")
	@RequiresAuthentication
	public String doUpdate(@ModelAttribute("rout") CheckConfigForm rout, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws BposException {
		checkConfigService.update(rout);
		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);

		return null;
	}
}
