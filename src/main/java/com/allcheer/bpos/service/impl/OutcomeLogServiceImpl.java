package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.TBLOutcomeBankRecordDO;
import com.allcheer.bpos.entity.TBLOutcomeBankRecordDOExample;
import com.allcheer.bpos.entity.TBLOutcomeBankRecordDOExample.Criteria;
import com.allcheer.bpos.form.OutcomeTransLogForm;
import com.allcheer.bpos.mapper.OUTCOMERECORDDOMapper;
import com.allcheer.bpos.mapper.TBLOutcomeBankRecordDOMapper;
import com.allcheer.bpos.service.OutcomeLogService;
import com.allcheer.bpos.util.DateUtil;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;

import java.util.List;

@Service
public class OutcomeLogServiceImpl implements OutcomeLogService {
    @Autowired
    OUTCOMERECORDDOMapper _OUTCOMERECORDDOMapper;
    
    @Autowired
    TBLOutcomeBankRecordDOMapper _TBLOutcomeBankRecordDOMapper;

    private boolean filedNotNull(String filed) {
        return filed != null && (!"".equals(filed.trim()));
    }

    private TBLOutcomeBankRecordDOExample getSearchFiled(OutcomeTransLogForm form) {
        String memberId = form.getMemberId();
        String channelId = form.getChannelId();
        String startTransDateTime = form.getStartTransDateTime();
        String endTransDateTime = form.getEndTransDateTime();
        String transStat = form.getTransStat();

        TBLOutcomeBankRecordDOExample example = new TBLOutcomeBankRecordDOExample();
        Criteria criteria = example.createCriteria();
        if (filedNotNull(memberId)) {
            criteria.andInstMerIdEqualTo(form.getMemberId());
        }
        if (filedNotNull(channelId)) {
            criteria.andOutcomeChannelEqualTo(form.getChannelId());
        }
        if (filedNotNull(startTransDateTime)) {
            criteria.andOrderDateGreaterThanOrEqualTo(DateUtil.removeLineDateString(form.getStartTransDateTime()));
        }
        if (filedNotNull(endTransDateTime)) {
            criteria.andOrderDateLessThanOrEqualTo(DateUtil.removeLineDateString(form.getEndTransDateTime()));
        }
        if (filedNotNull(transStat)) {
            criteria.andOutcomeStatusEqualTo(form.getTransStat());
        }
        return example;
    }

    /**
     * @see com.allcheer.bpos.service.OutcomeLogService#getOutcomeTransList(com.allcheer.bpos.form.OutcomeTransLogForm)
     */
    @Override
    public List<TBLOutcomeBankRecordDO> getOutcomeTransList(OutcomeTransLogForm form) {
        TBLOutcomeBankRecordDOExample example = getSearchFiled(form);
        List<TBLOutcomeBankRecordDO> selectByExample = _TBLOutcomeBankRecordDOMapper.selectByExample(example);
        return selectByExample;
    }

    /**
     * @see com.allcheer.bpos.service.OutcomeLogService#getOutcomeTransListCount(com.allcheer.bpos.form.OutcomeTransLogForm)
     */
    @Override
    public int getOutcomeTransListCount(OutcomeTransLogForm form) {
        TBLOutcomeBankRecordDOExample example = getSearchFiled(form);
        int countByExample = _TBLOutcomeBankRecordDOMapper.countByExample(example);
        return countByExample;
    }

    /**
     * @see com.allcheer.bpos.service.OutcomeLogService#exportSettlementInfo(java.util.List, javax.servlet.ServletOutputStream)
     */
    @Override
    public void exportSettlementInfo(List<TBLOutcomeBankRecordDO> transList,
                                     ServletOutputStream outputStream) throws Exception {
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
        String[] titles = {"商户号", "商户结算订单号", "商户交易流水号", "结算请求日期", "结算金额", "结算状态", "结算帐号", "结算姓名",
                "银行号", "联行号", "联行名称"};

        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = row.createCell((short) i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(style);
        }
        int i = 0;
        for (TBLOutcomeBankRecordDO _TBLOutcomeBankRecordDO : transList) {
            row = sheet.createRow((int) i + 1);

            HSSFCell cell0 = row.createCell((short) 0);
            cell0.setCellStyle(style);
            cell0.setCellValue(_TBLOutcomeBankRecordDO.getInstMerId());

            HSSFCell cell1 = row.createCell((short) 1);
            cell1.setCellStyle(style);
            cell1.setCellValue(_TBLOutcomeBankRecordDO.getOrderId());

            HSSFCell cell2 = row.createCell((short) 2);
            cell2.setCellStyle(style);
            cell2.setCellValue(_TBLOutcomeBankRecordDO.getTransId());

            HSSFCell cell3 = row.createCell((short) 3);
            cell3.setCellStyle(style);
            cell3.setCellValue(_TBLOutcomeBankRecordDO.getTransDate());

            HSSFCell cell4 = row.createCell((short) 4);
            cell4.setCellStyle(style);
            cell4.setCellValue(_TBLOutcomeBankRecordDO.getOutcomeAmount());

            HSSFCell cell5 = row.createCell((short) 5);
            cell5.setCellStyle(style);
            cell5.setCellValue(_TBLOutcomeBankRecordDO.getOutcomeStatus());

            HSSFCell cell6 = row.createCell((short) 6);
            cell6.setCellStyle(style);
            cell6.setCellValue(_TBLOutcomeBankRecordDO.getAcctNo());

            HSSFCell cell7 = row.createCell((short) 7);
            cell7.setCellStyle(style);
            cell7.setCellValue(_TBLOutcomeBankRecordDO.getAcctName());

            HSSFCell cell8 = row.createCell((short) 8);
            cell8.setCellStyle(style);
            cell8.setCellValue(_TBLOutcomeBankRecordDO.getBankName());

            HSSFCell cell9 = row.createCell((short) 9);
            cell9.setCellStyle(style);
            cell9.setCellValue(_TBLOutcomeBankRecordDO.getBankBranchCode());

            HSSFCell cell10 = row.createCell((short) 10);
            cell10.setCellStyle(style);
            cell10.setCellValue(_TBLOutcomeBankRecordDO.getBankBranchName());

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

}
