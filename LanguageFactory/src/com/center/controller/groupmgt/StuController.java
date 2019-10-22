package com.center.controller.groupmgt;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.groupmgt.Stu;
import com.center.po.groupmgt.StuQuery;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.StuService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/groupmgt")
public class StuController {

	@Autowired
	private StuService studentService;

	@RequestMapping(value = "/stuList", method = RequestMethod.GET)
	public ModelAndView studentList(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("groupmgt/stuList");
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/addStu", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addStu(Stu student,HttpServletRequest request) throws Exception {
		boolean result = studentService.addStu(student, request);
		HashMap<String, String> rsMap = new HashMap<String, String>();
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", "增加成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "增加失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryStuList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryStuList(StuQuery studentQuery) throws Exception {
		DatatablesView<Stu> dataTable = studentService.queryStuList(studentQuery);
		dataTable.setDraw(studentQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateStu", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String updateStu(Stu student) throws Exception {
		boolean result = studentService.updateStu(student);
		HashMap<String, String> rsMap = new HashMap<String, String>();

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
	@ResponseBody
	@RequestMapping(value = "/delStu", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String delStu(int stuId) throws Exception {
		boolean result = studentService.delStu(stuId);
		HashMap<String, String> rsMap = new HashMap<String, String>();
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
}
