package com.center.po.system;

import com.center.po.query.QueryCondition;

public class User extends QueryCondition {
	private int userId;
	private int roleId;
	private String roleName;
	private String userName;
	private String userPassword;
	private String newPassword;
	private String picPath;
	private int staffId;
	private String staffName;
	private String createTime;
	private int createBy;
	private String createByName;
	private String remark;
	private String verifyCode;
	
	
	public int getStaffId() {
		
		return staffId;
	}
	public void setStaffId(int staffId) {
	
		this.staffId = staffId;
	}
	public String getStaffName() {
	
		return staffName;
	}
	public void setStaffName(String staffName) {
	
		this.staffName = staffName;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getNewPassword() {
	
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
	
		this.newPassword = newPassword;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getRoleName() {
	
		return roleName;
	}
	public void setRoleName(String roleName) {
	
		this.roleName = roleName;
	}
	public String getCreateByName() {
	
		return createByName;
	}
	public void setCreateByName(String createByName) {
	
		this.createByName = createByName;
	}
	public String getVerifyCode() {
	
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
	
		this.verifyCode = verifyCode;
	}

}
