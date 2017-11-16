package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblRiskTimeInf;
import com.allcheer.bpos.entity.TblRiskTimeInfExample;
import com.allcheer.bpos.form.RiskTimeInfForm;
import com.allcheer.bpos.mapper.TblRiskTimeInfMapper;
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
@RequestMapping("risktime")
public class RiskTimeInfController {

	@Autowired
	TblRiskTimeInfMapper tblRiskTimeInfMapper;

	@RequiresAuthentication
	@RequestMapping(value = "gopage")
	public String goToLoginPage(HttpServletRequest request) {
		RiskTimeInfForm riskTimeInfForm = new RiskTimeInfForm();

		int pageCurrent = 1;
		int pageSize = 100;

		TblRiskTimeInfExample tblRiskTimeInfExample = new TblRiskTimeInfExample();
		tblRiskTimeInfExample.createCriteria();
		int transSize = tblRiskTimeInfMapper.countByExample(tblRiskTimeInfExample);

		Pagination<TblRiskTimeInf> pagination = new Pagination<TblRiskTimeInf>(transSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblRiskTimeInf> tblRouteControlList = tblRiskTimeInfMapper.selectByExample(tblRiskTimeInfExample);
		pagination.addResult(tblRouteControlList);
		request.setAttribute("pageUser", pagination);
		request.setAttribute("risktime", riskTimeInfForm);

		return "risktime/risk_time_inf";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("risktime") RiskTimeInfForm risktime, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		String riskSeq = null;
		String riskType = null;

		if (!StringUtils.isBlank(risktime.getRiskSeq())) {
			riskSeq = risktime.getRiskSeq();
		}

		if (!StringUtils.isBlank(risktime.getRiskType())) {
			riskType = risktime.getRiskSeq();
		}

		int pageCurrent = Integer.parseInt(risktime.getPageCurrent());
		int pageSize = Integer.parseInt(risktime.getPageSize());

		int transSize = tblRiskTimeInfMapper.countByCust(riskSeq, riskType);

		Pagination<TblRiskTimeInf> pagination = new Pagination<TblRiskTimeInf>(transSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblRiskTimeInf> tblRouteControlList = tblRiskTimeInfMapper.selectByCust(riskSeq, riskType);
		pagination.addResult(tblRouteControlList);
		request.setAttribute("pageUser", pagination);
		request.setAttribute("risktime", risktime);

		
		return "risktime/risk_time_inf";
	}

	@RequestMapping("delete/{riskSeq:.*}")
	@RequiresAuthentication
	public String deleteParam(@PathVariable String riskSeq, HttpServletRequest request, HttpServletResponse response) {
		TblRiskTimeInfExample funcExample = new TblRiskTimeInfExample();
		funcExample.createCriteria().andRiskSeqEqualTo(riskSeq);

		int i = tblRiskTimeInfMapper.deleteByExample(funcExample);
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
	@RequestMapping("updatepage/{riskSeq:.*}")
	@RequiresAuthentication
	public String doUpdateUserPage(@PathVariable String riskSeq, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		TblRiskTimeInfExample funcExample = new TblRiskTimeInfExample();
		funcExample.createCriteria().andRiskSeqEqualTo(riskSeq);
		List<TblRiskTimeInf> pfuncList = tblRiskTimeInfMapper.selectByExample(funcExample);

		int i;
		TblRiskTimeInf tblRiskTimeInf = new TblRiskTimeInf();
		for (i = 0; i < pfuncList.size(); i++) {
			tblRiskTimeInf.setRiskSeq(pfuncList.get(i).getRiskSeq());
			tblRiskTimeInf.setRiskType(pfuncList.get(i).getRiskType());
			tblRiskTimeInf.setRiskDetail(pfuncList.get(i).getRiskDetail());
			tblRiskTimeInf.setBeginTime(pfuncList.get(i).getBeginTime());
			tblRiskTimeInf.setEndTime(pfuncList.get(i).getEndTime());
			tblRiskTimeInf.setStat(pfuncList.get(i).getStat());
			tblRiskTimeInf.setRiskRemark(pfuncList.get(i).getRiskRemark());
		}

		request.setAttribute("risktime", tblRiskTimeInf);

		return "risktime/updateRiskTime";
	}

	/*
	 * 风控-修改更新配置参数
	 */
	@RequestMapping("alterUpdateRiskTime")
	@RequiresAuthentication
	public String alterUpdateParamInfo(@ModelAttribute("riskTimeInfForm") RiskTimeInfForm riskTimeInfForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String message = "";

		String riskSeq = request.getParameter("riskSeq");
		String riskType = request.getParameter("riskType");
		String riskDetail = request.getParameter("riskDetail");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String stat = request.getParameter("stat");
		String riskRemark = request.getParameter("riskRemark");
		

		TblRiskTimeInfExample funcExample = new TblRiskTimeInfExample();
		funcExample.createCriteria().andRiskSeqEqualTo(riskSeq);

		TblRiskTimeInf tblRiskTimeInf = new TblRiskTimeInf();
		tblRiskTimeInf.setRiskSeq(riskSeq);
		tblRiskTimeInf.setRiskType(riskType);
		tblRiskTimeInf.setRiskDetail(riskDetail);
		tblRiskTimeInf.setBeginTime(beginTime);
		tblRiskTimeInf.setEndTime(endTime);
		tblRiskTimeInf.setStat(stat);
		tblRiskTimeInf.setRiskRemark(riskRemark);

		int i = tblRiskTimeInfMapper.updateByExample(tblRiskTimeInf, funcExample);

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
	public String doAdd(@ModelAttribute("risktime") TblRiskTimeInf risktime, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws BposException {

		int count = tblRiskTimeInfMapper.insert(risktime);

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
		TblRiskTimeInf tblRiskTimeInf = new TblRiskTimeInf();
		request.setAttribute("risktime", tblRiskTimeInf);
		return "risktime/addRiskTime";
	}
}
