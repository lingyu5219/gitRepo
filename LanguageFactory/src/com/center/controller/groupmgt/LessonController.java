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

import com.center.po.groupmgt.Lesson;
import com.center.po.groupmgt.Lesson;
import com.center.po.groupmgt.LessonQuery;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.groupmgt.LessonService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/groupmgt")
public class LessonController {

	@Autowired
	private LessonService service;
	
	@RequestMapping(value = "/lessonList", method = RequestMethod.GET)
	public ModelAndView lessonList(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("groupmgt/lessonList");
		return modelAndView;
	}
	
	@RequestMapping(value = "/addLesson", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String addLesson( Lesson c,HttpSession session){
		User user = (User)session.getAttribute("user");
		c.setCreateBy(user.getUserId());
		boolean result = service.addLesson(c);
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
	@RequestMapping(value = "/queryLessonList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryLessonList(LessonQuery cq){
		DatatablesView<Lesson> dataTable = service.getLessonList(cq);
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@RequestMapping(value="/updateLesson", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String updateLesson(Lesson c) {
		Integer resultInteger = service.updateLesson(c);
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
	
	@RequestMapping(value = "/delLesson", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String delLesson(String lessonId) {
		Integer result = service.delLessonById(lessonId);
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
