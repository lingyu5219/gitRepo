package com.center.po.groupmgt;

import com.center.po.query.QueryCondition;

public class Class extends QueryCondition{

	private Integer classId;
	private String className;
	private String year;
	private String description;
	private Integer createBy;
	private String createTime;
	private String remark;
	private Integer classTypeId;
	private Integer gartenId;
	private Integer capacity;
	private Integer currentQuantity; 
	private String createByName;
	private String classTypeName;
	private String gartenName;
	@Override
	public String toString() {
		return "Class [classId=" + classId + ", className=" + className + ", year=" + year + ", description="
				+ description + ", createBy=" + createBy + ", createTime=" + createTime + ", remark=" + remark
				+ ", classTypeId=" + classTypeId + ", gartenId=" + gartenId + "]";
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	public Integer getGartenId() {
		return gartenId;
	}
	public void setGartenId(Integer gartenId) {
		this.gartenId = gartenId;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public String getCreateByName() {
		return createByName;
	}
	public Integer getCurrentQuantity() {
		return currentQuantity;
	}
	public void setCurrentQuantity(Integer currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	public String getClassTypeName() {
		return classTypeName;
	}
	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}
	public String getGartenName() {
		return gartenName;
	}
	public void setGartenName(String gartenName) {
		this.gartenName = gartenName;
	}
	
}
