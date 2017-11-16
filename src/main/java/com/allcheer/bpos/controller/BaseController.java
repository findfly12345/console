package com.allcheer.bpos.controller;

import com.allcheer.bpos.constant.NotifyException;
import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.util.Base64Util;
import com.allcheer.bpos.util.CryptUtil;
import com.allcheer.bpos.util.MD5Util;
import com.allcheer.bpos.util.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected String decryptRequest(String request) {
        String plainRequest = "";
        try {
            logger.debug("收到的密文: " + request);
            String sUrlDecode = URLDecoder.decode(request, "UTF-8");
            logger.debug("传入的参数对应Base64的转码值:" + sUrlDecode, new Object[0]);

            String sBase64Decode = new String(Base64Util.decode(sUrlDecode));
            logger.debug("传入的参数对应Base64转码前的值:" + sBase64Decode, new Object[0]);

            String sHexStr = CryptUtil.GetDecodeStr(sBase64Decode);
            logger.debug("传入的参数对应BCD转码前的值:" + sHexStr, new Object[0]);

            String sParamText = ParamUtil.getParamText(sHexStr);
            logger.debug("传入的参数值:" + sParamText, new Object[0]);

            String sInputSign = ParamUtil.getSign(sHexStr);
            logger.debug("传入的加签值:" + sInputSign, new Object[0]);

            String sCurSign = MD5Util.getMD5Str(sParamText + SystemConstant.ENCRYPT_HEX_STRING);
            logger.debug("算法的加签值:" + sCurSign, new Object[0]);
            if (!sCurSign.equals(sInputSign)) {
                logger.error("验签失败！");
            } else {
                plainRequest = sParamText;
                logger.debug("请求报文明文: " + plainRequest);
            }
        } catch (Exception e) {
            logger.error("解密错误: ", e);
        }
        return plainRequest;
    }

    protected abstract void doParamCheck(String plainRequest);

    protected abstract String doBusiness(String plainRequest);

    protected abstract String buildErrorResp(NotifyException notifyException);

    protected abstract String buildSuccessResp();

    protected void getJSONFromList(HttpServletResponse response, String jo) {
        response.setContentType("text/plain" + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
            response.getWriter().write(jo);
            response.getWriter().flush();
        } catch (IOException e) {
            logger.error("http返回失败", e);
        }
    }

    protected String buildXMLBody(Map<String, String> map) {
        Set<String> set = map.keySet();
        StringBuilder sb = new StringBuilder();
        for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
            String key = it.next();
            String value = map.get(key);
            if (null == value)
                value = "";
            sb.append("<" + key + ">" + value + "</" + key + ">");
        }
        return sb.toString();
    }

    public static String replaceExtraChar(String source) {
        String desc = source;
        if (source != null && source.trim() != "") {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(source);
            desc = m.replaceAll("");
        }

        return desc;
    }

}
