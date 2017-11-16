package com.allcheer.bpos.controller;

import com.allcheer.bpos.domain.InstBO;
import com.allcheer.bpos.domain.InstMerBO;
import com.allcheer.bpos.domain.MerRouteBO;
import com.allcheer.bpos.domain.MerSelectBO;
import com.allcheer.bpos.form.MerRouteForm;
import com.allcheer.bpos.form.MerRouteQueryForm;
import com.allcheer.bpos.service.InstitutionService;
import com.allcheer.bpos.service.RouteConfService;
import com.allcheer.bpos.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by fireWorks on 2016/3/9.
 */
@Controller
@RequestMapping(value = "/routeManage")
public class RouteController {


    @Autowired
    private RouteConfService routeConfService;

    @Autowired
    private InstitutionService institutionService;

    @RequestMapping(value = "/QueryMerRouteConf")
    public String goRouteConfigPage(@ModelAttribute("merRouteQueryForm") MerRouteQueryForm merRouteQueryForm) {
        MerRouteBO merRouteBO = new MerRouteBO();

        if (merRouteQueryForm.getsCurrentTradeCount() != null && !merRouteQueryForm.getsCurrentTradeCount().trim().equals("")) {
            merRouteBO.setCurrentTradeCount(merRouteQueryForm.getsCurrentTradeCount());
        }

        if (merRouteQueryForm.getsMerId() != null && !merRouteQueryForm.getsMerId().trim().equals("")) {
            merRouteBO.setMerId(merRouteQueryForm.getsMerId());
        }
        String pageCurrent = merRouteQueryForm.getPageCurrent();
        String pageSize = merRouteQueryForm.getPageSize();

        merRouteBO.setPageCurrent(Integer.valueOf(pageCurrent));
        merRouteBO.setPageSize(Integer.valueOf(pageSize));

        Pagination<MerRouteBO> merRouteBOPagination = routeConfService.getMerRouteTradeCountRules(merRouteBO);
        merRouteQueryForm.setPagination(merRouteBOPagination);

        return "edit_mer_route_info";
    }


    @RequestMapping(value = "/AddMerRouteConf")
    public Map AddTradeCountRuleRoute(@ModelAttribute("merRouteForm") MerRouteForm merRouteForm) {
        Map resultMap = new HashMap();

        MerRouteBO merRouteBO = new MerRouteBO();
        merRouteBO.setMerId(merRouteForm.getMerId());
        merRouteBO.setCurrentTradeCount(merRouteForm.getCurrentTradeCount());
        merRouteBO.setMerSelectBOList(merRouteForm.getRefsBigMers());

        resultMap = routeConfService.addNewTradeCountRuleForRoute(merRouteBO);

        return resultMap;
    }

    @RequestMapping(value = "/EditMerRouteConf")
    public Map EditTradeCountRuleRoute(@ModelAttribute("merRouteForm") MerRouteForm merRouteForm) {
        Map resultMap = new HashMap();

        MerRouteBO merRouteBO = new MerRouteBO();
        merRouteBO.setId(merRouteForm.getId());
        merRouteBO.setMerId(merRouteForm.getMerId());
        merRouteBO.setCurrentTradeCount(merRouteForm.getCurrentTradeCount());

        resultMap = routeConfService.addNewTradeCountRuleForRoute(merRouteBO);

        return resultMap;
    }

    @RequestMapping(value = "/select_big_mer")
    public String getInstList(@ModelAttribute("merRouteForm") MerRouteForm merRouteForm) {
        InstBO instBO = new InstBO();
        instBO.setPageCurrent(0);
        instBO.setPageSize(10);

        Pagination<InstBO> instBOPagination = institutionService.getTheInst(instBO);
        List<InstBO> instBOList = instBOPagination.getList();
        merRouteForm.setInstBOList(instBOList);

        if (merRouteForm.getInstCode() == null || merRouteForm.getInstCode().equals("")) {
            return "select_big_mer";
        }
        InstMerBO instMerBO = new InstMerBO();
        instMerBO.setInstId(merRouteForm.getInstCode());
        instMerBO.setPageCurrent(0);
        instMerBO.setPageSize(10);

        Pagination<InstMerBO> instMerBOPagination = institutionService.getMerByInst(instMerBO);

        List<MerSelectBO> merSelectBOList = new ArrayList<>();
        for (InstMerBO instMerBO1 : instMerBOPagination.getList()) {
            MerSelectBO merSelectBO = new MerSelectBO();
            merSelectBO.setMerId(instMerBO1.getInstMerId());
            merSelectBO.setChecked("false");
            merSelectBOList.add(merSelectBO);

        }
        merRouteForm.setRefsBigMers(merSelectBOList);

        return "select_big_mer";
    }

    @RequestMapping(value = "/select_big_mers", method = RequestMethod.GET)
    public String goAddTradeCountRulPage(@ModelAttribute("merRouteForm") MerRouteForm merRouteForm, HttpServletRequest request) {
        String id = request.getParameter("id");
        if (id == null || id == "") {
            return "select_big_mers";
        }
        MerRouteBO merRouteBO = new MerRouteBO();
        merRouteBO.setId(id);
        merRouteBO.setPageCurrent(0);
        merRouteBO.setPageSize(10);
        Pagination<MerRouteBO> merRouteBOPagination = routeConfService.getMerRouteTradeCountRules(merRouteBO);

        List<MerRouteBO> merRouteBOList = merRouteBOPagination.getList();
        merRouteBO = merRouteBOList.get(0);
        merRouteForm.setId(id);
        merRouteForm.setMerId(merRouteBO.getMerId());
        merRouteForm.setCurrentTradeCount(merRouteBO.getCurrentTradeCount());
        merRouteForm.setRefsBigMer(ListToString(merRouteBO.getRefsBigMers()));

        return "select_big_mers";
    }

    @ResponseBody
    @RequestMapping(value = "/select_big_mers", method = RequestMethod.POST)
    public Map addTradeCountRulPage(@ModelAttribute("merRouteForm") MerRouteForm merRouteForm) {

        Map resultMap = new HashMap();

        MerRouteBO merRouteBO = new MerRouteBO();
        merRouteBO.setMerId(merRouteForm.getMerId());
        merRouteBO.setCurrentTradeCount(merRouteForm.getCurrentTradeCount());
        String[] bigMerIdArray = merRouteForm.getRefsBigMer().split(",");
        List<String> bigMerIdList = Arrays.asList(bigMerIdArray);
        merRouteBO.setRefsBigMers(bigMerIdList);

        resultMap = routeConfService.addNewTradeCountRuleForRoute(merRouteBO);

        return resultMap;
    }

    private String ListToString(List<String> stringList) {
        StringBuilder result = new StringBuilder();
        boolean flag = false;

        for (String str : stringList) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(str);
        }

        return result.toString();

    }

    @ResponseBody
    @RequestMapping(value = "/delete_trade_count_rule", method = RequestMethod.POST)
    public Map deleteTradeCountRule(HttpServletRequest request) {
        Map resultMap = new HashMap();

        String id = request.getParameter("id");

        resultMap = routeConfService.deleteTradeCountRuleForRoute(id);

        return resultMap;
    }
}
