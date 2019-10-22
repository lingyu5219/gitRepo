package com.center.po.system;

import com.center.po.query.QueryCondition;

public class Log extends QueryCondition {
	private int logId;
	
	private String logRequest;
	
	private int logUserId;
	
	private String logOperation;
	
	private String createTime;
	
	private String remark;

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getLogRequest() {
		return logRequest;
	}

	public void setLogRequest(String logRequest) {
		this.logRequest = logRequest;
	}

	public int getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(int logUserId) {
		this.logUserId = logUserId;
	}

	public String getLogOperation() {
		return logOperation;
	}

	public void setLogOperation(String logOperation) {
		this.logOperation = logOperation;
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
