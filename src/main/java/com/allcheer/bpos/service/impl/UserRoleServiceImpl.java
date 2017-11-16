package com.allcheer.bpos.service.impl;


import com.allcheer.bpos.domain.RoleBO;
import com.allcheer.bpos.entity.TblBTSSysRoleDO;
import com.allcheer.bpos.entity.TblBTSSysUsrRoleDO;
import com.allcheer.bpos.entity.TblBTSSysUsrRoleDOExample;
import com.allcheer.bpos.mapper.TblBTSSysRoleDOMapper;
import com.allcheer.bpos.mapper.TblBTSSysUsrRoleDOMapper;
import com.allcheer.bpos.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by APPLE on 16/1/13.
 */
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    TblBTSSysUsrRoleDOMapper tblBTSSysUsrRoleDOMapper;

    @Autowired
    TblBTSSysRoleDOMapper tblBTSSysRoleDOMapper;

    @Override
    public List<RoleBO> getRolesByUserId(String userId) {
        TblBTSSysUsrRoleDOExample tblBTSSysUsrRoleDOExample = new TblBTSSysUsrRoleDOExample();
        tblBTSSysUsrRoleDOExample.createCriteria().andUsrIdEqualTo(userId);
        List<TblBTSSysUsrRoleDO> tblBTSSysUsrRoleDOList = tblBTSSysUsrRoleDOMapper.selectByExample(tblBTSSysUsrRoleDOExample);
        List<RoleBO> roleBOList = new ArrayList<>();
        for(TblBTSSysUsrRoleDO tblBTSSysUsrRoleDO:tblBTSSysUsrRoleDOList) {
            TblBTSSysRoleDO tblBTSSysRoleDO = tblBTSSysRoleDOMapper.selectByPrimaryKey(tblBTSSysUsrRoleDO.getRoleId());

        }
        return null;
    }
}
