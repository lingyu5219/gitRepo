package com.center.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.system.RoleMapper;
import com.center.po.query.DatatablesView;
import com.center.po.system.Role;
import com.center.po.system.RoleQuery;
import com.center.service.system.RoleService;
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleMapper roleMapper;
	
	private boolean Judge(int num){
		if(num>0)
			return true;
		else
			return false;
	}
	
	@Override
	public List<Role> queryRole(RoleQuery roleQuery) throws Exception {
		List<Role> roleList = roleMapper.queryRole(roleQuery);
		return roleList;
	}
	 
	@Override
	public DatatablesView<Role> queryRoleList(RoleQuery roleQuery) throws Exception {
		DatatablesView<Role> dataView = new DatatablesView<Role>();
		List<Role> roleList = roleMapper.queryRoleList(roleQuery);
		long Count = roleMapper.queryRoleCount(roleQuery);
		dataView.setRecordsTotal(Count);
		dataView.setData(roleList);
		return dataView;
	}

	@Override
	public boolean addRole(Role role) throws Exception {
		return Judge(roleMapper.addRole(role));
	}

	@Override
	public boolean  deleteRole(int roleId) throws Exception {
		int temp1=roleMapper.deleteRole(roleId);
		//int temp2=roleMapper.deleteRole2(roleId);
		return Judge(temp1);
	}

	@Override
	public boolean updateRole(Role role) throws Exception {
		return Judge(roleMapper.updateRole(role));
	}

}
