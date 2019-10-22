package com.center.controller.gartenmgt;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.gartenmgt.GartenClass;
import com.center.po.gartenmgt.GartenClassQuery;
import com.center.po.hrmgt.Staff;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.gartenmgt.GartenClassService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/gartenmgt")
public class GartenClassController {

	@Autowired
	private GartenClassService service;
	
	@RequestMapping(value = "/gartenClassList", method = RequestMethod.GET)
	public ModelAndView classList(HttpSession session,HttpServletRequest request) throws Exception {
		//testttt
				HashMap<String, Object> personInfo = (HashMap<String, Object>) session.getAttribute("personalInfo");
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("gartenmgt/gartenClassList");
		return modelAndView;
	}
	
	@RequestMapping(value = "/addGartenClass", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String addGartenClass( GartenClass c,HttpSession session){
		User user = (User)session.getAttribute("user");
		c.setCreateBy(user.getUserId());
		HashMap<String, Object> personInfo = (HashMap<String, Object>) session.getAttribute("personalInfo");
		Staff staff = (Staff) personInfo.get("personal");
		if (staff!=null) {
			c.setGartenId(staff.getStaffGartenId());
			c.setGartenName(staff.getStaffGartenName());
		}
		
//		c.setGartenId(session.getAttribute("gid"));
		boolean result = service.addGartenClass(c);
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
	@RequestMapping(value = "/queryGartenClassList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryGartenClassList(HttpSession session,GartenClassQuery cq){
		HashMap<String, Object> personalInfo = (HashMap<String, Object>) session.getAttribute("personalInfo");
		Staff staff = (Staff) personalInfo.get("personal");
		cq.setGartenId(staff.getStaffGartenId());
		DatatablesView<GartenClass> dataTable = service.getGartenClassList(cq);
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@RequestMapping(value="/updateGartenClass", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String updateGartenClass(HttpSession session,GartenClass c) {
		HashMap<String, Object> personInfo = (HashMap<String, Object>) session.getAttribute("personalInfo");
		Staff staff = (Staff) personInfo.get("personal");
		if (staff!=null) {
			c.setGartenId(staff.getStaffGartenId());
			c.setGartenName(staff.getStaffGartenName());
		}
		Integer resultInteger = service.updateGartenClass(c);
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
	
	@RequestMapping(value = "/delGartenClass", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String delGartenClass(String classId) {
		Integer result = service.delGartenClassById(classId);
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
