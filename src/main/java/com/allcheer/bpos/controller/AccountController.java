package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.ACCOUNTDO;
import com.allcheer.bpos.entity.TblAgentInfoDo;
import com.allcheer.bpos.service.AccountService;
import com.allcheer.bpos.service.MerAgentService;
import com.allcheer.bpos.util.Bean2Map;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by peng.ll on 2017/3/3.
 * description 商户对账管理
 */

@Controller
@RequestMapping(value = "/account")
public class AccountController {


    @Autowired
    AccountService accountService;

    @Autowired
    MerAgentService merAgentService;

    @RequestMapping(value = "/query_account_list")
    public String queryAccountList(@ModelAttribute("accountForm") ACCOUNTDO accountForm) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap = Bean2Map.beanToMapNoFill(accountForm);

        Pagination<ACCOUNTDO> accountDoPagination = accountService.queryAccountList(queryMap, accountForm);
        accountForm.setPagination(accountDoPagination);

        return "/account/query_account_list";
    }

    @RequestMapping(value = "/add_account_detail", method = RequestMethod.GET)
    public ModelAndView goToAddAccountPage(ModelAndView modelAndView) {

        String platFormMerId = merAgentService.getPlatFormMerId();
        modelAndView.getModel().put("merId", platFormMerId);
        modelAndView.setViewName("/account/add_account_detail");

        return modelAndView;
    }

    @RequestMapping(value = "/add_account_detail", method = RequestMethod.POST)
    @ResponseBody
    public Map addAccount(@ModelAttribute("accountForm") ACCOUNTDO accountForm,
                           @ModelAttribute("tblAgentInfoDo") TblAgentInfoDo tblAgentInfoDo,
                           ModelAndView modelAndView) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (!StringUtils.isNotBlank(tblAgentInfoDo.getMemberId())) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "请选择代理商!");
            return resultMap;
        }
        accountForm.setAgentId(tblAgentInfoDo.getMemberId());
        int ii = accountService.insertAccount(accountForm);

        if (ii > 0) {
            resultMap.put("statusCode", 200);
            resultMap.put("message", "保存成功");
        } else {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "保存失败");
        }

        return resultMap;
    }
}
