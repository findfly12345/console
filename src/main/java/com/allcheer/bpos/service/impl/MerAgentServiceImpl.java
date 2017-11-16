package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.constant.NotifyException;
import com.allcheer.bpos.entity.Enum.ErrorRespEnum;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.entity.vo.TblAgentMerInfoVO;
import com.allcheer.bpos.entity.vo.TblMerAuditRecordVO;
import com.allcheer.bpos.entity.vo.TblMerInfoVO;
import com.allcheer.bpos.form.AgentMerFeeForm;
import com.allcheer.bpos.form.SubBranchInfoForm;
import com.allcheer.bpos.mapper.*;
import com.allcheer.bpos.service.MerAgentService;
import com.allcheer.bpos.service.WechatRegisterService;
import com.allcheer.bpos.util.Bean2Map;
import com.allcheer.bpos.util.BposException;
import com.allcheer.bpos.util.CalcModeUtil;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.allcheer.bpos.entity.TblMerInfoDO;
import com.allcheer.bpos.entity.TblMerInfoDOExample;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

/**
 * Created by LiuBin on 2017/1/13.
 */
@Service("merAgentService")
public class MerAgentServiceImpl implements MerAgentService {

    private final static Logger logger = LoggerFactory.getLogger(MerAgentServiceImpl.class);

    @Autowired
    private SeqMapper seqMapper;
    @Autowired
    TblAgentInfoDoMapper tblAgentInfoDoMapper;
    @Autowired
    TblMerBankInfoDOMapper tblMerBankInfoDOMapper;
    @Autowired
    TblMerInfoDOMapper tblMerInfoDOMapper;
    @Autowired
    TblMerFeeInfoDOMapper tblMerFeeInfoDOMapper;
    @Autowired
    TblTermInfoDOMapper tblTermInfoDOMapper;
    @Autowired
    TblAgentMerTermDoMapper tblAgentMerTermDoMapper;
    @Autowired
    private MerInfoCustVOMapper merInfoCustVOMapper;
    @Autowired
    private TblMerFileInfoDOMapper tblMerFileInfoDOMapper;
    @Autowired
    private TblSubbranchInfoDOMapper tblSubbranchInfoDOMapper;

    @Autowired
    private TblMerAuditRecordDOMapper tblMerAuditRecordDOMapper;

    @Autowired
    private TblMerAuditRecordVOMapper tblMerAuditRecordVOMapper;

    @Autowired
    private TblMerCoreMccDoMapper tblMerCoreMccDoMapper;

    @Autowired
    private TblMerRelevanceMccDoMapper tblMerRelevanceMccDoMapper;

    @Autowired
    private MerChannelPerServiceImpl merChannelPerService;

    @Autowired
    private WechatRegisterService wechatRegisterService;

    @Override
    @Transactional
    public List<TblAgentInfoDo> findAgentInfos(TblAgentInfoDo tblAgentInfoDo) {
        TblAgentInfoDoExample tblAgentInfoDoExample = new TblAgentInfoDoExample();
        TblAgentInfoDoExample.Criteria criteria = tblAgentInfoDoExample.createCriteria();
        if (StringUtils.isNotBlank(tblAgentInfoDo.getAgentName())) {
            criteria.andAgentNameLike("%" + tblAgentInfoDo.getAgentName() + "%");
        }
        if (StringUtils.isNotBlank(tblAgentInfoDo.getAgentShortName())) {
            criteria.andAgentShortNameLike("%" + tblAgentInfoDo.getAgentShortName() + "%");
        }
        List<TblAgentInfoDo> tblAgentInfoDos = tblAgentInfoDoMapper.selectByExample(tblAgentInfoDoExample);
        return tblAgentInfoDos;
    }

