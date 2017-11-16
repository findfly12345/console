package com.allcheer.bpos.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by fireWorks on 2016/2/26.
 */
public class UserBO extends BaseBO implements Serializable{
	private static final long serialVersionUID = -5368343038685225231L;

	private String usrId;

    private String usrName;

    private String usrPwd;

    private String pass;

    private String usrRemark;

    private String usrType;

    private String usrDisableTag;

    private String usrEmail;

    private String usrCreateBy;

    private Date usrCreateDate;

    private String usrUpdateBy;

    private Date usrUpdateDate;
    
    private String merNumber;
    
    private String instId;
    
    private String agentId;
    
    private String merId;

    private Boolean bindMer;
    
    private List<RoleBO> roleBOList;

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsrRemark() {
        return usrRemark;
    }

    public void setUsrRemark(String usrRemark) {
        this.usrRemark = usrRemark;
    }

    public String getUsrType() {
        return usrType;
    }

    public void setUsrType(String usrType) {
        this.usrType = usrType;
    }

    public String getUsrDisableTag() {
        return usrDisableTag;
    }

    public void setUsrDisableTag(String usrDisableTag) {
        this.usrDisableTag = usrDisableTag;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrCreateBy() {
        return usrCreateBy;
    }

    public void setUsrCreateBy(String usrCreateBy) {
        this.usrCreateBy = usrCreateBy;
    }

    public Date getUsrCreateDate() {
        return usrCreateDate;
    }

    public void setUsrCreateDate(Date usrCreateDate) {
        this.usrCreateDate = usrCreateDate;
    }

    public String getUsrUpdateBy() {
        return usrUpdateBy;
    }

    public void setUsrUpdateBy(String usrUpdateBy) {
        this.usrUpdateBy = usrUpdateBy;
    }

    public Date getUsrUpdateDate() {
        return usrUpdateDate;
    }

    public void setUsrUpdateDate(Date usrUpdateDate) {
        this.usrUpdateDate = usrUpdateDate;
    }

    public List<RoleBO> getRoleBOList() {
        return roleBOList;
    }

    public void setRoleBOList(List<RoleBO> roleBOList) {
        this.roleBOList = roleBOList;
    }

	/**
	 * @return the merNumber
	 */
	public String getMerNumber() {
		return merNumber;
	}

	/**
	 * @param merNumber the merNumber to set
	 */
	public void setMerNumber(String merNumber) {
		this.merNumber = merNumber;
	}

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
	 * @return the agentId
	 */
	public String getAgentId() {
		return agentId;
	}

	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(String agentId) {
		this.agentId = agentId;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the bindMer
	 */
	public Boolean getBindMer() {
		return bindMer;
	}

	/**
	 * @param bindMer the bindMer to set
	 */
	public void setBindMer(Boolean bindMer) {
		this.bindMer = bindMer;
	}

}
