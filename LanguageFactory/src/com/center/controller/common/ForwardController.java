package com.center.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.system.User;

@Controller
//RESTful风格的URL参数
public class ForwardController {
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");

		return modelAndView;
	}
	
	/**
	 * 
	* admin:进入管理界面. <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param request
	* @return
	* @throws Exception
	 */
	@RequestMapping("/admin")
	public ModelAndView admin(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		//如果当前用户已经登录，则进入管理界面，否则跳转到登录页面
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("user");
		if(null != loginUser){
			modelAndView.setViewName("admin");
		} else{
			modelAndView.setViewName("redirect:/system/login");
		}
		return modelAndView;
	}
	
	@RequestMapping("/forward")
	public ModelAndView forward(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(page);

		return modelAndView;
	}
	
}
