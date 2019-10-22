package com.center.po.groupmgt;

import com.center.po.query.QueryCondition;

public class EduProject extends QueryCondition{

	private Integer eduProjectId;
	private String eduProjectName;
	private String description;
	private Integer createBy;
	private String createByName;
	private String createTime;
	private String remark;
	private Integer gartenId;
	private String gartenName;
	@Override
	public String toString() {
		return "EduProject [eduProjectId=" + eduProjectId + ", eduProjectName=" + eduProjectName + ", description="
				+ description + ", createBy=" + createBy + ", createByName=" + createByName + ", createTime="
				+ createTime + ", remark=" + remark + ", gartenId=" + gartenId + ", gartenName=" + gartenName + "]";
	}
	public Integer getEduProjectId() {
		return eduProjectId;
	}
	public void setEduProjectId(Integer eduProjectId) {
		this.eduProjectId = eduProjectId;
	}
	public String getEduProjectName() {
		return eduProjectName;
	}
	public void setEduProjectName(String eduProjectName) {
		this.eduProjectName = eduProjectName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
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
	public Integer getGartenId() {
		return gartenId;
	}
	public void setGartenId(Integer gartenId) {
		this.gartenId = gartenId;
	}
	public String getGartenName() {
		return gartenName;
	}
	public void setGartenName(String gartenName) {
		this.gartenName = gartenName;
	}
	
}
