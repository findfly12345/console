package com.allcheer.bpos.controller;

import com.allcheer.bpos.domain.AgentFeeBO;
import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblBtsInstMccFeeInfoDO;
import com.allcheer.bpos.form.AgentFeeForm;
import com.allcheer.bpos.form.InstMccFeeInfoForm;
import com.allcheer.bpos.service.InstMccFeeInfoService;
import com.allcheer.bpos.service.impl.InstBtsService;
import com.allcheer.bpos.util.BjuiAjaxReturnStatusUtil;
import com.allcheer.bpos.util.BposException;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("instmccfee")
public class InstMccFeeInfoController {


    @Autowired
    InstMccFeeInfoService instMccFeeInfoService;

    @Autowired
    private InstBtsService instBtsService;

    @RequestMapping("page")
    @RequiresAuthentication
    public String doPage(@ModelAttribute("tblBtsInstDO") TblBtsInstDO tblBtsInstDO,
                         HttpServletRequest request) {
        String pageCurrent = tblBtsInstDO.getPageCurrent();
        String pageSize = tblBtsInstDO.getPageSize();
        tblBtsInstDO.setPageCurrent(pageCurrent);
        tblBtsInstDO.setPageSize(pageSize);
        Pagination<TblBtsInstDO> tblBtsInstDOPagination = instBtsService.queryInstList(tblBtsInstDO);
        tblBtsInstDO.setPagination(tblBtsInstDOPagination);

        return "instfee/routPage";
    }

    @RequestMapping(value = "/query_inst_edit")
    public String editInstFees(HttpServletRequest request, @ModelAttribute("agentFeeForm") AgentFeeForm agentFeeForm) {
        String instId = request.getParameter("instId").trim();

        AgentFeeBO agentFeeBO = instBtsService.getInstFeeInfo(instId);

        agentFeeForm.setInstId(instId);
        agentFeeForm.setFee01(agentFeeBO.getCode01());
        agentFeeForm.setFee01L(agentFeeBO.getCode01L());
        agentFeeForm.setFee02(agentFeeBO.getCode02());
        agentFeeForm.setFee03(agentFeeBO.getCode03());
        agentFeeForm.setFee04(agentFeeBO.getCode04());
        agentFeeForm.setFee05(agentFeeBO.getCode05());
        agentFeeForm.setFee06(agentFeeBO.getCode06());
        agentFeeForm.setFee07(agentFeeBO.getCode07());
        agentFeeForm.setFee08(agentFeeBO.getCode08());

        return "instfee/edit_inst_fee";
    }


    @ResponseBody
    @RequestMapping(value = "/update_instFee_setting", method = RequestMethod.POST)
    public Map updateInstFeeSetting(@ModelAttribute("agentFeeForm") AgentFeeForm agentFeeForm, HttpSession session,
                                    HttpServletRequest request) {
        Map resultMap = new HashMap();

        if (StringUtils.isBlank(agentFeeForm.getInstId())) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "更新失败!");
            return resultMap;
        }
        if (StringUtils.isBlank(agentFeeForm.getFee01()) && StringUtils.isBlank(agentFeeForm.getFee01L())) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "请填写借记卡费率信息!");
            return resultMap;
        }
        if (StringUtils.isBlank(agentFeeForm.getFee02())) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "请填写贷记卡费率信息!");
            return resultMap;
        }
        if (StringUtils.isBlank(agentFeeForm.getFee07()) && StringUtils.isBlank(agentFeeForm.getFee08())) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "请填写提现费率信息!");
            return resultMap;
        }

        AgentFeeBO agentFeeBO = new AgentFeeBO();
        agentFeeBO.setInstId(agentFeeForm.getInstId());
        agentFeeBO.setCode01(agentFeeForm.getFee01());
        agentFeeBO.setCode01L(agentFeeForm.getFee01L());
        agentFeeBO.setCode02(agentFeeForm.getFee02());
        agentFeeBO.setCode03(agentFeeForm.getFee03());
        agentFeeBO.setCode04(agentFeeForm.getFee04());
        agentFeeBO.setCode05(agentFeeForm.getFee05());
        agentFeeBO.setCode06(agentFeeForm.getFee06());
        agentFeeBO.setCode07(agentFeeForm.getFee07());
        agentFeeBO.setCode08(agentFeeForm.getFee08());

        resultMap = instBtsService.updateFeeSetttingsForInst(agentFeeBO, agentFeeForm.getInstId());

        return resultMap;
    }

    @RequestMapping("search")
    @RequiresAuthentication
    public String doSelect(@ModelAttribute("rout") InstMccFeeInfoForm rout, HttpSession session, HttpServletRequest request,
                           HttpServletResponse response) {

        int pageCurrent = Integer.parseInt(rout.getPageCurrent());
        int pageSize = Integer.parseInt(rout.getPageSize());
        int userSize = instMccFeeInfoService.countByExample(rout);

        Pagination<TblBtsInstMccFeeInfoDO> page = new Pagination<TblBtsInstMccFeeInfoDO>(userSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblBtsInstMccFeeInfoDO> userList = instMccFeeInfoService.selectByExample(rout);
        page.addResult(userList);
        request.setAttribute("pageUser", page);

        return "instfee/routPage";
    }

    @RequestMapping("addpage")
    @RequiresAuthentication
    public String doAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        TblBtsInstMccFeeInfoDO rout = new TblBtsInstMccFeeInfoDO();
        request.setAttribute("rout", rout);
        return "instfee/addPage";
    }


    @RequestMapping("add")
    @RequiresAuthentication
    public String doAdd(@ModelAttribute("rout") TblBtsInstMccFeeInfoDO rout, HttpSession session, HttpServletRequest request,
                        HttpServletResponse response) throws BposException {
        instMccFeeInfoService.insert(rout);
        String succeed = BjuiAjaxReturnStatusUtil.succeed();
        BjuiAjaxReturnStatusUtil.retJson(response, succeed);
        return null;
    }


    @RequestMapping("updatepage/{name:.*}/{type:.*}")
    @RequiresAuthentication
    public String doUpdateUserPage(@PathVariable String name, @PathVariable String type, HttpSession session, HttpServletRequest request,
                                   HttpServletResponse response) {
        InstMccFeeInfoForm form = new InstMccFeeInfoForm();
        form.setInstId(name);
        form.setMccType(type);

        List<TblBtsInstMccFeeInfoDO> gaterout = instMccFeeInfoService.selectByExample(form);
        request.setAttribute("rout", gaterout.get(0));

        return "instfee/updatePage";
    }

    @RequestMapping("update")
    @RequiresAuthentication
    public String doUpdate(@ModelAttribute("rout") TblBtsInstMccFeeInfoDO rout, HttpSession session,
                           HttpServletRequest request, HttpServletResponse response) throws BposException {
        instMccFeeInfoService.updateByExample(rout);
        String succeed = BjuiAjaxReturnStatusUtil.succeed();
        BjuiAjaxReturnStatusUtil.retJson(response, succeed);
        return null;
    }
}
