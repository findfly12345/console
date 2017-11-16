package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.OUTCOMERECORDDO;
import com.allcheer.bpos.entity.OUTCOMERECORDDOExample;
import com.allcheer.bpos.entity.vo.LocalTxEntity;
import com.allcheer.bpos.mapper.OUTCOMERECORDDOMapper;
import com.allcheer.bpos.service.InstMerTXService;
import com.allcheer.bpos.service.WechatRegisterService;
import com.allcheer.bpos.service.impl.WechatRegisterServiceImpl;
import com.allcheer.bpos.util.*;
import com.allcheer.bpos.util.constant.CommonConstants;
import com.allcheer.bpos.util.task.HanYinTimedWithdrawalTask;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PENGLEILEI on 2017/5/10.
 * 机构商户提现
 */

@Controller
@RequestMapping(value = "/instMerTX")
public class InsetMerTXController {


    private final static Logger logger = LoggerFactory.getLogger(InsetMerTXController.class);

    @Autowired
    private InstMerTXService instMerTXService;

    @Autowired
    private HanYinTimedWithdrawalTask hanYinTimedWithdrawalTask;

    @Autowired
    private OUTCOMERECORDDOMapper outcomerecorddoMapper;

    @Autowired
    private WechatRegisterService wechatRegisterService;


    /**
     * 查询提现列表
     *
     * @param outcomerecorddo
     * @param modelAndView
     * @return
     */

    @RequestMapping(value = "/queryInstMerTxList")
    private String queryInstMerTxList(@ModelAttribute("outcomerecorddo") OUTCOMERECORDDO outcomerecorddo, ModelAndView modelAndView) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap = Bean2Map.beanToMapNoFill(outcomerecorddo);

        Pagination<OUTCOMERECORDDO> tblMerInfoVOPagination = instMerTXService.queryList(queryMap, outcomerecorddo);
        outcomerecorddo.setPagination(tblMerInfoVOPagination);

