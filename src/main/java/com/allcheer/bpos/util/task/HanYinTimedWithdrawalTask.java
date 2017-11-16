package com.allcheer.bpos.util.task;

import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.entity.OUTCOMERECORDDO;
import com.allcheer.bpos.entity.OUTCOMERECORDDOExample;
import com.allcheer.bpos.entity.TblOutcomeRecordBatch;
import com.allcheer.bpos.entity.TblOutcomeRecordBatchExample;
import com.allcheer.bpos.entity.dao.HanYinBo;
import com.allcheer.bpos.mapper.OUTCOMERECORDDOMapper;
import com.allcheer.bpos.mapper.TblOutcomeRecordBatchMapper;
import com.allcheer.bpos.util.*;
import com.allcheer.bpos.util.Minsheng.HttpClient4Util;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

/**
 * 翰银定时提现
 * Created by pengleilei on 2017/5/8.
 */

@Component
@EnableScheduling
public class HanYinTimedWithdrawalTask {


    private final static Logger logger = LoggerFactory.getLogger(HanYinTimedWithdrawalTask.class);

    @Autowired
    private OUTCOMERECORDDOMapper outcomerecorddoMapper;

    @Autowired
    private TblOutcomeRecordBatchMapper tblOutcomeRecordBatchMapper;


    /**
     * 定时提现任务
     * 16/分钟查询/提现一次
     */

    //@Scheduled(cron = " 0 0/16 * * * ?")
    public void run() throws Exception {
        logger.info("===========翰银定时提现  START===========");
        long tm1 = System.currentTimeMillis();

        List<OUTCOMERECORDDO> outcomerecorddos = new ArrayList<OUTCOMERECORDDO>();
        //查询当日提现交易是否成功不成功的继续体现
        boolean flg = this.queryOutcomeRecordList(outcomerecorddos);
        if (flg) {
            for (OUTCOMERECORDDO o : outcomerecorddos) {
                //1、调用提现查询接口
                HanYinBo hanYinBo = this.outcomeQuery(o);
                if (hanYinBo != null) {
                    if ("01".equals(hanYinBo.getTransStatus())) {//未提现成功状态
                        //判断是否提现超过5次
                        boolean isFlg = this.isTransfini5(o);
                        if (isFlg) {
                            //报存原提现交易记录
                            boolean saveFlg = this.insertOuntBatch(o);
                            if (saveFlg) {
                                //开始提现（调用提现接口）

                                if ("交易失败(WD041)".equals(o.getChannelReturnMessage())) {
                                    o.setTransAmt("3000000");
                                } else {
                                    o.setTransAmt("");
                                }

                                Map<String, String> map = this.outCome(o);

                                String statusCode = map.get("statusCode");
                                String statusMsg = map.get("statusMsg");

                                //提现成功修改交易状态
                                o.setChannelReturnCode(statusCode);
                                o.setChannelReturnMessage(statusMsg);

                                //修改提现后的交易状态
                                this.updateOutCome(o);

                                logger.info("=====跑批单笔提现结束=====");
                            }
                        }
                    }
                }
            }

        } else
            logger.info("当日没有可再次提现的交易交易");


        long tm2 = System.currentTimeMillis();
        logger.info("===========END 翰银定时提现:" + (tm2 - tm1) + "毫秒===========");

    }


    /**
     * 将原交易保存到提现交易表（TBL_OUTCOME_RECORD_BATCH）
     *
     * @return
     */
    public boolean insertOuntBatch(OUTCOMERECORDDO o) {
        logger.info("step3：保存原交易");

        TblOutcomeRecordBatch tblOutcomeRecordBatch = new TblOutcomeRecordBatch();
        BeanUtils.copyProperties(o, tblOutcomeRecordBatch);
        tblOutcomeRecordBatch.setId(UUIDGenerator.getUUID().toUpperCase());//唯一键
        tblOutcomeRecordBatch.setRecvDate(DateUtil.getCurrentDate());//提现日期
        tblOutcomeRecordBatch.setRecvTime(DateUtil.getCurrentTime());//提现时间

        int ret = tblOutcomeRecordBatchMapper.insertSelective(tblOutcomeRecordBatch);
        if (ret == 1) {
            return true;
        }
        return false;
    }

    /**
     * 提现
     */
    public Map<String, String> outCome(OUTCOMERECORDDO o) {
        logger.info("step5：开始提现");

        Map<String, String> map = new HashMap<String, String>();
        try {
            String transAmt = o.getTransAmt() == null ? "" : o.getTransAmt();
            HanYinBo hanYinBo = new HanYinBo();
            hanYinBo.setMerId(o.getMerId());
            hanYinBo.sethYinsCode(SystemConstant.HAN_YIN_INST_CODE);
            hanYinBo.setInsMerchantCode(SystemConstant.HAN_YIN_INST_MER_NUMBER);
            hanYinBo.setMerDate(o.getMerDate());
            hanYinBo.setHyorderId(DateUtil.getCurrentDateTime() + "00");
            hanYinBo.setHpMerCode(o.getChannelReturnResv2());
            hanYinBo.setSignKey(SystemConstant.HAN_YIN_KEY);
            hanYinBo.setTransAmt(transAmt);
            String signature = hanYinBo.getSig();

            logger.info("返回报文提现参数" + signature);

            String sign = MD5ZX.signHy(signature, "UTF-8");

            hanYinBo.setSignature(sign);
            List<NameValuePair> nvps = new LinkedList<NameValuePair>();
            nvps.add(new BasicNameValuePair("insCode", hanYinBo.gethYinsCode()));
            nvps.add(new BasicNameValuePair("insMerchantCode", hanYinBo.getInsMerchantCode()));
            nvps.add(new BasicNameValuePair("hpMerCode", o.getChannelReturnResv2()));
            nvps.add(new BasicNameValuePair("orderNo", hanYinBo.getHyorderId()));
            nvps.add(new BasicNameValuePair("transDate", hanYinBo.getMerDate()));
            nvps.add(new BasicNameValuePair("transAmount", transAmt));
            nvps.add(new BasicNameValuePair("signature", hanYinBo.getSignature()));

            byte[] b = HttpClient4Util.getInstance().doPost(SystemConstant.HAN_YIN_TX, null, nvps);
            String respStr = new String(b, "UTF-8");
            logger.info("返回报文[{}]", new Object[]{respStr});

            if (StringUtils.isNotBlank(respStr)) {
                map = JsonUtil.toMap(respStr);
            }
        } catch (Exception ex) {
            logger.error("发送报文或解析应答异常");
        }

        return map;
    }


