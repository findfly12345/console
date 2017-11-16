package com.allcheer.bpos.controller;

import com.allcheer.bpos.service.WechatRegisterService;
import com.allcheer.bpos.service.impl.WechatRegisterServiceImpl;
import com.allcheer.bpos.util.constant.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fireWorks on 2016/11/29.
 */
@Controller
@RequestMapping(value = "/wechat")
public class WechatRegisterController {

	private final static Logger logger = LoggerFactory.getLogger(WechatRegisterController.class);

	@Autowired
	private WechatRegisterService wechatRegisterService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/wechat_register", method = RequestMethod.POST)
	public Map wechatRegister(HttpServletRequest request, HttpServletResponse response) {
		Map resultMap = new HashMap();

		String memberId = request.getParameter("memberId");
		String action = request.getParameter("action");
		String[] merIds = memberId.split(",");
		if (merIds.length >= 1) {
			for (String id : merIds) {
				if (id.contains("hy")) {
					logger.info("翰银注册:" + id);
					id = id.replace("hy", "");
					Map<String, String> msMap = wechatRegisterService.registerForHanyin(id,
							WechatRegisterServiceImpl.HAN_YIN_CHANNEL, CommonConstants.OPER_FLAG_A);

					msMap.put("memberId", id);
					msMap.put("priority", "Y");

					Map resultMapMsHy = wechatRegisterService.addHanYinMapGen(msMap,
							WechatRegisterServiceImpl.HAN_YIN_CHANNEL);
					if (resultMapMsHy.get("statusCode").equals("200")) {
						resultMap.put("status", 200);
						resultMap.put("message", resultMapMsHy.get("message"));
					} else {
						resultMap.put("status", 300);
						resultMap.put("message", resultMapMsHy.get("message"));
					}

					return resultMap;
				}

				if (id.contains("mswx")) {
					logger.info("民生微信注册:" + id);
					id = id.replace("mswx", "");
					Map<String, String> msMapWx = wechatRegisterService.registerForMSBank(id,
							WechatRegisterServiceImpl.weiXinChannel);

					if (msMapWx == null || !msMapWx.containsKey("status")) {
						resultMap.put("status", 300);
						resultMap.put("message", "民生银行商户微信报备失败");
						return resultMap;
					}

					if (msMapWx.get("status").equals("SUCCESS")) {
						resultMap.put("status", 200);
						resultMap.put("message", msMapWx.get("message"));
					} else if (msMapWx.get("status").equals("FAIL")) {
						resultMap.put("status", 300);
						resultMap.put("message", msMapWx.get("message"));
					}
					return resultMap;

				}

				if (id.contains("mszf")) {
					logger.info("民生支付宝注册:" + id);
					id = id.replace("mszf", "");
					Map<String, String> msMapZf = wechatRegisterService.registerForMSBank(id,
							WechatRegisterServiceImpl.zhiFuBaoChannel);

					if (msMapZf == null || !msMapZf.containsKey("status")) {
						resultMap.put("status", 300);
						resultMap.put("message", "民生银行商户支付宝报备失败");
						return resultMap;
					}

					if (msMapZf.get("status").equals("SUCCESS")) {
						resultMap.put("status", 200);
						resultMap.put("message", msMapZf.get("message"));
					} else if (msMapZf.get("status").equals("FAIL")) {
						resultMap.put("status", 300);
						resultMap.put("message", msMapZf.get("message"));
					}
					return resultMap;
				}

				if (id.contains("shbank")) {

					logger.info("上海银行注册:" + id);

					id = id.replace("shbank", "");
					Map<String, String> shbankMapZf = wechatRegisterService.registerForSHBank(id, action);

					if (shbankMapZf == null || !shbankMapZf.containsKey("status")) {
						resultMap.put("status", 300);
						resultMap.put("message", "上海银行注册失败");
						return resultMap;
					} else if (shbankMapZf.containsKey("status") && !shbankMapZf.get("status").equals("SUCCESS")) {
						resultMap.put("status", 300);
						resultMap.put("message", shbankMapZf.get("message"));
					} else if (shbankMapZf.containsKey("status") && shbankMapZf.get("status").equals("SUCCESS")) {
						resultMap.put("status", 200);
						resultMap.put("message", "上海银行注册成功");
					}

					return resultMap;
				}

				if (id.contains("egbank")) {

					logger.info("恒丰银行注册:" + id);

					id = id.replace("egbank", "");
					Map<String, String> egbankMapZf = wechatRegisterService.registerForEGBank(id, action);

					logger.info("status: " + egbankMapZf.get("status"));
					logger.info("message: " + egbankMapZf.get("message"));

					if (egbankMapZf == null || !egbankMapZf.containsKey("status")) {
						resultMap.put("status", 300);
						resultMap.put("message", "恒丰银行注册失败");
						return resultMap;
					} else {
						resultMap.put("status", 300);
						resultMap.put("message", egbankMapZf.get("message"));
					}

					return resultMap;
				}
			}

		}

		return resultMap;
	}
}
