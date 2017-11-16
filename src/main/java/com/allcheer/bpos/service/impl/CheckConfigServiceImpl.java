package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.check.entity.constant.CheckConfigKey;
import com.allcheer.bpos.entity.GateParamConfigDO;
import com.allcheer.bpos.entity.TblBtsGateRoutDO;
import com.allcheer.bpos.form.CheckConfigForm;
import com.allcheer.bpos.mapper.TblBtsGateRoutDOMapper;
import com.allcheer.bpos.service.CheckConfigService;
import com.allcheer.bpos.service.GateConfigService;
import com.allcheer.bpos.util.BposException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckConfigServiceImpl implements CheckConfigService {
	private Logger logger = LoggerFactory.getLogger(CheckConfigServiceImpl.class);

	@Autowired
	GateConfigService gateConfigService;
	@Autowired
	TblBtsGateRoutDOMapper tblBtsGateRoutDOMapper;

	private static final String TASK_DATE = CheckConfigKey.CHECK_TASK_DATE.toString();
	private static final String CHECK_DATE = CheckConfigKey.CHECK_DATE.toString();
	private static final String CHECK_CLASS = CheckConfigKey.CHECK_CLASS_NAME.toString();

	@Override
	public int count(CheckConfigForm form) {
		String gateId = form.getGateId();
		int i = gateConfigService.countByExample(gateId, CheckConfigKey.CHECK_TASK_DATE.toString());
		return i;
	}

	@Override
	public List<CheckConfigForm> select(CheckConfigForm form) {
		List<CheckConfigForm> list = new ArrayList<CheckConfigForm>();
		CheckConfigForm config = null;
		String gateId = form.getGateId();
		Map<String, GateParamConfigDO> task = listToMap(gateConfigService.selectByExample(gateId, TASK_DATE));
		Map<String, GateParamConfigDO> check = listToMap(gateConfigService.selectByExample(gateId, CHECK_DATE));
		for (String gateid : task.keySet()) {
			config = new CheckConfigForm();
			config.setGateId(gateid);
			config.setCheckDate(check.get(gateid).getParamValue());
			config.setTaskDate(task.get(gateid).getParamValue());
			list.add(config);
		}
		return list;
	}

	@Override
	@Transactional
	public int delete(CheckConfigForm form) {
		String gateId = form.getGateId();
		gateConfigService.deleteByExample(gateId, TASK_DATE);
		gateConfigService.deleteByExample(gateId, CHECK_DATE);
		return 0;
	}

	@Override
	@Transactional
	public int insert(CheckConfigForm form) throws BposException {
		String gateId = form.getGateId();
		gateRequired(gateId);
		GateParamConfigDO task = new GateParamConfigDO();
		task.setGateId(gateId);
		task.setParamKey(TASK_DATE);
		task.setParamValue(form.getTaskDate());
		gateConfigService.insert(task);
		task.setParamKey(CHECK_DATE);
		task.setParamValue(form.getCheckDate());
		gateConfigService.insert(task);
		return 0;
	}

	@Override
	@Transactional
	public int update(CheckConfigForm form) throws BposException {
		String gateId = form.getGateId();
		gateRequired(gateId);
		gateConfigService.updateByExample(gateId, TASK_DATE, form.getTaskDate());
		gateConfigService.updateByExample(gateId, CHECK_DATE, form.getCheckDate());
		return 0;
	}

	private Map<String, GateParamConfigDO> listToMap(List<GateParamConfigDO> list) {
		Map<String, GateParamConfigDO> map = new HashMap<String, GateParamConfigDO>();
		for (GateParamConfigDO config : list) {
			map.put(config.getGateId(), config);
		}
		return map;
	}
	private void gateRequired(String gateId) throws BposException {
		TblBtsGateRoutDO gateRout = tblBtsGateRoutDOMapper.selectByPrimaryKey(gateId);
		if(gateRout == null){
			logger.error("插入网关【{}】对帐配置失败，原因：网关不存在",gateId);
			throw new BposException("插入网关对帐配置失败，原因：网关不存在");
		}
		List<GateParamConfigDO> list = gateConfigService.selectByExample(gateId, CHECK_CLASS);
		if(list == null || list.size() <1){
			logger.error("插入网关【{}】对帐配置失败，原因：对帐实现类不存在",gateId);
			throw new BposException("插入网关对帐配置失败，原因：对帐实现类不存在");
		}
	}
}