    @Override
    @Transactional
    public Pagination<TblSubbranchInfoDO> findBankInfos(SubBranchInfoForm subBranchInfoForm) {
        TblSubbranchInfoDOExample tblSubbranchInfoDOExample = new TblSubbranchInfoDOExample();
        TblSubbranchInfoDOExample.Criteria criteria = tblSubbranchInfoDOExample.createCriteria();
        if (StringUtils.isNotBlank(subBranchInfoForm.getSubbranchName())) {
            criteria.andSubbranchNameLike("%" + subBranchInfoForm.getSubbranchName() + "%");
        }
        if (StringUtils.isNotBlank(subBranchInfoForm.getBankName())) {
            criteria.andBankNameLike("%" + subBranchInfoForm.getBankName() + "%");
        }
        if (StringUtils.isNotBlank(subBranchInfoForm.getProvince())) {
            criteria.andProvinceLike("%" + subBranchInfoForm.getProvince() + "%");
        }
        if (StringUtils.isNotBlank(subBranchInfoForm.getCity())) {
            criteria.andCityLike("%" + subBranchInfoForm.getCity() + "%");
        }
        int count = tblSubbranchInfoDOMapper.countByExample(tblSubbranchInfoDOExample);
        int pageCurrent = Integer.parseInt(subBranchInfoForm.getPageCurrent());
        int pageSize = Integer.parseInt(subBranchInfoForm.getPageSize());
        Pagination pagination = new Pagination(count, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblSubbranchInfoDO> tblSubbranchInfoDOS = tblSubbranchInfoDOMapper
                .selectByExample(tblSubbranchInfoDOExample);
        pagination.addResult(tblSubbranchInfoDOS);
        return pagination;
    }

    @Override
    @Transactional
    public Pagination<TblMerCoreMccDo> findMerMccs(TblMerCoreMccDo tblMerCoreMccDo) {
        TblMerCoreMccDoExample tblMerCoreMccDoExample = new TblMerCoreMccDoExample();
        TblMerCoreMccDoExample.Criteria criteria = tblMerCoreMccDoExample.createCriteria();

        if (StringUtils.isNotBlank(tblMerCoreMccDo.getMccDesp())) {
            criteria.andMccDespLike("%" + tblMerCoreMccDo.getMccDesp() + "%");
        }
        int count = tblMerCoreMccDoMapper.countByExample(tblMerCoreMccDoExample);

        int pageCurrent = Integer.parseInt(tblMerCoreMccDo.getPageCurrent());
        int pageSize = Integer.parseInt(tblMerCoreMccDo.getPageSize());
        Pagination pagination = new Pagination(count, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblMerCoreMccDo> tblMerCoreMccDos = tblMerCoreMccDoMapper.selectByExample(tblMerCoreMccDoExample);
        pagination.addResult(tblMerCoreMccDos);
        return pagination;
    }

    @Override
    // @Transactional
    public int insertTblMerInfo(TblMerInfoDO tblMerInfoDO) {
        int i = tblMerInfoDOMapper.insertSelective(tblMerInfoDO);
        return i;
    }

    @Override
    public int insertTblMerBankInfo(TblMerBankInfoDO tblMerBankInfoDO) {
        int i = tblMerBankInfoDOMapper.insertSelective(tblMerBankInfoDO);
        return i;
    }

    @Override
    // @Transactional
    public int insertTblMerFeeInfo(AgentMerFeeForm agentMerFeeForm, String merId, String termId, String userName) {
        int i = 0;
        try {
            Field[] field = agentMerFeeForm.getClass().getDeclaredFields();
            String name = "";
            String value = "";
            String fee01L = "";
            String calc_mode = "";
            for (int j = 0; j < field.length; j++) {
                name = field[j].getName();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                Method m = agentMerFeeForm.getClass().getMethod("get" + name);
                value = (String) m.invoke(agentMerFeeForm);

                if (!StringUtils.isNotBlank(value))
                    continue;
                if ("MerId".equals(name)) {
                    continue;
                } else {
                    if ("Fee01L".equals(name)) {
                        fee01L = value;
                        continue;
                    } else {
                        name = name.substring(3);
                        TblMerFeeInfoDO tblMerFeeInfoDO = new TblMerFeeInfoDO();
                        tblMerFeeInfoDO.setMerId(merId);
                        tblMerFeeInfoDO.setTermId(termId);
                        tblMerFeeInfoDO.setFeeType(name);
                        tblMerFeeInfoDO.setUserName(userName);
                        tblMerFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
                        tblMerFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
                        if ("01".equals(name)) {
                            calc_mode = "MTM(0," + fee01L + ",AMT*" + value + ")";
                        } else {
                            calc_mode = "AMT*" + value;
                        }
                        tblMerFeeInfoDO.setCalcMode(calc_mode);
                        tblMerFeeInfoDOMapper.insertSelective(tblMerFeeInfoDO);
                    }
                }
            }
            i = 1;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            i = 0;
            throw new BposException("插入代理商费率时出现异常!");

            // return i;
        }
        return i;
    }

    @Override
    @Transactional
    public int insertTblTermInfo(TblTermInfoDO tblTermInfoDO) {
        int i = tblTermInfoDOMapper.insertSelective(tblTermInfoDO);
        return i;
    }

    @Override
    // @Transactional
    public int insertTblAgentMerTerm(TblAgentMerTermDo tblAgentMerTermDo) {
        int i = tblAgentMerTermDoMapper.insertSelective(tblAgentMerTermDo);
        return i;
    }

    @Override
    public TblAgentInfoDo queryAgentById(String merId) {
        TblAgentMerTermDoExample tblAgentMerTermDoExample = new TblAgentMerTermDoExample();
        tblAgentMerTermDoExample.createCriteria().andMerIdEqualTo(merId);
        List<TblAgentMerTermDo> tblAgentMerTermDos = tblAgentMerTermDoMapper.selectByExample(tblAgentMerTermDoExample);
        if (tblAgentMerTermDos.size() == 0) {
            return new TblAgentInfoDo();
        }
        TblAgentMerTermDo tblAgentMerTermDo = tblAgentMerTermDos.get(0);
        String agentId = tblAgentMerTermDo.getAgentId();
        TblAgentInfoDoExample tblAgentInfoDoExample = new TblAgentInfoDoExample();
        tblAgentInfoDoExample.createCriteria().andMemberIdEqualTo(agentId);
        List<TblAgentInfoDo> tblAgentInfoDos = tblAgentInfoDoMapper.selectByExample(tblAgentInfoDoExample);
        if (tblAgentInfoDos.size() == 0) {
            return new TblAgentInfoDo();
        }
        return tblAgentInfoDos.get(0);
    }

    /**
     * 查询商户信息
     */
    @Override
    public TblMerInfoDO queryMerById(String merId) {
        TblMerInfoDOExample tblMerInfoDOExample = new TblMerInfoDOExample();
        tblMerInfoDOExample.createCriteria().andMerIdEqualTo(merId);
        List<TblMerInfoDO> tblMerInfoDOS = tblMerInfoDOMapper.selectByExample(tblMerInfoDOExample);
        if (tblMerInfoDOS.size() == 0) {
            return new TblMerInfoDO();
        }
        return tblMerInfoDOS.get(0);
    }

    @Override
    public TblMerBankInfoDO queryMerBankById(String merId) {
        TblMerBankInfoDOExample tblMerBankInfoDOExample = new TblMerBankInfoDOExample();
        tblMerBankInfoDOExample.createCriteria().andMerIdEqualTo(merId);
        List<TblMerBankInfoDO> tblMerBankInfoDOS = tblMerBankInfoDOMapper.selectByExample(tblMerBankInfoDOExample);
        if (tblMerBankInfoDOS.size() == 0) {
            return new TblMerBankInfoDO();
        }
        return tblMerBankInfoDOS.get(0);
    }

    @Override
    public List<TblMerFeeInfoDO> queryMerFeeById(String merId) {
        TblMerFeeInfoDOExample tblMerFeeInfoDOExample = new TblMerFeeInfoDOExample();
        tblMerFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId);
        List<TblMerFeeInfoDO> tblMerFeeInfoDOS = tblMerFeeInfoDOMapper.selectByExample(tblMerFeeInfoDOExample);
        if (tblMerFeeInfoDOS.size() == 0) {
            return new ArrayList<TblMerFeeInfoDO>();
        }
        return tblMerFeeInfoDOS;
    }

    @Override
    public TblTermInfoDO queryTermById(String merId) {
        TblTermInfoDOExample tblTermInfoDOExample = new TblTermInfoDOExample();
        tblTermInfoDOExample.createCriteria().andMerIdEqualTo(merId);
        List<TblTermInfoDO> tblTermInfoDOS = tblTermInfoDOMapper.selectByExample(tblTermInfoDOExample);
        if (tblTermInfoDOS.size() == 0) {
            return new TblTermInfoDO();
        }
        return tblTermInfoDOS.get(0);
    }

    @Override
    public int updateTblMerInfo(TblMerInfoDO tblMerInfoDO) {
        int i = tblMerInfoDOMapper.updateByPrimaryKeySelective(tblMerInfoDO);
        return i;
    }

    @Override
    public int updateTblMerBankInfo(TblMerBankInfoDO tblMerBankInfoDO) {
        int i = tblMerBankInfoDOMapper.updateByPrimaryKeySelective(tblMerBankInfoDO);
        return i;
    }

    @Override
    public int updateTblTermInfo(TblTermInfoDO tblTermInfoDO) {
        int i = tblTermInfoDOMapper.updateByPrimaryKeySelective(tblTermInfoDO);
        return i;
    }

    @Override
    public Map updateTblMerFeeInfo(AgentMerFeeForm agentMerFeeForm, String merId, String termId, String userName) {
        Map resultMap = new HashMap();
        try {
            TblMerFeeInfoDOExample tblMerFeeInfoDOExample = new TblMerFeeInfoDOExample();
            tblMerFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId);
            List<TblMerFeeInfoDO> tblMerFeeInfoDOS = tblMerFeeInfoDOMapper.selectByExample(tblMerFeeInfoDOExample);
            if (tblMerFeeInfoDOS.size() == 0) {
                resultMap.put("statusCode", 300);
                resultMap.put("message", "上传失败");
                return resultMap;
            }
            Map<String, TblMerFeeInfoDO> map = new HashMap<String, TblMerFeeInfoDO>();
            for (TblMerFeeInfoDO tblMerFeeInfoDO : tblMerFeeInfoDOS) {
                System.out.println("tblMerFeeInfoDO.getFeeType(): " + tblMerFeeInfoDO.getFeeType());
                map.put(tblMerFeeInfoDO.getFeeType(), tblMerFeeInfoDO);
            }
            Field[] field = agentMerFeeForm.getClass().getDeclaredFields();

            String name = "";
            String value = "";
            String fee01L = "";
            String calc_mode = "";

            for (int j = 0; j < field.length; j++) {
                name = field[j].getName();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                Method m = agentMerFeeForm.getClass().getMethod("get" + name);
                value = (String) m.invoke(agentMerFeeForm);

                System.out.println("name: " + name);
                System.out.println("value: " + value);
                if (StringUtils.isBlank(value))
                    continue;
                if ("MerId".equals(name)) {
                    continue;
                } else {
                    if ("Fee01L".equals(name)) {
                        fee01L = value;
                        continue;
                    } else {
                        name = name.substring(3);
                        if ("01".equals(name))
                            calc_mode = "MTM(0," + fee01L + ",AMT*" + value + ")";
                        else
                            calc_mode = "AMT*" + value;
                        if (map.get(name) != null) {
                            TblMerFeeInfoDO tblMerFeeInfoDO = map.get(name);
                            tblMerFeeInfoDO.setCalcMode(calc_mode);
                            tblMerFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
                            TblMerFeeInfoDOExample tblMerFeeInfoDOExample1 = new TblMerFeeInfoDOExample();
                            tblMerFeeInfoDOExample1.createCriteria().andMerIdEqualTo(merId).andTermIdEqualTo(termId)
                                    .andFeeTypeEqualTo(name);
                            tblMerFeeInfoDOMapper.updateByExampleSelective(tblMerFeeInfoDO, tblMerFeeInfoDOExample1);
                        } else {
                            TblMerFeeInfoDO tblMerFeeInfoDO = new TblMerFeeInfoDO();
                            tblMerFeeInfoDO.setMerId(merId);
                            tblMerFeeInfoDO.setTermId(termId);
                            tblMerFeeInfoDO.setFeeType(name);
                            tblMerFeeInfoDO.setUserName(userName);
                            tblMerFeeInfoDO.setUpdateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
                            tblMerFeeInfoDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
                            tblMerFeeInfoDO.setCalcMode(calc_mode);
                            tblMerFeeInfoDOMapper.insertSelective(tblMerFeeInfoDO);
                        }
                    }
                }
            }
            resultMap.put("statusCode", 200);
            resultMap.put("message", "更新费率成功");

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            resultMap.put("statusCode", 300);
            resultMap.put("message", "更新费率失败");
            throw new BposException("更新费率上传失败");
        }
        return resultMap;
    }

