package com.center.po.system;


import java.util.List;

import com.center.po.query.QueryCondition;

public class Module extends QueryCondition{
	private int moduleId;
	private String moduleName;
	private String moduleIcon;
	private int createBy;
	private String createTime; 
	private String remark;
	private String userName;
	private List<Menu> subMenuList;
	
	public int getModuleId() {
	
		return moduleId;
	}
	public void setModuleId(int moduleId) {
	
		this.moduleId = moduleId;
	}
	public String getModuleName() {
	
		return moduleName;
	}
	public void setModuleName(String moduleName) {
	
		this.moduleName = moduleName;
	}
	
	public String getModuleIcon() {
	
		return moduleIcon;
	}
	public void setModuleIcon(String moduleIcon) {
	
		this.moduleIcon = moduleIcon;
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
	public List<Menu> getSubMenuList() {
	
		return subMenuList;
	}
	public void setSubMenuList(List<Menu> subMenuList) {
	
		this.subMenuList = subMenuList;
	}
	public String getUserName() {
	
		return userName;
	}
	public void setUserName(String userName) {
	
		this.userName = userName;
	}
	public String getRemark() {
	
		return remark;
	}
	public void setRemark(String remark) {
	
		this.remark = remark;
	}
	
}
