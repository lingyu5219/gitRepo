package com.center.po.groupmgt;

import com.center.po.query.QueryCondition;

/**
* ClassName: Media <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月19日 下午12:10:33 <br/>
*
* @author Tony
* @version 
*/

public class Media extends QueryCondition {
	private int mediaId;
	private String mediaTitle;
	private String mediaDesc;
	private String mediaUrl;
	private String mediaCoverUrl;
	private String mediaOriginalUrl;
	private String mediaCoverOriginalUrl;
	private int mediaPattern;
	private int mediaAlbumId;
	private String mediaAlbumName;
	private String mediaFileName;
	private long mediaFileSize;
	private int mediaIsHome;
	private int mediaFileChanged;
	private int mediaCoverFileChanged;
	private int mediaGartenId;
	private String mediaGartenName;
	private int mediaIsFront;
	private int createBy;
	private String createByName;
	private String createTime;
	private String remark;
	
	public int getMediaId() {
	
		return mediaId;
	}
	public void setMediaId(int mediaId) {
	
		this.mediaId = mediaId;
	}
	public String getMediaTitle() {
	
		return mediaTitle;
	}
	public void setMediaTitle(String mediaTitle) {
	
		this.mediaTitle = mediaTitle;
	}
	public String getMediaDesc() {
	
		return mediaDesc;
	}
	public void setMediaDesc(String mediaDesc) {
	
		this.mediaDesc = mediaDesc;
	}
	
	public String getMediaOriginalUrl() {
	
		return mediaOriginalUrl;
	}
	public void setMediaOriginalUrl(String mediaOriginalUrl) {
	
		this.mediaOriginalUrl = mediaOriginalUrl;
	}
	public String getMediaUrl() {
	
		return mediaUrl;
	}
	public void setMediaUrl(String mediaUrl) {
	
		this.mediaUrl = mediaUrl;
	}
	public int getMediaPattern() {
	
		return mediaPattern;
	}
	public void setMediaPattern(int mediaPattern) {
	
		this.mediaPattern = mediaPattern;
	}
	public int getMediaAlbumId() {
	
		return mediaAlbumId;
	}
	public void setMediaAlbumId(int mediaAlbumId) {
	
		this.mediaAlbumId = mediaAlbumId;
	}
	public String getMediaAlbumName() {
	
		return mediaAlbumName;
	}
	public void setMediaAlbumName(String mediaAlbumName) {
	
		this.mediaAlbumName = mediaAlbumName;
	}
	public String getMediaFileName() {
	
		return mediaFileName;
	}
	public void setMediaFileName(String mediaFileName) {
	
		this.mediaFileName = mediaFileName;
	}
	public long getMediaFileSize() {
	
		return mediaFileSize;
	}
	public void setMediaFileSize(long mediaFileSize) {
	
		this.mediaFileSize = mediaFileSize;
	}
	
	public int getMediaIsHome() {
	
		return mediaIsHome;
	}
	public void setMediaIsHome(int mediaIsHome) {
	
		this.mediaIsHome = mediaIsHome;
	}
	
	public int getMediaFileChanged() {
	
		return mediaFileChanged;
	}
	public void setMediaFileChanged(int mediaFileChanged) {
	
		this.mediaFileChanged = mediaFileChanged;
	}
	public int getMediaGartenId() {
	
		return mediaGartenId;
	}
	public void setMediaGartenId(int mediaGartenId) {
	
		this.mediaGartenId = mediaGartenId;
	}
	public String getMediaGartenName() {
	
		return mediaGartenName;
	}
	public void setMediaGartenName(String mediaGartenName) {
	
		this.mediaGartenName = mediaGartenName;
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
	public String getMediaCoverUrl() {
	
		return mediaCoverUrl;
	}
	public void setMediaCoverUrl(String mediaCoverUrl) {
	
		this.mediaCoverUrl = mediaCoverUrl;
	}
	public String getMediaCoverOriginalUrl() {
	
		return mediaCoverOriginalUrl;
	}
	public void setMediaCoverOriginalUrl(String mediaCoverOriginalUrl) {
	
		this.mediaCoverOriginalUrl = mediaCoverOriginalUrl;
	}
	public int getMediaCoverFileChanged() {
	
		return mediaCoverFileChanged;
	}
	public void setMediaCoverFileChanged(int mediaCoverFileChanged) {
	
		this.mediaCoverFileChanged = mediaCoverFileChanged;
	}
	public int getMediaIsFront() {
	
		return mediaIsFront;
	}
	public void setMediaIsFront(int mediaIsFront) {
	
		this.mediaIsFront = mediaIsFront;
	}
	
}

