package com.center.po.system;

import com.center.po.query.QueryCondition;
/**
 * 
* ClassName: CalendarDate <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月27日 下午9:20:37 <br/>
*
* @author 钱兆瑞
* @version
 */
public class CalendarDate extends QueryCondition {
    private Integer calendarId;

    private String calendarDate;

    private String calendarType;

    private String calendarDescribe;

    private String createTime;

    private Integer createBy;

    private String remark;

    

    public Integer getCalendarId() {
	
		return calendarId;
	}

	public void setCalendarId(Integer calendarId) {
	
		this.calendarId = calendarId;
	}

	public String getCalendarType() {
	
		return calendarType;
	}

	public void setCalendarType(String calendarType) {
	
		this.calendarType = calendarType;
	}

	public String getCalendarDescribe() {
	
		return calendarDescribe;
	}

	public void setCalendarDescribe(String calendarDescribe) {
	
		this.calendarDescribe = calendarDescribe;
	}


	public Integer getCreateBy() {
	
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
	
		this.createBy = createBy;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getCalendarDate() {
		
			return calendarDate;
	}

	public void setCalendarDate(String calendarDate) {
		
			this.calendarDate = calendarDate;
	}

	public String getCreateTime() {
		
			return createTime;
	}

	public void setCreateTime(String createTime) {
		
			this.createTime = createTime;
	}
}