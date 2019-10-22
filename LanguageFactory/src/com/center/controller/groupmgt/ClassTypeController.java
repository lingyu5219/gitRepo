package com.center.controller.groupmgt;

import java.util.ArrayList;
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

import com.center.po.groupmgt.ClassType;
import com.center.po.groupmgt.ClassTypeQuery;
import com.center.po.groupmgt.History;
import com.center.po.groupmgt.HistoryQuery;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.groupmgt.ClassTypeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/groupmgt")
public class ClassTypeController {

	@Autowired
	private ClassTypeService classTypeService;
	
	@RequestMapping(value = "/classTypeList", method = RequestMethod.GET)
	public ModelAndView classTypeList(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("groupmgt/classtypeList");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/addClassType", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addHistory( ClassType classType,HttpSession session){
		User user = (User)session.getAttribute("user");
		classType.setCreateBy(user.getUserId());
		boolean result = classTypeService.addClassType(classType);
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
	
	//query
		@ResponseBody
		@RequestMapping(value = "/queryClassTypeList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
		public String queryClassTypeList(ClassTypeQuery classType){
			DatatablesView<ClassType> dataTable = classTypeService.getClassTypeList(classType);
			String data = JSONObject.fromObject(dataTable).toString();
			return data;
		}
		
		//update
		@RequestMapping(value = "/updateClassType", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
		@ResponseBody
		public String updateClassType(ClassType classType){
			Integer resultInteger = classTypeService.updateClassType(classType);
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
		@RequestMapping(value = "/delClassType", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
		@ResponseBody
		public String delClassType(String classTypeId){
			Integer result = classTypeService.deleteClassTypeById(classTypeId);
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
		@RequestMapping(value="/classTypeSelectList",method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
		@ResponseBody
		public String getSelectList() {
			List<Map<String, Object>> result = classTypeService.getSelectList();
			return JSONArray.fromObject(result).toString();
		}
		
}
