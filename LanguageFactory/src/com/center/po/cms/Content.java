
/**
* Project Name:kindergarten
* File Name:Content.java
* Package Name:com.center.po.cms
* Date:2018年6月9日下午10:32:33
* Copyright (c) 2018, Tony All Rights Reserved.
*
*/

/**
* Project Name:kindergarten
* File Name:Content.java
* Package Name:com.center.po.cms
* Date:2018年6月9日下午10:32:33
* Copyright (c) 2018, Tony All Rights Reserved.
*
*/


package com.center.po.cms;
/**
* ClassName:Content <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月9日 下午10:32:33 <br/>
* @author Tony
* @version
* @see
*/

import com.center.po.query.QueryCondition;

/**
* ClassName: Content <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月9日 下午10:32:33 <br/>
*
* @author Tony
* @version 
*/

public class Content extends QueryCondition {
	private int contentId;
	private String contentTitle;
	private String contentSubTitle;
	private int contentType;
	private String contentBody;
	private String contentUrl;
	private int contentState;
	private int contentColumnId;
	private String contentColumnName;
	private String createTime;
	private int createBy;
	private String createByName;
	private String remark;
	
	public String getContentColumnName() {
	
		return contentColumnName;
	}
	public void setContentColumnName(String contentColumnName) {
	
		this.contentColumnName = contentColumnName;
	}
	public int getContentId() {
	
		return contentId;
	}
	public void setContentId(int contentId) {
	
		this.contentId = contentId;
	}
	public String getContentTitle() {
	
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
	
		this.contentTitle = contentTitle;
	}
	public String getContentSubTitle() {
	
		return contentSubTitle;
	}
	public void setContentSubTitle(String contentSubTitle) {
	
		this.contentSubTitle = contentSubTitle;
	}
	public int getContentType() {
	
		return contentType;
	}
	public void setContentType(int contentType) {
	
		this.contentType = contentType;
	}
	public String getContentBody() {
	
		return contentBody;
	}
	public void setContentBody(String contentBody) {
	
		this.contentBody = contentBody;
	}
	public String getContentUrl() {
	
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
	
		this.contentUrl = contentUrl;
	}
	public int getContentState() {
	
		return contentState;
	}
	public void setContentState(int contentState) {
	
		this.contentState = contentState;
	}
	public int getContentColumnId() {
	
		return contentColumnId;
	}
	public void setContentColumnId(int contentColumnId) {
	
		this.contentColumnId = contentColumnId;
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
	
	
}

