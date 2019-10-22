
/**
* Project Name:trainingCenter
* File Name:Parmeter.java
* Package Name:com.center.po.sysMgt
* Date:2018年6月23日下午1:19:18
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.po.system;

import com.center.po.query.QueryCondition;

/**
* ClassName:Parmeter <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月23日 下午1:19:18 <br/>
* @author donghao
* @version
* @see
*/
public class Param extends QueryCondition{
	private Integer paramId;
	private String paramType; // 参数类型
	private String paramCode; // 参数代码
	private String paramName; // 前端显示名称
	private int createBy;
	private String createByName;
	private String createTime;
	private String remark;
	public Integer getParamId() {
	
		return paramId;
	}
	public void setParamId(Integer paramId) {
	
		this.paramId = paramId;
	}
	public String getParamType() {
	
		return paramType;
	}
	public void setParamType(String paramType) {
	
		this.paramType = paramType;
	}
	public String getParamCode() {
	
		return paramCode;
	}
	public void setParamCode(String paramCode) {
	
		this.paramCode = paramCode;
	}
	public String getParamName() {
	
		return paramName;
	}
	public void setParamName(String paramName) {
	
		this.paramName = paramName;
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
	
}

