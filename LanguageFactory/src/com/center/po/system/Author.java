package com.center.po.system;

import com.center.po.query.QueryCondition;

public class Author extends QueryCondition {
	private int authorId;
	private int roleId;
	private int menuId;
	private String menuIds;
	private String roleName;
	private String menuName;
	private String menuNames;
	private String createTime;
	private int createBy;
	private String createByName;
	private String remark;
	public int getAuthorId() {
	
		return authorId;
	}
	public void setAuthorId(int authorId) {
	
		this.authorId = authorId;
	}
	public int getRoleId() {
	
		return roleId;
	}
	public void setRoleId(int roleId) {
	
		this.roleId = roleId;
	}
	public int getMenuId() {
	
		return menuId;
	}
	public void setMenuId(int menuId) {
	
		this.menuId = menuId;
	}
	public String getRoleName() {
	
		return roleName;
	}
	public void setRoleName(String roleName) {
	
		this.roleName = roleName;
	}
	public String getMenuName() {
	
		return menuName;
	}
	public void setMenuName(String menuName) {
	
		this.menuName = menuName;
	}
	public String getCreateTime() {
	
		return createTime;
	}
	public void setCreateTime(String createTime) {
	
		this.createTime = createTime;
	}
	public int getCreateBy() {
	
		return createBy;
	}
	public void setCreateBy(int createBy) {
	
		this.createBy = createBy;
	}
	public String getCreateByName() {
	
		return createByName;
	}
	public void setCreateByName(String createByName) {
	
		this.createByName = createByName;
	}
	public String getRemark() {
	
		return remark;
	}
	public void setRemark(String remark) {
	
		this.remark = remark;
	}
	public String getMenuIds() {
	
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
	
		this.menuIds = menuIds;
	}
	public String getMenuNames() {
	
		return menuNames;
	}
	public void setMenuNames(String menuNames) {
	
		this.menuNames = menuNames;
	}

}
