package com.center.po.groupmgt;

import com.center.po.query.QueryCondition;

public class LessonType extends QueryCondition{
	private Integer lessonTypeId;
	private String lessonTypeName;
	private String description;
	private Integer createBy;
	private String createTime;
	private String remark;
	private String createByName;
	@Override
	public String toString() {
		return "LessonType [lessonTypeId=" + lessonTypeId + ", lessonTypeName=" + lessonTypeName + ", description="
				+ description + ", createBy=" + createBy + ", createTime=" + createTime + ", remark=" + remark + "]";
	}
	public Integer getLessonTypeId() {
		return lessonTypeId;
	}
	public void setLessonTypeId(Integer lessonTypeId) {
		this.lessonTypeId = lessonTypeId;
	}
	public String getLessonTypeName() {
		return lessonTypeName;
	}
	public void setLessonTypeName(String lessonTypeName) {
		this.lessonTypeName = lessonTypeName;
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
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	
	
}
