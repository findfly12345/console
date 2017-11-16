package com.allcheer.bpos.domain.Minsheng;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import javax.xml.bind.annotation.XmlRootElement;


import lombok.Data;

@Data
@XmlRootElement(name="intoPicAddressRepuest")  
public class IntoPicAddressRepuest {
	@NotNull(message="合作机构号不能为空")
	private String instId;
	
    @NotNull(message="商户号不能为空")
    private String merId;
    
    @NotNull(message="机构商务号不能为空")
    private String instMerId;
    
    @NotNull(message="营业执照不能为空")
    private String picAddress1;
    
    @NotNull(message="法人代表身份证正面不能为空")
    private String picAddress2;
    
    @NotNull(message="法人代表身份证反面不能为空")
    private String picAddress3;
    
    @NotNull(message="开户许可证复印件不能为空")
    private String picAddress4;
    
    @NotNull(message="组织机构代码证复印件不能为空")
    private String picAddress5;
    
    @NotNull(message="税务登记证复印件不能为空")
    private String picAddress6;
    
    @NotNull(message="商户门头照片不能为空")
    private String picAddress7;
    
    @NotNull(message="商户内部照片不能为空")
    private String picAddress8;
    
    @NotNull(message="商户经营产品照片不能为空")
    private String picAddress9;
    
    @NotNull(message="银行卡正面照片不能为空")
    private String picAddress10;
    
    @NotNull(message="银行卡反面照片不能为空")
    private String picAddress11;
    
    @NotNull(message="商户信息调查表不能为空")
    private String picAddress12;
    
    @NotNull(message="合同协议不能为空")
    private String picAddress13;
    
    @NotNull(message="其他资料不能为空")
    private String picAddress15;

	/**
	 * @return the instId
	 */
	public String getInstId() {
		return instId;
	}

	/**
	 * @param instId the instId to set
	 */
	public void setInstId(String instId) {
		this.instId = instId;
	}

	/**
	 * @return the merId
	 */
	public String getMerId() {
		return merId;
	}

	/**
	 * @param merId the merId to set
	 */
	public void setMerId(String merId) {
		this.merId = merId;
	}

	/**
	 * @return the instMerId
	 */
	public String getInstMerId() {
		return instMerId;
	}

	/**
	 * @param instMerId the instMerId to set
	 */
	public void setInstMerId(String instMerId) {
		this.instMerId = instMerId;
	}

	/**
	 * @return the picAddress1
	 */
	public String getPicAddress1() {
		return picAddress1;
	}

	/**
	 * @param picAddress1 the picAddress1 to set
	 */
	public void setPicAddress1(String picAddress1) {
		this.picAddress1 = picAddress1;
	}

	/**
	 * @return the picAddress2
	 */
	public String getPicAddress2() {
		return picAddress2;
	}

	/**
	 * @param picAddress2 the picAddress2 to set
	 */
	public void setPicAddress2(String picAddress2) {
		this.picAddress2 = picAddress2;
	}

	/**
	 * @return the picAddress3
	 */
	public String getPicAddress3() {
		return picAddress3;
	}

	/**
	 * @param picAddress3 the picAddress3 to set
	 */
	public void setPicAddress3(String picAddress3) {
		this.picAddress3 = picAddress3;
	}

	/**
	 * @return the picAddress4
	 */
	public String getPicAddress4() {
		return picAddress4;
	}

	/**
	 * @param picAddress4 the picAddress4 to set
	 */
	public void setPicAddress4(String picAddress4) {
		this.picAddress4 = picAddress4;
	}

	/**
	 * @return the picAddress5
	 */
	public String getPicAddress5() {
		return picAddress5;
	}

	/**
	 * @param picAddress5 the picAddress5 to set
	 */
	public void setPicAddress5(String picAddress5) {
		this.picAddress5 = picAddress5;
	}

	/**
	 * @return the picAddress6
	 */
	public String getPicAddress6() {
		return picAddress6;
	}

	/**
	 * @param picAddress6 the picAddress6 to set
	 */
	public void setPicAddress6(String picAddress6) {
		this.picAddress6 = picAddress6;
	}

	/**
	 * @return the picAddress7
	 */
	public String getPicAddress7() {
		return picAddress7;
	}

	/**
	 * @param picAddress7 the picAddress7 to set
	 */
	public void setPicAddress7(String picAddress7) {
		this.picAddress7 = picAddress7;
	}

	/**
	 * @return the picAddress8
	 */
	public String getPicAddress8() {
		return picAddress8;
	}

	/**
	 * @param picAddress8 the picAddress8 to set
	 */
	public void setPicAddress8(String picAddress8) {
		this.picAddress8 = picAddress8;
	}

	/**
	 * @return the picAddress9
	 */
	public String getPicAddress9() {
		return picAddress9;
	}

	/**
	 * @param picAddress9 the picAddress9 to set
	 */
	public void setPicAddress9(String picAddress9) {
		this.picAddress9 = picAddress9;
	}

	/**
	 * @return the picAddress10
	 */
	public String getPicAddress10() {
		return picAddress10;
	}

	/**
	 * @param picAddress10 the picAddress10 to set
	 */
	public void setPicAddress10(String picAddress10) {
		this.picAddress10 = picAddress10;
	}

	/**
	 * @return the picAddress11
	 */
	public String getPicAddress11() {
		return picAddress11;
	}

	/**
	 * @param picAddress11 the picAddress11 to set
	 */
	public void setPicAddress11(String picAddress11) {
		this.picAddress11 = picAddress11;
	}

	/**
	 * @return the picAddress12
	 */
	public String getPicAddress12() {
		return picAddress12;
	}

	/**
	 * @param picAddress12 the picAddress12 to set
	 */
	public void setPicAddress12(String picAddress12) {
		this.picAddress12 = picAddress12;
	}

	/**
	 * @return the picAddress13
	 */
	public String getPicAddress13() {
		return picAddress13;
	}

	/**
	 * @param picAddress13 the picAddress13 to set
	 */
	public void setPicAddress13(String picAddress13) {
		this.picAddress13 = picAddress13;
	}

	/**
	 * @return the picAddress15
	 */
	public String getPicAddress15() {
		return picAddress15;
	}

	/**
	 * @param picAddress15 the picAddress15 to set
	 */
	public void setPicAddress15(String picAddress15) {
		this.picAddress15 = picAddress15;
	}


    
    
    
    
    
}