    @Transactional
    public int insertTblFileMerBak(String files, String agentId, String merId) {
        String images[] = files.split(";");
        int i = 0;
        String filePath = "";
        try {
            TblMerFileInfoDO tblMerFileInfoDO = new TblMerFileInfoDO();
            tblMerFileInfoDO.setInstId(agentId);
            tblMerFileInfoDO.setInstMerId(merId);
            tblMerFileInfoDO.setMerId(merId);
            for (String image : images) {
                if (!StringUtils.isNotBlank(image)) {
                    continue;
                }
                if (image.indexOf("PIC_") == -1) {
                    continue;
                }
                filePath = image;
                image = image.substring(image.indexOf("PIC_"), image.length());
                image = image.split("_")[1];
                image = image.replace("0", "");
                int num = Integer.parseInt(image);
                switch (num) {
                    case 1:
                        tblMerFileInfoDO.setPicAddress1(filePath);
                        break;
                    case 2:
                        tblMerFileInfoDO.setPicAddress2(filePath);
                        break;
                    case 3:
                        tblMerFileInfoDO.setPicAddress3(filePath);
                        break;
                    case 4:
                        tblMerFileInfoDO.setPicAddress4(filePath);
                        break;
                    case 5:
                        tblMerFileInfoDO.setPicAddress5(filePath);
                        break;
                    case 6:
                        tblMerFileInfoDO.setPicAddress6(filePath);
                        break;
                    case 7:
                        tblMerFileInfoDO.setPicAddress7(filePath);
                        break;
                    case 8:
                        tblMerFileInfoDO.setPicAddress8(filePath);
                        break;
                    case 9:
                        tblMerFileInfoDO.setPicAddress9(filePath);
                        break;
                    case 10:
                        tblMerFileInfoDO.setPicAddress10(filePath);
                        break;
                    case 11:
                        tblMerFileInfoDO.setPicAddress11(filePath);
                        break;
                    case 12:
                        tblMerFileInfoDO.setPicAddress12(filePath);
                        break;
                    case 13:
                        tblMerFileInfoDO.setPicAddress13(filePath);
                        break;
                    case 14:
                        tblMerFileInfoDO.setPicAddress15(filePath);
                        break;
                    default:
                        break;
                }
            }
            i = tblMerFileInfoDOMapper.insert(tblMerFileInfoDO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotifyException(ErrorRespEnum.RESP009998, "插入图片失败");
        }
        return i;
    }

    @Override
    // @Transactional
    public Map deleteImage(String merId, String path, String colName) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            TblMerFileInfoDOExample tblMerFileInfoDOExample = new TblMerFileInfoDOExample();
            tblMerFileInfoDOExample.createCriteria().andMerIdEqualTo(merId);
            List<TblMerFileInfoDO> tblMerFileInfoDOS = tblMerFileInfoDOMapper.selectByExample(tblMerFileInfoDOExample);
            if (tblMerFileInfoDOS.size() == 0) {
                resultMap.put("statusCode", 300);
                resultMap.put("message", "上传失败");
                return resultMap;
            }
            File file = new File(path);
            if (file.isFile() && file.exists()) {
                file.delete();
            }
            Class[] parameterTypes = new Class[1];
            TblMerFileInfoDO tblMerFileInfoDO = tblMerFileInfoDOS.get(0);
            Field field = tblMerFileInfoDO.getClass().getDeclaredField(colName);
            parameterTypes[0] = field.getType();
            colName = colName.substring(0, 1).toUpperCase() + colName.substring(1);
            Method m = tblMerFileInfoDO.getClass().getMethod("set" + colName, parameterTypes);
            m.invoke(tblMerFileInfoDO, new Object[]{""});
            tblMerFileInfoDOMapper.updateByExample(tblMerFileInfoDO, tblMerFileInfoDOExample);
            resultMap.put("statusCode", 200);
            resultMap.put("message", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "删除失败");
            throw new BposException("删除失败");
            // return resultMap;
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Map insertTblFileMer(TblMerFileInfoDO tblMerFileInfoDO) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            TblMerFileInfoDOExample tblMerFileInfoDOExample = new TblMerFileInfoDOExample();
            tblMerFileInfoDOExample.createCriteria().andMerIdEqualTo(tblMerFileInfoDO.getMerId());
            List<TblMerFileInfoDO> tblMerFileInfoDOS = tblMerFileInfoDOMapper.selectByExample(tblMerFileInfoDOExample);
            if (tblMerFileInfoDOS.size() > 0) {
                tblMerFileInfoDOMapper.updateByExampleSelective(tblMerFileInfoDO, tblMerFileInfoDOExample);
            } else {
                tblMerFileInfoDOMapper.insert(tblMerFileInfoDO);
            }
            resultMap.put("statusCode", 200);
            resultMap.put("message", "保存成功!");
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "保存文件失败!");
            throw new BposException("保存文件失败");
            // return resultMap;
        }

        return resultMap;
    }

    @Override
    public Pagination<TblAgentMerInfoVO> queryAgentMerInfoList(Map map) {
        int count = merInfoCustVOMapper.countAgentMerInfo(map);
        int pageCurrent = Integer.valueOf((String) map.get("pageCurrent"));
        int pageSize = Integer.valueOf((String) map.get("pageSize"));

        Pagination pagination = new Pagination(count, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        logger.debug("tonny:route count value: " + count);

        List<TblAgentMerInfoVO> tblAgentMerInfoVOS = merInfoCustVOMapper.queryAgentMerInfo(map);

        //判断代理商商户是否已经注册过支付宝和微信通道
        if (tblAgentMerInfoVOS != null && tblAgentMerInfoVOS.size() > 0) {
            Boolean isRegMinSheng = false;
            Boolean isRegHanYin = false;
            Boolean isRegMinShengZf = false;
            for (TblAgentMerInfoVO tblAgentMerInfoVO : tblAgentMerInfoVOS) {
                isRegMinSheng = merChannelPerService.IsRegisted(tblAgentMerInfoVO.getMerId());
                isRegMinShengZf = merChannelPerService.IsRegistedZf(tblAgentMerInfoVO.getMerId());
                isRegHanYin = hanYinIsRegisted(tblAgentMerInfoVO.getMerId());
                if (isRegMinSheng) {
                    tblAgentMerInfoVO.setRegistedMinSheng("1");
                } else {
                    tblAgentMerInfoVO.setRegistedMinSheng("0");
                }
                if (isRegMinShengZf) {
                    tblAgentMerInfoVO.setRegistedMinShengZf("1");
                } else {
                    tblAgentMerInfoVO.setRegistedMinShengZf("0");
                }
                if (isRegHanYin) {
                    tblAgentMerInfoVO.setRegistedHanYin("1");
                } else {
                    tblAgentMerInfoVO.setRegistedHanYin("0");
                }
            }

        }


        pagination.addResult(tblAgentMerInfoVOS);
        return pagination;
    }


    //判断翰银是否注册
    public Boolean hanYinIsRegisted(String merId) {

        Boolean isR = false;
        Boolean hanYinRegisted = wechatRegisterService.isHanYinRegisted(merId, WechatRegisterServiceImpl.HAN_YIN_CHANNEL);
        if (hanYinRegisted) {
            isR = true;
        } else {
            isR = false;
        }

        return isR;
    }

    @Override
    public List<TblAgentMerInfoVO> queryAgentMerExportList(Map map) {

        List<TblAgentMerInfoVO> tblAgentMerExportLists = merInfoCustVOMapper.queryAgentMerInfo(map);
        return tblAgentMerExportLists;
    }

    @Override
    public TblMerAuditRecordDO queryMerAuditReocrdById(String recordId) {
        return tblMerAuditRecordDOMapper.selectByPrimaryKey(recordId);
    }

    @Override
    public int auditOK(String merId, String errorFields, String remark, String inRemark, String userName) {
        TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(merId);
        if (tblMerInfoDO == null) {
            return 0;
        }
        TblMerInfoDOExample tblMerInfoDOExample = new TblMerInfoDOExample();
        tblMerInfoDOExample.createCriteria().andMerIdEqualTo(merId).andMerStatEqualTo(Constant.AUDIT_NONE);
        tblMerInfoDO.setMerStat(Constant.AUDIT_IN);
        int res = tblMerInfoDOMapper.updateByExample(tblMerInfoDO, tblMerInfoDOExample);
        if (res != 0) {
            TblMerAuditRecordDO tblMerAuditRecordDO = new TblMerAuditRecordDO();
            tblMerAuditRecordDO.setAuditId(String.valueOf(seqMapper.getSequenceNextVal("MER_AGENT_AUDIT_SEQ")));
            tblMerAuditRecordDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
            tblMerAuditRecordDO.setErrorField(errorFields);
            tblMerAuditRecordDO.setRemark(remark);
            tblMerAuditRecordDO.setUserName(userName);
            tblMerAuditRecordDO.setMerId(merId);
            tblMerAuditRecordDO.setAuditResult(Constant.AUDIT_IN);
            tblMerAuditRecordDO.setInRemark(inRemark);
            tblMerAuditRecordDOMapper.insert(tblMerAuditRecordDO);
        }
        return res;
    }

    @Override
    public int auditReject(String merId, String errorFields, String remark, String inRemark, String userName) {
        TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(merId);
        if (tblMerInfoDO == null) {
            return 0;
        }
        TblMerInfoDOExample tblMerInfoDOExample = new TblMerInfoDOExample();
        tblMerInfoDOExample.createCriteria().andMerIdEqualTo(merId).andMerStatEqualTo(Constant.AUDIT_NONE);
        tblMerInfoDO.setMerStat(Constant.AUDIT_REJECT);
        int res = tblMerInfoDOMapper.updateByExample(tblMerInfoDO, tblMerInfoDOExample);
        if (res != 0) {
            TblMerAuditRecordDO tblMerAuditRecordDO = new TblMerAuditRecordDO();
            tblMerAuditRecordDO.setAuditId(String.valueOf(seqMapper.getSequenceNextVal("MER_AGENT_AUDIT_SEQ")));
            tblMerAuditRecordDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
            tblMerAuditRecordDO.setErrorField(errorFields);
            tblMerAuditRecordDO.setRemark(remark);
            tblMerAuditRecordDO.setUserName(userName);
            tblMerAuditRecordDO.setMerId(merId);
            tblMerAuditRecordDO.setAuditResult(Constant.AUDIT_REJECT);
            tblMerAuditRecordDO.setInRemark(inRemark);
            tblMerAuditRecordDOMapper.insert(tblMerAuditRecordDO);
        }
        return res;
    }

    @Override
    public Pagination<Map<String, Object>> queryMerAuditRecordList(Map<String, String> queryMap) {

        int count = tblMerAuditRecordVOMapper.countByCondition(queryMap);
        int pageCurrent = Integer.parseInt(queryMap.get("pageCurrent"));
        int pageSize = Integer.parseInt(queryMap.get("pageSize"));
        Pagination pagination = new Pagination(count, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);
        List<TblMerAuditRecordVO> tblMerAuditRecordVOs = tblMerAuditRecordVOMapper.selectByCondition(queryMap);
        pagination.addResult(tblMerAuditRecordVOs);
        return pagination;
    }

    @Override
    public TblMerAuditRecordDO queryMerAuditReocrdByMerId(String merId) {
        TblMerAuditRecordDOExample tblMerAuditRecordDOExample = new TblMerAuditRecordDOExample();
        tblMerAuditRecordDOExample.createCriteria().andMerIdEqualTo(merId);
        tblMerAuditRecordDOExample.setOrderByClause(" CREATE_TIME DESC");
        List<TblMerAuditRecordDO> tblMerAuditRecordDOList = tblMerAuditRecordDOMapper
                .selectByExample(tblMerAuditRecordDOExample);
        if (tblMerAuditRecordDOList != null && tblMerAuditRecordDOList.size() != 0) {
            return tblMerAuditRecordDOList.get(0);
        }
        return null;
    }

    @Override
    public int insettTblMerRelevanceMccDo(TblMerRelevanceMccDo tblMerRelevanceMccDo) {
        tblMerRelevanceMccDo.setMccId(String.valueOf(seqMapper.getSequenceNextVal("MER_MCC_SEQ")));
        return tblMerRelevanceMccDoMapper.insertSelective(tblMerRelevanceMccDo);
    }

    @Override
    public TblMerRelevanceMccDo queryTblMerRelevanceMccDoById(String merId) {
        TblMerRelevanceMccDoExample tblMerRelevanceMccDoExample = new TblMerRelevanceMccDoExample();
        tblMerRelevanceMccDoExample.createCriteria().andMerIdEqualTo(merId);
        List<TblMerRelevanceMccDo> tblMerRelevanceMccDos = tblMerRelevanceMccDoMapper
                .selectByExample(tblMerRelevanceMccDoExample);
        if (tblMerRelevanceMccDos != null && tblMerRelevanceMccDos.size() != 0) {
            return tblMerRelevanceMccDos.get(0);
        }
        return null;
    }

    @Override
    public int updateMerMcc(TblMerRelevanceMccDo tblMerRelevanceMccDo) {
        TblMerRelevanceMccDoExample tblMerRelevanceMccDoExample = new TblMerRelevanceMccDoExample();
        return tblMerRelevanceMccDoMapper.updateByExampleSelective(tblMerRelevanceMccDo, tblMerRelevanceMccDoExample);
    }

    @Override
    public TblMerFileInfoDO queryFilesByMerId(String merId) {
        TblMerFileInfoDOExample tblMerFileInfoDOExample = new TblMerFileInfoDOExample();
        tblMerFileInfoDOExample.createCriteria().andMerIdEqualTo(merId);
        List<TblMerFileInfoDO> tblMerFileInfoDOS = tblMerFileInfoDOMapper.selectByExample(tblMerFileInfoDOExample);
        if (tblMerFileInfoDOS.size() > 0) {
            return tblMerFileInfoDOS.get(0);
        } else
            return new TblMerFileInfoDO();
    }

    @Override
    public String getPlatFormMerId() {
        long platFormMerIdSeq = seqMapper.getSequenceNextVal("SEQ_PLATFORM_MER_ID");
        return DateUtil.getCurrentDate() + StringUtils.leftPad(String.valueOf(platFormMerIdSeq), 7, '0');
    }

    @Override
    public String getPlatFormTermId() {
        long platFormTermIdSeq = seqMapper.getSequenceNextVal("SEQ_PLATFORM_TERM_ID");
        return StringUtils.leftPad(String.valueOf(platFormTermIdSeq), 8, '0');
    }

    // 导出代理商户信息
    @Override
    public void exportAgentMerList(List<TblAgentMerInfoVO> tblAgentMerList, ServletOutputStream outputStream)
            throws Exception {

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("商户信息列表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setWrapText(false);
        String[] titles = {"代理商号", "代理商简称", "审核状态", "终端号",

                "商户号", "商户名称", "商户类型", "注册号", "注册简称", "注册地址", "注册资本", "营业执照编号", "营业执照有效期", "税务登记证", "法人代表", "法人代表证件类型",
                "法人代表证件号码", "法人代表证件有效期", "联系人", "联系号码", "联系邮箱",

                "开户行", "开户行省", "开户行市", "开户支行", "联行号", "账户类型", "账户名", "账户号",

                "MCC描述", "借记卡T1手续费", "借记卡T1封顶", "贷记卡T1手续费", "T0提现手续费", "T0垫资手续费", "微信T0交易手续费", "支付宝T0交易手续费",
                "微信T1交易手续费", "支付宝T1交易手续费", "报备状态", "MCC码"};

        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = row.createCell((short) i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(style);
        }

        if (tblAgentMerList == null || tblAgentMerList.size() == 0) {

        }

        int i = 0;
        Map<String, String> feemap = new HashMap<>();
        for (TblAgentMerInfoVO agentMerDO : tblAgentMerList) {

            TblAgentInfoDo tblAgentInfoDo = queryAgentById(agentMerDO.getMerId());

            if (tblAgentInfoDo == null) {
                tblAgentInfoDo = new TblAgentInfoDo();
            }

            TblMerInfoDO tblMerInfoDO = queryMerById(agentMerDO.getMerId());

            if (tblMerInfoDO == null) {
                tblMerInfoDO = new TblMerInfoDO();
            }
            if (tblMerInfoDO.getReportStatus() == null) {
                tblMerInfoDO.setReportStatus("");
            }

            TblMerBankInfoDO tblMerBankInfoDO = queryMerBankById(agentMerDO.getMerId());

            if (tblMerBankInfoDO == null) {
                tblMerBankInfoDO = new TblMerBankInfoDO();
            }

            List<TblMerFeeInfoDO> tblMerFeeInfoDOS = queryMerFeeById(agentMerDO.getMerId());

            if (tblMerFeeInfoDOS != null) {
                String feeType = "";
                int num = 0;
                for (TblMerFeeInfoDO tblMerFeeInfoDO : tblMerFeeInfoDOS) {
                    feeType = tblMerFeeInfoDO.getFeeType();
                    feeType = feeType.replace("0", "");
                    num = Integer.parseInt(feeType);
                    String[] calc = CalcModeUtil.splitCalcMode(tblMerFeeInfoDO.getCalcMode(), false).split(",");
                    switch (num) {
                        case 1:
                            feemap.put("借记卡T1手续费", calc[0]);
                            feemap.put("借记卡T1-封顶", calc[1]);
                            break;
                        case 2:
                            feemap.put("贷记卡T1手续费", calc[0]);
                            break;
                        case 3:
                            feemap.put("微信T0交易手续费", calc[0]);
                            break;
                        case 4:
                            feemap.put("支付宝T0交易手续费", calc[0]);
                            break;
                        case 5:
                            feemap.put("微信T1交易手续费", calc[0]);
                            break;
                        case 6:
                            feemap.put("支付宝T1交易手续费", calc[0]);
                            break;
                        case 7:
                            feemap.put("T0提现手续费", calc[0]);
                            break;
                        case 8:
                            feemap.put("T0垫资手续费", calc[0]);
                            break;
                        default:
                            break;
                    }
                }
            }

            TblMerRelevanceMccDo tblMerRelevanceMccDo = queryTblMerRelevanceMccDoById(agentMerDO.getMerId());

            if (tblMerRelevanceMccDo == null) {

                tblMerRelevanceMccDo = new TblMerRelevanceMccDo();
            }

            // 可以用来补充Mer/bank/fee info

            row = sheet.createRow((int) i + 1);

            // 代理商号
            HSSFCell cell0 = row.createCell((short) 0);
            cell0.setCellStyle(style);
            cell0.setCellValue(agentMerDO.getAgentId());

            // 1 代理商简称
            HSSFCell cell1 = row.createCell((short) 1);
            cell1.setCellStyle(style);
            cell1.setCellValue(agentMerDO.getAgentShortName());
            // 2 审核状态
            HSSFCell cell2 = row.createCell((short) 2);
            cell2.setCellStyle(style);
            cell2.setCellValue(agentMerDO.getMerStat());
            // 3 终端号
            HSSFCell cell3 = row.createCell((short) 3);
            cell3.setCellStyle(style);
            cell3.setCellValue(agentMerDO.getTermId());
            // 4 商户号
            HSSFCell cell4 = row.createCell((short) 4);
            cell4.setCellStyle(style);
            cell4.setCellValue(agentMerDO.getMerId());
            // 5 商户名称
            HSSFCell cell5 = row.createCell((short) 5);
            cell5.setCellStyle(style);
            cell5.setCellValue(tblMerInfoDO.getMerName());
            // 6 商户类型
            HSSFCell cell6 = row.createCell((short) 6);
            cell6.setCellStyle(style);
            cell6.setCellValue(tblMerInfoDO.getMerName());
            // 7 注册号
            HSSFCell cell7 = row.createCell((short) 7);
            cell7.setCellStyle(style);
            cell7.setCellValue(tblMerInfoDO.getRegName());
            // 8 注册简称
            HSSFCell cell8 = row.createCell((short) 8);
            cell8.setCellStyle(style);
            cell8.setCellValue(tblMerInfoDO.getRegShortName());
            // 9 注册地址
            HSSFCell cell9 = row.createCell((short) 9);
            cell9.setCellStyle(style);
            cell9.setCellValue(tblMerInfoDO.getMerAddress());
            // 10 注册资本
            HSSFCell cell10 = row.createCell((short) 10);
            cell10.setCellStyle(style);
            cell10.setCellValue(tblMerInfoDO.getRegFunds());
            // 11 营业执照编号
            HSSFCell cell11 = row.createCell((short) 11);
            cell11.setCellStyle(style);
            cell11.setCellValue(tblMerInfoDO.getBusLicNm());
            // 12 营业执照有效期
            HSSFCell cell12 = row.createCell((short) 12);
            cell12.setCellStyle(style);
            cell12.setCellValue(tblMerInfoDO.getBusLicExpire());
            // 13 税务登记证
            HSSFCell cell13 = row.createCell((short) 13);
            cell13.setCellStyle(style);
            cell13.setCellValue(tblMerInfoDO.getTaxRegCert());
            // 14 法人代表
            HSSFCell cell14 = row.createCell((short) 14);
            cell14.setCellStyle(style);
            cell14.setCellValue(tblMerInfoDO.getLegalPerson());
            // 15 法人代表证件类型
            HSSFCell cell15 = row.createCell((short) 15);
            cell15.setCellStyle(style);
            cell15.setCellValue(tblMerInfoDO.getLegalPersonCertType());
            // 16 法人代表证件号码
            HSSFCell cell16 = row.createCell((short) 16);
            cell16.setCellStyle(style);
            cell16.setCellValue(tblMerInfoDO.getLegalPersonCertNm());
            // 17 法人代表证件有效期
            HSSFCell cell17 = row.createCell((short) 17);
            cell17.setCellStyle(style);
            cell17.setCellValue(tblMerInfoDO.getLegalPersonCertExpire());
            // 18 联系人
            HSSFCell cell18 = row.createCell((short) 18);
            cell18.setCellStyle(style);
            cell18.setCellValue(tblMerInfoDO.getContactPerson());
            // 19 联系号码
            HSSFCell cell19 = row.createCell((short) 19);
            cell19.setCellStyle(style);
            cell19.setCellValue(tblMerInfoDO.getContactMobile());
            // 20 联系邮箱
            HSSFCell cell20 = row.createCell((short) 20);
            cell20.setCellStyle(style);
            cell20.setCellValue(tblMerInfoDO.getContactEmail());

            // 21 开户行
            HSSFCell cell21 = row.createCell((short) 21);
            cell21.setCellStyle(style);
            cell21.setCellValue(tblMerBankInfoDO.getBankName());
            // 22 开户行省
            HSSFCell cell22 = row.createCell((short) 22);
            cell22.setCellStyle(style);
            cell22.setCellValue(tblMerBankInfoDO.getProvName());
            // 23开户行市
            HSSFCell cell23 = row.createCell((short) 23);
            cell23.setCellStyle(style);
            cell23.setCellValue(tblMerBankInfoDO.getCityName());
            // 24开户支行
            HSSFCell cell24 = row.createCell((short) 24);
            cell24.setCellStyle(style);
            cell24.setCellValue(tblMerBankInfoDO.getBankBranchName());
            // 25联行号
            HSSFCell cell25 = row.createCell((short) 25);
            cell25.setCellStyle(style);
            cell25.setCellValue(tblMerBankInfoDO.getCnaps());
            // 26账户类型
            HSSFCell cell26 = row.createCell((short) 26);
            cell26.setCellStyle(style);
            if (tblMerBankInfoDO.getIsPrivate() == null) {
                cell26.setCellValue("");
            } else {
                if (tblMerBankInfoDO.getIsPrivate().equals("Y")) {
                    cell26.setCellValue("对私");
                } else {
                    cell26.setCellValue("对公");
                }
            }
            // 27 账户名
            HSSFCell cell27 = row.createCell((short) 27);
            cell27.setCellStyle(style);
            cell27.setCellValue(tblMerBankInfoDO.getAcctName());
            // 28 账户号
            HSSFCell cell28 = row.createCell((short) 28);
            cell28.setCellStyle(style);
            cell28.setCellValue(tblMerBankInfoDO.getAcctNo());

            // 29 MCC描述
            HSSFCell cell29 = row.createCell((short) 29);
            cell29.setCellStyle(style);
            if (tblMerRelevanceMccDo == null) {
                cell29.setCellValue("");
            } else {
                cell29.setCellValue(tblMerRelevanceMccDo.getMccDesp());
            }

            // 30 借记卡T1手续费
            HSSFCell cell30 = row.createCell((short) 30);
            cell30.setCellStyle(style);
            cell30.setCellValue(feemap.get("借记卡T1手续费"));

            // 31借记卡T1封顶
            HSSFCell cell31 = row.createCell((short) 31);
            cell31.setCellStyle(style);
            cell31.setCellValue(feemap.get("借记卡T1-封顶"));
            ;

            // 32贷记卡T1手续费
            HSSFCell cell32 = row.createCell((short) 32);
            cell32.setCellStyle(style);
            cell32.setCellValue(feemap.get("贷记卡T1手续费"));
            // 33T0提现手续费
            HSSFCell cell33 = row.createCell((short) 33);
            cell33.setCellStyle(style);
            cell33.setCellValue(feemap.get("T0提现手续费"));
            // 34T0垫资手续费
            HSSFCell cell34 = row.createCell((short) 34);
            cell34.setCellStyle(style);
            cell34.setCellValue(feemap.get("T0垫资手续费"));
            // 35微信T0交易手续费
            HSSFCell cell35 = row.createCell((short) 35);
            cell35.setCellStyle(style);
            cell35.setCellValue(feemap.get("微信T0交易手续费"));
            // 36支付宝T0交易手续费
            HSSFCell cell36 = row.createCell((short) 36);
            cell36.setCellStyle(style);
            cell36.setCellValue(feemap.get("支付宝T0交易手续费"));
            // 37微信T1交易手续费
            HSSFCell cell37 = row.createCell((short) 37);
            cell37.setCellStyle(style);
            cell37.setCellValue(feemap.get("微信T1交易手续费"));
            // 38支付宝T1交易手续费
            HSSFCell cell38 = row.createCell((short) 38);
            cell38.setCellStyle(style);
            cell38.setCellValue(feemap.get("支付宝T1交易手续费"));
            feemap.clear();
            // 39报备状态
            HSSFCell cell39 = row.createCell((short) 39);
            cell39.setCellStyle(style);
            cell39.setCellValue(tblMerInfoDO.getReportStatus());
            feemap.clear();
            // 40MCC
            HSSFCell cell40 = row.createCell((short) 40);
            cell40.setCellStyle(style);
            cell40.setCellValue(tblMerRelevanceMccDo.getMccValue());
            feemap.clear();
            i++;
        }
        wb.write(outputStream);
        outputStream.close();

    }

    // return code: 0 - not found the mer info
    // return code: 1 - already reported
    // return code: 2 - error happens when reporting
    // return code: 3 - success recorded the reporting
    @Override
    public Map<String, Object> reportMerAgent(String merId) {

        Map<String, Object> replymap = new HashMap<>();
        TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(merId);
        if (tblMerInfoDO == null) {
            replymap.put("statusCode", "300");
            replymap.put("message", "未找到该商户");
            return replymap;
        }
        if (tblMerInfoDO.getReportStatus() != null) {
            if (tblMerInfoDO.getReportStatus().equals("Y")) {
                replymap.put("statusCode", "300");
                replymap.put("message", "该商户已经报备");
                return replymap;
            }
        }
        TblMerInfoDOExample tblMerInfoDOExample = new TblMerInfoDOExample();
        tblMerInfoDOExample.createCriteria().andMerIdEqualTo(merId);
        tblMerInfoDO.setReportStatus("Y");
        int res = tblMerInfoDOMapper.updateByExample(tblMerInfoDO, tblMerInfoDOExample);

        if (res != 0) {
            replymap.put("statusCode", "200");
            replymap.put("message", "标记商户报备成功");
        } else {
            replymap.put("statusCode", "300");
            replymap.put("message", "标记商户报备失败");
        }
        return replymap;
    }

    ;

    @Override
    public Map<String, Object> commitNewAgentReq(String merId, String userName) {

        Map<String, Object> replymap = new HashMap<>();
        TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(merId);
        if (tblMerInfoDO == null) {
            replymap.put("statusCode", 300);
            replymap.put("message", "没有找到进件信息");
            return replymap;
        }
        TblMerInfoDOExample tblMerInfoDOExample = new TblMerInfoDOExample();
        tblMerInfoDOExample.createCriteria().andMerIdEqualTo(merId).andMerStatEqualTo(Constant.AUDIT_AWAY);
        tblMerInfoDO.setMerStat(Constant.AUDIT_NONE);
        int res = tblMerInfoDOMapper.updateByExample(tblMerInfoDO, tblMerInfoDOExample);

        if (res != 0) {
            replymap.put("statusCode", 200);
            replymap.put("message", "提交成功");
        } else {
            replymap.put("statusCode", 300);
            replymap.put("message", "提交失败");
        }
        return replymap;
    }

    ;

    public static void main(String[] args) {
        String name = "fee01";

        System.out.println(name.substring(3));
    }

}
