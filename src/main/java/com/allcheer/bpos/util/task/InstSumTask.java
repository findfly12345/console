package com.allcheer.bpos.util.task;

import com.allcheer.bpos.entity.InstAccountingSummaryDO;
import com.allcheer.bpos.mapper.InstAccountingSummaryDOMapper;
import com.allcheer.bpos.mapper.TblBtsTransLogDOMapper;
import com.allcheer.bpos.util.BposException;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component("suminst")
public class InstSumTask {
	private Logger logger = LoggerFactory.getLogger(InstSumTask.class);
	
	@Autowired
	TblBtsTransLogDOMapper tblBtsTransLogDOMapper;
	@Autowired
	InstAccountingSummaryDOMapper instAccountingSummaryDOMapper;
	/**
	 * 默认汇总昨天交易
	 */
	public void run(){
		Calendar thisTime = Calendar.getInstance();
		Date transTime = DateUtils.addDays(thisTime.getTime(), -1);
		String format = DateFormatUtils.format(transTime, "yyyyMMdd");
		logger.debug("开始【{}】汇总交易",format);
		sumTask(format,null);
		logger.debug("汇总结束");
	}
	
	@Transactional
	public void sumTask(String transTime,String instId){
		List<InstAccountingSummaryDO> list = tblBtsTransLogDOMapper.rumGroupByInstId(transTime,instId);
		if(list != null){
			logger.debug("获取{}条记录",list.size());
			for(InstAccountingSummaryDO record:list){
				instAccountingSummaryDOMapper.insertSelective(record);
			}
			logger.debug("插入成功");
		}else{
			logger.error("无法获取汇总数据");
			throw new BposException("执行汇总任务失败");
		}
	}
}
