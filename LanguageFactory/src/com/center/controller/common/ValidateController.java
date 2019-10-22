package com.center.controller.common;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.center.po.common.Validate;
import com.center.service.common.ValidateService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/validate")
public class ValidateController {
	
	@Autowired
	ValidateService validateService;
	
	@ResponseBody
	@RequestMapping(value="/isUnique",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String isUnique(@ModelAttribute("validate") Validate validate,HttpServletRequest request) throws Exception {
		HashMap<String, Object> rsMap = new HashMap<String, Object>();
		boolean result = validateService.isUnique(validate);
		rsMap.put("status", result);
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
}

