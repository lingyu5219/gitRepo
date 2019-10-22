
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
import com.center.po.system.Menu;
import com.center.po.system.MenuQuery;
import com.center.po.system.Module;
import com.center.po.system.User;
import com.center.service.system.MenuService;
import com.center.utils.AuthorUtil;

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
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	//@Autowired
	//private AuthorUtil authorUtil;
	
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
	
	@RequestMapping(value = "/menuList", method = RequestMethod.GET)
	public ModelAndView userList(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/menuList");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryMenu",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryMenu(@ModelAttribute("menuQuery") MenuQuery menuQuery,HttpServletRequest request) throws Exception {
		List<Menu> menuList = menuService.queryMenu(menuQuery);
		
		String data = JSONArray.fromObject(menuList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryMenuTree",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryMenuTree(@ModelAttribute("menuQuery") MenuQuery menuQuery,HttpServletRequest request) throws Exception {
		List<Module> moduleList = menuService.queryMenuTree();
		
		String data = JSONArray.fromObject(moduleList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryMenuList",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryMenuList(@ModelAttribute("menuQuery") MenuQuery menuQuery,HttpServletRequest request) throws Exception {
		DatatablesView<Menu> dataTable = menuService.queryMenuList(menuQuery);
		dataTable.setDraw(new Menu().getDraw());
		
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/addMenu",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addMenu(@ModelAttribute("menu") Menu menu,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		menu.setCreateBy(user.getUserId());
		boolean rs = menuService.addMenu(menu);
		if(rs){
			AuthorUtil.refreshMenuMap();
		}
		return getJason(rs,"添加");
	}
	
	@ResponseBody
	@RequestMapping(value="/delMenu",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String deleteMenu(@ModelAttribute("menuId")int menuId,HttpServletRequest request) throws Exception {
		boolean rs = menuService.deleteMenu(menuId);
		if(rs){
			AuthorUtil.refreshMenuMap();
		}
		return getJason(rs,"删除");
	}
	
	@ResponseBody
	@RequestMapping(value="/updateMenu",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateMenu(@ModelAttribute("menu") Menu menu,HttpServletRequest request) throws Exception {
		boolean rs = menuService.updateMenu(menu);
		if(rs){
			AuthorUtil.refreshMenuMap();
		}
		return getJason(rs,"更新");
	}
	
}

