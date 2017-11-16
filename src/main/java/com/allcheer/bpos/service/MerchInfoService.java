package com.allcheer.bpos.service;

import com.allcheer.bpos.domain.Minsheng.EnterRepuest;
import com.allcheer.bpos.entity.TblMerFileInfoDO;
import com.allcheer.bpos.entity.vo.MerDetailInfo;
import com.allcheer.bpos.form.MerBankInfoForm;
import com.allcheer.bpos.form.MerDetailInfoForm;
import com.allcheer.bpos.form.MerFeeForm;

import java.util.List;
import java.util.Map;

/**
 * Created by fireWorks on 2016/10/25.
 */
public interface MerchInfoService {

    public MerDetailInfoForm queryMerDetailInfo2(MerDetailInfoForm merDetailInfoForm);

    public MerDetailInfo queryMerDetailInfo(String merId);

    public Map updateMerBankInfo(MerBankInfoForm merBankInfoForm) throws Exception;

    public Map updateMerfee(MerFeeForm merFeeForm) throws Exception;

    public MerFeeForm getMerfee(MerFeeForm merFeeForm);

    public MerBankInfoForm getMerBankInfo(MerBankInfoForm merBankInfoForm);

	public Map<String, String> addMerInfo(EnterRepuest rep);

	public String merExisted(EnterRepuest rep);
	
	public List<TblMerFileInfoDO> getMerPhotosFileInfo(String merId);

	/**
	 * 判断商户是机构商户还是代理商商户
	 * @param MerID
	 * @return
	 */
	public Map<String, String> InstOrAgent(String merId);

    /**
     * 判断是机构,是商户,还是代理商
     * @param MerID
     * @return
     */
	public Map<String, String> InstAgentMer(String merNumber);

	
	/**
	 * 判断机构是否注册
	 */
	public Boolean isInstExist(String instId);

	/**
	 * 验证结算信息
	 */
	public String verifyBankInfo(String bankName, String subBankBranchNumber, String payType, String bankAcctNo);
}
