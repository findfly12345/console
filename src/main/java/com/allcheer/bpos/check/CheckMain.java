package com.allcheer.bpos.check;

import com.allcheer.bpos.check.entity.constant.CheckConfigKey;
import com.allcheer.bpos.check.entity.constant.CheckStatus;
import com.allcheer.bpos.entity.GateBankCheckLog;
import com.allcheer.bpos.entity.GateBankCheckLogExample;
import com.allcheer.bpos.entity.GateParamConfigDO;
import com.allcheer.bpos.entity.GateParamConfigDOExample;
import com.allcheer.bpos.mapper.GateBankCheckLogMapper;
import com.allcheer.bpos.mapper.GateParamConfigDOMapper;
import com.allcheer.bpos.util.JsonUtil;
import com.allcheer.bpos.util.SpringUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("checkTask")
public class CheckMain {
    private Logger logger = LoggerFactory.getLogger(CheckMain.class);

    @Autowired
    private GateBankCheckLogMapper gateBankCheckLogMapper;

    @Autowired
    private GateParamConfigDOMapper gateParamConfigDOMapper;

    @Autowired
    private CheckService checkService;

    public void run() throws Exception {
        logger.debug("启动对帐任务");
        List<String> gateIds = new ArrayList<String>();
        Date thisTime = new Date();
        Date taskTime = null;

        GateParamConfigDOExample example = new GateParamConfigDOExample();
        example.createCriteria().andParamKeyEqualTo(
                CheckConfigKey.CHECK_TASK_DATE.toString());
        List<GateParamConfigDO> taskParamList = gateParamConfigDOMapper
                .selectByExample(example);
        for (GateParamConfigDO task : taskParamList) {
            taskTime = DateUtils.parseDate(task.getParamValue(),
                    new String[]{"HH:mm:ss"});
            taskTime.setYear(thisTime.getYear());
            taskTime.setMonth(thisTime.getMonth());
            taskTime.setDate(thisTime.getDate());
            if (taskTime.getTime() < thisTime.getTime()) {
                gateIds.add(task.getGateId());
            }
        }

        Map<String, String> taskDateRunClass = new HashMap<String, String>();
        example.clear();
        example.createCriteria().andGateIdIn(gateIds)
                .andParamKeyEqualTo(CheckConfigKey.CHECK_CLASS_NAME.toString());
        taskParamList = gateParamConfigDOMapper.selectByExample(example);
        for (GateParamConfigDO space : taskParamList) {
            taskDateRunClass.put(space.getGateId(), space.getParamValue());
        }

        List<String> alreadyGateIds = new ArrayList<String>();
        String thisEndTime = DateFormatUtils.format(thisTime, "yyyyMMdd")
                + "235959";
        String thisStartTime = DateFormatUtils.format(thisTime, "yyyyMMdd")
                + "000000";
        GateBankCheckLogExample example1 = new GateBankCheckLogExample();
        example1.createCriteria().andGateIdIn(gateIds)
                .andLastBankCheckTimeLessThanOrEqualTo(thisEndTime)
                .andLastBankCheckTimeGreaterThanOrEqualTo(thisStartTime);
        List<GateBankCheckLog> alreadyCheckGate = gateBankCheckLogMapper
                .selectByExample(example1);
        for (GateBankCheckLog alreadyCheck : alreadyCheckGate) {
            alreadyGateIds.add(alreadyCheck.getGateId());
        }

        String beanName = null;
        Date addDays = null;
        for (String checkId : gateIds) {
            if (!alreadyGateIds.contains(checkId)) {
                Map<String, String> config = getCheckConfig(checkId);
                String i = config.get(CheckConfigKey.CHECK_DATE.toString());
                if (i == null) {
                    logger.error("网关【{}】缺少配置【{}】参数", checkId, CheckConfigKey.CHECK_DATE.toString());
                    continue;
                }
                addDays = DateUtils.addDays(thisTime, Integer.parseInt(i));
                GateBankCheckLog initCheckLog = new GateBankCheckLog();
                initCheckLog.setGateId(checkId);
                String checkDate = DateFormatUtils.format(addDays, "yyyyMMdd");
                initCheckLog.setBankCheckDate(checkDate);
                initCheckLog.setBankCheckResult(CheckStatus.I.toString());
                initCheckLog.setBankCheckTimes("1");
                initCheckLog.setLastBankCheckTime(DateFormatUtils.format(
                        thisTime, "yyyyMMddHHmmss"));
                gateBankCheckLogMapper.insert(initCheckLog);

                try {
                    beanName = taskDateRunClass.get(checkId);
                    ((CheckService) SpringUtil.getBean(beanName)).run(checkId,
                            addDays, config);
                    initCheckLog.setBankCheckResult(CheckStatus.S.toString());
                } catch (Exception e) {
                    logger.error("网关【{}】对帐【{}】失败", checkId, checkDate, e);
                    initCheckLog.setBankCheckResult(CheckStatus.F.toString());
                }
                GateBankCheckLogExample example2 = new GateBankCheckLogExample();
                example2.createCriteria().andGateIdEqualTo(checkId)
                        .andBankCheckDateEqualTo(checkDate);
                gateBankCheckLogMapper.updateByExampleSelective(initCheckLog,
                        example2);
            }
        }
        logger.debug("对帐结束");
    }

    private Map<String, String> getCheckConfig(String gateId) {
        Map<String, String> configMap = new HashMap<String, String>();
        GateParamConfigDOExample example = new GateParamConfigDOExample();
        example.createCriteria().andGateIdEqualTo(gateId);
        List<GateParamConfigDO> configList = gateParamConfigDOMapper
                .selectByExample(example);
        String key = null;
        String value = null;
        for (GateParamConfigDO config : configList) {
            key = config.getParamKey();
            value = config.getParamValue();
            configMap.put(key, value);
        }
        logger.debug("config setting:{}", JsonUtil.toJson(configMap));
        return configMap;
    }
}
