package com.allcheer.bpos.form;

import com.allcheer.bpos.entity.TblAgentInfoDo;
import com.allcheer.bpos.entity.TblBtsInstDO;

import java.util.List;

/**
 * Created by APPLE on 2016/10/21.
 */
public class LeShuaMerInfoForm extends BaseForm {

	private String merId;

	private String merName;

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}


}
