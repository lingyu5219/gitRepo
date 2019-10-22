
package com.center.po.groupmgt;

import com.center.po.query.QueryCondition;

public class Comments extends QueryCondition{
	private int commentsId;
	private String commentsTitle;
	private String commentsContent;
	private String commentsBy;
	private String commentsEmail;
	private String commentsPhone;
	private String commentsAddress;
	private String createTime;
	private String verifyCode;
	private String remark;
	
	public int getCommentsId() {
	
		return commentsId;
	}
	public void setCommentsId(int commentsId) {
	
		this.commentsId = commentsId;
	}
	public String getCommentsTitle() {
	
		return commentsTitle;
	}
	public void setCommentsTitle(String commentsTitle) {
	
		this.commentsTitle = commentsTitle;
	}
	public String getCommentsContent() {
	
		return commentsContent;
	}
	public void setCommentsContent(String commentsContent) {
	
		this.commentsContent = commentsContent;
	}
	public String getCommentsBy() {
	
		return commentsBy;
	}
	public void setCommentsBy(String commentsBy) {
	
		this.commentsBy = commentsBy;
	}
	public String getCommentsEmail() {
	
		return commentsEmail;
	}
	public void setCommentsEmail(String commentsEmail) {
	
		this.commentsEmail = commentsEmail;
	}
	public String getCommentsPhone() {
	
		return commentsPhone;
	}
	public void setCommentsPhone(String commentsPhone) {
	
		this.commentsPhone = commentsPhone;
	}
	public String getCommentsAddress() {
	
		return commentsAddress;
	}
	public void setCommentsAddress(String commentsAddress) {
	
		this.commentsAddress = commentsAddress;
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
	public String getVerifyCode() {
	
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
	
		this.verifyCode = verifyCode.toUpperCase();
	}
	
}

