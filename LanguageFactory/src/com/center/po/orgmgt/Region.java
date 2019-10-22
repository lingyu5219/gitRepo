
/**
* Project Name:kindergarten
* File Name:Region.java
* Package Name:com.center.po.orgmgt
* Date:2018年6月12日上午9:41:55
* Copyright (c) 2018, Tony All Rights Reserved.
*
*/

/**
* Project Name:kindergarten
* File Name:Region.java
* Package Name:com.center.po.orgmgt
* Date:2018年6月12日上午9:41:55
* Copyright (c) 2018, Tony All Rights Reserved.
*
*/


package com.center.po.orgmgt;
import java.util.List;

import com.center.po.query.QueryCondition;

/**
* ClassName: Region <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月12日 上午9:41:55 <br/>
*
* @author Tony
* @version 
*/

public class Region extends QueryCondition {
	private int regionId;
	private String regionName;
	private int regionParentId;
	private int regionStatus;
	private int createBy;
	private String createByName;
	private String createTime;
	private String remark;
	private int regionChildNum;
	private List<Region> childRegionList;
	
	public int getRegionId() {
	
		return regionId;
	}
	public void setRegionId(int regionId) {
	
		this.regionId = regionId;
	}
	public String getRegionName() {
	
		return regionName;
	}
	public void setRegionName(String regionName) {
	
		this.regionName = regionName;
	}
	public int getRegionParentId() {
	
		return regionParentId;
	}
	public void setRegionParentId(int regionParentId) {
	
		this.regionParentId = regionParentId;
	}
	public int getRegionStatus() {
	
		return regionStatus;
	}
	public void setRegionStatus(int regionStatus) {
	
		this.regionStatus = regionStatus;
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
	public List<Region> getChildRegionList() {
	
		return childRegionList;
	}
	public void setChildRegionList(List<Region> childRegionList) {
	
		this.childRegionList = childRegionList;
	}
	public int getRegionChildNum() {
	
		return regionChildNum;
	}
	public void setRegionChildNum(int regionChildNum) {
	
		this.regionChildNum = regionChildNum;
	}
	
}

