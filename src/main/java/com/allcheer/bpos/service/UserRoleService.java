package com.allcheer.bpos.service;



import com.allcheer.bpos.domain.RoleBO;

import java.util.List;

/**
 * Created by APPLE on 16/1/13.
 */
public interface UserRoleService {
    List<RoleBO> getRolesByUserId(String userId);
}
