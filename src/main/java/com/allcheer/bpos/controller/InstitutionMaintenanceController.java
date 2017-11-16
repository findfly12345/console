package com.allcheer.bpos.controller;

import com.allcheer.bpos.domain.InstBO;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.form.InstitutionForm;
import com.allcheer.bpos.mapper.SeqMapper;
import com.allcheer.bpos.service.InstitutionService;
import com.allcheer.bpos.util.BjuiAjaxReturnStatusUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.SystemConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fireWorks on 2016/2/26.
 */
@Controller
@RequestMapping(value = "/IM")
public class InstitutionMaintenanceController {

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private SeqMapper seqMapper;


    @RequiresAuthentication
    @RequestMapping(value = "/inst_maintenance_page", method = RequestMethod.GET)
    public String goInstPage(HttpServletRequest request, HttpServletResponse response,
                             @ModelAttribute("institutionForm") InstitutionForm institutionForm) {
        return "InstMaintenance/inst_maintenance_list";
    }

    @RequiresAuthentication
    @RequestMapping(value = "/add_new_inst_page", method = RequestMethod.GET)
    public String goAddNewInstPage(@ModelAttribute("institutionForm") InstitutionForm institutionForm) {
        return "InstMaintenance/addnewinstitution";
    }

    /**
     * BTS
     *
     * @param institutionForm
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "/maintenance_the_inst", method = RequestMethod.POST)
    public String maintenanceTheInst(@ModelAttribute("institutionForm") InstitutionForm institutionForm,
                                     HttpServletRequest request) {

        String pageCurrent = institutionForm.getPageCurrent();
        String pageSize = institutionForm.getPageSize();

        InstBO instBO = new InstBO();
        instBO.setInstCode(institutionForm.getInstCode());
        instBO.setInstName(institutionForm.getInstName());
        instBO.setInstType(institutionForm.getInstType());
        instBO.setPageCurrent(Integer.valueOf(pageCurrent));
        instBO.setPageSize(Integer.valueOf(pageSize));

        Pagination<InstBO> instBOPagination = institutionService.getTheInst(instBO);
        institutionForm.setPagination(instBOPagination);

        return "InstMaintenance/inst_maintenance_list";
    }

    /**
     * BTS
     *
     * @param institutionForm
     * @param session
     * @return
     */
    @Transactional
    @RequiresAuthentication
    @RequestMapping(value = "/add_new_inst", method = RequestMethod.POST)
    public String addNewInst(@ModelAttribute("institutionForm") InstitutionForm institutionForm, HttpSession session,
                             HttpServletRequest request, HttpServletResponse response) {

        InstBO instBO = new InstBO();
        String instNum = String.valueOf(seqMapper.getTblBTSInstIdSeq());
        String instId = "";
        instId = StringUtils.leftPad(instNum, 8, '0');

        instBO.setInstId(instId);
        instBO.setInstCode(institutionForm.getInstCode());
        instBO.setInstName(institutionForm.getInstName());
        instBO.setInstType(institutionForm.getInstType());
        instBO.setInstEmail(institutionForm.getInstEmail());
        instBO.setInstTelphome(institutionForm.getInstTelphome());

        UserBO user = (UserBO) session.getAttribute(SystemConstant.USER_SESSION_KEY);
        instBO.setUsrCreateBy(user.getUsrId());
        
        institutionService.addNewInst(instBO);

        String succeed = BjuiAjaxReturnStatusUtil.succeed();
        BjuiAjaxReturnStatusUtil.retJson(response, succeed);
        return null;
    }

    /**
     * BTS
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping(value = "/delete_inst", method = RequestMethod.POST)
    public Map deleteInstInfo(HttpServletRequest request) {
        Map resultMap = new HashMap();

        String id = request.getParameter("id");
        resultMap = institutionService.deleteInstById(id);

        return resultMap;
    }

    /**
     * BTS
     *
     * @param request
     * @param response
     * @param institutionForm
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "/edit_inst_info", method = RequestMethod.GET)
    public String goEditInstInfo(HttpServletRequest request, HttpServletResponse response,
                                 @ModelAttribute("institutionForm") InstitutionForm institutionForm) {
        String instCode = request.getParameter("id");
        TblBtsInstDO instDO = institutionService.getInstById(instCode);

        institutionForm.setInstName(instDO.getInstName());
        institutionForm.setInstType(instDO.getInstType());
        institutionForm.setInstCode(instDO.getInstCode());
        institutionForm.setInstEmail(instDO.getInstEmail());
        institutionForm.setInstTelphome(instDO.getInstTelphome());

        return "InstMaintenance/edit_inst_info";
    }

    /**
     * BTS
     *
     * @param institutionForm
     * @param session
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "/change_inst_info", method = RequestMethod.POST)
    public Map changeInstInfo(@ModelAttribute("institutionForm") InstitutionForm institutionForm, HttpSession session,
                              HttpServletResponse response) {

        InstBO instBO = new InstBO();
        instBO.setInstCode(institutionForm.getInstCode());
        instBO.setInstName(institutionForm.getInstName());
        instBO.setInstType(institutionForm.getInstType());
        instBO.setInstEmail(institutionForm.getInstEmail());
        instBO.setInstTelphome(institutionForm.getInstTelphome());

        UserBO user = (UserBO) session.getAttribute(SystemConstant.USER_SESSION_KEY);
        instBO.setUsrCreateBy(user.getUsrId());

        institutionService.updateInstInfo(instBO);
        String succeed = BjuiAjaxReturnStatusUtil.succeed();
        BjuiAjaxReturnStatusUtil.retJson(response, succeed);
        return null;
    }
}
