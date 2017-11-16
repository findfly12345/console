package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblBlackCard;
import com.allcheer.bpos.entity.TblBlackCardExample;
import com.allcheer.bpos.form.BlackCardForm;
import com.allcheer.bpos.mapper.TblBlackCardMapper;
import com.allcheer.bpos.util.*;
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
@RequestMapping("blackcard")
public class BlackCardController {

	@Autowired
	TblBlackCardMapper tblBlackCardMapper;

	@RequiresAuthentication
	@RequestMapping(value = "gopage")
	public String goToLoginPage(HttpServletRequest request) {

		BlackCardForm blackCardForm = new BlackCardForm();

		int pageCurrent = 1;
		int pageSize = 100;

		TblBlackCardExample tblBlackCardExample = new TblBlackCardExample();
		tblBlackCardExample.createCriteria();
		int transSize = tblBlackCardMapper.countByExample(tblBlackCardExample);

		Pagination<TblBlackCard> pagination = new Pagination<TblBlackCard>(transSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblBlackCard> tblRouteControlList = tblBlackCardMapper.selectByExample(tblBlackCardExample);
		pagination.addResult(tblRouteControlList);
		request.setAttribute("pageUser", pagination);
		request.setAttribute("blackcard", blackCardForm);

		return "black/blackCard";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("blackcard") BlackCardForm blackcard, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

			String cardNo = null;
		TblBlackCardExample tblBlackCardExample = new TblBlackCardExample();

		if (!StringUtils.isBlank(blackcard.getCardNo())) {
			cardNo = blackcard.getCardNo();
			tblBlackCardExample.createCriteria().andCardNoEqualTo(cardNo);
		} else {
			tblBlackCardExample.createCriteria();
		}

		int pageCurrent = Integer.parseInt(blackcard.getPageCurrent());
		int pageSize = Integer.parseInt(blackcard.getPageSize());

		int transSize = tblBlackCardMapper.countByExample(tblBlackCardExample);

		Pagination<TblBlackCard> pagination = new Pagination<TblBlackCard>(transSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblBlackCard> tblBlackCardList = tblBlackCardMapper.selectByExample(tblBlackCardExample);

		pagination.addResult(tblBlackCardList);
		request.setAttribute("pageUser", pagination);
		request.setAttribute("blackcard", blackcard);

		return "black/blackCard";
	}

	@RequestMapping("delete/{cardNo:.*}")
	@RequiresAuthentication
	public String deleteParam(@PathVariable String cardNo, HttpServletRequest request, HttpServletResponse response) {

		TblBlackCardExample funcExample = new TblBlackCardExample();
		funcExample.createCriteria().andCardNoEqualTo(cardNo);

		int i = tblBlackCardMapper.deleteByExample(funcExample);
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
	@RequestMapping("updatepage/{cardNo:.*}")
	@RequiresAuthentication
	public String doUpdateUserPage(@PathVariable String cardNo, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		TblBlackCardExample funcExample = new TblBlackCardExample();
		funcExample.createCriteria().andCardNoEqualTo(cardNo);
		List<TblBlackCard> pfuncList = tblBlackCardMapper.selectByExample(funcExample);

		int i;
		TblBlackCard tblBlackCard = new TblBlackCard();
		for (i = 0; i < pfuncList.size(); i++) {
			tblBlackCard.setCardNo(cardNo);
			tblBlackCard.setCreateTime(DateUtil.getCurrentDate());
		}

		request.setAttribute("blackcard", tblBlackCard);

		return "black/updateBlackCard";
	}

	/*
	 * 风控-修改更新配置参数
	 */
	@RequestMapping("alterUpdateBlackCard")
	@RequiresAuthentication
	public String alterUpdateParamInfo(@ModelAttribute("blackCardForm") BlackCardForm blackCardForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String message = "";
		String cardNo = request.getParameter("cardNo");

		TblBlackCardExample funcExample = new TblBlackCardExample();
		funcExample.createCriteria().andCardNoEqualTo(cardNo);

		TblBlackCard tblBlackCard = new TblBlackCard();
		tblBlackCard.setCardNo(cardNo);
		tblBlackCard.setCreateTime(DateUtil.getCurrentDate());

		int i = tblBlackCardMapper.updateByExample(tblBlackCard, funcExample);

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
	public String doAdd(@ModelAttribute("blackcard") TblBlackCard blackcard, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws BposException {

		blackcard.setCreateTime(DateUtil.getCurrentDate());
		int count = tblBlackCardMapper.insert(blackcard);

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

		TblBlackCard tblBlackCard = new TblBlackCard();
		request.setAttribute("blackcard", tblBlackCard);

			return "black/addBlackCard";
	}
}
