package com.center.po.groupmgt;

import com.center.po.query.QueryCondition;

/**
 * 班级类型
 *
 */
public class ClassType extends QueryCondition{

	private Integer classTypeId;
	private String classTypeName;
	private String description;
	private Integer createBy;
	private String createTime;
	private String remark;
	private String createByName;
	@Override
	public String toString() {
		return "ClassType [classtypeId=" + classTypeId + ", classtypeName=" + classTypeName + ", description="
				+ description + ", createBy=" + createBy + ", createTime=" + createTime + ", remark=" + remark + "]";
	}
	public Integer getClasstypeId() {
		return classTypeId;
	}
	public void setClasstypeId(Integer classtypeId) {
		this.classTypeId = classtypeId;
	}
	public String getClasstypeName() {
		return classTypeName;
	}
	public void setClasstypeName(String classtypeName) {
		this.classTypeName = classtypeName;
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
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public String getClassTypeName() {
		return classTypeName;
	}
	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	
	
}
