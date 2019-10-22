package com.center.controller.orgmgt;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.hrmgt.Staff;
import com.center.po.orgmgt.Garten;
import com.center.po.orgmgt.GartenQuery;
import com.center.po.query.DatatablesView;
import com.center.service.orgmgt.GartenService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/orgmgt")
public class GartenController {

	@Autowired
	private GartenService gartenService;
	
	//返回全部中心信息
	@RequestMapping(value = "/gartenList", method = RequestMethod.GET)
	public ModelAndView gartenList(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("orgmgt/gartenList");

		return modelAndView;

	}
	
	@ResponseBody
	@RequestMapping(value="/queryGartenById",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryGartenById(HttpSession session) throws Exception {
		HashMap<String,Object> rsMap = new HashMap<String,Object>();
		//从session中获取当前登录用户所属的校园ID
		@SuppressWarnings("unchecked")
		HashMap<String, Object> personalInfo = (HashMap<String, Object>)session.getAttribute("personalInfo");
		Staff staff = null;
		if(personalInfo.containsKey("personal")){
			staff = (Staff)personalInfo.get("personal");
			Garten garten = gartenService.queryGartenById(staff.getStaffGartenId());
			if(garten == null){
				rsMap.put("status",0);
				rsMap.put("info", "您关联的员工所属的校园信息不存在");
			} else {
				rsMap.put("status", 1);
				rsMap.put("garten", garten);
			}
		} else {
			rsMap.put("status",0);
			rsMap.put("info", "您的账号没有关联员工信息，无法确定您所属的校园");
		}
		
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryGarten",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryGarten(@ModelAttribute("gartenQuery") GartenQuery gartenQuery,HttpServletRequest request) throws Exception {
		List<Garten> gartenList = gartenService.queryGarten(gartenQuery);
		String data = JSONArray.fromObject(gartenList).toString();
		return data;
	}
	
	//根据条件查询信息
	@ResponseBody
	@RequestMapping(value="/queryGartenList", method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryGartenList(@ModelAttribute("gartenQuery")GartenQuery gartenQuery,HttpServletRequest request) throws Exception {
		DatatablesView<Garten> dataTable= gartenService.queryGartenList(gartenQuery);
		dataTable.setDraw(gartenQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	//添加信息
	@ResponseBody
	@RequestMapping(value="/addGarten",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addGarten(@ModelAttribute("garten")Garten garten,HttpServletRequest request) throws Exception {
		
		boolean rs = gartenService.addGarten(garten, request);;
		HashMap<String,String> rsMap = new HashMap<String,String>();
		if (rs) {
			rsMap.put("status", "1");
			rsMap.put("info", "增加成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "增加失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
		  
	}
	 
	//删除信息
	@ResponseBody
	@RequestMapping(value="/delGarten",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String delGarten(@ModelAttribute("gartenId") int gartenId,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = gartenService.delGarten(gartenId);
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

	//更改信息
	@ResponseBody
	@RequestMapping(value="/updateGarten",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateGarten(@ModelAttribute("garten")Garten garten,HttpServletRequest request) throws Exception {
		
		boolean result = gartenService.updateGarten(garten);;
		HashMap<String,String> rsMap = new HashMap<String,String>();
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

