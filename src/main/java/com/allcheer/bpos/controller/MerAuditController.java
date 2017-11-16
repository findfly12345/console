package com.allcheer.bpos.controller;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.entity.vo.TblAgentMerInfoVO;
import com.allcheer.bpos.form.AgentMerFeeForm;
import com.allcheer.bpos.form.MerAuditForm;
import com.allcheer.bpos.form.MerFeeForm;
import com.allcheer.bpos.form.MerInfoForm;
import com.allcheer.bpos.service.MerAgentService;
import com.allcheer.bpos.service.MerchInfoService;
import com.allcheer.bpos.util.Bean2Map;
import com.allcheer.bpos.util.CalcModeUtil;
import com.allcheer.bpos.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商户审核Controller
 *
 * @audit Hu Shunfeng
 */
@Transactional
@Controller
@RequestMapping(value = "/mer")
public class MerAuditController {


    @Autowired
    MerAgentService merAgentService;
    @Autowired
    MerchInfoService merchInfoService;

    @RequestMapping(value = "/query_audit_mer_list")
    public String queryAgentMerList(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm,
                                    ModelAndView modelAndView) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap = Bean2Map.beanToMapNoFill(merInfoForm);
        List<TblAgentInfoDo> tblAgentInfoDoList = merAgentService.findAgentInfos(new TblAgentInfoDo());

        queryMap.put("merStat", Constant.AUDIT_NONE);
        Pagination<TblAgentMerInfoVO> tblAgentMerInfoVOPagination = merAgentService.queryAgentMerInfoList(queryMap);
        merInfoForm.setPagination(tblAgentMerInfoVOPagination);
        merInfoForm.setTblAgentInfoDos(tblAgentInfoDoList);

