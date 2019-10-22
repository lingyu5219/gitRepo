
/**
* Project Name:trainingCenter
* File Name:CenterController.java
* Package Name:com.center.controller.org
* Date:2018年6月22日下午2:22:59
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.controller.system;

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

import com.center.po.query.DatatablesView;
import com.center.po.system.Role;
import com.center.po.system.RoleQuery;
import com.center.po.system.User;
import com.center.service.system.RoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* ClassName:CenterController <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月22日 下午2:22:59 <br/>
* @author Tony
* @version
* @see
*/
@Controller
@RequestMapping("/system")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	private String getJason(boolean result,String info){
		HashMap<String,String> rsMap = new HashMap<String,String>();
		
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", info+"成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", info+"失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@RequestMapping(value = "/roleList", method = RequestMethod.GET)
	public ModelAndView userList(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/roleList");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryRole",method=RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryMenu(@ModelAttribute("roleQuery") RoleQuery roleQuery,HttpServletRequest request) throws Exception {
		List<Role> roleList = roleService.queryRole(roleQuery);
		String data = JSONArray.fromObject(roleList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryRoleList",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryAllRole(@ModelAttribute("roleQuery") RoleQuery roleQuery,HttpServletRequest request) throws Exception {
		DatatablesView<Role> dataTable = roleService.queryRoleList(roleQuery);
		dataTable.setDraw(roleQuery.getDraw());

		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/addRole",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addRole(@ModelAttribute("role") Role role,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("user");
		role.setCreateBy(loginUser.getUserId());
		
		return getJason(roleService.addRole(role),"添加");
	}
	
	@ResponseBody
	@RequestMapping(value="/delRole",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String delRole(@ModelAttribute("roleId") int roleId,HttpServletRequest request) throws Exception {
		
		return getJason(roleService.deleteRole(roleId),"删除");
	}
	
	@ResponseBody
	@RequestMapping(value="/updateRole",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateRole(@ModelAttribute("role") Role role,HttpServletRequest request) throws Exception {
		
		return getJason(roleService.updateRole(role),"更新");
	}
	
}

