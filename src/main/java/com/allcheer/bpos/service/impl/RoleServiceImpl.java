package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.domain.RoleBO;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.mapper.TblBTSSysFunctionDOMapper;
import com.allcheer.bpos.mapper.TblBTSSysRoleDOMapper;
import com.allcheer.bpos.mapper.TblBTSSysRoleFuncDOMapper;
import com.allcheer.bpos.mapper.TblBTSSysUsrRoleDOMapper;
import com.allcheer.bpos.service.RoleService;
import com.allcheer.bpos.util.Pagination;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fireWorks on 2016/2/2.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    TblBTSSysRoleDOMapper roleDOMapper;

    @Autowired
    TblBTSSysUsrRoleDOMapper usrRoleDOMapper;

    @Autowired
    TblBTSSysFunctionDOMapper functionDOMapper;

    @Autowired
    TblBTSSysRoleFuncDOMapper roleFuncDOMapper;

    @Override
    public Pagination<RoleBO> getRoleList(String uid){
        List<RoleBO> roleList = new ArrayList<>();

        TblBTSSysRoleDOExample tblBTSSysRoleDOExample = new TblBTSSysRoleDOExample();
        tblBTSSysRoleDOExample.createCriteria().andRoleDisableTagEqualTo("1");

        int count = roleDOMapper.countByExample(tblBTSSysRoleDOExample);
        Pagination pagination = new Pagination(count, 1, 100);

        List<TblBTSSysRoleDO> roleDOList = roleDOMapper.selectByExample(tblBTSSysRoleDOExample);

        List<RoleBO> roleBOList = new ArrayList<>();
        for(TblBTSSysRoleDO roleDO: roleDOList) {
            RoleBO roleBO = new RoleBO();
            roleBO.setRoleId(roleDO.getRoleId());
            roleBO.setRoleName(roleDO.getRoleName());
            roleBO.setRoleRemark(roleDO.getRoleRemark());
            roleBO.setRoleDisableTag(roleDO.getRoleDisableTag());
            TblBTSSysUsrRoleDOKey usrRoleDOKey = new TblBTSSysUsrRoleDOKey();
            usrRoleDOKey.setUsrId(uid);
            usrRoleDOKey.setRoleId(roleBO.getRoleId());
            TblBTSSysUsrRoleDO usrRoleDO = usrRoleDOMapper.selectByPrimaryKey(usrRoleDOKey);
            if(usrRoleDO == null){
                roleBO.setChecked("未授权");
            }else{
                roleBO.setChecked("已授权");
            }
            roleBOList.add(roleBO);
        }
        pagination.addResult(roleBOList);

        return pagination;

    }


    @Override
    public Map addRoleResouce(String sid,String rid) throws Exception{
        Map resultMap = new HashMap();

        TblBTSSysFunctionDOExample functionDOExample = new TblBTSSysFunctionDOExample();
        TblBTSSysFunctionDO functionDO = functionDOMapper.selectByPrimaryKey(sid);

        TblBTSSysRoleFuncDO roleFuncDO = new TblBTSSysRoleFuncDO();
        roleFuncDO.setRoleId(rid);
        roleFuncDO.setFuncId(sid);
        roleFuncDO.setRoleFuncRemark(functionDO.getFuncRemark());
        roleFuncDOMapper.insert(roleFuncDO);
        resultMap.put("statusCode", 200);
        resultMap.put("message", "操作成功!");

        return resultMap;
    }

    @Override
    public Map cancelRoleResouce(String sid,String rid) throws Exception{

        Map resultMap = new HashMap();
        TblBTSSysRoleFuncDOKey roleFuncDOKey = new TblBTSSysRoleFuncDOKey();
        roleFuncDOKey.setFuncId(sid);
        roleFuncDOKey.setRoleId(rid);
        roleFuncDOMapper.deleteByPrimaryKey(roleFuncDOKey);
        resultMap.put("statusCode", 200);
        resultMap.put("message", "操作成功!");

        return resultMap;
    }

    @Override
    public Map setRoleEnable(String roleId) throws Exception{
        Map resultMap = new HashMap();
        TblBTSSysRoleDOExample roleDOExample = new TblBTSSysRoleDOExample();
        roleDOExample.createCriteria().andRoleIdEqualTo(roleId);
        TblBTSSysRoleDO roleDO = new TblBTSSysRoleDO();
        roleDO.setRoleDisableTag("1");
        roleDOMapper.updateByExampleSelective(roleDO,roleDOExample);
        resultMap.put("statusCode", 200);
        resultMap.put("message", "操作成功!");

        return resultMap;

    }

    @Override
    public Map setRoleDisable(String roleId) throws Exception{
        Map resultMap = new HashMap();
        TblBTSSysRoleDOExample roleDOExample = new TblBTSSysRoleDOExample();
        roleDOExample.createCriteria().andRoleIdEqualTo(roleId);
        TblBTSSysRoleDO roleDO = new TblBTSSysRoleDO();
        roleDO.setRoleDisableTag("0");
        roleDOMapper.updateByExampleSelective(roleDO,roleDOExample);
        resultMap.put("statusCode", 200);
        resultMap.put("message", "操作成功!");

        return resultMap;

    }

    @Override
    public Pagination<RoleBO> getTheRole(RoleBO roleBO){

        TblBTSSysRoleDOExample tblBTSSysRoleDOExample = new TblBTSSysRoleDOExample();
        TblBTSSysRoleDOExample.Criteria cri = tblBTSSysRoleDOExample.createCriteria();
        if ( roleBO.getRoleName() != null && !roleBO.getRoleName().equals("") ) {
            //cri.andRoleNameEqualTo( roleBO.getRoleName() );
            cri.andRoleNameLike("%"+roleBO.getRoleName()+"%");
        }
        if(roleBO.getRoleRemark()!=null && !roleBO.getRoleRemark().equals("") ){
            //cri.andRoleRemarkEqualTo( roleBO.getRoleRemark() );
            cri.andRoleRemarkLike("%"+roleBO.getRoleRemark()+"%");
        }
        if(roleBO.getRoleDisableTag()!=null && !roleBO.getRoleDisableTag().equals("")){
            cri.andRoleDisableTagEqualTo(roleBO.getRoleDisableTag());
        }
        int count = roleDOMapper.countByExample(tblBTSSysRoleDOExample);
        Pagination pagination = new Pagination(count, roleBO.getPageCurrent(), roleBO.getPageSize());
        PageHelper.startPage(roleBO.getPageCurrent(), roleBO.getPageSize());
        List<TblBTSSysRoleDO> roleDOList = roleDOMapper.selectByExample(tblBTSSysRoleDOExample);

        List<RoleBO> roleBOList = new ArrayList<>();
        for(TblBTSSysRoleDO roleDO: roleDOList) {
            RoleBO resultRoleBO = new RoleBO();
            resultRoleBO.setRoleId(roleDO.getRoleId());
            resultRoleBO.setRoleName(roleDO.getRoleName());
            //resultRoleBO.setRoleDisableTag(roleDO.getRoleDisableTag());
            if(roleDO.getRoleDisableTag().equals("1")){
                resultRoleBO.setRoleDisableTag("启用");
            }else{
                resultRoleBO.setRoleDisableTag("禁用");
            }
            resultRoleBO.setRoleRemark(roleDO.getRoleRemark());
            roleBOList.add(resultRoleBO);
        }
        pagination.addResult(roleBOList);

        return pagination;

    }

    @Override
    public Pagination<RoleBO> getAllRole(RoleBO roleBO){
        List<RoleBO> userList = new ArrayList<>();

        TblBTSSysRoleDOExample tblBTSSysRoleDOExample = new TblBTSSysRoleDOExample();
        TblBTSSysRoleDOExample.Criteria cri = tblBTSSysRoleDOExample.createCriteria();
        int count = roleDOMapper.countByExample(tblBTSSysRoleDOExample);
        Pagination pagination = new Pagination(count, roleBO.getPageCurrent(), roleBO.getPageSize());
        PageHelper.startPage(roleBO.getPageCurrent(), roleBO.getPageSize());
        List<TblBTSSysRoleDO> roleDOList = roleDOMapper.selectByExample(tblBTSSysRoleDOExample);

        List<RoleBO> roleBOList = new ArrayList<>();
        for(TblBTSSysRoleDO roleDO: roleDOList) {
            RoleBO roleBO1 = new RoleBO();
            roleBO1.setRoleId(roleDO.getRoleId());
            roleBO1.setRoleName(roleDO.getRoleName());
            if(roleDO.getRoleDisableTag().trim().equals("1")){
                roleBO1.setRoleDisableTag("启用");
            }else{
                roleBO1.setRoleDisableTag("禁用");
            }
            roleBO1.setRoleRemark(roleDO.getRoleRemark());
            roleBOList.add(roleBO1);
        }
        pagination.addResult(roleBOList);

        return pagination;

    }

    @Override
    public Map addNewRole(RoleBO roleBO){
        Map resultMap = new HashMap();

        TblBTSSysRoleDO refSysRoleDO = new TblBTSSysRoleDO();
        refSysRoleDO.setRoleName(roleBO.getRoleName());
        refSysRoleDO.setRoleRemark(roleBO.getRoleRemark());
        refSysRoleDO.setRoleId(roleBO.getRoleId());
        refSysRoleDO.setRoleDisableTag(roleBO.getRoleDisableTag());
        int rt = roleDOMapper.insert(refSysRoleDO);
        if (rt != 0){
            resultMap.put("statusCode", 200);
            resultMap.put("message", "操作成功!");
        }else{
            resultMap.put("statusCode", 200);
            resultMap.put("message", "操作失败!");
        }
        return resultMap;

    }


    @Override
    public RoleBO getById(String roleId) {
        TblBTSSysRoleDOExample tblBTSSysRoleDOExample = new TblBTSSysRoleDOExample();
        tblBTSSysRoleDOExample.createCriteria().andRoleIdEqualTo(roleId);
        List<TblBTSSysRoleDO> tblBTSSysRoleDOList = roleDOMapper.selectByExample(tblBTSSysRoleDOExample);
        return convertRoleDOToRoleBO(tblBTSSysRoleDOList.get(0));
    }

    private RoleBO convertRoleDOToRoleBO(TblBTSSysRoleDO tblBTSSysRoleDO) {
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleId(tblBTSSysRoleDO.getRoleId());
        roleBO.setRoleName(tblBTSSysRoleDO.getRoleName());
        roleBO.setRoleRemark(tblBTSSysRoleDO.getRoleRemark());
        roleBO.setRoleDisableTag(tblBTSSysRoleDO.getRoleDisableTag());
        return roleBO;
    }

}
