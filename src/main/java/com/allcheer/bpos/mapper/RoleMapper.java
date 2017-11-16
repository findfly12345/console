package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.dao.RoleDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface RoleMapper {
	
	public int addRole(RoleDo role);
	
	public int updateRole(RoleDo role);
	
	public RoleDo findRoleByName(String name);
	
	/**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public int correlationPermissions(@Param("roleId") Long roleId,@Param("permissionId") Long permissionId);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public int uncorrelationPermissions(@Param("roleId") Long roleId,@Param("permissionId") Long permissionId);

    /**
	 * 查找角色的所有权限
	 * 
	 * @param name
	 * @return
	 */
	public Set<String> findPermissions(String name);
}
