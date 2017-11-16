package com.allcheer.bpos.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.allcheer.bpos.util.AmtUtil;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.StringUtils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allcheer.bpos.entity.InstProfitSummaryDO;
import com.allcheer.bpos.entity.WCINCOMEDO;
import com.allcheer.bpos.entity.WCINCOMEDOExample;
import com.allcheer.bpos.entity.WCINCOMEDOExample.Criteria;
import com.allcheer.bpos.form.WCTransLogForm;
import com.allcheer.bpos.mapper.WCINCOMEDOMapper;
import com.allcheer.bpos.service.WCTransLogService;

@Service
public class WCTransLogServiceImpl implements WCTransLogService {
	@Autowired
	WCINCOMEDOMapper _WCINCOMEDOMapper;

	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}

	/**
	 * @see com.allcheer.bpos.service.WCTransLogService#getTransList(com.allcheer.bpos.form.WCTransLogForm)
	 */
	@Override
	public List<WCINCOMEDO> getTransList(WCTransLogForm form) {
		WCINCOMEDOExample example = getSearchFiled(form);
		List<WCINCOMEDO> selectByExample = _WCINCOMEDOMapper.selectByExample(example);
		return selectByExample;
	}

	/**
	 * @see com.allcheer.bpos.service.WCTransLogService#getTransList(com.allcheer.bpos.form.WCTransLogForm)
	 */
	@Override
	public List<WCINCOMEDO> getTransListForExport(WCTransLogForm form) {
		WCINCOMEDOExample example = getSearchFiled(form);
		List<WCINCOMEDO> selectByExample = _WCINCOMEDOMapper.selectByExampleForExport(example);
		return selectByExample;
	}

	/**
	 * @see com.allcheer.bpos.service.WCTransLogService#getWCIncomeListCount(com.allcheer.bpos.form.WCTransLogForm)
	 */
	@Override
	public int getWCIncomeListCount(WCTransLogForm form) {
		WCINCOMEDOExample example = getSearchFiled(form);
		int countByExample = _WCINCOMEDOMapper.countByExample(example);
		return countByExample;
	}

	private WCINCOMEDOExample getSearchFiled(WCTransLogForm form) {
		String incomePlatform = form.getIncomePlatform();
		String memberId = form.getMemberId();
		String instId = form.getInstId();
		String mobileNo = form.getMobileNo();
		String transStat = form.getTransStat();
		String startTransDateTime = form.getStartTransDateTime();
		String endTransDateTime = form.getEndTransDateTime();
		String agentId = form.getAgentId();
		String cardFlag = form.getCardFlag();
		String incomeId = form.getIncomeId();

		WCINCOMEDOExample example = new WCINCOMEDOExample();
		Criteria criteria = example.createCriteria();
		if (filedNotNull(memberId)) {
			criteria.andMemberIdEqualTo(form.getMemberId());
		}
		if (filedNotNull(instId)) {
			criteria.andInstIdEqualTo(form.getInstId());
		}
		if (filedNotNull(agentId)) {
			criteria.andAgentIdEqualTo(form.getAgentId());
		}
		if (filedNotNull(incomeId)) {
			criteria.andIncomeIdEqualTo(form.getIncomeId());
		}
		if (filedNotNull(cardFlag)) {
			criteria.andCardFlagEqualTo(form.getCardFlag());
		}
		if (filedNotNull(startTransDateTime)) {
			criteria.andIncomeRecvDateGreaterThanOrEqualTo(DateUtil.removeLineDateString(form.getStartTransDateTime()));
		}
		if (filedNotNull(endTransDateTime)) {
			criteria.andIncomeRecvDateLessThanOrEqualTo(DateUtil.removeLineDateString(form.getEndTransDateTime()));
		}
		if (filedNotNull(mobileNo)) {
			criteria.andIncomeMobileEqualTo(form.getMobileNo());
		}
		if (filedNotNull(transStat)) {
			criteria.andIncomeStatusEqualTo(form.getTransStat());
		}
		if (filedNotNull(incomePlatform)) {
			criteria.andIncomePlatformEqualTo(form.getIncomePlatform());
		}
		return example;
	}

	@Override
	public void exportSettlementInfo(List<WCINCOMEDO> transList, ServletOutputStream outputStream) throws Exception {

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
		String[] titles = { "交易类型", "交易平台", "交易金额", "交易流水号", "交易组织号", "交易手机号", "交易状态", "交易对账状态", "会员号", "机构号", "交易接收日期",
				"交易接收时间", "交易发送日期", "交易发送时间", "返回码", "返回消息", "返回日期", "返回时间", "清算日期", "清算流水号" };

		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row.createCell((short) i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style);
		}
		int i = 0;
		for (WCINCOMEDO _WCINCOMEDO : transList) {
			row = sheet.createRow((int) i + 1);

			HSSFCell cell0 = row.createCell((short) 0);
			cell0.setCellStyle(style);
			cell0.setCellValue(_WCINCOMEDO.getIncomeType());

			HSSFCell cell1 = row.createCell((short) 1);
			cell1.setCellStyle(style);
			cell1.setCellValue(_WCINCOMEDO.getIncomePlatform());

			String transAmt = _WCINCOMEDO.getIncomeAmount();
			if (StringUtils.filedNotNull(transAmt)) {
				transAmt = AmtUtil.amtFormat((new BigDecimal(transAmt).divide(new BigDecimal(100))).toString());
				HSSFCell cell2 = row.createCell((short) 2);
				cell2.setCellStyle(style);
				cell2.setCellValue(transAmt);
			} else {
				transAmt = "0.00";
				HSSFCell cell2 = row.createCell((short) 2);
				cell2.setCellStyle(style);
				cell2.setCellValue(transAmt);
			}

			HSSFCell cell3 = row.createCell((short) 3);
			cell3.setCellStyle(style);
			cell3.setCellValue(_WCINCOMEDO.getIncomeId());

			HSSFCell cell4 = row.createCell((short) 4);
			cell4.setCellStyle(style);
			cell4.setCellValue(_WCINCOMEDO.getIncomeOrgId());

			HSSFCell cell5 = row.createCell((short) 5);
			cell5.setCellStyle(style);
			cell5.setCellValue(_WCINCOMEDO.getIncomeMobile());

			HSSFCell cell6 = row.createCell((short) 6);
			cell6.setCellStyle(style);
			cell6.setCellValue(_WCINCOMEDO.getIncomeStatus());

			HSSFCell cell7 = row.createCell((short) 7);
			cell7.setCellStyle(style);
			cell7.setCellValue(_WCINCOMEDO.getIncomeAuditStatus());

			HSSFCell cell8 = row.createCell((short) 8);
			cell8.setCellStyle(style);
			cell8.setCellValue(_WCINCOMEDO.getMemberId());

			HSSFCell cell9 = row.createCell((short) 9);
			cell9.setCellStyle(style);
			cell9.setCellValue(_WCINCOMEDO.getInstId());

			HSSFCell cell10 = row.createCell((short) 10);
			cell10.setCellStyle(style);
			cell10.setCellValue(_WCINCOMEDO.getIncomeRecvDate());

			HSSFCell cell11 = row.createCell((short) 11);
			cell11.setCellStyle(style);
			cell11.setCellValue(_WCINCOMEDO.getIncomeRecvTime());

			HSSFCell cell12 = row.createCell((short) 12);
			cell12.setCellStyle(style);
			cell12.setCellValue(_WCINCOMEDO.getIncomeSendDate());

			HSSFCell cell13 = row.createCell((short) 13);
			cell13.setCellStyle(style);
			cell13.setCellValue(_WCINCOMEDO.getIncomeSendTime());

			HSSFCell cell14 = row.createCell((short) 14);
			cell14.setCellStyle(style);
			cell14.setCellValue(_WCINCOMEDO.getReturnCode());

			HSSFCell cell15 = row.createCell((short) 15);
			cell15.setCellStyle(style);
			cell15.setCellValue(_WCINCOMEDO.getReturnMsg());

			HSSFCell cell16 = row.createCell((short) 16);
			cell16.setCellStyle(style);
			cell16.setCellValue(_WCINCOMEDO.getReturnDate());

			HSSFCell cell17 = row.createCell((short) 17);
			cell17.setCellStyle(style);
			cell17.setCellValue(_WCINCOMEDO.getReturnTime());

			HSSFCell cell18 = row.createCell((short) 18);
			cell18.setCellStyle(style);
			cell18.setCellValue(_WCINCOMEDO.getClearDate());

			HSSFCell cell19 = row.createCell((short) 19);
			cell19.setCellStyle(style);
			cell19.setCellValue(_WCINCOMEDO.getClearId());
			i++;
		}
		try {
			wb.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			wb.close();
		}
	}

	@Override
	public List<InstProfitSummaryDO> rumGroupByInstIdAndType(String instId, String transDate, String incomeStatus) {
		List<InstProfitSummaryDO> instProfitSummaryDOs = _WCINCOMEDOMapper.rumGroupByInstId(instId, transDate,
				incomeStatus);
		return instProfitSummaryDOs;
	}

	@Override
	public List<InstProfitSummaryDO> rumGroupByAgentIdAndType(String agentId, String transDate, String incomeStatus) {
		List<InstProfitSummaryDO> instProfitSummaryDOList = _WCINCOMEDOMapper.rumGroupByAgentId(agentId, transDate,
				incomeStatus);
		return instProfitSummaryDOList;
	}

}
