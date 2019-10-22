
/**
* Project Name:trainingCenter
* File Name:Garten.java
* Package Name:com.center.po.org
* Date:2018年6月22日下午1:50:57
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.po.orgmgt;

import com.center.po.query.QueryCondition;

/**
* ClassName:Garten <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月22日 下午1:50:57 <br/>
* @author Tony
* @version
* @see
*/
public class Garten extends QueryCondition {
	private int gartenId;
	private String gartenName;
	private String gartenDesc;
	private String gartenSpeech;
	private String gartenEmail; 
	private String gartenPhone;
	private Integer gartenZipCode;
	private int gartenProvinceId;
	private String gartenProvinceName;
	private int gartenCityId;
	private String gartenCityName;
	private int gartenDistrictId;
	private String gartenDistrictName;
	private String gartenAddress;
	private String gartenLongitude;
	private String gartenLatitude;
	private String gartenVisitTime;
	private String createTime;
	private String remark;
	private int createBy;
	private String createByName;
	
	public int getGartenId() {
	
		return gartenId;
	}
	public void setGartenId(int gartenId) {
	
		this.gartenId = gartenId;
	}
	public String getGartenName() {
	
		return gartenName;
	}
	public void setGartenName(String gartenName) {
	
		this.gartenName = gartenName;
	}
	public String getGartenDesc() {
	
		return gartenDesc;
	}
	public void setGartenDesc(String gartenDesc) {
	
		this.gartenDesc = gartenDesc;
	}
	public String getGartenSpeech() {
	
		return gartenSpeech;
	}
	public void setGartenSpeech(String gartenSpeech) {
	
		this.gartenSpeech = gartenSpeech;
	}
	public String getGartenEmail() {
	
		return gartenEmail;
	}
	public void setGartenEmail(String gartenEmail) {
	
		this.gartenEmail = gartenEmail;
	}
	public String getGartenPhone() {
	
		return gartenPhone;
	}
	public void setGartenPhone(String gartenPhone) {
	
		this.gartenPhone = gartenPhone;
	}
	public Integer getGartenZipCode() {
	
		return gartenZipCode;
	}
	public void setGartenZipCode(Integer gartenZipCode) {
	
		this.gartenZipCode = gartenZipCode;
	}
	public int getGartenProvinceId() {
	
		return gartenProvinceId;
	}
	public void setGartenProvinceId(int gartenProvinceId) {
	
		this.gartenProvinceId = gartenProvinceId;
	}
	public int getGartenCityId() {
	
		return gartenCityId;
	}
	public void setGartenCityId(int gartenCityId) {
	
		this.gartenCityId = gartenCityId;
	}
	public int getGartenDistrictId() {
	
		return gartenDistrictId;
	}
	public void setGartenDistrictId(int gartenDistrictId) {
	
		this.gartenDistrictId = gartenDistrictId;
	}
	public String getGartenAddress() {
	
		return gartenAddress;
	}
	public void setGartenAddress(String gartenAddress) {
	
		this.gartenAddress = gartenAddress;
	}
	public String getGartenLongitude() {
	
		return gartenLongitude;
	}
	public void setGartenLongitude(String gartenLongitude) {
	
		this.gartenLongitude = gartenLongitude;
	}
	public String getGartenLatitude() {
	
		return gartenLatitude;
	}
	public void setGartenLatitude(String gartenLatitude) {
	
		this.gartenLatitude = gartenLatitude;
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
	public int getCreateBy() {
	
		return createBy;
	}
	public void setCreateBy(int createBy) {
	
		this.createBy = createBy;
	}
	public String getGartenProvinceName() {
	
		return gartenProvinceName;
	}
	public void setGartenProvinceName(String gartenProvinceName) {
	
		this.gartenProvinceName = gartenProvinceName;
	}
	public String getGartenCityName() {
	
		return gartenCityName;
	}
	public void setGartenCityName(String gartenCityName) {
	
		this.gartenCityName = gartenCityName;
	}
	public String getGartenDistrictName() {
	
		return gartenDistrictName;
	}
	public void setGartenDistrictName(String gartenDistrictName) {
	
		this.gartenDistrictName = gartenDistrictName;
	}
	public String getCreateByName() {
	
		return createByName;
	}
	public void setCreateByName(String createByName) {
	
		this.createByName = createByName;
	}
	public String getGartenVisitTime() {
	
		return gartenVisitTime;
	}
	public void setGartenVisitTime(String gartenVisitTime) {
	
		this.gartenVisitTime = gartenVisitTime;
	}
	
}

