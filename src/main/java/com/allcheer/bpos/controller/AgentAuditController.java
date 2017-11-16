package com.allcheer.bpos.controller;


import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.domain.AgentFeeBO;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.TblAgentAuditRecordDo;
import com.allcheer.bpos.entity.TblAgentInfoDo;
import com.allcheer.bpos.service.AgentAuditService;
import com.allcheer.bpos.service.AgentService;
import com.allcheer.bpos.util.Bean2Map;
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
import java.util.Map;


/**
 * Created by peng.ll on 2017/3/1.
 * desc 代理商审核流程
 */
@Transactional
@Controller
@RequestMapping(value = "/agentAudit")
public class AgentAuditController {


    @Autowired
    private AgentAuditService agentAuditService;


    @Autowired
    AgentService agentService;

    @RequestMapping(value = "/query_agent_audit_list")
    public String queryAgentMerList(@ModelAttribute("tblAgentInfoDo") TblAgentInfoDo tblAgentInfoDo) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap = Bean2Map.beanToMapNoFill(tblAgentInfoDo);
        tblAgentInfoDo.setAgentStatus(Constant.AUDIT_NONE);

        Pagination<TblAgentInfoDo> TblAgentInfoDoPagination = agentAuditService.queryAgentInfoList(queryMap, tblAgentInfoDo);
        tblAgentInfoDo.setPagination(TblAgentInfoDoPagination);

        return "/agentAudit/agent_audit_list";
    }

    @RequestMapping(value = "/query_agent_detail_for_audit")
    public ModelAndView auditAgent(ModelAndView modelAndView, HttpServletRequest request,
                                   @ModelAttribute("tblAgentInfoDo") TblAgentInfoDo tblAgentInfoDo) {

        String memberId = request.getParameter("id");

        tblAgentInfoDo = agentAuditService.queryAgentById(memberId);
        AgentFeeBO agentFeeBO = agentService.getFeeForAgent(memberId);

        tblAgentInfoDo.setFee01(agentFeeBO.getCode01());
        tblAgentInfoDo.setFee01L(agentFeeBO.getCode01L());
        tblAgentInfoDo.setFee02(agentFeeBO.getCode02());
        tblAgentInfoDo.setFee03(agentFeeBO.getCode03());
        tblAgentInfoDo.setFee04(agentFeeBO.getCode04());
        tblAgentInfoDo.setFee05(agentFeeBO.getCode05());
        tblAgentInfoDo.setFee06(agentFeeBO.getCode06());
        tblAgentInfoDo.setFee07(agentFeeBO.getCode07());
        tblAgentInfoDo.setFee08(agentFeeBO.getCode08());
        modelAndView.addObject("tblAgentInfoDo", tblAgentInfoDo);
        modelAndView.setViewName("/agentAudit/query_agent_detail_for_audit");

        return modelAndView;
    }


    @RequestMapping(value = "/agent_audit")
    @ResponseBody
    public Map<String, Object> audit(String memberId, String errorFields, String remark, String audit, HttpSession session) {

        Map<String, Object> resultMap = new HashMap<>();
        UserBO user = (UserBO) session.getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
        String userName = user.getUsrName();

        int res = agentAuditService.auditAgent(memberId, errorFields, remark, userName, audit);
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
    public String queryAuditRecordList(@ModelAttribute("tblAgentInfoDo") TblAgentInfoDo tblAgentInfoDo,
                                       ModelAndView modelAndView) {
        Map<String, String> queryMap = Bean2Map.beanToMapNoFill(tblAgentInfoDo);
        Pagination<Map<String, Object>> tblAgentInfoVOPagination = agentAuditService.queryAgentAuditRecordList(queryMap);
        tblAgentInfoDo.setPagination(tblAgentInfoVOPagination);

        return "/agentAudit/agent_audit_record_list";
    }


    @RequestMapping(value = "/agent_audit_record_detail")
    public ModelAndView agentAuditRecordDetail(HttpServletRequest request,
                                               ModelAndView modelAndView) {
        String memberId = request.getParameter("id");
        TblAgentAuditRecordDo tblAgentAuditRecordDo = agentAuditService.queryAgentAuditReocrdById(memberId);
        TblAgentInfoDo tblAgentInfoDo = agentAuditService.queryAgentById(memberId);
        AgentFeeBO agentFeeBO = agentService.getFeeForAgent(memberId);
        tblAgentInfoDo.setFee01(agentFeeBO.getCode01());
        tblAgentInfoDo.setFee01L(agentFeeBO.getCode01L());
        tblAgentInfoDo.setFee02(agentFeeBO.getCode02());
        tblAgentInfoDo.setFee03(agentFeeBO.getCode03());
        tblAgentInfoDo.setFee04(agentFeeBO.getCode04());
        tblAgentInfoDo.setFee05(agentFeeBO.getCode05());
        tblAgentInfoDo.setFee06(agentFeeBO.getCode06());
        tblAgentInfoDo.setFee07(agentFeeBO.getCode07());
        tblAgentInfoDo.setFee08(agentFeeBO.getCode08());

        modelAndView.getModel().put("tblAgentInfoDo", tblAgentInfoDo);
        modelAndView.getModel().put("tblAgentAuditRecordDo", tblAgentAuditRecordDo);
        modelAndView.setViewName("/agentAudit/agent_audit_record_detail");

        return modelAndView;
    }


}
