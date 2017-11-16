package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.dao.UserDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserMapper {

	public int addUser(UserDo user);

	public int updateUser(UserDo user);

	public UserDo findUserByName(String name);
	
	public List<UserDo> findUser(UserDo user);

	/**
	 * 加角色
	 * 
	 * @param userId
	 * @param roleId
	 */
	public int correlationRoles(@Param("userId") long userId,@Param("roleId") long roleId);

	/**
	 * 删除角色
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public int nuCorrelationRoles(@Param("userId") long userId,@Param("roleId") long roleId);

	/**
	 * 查找用户的所有角色
	 * 
	 * @param name
	 * @return
	 */
	public Set<String> findRoles(String name);

	/**
	 * 查找用户的所有权限
	 * 
	 * @param name
	 * @return
	 */
	public Set<String> findPermissions(String name);

	public int findUserCount(UserDo userDo);
}
