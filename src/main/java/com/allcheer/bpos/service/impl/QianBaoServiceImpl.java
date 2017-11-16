package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.constant.*;
import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.entity.vo.TblMerInfoVO;
import com.allcheer.bpos.mapper.*;
import com.allcheer.bpos.service.QianBaoService;
import com.allcheer.bpos.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by APPLE on 2016/10/23.
 */
@Service
public class QianBaoServiceImpl implements QianBaoService {

    @Autowired
    MerInfoCustVOMapper merInfoCustVOMapper;

    @Autowired
    TblMerBankInfoDOMapper tblMerBankInfoDOMapper;

    @Autowired
    TblMerFeeInfoDOMapper tblMerFeeInfoDOMapper;

    @Autowired
    TblTermInfoDOMapper tblTermInfoDOMapper;

    @Autowired
    TblBankPosInfoDOMapper tblBankPosInfoDOMapper;

    @Autowired
    TblMerChannelPreInfoDOMapper tblMerChannelPreInfoDOMapper;

    @Autowired
    SeqMapper seqMapper;

    @Override
    public void downChannelPreFile(Map map, HttpServletResponse httpServletResponse) {

        String userName = (String)map.get("userName");

        List<TblMerInfoVO> tblMerInfoVOList = merInfoCustVOMapper.queryMerInfo(map);

        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.setSrcPath(com.allcheer.bpos.constant.SystemConstant.QIANBAO_MER_CHANNEL_PRE_TEMP);


        File file = new File(SystemConstant.MER_CHANNEL_PRE_PATH+DateUtil.getCurrentDate()+DateUtil.getCurrentTime());
        if(!file.exists()) {
            file.mkdirs();
        }

        int row = 0;
        for (int i = 0; i < tblMerInfoVOList.size(); i++) {

            TblMerInfoVO tblMerInfoVO = tblMerInfoVOList.get(i);
            String merId = tblMerInfoVO.getMerId();//商户编号
            String termId = tblMerInfoVO.getTermId();//终端号

            List<String> channelStatList = new ArrayList<>();
            channelStatList.add("3");//报备成功
            channelStatList.add("1");//报备中

            TblMerChannelPreInfoDOExample tblMerChannelPreInfoDOExample = new TblMerChannelPreInfoDOExample();
            tblMerChannelPreInfoDOExample.createCriteria().
                    andMerIdEqualTo(merId).
                    andTermIdEqualTo(termId).
                    andChannelStatIn(channelStatList).
                    andGateIdEqualTo("U1");
            List<TblMerChannelPreInfoDO> tblMerChannelPreInfoDOs = tblMerChannelPreInfoDOMapper.selectByExample(tblMerChannelPreInfoDOExample);

            if(tblMerChannelPreInfoDOs != null && tblMerChannelPreInfoDOs.size() != 0) {
                continue;
            }

            row++;
            String seqNo = String.valueOf(seqMapper.getSequenceNextVal("SEQ_MER_CHANNEL_PRE_NO"));

            String merStat = tblMerInfoVO.getMerStat();//商户状态
            String merType = tblMerInfoVO.getMerType();//商户类型
            String regName = tblMerInfoVO.getRegName();//注册名称
            String regShortName = tblMerInfoVO.getRegShortName();//中文简称
            String merAddress = tblMerInfoVO.getMerAddress();//注册地址
            String regFunds = tblMerInfoVO.getRegFunds();//注册资金
            String busLicNm = tblMerInfoVO.getBusLicNm();//营业执照号
            String busLicExpire = tblMerInfoVO.getBusLicExpire();//营业执照有效期
            String taxRegCert = tblMerInfoVO.getTaxRegCert();//税务登记证
            String legalPerson = tblMerInfoVO.getLegalPerson();//法人代表
            String legalPersonCertType = tblMerInfoVO.getLegalPersonCertType();//法人代表证件l类型
            String legalPersonCertNm = tblMerInfoVO.getLegalPersonCertNm();//法人代表证件号码
            String legalPersonCertExpire = tblMerInfoVO.getLegalPersonCertExpire();//法人代表证件有效期
            String merName = tblMerInfoVO.getMerName();//经营名称

            TblTermInfoDOExample tblTermInfoDOExample = new TblTermInfoDOExample();
            tblTermInfoDOExample.createCriteria().andMerIdEqualTo(tblMerInfoVO.getMerId()).andTermIdEqualTo(tblMerInfoVO.getTermId());
            List<TblTermInfoDO> tblTermInfoDOList = tblTermInfoDOMapper.selectByExample(tblTermInfoDOExample);
            String termInstallProv = "";
            String termInstallCity = "";
            String termInstallCounty = "";
            String termInstallAddress = "";
            String termStat = "";
            String termName = "";
            String termType = "";
            String termSn = "";

            if (tblMerInfoVOList != null && tblMerInfoVOList.size() != 0) {
                TblTermInfoDO tblTermInfoDO = tblTermInfoDOList.get(0);
                termId = tblTermInfoDO.getTermId();//终端号
                termInstallProv = tblTermInfoDO.getTermInstallProv();//装机地址-省
                termInstallCity = tblTermInfoDO.getTermInstallCity();//装机地址-市
                termInstallCounty = tblTermInfoDO.getTermInstallCounty();//装机地址-县
                termInstallAddress = tblTermInfoDO.getTermInstallAddress();//装机地址-详细地址

                termStat = tblTermInfoDO.getTermStat();//终端状态
                termName = tblTermInfoDO.getTermName();//终端名称
                termType = tblTermInfoDO.getTermType();//终端类型
                termSn = tblTermInfoDO.getTermSn();//终端SN号
            }

            String contactPerson = tblMerInfoVO.getContactPerson();//业务联系人
            String contactMobile = tblMerInfoVO.getContactMobile();//电话
            String contactEmail = tblMerInfoVO.getContactEmail();//企业邮箱

            TblMerBankInfoDO tblMerBankInfoDO = tblMerBankInfoDOMapper.selectByPrimaryKey(merId);
            String acctName = tblMerBankInfoDO.getAcctName();//账户户名
            String isPrivate = tblMerBankInfoDO.getIsPrivate();//结算账户类型
            String acctNo = tblMerBankInfoDO.getAcctNo();//账户号
            String bankName = tblMerBankInfoDO.getBankName();//开户银行-银行总行
            String bankBranchName = tblMerBankInfoDO.getBankBranchName();//开户银行-银行支行名
            TblMerFeeInfoDOExample tblMerFeeInfoDOExample = new TblMerFeeInfoDOExample();
            tblMerFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId).andTermIdEqualTo(tblMerInfoVO.getTermId());
            List<TblMerFeeInfoDO> tblMerFeeInfoDOList = tblMerFeeInfoDOMapper.selectByExample(tblMerFeeInfoDOExample);
            String posDebitFeeRate = "";//借记卡-比例
            String posDebitFeeMax = "";//借记卡-封顶
            String posCreditFeeRate = "";//贷记卡-比例
            String withdrawalFeeRate = "";//提现-比例
            String withdrawalFeeMax = "";//提现-固定

            for (TblMerFeeInfoDO tblMerFeeInfoDO : tblMerFeeInfoDOList) {
                if("01".equals(tblMerFeeInfoDO.getFeeType())) {//借记卡费率
                    String fee = CalcModeUtil.splitCalcMode(tblMerFeeInfoDO.getCalcMode(),true);
                    posDebitFeeRate = fee.split("\\,")[0];//借记卡-比例
                    posDebitFeeRate = fomatNum("0.00",posDebitFeeRate);
                    posDebitFeeMax = fee.split("\\,")[1];//借记卡-封顶
                    posDebitFeeMax = fomatNum("0.00",posDebitFeeMax);
                }
                if("02".equals(tblMerFeeInfoDO.getFeeType())) {//贷记卡-比例
                    String fee = CalcModeUtil.splitCalcMode(tblMerFeeInfoDO.getCalcMode(),true);
                    posCreditFeeRate = fee.split("\\,")[0];//贷记卡-比例
                    posCreditFeeRate = fomatNum("0.00",posCreditFeeRate);

                }
                if("04".equals(tblMerFeeInfoDO.getFeeType())) {//提现
                    String fee = CalcModeUtil.splitCalcMode(tblMerFeeInfoDO.getCalcMode(),true);
                    withdrawalFeeRate = fee.split("\\,")[0];//提现-比例
                    withdrawalFeeRate = fomatNum("0.00",withdrawalFeeRate);

                    withdrawalFeeMax = fee.split("\\,")[1];
                    withdrawalFeeMax = fomatNum("0.00",withdrawalFeeMax);
                }
            }

            //商户
            excelUtil.getSheetByIndex(0);
            excelUtil.setCellStrValue(row, 0, merId);//商户号
            excelUtil.setCellStrValue(row, 1, com.allcheer.bpos.constant.SystemConstant.QIANBAO_INST_ID);//合作伙伴号
            excelUtil.setCellStrValue(row, 2, "开通");//商户状态 开通或冻结
            if ("0".equals(merType)) {              //商户类型
                excelUtil.setCellStrValue(row, 3, "公司商户");
            } else if ("1".equals(merType)) {
                excelUtil.setCellStrValue(row, 3, "个体商户");
            } else if ("2".equals(merType)) {
                excelUtil.setCellStrValue(row, 3, "无执照商户");
            }

            excelUtil.setCellStrValue(row, 4, regName);//注册名称
            excelUtil.setCellStrValue(row, 5, regShortName);//中文简称
            excelUtil.setCellStrValue(row, 6, merAddress);//注册地址
            excelUtil.setCellStrValue(row, 7, regFunds);//注册资金
            excelUtil.setCellStrValue(row, 8, busLicNm);//营业执照编号
            excelUtil.setCellStrValue(row, 9, busLicExpire);//营业执照有效期
            excelUtil.setCellStrValue(row, 10, taxRegCert);//税务登记证
            excelUtil.setCellStrValue(row, 11, legalPerson);//法人代表
            if ("0".equals(legalPersonCertType)) {
                excelUtil.setCellStrValue(row, 12, "身份证"); //法人证件类型
            }
            excelUtil.setCellStrValue(row, 13, legalPersonCertNm);//法人证件号
            excelUtil.setCellStrValue(row, 14, legalPersonCertExpire);//法人证件有效期
            excelUtil.setCellStrValue(row, 15, merName);//经营名称
            excelUtil.setCellStrValue(row, 16, termInstallProv);//装机地址-省
            excelUtil.setCellStrValue(row, 17, termInstallCity);//装机地址-市
            excelUtil.setCellStrValue(row, 18, termInstallCounty);//装机地址-县
            excelUtil.setCellStrValue(row, 19, termInstallAddress);//装机地址-详细地址
            excelUtil.setCellStrValue(row, 20, "");//营业时间-开始 不用填
            excelUtil.setCellStrValue(row, 21, "");//营业时间-结束 不用填
            excelUtil.setCellStrValue(row, 22, "");//营业面积      不用填
            excelUtil.setCellStrValue(row, 23, contactPerson);//业务联系人
            excelUtil.setCellStrValue(row, 24, contactMobile);//电话
            excelUtil.setCellStrValue(row, 25, contactEmail);//企业邮箱
            excelUtil.setCellStrValue(row, 26, acctName);//账户户名
            if (isPrivate.equals("Y")) {                //结算账户类型
                excelUtil.setCellStrValue(row, 27, "对私");
            } else {
                excelUtil.setCellStrValue(row, 27, "对公");
            }

            excelUtil.setCellStrValue(row, 28, acctNo);//银行账号
            excelUtil.setCellStrValue(row, 29, bankName);//开户银行-银行总行
            excelUtil.setCellStrValue(row, 30, bankBranchName);//开户银行-银行支行名
            excelUtil.setCellStrValue(row, 31,"");//手续费方式 不用填
            excelUtil.setCellStrValue(row, 32,posDebitFeeRate);//借记卡-比例
            if(posDebitFeeMax.equals("0.00")) {
                excelUtil.setCellStrValue(row, 33,"-1");//借记卡-封顶
            } else {
                excelUtil.setCellStrValue(row, 33,posDebitFeeMax);//借记卡-封顶
            }
            excelUtil.setCellStrValue(row, 34,posCreditFeeRate);//贷记卡-比例
            excelUtil.setCellStrValue(row, 35, "");//贷记卡-封顶 不用填
            excelUtil.setCellStrValue(row, 36, "标准扣率");//分润方式 标准扣率 优惠扣率 减免 特殊计费
            excelUtil.setCellStrValue(row, 37, "");//分润比例档位 不用填
            excelUtil.setCellStrValue(row, 38, "");//分润封顶档位 不用填
            excelUtil.setCellStrValue(row, 39, "钱宝商户报备");//备注
            excelUtil.setCellStrValue(row, 40, "5968");//商户MCC
            excelUtil.setCellStrValue(row, 41, "低");//商户风险级别 高 中 低 其他
            excelUtil.setCellStrValue(row, 42, "是");//是否完全合规 是或否
            excelUtil.setCellStrValue(row, 43, "");//经营内容
            excelUtil.setCellStrValue(row, 44, "");//是否县乡商户 可不填，默认否；可填是或否
            excelUtil.setCellStrValue(row, 45, "");//是否需要对账单 可不填，默认否；可填是或否
            excelUtil.setCellStrValue(row, 46, "");//需要对账单理由
            excelUtil.setCellStrValue(row, 47, "");//是否积分 可不填，默认否；可填是或否
            excelUtil.setCellStrValue(row, 48, "");//需要积分理由
            excelUtil.setCellStrValue(row, 49, "是");//是否紧急
            excelUtil.setCellStrValue(row, 50, seqNo);//申请单号
            excelUtil.setCellStrValue(row, 51, "0");//T+N 填0或1. 0 ：代表T+0 1 ：代表T+1
            excelUtil.setCellStrValue(row, 52, "是");//是否开通提现
            excelUtil.setCellStrValue(row, 53, "主动按金额");//提现方式 主动按金额 单笔自动
            if(!withdrawalFeeRate.equals("0.00")) {
                excelUtil.setCellStrValue(row, 54, "按比例");//提现手续费方式 按比例 单笔固定金额
                excelUtil.setCellStrValue(row, 56, withdrawalFeeRate);//单笔手续费金融 最大12位整数，2位小数，没有小数要补0
            } else {
                excelUtil.setCellStrValue(row, 54, "单笔固定金额");//提现手续费方式 按比例 单笔固定金额
                if(withdrawalFeeMax.equals("0.00")) {
                    excelUtil.setCellStrValue(row, 56, "0.00");//单笔手续费金融 最大12位整数，2位小数，没有小数要补0
                } else {
                    excelUtil.setCellStrValue(row, 56, withdrawalFeeMax);//单笔手续费金融 最大12位整数，2位小数，没有小数要补0
                }
            }

            excelUtil.setCellStrValue(row, 55, "");//垫资日息 最大3位整数，3位小数，没有小数要补0
            excelUtil.setCellStrValue(row, 57, "按工作日（T）");//垫资天数计算方式 按工作日（T）
            excelUtil.setCellStrValue(row, 58, "按工作日（T）");//分润垫资天数计算方式
            excelUtil.setCellStrValue(row, 59, "自动结算");//未提现金额处理 自动结算 手工提现

            try {
                excelUtil.setDesPath(file.getCanonicalPath()+"/"+"钱宝数据报备文件.xls");//报备数据文件路径
            } catch (IOException e) {
                e.printStackTrace();
            }
            excelUtil.exportToNewFile();

            try {
                excelUtil.setSrcPath(file.getCanonicalPath()+"/"+"钱宝数据报备文件.xls");
            } catch (IOException e) {
                e.printStackTrace();
            }


            //终端
            excelUtil.getSheetByIndex(1);
            excelUtil.setCellStrValue(row, 0, merId);//终端所属商户编号
            excelUtil.setCellStrValue(row, 1, termId);//终端号
            if ("1".equals(termStat)) {
                excelUtil.setCellStrValue(row, 2, "开通");//终端状态
            } else {
                excelUtil.setCellStrValue(row, 2, "未开通");//终端状态

            }
            excelUtil.setCellStrValue(row, 3, termName);//终端名称
            excelUtil.setCellStrValue(row, 4, contactMobile);//联系电话
            excelUtil.setCellStrValue(row, 5, "合作伙伴所有");//产权属性 我司所有 合作伙伴所有 商户自有
            if ("0".equals(termType)) {//终端类型 移动 固定 MPOS
                excelUtil.setCellStrValue(row, 6, "移动");
            } else if ("1".equals(termType)) {
                excelUtil.setCellStrValue(row, 6, "固定");
            } else if ("2".equals(termType)) {
                excelUtil.setCellStrValue(row, 6, "MPOS");
            }
            excelUtil.setCellStrValue(row, 7, termInstallAddress);//终端安装地址
            excelUtil.setCellStrValue(row, 8, "");//交易功能 可不填
            excelUtil.setCellStrValue(row, 9, "");//卡类型权限 可不填
            excelUtil.setCellStrValue(row, 10, termSn);//终端SN号

            excelUtil.setCellStrValue(row, 11, "是");//是否开通消费
            excelUtil.setCellStrValue(row, 12, "是");//是否开通撤销
            excelUtil.setCellStrValue(row, 13, "否");//是否开通退货
            excelUtil.setCellStrValue(row, 14, "是");//是否开通查询余额
            excelUtil.setCellStrValue(row, 15, "否");//是否开通预授权
            excelUtil.setCellStrValue(row, 16, "否");//是否开通预授权撤销
            excelUtil.setCellStrValue(row, 17, "否");//是否开通预授权完成联机
            excelUtil.setCellStrValue(row, 18, "否");//是否开通预授权完成离线
            excelUtil.setCellStrValue(row, 19, "否");//是否开通预授权完成撤销
            excelUtil.setCellStrValue(row, 20, "是");//是否允许贷记卡交易

            try {
                excelUtil.setDesPath(file.getCanonicalPath()+"/"+"钱宝数据报备文件.xls");//报备数据文件路径
            } catch (IOException e) {
                e.printStackTrace();
            }
            excelUtil.exportToNewFile();

            String attchmentFilePath = "";
            try {
                File attchmentFilePathFold = new File(file.getCanonicalPath()+"/PIC/"+merId+"/");
                attchmentFilePath = file.getCanonicalPath()+"/PIC/"+merId+"/";
                if(!attchmentFilePathFold.exists()) {
                    attchmentFilePathFold.mkdirs();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //生成商户报备附件文件
            UnixUtil.doExecUnixCPCMD(tblMerInfoVO.getAttachmentPath(), "/.", attchmentFilePath);//目标目录

            TblMerChannelPreInfoDO tblMerChannelPreInfoDO = new TblMerChannelPreInfoDO();

            tblMerChannelPreInfoDO.setUserName(userName);
            tblMerChannelPreInfoDO.setChannelStat("1");
            tblMerChannelPreInfoDO.setGateId("U1");
            tblMerChannelPreInfoDO.setUpdateTime(DateUtil.getCurrentDate()+DateUtil.getCurrentTime());

            List<String> channelStatList1 = new ArrayList<>();
            channelStatList1.add("0");//未报备
            channelStatList1.add("2");//报备失败

            TblMerChannelPreInfoDOExample tblMerChannelPreInfoDOExample1 = new TblMerChannelPreInfoDOExample();
            tblMerChannelPreInfoDOExample1.createCriteria().
                    andMerIdEqualTo(merId).
                    andTermIdEqualTo(termId).
                    andChannelStatIn(channelStatList1).
                    andGateIdEqualTo("U1");
            List<TblMerChannelPreInfoDO> tblMerChannelPreInfoDOs1 = tblMerChannelPreInfoDOMapper.selectByExample(tblMerChannelPreInfoDOExample1);
            if(tblMerChannelPreInfoDOs1 != null && tblMerChannelPreInfoDOs1.size() != 0) {
                tblMerChannelPreInfoDOMapper.updateByExampleSelective(tblMerChannelPreInfoDO,tblMerChannelPreInfoDOExample1);
            } else {
                tblMerChannelPreInfoDO.setMerId(merId);
                tblMerChannelPreInfoDO.setTermId(termId);
                tblMerChannelPreInfoDO.setInstId(tblMerInfoVO.getInstId());
                tblMerChannelPreInfoDO.setInstMerId(tblMerInfoVO.getInstMerId());
                tblMerChannelPreInfoDO.setInstTermId(tblMerInfoVO.getInstTermId());
                tblMerChannelPreInfoDO.setCreateTime(DateUtil.getCurrentDate()+DateUtil.getCurrentTime());
                tblMerChannelPreInfoDOMapper.insertSelective(tblMerChannelPreInfoDO);
            }
        }

        String merChannelPreFile = null;//压缩文件目录
        try {
            merChannelPreFile = ZipUtil.zip(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            downLoad(merChannelPreFile,httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Transactional
    @Override
    public Map resultUpdate(File resultFile,String userName) {
        Map resultMap = new HashMap<>();
        ExcelReader excelReader = new ExcelReader();
        try {
            List<List<Object>> rows = excelReader.read2003Excel(resultFile);

            for (List<Object> cells:rows) {
                String platMerId = (String)cells.get(0);
                String merId = (String)cells.get(1);
                String termId = (String)cells.get(4);
                TblMerChannelPreInfoDOExample tblMerChannelPreInfoDOExample = new TblMerChannelPreInfoDOExample();
                tblMerChannelPreInfoDOExample.createCriteria().
                        andMerIdEqualTo(platMerId).
                        andGateIdEqualTo("U1").
                        andChannelStatEqualTo("1");
                TblMerChannelPreInfoDO tblMerChannelPreInfoDO = new TblMerChannelPreInfoDO();
                tblMerChannelPreInfoDO.setChannelMerId(merId);
                tblMerChannelPreInfoDO.setChannelTermId(termId);
                tblMerChannelPreInfoDO.setGateId("U1");
                tblMerChannelPreInfoDO.setChannelStat("3");
                tblMerChannelPreInfoDO.setUserName(userName);
                tblMerChannelPreInfoDO.setUpdateTime(DateUtil.getCurrentDate()+DateUtil.getCurrentTime());
                tblMerChannelPreInfoDOMapper.updateByExampleSelective(tblMerChannelPreInfoDO,tblMerChannelPreInfoDOExample);
            }

            resultMap.put("statusCode", 200);
            resultMap.put("message", "结果维护成功!");

        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("statusCode", 300);
            resultMap.put("message", "结果维护异常!");
        } finally {
            return resultMap;
        }


    }

    public String downLoad(String filePath, HttpServletResponse response) throws Exception {
        File f = new File(filePath);
        if (!f.exists()) { response.sendError(404, "File not found!");
         return ""; }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset(); // 非常重要
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename="
                + toUTF8(f.getName()));
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        out.flush();
        br.close();
        out.close();
        return null;
    }

    // UTF-8编码
    public String toUTF8(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    private String fomatNum(String formatPattern,String num) {
        DecimalFormat decimalFormat = new DecimalFormat(formatPattern);
        return decimalFormat.format(new BigDecimal(num));
    }
}
