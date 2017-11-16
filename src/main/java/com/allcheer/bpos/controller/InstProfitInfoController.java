package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblInstProfitInfo;
import com.allcheer.bpos.form.InstProfitInfoForm;
import com.allcheer.bpos.service.InstProfitInfoService;
import com.allcheer.bpos.service.InstitutionService;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("instprofit")
public class InstProfitInfoController {

	private static final Logger logger = LoggerFactory.getLogger(InstProfitInfoController.class);

	@Autowired
	private InstProfitInfoService instProfitInfoService;

	@Autowired
	private InstitutionService institutionService;

	@RequiresAuthentication
	@RequestMapping(value = "gopage")
	public String goToLoginPage(HttpServletRequest request) {
		logger.info("机构分润");

		InstProfitInfoForm instProfitForm = new InstProfitInfoForm();

		int pageCurrent = Integer.parseInt(instProfitForm.getPageCurrent());
		int pageSize = Integer.parseInt(instProfitForm.getPageSize());
		int transSize = instProfitInfoService.getInstProfitListCount(instProfitForm);

		Pagination<TblInstProfitInfo> page = new Pagination<TblInstProfitInfo>(transSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		instProfitForm.setStartTransDateTime(DateUtil.date2String(new Date(), "yyyy-MM-dd"));
		instProfitForm.setEndTransDateTime(DateUtil.date2String(new Date(), "yyyy-MM-dd"));

		List<TblInstProfitInfo> transList = instProfitInfoService.getInstProfitList(instProfitForm);
		page.addResult(transList);
		List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
		request.setAttribute("tblBtsInstDOList", tblBtsInstDOList);
		request.setAttribute("pageUser", page);
		request.setAttribute("instProfitForm", instProfitForm);
		
		return "instsum/profitPage";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("instProfitForm") InstProfitInfoForm instProfitForm, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		int pageCurrent = Integer.parseInt(instProfitForm.getPageCurrent());
		int pageSize = Integer.parseInt(instProfitForm.getPageSize());
		int transSize = instProfitInfoService.getInstProfitListCount(instProfitForm);
		Pagination<TblInstProfitInfo> page = new Pagination<TblInstProfitInfo>(transSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);
		List<TblInstProfitInfo> transList = instProfitInfoService.getInstProfitList(instProfitForm);
		page.addResult(transList);
		List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();

		request.setAttribute("tblBtsInstDOList", tblBtsInstDOList);
		request.setAttribute("pageUser", page);

		return "instsum/profitPage";
	}

}
