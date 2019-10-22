
/**
* Project Name:trainingCenter
* File Name:notice.java
* Package Name:com.center.po.sysMgt
* Date:2018年6月23日上午11:05:52
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.po.system;

import com.center.po.query.QueryCondition;

/**
* ClassName:notice <br/>
* Function: TODO ADD 系统公告. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月23日 上午11:05:52 <br/>
* @author donghao s
* @version 1.0
* @see
*/
public class Notice extends QueryCondition{
	private int noticeId;
	private String noticeTitle; // 标题
	private String noticeContent;  // 内容
	//公告状态 1:保存 2:发布 默认为1
	private int noticeState; 
	private int readStatus; 
	private String createTime;
	private int createBy;
	private String createByName;
	private String remark;
	private int logId;
	
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeTitle() {
	
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
	
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
	
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
	
		this.noticeContent = noticeContent;
	}
	public int getNoticeState() {
	
		return noticeState;
	}
	public void setNoticeState(int noticeState) {
	
		this.noticeState = noticeState;
	}
	public int getReadStatus() {
		
		return readStatus;
	}
	public void setReadStatus(int readStatus) {
	
		this.readStatus = readStatus;
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
	public int getLogId() {
	
		return logId;
	}
	public void setLogId(int logId) {
	
		this.logId = logId;
	}
	
}

