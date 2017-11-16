package com.allcheer.bpos.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.mapper.TblBtsInstDOMapper;
import com.allcheer.bpos.util.*;

import org.apache.poi.hssf.util.Region;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allcheer.bpos.entity.TblBtsTransLogDOExample.Criteria;
import com.allcheer.bpos.form.TransLogForm;
import com.allcheer.bpos.mapper.TblBtsTransLogDOMapper;
import com.allcheer.bpos.service.TransLogService;

import javax.servlet.ServletOutputStream;

@Service
public class TransLogServiceImpl implements TransLogService {

	@Autowired
	TblBtsTransLogDOMapper tblBtsTransLogDOMapper;

	@Autowired
	private TblBtsInstDOMapper tblBtsInstDOMapper;

	@Override
	public List<TblBtsTransLogDO> getTransList(TransLogForm form) {
		TblBtsTransLogDOExample example = getSearchFiled(form);
		List<TblBtsTransLogDO> selectByExample = tblBtsTransLogDOMapper.selectByExample(example);
		return selectByExample;
	}

	@Override
	public List<InstProfitDO> rumGroupCardFlagByInstInstId(TransLogForm form) {
		String startDate = "";
		String endDate = "";
		if (form != null) {
			int month = form.getMonth();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			String dd = sdf.format(date);

			// 获取每月第一天和最后一天
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(dd));
			cal.set(Calendar.DAY_OF_MONTH, 1);

			cal.set(Calendar.MONTH, month);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			endDate = sdf2.format(cal.getTime());

			cal.set(Calendar.DAY_OF_MONTH, 1);
			startDate = sdf2.format(cal.getTime());
		}

