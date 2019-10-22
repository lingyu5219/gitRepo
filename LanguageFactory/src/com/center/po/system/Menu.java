package com.center.po.system;

import java.util.List;

import com.center.po.query.QueryCondition;

public class Menu extends QueryCondition {
	private int menuId;
	private String menuName;
	private int moduleId;
	private String moduleName;
	private int isPublic;
	private int isFork;
	private int parentId;
	private String parentMenuName;
	private String location;
	private String createTime;
	private int createBy;
	private String userName;
	private String remark;
	private List<Menu> subMenuList;
	
	public int getMenuId() {
	
		return menuId;
	}
	public void setMenuId(int menuId) {
	
		this.menuId = menuId;
	}
	public String getMenuName() {
	
		return menuName;
	}
	public void setMenuName(String menuName) {
	
		this.menuName = menuName;
	}
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
	public int getIsPublic() {
	
		return isPublic;
	}
	public void setIsPublic(int isPublic) {
	
		this.isPublic = isPublic;
	}
	public int getIsFork() {
	
		return isFork;
	}
	public void setIsFork(int isFork) {
	
		this.isFork = isFork;
	}
	public int getParentId() {
	
		return parentId;
	}
	public void setParentId(int parentId) {
	
		this.parentId = parentId;
	}
	public String getParentMenuName() {
	
		return parentMenuName;
	}
	public void setParentMenuName(String parentMenuName) {
	
		this.parentMenuName = parentMenuName;
	}
	public String getLocation() {
	
		return location;
	}
	public void setLocation(String location) {
	
		this.location = location;
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
	public List<Menu> getSubMenuList() {
	
		return subMenuList;
	}
	public void setSubMenuList(List<Menu> subMenuList) {
	
		this.subMenuList = subMenuList;
	}
	
}
