package com.center.po.system;

import com.center.po.query.QueryCondition;

public class Role extends QueryCondition {
	private int roleId;
	private String roleName;
	private String createTime;
	private int isDefault;
	private int createBy;
	private String createByName;
	private String remark;
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleID) {
		this.roleId = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public int getIsDefault() {
	
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
	
		this.isDefault = isDefault;
	}

}
