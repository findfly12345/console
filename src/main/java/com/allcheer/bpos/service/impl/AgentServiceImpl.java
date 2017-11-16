package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.domain.AgentDetailInfoBO;
import com.allcheer.bpos.domain.AgentFeeBO;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.mapper.*;
import com.allcheer.bpos.service.AgentService;
import com.allcheer.bpos.service.MonitorRealm;
import com.allcheer.bpos.service.UserService;
import com.allcheer.bpos.shiro.CustomCredentialsMatcher;
import com.allcheer.bpos.util.*;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by LiuBin on 2017/1/17.
 */
@Service("agentService")
public class AgentServiceImpl implements AgentService {

    private static final Logger logger = LoggerFactory.getLogger(AgentServiceImpl.class);

    @Autowired
    private TblAgentInfoDoMapper tblAgentInfoDoMapper;
    @Autowired
    private TblAgentLevelDoMapper tblAgentLevelDoMapper;
    @Autowired
    private TblAiLoginUsrDoMapper tblAiLoginUsrDoMapper;
    @Autowired
    private TblAgentFeeInfoDoMapper tblAgentFeeInfoDoMapper;
    @Autowired
    private AgentCustMapper agentCustMapper;
    @Autowired
    private SeqMapper seqMapper;

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public Map openNewAgent(AgentDetailInfoBO agentDetailInfoBO, String userName) {
        Map resultMap = new HashMap();

        DateTime now = new DateTime();
        String date = DateUtil.getCurrentDate();
        String time = DateUtil.getCurrentTime();

        String nowTime = DateUtil.date2String(new Date(), "yyyyMMddHHmmss");

        int result = 0;
        Boolean isRegCodeUsed = isRegCodeExist(agentDetailInfoBO.getRegCode());
        if (isRegCodeUsed) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "公司注册号已经在使用!");
            return resultMap;
        }
        try {
            String agentIdSeq = getAgentIdSeq();
            String userNameSeq = getUserNameSeq();

            TblAgentInfoDo tblAgentInfoDo = new TblAgentInfoDo();

            tblAgentInfoDo.setMemberId(agentIdSeq);
            tblAgentInfoDo.setOemId(agentIdSeq);
            tblAgentInfoDo.setUpAgentId(agentIdSeq);
            tblAgentInfoDo.setAgentName(agentDetailInfoBO.getAgentName().trim());
            tblAgentInfoDo.setAgentShortName(agentDetailInfoBO.getAgentShortName().trim());
            tblAgentInfoDo.setRegName(agentDetailInfoBO.getRegName().trim());
            tblAgentInfoDo.setRegCode(agentDetailInfoBO.getRegCode().trim());
            tblAgentInfoDo.setTaxCode(agentDetailInfoBO.getTaxCode().trim());
            tblAgentInfoDo.setLegalName(agentDetailInfoBO.getLegalName().trim());
            tblAgentInfoDo.setIdno(agentDetailInfoBO.getIdno().trim());
            tblAgentInfoDo.setIdtype("1");
            tblAgentInfoDo.setAgentStatus("1");
            tblAgentInfoDo.setContactName(agentDetailInfoBO.getContactName().trim());
            tblAgentInfoDo.setContactMobile(agentDetailInfoBO.getContactMobile().trim());
            tblAgentInfoDo.setContactAddr(agentDetailInfoBO.getContactAddr().trim());
            tblAgentInfoDo.setSignDate(date);
            tblAgentInfoDo.setRecCretDttm(nowTime);
            tblAgentInfoDo.setLstUpdDttm(nowTime);
            result = tblAgentInfoDoMapper.insert(tblAgentInfoDo);

            TblAgentLevelDo tblAgentLevelDo = new TblAgentLevelDo();
            tblAgentLevelDo.setAgentLevel("1");
            tblAgentLevelDo.setMemberId(agentIdSeq);
            tblAgentLevelDo.setUpMemberId(agentIdSeq);
            tblAgentLevelDo.setUpdateTime(nowTime);
            tblAgentLevelDo.setCreateTime(nowTime);
            result = tblAgentLevelDoMapper.insert(tblAgentLevelDo);

            //开通代理商登陆账户 并激活
            UserBO userBO = new UserBO();
            userBO.setUsrId(String.valueOf(seqMapper.getTblBTSSysUsrIdSeq()));
            userBO.setUsrName(userNameSeq);
            CustomCredentialsMatcher customCredentialsMatcher = new CustomCredentialsMatcher();
            String userPass = customCredentialsMatcher.encrypt("111111");
            userBO.setUsrPwd(userPass);
            userBO.setUsrDisableTag(Constant.USER_ACTIVATED);
            userBO.setUsrType(Constant.AGENT_USER);
            Subject currentUser = SecurityUtils.getSubject();
            userBO.setAgentId(agentIdSeq);
            MonitorRealm.ShiroUser shiroUser = (MonitorRealm.ShiroUser) currentUser.getPrincipal();
            userBO.setUsrCreateBy(shiroUser.getLoginName());
            userBO.setUsrUpdateBy(shiroUser.getLoginName());
            Map map = userService.addNewUsr(userBO);
            if ("操作成功!".equals(map.get("message")))
                result = 1;
            else
                result = 0;

            //添加角色关联
            TblAiLoginUsrDo tblAiLoginUsrDo = new TblAiLoginUsrDo();
            tblAiLoginUsrDo.setAgentId(agentIdSeq);
            tblAiLoginUsrDo.setLoginUsr(userNameSeq);
            tblAiLoginUsrDo.setLoginUsrType(Constant.AGENT_USER);
            result = tblAiLoginUsrDoMapper.insert(tblAiLoginUsrDo);

            //添加代理商角色权限
            userService.addAcctAuthority("5", userBO.getUsrId());

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new BposException("新增代理商异常!");
        }
        if (result == 1) {
            resultMap.put("statusCode", 200);
            resultMap.put("message", "新增代理商成功!");
        } else {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "账号类型失败!");
        }
        return resultMap;
    }

    public Boolean isRegCodeExist(String regCode) {
        TblAgentInfoDoExample tblAgentInfoDoExample = new TblAgentInfoDoExample();
        tblAgentInfoDoExample.createCriteria().andRegCodeEqualTo(regCode);
        List<TblAgentInfoDo> tblAgentInfoDos = tblAgentInfoDoMapper.selectByExample(tblAgentInfoDoExample);
        if (tblAgentInfoDos == null || tblAgentInfoDos.size() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Pagination<AgentDetailInfoBO> queryAgentList(AgentDetailInfoBO agentDetailInfoBO) {
        TblAgentInfoDoExample tblAgentInfoDoExample = new TblAgentInfoDoExample();
        TblAgentInfoDoExample.Criteria criteria = tblAgentInfoDoExample.createCriteria();

        if (!StringUtils.isBlank(agentDetailInfoBO.getAgentShortName())) {
            criteria.andAgentShortNameLike("%"+agentDetailInfoBO.getAgentShortName()+"%");
        }
        if (!StringUtils.isBlank(agentDetailInfoBO.getMemberId())) {
            criteria.andMemberIdEqualTo(agentDetailInfoBO.getMemberId());
        }
        if (!StringUtils.isBlank(agentDetailInfoBO.getMemberStat())) {
            criteria.andAgentStatusEqualTo(agentDetailInfoBO.getMemberStat());
        }
        int count = tblAgentInfoDoMapper.countByExample(tblAgentInfoDoExample);
        Pagination pagination = new Pagination(count, agentDetailInfoBO.getPageCurrent(),
                agentDetailInfoBO.getPageSize());
        PageHelper.startPage(agentDetailInfoBO.getPageCurrent(), agentDetailInfoBO.getPageSize());
        List<TblAgentInfoDo> tblAgentInfoDos = tblAgentInfoDoMapper.selectByExample(tblAgentInfoDoExample);

        if (tblAgentInfoDos == null || tblAgentInfoDos.size() == 0) {
            return null;
        }
        List<AgentDetailInfoBO> agentDetailInfoBOList = new ArrayList<>();

        for (TblAgentInfoDo agentDO : tblAgentInfoDos) {

            TblAgentLevelDo agentLevelDO = getAgentLevelById(agentDO.getMemberId());
            TblAiLoginUsrDo tblAiLoginUsrDo = getAILoginById(agentDO.getMemberId());
            AgentDetailInfoBO agentDetailInfoBO1 = new AgentDetailInfoBO();
            agentDetailInfoBO1.setMemberId(agentDO.getMemberId());
            agentDetailInfoBO1.setAgentName(agentDO.getAgentName());
            agentDetailInfoBO1.setAgentShortName(agentDO.getAgentShortName());
            agentDetailInfoBO1.setRegName(agentDO.getRegName());
            agentDetailInfoBO1.setRegCode(agentDO.getRegCode());
            agentDetailInfoBO1.setTaxCode(agentDO.getTaxCode());
            agentDetailInfoBO1.setRegAddr(agentDO.getRegAddr());
            agentDetailInfoBO1.setLicType(agentDO.getLicType());
            agentDetailInfoBO1.setLicAmt(agentDO.getLicAmt());
            agentDetailInfoBO1.setLegalName(agentDO.getLegalName());
            agentDetailInfoBO1.setIdno(agentDO.getIdno());
            agentDetailInfoBO1.setContactName(agentDO.getContactName());
            agentDetailInfoBO1.setContactMobile(agentDO.getContactMobile());
            agentDetailInfoBO1.setContactAddr(agentDO.getContactAddr());
            agentDetailInfoBO1.setEmail(agentDO.getEmail());
            agentDetailInfoBO1.setMemberStat(agentDO.getAgentStatus());
            if (agentLevelDO != null) {
                agentDetailInfoBO1.setAgentLevel(agentLevelDO.getAgentLevel());
                agentDetailInfoBO1.setUpAgentId(agentLevelDO.getUpMemberId());
            } else {
                agentDetailInfoBO1.setAgentLevel("1");
            }
            if (tblAiLoginUsrDo != null) {
                agentDetailInfoBO1.setTellerId(tblAiLoginUsrDo.getLoginUsr());
            }
            agentDetailInfoBO1.setFatherAgentName(agentDO.getAgentName());
            agentDetailInfoBO1.setOemId(agentDO.getOemId());
            agentDetailInfoBO1.setSignDate(agentDO.getSignDate());
            agentDetailInfoBOList.add(agentDetailInfoBO1);
        }
        pagination.addResult(agentDetailInfoBOList);
        return pagination;
    }

    @Override
    public Map updateAgentDetails(AgentDetailInfoBO agentDetailInfoBO) {
        Map resultMap = new HashMap();

        TblAgentInfoDo agentDO = new TblAgentInfoDo();
        agentDO.setMemberId(agentDetailInfoBO.getMemberId());
        agentDO.setAgentName(agentDetailInfoBO.getAgentName());
        agentDO.setAgentShortName(agentDetailInfoBO.getAgentShortName());
        agentDO.setRegName(agentDetailInfoBO.getRegName());
        agentDO.setRegCode(agentDetailInfoBO.getRegCode());
        agentDO.setTaxCode(agentDetailInfoBO.getTaxCode());
        agentDO.setRegAddr(agentDetailInfoBO.getRegAddr());
        agentDO.setLicType(agentDetailInfoBO.getLicType());
        agentDO.setLicAmt(agentDetailInfoBO.getLicAmt());
        agentDO.setLegalName(agentDetailInfoBO.getLegalName());
        agentDO.setIdno(agentDetailInfoBO.getIdno());
        agentDO.setContactName(agentDetailInfoBO.getContactName());
        agentDO.setContactMobile(agentDetailInfoBO.getContactMobile());
        agentDO.setContactAddr(agentDetailInfoBO.getContactAddr());
        agentDO.setEmail(agentDetailInfoBO.getEmail());
        agentDO.setAgentStatus(agentDetailInfoBO.getMemberStat());
        try {
            tblAgentInfoDoMapper.updateByPrimaryKeySelective(agentDO);
            resultMap.put("statusCode", 200);
            resultMap.put("message", "更改已保存!");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "更新失败!");
            throw new BposException("更新失败!");
        }
        return resultMap;
    }

    @Override
    public AgentFeeBO getFeeForAgent(String agentId) {
        AgentFeeBO agentFeeBO = new AgentFeeBO();
        try {
            TblAgentFeeInfoDoExample tblAgentFeeInfoDoExample = new TblAgentFeeInfoDoExample();
            tblAgentFeeInfoDoExample.createCriteria().andAgentIdEqualTo(agentId);
            List<TblAgentFeeInfoDo> tblAgentFeeInfoDos = tblAgentFeeInfoDoMapper
                    .selectByExample(tblAgentFeeInfoDoExample);
            for (TblAgentFeeInfoDo tblAgentFeeInfoDo : tblAgentFeeInfoDos) {
                String feeType = tblAgentFeeInfoDo.getFeeType();
                feeType = feeType.replace("0", "");
                int type = Integer.parseInt(feeType);
                String code = CalcModeUtil.splitCalcMode(tblAgentFeeInfoDo.getCalcMode(), false);
                String rate = code.substring(0, code.indexOf(","));
                String max = "";
                switch (type) {
                    case 1:
                        max = code.split(",")[1];
                        agentFeeBO.setCode01(rate);
                        agentFeeBO.setCode01L(max);
                        break;
                    case 2:
                        agentFeeBO.setCode02(rate);
                        break;
                    case 3:
                        agentFeeBO.setCode03(rate);
                        break;
                    case 4:
                        agentFeeBO.setCode04(rate);
                        break;
                    case 5:
                        agentFeeBO.setCode05(rate);
                        break;
                    case 6:
                        agentFeeBO.setCode06(rate);
                        break;
                    case 7:
                        agentFeeBO.setCode07(rate);
                        break;
                    case 8:
                        agentFeeBO.setCode08(rate);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BposException("查询已有代理商费率失败!");
        }
        return agentFeeBO;
    }

    @Override
    public Map updateFeeSetttingsForAgent(AgentFeeBO agentFeeBO, String agentId, String userName) {
        Map resultMap = new HashMap();
        try {
            TblAgentFeeInfoDoExample tblAgentFeeInfoDoExample = new TblAgentFeeInfoDoExample();
            tblAgentFeeInfoDoExample.createCriteria().andAgentIdEqualTo(agentId);
            List<TblAgentFeeInfoDo> tblAgentFeeInfoDos = tblAgentFeeInfoDoMapper
                    .selectByExample(tblAgentFeeInfoDoExample);
            Map<String, TblAgentFeeInfoDo> map = new HashMap<String, TblAgentFeeInfoDo>();
            for (TblAgentFeeInfoDo tblAgentFeeInfoDo : tblAgentFeeInfoDos) {
                map.put(tblAgentFeeInfoDo.getFeeType(), tblAgentFeeInfoDo);
            }
            Field[] field = agentFeeBO.getClass().getDeclaredFields();
            String name = "";
            String value = "";
            String fee01L = "";
            String calc_mode = "";
            for (int j = 0; j < field.length; j++) {
                name = field[j].getName();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                Method m = agentFeeBO.getClass().getMethod("get" + name);
                value = (String) m.invoke(agentFeeBO);

                if (StringUtils.isBlank(value))
                    continue;
                if ("AgentId".equals(name) || "InstId".equals(name)) {
                    continue;
                } else {
                    if ("Code01L".equals(name)) {
                        fee01L = value;
                        continue;
                    } else {
                        name = name.substring(4);
                        if ("01".equals(name))
                            calc_mode = "MTM(0," + fee01L + ",AMT*" + value + ")";
                        else
                            calc_mode = "AMT*" + value;
                        if (map.get(name) != null) {
                            TblAgentFeeInfoDo tblAgentFeeInfoDo = map.get(name);
                            tblAgentFeeInfoDo.setCalcMode(calc_mode);
                            tblAgentFeeInfoDo.setUpdateTime(DateUtil.getCurrentDate());
                            TblAgentFeeInfoDoExample tblAgentFeeInfoDoExample1 = new TblAgentFeeInfoDoExample();
                            tblAgentFeeInfoDoExample1.createCriteria().andAgentIdEqualTo(agentId)
                                    .andFeeTypeEqualTo(name);
                            tblAgentFeeInfoDoMapper.updateByExampleSelective(tblAgentFeeInfoDo,
                                    tblAgentFeeInfoDoExample1);
                        } else {
                            TblAgentFeeInfoDo tblAgentFeeInfoDo = new TblAgentFeeInfoDo();
                            tblAgentFeeInfoDo.setAgentId(agentId);
                            tblAgentFeeInfoDo.setTermId("1");
                            tblAgentFeeInfoDo.setFeeType(name);
                            tblAgentFeeInfoDo.setUserName(userName);
                            tblAgentFeeInfoDo.setUpdateTime(DateUtil.getCurrentDate());
                            tblAgentFeeInfoDo.setCreateTime(DateUtil.getCurrentDate());
                            tblAgentFeeInfoDo.setCalcMode(calc_mode);
                            tblAgentFeeInfoDoMapper.insertSelective(tblAgentFeeInfoDo);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "更新失败!");
            return resultMap;
        }
        resultMap.put("statusCode", 200);
        resultMap.put("message", "更新成功!");
        return resultMap;
    }

    @Override
    public AgentDetailInfoBO getAgtDetailInfoById(String memberId) {

        AgentDetailInfoBO agentDetailInfoBO = agentCustMapper.selectAgentDetailInfoBO(memberId);
        if (agentDetailInfoBO == null) {
            return null;
        }
        return agentDetailInfoBO;
    }

    @Override
    public void exportAgentList(AgentDetailInfoBO agentDetailInfoBO, ServletOutputStream outputStream)
            throws Exception {
        TblAgentInfoDoExample tblAgentInfoDoExample = new TblAgentInfoDoExample();
        TblAgentInfoDoExample.Criteria criteria = tblAgentInfoDoExample.createCriteria();

        if (!StringUtils.isBlank(agentDetailInfoBO.getAgentShortName())) {
            criteria.andAgentShortNameEqualTo(agentDetailInfoBO.getAgentShortName());
        }
        if (!StringUtils.isBlank(agentDetailInfoBO.getMemberId())) {
            criteria.andMemberIdEqualTo(agentDetailInfoBO.getMemberId());
        }
        if (!StringUtils.isBlank(agentDetailInfoBO.getMemberStat())) {
            criteria.andAgentStatusEqualTo(agentDetailInfoBO.getMemberStat());
        }

        List<TblAgentInfoDo> agentDOList = tblAgentInfoDoMapper.selectByExample(tblAgentInfoDoExample);

        if (agentDOList == null || agentDOList.size() == 0) {
        }

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("记录列表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setWrapText(true);
        String[] titles = {"代理商编号", "代理商名称", "上级代理商名称", "上级代理商编号", "代理商级别", "开户时间", "代理商状态"};

        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = row.createCell((short) i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(style);
        }
        int i = 0;

        for (TblAgentInfoDo agentDO : agentDOList) {

            TblAgentLevelDo tblAgentLevelDo = getAgentLevelById(agentDO.getMemberId());

            row = sheet.createRow((int) i + 1);

            HSSFCell cell0 = row.createCell((short) 0);
            cell0.setCellStyle(style);
            cell0.setCellValue(agentDO.getMemberId());

            HSSFCell cell1 = row.createCell((short) 1);
            cell1.setCellStyle(style);
            cell1.setCellValue(agentDO.getAgentShortName());

            TblAgentInfoDo upAgent = tblAgentInfoDoMapper.selectByPrimaryKey(tblAgentLevelDo.getUpMemberId());
            HSSFCell cell2 = row.createCell((short) 2);
            cell2.setCellStyle(style);
            cell2.setCellValue(upAgent.getAgentShortName());

            HSSFCell cell3 = row.createCell((short) 3);
            cell3.setCellStyle(style);
            cell3.setCellValue(tblAgentLevelDo.getUpMemberId());

            HSSFCell cell7 = row.createCell((short) 4);
            cell7.setCellStyle(style);
            cell7.setCellValue(tblAgentLevelDo.getAgentLevel());

            HSSFCell cell8 = row.createCell((short) 5);
            cell8.setCellStyle(style);
            cell8.setCellValue(agentDO.getSignDate());

            HSSFCell cell9 = row.createCell((short) 6);
            cell9.setCellStyle(style);
            cell9.setCellValue(agentDO.getAgentStatus());
            i++;

        }
        wb.write(outputStream);
        outputStream.close();

    }

    private TblAgentLevelDo getAgentLevelById(String memberId) {
        TblAgentLevelDoExample tblAgentLevelDoExample = new TblAgentLevelDoExample();
        tblAgentLevelDoExample.createCriteria().andMemberIdEqualTo(memberId);
        List<TblAgentLevelDo> tblAgentLevelDos = tblAgentLevelDoMapper.selectByExample(tblAgentLevelDoExample);
        if (tblAgentLevelDos.size() > 0) {
            return tblAgentLevelDos.get(0);
        } else
            return null;
    }

    private TblAiLoginUsrDo getAILoginById(String memberId) {
        TblAiLoginUsrDoExample tblAiLoginUsrDoExample = new TblAiLoginUsrDoExample();
        tblAiLoginUsrDoExample.createCriteria().andAgentIdEqualTo(memberId);
        List<TblAiLoginUsrDo> tblAiLoginUsrDos = tblAiLoginUsrDoMapper.selectByExample(tblAiLoginUsrDoExample);
        if (tblAiLoginUsrDos.size() > 0) {
            return tblAiLoginUsrDos.get(0);
        } else
            return null;
    }

    @Override
    public String getAgentIdSeq() {
        long agentIdSeq = seqMapper.getSequenceNextVal("TBL_AGENT_ID_SEQ");
        return StringUtils.leftPad(String.valueOf(agentIdSeq), 15, '0').trim();
    }

    @Override
    public String getUserNameSeq() {
        long userIdSeq = seqMapper.getSequenceNextVal("LOGIN_USR_SEQ");
        return "AGENT_" + StringUtils.leftPad(String.valueOf(userIdSeq), 8, '0').trim();
    }

    @Override
    public TblAgentFeeInfoDo queryAgentFeeList(TblAgentFeeInfoDo tblAgentFeeInfoDo) {
        TblAgentFeeInfoDoExample example = getInstMccFeeSearchFiled(tblAgentFeeInfoDo);
        List<TblAgentFeeInfoDo> list = tblAgentFeeInfoDoMapper.selectByExample(example);
        if(list != null && list.size() > 0 ){
            return list.get(0);
        }
        return new TblAgentFeeInfoDo();
    }


    private TblAgentFeeInfoDoExample getInstMccFeeSearchFiled(TblAgentFeeInfoDo tblAgentFeeInfoDo) {
        String agentId = tblAgentFeeInfoDo.getAgentId();
        String calcMode = tblAgentFeeInfoDo.getCalcMode();
        String feeType = tblAgentFeeInfoDo.getFeeType();

        TblAgentFeeInfoDoExample example = new TblAgentFeeInfoDoExample();
        TblAgentFeeInfoDoExample.Criteria criteria = example.createCriteria();

        if (filedNotNull(agentId)) {
            criteria.andAgentIdEqualTo(agentId);
        }
        if (filedNotNull(calcMode)) {
            criteria.andCalcModeEqualTo(calcMode);
        }
        if (filedNotNull(feeType)) {
            criteria.andFeeTypeEqualTo(feeType);
        }
        return example;
    }

    private boolean filedNotNull(String filed) {
        return filed != null && (!"".equals(filed.trim()));
    }

}
