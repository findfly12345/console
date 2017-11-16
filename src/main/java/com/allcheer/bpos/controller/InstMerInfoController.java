package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblMerChannelPreInfoDO;
import com.allcheer.bpos.entity.TblMerChannelPreInfoDOExample;
import com.allcheer.bpos.form.MerChannelPreInfoForm;
import com.allcheer.bpos.mapper.TblMerChannelPreInfoDOMapper;
import com.allcheer.bpos.util.BjuiAjaxReturnStatusUtil;
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
@RequestMapping("instmerinfo")
public class InstMerInfoController {

	@Autowired
	TblMerChannelPreInfoDOMapper tblMerChannelPreInfoDOMapper;

	@RequiresAuthentication
	@RequestMapping(value = "gopage")
	public String goToLoginPage(HttpServletRequest request) {
		MerChannelPreInfoForm merChannelPreInfoForm = new MerChannelPreInfoForm();

		int pageCurrent = 1;
		int pageSize = 100;

		TblMerChannelPreInfoDOExample tblMerChannelPreInfoDOExample = new TblMerChannelPreInfoDOExample();
		tblMerChannelPreInfoDOExample.createCriteria();
		int transSize = tblMerChannelPreInfoDOMapper.countByExample(tblMerChannelPreInfoDOExample);

		Pagination<TblMerChannelPreInfoDO> pagination = new Pagination<TblMerChannelPreInfoDO>(transSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblMerChannelPreInfoDO> tblMerChannelPreInfoDOList = tblMerChannelPreInfoDOMapper.selectByExample(tblMerChannelPreInfoDOExample);
		pagination.addResult(tblMerChannelPreInfoDOList);
		request.setAttribute("pageUser", pagination);
		request.setAttribute("channelpreiinfo", merChannelPreInfoForm);

		return "instmerinfo/routPage";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("merChannelPreInfoForm") MerChannelPreInfoForm merChannelPreInfoForm, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		String instId = null;
		String merId = null;
		String instMerId = null;
		String channelMerId = null;
		String channelStat = null;

		if (!StringUtils.isBlank(merChannelPreInfoForm.getInstId())) {
			instId = merChannelPreInfoForm.getInstId();
		}

		if (!StringUtils.isBlank(merChannelPreInfoForm.getMerId())) {
			merId = merChannelPreInfoForm.getMerId();
		}
		
		if (!StringUtils.isBlank(merChannelPreInfoForm.getInstMerId())) {
			instMerId = merChannelPreInfoForm.getInstMerId();
		}

		if (!StringUtils.isBlank(merChannelPreInfoForm.getChannelMerId())) {
			channelMerId = merChannelPreInfoForm.getChannelMerId();
		}

		if (!StringUtils.isBlank(merChannelPreInfoForm.getChannelStat())) {
			channelStat = merChannelPreInfoForm.getChannelStat();
		}

		int pageCurrent = Integer.parseInt(merChannelPreInfoForm.getPageCurrent());
		int pageSize = Integer.parseInt(merChannelPreInfoForm.getPageSize());

		int transSize = tblMerChannelPreInfoDOMapper.countByCust(instId, merId, instMerId,channelMerId,channelStat);

		Pagination<TblMerChannelPreInfoDO> pagination = new Pagination<TblMerChannelPreInfoDO>(transSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblMerChannelPreInfoDO> tblRouteControlList = tblMerChannelPreInfoDOMapper.selectByCust(instId, merId, instMerId,channelMerId,channelStat);
		pagination.addResult(tblRouteControlList);
		request.setAttribute("pageUser", pagination);
		request.setAttribute("channelpreiinfo", merChannelPreInfoForm);

		
		return "instmerinfo/routPage";
	}

	@RequestMapping("delete/{merId:.*}")
	@RequiresAuthentication
	public String deleteParam(@PathVariable String merId, HttpServletRequest request, HttpServletResponse response) {
		TblMerChannelPreInfoDOExample funcExample = new TblMerChannelPreInfoDOExample();
		funcExample.createCriteria().andMerIdEqualTo(merId);

		int i = tblMerChannelPreInfoDOMapper.deleteByExample(funcExample);
		String message = "";

		if (i > 0 ) {
			message = BjuiAjaxReturnStatusUtil.succeedDel();
		} else {
			message = BjuiAjaxReturnStatusUtil.error("未找到该记录");
		}
		BjuiAjaxReturnStatusUtil.retJson(response, message);
		return null;
	}

}