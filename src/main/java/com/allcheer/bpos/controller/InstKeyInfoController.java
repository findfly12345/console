package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblInstKeyInfo;
import com.allcheer.bpos.entity.TblInstKeyInfoExample;
import com.allcheer.bpos.form.InstKeyInfoForm;
import com.allcheer.bpos.mapper.TblInstKeyInfoMapper;
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
@RequestMapping("instkey")
public class InstKeyInfoController {

    @Autowired
    TblInstKeyInfoMapper tblInstKeyInfoMapper;

    @RequiresAuthentication
    @RequestMapping(value = "gopage")
    public String goToLoginPage(HttpServletRequest request) {
        InstKeyInfoForm instKeyInfoForm = new InstKeyInfoForm();

        int pageCurrent = 1;
        int pageSize = 100;

        TblInstKeyInfoExample tblInstKeyInfoExample = new TblInstKeyInfoExample();
        tblInstKeyInfoExample.createCriteria();
        int transSize = tblInstKeyInfoMapper.countByExample(tblInstKeyInfoExample);

        Pagination<TblInstKeyInfo> pagination = new Pagination<TblInstKeyInfo>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblInstKeyInfo> tblRouteControlList = tblInstKeyInfoMapper.selectByExample(tblInstKeyInfoExample);
        pagination.addResult(tblRouteControlList);
        request.setAttribute("pageUser", pagination);
        request.setAttribute("instkey", instKeyInfoForm);

        return "instkey/instKey";
    }

    @RequestMapping("search")
    @RequiresAuthentication
    public String doSelect(@ModelAttribute("instkey") InstKeyInfoForm instkey, HttpSession session,
                           HttpServletRequest request, HttpServletResponse response) {

        String instCode = null;
        TblInstKeyInfoExample tblInstKeyInfoExample = new TblInstKeyInfoExample();
        if (!StringUtils.isBlank(instkey.getInstCode())) {
            instCode = instkey.getInstCode();
            tblInstKeyInfoExample.createCriteria().andInstCodeEqualTo(instCode);
        } else {
            tblInstKeyInfoExample.createCriteria();

        }

        int pageCurrent = Integer.parseInt(instkey.getPageCurrent());
        int pageSize = Integer.parseInt(instkey.getPageSize());
        int transSize = tblInstKeyInfoMapper.countByExample(tblInstKeyInfoExample);

        Pagination<TblInstKeyInfo> pagination = new Pagination<TblInstKeyInfo>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblInstKeyInfo> tblRouteControlList = tblInstKeyInfoMapper.selectByExample(tblInstKeyInfoExample);
        pagination.addResult(tblRouteControlList);
        request.setAttribute("pageUser", pagination);
        request.setAttribute("instkey", instkey);

        return "instkey/instKey";
    }


    @RequestMapping("delete/{instCode:.*}")
    @RequiresAuthentication
    public String deleteInstKey(@PathVariable String instCode, HttpServletRequest request, HttpServletResponse response) {
        TblInstKeyInfoExample funcExample = new TblInstKeyInfoExample();
        funcExample.createCriteria().andInstCodeEqualTo(instCode);

        int i = tblInstKeyInfoMapper.deleteByExample(funcExample);
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

        TblInstKeyInfoExample funcExample = new TblInstKeyInfoExample();
        funcExample.createCriteria().andInstCodeEqualTo(instCode);
        List<TblInstKeyInfo> pfuncList = tblInstKeyInfoMapper.selectByExample(funcExample);

        int i;
        TblInstKeyInfo tblInstKeyInfo = new TblInstKeyInfo();
        for (i = 0; i < pfuncList.size(); i++) {
            tblInstKeyInfo.setInstCode(pfuncList.get(i).getInstCode());
            tblInstKeyInfo.setInstMainKey(pfuncList.get(i).getInstMainKey());
            tblInstKeyInfo.setInstPinKey(pfuncList.get(i).getInstPinKey());
            tblInstKeyInfo.setInstMacKey(pfuncList.get(i).getInstMacKey());
            tblInstKeyInfo.setInstTdKey(pfuncList.get(i).getInstTdKey());
            tblInstKeyInfo.setLoginStat(pfuncList.get(i).getLoginStat());
        }
        request.setAttribute("instkey", tblInstKeyInfo);

        return "instkey/updateInstKey";
    }

    /*
     * 风控-修改更新配置参数
     */
    @RequestMapping("alterUpdateInstKey")
    @RequiresAuthentication
    public String alterUpdateParamInfo(@ModelAttribute("instKeyInfoForm") InstKeyInfoForm instKeyInfoForm,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {

        String message = null;

        String instCode = request.getParameter("instCode");
        String instMainKey = request.getParameter("instMainKey");
        String instPinKey = request.getParameter("instPinKey");
        String instMacKey = request.getParameter("instMacKey");
        String instTdKey = request.getParameter("instTdKey");
        String loginStat = request.getParameter("loginStat");

        TblInstKeyInfoExample funcExample = new TblInstKeyInfoExample();
        funcExample.createCriteria().andInstCodeEqualTo(instCode);

        TblInstKeyInfo tblInstKeyInfo = new TblInstKeyInfo();
        tblInstKeyInfo.setInstCode(instCode);
        tblInstKeyInfo.setInstMainKey(instMainKey);
        tblInstKeyInfo.setInstPinKey(instPinKey);
        tblInstKeyInfo.setInstMacKey(instMacKey);
        tblInstKeyInfo.setInstTdKey(instTdKey);
        tblInstKeyInfo.setLoginStat(loginStat);


        int i = tblInstKeyInfoMapper.updateByExample(tblInstKeyInfo, funcExample);

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
    public String doAdd(@ModelAttribute("instkey") TblInstKeyInfo instkey, HttpSession session, HttpServletRequest request,
                        HttpServletResponse response) throws BposException {

        int count = tblInstKeyInfoMapper.insert(instkey);

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
        TblInstKeyInfo tblInstKeyInfo = new TblInstKeyInfo();
        request.setAttribute("instkey", tblInstKeyInfo);
        return "instkey/addInstKey";
    }
}
