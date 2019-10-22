package com.center.po.groupmgt;

import java.util.Date;

import com.center.po.query.QueryCondition;

/**
 * @author 
 * 发展历程实体类
 */
public class History extends QueryCondition{
	private Integer historyId;
	private String historyTitle;
	private String historyContent;
	private String historyDate;
	private Integer createBy;
	private String createTime;
	private String createByName;
	private String remark;
	
	public History() {
	}
	

	@Override
	public String toString() {
		return "History [historyId=" + historyId + ", historyTitle="
				+ historyTitle + ", historyContent=" + historyContent
				+ ", historyDate=" + historyDate + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", remark=" + remark + "]";
	}
	public Integer getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	public String getHistoryTitle() {
		return historyTitle;
	}
	public void setHistoryTitle(String historyTitle) {
		this.historyTitle = historyTitle;
	}
	public String getHistoryContent() {
		return historyContent;
	}
	public void setHistoryContent(String historyContent) {
		this.historyContent = historyContent;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public String getHistoryDate() {
		return historyDate;
	}


	public void setHistoryDate(String historyDate) {
		this.historyDate = historyDate;
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
