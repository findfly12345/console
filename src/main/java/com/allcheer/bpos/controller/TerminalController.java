package com.allcheer.bpos.controller;

import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.TblAgentMerTermDo;
import com.allcheer.bpos.entity.TblInstMerKeyInfoDO;
import com.allcheer.bpos.entity.TblMerInfoDO;
import com.allcheer.bpos.entity.TblTermInfoDO;
import com.allcheer.bpos.form.MerInfoForm;
import com.allcheer.bpos.form.TerminalForm;
import com.allcheer.bpos.service.TerminalService;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuBin on 2017/2/8.
 */
@Transactional
@Controller
@RequestMapping(value="/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @RequestMapping(value = "/query_terminal_list")
    public String queryTerminalList(@ModelAttribute("terminalForm") TerminalForm terminalForm,
                                    ModelAndView modelAndView) {
        Pagination<TblTermInfoDO> tblTermInfoDOPagination = terminalService.queryTermInfoList(terminalForm);
        terminalForm.setPagination(tblTermInfoDOPagination);

        return "/terminal/query_terminal_list";
    }

    @RequestMapping(value = "/manage_terminal_list")
    public String manageTerminalList(@ModelAttribute("terminalForm") TerminalForm terminalForm,
                                    ModelAndView modelAndView) {
        Pagination<TblTermInfoDO> tblTermInfoDOPagination = terminalService.queryTermInfoList(terminalForm);
        terminalForm.setPagination(tblTermInfoDOPagination);

        return "/terminal/manage_terminal_list";
    }

    @RequestMapping(value = "/bind_terminal_list")
    public String bindTerminalList(@ModelAttribute("terminalForm") TerminalForm terminalForm,
                                     ModelAndView modelAndView) {
        Pagination<TblTermInfoDO> tblTermInfoDOPagination = terminalService.queryTermInfoList(terminalForm);
        terminalForm.setPagination(tblTermInfoDOPagination);

        return "/terminal/bind_terminal_list";
    }

    @RequestMapping(value = "/unbind_terminal_list")
    public String unbindTerminalList(@ModelAttribute("terminalForm") TerminalForm terminalForm,
                                   ModelAndView modelAndView) {
        Pagination<TblTermInfoDO> tblTermInfoDOPagination = terminalService.queryTermInfoList(terminalForm);
        terminalForm.setPagination(tblTermInfoDOPagination);

        return "/terminal/unbind_terminal_list";
    }

    @RequestMapping(value = "/terminal_info_edit")
    public String editTerminalInfo(HttpServletRequest request, @ModelAttribute("terminalForm") TerminalForm terminalForm){
        String termId = request.getParameter("termId").trim();
        if(StringUtils.isBlank(termId)) {
            return "/terminal/manage_terminal_list";
        }
        TblTermInfoDO tblTermInfoDO = terminalService.queryTermById(termId);

        terminalForm.setMerId(tblTermInfoDO.getMerId());
        terminalForm.setTermId(termId);
        terminalForm.setTermType(tblTermInfoDO.getTermType());
        terminalForm.setTermStat(tblTermInfoDO.getTermStat());
        terminalForm.setTermInstallAddress(tblTermInfoDO.getTermInstallAddress());
        terminalForm.setTermInstallCity(tblTermInfoDO.getTermInstallCity());
        terminalForm.setTermInstallCounty(tblTermInfoDO.getTermInstallCounty());
        terminalForm.setTermInstallProv(tblTermInfoDO.getTermInstallProv());
        terminalForm.setTermName(tblTermInfoDO.getTermName());
        terminalForm.setTermSn(tblTermInfoDO.getTermSn());

        return "/terminal/terminal_info_edit";
    }

    @RequestMapping(value = "/terminal_info_add")
    public String addTerminalInfo(HttpServletRequest request, @ModelAttribute("terminalForm") TerminalForm terminalForm){
        return "/terminal/terminal_info_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/update_terminal_info" , method = RequestMethod.POST)
    public Map updateTerminalInfo(@ModelAttribute("terminalForm") TerminalForm terminalForm, HttpSession session){
        Map resultMap = new HashMap();

        UserBO user = (UserBO) session
                .getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
        String userName = user.getUsrName();
        TblTermInfoDO tblTermInfoDO = new TblTermInfoDO();
        tblTermInfoDO.setMerId(terminalForm.getMerId());
        tblTermInfoDO.setTermName(terminalForm.getTermName());
        tblTermInfoDO.setTermSn(terminalForm.getTermSn());
        tblTermInfoDO.setTermStat(terminalForm.getTermStat());
        tblTermInfoDO.setTermType(terminalForm.getTermType());
        tblTermInfoDO.setTermInstallProv(terminalForm.getTermInstallProv());
        tblTermInfoDO.setTermInstallCounty(terminalForm.getTermInstallCounty());
        tblTermInfoDO.setTermId(terminalForm.getTermId());
        tblTermInfoDO.setTermInstallCity(terminalForm.getTermInstallCity());
        tblTermInfoDO.setTermInstallAddress(terminalForm.getTermInstallAddress());
        tblTermInfoDO.setUpdateTime(DateUtil.date2String(new Date(),"yyyyMMddHHmmss"));
        tblTermInfoDO.setUserName(userName);

        if(StringUtils.isBlank(tblTermInfoDO.getTermId())) {
            String platFormTermId = terminalService.getPlatFormTermId();
            tblTermInfoDO.setTermId(platFormTermId);
            tblTermInfoDO.setCreateTime(DateUtil.date2String(new Date(),"yyyyMMddHHmmss"));
            tblTermInfoDO.setBindStat("0");
            resultMap = terminalService.insertTblTermInfo(tblTermInfoDO);
        }else {
            resultMap = terminalService.updateTblTermInfo(tblTermInfoDO);
        }
        return resultMap;
    }

    @RequestMapping(value = "/go_to_bind_terminal")
    public String gotoBindTerminalInfo(HttpServletRequest request, @ModelAttribute("terminalForm") TerminalForm terminalForm){
        String termId = request.getParameter("termId").trim();
        if(StringUtils.isBlank(termId)) {
            return "/terminal/bind_terminal_list";
        }
        TblTermInfoDO tblTermInfoDO = terminalService.queryTermById(termId);

        terminalForm.setMerId(tblTermInfoDO.getMerId());
        terminalForm.setTermId(termId);
        terminalForm.setTermType(tblTermInfoDO.getTermType());
        terminalForm.setTermStat(tblTermInfoDO.getTermStat());
        terminalForm.setTermInstallAddress(tblTermInfoDO.getTermInstallAddress());
        terminalForm.setTermInstallCity(tblTermInfoDO.getTermInstallCity());
        terminalForm.setTermInstallCounty(tblTermInfoDO.getTermInstallCounty());
        terminalForm.setTermInstallProv(tblTermInfoDO.getTermInstallProv());
        terminalForm.setTermName(tblTermInfoDO.getTermName());
        terminalForm.setTermSn(tblTermInfoDO.getTermSn());

        return "/terminal/bind_terminal_mer";
    }

    @RequestMapping(value = "/go_to_unbind_terminal")
    public String gotoUnbindTerminalInfo(HttpServletRequest request, @ModelAttribute("terminalForm") TerminalForm terminalForm){
        String termId = request.getParameter("termId").trim();
        if(StringUtils.isBlank(termId)) {
            return "/terminal/bind_terminal_list";
        }
        TblTermInfoDO tblTermInfoDO = terminalService.queryTermById(termId);

        terminalForm.setMerId(tblTermInfoDO.getMerId());
        terminalForm.setTermId(termId);
        terminalForm.setTermType(tblTermInfoDO.getTermType());
        terminalForm.setTermStat(tblTermInfoDO.getTermStat());
        terminalForm.setTermInstallAddress(tblTermInfoDO.getTermInstallAddress());
        terminalForm.setTermInstallCity(tblTermInfoDO.getTermInstallCity());
        terminalForm.setTermInstallCounty(tblTermInfoDO.getTermInstallCounty());
        terminalForm.setTermInstallProv(tblTermInfoDO.getTermInstallProv());
        terminalForm.setTermName(tblTermInfoDO.getTermName());
        terminalForm.setTermSn(tblTermInfoDO.getTermSn());

        return "/terminal/unbind_terminal_mer";
    }

    @ResponseBody
    @RequestMapping(value = "/unbind_terminal_mer")
    public Map unbindTerminalMer(HttpServletRequest request) {
        Map resultMap = new HashMap();
        String termId = request.getParameter("termId").trim();
        if(StringUtils.isBlank(termId)) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "解绑失败!");
        }
        TblTermInfoDO tblTermInfoDO = new TblTermInfoDO();
        tblTermInfoDO.setBindStat("0");
        tblTermInfoDO.setMerId("");
        tblTermInfoDO.setTermId(termId);

        resultMap = terminalService.updateTblTermInfo(tblTermInfoDO);
        TblAgentMerTermDo tblAgentMerTermDo = new TblAgentMerTermDo();
        tblAgentMerTermDo.setTermId(termId);

        resultMap = terminalService.updateTblAgentMerTerm(tblAgentMerTermDo);

        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/bind_terminal_mer")
    public Map bindTerminalMer(@ModelAttribute("terminalForm") TerminalForm terminalForm, HttpSession session) {
        Map resultMap = new HashMap();
        String termId = terminalForm.getTermId();
        if(StringUtils.isBlank(termId) || StringUtils.isBlank(terminalForm.getMerId())) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "请选择商户!");
        }
        UserBO user = (UserBO) session
                .getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
        String userName = user.getUsrName();
        TblTermInfoDO tblTermInfoDO = terminalService.queryTermById(termId);

        TblAgentMerTermDo tblAgentMerTermDo = new TblAgentMerTermDo();
        tblAgentMerTermDo.setAgentId(tblTermInfoDO.getAgentId());
        tblAgentMerTermDo.setTermId(termId);
        tblAgentMerTermDo.setMerId(terminalForm.getMerId());
        tblAgentMerTermDo.setUserName(userName);
        resultMap = terminalService.bindTblAgentMerTerm(tblAgentMerTermDo);
        if(resultMap.get("statusCode").toString().equals("300")) {
            return resultMap;
        }
        TblInstMerKeyInfoDO tblInstMerKeyInfoDO = new TblInstMerKeyInfoDO();
        tblInstMerKeyInfoDO.setInstId("00000000");
        tblInstMerKeyInfoDO.setInstMerId(terminalForm.getMerId());
        tblInstMerKeyInfoDO.setInstMerTermId(termId);
        tblInstMerKeyInfoDO.setMacKey("1");
        tblInstMerKeyInfoDO.setTdKey("1");
        tblInstMerKeyInfoDO.setPrimaryKey("1");
        tblInstMerKeyInfoDO.setPinKey("1");
        tblInstMerKeyInfoDO.setCheckStatus("Y");
        int i = terminalService.insertInstMerKey(tblInstMerKeyInfoDO);

        tblTermInfoDO.setBindStat("1");
        tblTermInfoDO.setAgentId(resultMap.get("agentId").toString());
        tblTermInfoDO.setMerId(terminalForm.getMerId());
        tblTermInfoDO.setTermId(termId);
        resultMap = terminalService.updateTblTermInfo(tblTermInfoDO);

        return resultMap;
    }

    @RequestMapping(value = "/go_to_select_mer", method = RequestMethod.GET)
    public ModelAndView goToSelectAgertMerPage(ModelAndView modelAndView) {
        MerInfoForm merInfoForm = new MerInfoForm();
        modelAndView.getModel().put("merInfoForm",merInfoForm);
        modelAndView.setViewName("/terminal/select_mer_info");

        return modelAndView;
    }

    @RequestMapping(value = "/go_to_select_mer", method = RequestMethod.POST)
    public ModelAndView selectMerInfo(@ModelAttribute("MerInfoForm") MerInfoForm merInfoForm, ModelAndView modelAndView) {
        Pagination<TblMerInfoDO> tblMerInfoDOS =  terminalService.queryMerInfoList(merInfoForm);
        modelAndView.getModel().put("tblMerInfoDOS",tblMerInfoDOS);
        modelAndView.setViewName("/terminal/select_mer_info");

        return modelAndView;
    }
}
