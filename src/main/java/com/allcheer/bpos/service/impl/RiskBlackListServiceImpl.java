package com.allcheer.bpos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allcheer.bpos.entity.TblMerBankInfoDO;
import com.allcheer.bpos.entity.TblMerFeeInfoDO;
import com.allcheer.bpos.entity.TblMerRelevanceMccDo;
import com.allcheer.bpos.entity.TblRiskBlackList;
import com.allcheer.bpos.entity.TblRiskBlackListExample;
import com.allcheer.bpos.entity.TblRiskBlackListExample.Criteria;
import com.allcheer.bpos.entity.vo.TblMerInfoVO;
import com.allcheer.bpos.form.RiskBlackListForm;
import com.allcheer.bpos.mapper.TblRiskBlackListMapper;
import com.allcheer.bpos.service.RiskBlackListService;
import com.allcheer.bpos.util.BposException;
import com.allcheer.bpos.util.CalcModeUtil;

@Service
public class RiskBlackListServiceImpl implements RiskBlackListService{
	private Logger logger = LoggerFactory.getLogger(RiskBlackListServiceImpl.class);
	
	@Autowired
	TblRiskBlackListMapper tblRiskBlackListMapper;

	
	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}
	
	private TblRiskBlackListExample getSearchFiled(RiskBlackListForm form){
		String priCardNo = form.getPriCardNo();
		String merchId = form.getMerchId();		
		String termCode = form.getTermCode();


		TblRiskBlackListExample example = new TblRiskBlackListExample();
		Criteria criteria = example.createCriteria();
		
		if (filedNotNull(priCardNo)) {
			criteria.andPriCardNoEqualTo(priCardNo);
		}
		if (filedNotNull(merchId)) {
			criteria.andMerchIdEqualTo(merchId);
		}
		if(filedNotNull(termCode)){
			criteria.andTermCodeEqualTo(termCode);
		}
		return example;
	} 
	
	
	@Override
	public int countByExample(RiskBlackListForm form) {
		TblRiskBlackListExample example = getSearchFiled(form);
		int i = tblRiskBlackListMapper.countByExample(example);
		return i;
	}

	@Override
	public List<TblRiskBlackList> selectByExample(RiskBlackListForm form) {
		TblRiskBlackListExample example = getSearchFiled(form);
		List<TblRiskBlackList> list = tblRiskBlackListMapper.selectByExample(example);
		return list;
	}

	@Override
	public int deleteByExample(RiskBlackListForm form) throws BposException {
		String priCardNo = form.getPriCardNo();
		String merchId = form.getMerchId();		
		String termCode = form.getTermCode();
		
		if(filedNotNull(priCardNo) && filedNotNull(merchId) &&  filedNotNull(termCode)){
			TblRiskBlackListExample example = new TblRiskBlackListExample();
			example.createCriteria().andPriCardNoEqualTo(priCardNo).andMerchIdEqualTo(merchId).andTermCodeEqualTo(termCode);
			int i = tblRiskBlackListMapper.deleteByExample(example);
			return i;
		}
		
		throw new BposException("删除黑名单失败");
	}

	@Override
	public int insert(TblRiskBlackList record) throws BposException {
		gateRequired(record);
		return tblRiskBlackListMapper.insertSelective(record);
	}

	@Override
	public int updateByExample(TblRiskBlackList record) throws BposException {
		String priCardNo = record.getPriCardNo();
		String merchId = record.getMerchId();		
		String termCode = record.getTermCode();
		
		if(filedNotNull(priCardNo) && filedNotNull(merchId) &&  filedNotNull(termCode)){
			gateRequired(record);
			TblRiskBlackListExample example = new TblRiskBlackListExample();
			example.createCriteria().andPriCardNoEqualTo(priCardNo).andMerchIdEqualTo(merchId).andTermCodeEqualTo(termCode);
			int i = tblRiskBlackListMapper.updateByExampleSelective(record, example);
			return i;
		}
		throw new BposException("更新黑名单失败");
	}
	private void gateRequired(TblRiskBlackList record) throws BposException {
		String priCardNo = record.getPriCardNo();
		String merchId = record.getMerchId();		
		String termCode = record.getTermCode();
		
		
		TblRiskBlackListExample tblRiskBlackListExample = new TblRiskBlackListExample();
		tblRiskBlackListExample.createCriteria().andPriCardNoEqualTo(priCardNo).andMerchIdEqualTo(merchId).andTermCodeEqualTo(termCode);
		
		List<TblRiskBlackList> riskBlackList = tblRiskBlackListMapper.selectByExample(tblRiskBlackListExample);
		if(riskBlackList == null){
			throw new BposException("原黑名单不存在");
		}
	}

	// 导出风控触发商户
	@Override
	public void exportRiskTriggerMer(List<TblRiskBlackList> userList, ServletOutputStream outputStream)
			throws Exception {

		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("风控触发商户列表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setWrapText(false);
		String[] titles = { "卡号", "机构号", "商户号", "终端号", "触发规则", "状态", "创建日期", "创建时间", "备注"};
				
		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row.createCell((short) i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style);
		}

		int i = 0;
		Map<String, String> feemap = new HashMap<>();
		for (TblRiskBlackList tblRiskBlackList : userList) {

			row = sheet.createRow((int) i + 1);

			// 卡号
			HSSFCell cell0 = row.createCell((short) 0);
			cell0.setCellStyle(style);
			cell0.setCellValue(tblRiskBlackList.getPriCardNo());

			// 机构号
			HSSFCell cell1 = row.createCell((short) 1);
			cell1.setCellStyle(style);
			cell1.setCellValue(tblRiskBlackList.getInstCode());
			// 商户号
			HSSFCell cell2 = row.createCell((short) 2);
			cell2.setCellStyle(style);	
			cell2.setCellValue(tblRiskBlackList.getMerchId());
			// 终端号
			HSSFCell cell3 = row.createCell((short) 3);
			cell3.setCellStyle(style);
			cell3.setCellValue(tblRiskBlackList.getTermCode());
			// 触发规则
			HSSFCell cell4 = row.createCell((short) 4);
			cell4.setCellStyle(style);
			cell4.setCellValue(tblRiskBlackList.getErrRule());
			// 状态
			HSSFCell cell5 = row.createCell((short) 5);
			cell5.setCellStyle(style);
			String riskFlag = "";
			if (tblRiskBlackList.getRiskFlag().equals("Y")) {
				riskFlag = "启用";
			}
			if (tblRiskBlackList.getRiskFlag().equals("N")) {
				riskFlag = "关闭";
			}
			cell5.setCellValue(riskFlag);
			// 创建日期
			HSSFCell cell6 = row.createCell((short) 6);
			cell6.setCellStyle(style);		
			cell6.setCellValue(tblRiskBlackList.getCreateDate());
			// 创建时间
			HSSFCell cell7 = row.createCell((short) 7);
			cell7.setCellStyle(style);
			cell7.setCellValue(tblRiskBlackList.getCreateTime());			
			// 备注
			HSSFCell cell8 = row.createCell((short) 8);
			cell8.setCellStyle(style);
			cell8.setCellValue(tblRiskBlackList.getRemark());							
			i++;
		}
		wb.write(outputStream);
		outputStream.close();
	}
		
}
