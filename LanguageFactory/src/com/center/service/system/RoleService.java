package com.center.service.system;

import java.util.List;

import com.center.po.query.DatatablesView;
import com.center.po.system.Author;
import com.center.po.system.Menu;
import com.center.po.system.MenuQuery;
import com.center.po.system.Role;
import com.center.po.system.RoleQuery;

public interface RoleService {
	public List<Role> queryRole(RoleQuery roleQuery) throws Exception;
	
	public DatatablesView<Role> queryRoleList(RoleQuery roleQuery) throws Exception;
	 
	public  boolean addRole(Role role) throws Exception;
	 
	public  boolean deleteRole(int roleId) throws Exception;
	
	public  boolean updateRole(Role role) throws Exception;
	 
}
