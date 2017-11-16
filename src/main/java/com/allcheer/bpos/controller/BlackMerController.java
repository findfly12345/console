package com.allcheer.bpos.controller;

import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.TblBlackAccDo;
import com.allcheer.bpos.entity.TblBlackAccDoExample;
import com.allcheer.bpos.entity.TblBlackMer;
import com.allcheer.bpos.entity.TblBlackMerExample;
import com.allcheer.bpos.form.BlackMerForm;
import com.allcheer.bpos.mapper.TblBlackAccDoMapper;
import com.allcheer.bpos.mapper.TblBlackMerMapper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("blackmer")
public class BlackMerController {

	@Autowired
	TblBlackMerMapper tblBlackMerMapper;

	@Autowired
	private TblBlackAccDoMapper tblBlackAccDoMapper;

	@RequiresAuthentication
	@RequestMapping(value = "gopage")
	public String goToLoginPage(HttpServletRequest request) {

		TblBlackAccDo tblBlackAccDo = new TblBlackAccDo();
		int pageCurrent = 1;
		int pageSize = 100;

		TblBlackAccDoExample tblBlackAccDoExample = new TblBlackAccDoExample();
		tblBlackAccDoExample.createCriteria();

		int transSize = tblBlackAccDoMapper.countByExample(tblBlackAccDoExample);

		Pagination<TblBlackAccDo> pagination = new Pagination<TblBlackAccDo>(transSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblBlackAccDo> tblBlackAccDoList = tblBlackAccDoMapper.selectByExample(tblBlackAccDoExample);
		pagination.addResult(tblBlackAccDoList);
		request.setAttribute("pageUser", pagination);
		request.setAttribute("tblBlackAccDo", tblBlackAccDo);

		return "black/blackMer";
	}


	@RequiresAuthentication
	@RequestMapping(value = "gopagemer")
	public String goToLoginPageMer(HttpServletRequest request) {

        BlackMerForm blackMerForm = new BlackMerForm();

		int pageCurrent = 1;
		int pageSize = 100;

		TblBlackMerExample tblBlackMerExample = new TblBlackMerExample();
		tblBlackMerExample.createCriteria();
		int transSize = tblBlackMerMapper.countByExample(tblBlackMerExample);

		Pagination<TblBlackMer> pagination = new Pagination<TblBlackMer>(transSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblBlackMer> tblRouteControlList = tblBlackMerMapper.selectByExample(tblBlackMerExample);

		pagination.addResult(tblRouteControlList);
		request.setAttribute("pageUser", pagination);
		request.setAttribute("blackmer", blackMerForm);

		return "black/blackCard";
	}

    @RequestMapping("searchmer")
    @RequiresAuthentication
    public String doSelect(@ModelAttribute("blackmer") BlackMerForm blackmer, HttpSession session,
                           HttpServletRequest request, HttpServletResponse response) {
        String merNo = null;
        TblBlackMerExample tblBlackMerExample = new TblBlackMerExample();
        if (!StringUtils.isBlank(blackmer.getMerNo())) {
            merNo = blackmer.getMerNo();
            tblBlackMerExample.createCriteria().andMerNoEqualTo(merNo);
        } else {
            tblBlackMerExample.createCriteria();
        }

        int pageCurrent = Integer.parseInt(blackmer.getPageCurrent());
        int pageSize = Integer.parseInt(blackmer.getPageSize());

        int transSize = tblBlackMerMapper.countByExample(tblBlackMerExample);

        Pagination<TblBlackMer> pagination = new Pagination<TblBlackMer>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblBlackMer> tblBlackMerList = tblBlackMerMapper.selectByExample(tblBlackMerExample);

        pagination.addResult(tblBlackMerList);
        request.setAttribute("pageUser", pagination);
        request.setAttribute("blackmer", blackmer);

        return "black/blackCard";
    }

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("tblBlackAccDo") TblBlackAccDo tblBlackAccDo, HttpSession session,
						   HttpServletRequest request, HttpServletResponse response) {
		String merNo = null;
		TblBlackAccDoExample tblBlackAccDoExample = new TblBlackAccDoExample();

		if (!StringUtils.isBlank(tblBlackAccDo.getMerId())) {
			tblBlackAccDoExample.createCriteria().andMerIdEqualTo(tblBlackAccDo.getMerId());
		} else {
			tblBlackAccDoExample.createCriteria();
		}

		int pageCurrent = Integer.parseInt(tblBlackAccDo.getPageCurrent());
		int pageSize = Integer.parseInt(tblBlackAccDo.getPageSize());

		int transSize = tblBlackAccDoMapper.countByExample(tblBlackAccDoExample);
		Pagination<TblBlackAccDo> pagination = new Pagination<TblBlackAccDo>(transSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);
		List<TblBlackAccDo> tblBlackAccDoList = tblBlackAccDoMapper.selectByExample(tblBlackAccDoExample);

		pagination.addResult(tblBlackAccDoList);
		request.setAttribute("pageUser", pagination);
		request.setAttribute("blackmer", tblBlackAccDo);

		return "black/blackMer";
	}

   @RequestMapping("deletemer/{merNo:.*}")
    @RequiresAuthentication
    public String deleteParammer(@PathVariable String merNo, HttpServletRequest request, HttpServletResponse response) {

        TblBlackMerExample funcExample = new TblBlackMerExample();
        funcExample.createCriteria().andMerNoEqualTo(merNo);

        int i = tblBlackMerMapper.deleteByExample(funcExample);
        String message = "";

        if (i == 1) {
            message = BjuiAjaxReturnStatusUtil.succeedDel();
        } else {
            message = BjuiAjaxReturnStatusUtil.error("未找到该记录");
        }
        BjuiAjaxReturnStatusUtil.retJson(response, message);

        return null;
    }

	@RequestMapping("delete/{merId:.*}")
	@RequiresAuthentication
	public String deleteParam(@PathVariable String merId, HttpServletRequest request, HttpServletResponse response) {

		TblBlackAccDoExample tblBlackAccDoExample = new TblBlackAccDoExample();
		tblBlackAccDoExample.createCriteria().andMerIdEqualTo(merId);
		int i = tblBlackAccDoMapper.deleteByExample(tblBlackAccDoExample);

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
	@RequestMapping("updatepage/{merNo:.*}")
	@RequiresAuthentication
	public String doUpdateUserPage(@PathVariable String merNo, HttpSession session, HttpServletRequest request,
								   HttpServletResponse response) {

		TblBlackMerExample funcExample = new TblBlackMerExample();
		funcExample.createCriteria().andMerNoEqualTo(merNo);
		List<TblBlackMer> pfuncList = tblBlackMerMapper.selectByExample(funcExample);

		int i;
		TblBlackMer tblBlackMer = new TblBlackMer();
		for (i = 0; i < pfuncList.size(); i++) {
			tblBlackMer.setMerNo(merNo);
			tblBlackMer.setCreateTime(DateUtil.getCurrentDate());
		}

		request.setAttribute("blackmer", tblBlackMer);

		return "black/updateBlackMer";
	}

	/*
     * 风控-修改更新配置参数
     */
	@RequestMapping("alterUpdateBlackMer")
	@RequiresAuthentication
	public String alterUpdateParamInfo(@ModelAttribute("blackMerForm") BlackMerForm blackMerForm,
									   HttpServletRequest request, HttpServletResponse response) throws Exception {

		String message = "";
		String merNo = request.getParameter("merNo");

		TblBlackMerExample funcExample = new TblBlackMerExample();
		funcExample.createCriteria().andMerNoEqualTo(merNo);

		TblBlackMer tblBlackMer = new TblBlackMer();
		tblBlackMer.setMerNo(merNo);
		tblBlackMer.setCreateTime(DateUtil.getCurrentDate());

		int i = tblBlackMerMapper.updateByExample(tblBlackMer, funcExample);

		if (i == 1) {
			message = BjuiAjaxReturnStatusUtil.succeed("保存成功");
		} else {
			message = BjuiAjaxReturnStatusUtil.error("保存失败");
		}
		BjuiAjaxReturnStatusUtil.retJson(response, message);

		return null;
	}

   @RequestMapping("addmer")
    @RequiresAuthentication
    public String doAddmer(@ModelAttribute("blackmer") TblBlackMer blackmer, HttpSession session,
                        HttpServletRequest request, HttpServletResponse response) throws BposException {

        blackmer.setCreateTime(DateUtil.getCurrentDate());
        int count = tblBlackMerMapper.insert(blackmer);

        if (count == 1) {
            String succeed = BjuiAjaxReturnStatusUtil.succeed();
            BjuiAjaxReturnStatusUtil.retJson(response, succeed);
        } else {
            String message = BjuiAjaxReturnStatusUtil.error("新增失败");
            BjuiAjaxReturnStatusUtil.retJson(response, message);

        }
        return null;
    }



	@RequestMapping("add")
	@RequiresAuthentication
	public String doAdd(@ModelAttribute("tblBlackAccDo") TblBlackAccDo tblBlackAccDo, HttpSession session,
						HttpServletRequest request, HttpServletResponse response) throws BposException {

			Map<String, Object> resultMap = new HashMap<>();
		UserBO user = (UserBO) session.getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
		String userName = user.getUsrName();
		tblBlackAccDo.setCreateTime(DateUtil.getCurrentDate());
		tblBlackAccDo.setCreateUser(userName);

			int count = tblBlackAccDoMapper.insert(tblBlackAccDo);
		if (count == 1) {
			String succeed = BjuiAjaxReturnStatusUtil.succeed();
			BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		} else {
			String message = BjuiAjaxReturnStatusUtil.error("新增失败");
			BjuiAjaxReturnStatusUtil.retJson(response, message);
		}

		return null;
	}

    @RequestMapping("addpagemer")
    @RequiresAuthentication
    public String doAddUserPagemer(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        TblBlackMer tblBlackMer = new TblBlackMer();
        request.setAttribute("blackmer", tblBlackMer);

        return "black/addBlackCard";
    }

	@RequestMapping("addpage")
	@RequiresAuthentication
	public String doAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		TblBlackAccDo tblBlackAccDo = new TblBlackAccDo();
		request.setAttribute("tblBlackAccDo", tblBlackAccDo);

		return "black/addBlackMer";
	}
}
