package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblFeeInfoDO;
import com.allcheer.bpos.form.GateFeeInfoForm;
import com.allcheer.bpos.service.GateFeeInfoService;
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
@RequestMapping("gatefee")
public class GateFeeInfoController {


	@Autowired
	GateFeeInfoService gateFeeInfoService;

	@RequestMapping("page")
	@RequiresAuthentication
	public String doPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		GateFeeInfoForm rout = new GateFeeInfoForm();

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = gateFeeInfoService.countByExample(rout);
		Pagination<TblFeeInfoDO> page = new Pagination<TblFeeInfoDO>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);
		List<TblFeeInfoDO> userList = gateFeeInfoService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);

		request.setAttribute("rout", rout);
		return "gatefee/routPage";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("rout") GateFeeInfoForm rout, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = gateFeeInfoService.countByExample(rout);

		Pagination<TblFeeInfoDO> page = new Pagination<TblFeeInfoDO>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblFeeInfoDO> userList = gateFeeInfoService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);

		return "gatefee/routPage";
	}

	@RequestMapping("addpage")
	@RequiresAuthentication
	public String doAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		TblFeeInfoDO rout = new TblFeeInfoDO();
		request.setAttribute("rout", rout);
		return "gatefee/addPage";
	}

	@RequestMapping("delete/{id:.*}/{mer:.*}/{term:.*}")
	@RequiresAuthentication
	public String deleteUser(@PathVariable String id,@PathVariable String mer,@PathVariable String term, HttpServletRequest request, HttpServletResponse response) throws BposException {
		GateFeeInfoForm form = new GateFeeInfoForm();
		form.setGateId(id);
		form.setPosMerId(mer);
		form.setPosTermId(term);
		gateFeeInfoService.deleteByExample(form);

		String message = BjuiAjaxReturnStatusUtil.succeedDel();
		BjuiAjaxReturnStatusUtil.retJson(response, message);

		return null;
	}
	
	@RequestMapping("add")
	@RequiresAuthentication
	public String doAdd(@ModelAttribute("rout") TblFeeInfoDO rout, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws BposException {
		gateFeeInfoService.insert(rout);

		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);

		return null;
	}


	@RequestMapping("updatepage/{name:.*}/{merid:.*}/{termid:.*}")
	@RequiresAuthentication
	public String doUpdateUserPage(@PathVariable String name,@PathVariable String merid,@PathVariable String termid, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		GateFeeInfoForm form = new GateFeeInfoForm();
		form.setGateId(name);
		form.setPosMerId(merid);
		form.setPosTermId(termid);

		List<TblFeeInfoDO> gaterout = gateFeeInfoService.selectByExample(form);
		request.setAttribute("rout", gaterout.get(0));

		return "gatefee/updatePage";
	}
	
	@RequestMapping("update")
	@RequiresAuthentication
	public String doUpdate(@ModelAttribute("rout") TblFeeInfoDO rout, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws BposException {
		gateFeeInfoService.updateByExample(rout);

		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);

		return null;
	}
}
