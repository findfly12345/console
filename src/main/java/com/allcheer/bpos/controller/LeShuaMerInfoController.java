package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblMerInfoDO;
import com.allcheer.bpos.entity.TblMerLeshuaAddressDo;
import com.allcheer.bpos.entity.TblMerLeshuaAddressDoExample;
import com.allcheer.bpos.entity.vo.LeShuaMerInfoVO;
import com.allcheer.bpos.form.LeShuaMerInfoForm;
import com.allcheer.bpos.mapper.TblMerInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerLeshuaAddressDoMapper;
import com.allcheer.bpos.service.LeShuaMerService;
import com.allcheer.bpos.util.Bean2Map;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 乐刷商户管理
 */
@Controller
@RequestMapping(value = "/channel_mer")
public class LeShuaMerInfoController {

    private final static Logger logger = LoggerFactory.getLogger(LeShuaMerInfoController.class);

    @Autowired
    private LeShuaMerService leShuaMerService;

    @Autowired
    private TblMerInfoDOMapper tblMerInfoDOMapper;

    @Autowired
    private TblMerLeshuaAddressDoMapper tblMerLeshuaAddressDoMapper;

    /**
     * 商户信息列表查询
     * @param leShuaMerInfoForm
     * @return
     */
    @RequestMapping(value = "/leshua_mer_manage")
    public String goToMerInfoPage(@ModelAttribute("leShuaMerInfoForm") LeShuaMerInfoForm leShuaMerInfoForm) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap = Bean2Map.beanToMapNoFill(leShuaMerInfoForm);
        Pagination<LeShuaMerInfoVO> leShuaMerInfoVOPagination = leShuaMerService.getLeShuaMerInfoList(queryMap);
        leShuaMerInfoForm.setPagination(leShuaMerInfoVOPagination);
        return "/channelmer/le_shua_mer_manage";
    }



    @RequestMapping(value = "/le_shua_register")
    @ResponseBody
    public Map leShuaRegister(HttpServletRequest httpServletRequest) {
        String  memberId = httpServletRequest.getParameter("memberId");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (StringUtils.isEmpty(memberId)) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "商户号为空!");
            return resultMap;
        }
        TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(memberId);
        if(tblMerInfoDO == null) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "商户非法!");
            return resultMap;
        }

        TblMerLeshuaAddressDoExample tblMerLeshuaAddressDoExample = new TblMerLeshuaAddressDoExample();
        tblMerLeshuaAddressDoExample.createCriteria().andMerIdEqualTo(memberId);
        List<TblMerLeshuaAddressDo> tblMerLeshuaAddressDoList = tblMerLeshuaAddressDoMapper.selectByExample(tblMerLeshuaAddressDoExample);
        if(tblMerLeshuaAddressDoList == null || tblMerLeshuaAddressDoList.size() == 0) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "商户地址还未维护!");
            return resultMap;
        }
        
        if(tblMerInfoDO.getMerType().equals("2")) {
            resultMap = leShuaMerService.register(memberId);
        }else {
            resultMap = leShuaMerService.registerBusiness(memberId);
        }
        return resultMap;
    }

    @RequestMapping(value = "/le_shua_open_shaoma")
    @ResponseBody
    public Map leShuaOpenShaoMao(HttpServletRequest httpServletRequest) {
        String  merchantId = httpServletRequest.getParameter("merchantId");
        String  memberId = httpServletRequest.getParameter("memberId");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (StringUtils.isEmpty(memberId)) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "商户号为空!");
            return resultMap;
        }
        if (StringUtils.isEmpty(merchantId)) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "请先完成商户进件!");
            return resultMap;
        }

        TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(memberId);
        if(tblMerInfoDO == null) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "商户非法!");
            return resultMap;
        }

        resultMap = leShuaMerService.openShaoMa(memberId,merchantId);

        return resultMap;
    }

}
