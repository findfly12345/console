package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.GateBankCheckDataDO;
import com.allcheer.bpos.form.GateBankCheckDataForm;
import com.allcheer.bpos.service.GateBankCheckDataService;
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
@RequestMapping("checkdata")
public class CheckDataController {

	@Autowired
	GateBankCheckDataService gateBankCheckDataService;

	@RequestMapping("page")
	@RequiresAuthentication
	public String doPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		GateBankCheckDataForm rout = new GateBankCheckDataForm();

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = gateBankCheckDataService.countByExample(rout);

		Pagination<GateBankCheckDataDO> page = new Pagination<GateBankCheckDataDO>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<GateBankCheckDataDO> userList = gateBankCheckDataService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);
		request.setAttribute("rout", rout);

		return "checkdata/routPage";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("rout") GateBankCheckDataForm rout, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = gateBankCheckDataService.countByExample(rout);

		Pagination<GateBankCheckDataDO> page = new Pagination<GateBankCheckDataDO>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<GateBankCheckDataDO> userList = gateBankCheckDataService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);

		return "checkdata/routPage";
	}

	@RequestMapping("updatepage/{name:.*}/{seq:.*}/{date:.*}/{stat:.*}")
	@RequiresAuthentication
	public String doUpdateUserPage(@PathVariable String name, @PathVariable String seq, @PathVariable String date,
			@PathVariable String stat, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws BposException {
		GateBankCheckDataDO form = new GateBankCheckDataDO();
		form.setGateId(name);
		form.setSeqId(seq);
		form.setTransDate(date);
		form.setCheckDate(date);
		form.setCheckFlag(stat);
		gateBankCheckDataService.update(form);

		String message = BjuiAjaxReturnStatusUtil.succeedDel();
		BjuiAjaxReturnStatusUtil.retJson(response, message);

		return null;
	}
}
