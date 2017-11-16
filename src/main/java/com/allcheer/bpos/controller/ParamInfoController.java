package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblParamInfo;
import com.allcheer.bpos.entity.TblParamInfoExample;
import com.allcheer.bpos.form.ParamInfoForm;
import com.allcheer.bpos.mapper.TblParamInfoMapper;
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
@RequestMapping("param")
public class ParamInfoController {

    @Autowired
    private TblParamInfoMapper tblParamInfoMapper;

    @RequiresAuthentication
    @RequestMapping(value = "gopage")
    public String goToLoginPage(HttpServletRequest request) {
        ParamInfoForm paramInfoForm = new ParamInfoForm();
        int pageCurrent = 1;
        int pageSize = 100;

        TblParamInfoExample tblParamInfoExample = new TblParamInfoExample();
        tblParamInfoExample.createCriteria();
        int transSize = tblParamInfoMapper.countByExample(tblParamInfoExample);

        Pagination<TblParamInfo> pagination = new Pagination<TblParamInfo>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblParamInfo> tblRouteControlList = tblParamInfoMapper.selectByExample(tblParamInfoExample);
        pagination.addResult(tblRouteControlList);
        request.setAttribute("pageUser", pagination);
        request.setAttribute("param", paramInfoForm);

        return "param/param_info";
    }

    @RequestMapping("search")
    @RequiresAuthentication
    public String doSelect(@ModelAttribute("param") ParamInfoForm param, HttpSession session,
                           HttpServletRequest request, HttpServletResponse response) {
        String paramNum = null;
        String paramName = null;

        if (!StringUtils.isBlank(param.getParamNum())) {
            paramNum = param.getParamNum();
        }

        if (!StringUtils.isBlank(param.getParamName())) {
            paramName = param.getParamName();
        }

        int pageCurrent = Integer.parseInt(param.getPageCurrent());
        int pageSize = Integer.parseInt(param.getPageSize());

        int transSize = tblParamInfoMapper.countByCust(paramNum, paramName);

        Pagination<TblParamInfo> pagination = new Pagination<TblParamInfo>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblParamInfo> tblRouteControlList = tblParamInfoMapper.selectByCust(paramNum, paramName);
        pagination.addResult(tblRouteControlList);
        request.setAttribute("pageUser", pagination);
        request.setAttribute("param", param);

        return "param/param_info";
    }

    @RequestMapping("delete/{paramNum:.*}")
    @RequiresAuthentication
    public String deleteParam(@PathVariable String paramNum, HttpServletRequest request, HttpServletResponse response) {
        TblParamInfoExample funcExample = new TblParamInfoExample();
        funcExample.createCriteria().andParamNumEqualTo(paramNum);

        int i = tblParamInfoMapper.deleteByExample(funcExample);
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
    @RequestMapping("updatepage/{paramNum:.*}")
    @RequiresAuthentication
    public String doUpdateUserPage(@PathVariable String paramNum, HttpSession session, HttpServletRequest request,
                                   HttpServletResponse response) {

        TblParamInfoExample funcExample = new TblParamInfoExample();
        funcExample.createCriteria().andParamNumEqualTo(paramNum);
        List<TblParamInfo> pfuncList = tblParamInfoMapper.selectByExample(funcExample);

        int i;
        TblParamInfo param = new TblParamInfo();
        for (i = 0; i < pfuncList.size(); i++) {
            param.setParamNum(pfuncList.get(i).getParamNum());
            param.setParamName(pfuncList.get(i).getParamName());
            param.setParamValue(pfuncList.get(i).getParamValue());
            param.setParamStat(pfuncList.get(i).getParamStat());
        }

        request.setAttribute("param", param);

        return "param/updateParam";
    }

    /*
     * 风控-修改更新配置参数
     */
    @RequestMapping("alterUpdateParam")
    @RequiresAuthentication
    public String alterUpdateParamInfo(@ModelAttribute("paramInfoForm") ParamInfoForm paramInfoForm,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        String message = "";
        String paramNum = request.getParameter("paramNum");
        String paramName = request.getParameter("paramName");
        String paramValue = request.getParameter("paramValue");
        String paramStat = request.getParameter("paramStat");

        TblParamInfoExample funcExample = new TblParamInfoExample();
        funcExample.createCriteria().andParamNumEqualTo(paramNum);

        TblParamInfo param = new TblParamInfo();
        param.setParamNum(paramNum);
        param.setParamName(paramName);
        param.setParamValue(paramValue);
        param.setParamStat(paramStat);

        int i = tblParamInfoMapper.updateByExample(param, funcExample);

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
    public String doAdd(@ModelAttribute("param") TblParamInfo param, HttpSession session, HttpServletRequest request,
                        HttpServletResponse response) throws BposException {
        int count = tblParamInfoMapper.insert(param);

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
        TblParamInfo param = new TblParamInfo();
        request.setAttribute("param", param);
        return "param/addParam";
    }
}