    /**
     * 判断是否提现超过5次
     */
    public boolean isTransfini5(OUTCOMERECORDDO o) {
        logger.info("step4：判断提现次数是否超限");

        TblOutcomeRecordBatchExample tblOutcomeRecordBatchExample = new TblOutcomeRecordBatchExample();
        tblOutcomeRecordBatchExample.createCriteria().andMerTransIdEqualTo(o.getMerTransId()).andMerDateEqualTo(o.getMerDate());
        List<TblOutcomeRecordBatch> tblOutcomeRecordBatches = tblOutcomeRecordBatchMapper.selectByExample(tblOutcomeRecordBatchExample);
        if (tblOutcomeRecordBatches != null && tblOutcomeRecordBatches.size() >= 4) {
            logger.info("提现次数超限,拒绝再次体现");
            return false;
        }

        return true;
    }

    /**
     * 查询当日交易
     */
    private boolean queryOutcomeRecordList(List<OUTCOMERECORDDO> outcomerecorddos) {
        logger.info("step1：查询当日提现交易是否成功");

        OUTCOMERECORDDOExample outcomerecorddoExample = new OUTCOMERECORDDOExample();
        outcomerecorddoExample.createCriteria().andTransStatusEqualTo("P").andMerDateEqualTo(DateUtil.getCurrentDate()).andChannelIdEqualTo("HY00");
        outcomerecorddoExample.setOrderByClause("recv_time");
        List<OUTCOMERECORDDO> list = outcomerecorddoMapper.selectByExample(outcomerecorddoExample);
        if (list != null && list.size() > 0) {
            outcomerecorddos.addAll(list);
            return true;
        }

        return false;
    }


    /**
     * 修改提修改的交易
     *
     * @param outcomerecorddo
     */
    public void updateOutCome(OUTCOMERECORDDO outcomerecorddo) {
        logger.info("修改提现交易状态");

        OUTCOMERECORDDOExample outcomerecorddoExample = new OUTCOMERECORDDOExample();
        outcomerecorddoExample.createCriteria().andIdEqualTo(outcomerecorddo.getId());
        outcomerecorddoMapper.updateByExample(outcomerecorddo, outcomerecorddoExample);
    }


    /**
     * 翰银查询接口
     *
     * @throws Exception
     */
    public HanYinBo outcomeQuery(OUTCOMERECORDDO o) throws Exception {
        logger.info("step2：调用提现查询接口");

        String[] keys = {"merId", "orderNo",};
        String[] params = {o.getMerId(), o.getMerTransId()};

        logger.info(o.getMerId() + "||" + o.getMerTransId());

        return build(keys, params, "hanYin/TimedWithdrawal");
    }

    private HanYinBo build(String[] keys, String[] params,
                           String service) throws UnsupportedEncodingException {
        String str = _MakeURL(keys, params);
        String sign = MD5Util.getMD5Str(str + "0123456789ABCDEF");
        logger.info("MD5 SIGN : " + sign);
        StringBuilder sb = new StringBuilder(str.toString());
        sb.append("&");
        sb.append("sign");
        sb.append('=');
        sb.append(sign);
        String paramStr = CryptUtil.GetEncodeStr(sb.toString());
        String finalStr = Base64Util.encode(paramStr.getBytes());
        String strJson = "";
        HanYinBo hanYinBo = new HanYinBo();
        try {
            finalStr = filter(finalStr);
            finalStr = URLEncoder.encode(finalStr, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String param = "sText=" + finalStr;
        InputStream is = null;
        HttpURLConnection httpUrlConnection = null;
        try {
            URL url = new URL("http://10.1.0.54:8090/outcome/hanYin/TimedWithdrawal");
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
                StringBuffer sb1 = new StringBuffer();
                String readLine = new String();
                BufferedReader responseReader = new BufferedReader(
                        new InputStreamReader(httpUrlConnection.getInputStream()));
                while ((readLine = responseReader.readLine()) != null) {
                    sb1.append(readLine).append("\n");
                }
                strJson = sb1.toString();
                responseReader.close();
            }

            logger.info("查询提现返回 : " + strJson);
            //将json字符串转对象
            hanYinBo = (HanYinBo) JsonUtil.toObject(strJson, hanYinBo);
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

        return hanYinBo;
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


}
