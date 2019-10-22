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

import com.center.po.hrmgt.Staff;
import com.center.po.query.DatatablesView;
import com.center.po.system.Menu;
import com.center.po.system.Module;
import com.center.po.system.User;
import com.center.po.system.UserQuery;
import com.center.service.hrmgt.StaffService;
import com.center.service.system.AuthorService;
import com.center.service.system.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/system")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private AuthorService authorityService;
	
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
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView userLogin(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//首先检测当前用户是否已经登录过了，如果登录则直接跳转到管理中心
		User loginUser = (User)request.getSession().getAttribute("user");
		if(null != loginUser){
			modelAndView.setViewName("redirect:/admin");
			return modelAndView;
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView checkLogin(@ModelAttribute("UserQuery") UserQuery userQuery,HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		//如果登录成功，从数据库获取相管权限的菜单放入session，并且跳转到登录界面
		User user = userService.checkUser(userQuery);
		if(null != user){
			//关联当前账户的信息
			Staff staff = staffService.queryStaff(user.getStaffId());
			HashMap<String, Object> personalInfo = new HashMap<String,Object>();
			if(null != staff){
				personalInfo.put("flag", "2");
				personalInfo.put("personal", staff);
			}
			
			//将用户和个人信息放入session
			session.setAttribute("user", user);
			session.setAttribute("personalInfo", personalInfo);
			//通过权限获取菜单
			List<Module> moduleList = authorityService.queryModuleByRole(user.getRoleId());
			List<Menu> menuList = authorityService.queryMenuByRole(user.getRoleId());
			session.setAttribute("menuList", menuList);
			session.setAttribute("menuTreeList", authorityService.menu2TreeByModule(moduleList,menuList));
			//跳转到登录界面
			modelAndView.setViewName("redirect:/admin");
		} else {
			//返回登录界面
			request.setAttribute("info", "账号或密码错误");
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		if (null != session.getAttribute("user")){
			//session.removeAttribute("user");
			session.invalidate();
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/system/login");

		return modelAndView;
	}
	
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public ModelAndView userList(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/userList");

		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/staffIsRelatedByUser",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String staffIsRelatedByUser(@ModelAttribute("staffId") int staffId,HttpServletRequest request) throws Exception {
		HashMap<String, Object> rsMap = new HashMap<String, Object>();
		boolean result = userService.staffIsRelatedByUser(staffId);
		rsMap.put("status", result);
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/staffIsRelatedByUserNotSelf",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String staffIsRelatedByUserNotSelf(@ModelAttribute("userQuery") UserQuery userQuery,HttpServletRequest request) throws Exception {
		HashMap<String, Object> rsMap = new HashMap<String, Object>();
		boolean result = userService.staffIsRelatedByUserNotSelf(userQuery);
		rsMap.put("status", result);
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/userNameExist",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String userNameExist(@ModelAttribute("userName") String userName,HttpServletRequest request) throws Exception {
		HashMap<String, Object> rsMap = new HashMap<String, Object>();
		boolean result = userService.userNameExist(userName);
		rsMap.put("status", result);
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/userNameExistNotSelf",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String userNameExistNotSelf(@ModelAttribute("userQuery") UserQuery userQuery,HttpServletRequest request) throws Exception {
		HashMap<String, Object> rsMap = new HashMap<String, Object>();
		boolean result = userService.userNameExistNotSelf(userQuery);
		rsMap.put("status", result);
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryUserList",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryUserList(@ModelAttribute("UserQuery") UserQuery userQuery,HttpServletRequest request) throws Exception {
        
		DatatablesView<User> dataTable = userService.queryUserList(userQuery);
		dataTable.setDraw(userQuery.getDraw());

		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/addUser",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addUser(@ModelAttribute("User") User user,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("user");
		user.setCreateBy(loginUser.getUserId());
		
		return getJason(userService.addUser(user),"添加");
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/delUser",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String delUser(@ModelAttribute("userId") int userId,HttpServletRequest request) throws Exception {
		
		return getJason(userService.delUser(userId),"删除");
	}
	
	@ResponseBody
	@RequestMapping(value="/updateUser",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateUser(@ModelAttribute("User") User user,HttpServletRequest request) throws Exception {
		
		return getJason(userService.updateUser(user),"更新");
	}
	
	@ResponseBody
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updatePassword(@ModelAttribute("User") User user,HttpServletRequest request) throws Exception {
		
		return userService.updatePassword(user,request);
	}
}
