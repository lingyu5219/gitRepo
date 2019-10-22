
package com.center.po.groupmgt;


/**
* ClassName: CommentsQuery <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年7月1日 下午6:21:13 <br/>
*
* @author Tony
* @version 
*/

public class CommentsQuery extends Comments {
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

