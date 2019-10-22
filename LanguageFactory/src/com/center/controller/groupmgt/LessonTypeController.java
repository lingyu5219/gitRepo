package com.center.controller.groupmgt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.groupmgt.LessonType;
import com.center.po.groupmgt.LessonTypeQuery;
import com.center.po.groupmgt.LessonType;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.groupmgt.LessonTypeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/groupmgt")
public class LessonTypeController {

	@Autowired
	private LessonTypeService lessonTypeService;
	
	@RequestMapping(value="/lessonTypeList",method=RequestMethod.GET)
	public ModelAndView lessonTypeList(HttpServletRequest request)throws Exception{
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("groupmgt/lessonTypeList");
			return modelAndView;
	}
	
	@RequestMapping(value="/addLessonType",method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String addLessonType(LessonType lessonType,HttpSession session) {
		User user = (User) session.getAttribute("user");
		lessonType.setCreateBy(user.getUserId());
		boolean result = lessonTypeService.addLessonType(lessonType);
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
	@RequestMapping(value = "/queryLessonTypeList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryLessonTypeList(LessonTypeQuery lessonType){
		DatatablesView<LessonType> dataTable = lessonTypeService.getLessonTypeList(lessonType);
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	//update
	@RequestMapping(value = "/updateLessonType", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String updateLessonType(LessonType lessonType){
		Integer resultInteger = lessonTypeService.updateLessonType(lessonType);
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
	//delete
	@RequestMapping(value = "/delLessonType", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String delLessonType(String lessonTypeId){
		Integer result = lessonTypeService.deleteLessonTypeById(lessonTypeId);
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
	//下拉
	@RequestMapping(value="/lessonTypeSelectList",method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String getSelectList() {
		List<Map<String, Object>> result = lessonTypeService.getSelectList();
		return JSONArray.fromObject(result).toString();
	}
}
