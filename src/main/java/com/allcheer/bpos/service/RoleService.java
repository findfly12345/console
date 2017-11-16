package com.allcheer.bpos.service;


import com.allcheer.bpos.domain.RoleBO;
import com.allcheer.bpos.util.Pagination;

import java.util.Map;

/**
 * Created by fireWorks on 2016/2/2.
 */
public interface RoleService {

    public Pagination<RoleBO> getRoleList(String uid);

    RoleBO getById(String roleId);

    Map addNewRole(RoleBO roleBO);

    Pagination<RoleBO> getAllRole(RoleBO roleBO);

    Pagination<RoleBO> getTheRole(RoleBO roleBO);

    Map setRoleDisable(String roleId) throws Exception;

    Map setRoleEnable(String roleId) throws Exception;

    Map cancelRoleResouce(String sid,String rid) throws Exception;

    Map addRoleResouce(String sid,String rid) throws Exception;

}
