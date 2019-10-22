package com.center.po.groupmgt;

import com.center.po.query.QueryCondition;

public class Album extends QueryCondition {
	private int albumId;
	private String albumName;
	private int createBy;
	private String createByName;
	private String createTime;
	private String remark;
	
	public int getAlbumId() {
	
		return albumId;
	}
	public void setAlbumId(int albumId) {
	
		this.albumId = albumId;
	}
	public String getAlbumName() {
	
		return albumName;
	}
	public void setAlbumName(String albumName) {
	
		this.albumName = albumName;
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

