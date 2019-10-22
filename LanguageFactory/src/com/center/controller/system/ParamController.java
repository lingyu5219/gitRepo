
/**
* Project Name:trainingCenter
* File Name:ParmeterController.java
* Package Name:com.center.controller.system
* Date:2018年6月29日下午3:43:52
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.controller.system;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.query.DatatablesView;
import com.center.po.system.Param;
import com.center.po.system.ParamQuery;
import com.center.service.system.ParamService;
import com.center.utils.ParamUtil;

import net.sf.json.JSONObject;

/**
* ClassName:ParmeterController <br/>
* Function: TODO ADD 系统参数. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月29日 下午3:43:52 <br/>
* @author donghao
* @version
* @see
*/
@Controller
@RequestMapping("/system")
public class ParamController {
	
	@Autowired
	private ParamService paramService;
	
	@Autowired
	private ParamUtil paramUtil;
	
	@RequestMapping("/paramList")
	public ModelAndView paramList(HttpServletRequest request) throws Exception {
		return new ModelAndView("system/paramList");
	}
	
	@ResponseBody
	@RequestMapping(value="/queryParamList",method=RequestMethod.POST,produces="text/json;charset=UTF-8")
	public String queryParamList(@ModelAttribute("UserQuery") ParamQuery paramQuery,HttpServletRequest request) throws Exception{
		DatatablesView<Param> datatablesView = paramService.queryParamList(paramQuery);
		datatablesView.setDraw(paramQuery.getDraw());
		String data = JSONObject.fromObject(datatablesView).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/addParam",method=RequestMethod.POST,produces="text/json;charset=UTF-8")
	public String addParam(HttpServletRequest request,@ModelAttribute("param") Param param) throws Exception{
		paramService.addParam(request, param);
		HashMap<String,String> rsMap = new HashMap<String,String>();
		if (param.getParamId() > 0) {
			rsMap.put("status", "1");
			rsMap.put("info", "增加成功");
			paramUtil.refreshParamMap();
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "增加失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateParam",method=RequestMethod.POST,produces="text/json;charset=UTF-8")
	public String updateParam(@ModelAttribute("param") Param param,HttpServletRequest request) throws Exception{
		
		boolean result = paramService.updateParam(request, param);
		
		HashMap<String,String> rsMap = new HashMap<String,String>();
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", "更新成功");
			paramUtil.refreshParamMap();
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "更新失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/delParam",method=RequestMethod.POST,produces="text/json;charset=UTF-8")
	public String delParam(@ModelAttribute("paramId") int paramId,HttpServletRequest request) throws Exception{
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = paramService.delParam(paramId);
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", "删除成功");
			paramUtil.refreshParamMap();
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "删除失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
}

