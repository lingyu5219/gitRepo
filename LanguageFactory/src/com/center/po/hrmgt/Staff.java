
/**
* Project Name:trainingCenter
* File Name:Staff.java
* Package Name:com.center.po.personnel
* Date:2018年6月22日下午3:01:08
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.po.hrmgt;

import com.center.po.query.QueryCondition;

/**
* ClassName:Staff <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月22日 下午3:01:08 <br/>
* @author 李逢杰
* @version
* @see
*/
public class Staff extends QueryCondition{
	
	private int staffId;
	private String staffName;
	private String staffBirthday;
	private String staffPhone;
	private String staffEmail;
	private int staffGartenId;
    private String staffGartenName;
    private String staffOriginalPic;
    private String staffPic;
    private String staffPicName;
    private int staffPicSize;
    private int staffPicChanged;
	private int positionId;
	private String positionName;
	private String positionIds;
	private String createTime;
	private int createBy;
	private String createByName;
	private String remark;
	
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
	public String getStaffBirthday() {
	
		return staffBirthday;
	}
	public void setStaffBirthday(String staffBirthday) {
	
		this.staffBirthday = staffBirthday;
	}
	public String getStaffPhone() {
	
		return staffPhone;
	}
	public void setStaffPhone(String staffPhone) {
	
		this.staffPhone = staffPhone;
	}
	public String getStaffEmail() {
	
		return staffEmail;
	}
	public void setStaffEmail(String staffEmail) {
	
		this.staffEmail = staffEmail;
	}
	public int getStaffGartenId() {
	
		return staffGartenId;
	}
	public void setStaffGartenId(int staffGartenId) {
	
		this.staffGartenId = staffGartenId;
	}
	public String getStaffGartenName() {
	
		return staffGartenName;
	}
	public void setStaffGartenName(String staffGartenName) {
	
		this.staffGartenName = staffGartenName;
	}
	
	public String getStaffOriginalPic() {
	
		return staffOriginalPic;
	}
	public void setStaffOriginalPic(String staffOriginalPic) {
	
		this.staffOriginalPic = staffOriginalPic;
	}
	public String getStaffPic() {
	
		return staffPic;
	}
	public void setStaffPic(String staffPic) {
	
		this.staffPic = staffPic;
	}
	public String getStaffPicName() {
	
		return staffPicName;
	}
	public void setStaffPicName(String staffPicName) {
	
		this.staffPicName = staffPicName;
	}
	public int getStaffPicSize() {
	
		return staffPicSize;
	}
	public void setStaffPicSize(int staffPicSize) {
	
		this.staffPicSize = staffPicSize;
	}
	public int getStaffPicChanged() {
	
		return staffPicChanged;
	}
	public void setStaffPicChanged(int staffPicChanged) {
	
		this.staffPicChanged = staffPicChanged;
	}
	public int getPositionId() {
	
		return positionId;
	}
	public void setPositionId(int positionId) {
	
		this.positionId = positionId;
	}
	public String getPositionName() {
	
		return positionName;
	}
	public void setPositionName(String positionName) {
	
		this.positionName = positionName;
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
	public String getPositionIds() {
	
		return positionIds;
	}
	public void setPositionIds(String positionIds) {
	
		this.positionIds = positionIds;
	}
	
}

