package com.center.mapper.system;

import java.util.List;

import com.center.po.system.Author;
import com.center.po.system.Role;
import com.center.po.system.RoleQuery;

public interface RoleMapper {
	public List<Role> queryRole(RoleQuery roleQuery) throws Exception;
	
	public List<Role> queryRoleList(RoleQuery roleQuery) throws Exception;
 
	public long queryRoleCount(RoleQuery roleQuery) throws Exception;
 
	public int addRole(Role role) throws Exception;
 
	public int deleteRole(int roleId) throws Exception;
 
	public int deleteRole2(int roleId) throws Exception;
 
	public int updateRole(Role role) throws Exception;
 
	public String queryRoleNameByRoldId(int roleId);
}
