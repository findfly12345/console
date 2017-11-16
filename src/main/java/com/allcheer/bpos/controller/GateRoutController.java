package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblBtsGateRoutDO;
import com.allcheer.bpos.form.GateRoutForm;
import com.allcheer.bpos.service.GateRoutService;
import com.allcheer.bpos.util.BjuiAjaxReturnStatusUtil;
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
@RequestMapping("gaterout")
public class GateRoutController {

	@Autowired
	GateRoutService gateRoutService;
	
	@RequestMapping("page")
	@RequiresAuthentication
	public String doPage(HttpSession session, HttpServletRequest request,HttpServletResponse response){
		GateRoutForm rout = new GateRoutForm();
		
		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = gateRoutService.countByExample(rout);

		Pagination<TblBtsGateRoutDO> page = new Pagination<TblBtsGateRoutDO>(userSize,pageCurrent,pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblBtsGateRoutDO> userList = gateRoutService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);
		request.setAttribute("rout", rout);

		return "gaterout/routPage";
	}
	
	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("rout") GateRoutForm rout,HttpSession session, HttpServletRequest request,HttpServletResponse response){
		
		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = gateRoutService.countByExample(rout);

		Pagination<TblBtsGateRoutDO> page = new Pagination<TblBtsGateRoutDO>(userSize,pageCurrent,pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblBtsGateRoutDO> userList = gateRoutService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);
		
		return "gaterout/routPage";
	}
	
	@RequestMapping("addpage")
	@RequiresAuthentication
	public String doAddUserPage(HttpSession session, HttpServletRequest request,HttpServletResponse response){
		GateRoutForm rout = new GateRoutForm();
		request.setAttribute("rout", rout);
		return "gaterout/addPage";
	}
	
	@RequestMapping("add")
	@RequiresAuthentication
	public String doAdd(@ModelAttribute("rout") TblBtsGateRoutDO rout,HttpSession session, HttpServletRequest request,HttpServletResponse response){
		gateRoutService.insert(rout);
		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		return null;
	}
	
	@RequestMapping("delete/{name:.*}")
	@RequiresAuthentication
	public String deleteUser(@PathVariable String name,HttpServletRequest request,HttpServletResponse response){
		TblBtsGateRoutDO gaterout = gateRoutService.selectByGateId(name);
		String message = "";
		if(gaterout != null){
			GateRoutForm rout = new GateRoutForm();
			rout.setGateId(name);
			gateRoutService.deleteByExample(rout);
			message = BjuiAjaxReturnStatusUtil.succeedDel();
		}else{
			message = BjuiAjaxReturnStatusUtil.error("未找到该记录");
		}

		BjuiAjaxReturnStatusUtil.retJson(response, message);
		return null;
	}
	
	@RequestMapping("updatepage/{name:.*}")
	@RequiresAuthentication
	public String doUpdateUserPage(@PathVariable String name,HttpSession session, HttpServletRequest request,HttpServletResponse response){
		TblBtsGateRoutDO gaterout = gateRoutService.selectByGateId(name);
		request.setAttribute("rout", gaterout);
		return "gaterout/updatePage";
	}
	
	@RequestMapping("update")
	@RequiresAuthentication
	public String doUpdate(@ModelAttribute("rout") TblBtsGateRoutDO rout,HttpSession session, HttpServletRequest request,HttpServletResponse response){
		gateRoutService.updateByExample(rout);
		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		return null;
	}
	
}