		return tblBtsTransLogDOMapper.rumGroupCardFlagByInstInstId(form.getInstId(), startDate, endDate);
	}

	@Override
	public List<InstProfitDO> rumGroupChannelReport(TransLogForm form) {
		String startDate = "";
		String endDate = "";
		if (form != null) {
			int month = form.getMonth();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			String dd = sdf.format(date);

			// 获取每月第一天和最后一天
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(dd));
			cal.set(Calendar.DAY_OF_MONTH, 1);

			cal.set(Calendar.MONTH, month);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			endDate = sdf2.format(cal.getTime());

			cal.set(Calendar.DAY_OF_MONTH, 1);
			startDate = sdf2.format(cal.getTime());
		}

		return tblBtsTransLogDOMapper.rumGroupChannelReport(startDate, endDate);
	}

	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}

	private TblBtsTransLogDOExample getSearchFiled(TransLogForm form) {
		String cardNo = form.getCardNo();
		String instId = form.getInstId();
		String bigMerId = form.getBigMerId();
		String gateId = form.getGateId();
		String transStat = form.getTransStat();
		String refNum = form.getRefNum();
		String posSeqId = form.getPosSeqId();
		String startTransDateTime = form.getStartTransDateTime();
		String endTransDateTime = form.getEndTransDateTime();
		String agentId = form.getAgentId();
		String cardFlag = form.getCardFlag();
		String ordId = form.getOrdId();
		String merId = form.getMerId();
		String merName = form.getMerName();
		String posMerId = form.getPosMerId();
		String sysMerName = form.getSysMerName();

		TblBtsTransLogDOExample example = new TblBtsTransLogDOExample();
		Criteria criteria = example.createCriteria();
		if (filedNotNull(cardNo)) {
			criteria.andCardNoLike("%" + form.getCardNo() + "%");
		}
		if (filedNotNull(instId)) {
			criteria.andAcqInstIdCodeEqualTo(form.getInstId());
		}
		if (filedNotNull(gateId)) {
			criteria.andGateIdEqualTo(gateId);
		}
		if (filedNotNull(ordId)) {
			criteria.andOrdIdEqualTo(form.getOrdId());
		}
		if (filedNotNull(agentId)) {
			criteria.andAgentIdEqualTo(agentId);
		}
		if (filedNotNull(cardFlag)) {
			criteria.andCardFlagEqualTo(cardFlag);
		}
		if (filedNotNull(bigMerId)) {
			criteria.andInstPosMerIdEqualTo(form.getBigMerId());
		}
		if (filedNotNull(transStat)) {
			criteria.andTransStatEqualTo(form.getTransStat());
		}
		if (filedNotNull(refNum)) {
			criteria.andRefNumEqualTo(form.getRefNum());
		}
		if (filedNotNull(posSeqId)) {
			criteria.andRefNumEqualTo(form.getPosSeqId());
		}
		if (filedNotNull(startTransDateTime)) {
			criteria.andAcctDateGreaterThanOrEqualTo(DateUtil.removeLineDateString(startTransDateTime));
		}
		if (filedNotNull(endTransDateTime)) {
			criteria.andAcctDateLessThanOrEqualTo(DateUtil.removeLineDateString(endTransDateTime));
		}
		if (filedNotNull(merId)) {
			criteria.andMerIdEqualTo(merId);
		}
		if (filedNotNull(merName)) {
			criteria.andSysMerNameLike("%" + merName + "%");
		}
		if (filedNotNull(posMerId)) {
			criteria.andInstPosMerIdEqualTo(posMerId);
		}
		if (filedNotNull(sysMerName)) {
			criteria.andSysMerNameLike("%" + sysMerName + "%");
		}

		return example;
	}

	@Override
	public int getTransListCount(TransLogForm form) {
		TblBtsTransLogDOExample example = getSearchFiled(form);
		int countByExample = tblBtsTransLogDOMapper.countByExample(example);
		return countByExample;
	}

	/**
	 * @see TransLogService#exportSettlementInfo(List, ServletOutputStream)
	 */
	@Override
	public void exportSettlementInfo(List<TblBtsTransLogDO> transList, ServletOutputStream outputStream)
			throws Exception {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("记录列表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setWrapText(true);
		/*
		 * String[] titles = { "MER_ID", "ORD_ID", "CARD_NO", "TRANS_TYPE",
		 * "PNR_DEV_ID", "POS_DEV_ID", "GATE_ID", "POS_MER_ID", "POS_TERM_ID",
		 * "ORD_AMT", "REF_AMT", "FEE_AMT", "SYS_TIME", "ACCT_DATE",
		 * "SYS_SEQ_ID", "POS_SEQ_ID", "RESP_CD", "REF_NUM", "AUTH_CODE",
		 * "BATCH_ID", "CUST_ID", "MER_USR_ID", "MER_ORD_ID", "REMARK_TYPE",
		 * "REMARK", "TRANS_STAT", "BANK_STAT", "CHK_FLAG", "PROC_DRI", "PID",
		 * "MACHINE_NO", "TERM_FLAG", "CALC_MODE", "POS_SYSTEM", "FIELD22",
		 * "TRANS_DATE_TIME", "SYS_TRACE_AUDIT_NUM", "MCHNT_TYPE",
		 * "ACQ_INST_ID_CODE", "FWD_INST_ID_CODE", "INST_POS_TERM_ID",
		 * "INST_POS_MER_ID", "SYS_MER_NAME", "FLD_RESERVED", "REF_NO",
		 * "RCV_INST_ID_CODE", "TXN_NUM", "TYPE_PROCESS_CODE" };
		 */
		String[] titles = { "网关", "内部商户号", "商户号", "商户名称", "机构号", "代理商", "订单号", "卡号", "交易类型", "内部设备号", "内部终端号", "POS商户号", "POS终端号", "卡类型", "交易金额",
				"手续费公式", "交易手续费",  "分润", "交易时间", "交易日期", "系统流水号", "交易流水号", "应答码", "参考号", "授权号", "批次号", "客户号", "交易状态"};

		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row.createCell((short) i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style);
		}
		int i = 0;
		for (TblBtsTransLogDO tblBtsTransLogDO : transList) {
			row = sheet.createRow(i + 1);
			
			HSSFCell cell0 = row.createCell((short) 0);
			cell0.setCellStyle(style);
			cell0.setCellValue(tblBtsTransLogDO.getGateId());
			
			HSSFCell cell1 = row.createCell((short) 1);
			cell1.setCellStyle(style);
			cell1.setCellValue(tblBtsTransLogDO.getMachineNo());

			HSSFCell cell2 = row.createCell((short) 2);
			cell2.setCellStyle(style);
			cell2.setCellValue(tblBtsTransLogDO.getInstPosMerId());

			HSSFCell cell3 = row.createCell((short) 3);
			cell3.setCellStyle(style);
			cell3.setCellValue(tblBtsTransLogDO.getSysMerName());
			
			HSSFCell cell4 = row.createCell((short) 4);
			cell4.setCellStyle(style);
			cell4.setCellValue(tblBtsTransLogDO.getAcqInstIdCode());

			HSSFCell cell5 = row.createCell((short) 5);
			cell5.setCellStyle(style);
			cell5.setCellValue(tblBtsTransLogDO.getAgentId());

			HSSFCell cell6 = row.createCell((short) 6);
			cell6.setCellStyle(style);
			cell6.setCellValue(tblBtsTransLogDO.getOrdId());

			HSSFCell cell7 = row.createCell((short) 7);
			cell7.setCellStyle(style);
			cell7.setCellValue(tblBtsTransLogDO.getCardNo());

			HSSFCell cell8 = row.createCell((short) 8);
			cell8.setCellStyle(style);
			cell8.setCellValue("消费");

			HSSFCell cell9 = row.createCell((short) 9);
			cell9.setCellStyle(style);
			cell9.setCellValue(tblBtsTransLogDO.getPnrDevId());

			HSSFCell cell10 = row.createCell((short) 10);
			cell10.setCellStyle(style);
			cell10.setCellValue(tblBtsTransLogDO.getPosDevId());

			HSSFCell cell11 = row.createCell((short) 11);
			cell11.setCellStyle(style);
			cell11.setCellValue(tblBtsTransLogDO.getInstPosMerId());

			HSSFCell cell12 = row.createCell((short) 12);
			cell12.setCellStyle(style);
			cell12.setCellValue(tblBtsTransLogDO.getInstPosTermId());

			HSSFCell cell13 = row.createCell((short) 13);
			cell13.setCellStyle(style);
			cell13.setCellValue(tblBtsTransLogDO.getCardFlag());
			
			HSSFCell cell14 = row.createCell((short) 14);
			cell14.setCellStyle(style);
			cell14.setCellValue(tblBtsTransLogDO.getOrdAmt());
			
			HSSFCell cell15 = row.createCell((short) 15);
			cell15.setCellStyle(style);
			cell15.setCellValue(tblBtsTransLogDO.getCalcMode());

			HSSFCell cell16 = row.createCell((short) 16);
			cell16.setCellStyle(style);
			cell16.setCellValue(tblBtsTransLogDO.getFeeAmt());
			
			HSSFCell cell17 = row.createCell((short) 17);
			cell17.setCellStyle(style);
			cell17.setCellValue(tblBtsTransLogDO.getProfitAmt());

			HSSFCell cell18 = row.createCell((short) 18);
			cell18.setCellStyle(style);
			cell18.setCellValue(tblBtsTransLogDO.getSysTime());

			HSSFCell cell19 = row.createCell((short) 19);
			cell19.setCellStyle(style);
			cell19.setCellValue(tblBtsTransLogDO.getAcctDate());

			HSSFCell cell20 = row.createCell((short) 20);
			cell20.setCellStyle(style);
			cell20.setCellValue(tblBtsTransLogDO.getSysSeqId());

			HSSFCell cell21 = row.createCell((short) 21);
			cell21.setCellStyle(style);
			cell21.setCellValue(tblBtsTransLogDO.getPosSeqId());

			HSSFCell cell22 = row.createCell((short) 22);
			cell22.setCellStyle(style);
			cell22.setCellValue(tblBtsTransLogDO.getRespCd());

			HSSFCell cell23 = row.createCell((short) 23);
			cell23.setCellStyle(style);
			cell23.setCellValue(tblBtsTransLogDO.getRefNum());

			HSSFCell cell24 = row.createCell((short) 24);
			cell24.setCellStyle(style);
			cell24.setCellValue(tblBtsTransLogDO.getAuthCode());

			HSSFCell cell25 = row.createCell((short) 25);
			cell25.setCellStyle(style);
			cell25.setCellValue(tblBtsTransLogDO.getBatchId());

			HSSFCell cell26 = row.createCell((short) 26);
			cell26.setCellStyle(style);
			cell26.setCellValue(tblBtsTransLogDO.getCustId());

			HSSFCell cell27 = row.createCell((short) 27);
			cell27.setCellStyle(style);
			if ("S".equals(tblBtsTransLogDO.getTransStat()))
				cell27.setCellValue("成功");
			else if ("F".equals(tblBtsTransLogDO.getTransStat()))
				cell27.setCellValue("失败");
			else if ("I".equals(tblBtsTransLogDO.getTransStat()))
				cell27.setCellValue("初始");
			else
				cell27.setCellValue("未知");

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
	public List<InstProfitSummaryDO> rumGroupByAgentIdAndType(String agentId, String acctDate, String transStat) {
		return tblBtsTransLogDOMapper.rumGroupByAgentId(agentId, acctDate, transStat);
	}

	@Override
	public List<InstProfitSummaryDO> rumGroupByIinstIdAndType(String instId, String acctDate, String transStat) {
		return tblBtsTransLogDOMapper.rumGroupByInstInstId(instId, acctDate, transStat);
	}

	@Override
	public int selectInstByFeeAmmtAndAccoDateAndGageIdAndTransStat(String acctDate) {
		return tblBtsTransLogDOMapper.selectInstByFeeAmmtAndAccoDateAndGageIdAndTransStat(acctDate);
	}

	@Override
	public List<TblBtsTransLogDO> rumByIinstIdAndType(String acctDate) {
		TblBtsTransLogDOExample tblBtsTransLogDOExample = new TblBtsTransLogDOExample();
		tblBtsTransLogDOExample.createCriteria().andAcctDateBetween("20170301", "20170331").andTransStatEqualTo("S")
				.andCardFlagEqualTo("1");
		;
		// tblBtsTransLogDOExample.createCriteria().andAcctDateEqualTo(acctDate).andTransStatEqualTo("S").andCardFlagEqualTo("1");
		return tblBtsTransLogDOMapper.selectByExample(tblBtsTransLogDOExample);
	}

	@Override
	public int saveTransLog(TblBtsTransLogDO tblBtsTransLogDO) {
		return tblBtsTransLogDOMapper.saveTransLog(tblBtsTransLogDO.getOrdId(), tblBtsTransLogDO.getRefAmt());
	}

	@Override
	public void exporInstProfitInfo(List<InstProfitDO> transList, ServletOutputStream outputStream, TransLogForm trans)
			throws Exception {

		String startDate = "";
		String endDate = "";
		if (trans != null) {
			int month = trans.getMonth();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			String dd = sdf.format(date);

			// 获取每月第一天和最后一天
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(dd));
			cal.set(Calendar.DAY_OF_MONTH, 1);

			cal.set(Calendar.MONTH, month);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			endDate = sdf2.format(cal.getTime());

			cal.set(Calendar.DAY_OF_MONTH, 1);
			startDate = sdf2.format(cal.getTime());
		}

		// 创建workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 二、设置边框:
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 三、设置居中:
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		// 创建sheet页
		HSSFSheet sheet = workbook.createSheet("机构分润总汇");
		// 创建单元格 0
		HSSFRow row = sheet.createRow(0);

		HSSFCell c0 = row.createCell(0);
		c0.setCellValue(new HSSFRichTextString("期间"));
		c0.setCellStyle(cellStyle);

		HSSFCell c1 = row.createCell(1);
		c1.setCellValue(new HSSFRichTextString("机构名称"));
		c1.setCellStyle(cellStyle);

		HSSFCell c2 = row.createCell(2);
		c2.setCellValue(new HSSFRichTextString("机构号"));
		c2.setCellStyle(cellStyle);

		HSSFCell c4 = row.createCell(3);
		c4.setCellValue(new HSSFRichTextString("借记卡交易"));
		c4.setCellStyle(cellStyle);

		HSSFCell c5 = row.createCell(6);
		c5.setCellValue(new HSSFRichTextString("贷记卡交易"));
		c5.setCellStyle(cellStyle);

		HSSFCell c12 = row.createCell(9);
		c12.setCellValue(new HSSFRichTextString("机构分润"));
		c12.setCellStyle(cellStyle);

		// 创建单元格 1
		HSSFRow row1 = sheet.createRow(1);
		HSSFCell c6 = row1.createCell(3);
		c6.setCellValue(new HSSFRichTextString("交易笔数"));
		c6.setCellStyle(cellStyle);

		HSSFCell c7 = row1.createCell(4);
		c7.setCellValue(new HSSFRichTextString("交易金额"));
		c7.setCellStyle(cellStyle);

		HSSFCell c8 = row1.createCell(5);
		c8.setCellValue(new HSSFRichTextString("分润金额"));
		c8.setCellStyle(cellStyle);

		HSSFCell c9 = row1.createCell(6);
		c9.setCellValue(new HSSFRichTextString("交易笔数"));
		c9.setCellStyle(cellStyle);

		HSSFCell c10 = row1.createCell(7);
		c10.setCellValue(new HSSFRichTextString("交易金额"));
		c10.setCellStyle(cellStyle);

		HSSFCell c11 = row1.createCell(8);
		c11.setCellValue(new HSSFRichTextString("分润金额"));
		c11.setCellStyle(cellStyle);

		Region region1 = new Region(0, (short) 0, 1, (short) 0);
		Region region2 = new Region(0, (short) 1, 1, (short) 1);
		Region region3 = new Region(0, (short) 2, 1, (short) 2);
		Region region4 = new Region(0, (short) 3, 0, (short) 5);
		Region region5 = new Region(0, (short) 6, 0, (short) 8);
		Region region6 = new Region(0, (short) 9, 1, (short) 9);
		sheet.addMergedRegion(region1);
		sheet.addMergedRegion(region2);
		sheet.addMergedRegion(region3);
		sheet.addMergedRegion(region4);
		sheet.addMergedRegion(region5);
		sheet.addMergedRegion(region6);

		// 查询机构费率
		TblBtsInstDO tblBtsInstDO = tblBtsInstDOMapper.instAndProfit(trans.getInstId());

		/**
		 * 设置金额参数
		 */
		// 处理分润(借记卡)
		String debitProfit = "0.0";// 借记卡
		String creditProfit = "0.0";// 贷记卡
		String instProfitSum = "0.0"; // 总分润
		InstProfitDO instProfitDO1 = new InstProfitDO();
		InstProfitDO instProfitDO2 = new InstProfitDO();
		if (transList != null && transList.size() > 0) {

			for (InstProfitDO tran : transList) {

				// 借记卡
				if ("1".equals(tran.getCardFlag())) {
					instProfitDO1 = tran;
					BigDecimal b1 = new BigDecimal(tran.getFeeAmtSum());
					BigDecimal b2 = new BigDecimal(tran.getRefAmtSum());
					debitProfit = b1.subtract(b2).toString();
					debitProfit = String.valueOf(new Money(debitProfit).getYuan());
				} else {
					// 贷记卡
					instProfitDO2 = tran;
					String calcMode = tblBtsInstDO.getCalcMode();
					calcMode = calcMode.substring(4);
					calcMode = AmtUtil.divideWithoutFormat(calcMode, "100");
					String fee = AmtUtil.multiplyWithoutTwoFormat(tran.getOrdAmtSum(), calcMode);
					creditProfit = Double.toString(Double.valueOf(tran.getFeeAmtSum()) - Double.valueOf(fee));
					creditProfit = String.valueOf(new Money(creditProfit).getYuan());
				}

			}

			// 求总分润
			BigDecimal b1 = new BigDecimal(debitProfit);
			BigDecimal b2 = new BigDecimal(creditProfit);
			instProfitSum = b1.add(b2).toString();
			instProfitSum = String.valueOf(new Money(instProfitSum).getYuan());
		}

		row = sheet.createRow((int) 2);
		int index = 0;

		HSSFCell cell0 = row.createCell((short) index++);
		cell0.setCellStyle(cellStyle);
		cell0.setCellValue(startDate + "~" + endDate);

		HSSFCell cell01 = row.createCell((short) index++);
		cell01.setCellStyle(cellStyle);
		if (tblBtsInstDO != null) {
			cell01.setCellValue(tblBtsInstDO.getInstName());
		}

		HSSFCell cell02 = row.createCell((short) index++);
		cell02.setCellStyle(cellStyle);
		cell02.setCellValue(trans.getInstId());

		HSSFCell cell03 = row.createCell((short) index++);
		cell03.setCellStyle(cellStyle);
		cell03.setCellValue(instProfitDO1.getCnt() == null ? "0" : instProfitDO1.getCnt());

		HSSFCell cell04 = row.createCell((short) index++);
		cell04.setCellStyle(cellStyle);
		cell04.setCellValue(instProfitDO1.getOrdAmtSum() == null ? "0.0" : instProfitDO1.getOrdAmtSum());

		HSSFCell cell05 = row.createCell((short) index++);
		cell05.setCellStyle(cellStyle);
		cell05.setCellValue(debitProfit);

		HSSFCell cell06 = row.createCell((short) index++);
		cell06.setCellStyle(cellStyle);
		cell06.setCellValue(instProfitDO2.getCnt() == null ? "0" : instProfitDO2.getCnt());

		HSSFCell cell07 = row.createCell((short) index++);
		cell07.setCellStyle(cellStyle);
		cell07.setCellValue(instProfitDO2.getOrdAmtSum() == null ? "0.0" : instProfitDO2.getOrdAmtSum());

		HSSFCell cell08 = row.createCell((short) index++);
		cell08.setCellStyle(cellStyle);
		cell08.setCellValue(creditProfit);

		HSSFCell cell09 = row.createCell((short) index++);
		cell09.setCellStyle(cellStyle);
		cell09.setCellValue(instProfitSum);

		try {
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook.cloneSheet(0);
		}

	}

	/**
	 * 通道报表
	 *
	 * @param transList
	 * @param outputStream
	 * @param trans
	 * @throws Exception
	 */
	@Override
	public void exporchannelReport(List<InstProfitDO> transList, ServletOutputStream outputStream, TransLogForm trans)
			throws Exception {
		String startDate = "";
		String endDate = "";
		if (trans != null) {
			int month = trans.getMonth();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			String dd = sdf.format(date);

			// 获取每月第一天和最后一天
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(dd));
			cal.set(Calendar.DAY_OF_MONTH, 1);

			cal.set(Calendar.MONTH, month);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			endDate = sdf2.format(cal.getTime());

			cal.set(Calendar.DAY_OF_MONTH, 1);
			startDate = sdf2.format(cal.getTime());
		}

		// 创建workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 二、设置边框:
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 三、设置居中:
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		// 创建sheet页
		HSSFSheet sheet = workbook.createSheet("通道分润");
		// 创建单元格 0
		HSSFRow row = sheet.createRow(0);

		HSSFCell c0 = row.createCell(0);
		c0.setCellValue(new HSSFRichTextString("序号"));
		c0.setCellStyle(cellStyle);

		HSSFCell c1 = row.createCell(1);
		c1.setCellValue(new HSSFRichTextString("期间"));
		c1.setCellStyle(cellStyle);

		HSSFCell c2 = row.createCell(2);
		c2.setCellValue(new HSSFRichTextString("网关"));
		c2.setCellStyle(cellStyle);

		HSSFCell c4 = row.createCell(3);
		c4.setCellValue(new HSSFRichTextString("借记卡交易"));
		c4.setCellStyle(cellStyle);

		HSSFCell c5 = row.createCell(6);
		c5.setCellValue(new HSSFRichTextString("贷记卡交易"));
		c5.setCellStyle(cellStyle);

		HSSFCell c12 = row.createCell(9);
		c12.setCellValue(new HSSFRichTextString("合计分润"));
		c12.setCellStyle(cellStyle);

		// 创建单元格 1
		HSSFRow row1 = sheet.createRow(1);
		HSSFCell c6 = row1.createCell(3);
		c6.setCellValue(new HSSFRichTextString("交易笔数"));
		c6.setCellStyle(cellStyle);

		HSSFCell c7 = row1.createCell(4);
		c7.setCellValue(new HSSFRichTextString("交易金额"));
		c7.setCellStyle(cellStyle);

		HSSFCell c8 = row1.createCell(5);
		c8.setCellValue(new HSSFRichTextString("分润金额"));
		c8.setCellStyle(cellStyle);

		HSSFCell c9 = row1.createCell(6);
		c9.setCellValue(new HSSFRichTextString("交易笔数"));
		c9.setCellStyle(cellStyle);

		HSSFCell c10 = row1.createCell(7);
		c10.setCellValue(new HSSFRichTextString("交易金额"));
		c10.setCellStyle(cellStyle);

		HSSFCell c11 = row1.createCell(8);
		c11.setCellValue(new HSSFRichTextString("分润金额"));
		c11.setCellStyle(cellStyle);

		Region region1 = new Region(0, (short) 0, 1, (short) 0);
		Region region2 = new Region(0, (short) 1, 1, (short) 1);
		Region region3 = new Region(0, (short) 2, 1, (short) 2);
		Region region4 = new Region(0, (short) 3, 0, (short) 5);
		Region region5 = new Region(0, (short) 6, 0, (short) 8);
		Region region6 = new Region(0, (short) 9, 1, (short) 9);
		sheet.addMergedRegion(region1);
		sheet.addMergedRegion(region2);
		sheet.addMergedRegion(region3);
		sheet.addMergedRegion(region4);
		sheet.addMergedRegion(region5);
		sheet.addMergedRegion(region6);

		InstProfitDO inst1 = new InstProfitDO();
		InstProfitDO inst2 = new InstProfitDO();
		InstProfitDO inst3 = new InstProfitDO();
		InstProfitDO inst4 = new InstProfitDO();

		if (transList != null && transList.size() > 0) {
			for (InstProfitDO tran1 : transList) {
				if ("U1".equals(tran1.getGateId())) {
					inst1.setGateId("U1");
					if ("1".equals(tran1.getCardFlag())) {
						inst1.setCnt(tran1.getCnt());
						inst1.setOrdAmtSum(tran1.getOrdAmtSum());
						BigDecimal b1 = new BigDecimal(tran1.getFeeAmtSum());
						BigDecimal b2 = new BigDecimal(tran1.getRefAmtSum());
						inst1.setProfitAmt(b1.subtract(b2).toString());
					} else {
						inst2.setCnt(tran1.getCnt());
						inst2.setOrdAmtSum(tran1.getOrdAmtSum());
						inst2.setProfitAmt(tran1.getProfitAmt());
					}

				} else {
					inst2.setGateId("U2");
					if ("1".equals(tran1.getCardFlag())) {
						inst3.setCnt(tran1.getCnt());
						inst3.setOrdAmtSum(tran1.getOrdAmtSum());
						BigDecimal b1 = new BigDecimal(tran1.getFeeAmtSum());
						BigDecimal b2 = new BigDecimal(tran1.getRefAmtSum());
						inst3.setProfitAmt(b1.subtract(b2).toString());
					} else {
						inst4.setCnt(tran1.getCnt());
						inst4.setOrdAmtSum(tran1.getOrdAmtSum());
						inst4.setProfitAmt(tran1.getProfitAmt());
					}
				}

			}
		}
		// ======================START U1
		row = sheet.createRow((int) 2);
		int index = 0;

		HSSFCell cell0 = row.createCell((short) index++);
		cell0.setCellStyle(cellStyle);
		cell0.setCellValue("1");

		HSSFCell cell01 = row.createCell((short) index++);
		cell01.setCellStyle(cellStyle);
		cell01.setCellValue(startDate + "~" + endDate);

		HSSFCell cell02 = row.createCell((short) index++);
		cell02.setCellStyle(cellStyle);
		cell02.setCellValue("钱宝");

		HSSFCell cell03 = row.createCell((short) index++);
		cell03.setCellStyle(cellStyle);
		if (inst1 != null) {
			cell03.setCellValue(inst1.getCnt() == null ? "0" : inst1.getCnt());
		}

		HSSFCell cell04 = row.createCell((short) index++);
		cell04.setCellStyle(cellStyle);
		if (inst1 != null) {
			cell04.setCellValue(inst1.getOrdAmtSum() == null ? "0.0" : inst1.getOrdAmtSum());
		}

		HSSFCell cell05 = row.createCell((short) index++);
		cell05.setCellStyle(cellStyle);
		if (inst1 != null) {
			cell05.setCellValue("");
		}

		HSSFCell cell06 = row.createCell((short) index++);
		cell06.setCellStyle(cellStyle);
		if (inst2 != null) {
			cell06.setCellValue(inst2.getCnt() == null ? "0" : inst2.getCnt());
		}

		HSSFCell cell07 = row.createCell((short) index++);
		cell07.setCellStyle(cellStyle);
		if (inst2 != null) {
			cell07.setCellValue(inst2.getOrdAmtSum() == null ? "0.0" : inst2.getOrdAmtSum());
		}

		HSSFCell cell08 = row.createCell((short) index++);
		cell08.setCellStyle(cellStyle);
		if (inst2 != null) {
			cell08.setCellValue("");
		}

		HSSFCell cell09 = row.createCell((short) index++);
		cell09.setCellStyle(cellStyle);
		cell09.setCellValue("");
		// ======================START U

		// ======================START U2
		row = sheet.createRow((int) 3);
		int index2 = 0;

		HSSFCell cell021 = row.createCell((short) index2++);
		cell021.setCellStyle(cellStyle);
		cell021.setCellValue("2");

		HSSFCell cell22 = row.createCell((short) index2++);
		cell22.setCellStyle(cellStyle);
		cell22.setCellValue(startDate + "~" + endDate);

		HSSFCell cell023 = row.createCell((short) index2++);
		cell023.setCellStyle(cellStyle);
		cell023.setCellValue("银嘉");

		HSSFCell cell024 = row.createCell((short) index2++);
		cell024.setCellStyle(cellStyle);
		if (inst3 != null) {
			cell024.setCellValue(inst3.getCnt() == null ? "0" : inst3.getCnt());
		}

		HSSFCell cell025 = row.createCell((short) index2++);
		cell025.setCellStyle(cellStyle);
		if (inst3 != null) {
			cell025.setCellValue(inst3.getOrdAmtSum() == null ? "0.0" : inst3.getOrdAmtSum());
		}

		HSSFCell cell25 = row.createCell((short) index2++);
		cell25.setCellStyle(cellStyle);
		if (inst3 != null) {
			cell25.setCellValue("");
		}

		HSSFCell cell27 = row.createCell((short) index2++);
		cell27.setCellStyle(cellStyle);
		if (inst4 != null) {
			cell27.setCellValue(inst4.getCnt() == null ? "0" : inst4.getCnt());
		}

		HSSFCell cell28 = row.createCell((short) index2++);
		cell28.setCellStyle(cellStyle);
		if (inst4 != null) {
			cell28.setCellValue(inst4.getOrdAmtSum() == null ? "0.0" : inst4.getOrdAmtSum());
		}

		HSSFCell cell29 = row.createCell((short) index2++);
		cell29.setCellStyle(cellStyle);
		if (inst4 != null) {
			cell29.setCellValue("");
		}

		HSSFCell cell30 = row.createCell((short) index2++);
		cell30.setCellStyle(cellStyle);
		cell30.setCellValue("");
		// ======================START U2

		try {
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook.cloneSheet(0);
		}
	}

}
