package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblRiskBlackList;
import com.allcheer.bpos.form.RiskBlackListForm;
import com.allcheer.bpos.service.RiskBlackListService;
import com.allcheer.bpos.util.*;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("riskblacklist")
public class RiskBlackListController {

    private Logger logger = LoggerFactory.getLogger(RiskBlackListController.class);

    @Autowired
    private RiskBlackListService riskBlackListService;

    @RequestMapping("page")
    @RequiresAuthentication
    public String doPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        RiskBlackListForm rout = new RiskBlackListForm();

        int pageCurrent = Integer.parseInt(rout.getPageCurrent());
        int pageSize = Integer.parseInt(rout.getPageSize());
        int userSize = riskBlackListService.countByExample(rout);

        Pagination<TblRiskBlackList> page = new Pagination<TblRiskBlackList>(userSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblRiskBlackList> userList = riskBlackListService.selectByExample(rout);
        page.addResult(userList);
        request.setAttribute("pageUser", page);
        request.setAttribute("rout", rout);

        return "risk/riskBlackList";
    }

    @RequestMapping("search")
    @RequiresAuthentication
    public String doSelect(@ModelAttribute("rout") RiskBlackListForm rout, HttpSession session, HttpServletRequest request,
                           HttpServletResponse response) {
        int pageCurrent = Integer.parseInt(rout.getPageCurrent());
        int pageSize = Integer.parseInt(rout.getPageSize());
        int userSize = riskBlackListService.countByExample(rout);

        Pagination<TblRiskBlackList> page = new Pagination<TblRiskBlackList>(userSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblRiskBlackList> userList = riskBlackListService.selectByExample(rout);
        page.addResult(userList);
        request.setAttribute("pageUser", page);

        return "risk/riskBlackList";
    }

    @RequestMapping("addpage")
    @RequiresAuthentication
    public String doAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        TblRiskBlackList rout = new TblRiskBlackList();
        request.setAttribute("rout", rout);
        return "risk/addRiskBlackList";
    }

    @RequestMapping("delete/{priCardNo:.*}/{merchId:.*}/{termCode:.*}")
    @RequiresAuthentication
    public String deleteUser(@PathVariable String priCardNo, @PathVariable String merchId, @PathVariable String termCode, HttpServletRequest request, HttpServletResponse response) throws BposException {
        RiskBlackListForm form = new RiskBlackListForm();
        form.setPriCardNo(priCardNo);
        form.setMerchId(merchId);
        form.setTermCode(termCode);

        riskBlackListService.deleteByExample(form);
        String message = BjuiAjaxReturnStatusUtil.succeedDel();
        BjuiAjaxReturnStatusUtil.retJson(response, message);
        return null;
    }

    @RequestMapping("add")
    @RequiresAuthentication
    public String doAdd(@ModelAttribute("rout") TblRiskBlackList rout, HttpSession session, HttpServletRequest request,
                        HttpServletResponse response) throws BposException {
        rout.setCreateDate(DateUtil.getCurrentDate());
        rout.setCreateTime(DateUtil.getCurrentTime());
        riskBlackListService.insert(rout);

        String succeed = BjuiAjaxReturnStatusUtil.succeed();
        BjuiAjaxReturnStatusUtil.retJson(response, succeed);
        return null;
    }


    @RequestMapping("updatepage/{priCardNo:.*}/{merchId:.*}/{termCode:.*}")
    @RequiresAuthentication
    public String doUpdateUserPage(@PathVariable String priCardNo, @PathVariable String merchId, @PathVariable String termCode, HttpSession session, HttpServletRequest request,
                                   HttpServletResponse response) {
        RiskBlackListForm form = new RiskBlackListForm();
        form.setPriCardNo(priCardNo);
        form.setMerchId(merchId);
        form.setTermCode(termCode);

        List<TblRiskBlackList> gaterout = riskBlackListService.selectByExample(form);
        request.setAttribute("rout", gaterout.get(0));

        return "risk/updateRiskBlackList";
    }

    @RequestMapping("update")
    @RequiresAuthentication
    public String doUpdate(@ModelAttribute("rout") TblRiskBlackList rout, HttpSession session,
                           HttpServletRequest request, HttpServletResponse response) throws BposException {
        riskBlackListService.updateByExample(rout);
        String succeed = BjuiAjaxReturnStatusUtil.succeed();
        BjuiAjaxReturnStatusUtil.retJson(response, succeed);
        return null;
    }


    @RequestMapping(value = "/export_risk_trigger_mer")
    public String exportRiskTriggerMer(HttpServletRequest request,
                                       @ModelAttribute("rout") RiskBlackListForm rout, HttpServletResponse response)
            throws Exception {
        Map<String, String> queryMap = new HashMap<>();
        queryMap = Bean2Map.beanToMapNoFill(rout);
        List<TblRiskBlackList> userList = riskBlackListService.selectByExample(rout);
        response.setContentType("application/binary;charset=ISO8859_1");

        try {
            String nowTime = DateUtil.date2String(new Date(), "yyyyMMddHHmmss");
            ServletOutputStream outputStream = response.getOutputStream();
            String fileName = new String(("风控触发商户列表").getBytes(), "ISO8859_1");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + "_" + nowTime + ".xls");// 组装附件名称和格式
            riskBlackListService.exportRiskTriggerMer(userList, outputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }
}