        return "/mer/mer_audit_list";
    }

    @RequestMapping(value = "/query_mer_agent_detail_for_audit")
    public ModelAndView auditAgentMer(ModelAndView modelAndView, HttpServletRequest request,
                                      @ModelAttribute("merFeeForm") MerFeeForm merFeeForm) {
        String merId = request.getParameter("id");

        TblAgentInfoDo tblAgentInfoDo = merAgentService.queryAgentById(merId);
        TblMerInfoDO tblMerInfoDO = merAgentService.queryMerById(merId);
        TblMerBankInfoDO tblMerBankInfoDO = merAgentService.queryMerBankById(merId);
        List<TblMerFeeInfoDO> tblMerFeeInfoDOS = merAgentService.queryMerFeeById(merId);
        TblMerFileInfoDO tblMerFileInfoDO = merAgentService.queryFilesByMerId(merId);

        AgentMerFeeForm agentMerFeeForm = new AgentMerFeeForm();
        String feeType = "";
        String[] calc;
        int num = 0;
        for (TblMerFeeInfoDO tblMerFeeInfoDO : tblMerFeeInfoDOS) {
            feeType = tblMerFeeInfoDO.getFeeType();
            feeType = feeType.replace("0", "");
            num = Integer.parseInt(feeType);
            calc = CalcModeUtil.splitCalcMode(tblMerFeeInfoDO.getCalcMode(), false).split(",");
            switch (num) {
                case 1:
                    agentMerFeeForm.setFee01(calc[0]);
                    agentMerFeeForm.setFee01L(calc[1]);
                    break;
                case 2:
                    agentMerFeeForm.setFee02(calc[0]);
                    break;
                case 3:
                    agentMerFeeForm.setFee03(calc[0]);
                    break;
                case 4:
                    agentMerFeeForm.setFee04(calc[0]);
                    break;
                case 5:
                    agentMerFeeForm.setFee05(calc[0]);
                    break;
                case 6:
                    agentMerFeeForm.setFee06(calc[0]);
                    break;
                case 7:
                    agentMerFeeForm.setFee07(calc[0]);
                    break;
                case 8:
                    agentMerFeeForm.setFee08(calc[0]);
                    break;
                default:
                    break;
            }
        }

        modelAndView.addObject("tblAgentInfoDo", tblAgentInfoDo);
        modelAndView.addObject("tblMerInfoDO", tblMerInfoDO);
        modelAndView.addObject("tblMerBankInfoDO", tblMerBankInfoDO);
        modelAndView.addObject("agentMerFeeForm", agentMerFeeForm);
        modelAndView.addObject("tblMerFileInfoDO", tblMerFileInfoDO);
        modelAndView.setViewName("/mer/query_mer_agent_detail_for_audit");

        return modelAndView;
    }


    @RequestMapping(value = "/mer_audit")
    @ResponseBody
    public Map<String, Object> audit(String merId, String errorFields, String remark, String audit, String inRemark, HttpSession session) {

        Map<String, Object> resultMap = new HashMap<>();
        UserBO user = (UserBO) session.getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
        String userName = user.getUsrName();
        int res;
        if ("ok".equals(audit)) {
            res = merAgentService.auditOK(merId, errorFields, remark, inRemark, userName);
        } else {
            res = merAgentService.auditReject(merId, errorFields, remark, inRemark, userName);
        }
        if (res > 0) {
            resultMap.put("statusCode", 200);
            resultMap.put("message", "审核成功");
        } else {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "审核失败");
        }
        return resultMap;
    }


    @RequestMapping(value = "/query_audit_record_list")
    public String queryAuditRecordList(@ModelAttribute("merAuditForm") MerAuditForm merAuditForm,
                                       ModelAndView modelAndView) {
        Map<String, String> queryMap = Bean2Map.beanToMapNoFill(merAuditForm);

        Pagination<Map<String, Object>> tblAgentMerInfoVOPagination = merAgentService.queryMerAuditRecordList(queryMap);
        merAuditForm.setPagination(tblAgentMerInfoVOPagination);

        return "/mer/mer_audit_record_list";
    }

    @RequestMapping(value = "/audit_record_detail")
    public ModelAndView auditRecordDetail(HttpServletRequest request,
                                          ModelAndView modelAndView) {
        String recordId = request.getParameter("id");
        TblMerAuditRecordDO tblMerAuditRecordDO = merAgentService.queryMerAuditReocrdById(recordId);
        TblAgentInfoDo tblAgentInfoDo = merAgentService.queryAgentById(tblMerAuditRecordDO.getMerId());
        TblMerInfoDO tblMerInfoDO = merAgentService.queryMerById(tblMerAuditRecordDO.getMerId());
        TblMerBankInfoDO tblMerBankInfoDO = merAgentService.queryMerBankById(tblMerAuditRecordDO.getMerId());
        List<TblMerFeeInfoDO> tblMerFeeInfoDOS = merAgentService.queryMerFeeById(tblMerAuditRecordDO.getMerId());
        AgentMerFeeForm agentMerFeeForm = new AgentMerFeeForm();
        String feeType = "";
        String[] calc;
        int num = 0;
        for (TblMerFeeInfoDO tblMerFeeInfoDO : tblMerFeeInfoDOS) {
            feeType = tblMerFeeInfoDO.getFeeType();
            feeType = feeType.replace("0", "");
            num = Integer.parseInt(feeType);
            calc = CalcModeUtil.splitCalcMode(tblMerFeeInfoDO.getCalcMode(), false).split(",");
            switch (num) {
                case 1:
                    agentMerFeeForm.setFee01(calc[0]);
                    agentMerFeeForm.setFee01L(calc[1]);
                    break;
                case 2:
                    agentMerFeeForm.setFee02(calc[0]);
                    break;
                case 3:
                    agentMerFeeForm.setFee03(calc[0]);
                    break;
                case 4:
                    agentMerFeeForm.setFee04(calc[0]);
                    break;
                case 5:
                    agentMerFeeForm.setFee05(calc[0]);
                    break;
                case 6:
                    agentMerFeeForm.setFee06(calc[0]);
                    break;
                case 7:
                    agentMerFeeForm.setFee07(calc[0]);
                    break;
                case 8:
                    agentMerFeeForm.setFee08(calc[0]);
                    break;
                default:
                    break;
            }
        }

        modelAndView.getModel().put("tblAgentInfoDo", tblAgentInfoDo);
        modelAndView.getModel().put("tblMerInfoDO", tblMerInfoDO);
        modelAndView.getModel().put("tblMerBankInfoDO", tblMerBankInfoDO);
        modelAndView.getModel().put("agentMerFeeForm", agentMerFeeForm);
        modelAndView.getModel().put("tblMerAuditRecordDO", tblMerAuditRecordDO);
        modelAndView.setViewName("/mer/mer_audit_record_detail");

        return modelAndView;
    }
}
