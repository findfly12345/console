package com.allcheer.bpos.form;

/**
 * Created by fireWorks on 2016/2/1.
 */
public class UserManageForm extends BaseForm {
    private String usrId;

    private String usrName;

    private String passRe;

    private String pass;

    private String usrRemark;

    private String usrDisableTag;

    private String usrEmail;
    
    private String merNumber;
    
    private String instId; 
    
    private String agentId;
    
    private String merId;
    
    private String bindMer;
    
    private String bindUser;

    public String getUsrid() {
        return usrId;
    }

    public void setUsrid(String usrId) {
        this.usrId = usrId;
    }

    public String getUsername() {
        return usrName;
    }

    public void setUsername(String usrName) {
        this.usrName = usrName;
    }

    public String getPassRe() {
        return passRe;
    }

    public void setPassRe(String passRe) {
        this.passRe = passRe;
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
        this.usrRemark = usrRemark == null ? null : usrRemark.trim();
    }

    public String getUsrDisableTag() {
        return usrDisableTag;
    }

    public void setUsrDisableTag(String usrDisableTag) {
        this.usrDisableTag = usrDisableTag == null ? null : usrDisableTag.trim();
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail == null ? null : usrEmail.trim();
    }

	/**
	 * @return the usrId
	 */
	public String getUsrId() {
		return usrId;
	}

	/**
	 * @param usrId the usrId to set
	 */
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	/**
	 * @return the usrName
	 */
	public String getUsrName() {
		return usrName;
	}

	/**
	 * @param usrName the usrName to set
	 */
	public void setUsrName(String usrName) {
		this.usrName = usrName;
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
	 * @return the bindMer
	 */
	public String getBindMer() {
		return bindMer;
	}

	/**
	 * @param bindMer the bindMer to set
	 */
	public void setBindMer(String bindMer) {
		this.bindMer = bindMer;
	}

	/**
	 * @return the bindUser
	 */
	public String getBindUser() {
		return bindUser;
	}

	/**
	 * @param bindUser the bindUser to set
	 */
	public void setBindUser(String bindUser) {
		this.bindUser = bindUser;
	}


}
