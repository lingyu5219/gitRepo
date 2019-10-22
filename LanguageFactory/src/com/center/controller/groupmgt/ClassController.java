package com.center.controller.groupmgt;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.groupmgt.Class;
import com.center.po.groupmgt.Class;
import com.center.po.groupmgt.ClassQuery;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.groupmgt.ClassService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/groupmgt")
public class ClassController {

	@Autowired
	private ClassService service;
	
	@RequestMapping(value = "/classList", method = RequestMethod.GET)
	public ModelAndView classList(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("groupmgt/classList");
		return modelAndView;
	}
	
	@RequestMapping(value = "/addClass", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String addClass( Class c,HttpSession session){
		User user = (User)session.getAttribute("user");
		c.setCreateBy(user.getUserId());
		boolean result = service.addClass(c);
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
	@RequestMapping(value = "/queryClassList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryClassList(ClassQuery cq){
		DatatablesView<Class> dataTable = service.getClassList(cq);
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@RequestMapping(value="/updateClass", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String updateClass(Class c) {
		Integer resultInteger = service.updateClass(c);
		HashMap<String, String> rsMap = new HashMap<String, String>();
		if (resultInteger > 0) {
			rsMap.put("status", "1");
			rsMap.put("info", "更新成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "更新失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@RequestMapping(value = "/delClass", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String delClass(String classId) {
		Integer result = service.delClassById(classId);
		HashMap<String, String> rsMap = new HashMap<String, String>();
		if (result > 0) {
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
