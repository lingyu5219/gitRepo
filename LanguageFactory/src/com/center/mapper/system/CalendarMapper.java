package com.center.mapper.system;

import java.util.List;

import com.center.po.system.CalendarDate;
/**
 * 
* ClassName: CalendarMapper <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月27日 下午9:20:02 <br/>
*
* @author 钱兆瑞
* @version
 */

public interface CalendarMapper {
//  查询需要签到时间
    List<CalendarDate> queryCalendarList(CalendarDate calendarDate) throws Exception;
    Long  queryCalendarCount(CalendarDate calendarDate) throws Exception;
//	插入需要签到时间
	void insertCalendarDate(CalendarDate calendarDate) throws Exception;
	//删除需要签到时间
	public int deleteCalendarById(int calendarId) throws Exception;
	
	public int updateCalendar(CalendarDate calendarDate) throws Exception;
}