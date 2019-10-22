
/**
* Project Name:kindergarten
* File Name:Column.java
* Package Name:com.center.po.cms
* Date:2018年6月2日下午4:44:29
* Copyright (c) 2018, Tony All Rights Reserved.
*
*/

/**
* Project Name:kindergarten
* File Name:Column.java
* Package Name:com.center.po.cms
* Date:2018年6月2日下午4:44:29
* Copyright (c) 2018, Tony All Rights Reserved.
*
*/


package com.center.po.cms;
/**
* ClassName:Column <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月2日 下午4:44:29 <br/>
* @author Tony
* @version
* @see
*/

import java.util.List;

import com.center.po.query.QueryCondition;

/**
* ClassName: Column <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月2日 下午4:44:29 <br/>
*
* @author Tony
* @version 
*/

public class Column extends QueryCondition {
	private int columnId;
	private String columnName;
	private int columnParentId;
	private String columnUrl;
	private int columnSort;
	private int columnSortMin;
	private int columnSortMax;
	private int columnChildNum;
	private int columnEnable;
	private int createBy;
	private String createByName;
	private String createTime;
	private String remark;
	private List<Column> childColumnList;
	
	public int getColumnChildNum() {
		
		return columnChildNum;
	}
	public void setColumnChildNum(int columnChildNum) {
	
		this.columnChildNum = columnChildNum;
	}
	public List<Column> getChildColumnList() {
	
		return childColumnList;
	}
	public void setChildColumnList(List<Column> childColumnList) {
	
		this.childColumnList = childColumnList;
	}
	public int getColumnId() {
	
		return columnId;
	}
	public void setColumnId(int columnId) {
	
		this.columnId = columnId;
	}
	public String getColumnName() {
	
		return columnName;
	}
	public void setColumnName(String columnName) {
	
		this.columnName = columnName;
	}
	public int getColumnParentId() {
	
		return columnParentId;
	}
	public void setColumnParentId(int columnParentId) {
	
		this.columnParentId = columnParentId;
	}
	public String getColumnUrl() {
	
		return columnUrl;
	}
	public void setColumnUrl(String columnUrl) {
	
		this.columnUrl = columnUrl;
	}
	public int getColumnSort() {
	
		return columnSort;
	}
	public void setColumnSort(int columnSort) {
	
		this.columnSort = columnSort;
	}
	public int getColumnEnable() {
	
		return columnEnable;
	}
	public void setColumnEnable(int columnEnable) {
	
		this.columnEnable = columnEnable;
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
	public int getColumnSortMin() {
	
		return columnSortMin;
	}
	public void setColumnSortMin(int columnSortMin) {
	
		this.columnSortMin = columnSortMin;
	}
	public int getColumnSortMax() {
	
		return columnSortMax;
	}
	public void setColumnSortMax(int columnSortMax) {
	
		this.columnSortMax = columnSortMax;
	}
	
}

