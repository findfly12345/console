package com.allcheer.bpos.service;

import java.util.List;
import java.util.Map;

import com.allcheer.bpos.entity.TblAgentInfoDo;
import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblCustAddressDo;
import com.allcheer.bpos.entity.TblMerBankInfoDO;
import com.allcheer.bpos.entity.TblMerFeeInfoDO;
import com.allcheer.bpos.entity.TblMerFileInfoDO;
import com.allcheer.bpos.entity.TblMerInfoDO;
import com.allcheer.bpos.entity.TblMerRelevanceMccDo;
import com.allcheer.bpos.entity.vo.TblMerInfoVO;
import com.allcheer.bpos.form.MerInfoForm;
import com.allcheer.bpos.util.Pagination;

/**
 */
public interface QuickMerService {

		
	/**
	 * 查询商户列表
	 */
	public Pagination<TblMerInfoVO> queryMerList(MerInfoForm merForm);

	
	/**
	 * 查询快捷商户列表
	 */
	public Pagination<TblMerInfoVO> queryQuickMerList(MerInfoForm merForm);
	
	/**
	 * 注册快捷到翰银
	 */
	public Map<String, String> registerQuickMerToChannel (String merId, String channel);
	
	/**
	 * 查询代理商信息
	 */
	public TblAgentInfoDo queryAgentById(String merId);
	
	
	/**
	 * 查询机构商信息
	 */
	public TblBtsInstDO queryInstById(String merId);


    /**
     * 查询商户信息
     */
	public TblMerInfoDO queryMerById(String merId);

    /**
     * 查询结算信息
     */
	public TblMerBankInfoDO queryMerBankById(String merId);

    /**
     * 查询费率信息
     */
    public List<TblMerFeeInfoDO> queryMerFeeById(String merId);

    /**
     * 查询附件信息
     */
    public TblMerFileInfoDO queryFilesByMerId(String merId);


    /**
     * 查询MCC信息
     */
    public TblMerRelevanceMccDo queryTblMerRelevanceMccDoById(String merId);	
	
    /**
     * 查询地址码信息
     */
    public TblCustAddressDo queryMerAddressCodeById(String merId);

    /**
     * 更新商户信息
     */
    public Map<String, String> updateQuickMer(MerInfoForm merForm);


    /**
     * 更新翰银渠道商户报备信息
     */
    public Map<String, String> modifyQuickMerToChannel(String merId, String Channel);


    /**
     * 创建无卡商户信息
     */
    public Boolean saveQuickMerIntoSystem(TblMerInfoDO MerInfoDo, String instCode, String agentCode);
	
}
