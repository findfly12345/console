package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.domain.FuncBO;
import com.allcheer.bpos.domain.RoleBO;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.service.UserAuthorService;
import com.allcheer.bpos.entity.TblBTSSysFunctionVO;
import com.allcheer.bpos.entity.TblBTSSysRoleVO;
import com.allcheer.bpos.entity.TblBTSSysUsrVO;
import com.allcheer.bpos.mapper.UserAuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by APPLE on 16/1/13.
 */
@Service("userAuthorService")
public class UserAuthorServiceImpl implements UserAuthorService {

    @Autowired
    UserAuthorMapper userAuthorMapper;

    @Override
    public UserBO getAuthorByUserId(String userId) {
        TblBTSSysUsrVO tblBTSSysUsrVO = userAuthorMapper.getAuthorByUserId(userId);
        UserBO userBO = new UserBO();
        userBO.setUsrId(tblBTSSysUsrVO.getUsrId());
        List<TblBTSSysRoleVO> tblBTSSysRoleVOList = tblBTSSysUsrVO.getTblBTSSysRoleVOList();
        List<RoleBO> roleBOList = new ArrayList<>();
        for(TblBTSSysRoleVO tblBTSSysRoleVO:tblBTSSysRoleVOList) {
            RoleBO roleBO = new RoleBO();
            roleBO.setRoleId(tblBTSSysRoleVO.getRoleId());
            roleBO.setRoleName(tblBTSSysRoleVO.getRoleName());
            List<TblBTSSysFunctionVO> tblBTSSysFunctionVOList = tblBTSSysRoleVO.getTblBTSSysFunctionVOList();
            List<FuncBO> funcBOList = new ArrayList<>();
            for(TblBTSSysFunctionVO tblBTSSysFunctionVO:tblBTSSysFunctionVOList) {
                FuncBO funcBO = new FuncBO();
                funcBO.setFuncId(tblBTSSysFunctionVO.getFuncId());
                funcBO.setFuncUrl(tblBTSSysFunctionVO.getFuncUrl());
                funcBOList.add(funcBO);
            }
            roleBO.setFuncBOList(funcBOList);
            roleBOList.add(roleBO);
        }
        userBO.setRoleBOList(roleBOList);
        return userBO;
    }
}
