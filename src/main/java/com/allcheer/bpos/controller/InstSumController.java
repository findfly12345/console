package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.InstAccountingSummaryDO;
import com.allcheer.bpos.form.InstAccountSumForm;
import com.allcheer.bpos.service.InstAccountSumService;
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
@RequestMapping("instsum")
public class InstSumController {


	@Autowired
	InstAccountSumService instAccountSumService;

	@RequestMapping("page")
	@RequiresAuthentication
	public String doPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		InstAccountSumForm rout = new InstAccountSumForm();

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = instAccountSumService.countByExample(rout);

		Pagination<InstAccountingSummaryDO> page = new Pagination<InstAccountingSummaryDO>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<InstAccountingSummaryDO> userList = instAccountSumService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);
		request.setAttribute("rout", rout);

		return "instsum/routPage";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("rout") InstAccountSumForm rout, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = instAccountSumService.countByExample(rout);

		Pagination<InstAccountingSummaryDO> page = new Pagination<InstAccountingSummaryDO>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<InstAccountingSummaryDO> userList = instAccountSumService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);
		request.setAttribute("rout", rout);

		return "instsum/routPage";
	}


	@RequestMapping("delete/{id:.*}/{date:.*}")
	@RequiresAuthentication
	public String deleteUser(@PathVariable String id,@PathVariable String date, HttpServletRequest request, HttpServletResponse response) throws BposException {
		InstAccountSumForm form = new InstAccountSumForm();
		form.setInstId(id);
		form.setAccountingDate(date);
		instAccountSumService.deleteByExample(form);
		String message = BjuiAjaxReturnStatusUtil.succeedDel();
		BjuiAjaxReturnStatusUtil.retJson(response, message);
		return null;
	}
	
}
