package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.vo.TblMerInfoVO;
import com.allcheer.bpos.mapper.MerInfoCustVOMapper;
import com.allcheer.bpos.service.MerChannelPreService;
import com.allcheer.bpos.util.Pagination;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.allcheer.bpos.service.WechatRegisterService;
import com.allcheer.bpos.service.impl.WechatRegisterServiceImpl;

/**
 * Created by APPLE on 2016/10/22.
 */
@Service
public class MerChannelPerServiceImpl implements MerChannelPreService {

	@Autowired
	private MerInfoCustVOMapper merInfoCustVOMapper;

	@Autowired
	WechatRegisterService wechatRegisterService;

	@Override
	public Pagination<TblMerInfoVO> queryMerInfoList(Map map) {

		int count = merInfoCustVOMapper.countMerInfo(map);
		int pageCurrent = Integer.valueOf((String) map.get("pageCurrent"));
		int pageSize = Integer.valueOf((String) map.get("pageSize"));

		Pagination pagination = new Pagination(count, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblMerInfoVO> tblMerInfoVOList = merInfoCustVOMapper.queryMerInfo(map);

		Boolean isRegMinSheng = false;
		Boolean isRegHanYin = false;
		Boolean isRegMinShengZf = false;
		Boolean isRegSHBank = false;
		Boolean isRegEGBank = false;

		for (TblMerInfoVO merInfoList : tblMerInfoVOList) {
			isRegMinSheng = IsRegisted(merInfoList.getMerId());
			isRegMinShengZf = IsRegistedZf(merInfoList.getMerId());
			isRegHanYin = hanYinIsRegisted(merInfoList.getMerId());
			isRegSHBank = SHBankRegisted(merInfoList.getMerId());
			isRegEGBank = EGBankRegisted(merInfoList.getMerId());
			if (isRegMinSheng) {
				merInfoList.setRegistedMinSheng("1");
			} else {
				merInfoList.setRegistedMinSheng("0");
			}
			if (isRegMinShengZf) {
				merInfoList.setRegistedMinShengZf("1");
			} else {
				merInfoList.setRegistedMinShengZf("0");
			}
			if (isRegHanYin) {
				merInfoList.setRegistedHanYin("1");
			} else {
				merInfoList.setRegistedHanYin("0");
			}
			if (isRegSHBank) {
				merInfoList.setRegistedSHBank("1");
			} else {
				merInfoList.setRegistedSHBank("0");
			}

			if (isRegEGBank) {
				merInfoList.setRegistedEGBank("1");
			} else {
				merInfoList.setRegistedEGBank("0");
			}
		}
		pagination.addResult(tblMerInfoVOList);
		return pagination;
	}

	@Override
	public List<TblMerInfoVO> merInfoList(Map map) {

		List<TblMerInfoVO> tblMerInfoVOList = merInfoCustVOMapper.queryMerInfo(map);

		if (tblMerInfoVOList == null || tblMerInfoVOList.size() == 0) {
			return tblMerInfoVOList;
		}
		Boolean isRegMinSheng = false;
		Boolean isRegHanYin = false;
		Boolean isRegMinShengZf = false;
		for (TblMerInfoVO merInfoList : tblMerInfoVOList) {
			isRegMinSheng = IsRegisted(merInfoList.getMerId());
			isRegMinShengZf = IsRegistedZf(merInfoList.getMerId());
			isRegHanYin = hanYinIsRegisted(merInfoList.getMerId());
			if (isRegMinSheng) {
				merInfoList.setRegistedMinSheng("1");
			} else {
				merInfoList.setRegistedMinSheng("0");
			}
			if (isRegMinShengZf) {
				merInfoList.setRegistedMinShengZf("1");
			} else {
				merInfoList.setRegistedMinShengZf("0");
			}
			if (isRegHanYin) {
				merInfoList.setRegistedHanYin("1");
			} else {
				merInfoList.setRegistedHanYin("0");
			}
		}
		return tblMerInfoVOList;
	}

	// 是否注册微信
	public Boolean IsRegisted(String merId) {

		Boolean isR = false;
		String memberId = merId;
		Map resultMap = new HashMap();

		Boolean webXinisRegisted = wechatRegisterService.isRegisted(memberId, WechatRegisterServiceImpl.weiXinChannel);

		if (webXinisRegisted) {
			isR = true;
		} else {
			isR = false;
		}
		return isR;
	}

	// 是否注册支付宝
	public Boolean IsRegistedZf(String merId) {

		Boolean isR = false;
		String memberId = merId;
		Map resultMap = new HashMap();

		Boolean zhiFuBaoRegisted = wechatRegisterService.isRegisted(memberId,
				WechatRegisterServiceImpl.zhiFuBaoChannel);

		if (zhiFuBaoRegisted) {
			isR = true;
		} else {
			isR = false;
		}
		return isR;
	}

	// 判断翰银是否注册
	public Boolean hanYinIsRegisted(String merId) {
		Boolean isR = false;
		Boolean hanYinRegisted = wechatRegisterService.isHanYinRegisted(merId,
				WechatRegisterServiceImpl.HAN_YIN_CHANNEL);
		if (hanYinRegisted) {
			isR = true;
		} else {
			isR = false;
		}
		return isR;
	}

	// 判断是否注册上海银行
	@Override
	public Boolean SHBankRegisted(String merId) {
		Boolean isR = false;
		Boolean SHBankRegistedWX = wechatRegisterService.isSHBankRegisted(merId,
				WechatRegisterServiceImpl.SHBANK_CHANNEL_WECHAT);

		Boolean SHBankRegistedZFB = wechatRegisterService.isSHBankRegisted(merId,
				WechatRegisterServiceImpl.SHBANK_CHANNEL_ALIPAY);

		if (SHBankRegistedWX || SHBankRegistedZFB) {
			isR = true;
		} else {
			isR = false;
		}
		return isR;
	}

	// 判断是否注册过恒丰银行
	@Override
	public Boolean EGBankRegisted(String merId) {
		Boolean isR = false;
		Boolean EGBankRegistedD0 = wechatRegisterService.isEGBankRegisted(merId, "U6");

		Boolean EGBankRegistedT1 = wechatRegisterService.isEGBankRegisted(merId, "U7");

		if (EGBankRegistedD0 || EGBankRegistedD0) {
			isR = true;
		} else {
			isR = false;
		}
		return isR;
	}
}
