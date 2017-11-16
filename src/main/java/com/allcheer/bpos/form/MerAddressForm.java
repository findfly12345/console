package com.allcheer.bpos.form;

/**
 * Created by LiuBin on 2017/1/17.
 */
public class MerAddressForm extends BaseForm {
	private String provinceId;

	private String cityId;

	private String areaId;

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

}
