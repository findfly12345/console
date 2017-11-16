package com.allcheer.bpos.service.impl;

import c.a.b.leshua.*;
import com.alibaba.fastjson.JSONObject;
import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.domain.LeShuaMerInfoBO;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.entity.vo.LeShuaMerInfoVO;
import com.allcheer.bpos.mapper.*;
import com.allcheer.bpos.service.LeShuaMerService;
import com.allcheer.bpos.util.*;
import com.github.pagehelper.PageHelper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.util.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by APPLE on 2017/8/29.
 */
@Service
public class LeShuaMerServiceImpl implements LeShuaMerService {

    private Logger logger = LoggerFactory.getLogger(LeShuaMerServiceImpl.class);

    @Autowired
    private LeShuaMerInfoCustDOMapper leShuaMerInfoCustDOMapper;

    @Autowired
    private TblMerInfoDOMapper tblMerInfoDOMapper;

    @Autowired
    private TblMerBankInfoDOMapper tblMerBankInfoDOMapper;

    @Autowired
    private LeShuaMerInfoDOMapper leShuaMerInfoDOMapper;

    @Autowired
    private LeShuaLogDOMapper leShuaLogDOMapper;

    @Autowired
    private SeqMapper seqMapper;

    @Autowired
    private TblMerFeeInfoDOMapper tblMerFeeInfoDOMapper;

    @Autowired
    private LeShuaMerFuncInfoDOMapper leShuaMerFuncInfoDOMapper;

    @Autowired
    private TblRouteControlMapper tblRouteControlMapper;

    @Autowired
    private TblMerFileInfoDOMapper tblMerFileInfoDOMapper;

    @Autowired
    private TblMerLeshuaAddressDoMapper tblMerLeshuaAddressDoMapper;

    @Override
    public Pagination<LeShuaMerInfoVO> getLeShuaMerInfoList(Map map) {

        int count = leShuaMerInfoCustDOMapper.countLeShuaMerInfoList(map);
        int pageCurrent = Integer.valueOf((String) map.get("pageCurrent"));
        int pageSize = Integer.valueOf((String) map.get("pageSize"));

        Pagination pagination = new Pagination(count, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);
        List<LeShuaMerInfoBO> leShuaMerInfoBOList = leShuaMerInfoCustDOMapper.getLeShuaMerInfoList(map);
        List<LeShuaMerInfoVO> leShuaMerInfoVOList = new ArrayList<>();
        for(LeShuaMerInfoBO leShuaMerInfoBO:leShuaMerInfoBOList) {
            LeShuaMerInfoVO leShuaMerInfoVO = new LeShuaMerInfoVO();
            leShuaMerInfoVO.setMerId(leShuaMerInfoBO.getMerId());
            leShuaMerInfoVO.setMerName(leShuaMerInfoBO.getMerName());
            leShuaMerInfoVO.setLeShuaMerId(leShuaMerInfoBO.getLeShuaMerId());
            leShuaMerInfoVO.setLeShuaMerName(leShuaMerInfoBO.getLeShuaMerName());
            leShuaMerInfoVO.setRegistedLeShua(StringUtils.isEmpty(leShuaMerInfoBO.getLeShuaMerId())?"0":"1");
            LeShuaMerFuncInfoDOKey leShuaMerFuncInfoDOKey = new LeShuaMerFuncInfoDOKey();
            leShuaMerFuncInfoDOKey.setMerId(leShuaMerInfoBO.getMerId());
            leShuaMerFuncInfoDOKey.setLeShuaMerId(leShuaMerInfoBO.getLeShuaMerId());
            leShuaMerInfoVO.setOpenedShaoMa(leShuaMerFuncInfoDOMapper.selectByPrimaryKey(leShuaMerFuncInfoDOKey) == null?"0":"1");
            if(leShuaMerInfoBO.getUpdateTime() != null) {
                leShuaMerInfoVO.setUpdateTime(DateUtil.stringTostring(leShuaMerInfoBO.getUpdateTime(),DateUtil.PATTERN_TIME_14,DateUtil.PATTERN_STANDARD));
            }
            if(leShuaMerInfoBO.getCreateTime() != null) {
                leShuaMerInfoVO.setCreateTime(DateUtil.stringTostring(leShuaMerInfoBO.getCreateTime(),DateUtil.PATTERN_TIME_14,DateUtil.PATTERN_STANDARD));
            }
            leShuaMerInfoVOList.add(leShuaMerInfoVO);
        }
        pagination.addResult(leShuaMerInfoVOList);
        return pagination;
    }

