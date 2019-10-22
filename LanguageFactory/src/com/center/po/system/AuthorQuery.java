
/**
* Project Name:trainingCenter
* File Name:CalendarQuery.java
* Package Name:com.center.po.system
* Date:2017年1月11日上午12:02:25
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package com.center.po.system;
/**
* ClassName:CalendarQuery <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年1月11日 上午12:02:25 <br/>
* @author qian
* @version
* @see
*/
public class AuthorQuery extends Author{
	private String createTimeBegin;
	private String createTimeEnd;
	public String getCreateTimeBegin() {
	
		return createTimeBegin;
	}
	public void setCreateTimeBegin(String createTimeBegin) {
	
		this.createTimeBegin = createTimeBegin;
	}
	public String getCreateTimeEnd() {
	
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
	
		this.createTimeEnd = createTimeEnd;
	}
	
}

