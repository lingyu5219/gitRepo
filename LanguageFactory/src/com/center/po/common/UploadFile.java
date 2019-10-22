
/**
* Project Name:eduSystem
* File Name:UploadFile.java
* Package Name:com.center.po.common
* Date:2017年5月17日下午9:51:14
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package com.center.po.common;

/**
* ClassName:UploadFile <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年5月17日 下午9:51:14 <br/>
* @author Tony
* @version
* @see
*/
public class UploadFile {
	private String fileName;
	
	private String uploadPath;
	
	private String fileRelativePath;
	
	private String fileType;
	
	private long fileSize;
	
	private long fileMaxSize;
	
	private int fileMaxCount;
	
	private String fileLimitType;

	public String getFileName() {
	
		return fileName;
	}

	public void setFileName(String fileName) {
	
		this.fileName = fileName;
	}

	public String getFileRelativePath() {
	
		return fileRelativePath;
	}

	public void setFileRelativePath(String fileRelativePath) {
	
		this.fileRelativePath = fileRelativePath;
	}

	public String getFileType() {
	
		return fileType;
	}

	public void setFileType(String fileType) {
	
		this.fileType = fileType;
	}

	public long getFileSize() {
	
		return fileSize;
	}

	public void setFileSize(long fileSize) {
	
		this.fileSize = fileSize;
	}

	public String getUploadPath() {
	
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
	
		this.uploadPath = uploadPath;
	}

	public long getFileMaxSize() {
	
		return fileMaxSize;
	}

	public void setFileMaxSize(long fileMaxSize) {
	
		this.fileMaxSize = fileMaxSize;
	}

	public int getFileMaxCount() {
	
		return fileMaxCount;
	}

	public void setFileMaxCount(int fileMaxCount) {
	
		this.fileMaxCount = fileMaxCount;
	}

	public String getFileLimitType() {
	
		return fileLimitType;
	}

	public void setFileLimitType(String fileLimitType) {
	
		this.fileLimitType = fileLimitType;
	}
	
}

