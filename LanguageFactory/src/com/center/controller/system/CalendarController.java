
/**
* Project Name:trainingCenter
* File Name:CalendarController.java
* Package Name:com.center.controller.system
* Date:2017年1月10日下午4:53:43
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package com.center.controller.system;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.query.DatatablesView;
import com.center.po.system.CalendarDate;
import com.center.po.system.CalendarQuery;
import com.center.service.system.CalendarService;

import net.sf.json.JSONObject;

/**
* ClassName:CalendarController <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年1月10日 下午4:53:43 <br/>
* @author 钱兆瑞
* @version
* @see
*/


@Controller
@RequestMapping("/system")
public class CalendarController {
	
	@Autowired
	private CalendarService calendarService;
	
//	进入日历页面
	@RequestMapping(value = "/calendarList", method = RequestMethod.GET)
	public ModelAndView userList(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("system/calendarList");

		return modelAndView;

	}	
	
//	查询日历列表
	@ResponseBody
	@RequestMapping(value="/queryCalendarList",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryCalendarList(@ModelAttribute("CalendarDate") CalendarDate calendarDate,HttpServletRequest request) throws Exception {
        
		DatatablesView<CalendarDate> dataTable = calendarService.queryUserList(calendarDate);
		
		dataTable.setDraw(calendarDate.getDraw());

		String data = JSONObject.fromObject(dataTable).toString();

		return data;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/addCalendar",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addCalendar(@ModelAttribute("CalendarQuery") CalendarQuery calendarQuery,HttpServletRequest request) throws Exception {
		calendarQuery.setCreateBy(1);
		
		List<String> errorDate=calendarService.insertCalendarDate(calendarQuery);
		
		HashMap<String,String> rsMap = new HashMap<String,String>();
		if (errorDate.size()==0) {
			rsMap.put("status", "1");
			rsMap.put("info", "增加成功");
		} else {
			rsMap.put("status", "1");			
			String error="";
			for (String string : errorDate) {
				error+=string+",";
			}			
			rsMap.put("info",error+"<br/>增加失败，请检查是否已经添加过");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/delCalendar",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String delCalendar(@ModelAttribute("calendarId") int calendarId,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = calendarService.deleteCalendarById(calendarId);
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", "删除成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "删除失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateCalendar",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateCalendar(@ModelAttribute("calendarDate") CalendarDate calendarDate,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		
		calendarDate.setCreateBy(1);
		
		boolean result = calendarService.updateCalendar(calendarDate);
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", "更新成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "更新失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
}

