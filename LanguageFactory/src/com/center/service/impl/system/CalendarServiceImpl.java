
/**
* Project Name:trainingCenter
* File Name:CalendarServiceImpl.java
* Package Name:com.center.service.impl.system
* Date:2017年1月10日下午5:09:30
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package com.center.service.impl.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.system.CalendarMapper;
import com.center.po.query.DatatablesView;
import com.center.po.system.CalendarDate;
import com.center.po.system.CalendarQuery;
import com.center.service.system.CalendarService;

/**
 * ClassName:CalendarServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年1月10日 下午5:09:30 <br/>
 * 
 * 
 * @author 钱兆瑞
 * @version
 * @see
 */
@Service
public class CalendarServiceImpl implements CalendarService {
	@Autowired
	private CalendarMapper calendarMapper;

	// 更新签到时间
	@Override
	public boolean updateCalendar(CalendarDate calendarDate) throws Exception {
		int affectedRecords = calendarMapper.updateCalendar(calendarDate);
		
		if (affectedRecords > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 删除签到时间
	@Override
	public boolean deleteCalendarById(int calendarId) throws Exception {
		int affectedRecords = calendarMapper.deleteCalendarById(calendarId);
		if (affectedRecords > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 添加签到时间
	@Override
	public List<String> insertCalendarDate(CalendarQuery calendarQuery) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<Date> dateList = getDatesBetweenTwoDate(sdf.parse(calendarQuery.getTime1()),
				sdf.parse(calendarQuery.getTime2()));

		List<String> errorDate = new ArrayList<>();
		for (Date date : dateList) {

			calendarQuery.setCalendarDate(sdf.format(date));
			try {
				calendarMapper.insertCalendarDate(calendarQuery);
			} catch (Exception e) {
				errorDate.add(sdf.format(date));
				e.printStackTrace();
			}
		}

		return errorDate;
	}

	private List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(beginDate);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);// 把结束时间加入集合
		return lDate;
	}

	@Override
	public DatatablesView<CalendarDate> queryUserList(CalendarDate calendarDate) throws Exception {
		DatatablesView<CalendarDate> dataView = new DatatablesView<CalendarDate>();

		List<CalendarDate> calendarList = calendarMapper.queryCalendarList(calendarDate);

		Long count = calendarMapper.queryCalendarCount(calendarDate);

		dataView.setRecordsTotal(count);
		dataView.setData(calendarList);
		return dataView;

	}

}
