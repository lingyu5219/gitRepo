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

import com.center.po.groupmgt.EduProject;
import com.center.po.groupmgt.EduProject;
import com.center.po.groupmgt.EduProjectQuery;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.groupmgt.EduProjectService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/groupmgt")
public class EduProjectController {

	@Autowired
	private EduProjectService service;
	
	@RequestMapping(value = "/eduProjectList", method = RequestMethod.GET)
	public ModelAndView eduProjectList(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("groupmgt/eduProjectList");
		return modelAndView;
	}
	
	@RequestMapping(value = "/addEduProject", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String addEduProject( EduProject c,HttpSession session){
		User user = (User)session.getAttribute("user");
		c.setCreateBy(user.getUserId());
		boolean result = service.addEduProject(c);
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
	@RequestMapping(value = "/queryEduProjectList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryEduProjectList(EduProjectQuery cq){
		DatatablesView<EduProject> dataTable = service.getEduProjectList(cq);
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@RequestMapping(value="/updateEduProject", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String updateEduProject(EduProject c) {
		Integer resultInteger = service.updateEduProject(c);
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
	
	@RequestMapping(value = "/delEduProject", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String delEduProject(String eduProjectId) {
		Integer result = service.delEduProjectById(eduProjectId);
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
