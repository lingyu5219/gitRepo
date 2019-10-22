package com.center.service.system;

import java.util.List;

import com.center.po.query.DatatablesView;
import com.center.po.system.CalendarDate;
import com.center.po.system.CalendarQuery;
/**
* ClassName:CalendarService <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年1月10日 下午5:09:30 <br/>
* @author 钱兆瑞
* @version
* @see
*/
public interface CalendarService {
	//	插入签到时间
	List<String> insertCalendarDate(CalendarQuery calendarQuery) throws Exception;
	//	查询签到时间
	public DatatablesView<CalendarDate> queryUserList(CalendarDate calendarDate) throws Exception;
	//  删除签到时间
	public boolean deleteCalendarById(int calendarId) throws Exception;
	
	public boolean updateCalendar(CalendarDate calendarDate) throws Exception;
}
