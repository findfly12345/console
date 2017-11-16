package com.allcheer.bpos.service;


import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.form.UserManageForm;
import com.allcheer.bpos.util.Pagination;

import java.util.Map;

/**
 * Created by APPLE on 15/12/27.
 */
public interface UserService {
    UserBO get(String username);

    UserBO getById(String username);

    Map updatePwd(UserBO userBO);

    Map addNewUsr(UserBO userBO);

    Map setAcctEnable(String usrId) throws Exception;

    Map setAcctDisable(String usrId) throws Exception;

    Pagination<UserBO> getAllUsr(UserBO userBO);

    Pagination<UserBO> getTheUsr(UserBO userBO);

    Map cancelAcctAuthority(String id,String uid) throws Exception;

    Map addAcctAuthority(String id,String uid);

	String getRoleId(String roleName);

    /**
     * 检查关联商户是否已经绑定过
     * @param userId
     * @param  merType: 1
     * @return
     */
	public String checkIfMerIsAlreadyBinded(String merNumber, String merCatatory);

    /**
     * 更新绑定信息
     * @param userManageForm
     * @return
     */
	public Boolean goBindMer(UserManageForm userManageForm);

}
