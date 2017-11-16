package com.allcheer.bpos.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.constant.NotifyException;
import com.allcheer.bpos.domain.MerFee;
import com.allcheer.bpos.domain.PhotoBO;
import com.allcheer.bpos.domain.Minsheng.EnterRepuest;
import com.allcheer.bpos.entity.TBLCoreCardBinDO;
import com.allcheer.bpos.entity.TBLCoreCardBinDOExample;
import com.allcheer.bpos.entity.TblAgentInfoDo;
import com.allcheer.bpos.entity.TblAgentMerTermDo;
import com.allcheer.bpos.entity.TblAgentMerTermDoExample;
import com.allcheer.bpos.entity.TblBankInfo;
import com.allcheer.bpos.entity.TblBankInfoExample;
import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblCityDO;
import com.allcheer.bpos.entity.TblCityDOExample;
import com.allcheer.bpos.entity.TblCustAddressDo;
import com.allcheer.bpos.entity.TblInstMerTermRelDO;
import com.allcheer.bpos.entity.TblInstMerTermRelDOExample;
import com.allcheer.bpos.entity.TblMerAddressDo;
import com.allcheer.bpos.entity.TblMerBankInfoDO;
import com.allcheer.bpos.entity.TblMerChannelPreInfoDO;
import com.allcheer.bpos.entity.TblMerChannelPreInfoDOExample;
import com.allcheer.bpos.entity.TblMerFeeInfoDO;
import com.allcheer.bpos.entity.TblMerFeeInfoDOExample;
import com.allcheer.bpos.entity.TblMerFileInfoDO;
import com.allcheer.bpos.entity.TblMerFileInfoDOExample;
import com.allcheer.bpos.entity.TblMerInfoDO;
import com.allcheer.bpos.entity.TblMerLeshuaAddressDo;
import com.allcheer.bpos.entity.TblOnlineUserMapDO;
import com.allcheer.bpos.entity.TblOnlineUserMapDOExample;
import com.allcheer.bpos.entity.TblProvDO;
import com.allcheer.bpos.entity.TblProvDOExample;
import com.allcheer.bpos.entity.TblMerAddressDo;
import com.allcheer.bpos.entity.TblMerAddressDoExample;
import com.allcheer.bpos.entity.TblTermInfoDO;
import com.allcheer.bpos.entity.Enum.ErrorRespEnum;
import com.allcheer.bpos.entity.Enum.FeeTypeEnum;
import com.allcheer.bpos.entity.Enum.MerStatusEnum;
import com.allcheer.bpos.entity.Enum.MerTypeEnum;
import com.allcheer.bpos.entity.Enum.PhotoTypeEnum;
import com.allcheer.bpos.entity.Enum.SettleTypeEnum;
import com.allcheer.bpos.entity.vo.MerDetailInfo;
import com.allcheer.bpos.form.MerAddressForm;
import com.allcheer.bpos.form.MerBankInfoForm;
import com.allcheer.bpos.form.MerDetailInfoForm;
import com.allcheer.bpos.form.MerFeeForm;
import com.allcheer.bpos.mapper.SeqMapper;
import com.allcheer.bpos.mapper.TBLCoreCardBinDOMapper;
import com.allcheer.bpos.mapper.TblAgentInfoDoMapper;
import com.allcheer.bpos.mapper.TblAgentMerTermDoMapper;
import com.allcheer.bpos.mapper.TblBankInfoMapper;
import com.allcheer.bpos.mapper.TblBtsInstDOMapper;
import com.allcheer.bpos.mapper.TblCityDOMapper;
import com.allcheer.bpos.mapper.TblCustAddressDoMapper;
import com.allcheer.bpos.mapper.TblInstMerTermRelDOMapper;
import com.allcheer.bpos.mapper.TblMerBankInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerChannelPreInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerFeeInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerFileInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerLeshuaAddressDoMapper;
import com.allcheer.bpos.mapper.TblOnlineUserLoginDOMapper;
import com.allcheer.bpos.mapper.TblOnlineUserMapDOMapper;
import com.allcheer.bpos.mapper.TblProvDOMapper;
import com.allcheer.bpos.mapper.TblSubbranchInfoCustDOMapper;
import com.allcheer.bpos.mapper.TblMerAddressDoMapper;
import com.allcheer.bpos.mapper.TblTermInfoDOMapper;
import com.allcheer.bpos.service.AddressService;
import com.allcheer.bpos.service.MerAddressService;
import com.allcheer.bpos.service.MerchInfoService;
import com.allcheer.bpos.service.OpenLoginUserService;
import com.allcheer.bpos.service.QuickMerService;
import com.allcheer.bpos.util.CalcModeUtil;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import com.github.pagehelper.PageHelper;

import jodd.util.StringUtil;

/**
 * Created by fireWorks on 2016/10/25.
 */
@Service("merAddressService")
public class MerAddressServiceImpl implements MerAddressService {

	@Autowired
	TblMerAddressDoMapper tblMerAddressDoMapper;

	@Autowired
	TblMerLeshuaAddressDoMapper tblMerLeshuaAddressDoMapper;

	public Pagination<TblMerAddressDo> findMerAddressInfos(MerAddressForm merAddressForm) {
		TblMerAddressDoExample tblMerAddressDoExample = new TblMerAddressDoExample();
		TblMerAddressDoExample.Criteria criteria = tblMerAddressDoExample.createCriteria();
		if (StringUtils.isNotBlank(merAddressForm.getProvinceId())) {
			criteria.andProvinceIdLike("%" + merAddressForm.getProvinceId() + "%");
		}
		if (StringUtils.isNotBlank(merAddressForm.getCityId())) {
			criteria.andCityIdLike("%" + merAddressForm.getCityId() + "%");
		}
		if (StringUtils.isNotBlank(merAddressForm.getAreaId())) {
			criteria.andAreaIdLike("%" + merAddressForm.getAreaId() + "%");
		}

		int count = tblMerAddressDoMapper.countByExample(tblMerAddressDoExample);
		int pageCurrent = Integer.parseInt(merAddressForm.getPageCurrent());
		int pageSize = Integer.parseInt(merAddressForm.getPageSize());
		Pagination pagination = new Pagination(count, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblMerAddressDo> TblMerAddressDoS = tblMerAddressDoMapper.selectByExample(tblMerAddressDoExample);
		pagination.addResult(TblMerAddressDoS);
		return pagination;
	}

	public int insertMerAddress(TblMerLeshuaAddressDo tblMerLeshuaAddressDo) {
		int count = 0;

		count = tblMerLeshuaAddressDoMapper.insert(tblMerLeshuaAddressDo);

		return count;
	}

}