    @Override
    public Map register(String memberId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(memberId);
        if(tblMerInfoDO == null) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "商户非法!");
            return resultMap;
        }

        LeShuaMerInfoDO leShuaMerInfoDO = leShuaMerInfoDOMapper.selectByPrimaryKey(memberId);
        if(leShuaMerInfoDO != null) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "商户已完成进件!");
            return resultMap;
        }

        BaseReq baseReq = new BaseReq();
        baseReq.setAgentId(SystemConstant.LE_SHUA_AGENT_ID);
        baseReq.setReqSerialNo(getReqSerialNo());
        baseReq.setVersion("1.0");
        MerRegistInfo merRegistInfo = new MerRegistInfo();
        merRegistInfo.setBase(getMerBaseInfo(memberId));
        merRegistInfo.setAccount(getMerAccountInfo(memberId));
        baseReq.setData(JsonUtil.toJson(merRegistInfo));
        String signString = "lepos" + SystemConstant.LE_SHUA_MD5_KEY + JsonUtil.toJson(merRegistInfo);
        String md5String = MD5ZX.signHy(signString,"UTF-8");
        String sign = Base64.encodeBase64String(md5String.getBytes());
        baseReq.setSign(StringUtils.replaceBlank(sign));
        LeShuaLogDO leShuaLogDO = new LeShuaLogDO();
        leShuaLogDO.setReqSerialNo(baseReq.getReqSerialNo());
        leShuaLogDO.setBusinessType("1");
        leShuaLogDO.setReqData(baseReq.getData());
        leShuaLogDO.setSign(baseReq.getSign());
        leShuaLogDO.setUpdateTime(DateUtil.getCurrentDateTime());
        leShuaLogDO.setCreateTime(DateUtil.getCurrentDateTime());
        leShuaLogDOMapper.insert(leShuaLogDO);


        String param = "agentId="+baseReq.getAgentId()+"&version="+baseReq.getVersion()+"&reqSerialNo="+baseReq.getReqSerialNo()+"&sign="+baseReq.getSign()+"&data="+baseReq.getData();
        logger.info("乐刷商户进件地址参数:" + param);

        Map<String,String> map = new HashMap<String,String>();
        map.put("agentId", baseReq.getAgentId());
        map.put("version", baseReq.getVersion());
        map.put("reqSerialNo", baseReq.getReqSerialNo());
        map.put("sign", baseReq.getSign());
        map.put("data", baseReq.getData());

        String registerurl;
        try {
            registerurl = SystemConstant.LE_SHUA_MER_URL + "/register.do";
            logger.info("乐刷商户进件地址:" + registerurl);
            String str= HttpClientUtils.postForm(registerurl,map,null, 10000, 10000);
            if (!StringUtils.isEmpty(str)) {
                logger.info("乐刷商户进件返回参数{}",str.toString());
                BaseResp baseResp = new BaseResp();
                baseResp = (BaseResp)JsonUtil.toObject(str.toString(), baseResp);
                leShuaLogDO.setRespCode(baseResp.getRespCode());
                leShuaLogDO.setRespMsg(baseResp.getRespMsg());
                if(baseResp.getRespCode().equals("000000")) {
                    String data = JsonUtil.toJson(baseResp.getData());
                    LeShuaMerInfo leShuaMerInfo = new LeShuaMerInfo();
                    leShuaMerInfo = (LeShuaMerInfo) JsonUtil.toObject(data,leShuaMerInfo);
                    resultMap.put("statusCode", 200);
                    leShuaLogDO.setRespData(data);
                    leShuaMerInfoDO = new LeShuaMerInfoDO();
                    leShuaMerInfoDO.setMerId(memberId);
                    leShuaMerInfoDO.setLeShuaMerId(leShuaMerInfo.getMerchantId());
                    leShuaMerInfoDO.setLeShuaMerName(leShuaMerInfo.getUsername());
                    leShuaMerInfoDO.setUpdateTime(DateUtil.getCurrentDateTime());
                    leShuaMerInfoDO.setCreateTime(DateUtil.getCurrentDateTime());
                    leShuaMerInfoDOMapper.insert(leShuaMerInfoDO);
                } else {
                    resultMap.put("statusCode", 300);
                }
                resultMap.put("message", baseResp.getRespMsg());
            } else {
                leShuaLogDO.setRespCode("300");
                leShuaLogDO.setRespMsg("报备失败");
                resultMap.put("statusCode", 300);
                resultMap.put("message", "进件失败");
            }
        } catch (IOException e) {
            logger.error("进件异常{}",e);
            leShuaLogDO.setRespCode("300");
            leShuaLogDO.setRespMsg("进件异常");
            resultMap.put("statusCode", 300);
            resultMap.put("message", "进件异常!");
        } catch (Exception e) {
            logger.error("进件异常{}",e);
            leShuaLogDO.setRespCode("300");
            leShuaLogDO.setRespMsg("进件异常");
            resultMap.put("statusCode", 300);
            resultMap.put("message", "进件异常!");
        }
        leShuaLogDO.setUpdateTime(DateUtil.getCurrentDateTime());

        leShuaLogDOMapper.updateByPrimaryKeySelective(leShuaLogDO);


        return resultMap;
    }

    @Override
    public Map registerBusiness(String memberId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(memberId);
        if(tblMerInfoDO == null) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "商户非法!");
            return resultMap;
        }

        LeShuaMerInfoDO leShuaMerInfoDO = leShuaMerInfoDOMapper.selectByPrimaryKey(memberId);
        if(leShuaMerInfoDO != null) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "商户已完成进件!");
            return resultMap;
        }

        String reqSerialNo = getReqSerialNo();

        JSONObject baseInfo = buildBasicInfo2(memberId);
        JSONObject accountInfo = buildAccountInfo2(memberId);
        JSONObject dataJsonObject = buildData(baseInfo, accountInfo, null);

        String signString = "lepos" + SystemConstant.LE_SHUA_MD5_KEY + dataJsonObject.toJSONString();
        String md5String = MD5ZX.signHy(signString,"UTF-8");
        String sign = StringUtils.replaceBlank(Base64.encodeBase64String(md5String.getBytes()));

        LeShuaLogDO leShuaLogDO = new LeShuaLogDO();
        leShuaLogDO.setReqSerialNo(reqSerialNo);
        leShuaLogDO.setBusinessType("1");
        leShuaLogDO.setReqData(dataJsonObject.toJSONString());
        leShuaLogDO.setSign(sign);
        leShuaLogDO.setUpdateTime(DateUtil.getCurrentDateTime());
        leShuaLogDO.setCreateTime(DateUtil.getCurrentDateTime());
        leShuaLogDOMapper.insert(leShuaLogDO);

        String param = "agentId="+SystemConstant.LE_SHUA_AGENT_ID+"&version=1.0&reqSerialNo="+reqSerialNo+"&sign="+sign+"&data="+dataJsonObject.toJSONString();
        logger.info("乐刷企业商户进件地址参数:" + param);

        StringBody signBody = new StringBody(sign, ContentType.APPLICATION_JSON);
        StringBody dataBody = new StringBody(dataJsonObject.toJSONString(), ContentType.APPLICATION_JSON);
        String registerurl;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            registerurl = SystemConstant.LE_SHUA_MER_URL + "/register.do";
            HttpPost httppost = new HttpPost(registerurl);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            multipartEntityBuilder.setCharset(Charset.forName("utf-8"));
            StringBody vtBody = new StringBody("1.0", ContentType.APPLICATION_JSON);
            StringBody agentIdBody = new StringBody(SystemConstant.LE_SHUA_AGENT_ID, ContentType.APPLICATION_JSON);
            StringBody serialBody = new StringBody(reqSerialNo, ContentType.APPLICATION_JSON);
            multipartEntityBuilder = multipartEntityBuilder.addPart("agentId", agentIdBody);
            multipartEntityBuilder = multipartEntityBuilder.addPart("version", vtBody);
            multipartEntityBuilder = multipartEntityBuilder.addPart("reqSerialNo", serialBody);
            multipartEntityBuilder = multipartEntityBuilder.addPart("sign", signBody);
            multipartEntityBuilder = multipartEntityBuilder.addPart("data", dataBody);
            TblMerFileInfoDOExample tblMerFileInfoDOExample = new TblMerFileInfoDOExample();
            tblMerFileInfoDOExample.createCriteria().andMerIdEqualTo(memberId);
            List<TblMerFileInfoDO> tblMerFileInfoDOList = tblMerFileInfoDOMapper.selectByExample(tblMerFileInfoDOExample);
            if(tblMerFileInfoDOList == null || tblMerFileInfoDOList.size() == 0) {
                resultMap.put("statusCode", 300);
                resultMap.put("message", "商户还未上传照片!");
                return resultMap;
            }

            TblMerFileInfoDO tblMerFileInfoDO = tblMerFileInfoDOList.get(0);
            if(!StringUtils.isEmpty(tblMerFileInfoDO.getPicAddress2())) {
                multipartEntityBuilder = multipartEntityBuilder.addPart("idcardFrontPic", new FileBody(new File(tblMerFileInfoDO.getPicAddress2())));
            }
            if(!StringUtils.isEmpty(tblMerFileInfoDO.getPicAddress3())) {
                multipartEntityBuilder = multipartEntityBuilder.addPart("idcardBackPic", new FileBody(new File(tblMerFileInfoDO.getPicAddress3())));
            }
            if(!StringUtils.isEmpty(tblMerFileInfoDO.getPicAddress10())) {
                multipartEntityBuilder = multipartEntityBuilder.addPart("bankCardFrontPic", new FileBody(new File(tblMerFileInfoDO.getPicAddress10())));
            }
            if(!StringUtils.isEmpty(tblMerFileInfoDO.getPicAddress1())) {
                multipartEntityBuilder = multipartEntityBuilder.addPart("licensePic", new FileBody(new File(tblMerFileInfoDO.getPicAddress1())));
            }
            if(!StringUtils.isEmpty(tblMerFileInfoDO.getPicAddress8())) {
                multipartEntityBuilder = multipartEntityBuilder.addPart("insidePic", new FileBody(new File(tblMerFileInfoDO.getPicAddress8())));
            }
            if(!StringUtils.isEmpty(tblMerFileInfoDO.getPicAddress7())) {
                multipartEntityBuilder = multipartEntityBuilder.addPart("doorPic", new FileBody(new File(tblMerFileInfoDO.getPicAddress7())));
            }
            if(!StringUtils.isEmpty(tblMerFileInfoDO.getPicAddress15())) {
                multipartEntityBuilder = multipartEntityBuilder.addPart("cashierDeskPic", new FileBody(new File(tblMerFileInfoDO.getPicAddress15())));
            }
            if(!StringUtils.isEmpty(tblMerFileInfoDO.getPicAddress13())) {
                multipartEntityBuilder = multipartEntityBuilder.addPart("agreementPic", new FileBody(new File(tblMerFileInfoDO.getPicAddress13())));
            }

            HttpEntity reqEntity = multipartEntityBuilder.build();

            httppost.setEntity(reqEntity);


            logger.info("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    logger.info("Response content length: " + resEntity.getContentLength());
                    String content = IOUtils.toString(resEntity.getContent());
                    if (!StringUtils.isEmpty(content)) {
                        logger.info("乐刷商户进件返回参数{}",content.toString());
                        BaseResp baseResp = new BaseResp();
                        baseResp = (BaseResp)JsonUtil.toObject(content.toString(), baseResp);
                        leShuaLogDO.setRespCode(baseResp.getRespCode());
                        leShuaLogDO.setRespMsg(baseResp.getRespMsg());
                        if(baseResp.getRespCode().equals("000000")) {
                            String data = JsonUtil.toJson(baseResp.getData());
                            LeShuaMerInfo leShuaMerInfo = new LeShuaMerInfo();
                            leShuaMerInfo = (LeShuaMerInfo) JsonUtil.toObject(data,leShuaMerInfo);
                            resultMap.put("statusCode", 200);
                            leShuaLogDO.setRespData(data);
                            leShuaMerInfoDO = new LeShuaMerInfoDO();
                            leShuaMerInfoDO.setMerId(memberId);
                            leShuaMerInfoDO.setLeShuaMerId(leShuaMerInfo.getMerchantId());
                            leShuaMerInfoDO.setLeShuaMerName(leShuaMerInfo.getUsername());
                            leShuaMerInfoDO.setUpdateTime(DateUtil.getCurrentDateTime());
                            leShuaMerInfoDO.setCreateTime(DateUtil.getCurrentDateTime());
                            leShuaMerInfoDOMapper.insert(leShuaMerInfoDO);
                        } else {
                            resultMap.put("statusCode", 300);
                        }
                        resultMap.put("message", baseResp.getRespMsg());
                    } else {
                        leShuaLogDO.setRespCode("300");
                        leShuaLogDO.setRespMsg("报备失败");
                        resultMap.put("statusCode", 300);
                        resultMap.put("message", "进件失败");
                    }
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            logger.error("进件异常{}",e);
            leShuaLogDO.setRespCode("300");
            leShuaLogDO.setRespMsg("进件异常");
            resultMap.put("statusCode", 300);
            resultMap.put("message", "进件异常!");
        } catch (IOException e) {
            logger.error("进件异常{}",e);
            leShuaLogDO.setRespCode("300");
            leShuaLogDO.setRespMsg("进件异常");
            resultMap.put("statusCode", 300);
            resultMap.put("message", "进件异常!");
        }  finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        leShuaLogDO.setUpdateTime(DateUtil.getCurrentDateTime());
        leShuaLogDOMapper.updateByPrimaryKeySelective(leShuaLogDO);
        return resultMap;
    }

    @Override
    public String getReqSerialNo() {
        long leShuaSeq = seqMapper.getSequenceNextVal("LE_SHUA_SEQ");
        return DateUtil.getCurrentDate()+StringUtils.leftPad(String.valueOf(leShuaSeq),8,'0');
    }

    @Override
    public MerBaseInfo getMerBaseInfo(String memberId) {
        MerBaseInfo merBaseInfo = new MerBaseInfo();
        TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(memberId);
        merBaseInfo.setMerchantType(1);
        merBaseInfo.setName(tblMerInfoDO.getLegalPerson());
        merBaseInfo.setIdcard(tblMerInfoDO.getLegalPersonCertNm());
        merBaseInfo.setMobile(tblMerInfoDO.getContactMobile());
        merBaseInfo.setMerchantName(tblMerInfoDO.getLegalPerson());
        TblMerLeshuaAddressDoExample tblMerLeshuaAddressDoExample = new TblMerLeshuaAddressDoExample();
        tblMerLeshuaAddressDoExample.createCriteria().andMerIdEqualTo(memberId);
        List<TblMerLeshuaAddressDo> tblMerLeshuaAddressDoList = tblMerLeshuaAddressDoMapper.selectByExample(tblMerLeshuaAddressDoExample);
        if(tblMerLeshuaAddressDoList != null && tblMerLeshuaAddressDoList.size() > 0) {
            TblMerLeshuaAddressDo tblMerLeshuaAddressDo = tblMerLeshuaAddressDoList.get(0);
            merBaseInfo.setProvince(tblMerLeshuaAddressDo.getProvinceId());
            merBaseInfo.setCity(tblMerLeshuaAddressDo.getCityId());
            merBaseInfo.setArea(tblMerLeshuaAddressDo.getAreaId());
            merBaseInfo.setAddress(tblMerLeshuaAddressDo.getDetailAddress());
        }
        return merBaseInfo;
    }

    @Override
    public MerAccountInfo getMerAccountInfo(String memberId) {
        MerAccountInfo merAccountInfo = new MerAccountInfo();
        TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(memberId);
        TblMerBankInfoDO tblMerBankInfoDO = tblMerBankInfoDOMapper.selectByPrimaryKey(memberId);
        if(tblMerBankInfoDO.getIsPrivate().equals("Y")) {
            merAccountInfo.setType(1);
        } else {
            merAccountInfo.setType(2);
        }
        merAccountInfo.setBranch(tblMerBankInfoDO.getBankBranchName());
        merAccountInfo.setUnionpay(tblMerBankInfoDO.getCnaps());
        merAccountInfo.setHolder(tblMerBankInfoDO.getAcctName());
        merAccountInfo.setCardId(tblMerBankInfoDO.getAcctNo());
        merAccountInfo.setMobile(tblMerInfoDO.getContactMobile());
        return merAccountInfo;
    }

    @Override
    public Map openShaoMa(String memberId,String merchantId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        BaseReq baseReq = new BaseReq();
        baseReq.setAgentId(SystemConstant.LE_SHUA_AGENT_ID);
        baseReq.setReqSerialNo(getReqSerialNo());
        baseReq.setVersion("V1.0.0");
        OpenShaoMaInfo openShaoMaInfo = new OpenShaoMaInfo();
        if(getWeixin(memberId) == null) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "微信费率为空!");
            return resultMap;
        }
        if(getAlipay(memberId) == null) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "支付宝费率为空!");
            return resultMap;
        }
        OpenFee openFee = new OpenFee();
        openFee.setWeixin(getWeixin(memberId));
        openFee.setAlipay(getAlipay(memberId));
        openFee.setOpenType(1);
        openFee.setMerchantId(merchantId);
        openShaoMaInfo.setFee(openFee);
        baseReq.setData(JsonUtil.toJson(openShaoMaInfo));
        String signString = "lepos" + SystemConstant.LE_SHUA_MD5_KEY + JsonUtil.toJson(openShaoMaInfo);
        String md5String = MD5ZX.signHy(signString,"UTF-8");
        String sign = Base64.encodeBase64String(md5String.getBytes());
        baseReq.setSign(StringUtils.replaceBlank(sign));
        baseReq.setData(JsonUtil.toJson(openShaoMaInfo));

        LeShuaLogDO leShuaLogDO = new LeShuaLogDO();
        leShuaLogDO.setReqSerialNo(baseReq.getReqSerialNo());
        leShuaLogDO.setBusinessType("2");
        leShuaLogDO.setReqData(baseReq.getData());
        leShuaLogDO.setSign(baseReq.getSign());
        leShuaLogDO.setUpdateTime(DateUtil.getCurrentDateTime());
        leShuaLogDO.setCreateTime(DateUtil.getCurrentDateTime());
        leShuaLogDOMapper.insert(leShuaLogDO);

        String param = "agentId="+baseReq.getAgentId()+"&version="+baseReq.getVersion()+"&reqSerialNo="+baseReq.getReqSerialNo()+"&sign="+baseReq.getSign()+"&data="+baseReq.getData();
        logger.info("乐刷开通扫码功能参数:" + param);

        Map<String,String> map = new HashMap<String,String>();
        map.put("agentId", baseReq.getAgentId());
        map.put("version", baseReq.getVersion());
        map.put("reqSerialNo", baseReq.getReqSerialNo());
        map.put("sign", baseReq.getSign());
        map.put("data", baseReq.getData());

        String registerurl;
        try {
            registerurl = SystemConstant.LE_SHUA_MER_URL + "/open.do";
            logger.info("乐刷开通扫码功能地址:" + registerurl);
            String str= HttpClientUtils.postForm(registerurl,map,null, 10000, 10000);
            if (!StringUtils.isEmpty(str)) {
                logger.info("乐刷开通扫码功能返回参数{}",str.toString());
                BaseResp baseResp = new BaseResp();
                baseResp = (BaseResp)JsonUtil.toObject(str.toString(), baseResp);
                leShuaLogDO.setRespCode(baseResp.getRespCode());
                leShuaLogDO.setRespMsg(baseResp.getRespMsg());
                if(baseResp.getRespCode().equals("000000")) {
                    String data = JsonUtil.toJson(baseResp.getData());
                    LeShuaMerFuncInfoDO leShuaMerFuncInfoDO = new LeShuaMerFuncInfoDO();
                    leShuaMerFuncInfoDO.setMerId(memberId);
                    leShuaMerFuncInfoDO.setLeShuaMerId(merchantId);
                    leShuaMerFuncInfoDO.setLeShuaOpenType(new BigDecimal(1));
                    leShuaMerFuncInfoDO.setMerT0(new BigDecimal(1));
                    leShuaMerFuncInfoDO.setWeixin(JsonUtil.toJson(getWeixin(memberId)));
                    leShuaMerFuncInfoDO.setAlipay(JsonUtil.toJson(getAlipay(memberId)));
                    leShuaMerFuncInfoDO.setUpdateTime(DateUtil.getCurrentDateTime());
                    leShuaMerFuncInfoDO.setCreateTime(DateUtil.getCurrentDateTime());
                    leShuaMerFuncInfoDOMapper.insert(leShuaMerFuncInfoDO);

                    TblRouteControl routeControl = new TblRouteControl();
                    routeControl.setInstId("00000009");
                    routeControl.setMerId(memberId);
                    routeControl.setTermId("03");
                    routeControl.setDestGateId("UB");
                    routeControl.setDestMerId(merchantId);
                    routeControl.setDestTermId("00000000");
                    routeControl.setRemark("");
                    routeControl.setPosRouteSeq(String.valueOf(seqMapper.getSequenceNextVal("POS_ROUTE_SEQ")));
                    routeControl.setStat("");
                    tblRouteControlMapper.insertSelective(routeControl);

                    routeControl.setInstId("00000009");
                    routeControl.setMerId(memberId);
                    routeControl.setTermId("04");
                    routeControl.setDestGateId("UB");
                    routeControl.setDestMerId(merchantId);
                    routeControl.setDestTermId("00000000");
                    routeControl.setRemark("");
                    routeControl.setPosRouteSeq(String.valueOf(seqMapper.getSequenceNextVal("POS_ROUTE_SEQ")));
                    routeControl.setStat("");
                    tblRouteControlMapper.insertSelective(routeControl);

                    resultMap.put("statusCode", 200);
                    leShuaLogDO.setRespData(data);

                } else {
                    resultMap.put("statusCode", 300);
                }
                resultMap.put("message", baseResp.getRespMsg());
            } else {
                leShuaLogDO.setRespCode("300");
                leShuaLogDO.setRespMsg("开通扫码支付失败");
                resultMap.put("statusCode", 300);
                resultMap.put("message", "开通扫码支付失败");
            }
        } catch (IOException e) {
            logger.error("开通扫码支付异常{}",e);
            leShuaLogDO.setRespCode("300");
            leShuaLogDO.setRespMsg("开通扫码支付异常");
            resultMap.put("statusCode", 300);
            resultMap.put("message", "开通扫码支付异常!");
        } catch (Exception e) {
            logger.error("开通扫码支付异常{}",e);
            leShuaLogDO.setRespCode("300");
            leShuaLogDO.setRespMsg("开通扫码支付异常");
            resultMap.put("statusCode", 300);
            resultMap.put("message", "开通扫码支付异常!");
        }
        leShuaLogDO.setUpdateTime(DateUtil.getCurrentDateTime());

        leShuaLogDOMapper.updateByPrimaryKeySelective(leShuaLogDO);

        return resultMap;
    }

    private ShaoMaFee getWeixin(String memberId) {
        TblMerFeeInfoDOExample tblMerFeeInfoDOExample = new TblMerFeeInfoDOExample();
        tblMerFeeInfoDOExample.createCriteria().andMerIdEqualTo(memberId).andFeeTypeEqualTo("03");
        List<TblMerFeeInfoDO> tblMerFeeInfoDOList = tblMerFeeInfoDOMapper.selectByExample(tblMerFeeInfoDOExample);
        if(tblMerFeeInfoDOList == null || tblMerFeeInfoDOList.size() == 0) {
            return null;
        }
        String feeRate = CalcModeUtil.splitCalcMode(tblMerFeeInfoDOList.get(0).getCalcMode(),false).split(",")[0];
        ShaoMaFee shaoMaFee = new ShaoMaFee();
        ShaoMaFeeUnit t1 = new ShaoMaFeeUnit();
        t1.setRate(new BigDecimal(feeRate).multiply(new BigDecimal(100)).longValue());
        ShaoMaFeeUnit t0 = new ShaoMaFeeUnit();
        t0.setRate(new BigDecimal(feeRate).multiply(new BigDecimal(100)).longValue());
        shaoMaFee.setT0(t0);
        shaoMaFee.setT1(t1);
        return shaoMaFee;
    }

    private ShaoMaFee getAlipay(String memberId) {
        TblMerFeeInfoDOExample tblMerFeeInfoDOExample = new TblMerFeeInfoDOExample();
        tblMerFeeInfoDOExample.createCriteria().andMerIdEqualTo(memberId).andFeeTypeEqualTo("04");
        List<TblMerFeeInfoDO> tblMerFeeInfoDOList = tblMerFeeInfoDOMapper.selectByExample(tblMerFeeInfoDOExample);
        if(tblMerFeeInfoDOList == null || tblMerFeeInfoDOList.size() == 0) {
            return null;
        }
        String feeRate = CalcModeUtil.splitCalcMode(tblMerFeeInfoDOList.get(0).getCalcMode(),false).split(",")[0];
        ShaoMaFee shaoMaFee = new ShaoMaFee();
        ShaoMaFeeUnit t1 = new ShaoMaFeeUnit();
        t1.setRate(new BigDecimal(feeRate).multiply(new BigDecimal(100)).longValue());
        ShaoMaFeeUnit t0 = new ShaoMaFeeUnit();
        t0.setRate(new BigDecimal(feeRate).multiply(new BigDecimal(100)).longValue());
        shaoMaFee.setT0(t0);
        shaoMaFee.setT1(t1);
        return shaoMaFee;
    }


    private JSONObject buildBasicInfo2(String merId) {
        TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(merId);
        JSONObject baseInfo = new JSONObject();
        baseInfo.put("merchantType", 3);
        baseInfo.put("name",tblMerInfoDO.getLegalPerson());
        baseInfo.put("idcard",tblMerInfoDO.getLegalPersonCertNm());
        baseInfo.put("mobile", tblMerInfoDO.getContactMobile());
        baseInfo.put("merchantName", tblMerInfoDO.getMerName());
        TblMerLeshuaAddressDoExample tblMerLeshuaAddressDoExample = new TblMerLeshuaAddressDoExample();
        tblMerLeshuaAddressDoExample.createCriteria().andMerIdEqualTo(merId);
        List<TblMerLeshuaAddressDo> tblMerLeshuaAddressDoList = tblMerLeshuaAddressDoMapper.selectByExample(tblMerLeshuaAddressDoExample);
        if(tblMerLeshuaAddressDoList != null && tblMerLeshuaAddressDoList.size() > 0) {
            TblMerLeshuaAddressDo tblMerLeshuaAddressDo = tblMerLeshuaAddressDoList.get(0);
            baseInfo.put("province", tblMerLeshuaAddressDo.getProvinceId());
            baseInfo.put("city", tblMerLeshuaAddressDo.getCityId());
            baseInfo.put("area", tblMerLeshuaAddressDo.getAreaId());
            baseInfo.put("address", tblMerLeshuaAddressDo.getDetailAddress());
        }

        baseInfo.put("licenseFullName", tblMerInfoDO.getRegName());
        baseInfo.put("license", tblMerInfoDO.getBusLicNm());
        baseInfo.put("licenseAddress", tblMerInfoDO.getMerAddress());
        baseInfo.put("licenseStart", "2017-06-20");
        baseInfo.put("licenseEnd", DateUtil.stringTostring(tblMerInfoDO.getBusLicExpire(),DateUtil.PATTERN_DATE_8,DateUtil.PATTERN_DATE));
        return baseInfo;
    }

    private  JSONObject buildAccountInfo2(String merId) {
        TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(merId);
        TblMerBankInfoDO tblMerBankInfoDO = tblMerBankInfoDOMapper.selectByPrimaryKey(merId);
        JSONObject accountInfo = new JSONObject();
        if(tblMerBankInfoDO.getIsPrivate().equals("Y")) {
            accountInfo.put("type", 1);
        } else {
            accountInfo.put("type", 2);
        }
        accountInfo.put("branch", tblMerBankInfoDO.getBankBranchName());
        accountInfo.put("unionpay", tblMerBankInfoDO.getCnaps());
        accountInfo.put("holder",tblMerBankInfoDO.getAcctName());
        accountInfo.put("cardId", tblMerBankInfoDO.getAcctNo());
        accountInfo.put("mobile", tblMerInfoDO.getContactMobile());
        return accountInfo;
    }

    private static JSONObject buildData(JSONObject baseInfo, JSONObject accountInfo, JSONObject feeInfo) {
        JSONObject data = new JSONObject();
        data.put("base", baseInfo);
        data.put("account", accountInfo);
        data.put("fee", feeInfo);
        return data;
    }

}