        return "/instMerTX/instMerTxList";

    }


    /**
     * 翰银提现接口
     *
     * @param request
     * @param response
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/hanYin_tx", method = RequestMethod.POST)
    public Map wechatRegister(HttpServletRequest request, HttpServletResponse response) {
        Map resultMap = new HashMap();
        String txId = request.getParameter("txId");
        //查询当比交易
        OUTCOMERECORDDO outcomerecorddo = this.queryOutComeById(txId);

        if (outcomerecorddo != null) {
            //判断是否提现超过5次
            boolean isFlg = hanYinTimedWithdrawalTask.isTransfini5(outcomerecorddo);

            if (isFlg) {
                //保存原提现交易记录
                boolean saveFlg = hanYinTimedWithdrawalTask.insertOuntBatch(outcomerecorddo);

                if (saveFlg) {

                    //根据订单号查询原体现交易数据
                    List<LocalTxEntity> list = instMerTXService.list(DateUtil.getCurrentDate(), outcomerecorddo.getMerTransId());
                    if (list != null && list.size() > 0) {
                        //开始提现
                        String amt = "";
                        if ("交易失败(WD041)".equals(outcomerecorddo.getChannelReturnMessage())) {
                            amt = "3000000";
                        }

                        try {
                            Map<String, String> map = this.singleOut(DateUtil.getCurrentDate(), outcomerecorddo.getMerTransId(), amt);

                            String statusCode = map.get("respCode");
                            String statusMsg = map.get("respDesc");

                            if ("000000".equals(statusCode)) {
                                //提现成功修改交易状态
                                resultMap.put("status", 200);
                                resultMap.put("message", statusMsg);
                            } else if ("51".equals(statusCode)) {
                                resultMap.put("status", 200);
                                resultMap.put("message", "余额不足");
                                statusMsg = "余额不足";
                            } else {
                                resultMap.put("status", 300);
                                resultMap.put("message", statusMsg);
                            }

                            logger.info("修改体现交易状态");

                            outcomerecorddo.setChannelReturnCode(statusCode);
                            outcomerecorddo.setChannelReturnMessage(statusMsg);

                            hanYinTimedWithdrawalTask.updateOutCome(outcomerecorddo);

                            logger.info("=====手动提现结束=====");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            } else {
                resultMap.put("status", 300);
                resultMap.put("message", "提现次数已超过5次");
            }
        } else {
            resultMap.put("status", 300);
            resultMap.put("message", "提现失败");
        }


        return resultMap;
    }


    /**
     * 单笔提现
     */
    private Map<String, String> singleOut(String date, String ordId, String amt) throws Exception {

        //查询提现交易表是否存在记录存在则删除
        int rel = instMerTXService.delByOrdId(ordId);
        if (rel > 0) {
            logger.info(" ==========删除提现交易成功 ==========");
        }

        Map<String, String> map = new HashMap<String, String>();

        List<LocalTxEntity> list = instMerTXService.list(date, ordId);
        if (list != null && list.size() > 0) {
            LocalTxEntity l = list.get(0);
            //订单号   商户号  机构号  终端号  金额
            String[] keys = {"orderNo", "merId", "instId", "merDate", "termId", "transAmount"};
            String[] params = {l.getOrd_id(), l.getMachine_no(), l.getAcq_inst_id_code(), date, l.getInst_mer_term_id(), amt};
            String json = build(keys, params, "/hanYin/outcome");
            map = JsonUtil.toMap(json);
        }

        return map;
    }


    /**
     * 根据ID查询提现交易
     */
    private OUTCOMERECORDDO queryOutComeById(String id) {
        OUTCOMERECORDDOExample outcomerecorddoExample = new OUTCOMERECORDDOExample();
        outcomerecorddoExample.createCriteria().andIdEqualTo(id);

        List<OUTCOMERECORDDO> outcomerecorddos = outcomerecorddoMapper.selectByExample(outcomerecorddoExample);
        if (outcomerecorddos != null && outcomerecorddos.size() > 0) {
            return outcomerecorddos.get(0);
        }

        return null;
    }


    private String build(String[] keys, String[] params, String service) throws UnsupportedEncodingException {
        String str = _MakeURL(keys, params);
        logger.info(str);
        String sign = MD5Util.getMD5Str(str + "0123456789ABCDEF");

        logger.info("after MD5 : " + sign);

        StringBuilder sb = new StringBuilder(str.toString());
        sb.append("&");
        sb.append("sign");
        sb.append('=');
        sb.append(sign);
        logger.info(sb.toString());

        String paramStr = CryptUtil.GetEncodeStr(sb.toString());
        String finalStr = Base64Util.encode(paramStr.getBytes());
        try {
            finalStr = filter(finalStr);
            finalStr = URLEncoder.encode(finalStr, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        String param = "sText=" + finalStr;
        logger.info(param);

        InputStream is = null;
        HttpURLConnection httpUrlConnection = null;
        StringBuffer sb1 = new StringBuffer();
        try {
            URL url = new URL("http://10.1.0.54:8090/outcome/" + service);
            URLConnection urlConnection = url.openConnection();
            httpUrlConnection = (HttpURLConnection) urlConnection;

            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setRequestMethod("POST");

            httpUrlConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpUrlConnection.setRequestProperty("Charset", "UTF-8");

            httpUrlConnection.connect();

            DataOutputStream dos = new DataOutputStream(httpUrlConnection.getOutputStream());
            dos.writeBytes(param);
            dos.flush();
            dos.close();

            int resultCode = httpUrlConnection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == resultCode) {
                String readLine = new String();
                BufferedReader responseReader = new BufferedReader(
                        new InputStreamReader(httpUrlConnection.getInputStream()));
                while ((readLine = responseReader.readLine()) != null) {
                    sb1.append(readLine).append("\n");
                }
                responseReader.close();
                logger.info("{返回}" + sb1.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }
        }

        return sb1.toString();
    }

    public static String _MakeURL(String[] keys, String[] params) {
        if (keys.length != params.length) {
            return null;
        }

        StringBuilder url = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            url.append('&');
            url.append(keys[i]);
            url.append('=');
            url.append(params[i]);
        }

        return url.toString().replaceFirst("&", "");
    }

    private static String filter(String str) throws UnsupportedEncodingException {
        String output = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int asc = str.charAt(i);
            if ((asc != 10) && (asc != 13))
                sb.append(str.subSequence(i, i + 1));
        }
        output = new String(sb.toString().getBytes(), "UTF-8");
        return output;
    }


    /**
     * 翰银手动进件
     */

    @RequestMapping(value = "/local/register")
    private void localRegister(HttpServletRequest request, HttpServletResponse response) {

        logger.info(" ==========START 手动进件 ==========");

        String merId = request.getParameter("merId");
        String operFlag = request.getParameter("operFlag");

        logger.info("merId:" + merId);
        logger.info("operFlag:" + operFlag);

        try {
            Map<String, String> map = wechatRegisterService.registerForHanyin(merId, WechatRegisterServiceImpl.HAN_YIN_CHANNEL, operFlag);
            map.put("merId", merId);

            if (CommonConstants.OPER_FLAG_A.equals(operFlag)) {
                wechatRegisterService.addHanYinMapGen(map, WechatRegisterServiceImpl.HAN_YIN_CHANNEL);
            }

            Gson gson = new Gson();
            String json = gson.toJson(map);

            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(" ==========手动进件 END==========");

    }


}
