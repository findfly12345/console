package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblBtsGateMerInfoDO;
import com.allcheer.bpos.form.GateMerInfoForm;
import com.allcheer.bpos.service.GateMerInfoService;
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
@RequestMapping("gatemer")
public class GateMerInfoController {


	@Autowired
	GateMerInfoService gateMerInfoService;

	@RequestMapping("page")
	@RequiresAuthentication
	public String doPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		GateMerInfoForm rout = new GateMerInfoForm();

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = gateMerInfoService.countByExample(rout);

		Pagination<TblBtsGateMerInfoDO> page = new Pagination<TblBtsGateMerInfoDO>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblBtsGateMerInfoDO> userList = gateMerInfoService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);
		request.setAttribute("rout", rout);

		return "gatemer/routPage";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("rout") GateMerInfoForm rout, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = gateMerInfoService.countByExample(rout);

		Pagination<TblBtsGateMerInfoDO> page = new Pagination<TblBtsGateMerInfoDO>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblBtsGateMerInfoDO> userList = gateMerInfoService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);

		return "gatemer/routPage";
	}

	@RequestMapping("addpage")
	@RequiresAuthentication
	public String doAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		TblBtsGateMerInfoDO rout = new TblBtsGateMerInfoDO();
		request.setAttribute("rout", rout);

		return "gatemer/addPage";
	}

	@RequestMapping("delete/{id:.*}/{mer:.*}/{term:.*}")
	@RequiresAuthentication
	public String deleteUser(@PathVariable String id,@PathVariable String mer,@PathVariable String term, HttpServletRequest request, HttpServletResponse response) throws BposException {
		GateMerInfoForm form = new GateMerInfoForm();
		form.setGateId(id);
		form.setPosMerId(mer);
		form.setPosTermId(term);
		gateMerInfoService.deleteByExample(form);
		String message = BjuiAjaxReturnStatusUtil.succeedDel();
		BjuiAjaxReturnStatusUtil.retJson(response, message);
		return null;
	}
	
	@RequestMapping("add")
	@RequiresAuthentication
	public String doAdd(@ModelAttribute("rout") TblBtsGateMerInfoDO rout, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws BposException {
		gateMerInfoService.insert(rout);
		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		return null;
	}


	@RequestMapping("updatepage/{name:.*}/{merid:.*}/{termid:.*}")
	@RequiresAuthentication
	public String doUpdateUserPage(@PathVariable String name,@PathVariable String merid,@PathVariable String termid, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		GateMerInfoForm form = new GateMerInfoForm();
		form.setGateId(name);
		form.setPosMerId(merid);
		form.setPosTermId(termid);

		List<TblBtsGateMerInfoDO> gaterout = gateMerInfoService.selectByExample(form);
		request.setAttribute("rout", gaterout.get(0));

		return "gatemer/updatePage";
	}
	
	@RequestMapping("update")
	@RequiresAuthentication
	public String doUpdate(@ModelAttribute("rout") TblBtsGateMerInfoDO rout, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws BposException {
		gateMerInfoService.updateByExample(rout);
		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		return null;
	}
}
