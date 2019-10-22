package com.center.po.groupmgt;

import com.center.po.query.QueryCondition;

public class Message extends QueryCondition {
	private int msgId;
	private String msgTitle;
	private int msgType;
	private int msgPattern;
	private int msgGartenId;
	private String msgGartenName;
	private String msgContent;
	private int msgVideoId;
	private String msgVideoName;
	private String msgVideoUrl;
	private String msgCoverUrl;
	private String mediaCoverUrl;
	private int isGroup;
	private int msgTypeIsNot;
	private int createBy;
	private String createByName;
	private String createTime;
	private String remark;
	
	public int getMsgId() {
	
		return msgId;
	}
	public void setMsgId(int msgId) {
	
		this.msgId = msgId;
	}
	public String getMsgTitle() {
	
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
	
		this.msgTitle = msgTitle;
	}
	public int getMsgType() {
	
		return msgType;
	}
	public void setMsgType(int msgType) {
	
		this.msgType = msgType;
	}
	public int getMsgPattern() {
	
		return msgPattern;
	}
	public void setMsgPattern(int msgPattern) {
	
		this.msgPattern = msgPattern;
	}
	public int getMsgGartenId() {
	
		return msgGartenId;
	}
	public void setMsgGartenId(int msgGartenId) {
	
		this.msgGartenId = msgGartenId;
	}
	public String getMsgGartenName() {
	
		return msgGartenName;
	}
	public void setMsgGartenName(String msgGartenName) {
	
		this.msgGartenName = msgGartenName;
	}
	public String getMsgContent() {
	
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
	
		this.msgContent = msgContent;
	}
	public int getMsgVideoId() {
	
		return msgVideoId;
	}
	public void setMsgVideoId(int msgVideoId) {
	
		this.msgVideoId = msgVideoId;
	}
	public String getMsgVideoName() {
	
		return msgVideoName;
	}
	public void setMsgVideoName(String msgVideoName) {
	
		this.msgVideoName = msgVideoName;
	}
	public String getMsgVideoUrl() {
	
		return msgVideoUrl;
	}
	public void setMsgVideoUrl(String msgVideoUrl) {
	
		this.msgVideoUrl = msgVideoUrl;
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
	public int getIsGroup() {
	
		return isGroup;
	}
	public void setIsGroup(int isGroup) {
	
		this.isGroup = isGroup;
	}
	public String getMsgCoverUrl() {
	
		return msgCoverUrl;
	}
	public void setMsgCoverUrl(String msgCoverUrl) {
	
		this.msgCoverUrl = msgCoverUrl;
	}
	public String getMediaCoverUrl() {
	
		return mediaCoverUrl;
	}
	public void setMediaCoverUrl(String mediaCoverUrl) {
	
		this.mediaCoverUrl = mediaCoverUrl;
	}
	public int getMsgTypeIsNot() {
	
		return msgTypeIsNot;
	}
	public void setMsgTypeIsNot(int msgTypeIsNot) {
	
		this.msgTypeIsNot = msgTypeIsNot;
	}
	
}

