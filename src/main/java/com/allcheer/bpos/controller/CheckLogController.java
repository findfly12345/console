package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.GateBankCheckLog;
import com.allcheer.bpos.form.CheckLogForm;
import com.allcheer.bpos.service.CheckLogService;
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
@RequestMapping("checklog")
public class CheckLogController {


	@Autowired
	CheckLogService checkLogService;

	@RequestMapping("page")
	@RequiresAuthentication
	public String doPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		CheckLogForm rout = new CheckLogForm();

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = checkLogService.countByExample(rout);
		Pagination<GateBankCheckLog> page = new Pagination<GateBankCheckLog>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);
		List<GateBankCheckLog> userList = checkLogService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);

		request.setAttribute("rout", rout);
		return "checklog/routPage";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("rout") CheckLogForm rout, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = checkLogService.countByExample(rout);

		Pagination<GateBankCheckLog> page = new Pagination<GateBankCheckLog>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<GateBankCheckLog> userList = checkLogService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);

		return "checklog/routPage";
	}


	@RequestMapping("delete/{id:.*}/{date:.*}")
	@RequiresAuthentication
	public String deleteUser(@PathVariable String id,@PathVariable String date, HttpServletRequest request, HttpServletResponse response) throws BposException {
		CheckLogForm form = new CheckLogForm();
		form.setGateId(id);
		form.setBankCheckDate(date);
		checkLogService.deleteByExample(form);

		String message = BjuiAjaxReturnStatusUtil.succeedDel();
		BjuiAjaxReturnStatusUtil.retJson(response, message);

		return null;
	}
	
}
