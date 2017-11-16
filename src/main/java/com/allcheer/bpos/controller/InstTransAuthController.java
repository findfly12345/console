package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblInstTransAuth;
import com.allcheer.bpos.entity.TblInstTransAuthExample;
import com.allcheer.bpos.form.InstTransAuthForm;
import com.allcheer.bpos.mapper.TblInstTransAuthMapper;
import com.allcheer.bpos.util.BjuiAjaxReturnStatusUtil;
import com.allcheer.bpos.util.BposException;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
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
@RequestMapping("insttransauth")
public class InstTransAuthController {

	@Autowired
	TblInstTransAuthMapper tblInstTransAuthMapper;

	@RequiresAuthentication
	@RequestMapping(value = "gopage")
	public String goToLoginPage(HttpServletRequest request) {
		InstTransAuthForm instTransAuthForm = new InstTransAuthForm();

		int pageCurrent = 1;
		int pageSize = 100;

		TblInstTransAuthExample TblInstTransAuthExample = new TblInstTransAuthExample();
		TblInstTransAuthExample.createCriteria();
		int transSize = tblInstTransAuthMapper.countByExample(TblInstTransAuthExample);

		Pagination<TblInstTransAuth> pagination = new Pagination<TblInstTransAuth>(transSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblInstTransAuth> tblRouteControlList = tblInstTransAuthMapper.selectByExample(TblInstTransAuthExample);
		pagination.addResult(tblRouteControlList);
		request.setAttribute("pageUser", pagination);
		request.setAttribute("insttransauth", instTransAuthForm);

		return "insttransauth/instTransAuth";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("insttransauth") InstTransAuthForm insttransauth, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		String instCode = null;

		TblInstTransAuthExample TblInstTransAuthExample = new TblInstTransAuthExample();		
		if (!StringUtils.isBlank(insttransauth.getInstCode())) {
			instCode = insttransauth.getInstCode();
			TblInstTransAuthExample.createCriteria().andInstCodeEqualTo(instCode);
		}else
		{
			TblInstTransAuthExample.createCriteria();
		}

		int pageCurrent = Integer.parseInt(insttransauth.getPageCurrent());
		int pageSize = Integer.parseInt(insttransauth.getPageSize());
		int transSize = tblInstTransAuthMapper.countByExample(TblInstTransAuthExample);

		Pagination<TblInstTransAuth> pagination = new Pagination<TblInstTransAuth>(transSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblInstTransAuth> tblRouteControlList = tblInstTransAuthMapper.selectByExample(TblInstTransAuthExample);
		pagination.addResult(tblRouteControlList);
		request.setAttribute("pageUser", pagination);
		request.setAttribute("insttransauth", insttransauth);

		return "insttransauth/instTransAuth";
	}

	
	@RequestMapping("delete/{instCode:.*}")
	@RequiresAuthentication
	public String deleteInstKey(@PathVariable String instCode, HttpServletRequest request, HttpServletResponse response) {
		TblInstTransAuthExample funcExample = new TblInstTransAuthExample();
		funcExample.createCriteria().andInstCodeEqualTo(instCode);

		int i = tblInstTransAuthMapper.deleteByExample(funcExample);
		String message = "";

		if (i == 1) {
			message = BjuiAjaxReturnStatusUtil.succeedDel();
		} else {
			message = BjuiAjaxReturnStatusUtil.error("未找到该记录");
		}
		BjuiAjaxReturnStatusUtil.retJson(response, message);
		return null;
	}

	/*
	 * 风控-修改配置参数
	 */
	@RequestMapping("updatepage/{instCode:.*}")
	@RequiresAuthentication
	public String doUpdateUserPage(@PathVariable String instCode, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		TblInstTransAuthExample funcExample = new TblInstTransAuthExample();
		funcExample.createCriteria().andInstCodeEqualTo(instCode);
		List<TblInstTransAuth> pfuncList = tblInstTransAuthMapper.selectByExample(funcExample);

		int i;
		TblInstTransAuth tblInstTransAuth = new TblInstTransAuth();
		for (i = 0; i < pfuncList.size(); i++) {
			tblInstTransAuth.setInstCode(pfuncList.get(i).getInstCode());
			tblInstTransAuth.setPosStat(pfuncList.get(i).getPosStat());
			tblInstTransAuth.setChatStat(pfuncList.get(i).getChatStat());
			tblInstTransAuth.setAllipayStat(pfuncList.get(i).getAllipayStat());
			tblInstTransAuth.setAuthStat(pfuncList.get(i).getAuthStat());
			tblInstTransAuth.setRemark(pfuncList.get(i).getRemark());
		}

		request.setAttribute("insttransauth", tblInstTransAuth);

		return "insttransauth/updateInstTransAuth";
	}

	/*
	 * 风控-修改更新配置参数
	 */
	@RequestMapping("alterUpdateInstTransAuth")
	@RequiresAuthentication
	public String alterUpdateInstTransAuth(@ModelAttribute("instTransAuthForm") InstTransAuthForm instTransAuthForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String message = null;
		
		String instCode = request.getParameter("instCode");
		String posStat = request.getParameter("posStat");
		String chatStat = request.getParameter("chatStat");
		String allipayStat = request.getParameter("allipayStat");
		String authStat = request.getParameter("authStat");
		String remark = request.getParameter("remark");
		
		TblInstTransAuthExample funcExample = new TblInstTransAuthExample();
		funcExample.createCriteria().andInstCodeEqualTo(instCode);

		TblInstTransAuth tblInstTransAuth = new TblInstTransAuth();
		tblInstTransAuth.setInstCode(instCode);
		tblInstTransAuth.setPosStat(posStat);
		tblInstTransAuth.setChatStat(chatStat);
		tblInstTransAuth.setAllipayStat(allipayStat);
		tblInstTransAuth.setAuthStat(authStat);
		tblInstTransAuth.setRemark(remark);

		int i = tblInstTransAuthMapper.updateByExample(tblInstTransAuth, funcExample);

		if (i == 1) {
			message = BjuiAjaxReturnStatusUtil.succeed("保存成功");
		} else {
			message = BjuiAjaxReturnStatusUtil.error("保存失败");
		}
		BjuiAjaxReturnStatusUtil.retJson(response, message);

		return null;
	}

	@RequestMapping("add")
	@RequiresAuthentication
	public String doAdd(@ModelAttribute("insttransauth") TblInstTransAuth insttransauth, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws BposException {
		int count = tblInstTransAuthMapper.insert(insttransauth);

		if (count == 1) {
			String succeed = BjuiAjaxReturnStatusUtil.succeed();
			BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		} else {
			String message = BjuiAjaxReturnStatusUtil.error("新增失败");
			BjuiAjaxReturnStatusUtil.retJson(response, message);

		}
		return null;
	}
	
	@RequestMapping("addpage")
	@RequiresAuthentication
	public String doAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		TblInstTransAuth tblInstTransAuth = new TblInstTransAuth();
		request.setAttribute("insttransauth", tblInstTransAuth);
		return "insttransauth/addInstTransAuth";
	}
}
