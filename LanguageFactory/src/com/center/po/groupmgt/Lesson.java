package com.center.po.groupmgt;

import com.center.po.query.QueryCondition;

public class Lesson extends QueryCondition{

	private Integer lessonId;
	private String lessonName;
	private String description;
	private Integer createBy;
	private String createTime;
	private String remark;
	private Integer lessonTypeId;
	private String createByName;
	private String lessonTypeName;
	
	@Override
	public String toString() {
		return "Lesson [lessonId=" + lessonId + ", lessonName=" + lessonName + ", description=" + description
				+ ", createBy=" + createBy + ", createTime=" + createTime + ", remark=" + remark + ", lessonTypeId="
				+ lessonTypeId + "]";
	}
	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
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
	public Integer getLessonTypeId() {
		return lessonTypeId;
	}
	public void setLessonTypeId(Integer lessonTypeId) {
		this.lessonTypeId = lessonTypeId;
	}
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	public String getLessonTypeName() {
		return lessonTypeName;
	}
	public void setLessonTypeName(String lessonTypeName) {
		this.lessonTypeName = lessonTypeName;
	}
	
}
