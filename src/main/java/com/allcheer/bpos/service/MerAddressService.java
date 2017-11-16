package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblMerAddressDo;
import com.allcheer.bpos.entity.TblMerLeshuaAddressDo;
import com.allcheer.bpos.form.MerAddressForm;
import com.allcheer.bpos.util.Pagination;

public interface MerAddressService {

	public Pagination<TblMerAddressDo> findMerAddressInfos(MerAddressForm merAddressForm);

	public int insertMerAddress(TblMerLeshuaAddressDo tblMerLeshuaAddressDo);

}
