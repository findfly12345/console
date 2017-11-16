package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.form.CheckFileForm;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

/**
 * Created by LiuBin on 2017/1/5.
 */
public interface CheckWithOutCardService {

    public Map<String,String> getMs020(String settleDate);

    public List<TblMsCheckFileDo> getMsCheckFile(String settleDate);

    public List<TblT1CheckFileRespondeDo> getT1CheckFile(CheckFileForm form);

    public List<TblT0CheckFileResponseDo> getT0CheckFile(CheckFileForm form);

    public Map compareT1CheckFile(String settleDate);

    public List<TblWechatIncomeCheckFileDo> getDifferenceWithCheckFile(CheckFileForm form);

    public List<TblCapitalClearingDo> getMerchantT1Clearing(String settleDate, String merchantCode);

    public Map<String,String> getMs026(String settleDate,String merchantCode);

    public Map<String,String> getMs025(String settleDate);

    public Map<String,String> getMs024(String settleDate);

    public int getCheckFileListCount(CheckFileForm form);

    public int getMsCheckFileListCount(CheckFileForm form);

    public int getWeChatIncomeCheckFileListCount(CheckFileForm form);

    public int geT1CheckFileListCount(CheckFileForm form);

    public int geT0CheckFileListCount(CheckFileForm form);
       
	void exportSettlementInfo(List<TblT1CheckFileRespondeDo> tblT1CheckFileRespondeDoList,
            ServletOutputStream outputStream, String instCode) throws Exception;
	
	void exportD0SettlementInfo(List<TblT0CheckFileResponseDo> tblT0CheckFileRespondeDoList,
            ServletOutputStream outputStream, String instCode) throws Exception;
}
