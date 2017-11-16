package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblBtsPosInfoDO;
import com.allcheer.bpos.form.GateBankPosInfoForm;
import com.allcheer.bpos.service.GateBankPosInfoService;
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
@RequestMapping("gatekey")
public class GateKeyInfoController {


	@Autowired
	GateBankPosInfoService gateBankPosInfoService;

	@RequestMapping("page")
	@RequiresAuthentication
	public String doPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		GateBankPosInfoForm rout = new GateBankPosInfoForm();

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = gateBankPosInfoService.countByExample(rout);

		Pagination<TblBtsPosInfoDO> page = new Pagination<TblBtsPosInfoDO>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblBtsPosInfoDO> userList = gateBankPosInfoService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);
		request.setAttribute("rout", rout);

		return "gatekey/routPage";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("rout") GateBankPosInfoForm rout, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = gateBankPosInfoService.countByExample(rout);

		Pagination<TblBtsPosInfoDO> page = new Pagination<TblBtsPosInfoDO>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblBtsPosInfoDO> userList = gateBankPosInfoService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);

		return "gatekey/routPage";
	}

	@RequestMapping("addpage")
	@RequiresAuthentication
	public String doAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		TblBtsPosInfoDO rout = new TblBtsPosInfoDO();
		request.setAttribute("rout", rout);

			return "gatekey/addPage";
	}

	@RequestMapping("delete/{id:.*}/{mer:.*}/{term:.*}")
	@RequiresAuthentication
	public String deleteUser(@PathVariable String id,@PathVariable String mer,@PathVariable String term, HttpServletRequest request, HttpServletResponse response) throws BposException {
		GateBankPosInfoForm form = new GateBankPosInfoForm();
		form.setGateId(id);
		form.setPosMerId(mer);
		form.setPosTermId(term);
		gateBankPosInfoService.deleteByExample(form);

		String message = BjuiAjaxReturnStatusUtil.succeedDel();
		BjuiAjaxReturnStatusUtil.retJson(response, message);

		return null;
	}
	
	@RequestMapping("add")
	@RequiresAuthentication
	public String doAdd(@ModelAttribute("rout") TblBtsPosInfoDO rout, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws BposException {
		gateBankPosInfoService.insert(rout);

		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);

		return null;
	}


	@RequestMapping("updatepage/{name:.*}/{merid:.*}/{termid:.*}")
	@RequiresAuthentication
	public String doUpdateUserPage(@PathVariable String name,@PathVariable String merid,@PathVariable String termid, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		GateBankPosInfoForm form = new GateBankPosInfoForm();
		form.setGateId(name);
		form.setPosMerId(merid);
		form.setPosTermId(termid);
		List<TblBtsPosInfoDO> gaterout = gateBankPosInfoService.selectByExample(form);
		request.setAttribute("rout", gaterout.get(0));

		return "gatekey/updatePage";
	}
	
	@RequestMapping("update")
	@RequiresAuthentication
	public String doUpdate(@ModelAttribute("rout") TblBtsPosInfoDO rout, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws BposException {
		gateBankPosInfoService.updateByExample(rout);
		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		return null;
	}
}
