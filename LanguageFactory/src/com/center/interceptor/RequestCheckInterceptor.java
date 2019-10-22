package com.center.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.system.Menu;
import com.center.utils.AuthorUtil;

import net.sf.json.JSONObject;


/**
 * 
		* ClassName: UserLoginCheckInterceptor <br/>
		* Function: 检查用户发起的每一个请求是否拥有相应的权限,限制非法访问 <br/>
		* Reason: TODO ADD REASON(可选). <br/>
		* date: 2018年6月22日 上午10:35:36 <br/>
		*
		* @author Tony
		* @version
 */
public class RequestCheckInterceptor implements HandlerInterceptor {
	
	//@Autowired
	//private AuthorUtil authorUtil;
	
	/**
	 * 
			* preHandle:(这里用一句话描述这个方法的作用). <br/>
			* TODO(这里描述这个方法适用条件 – 可选).<br/>
			* TODO(这里描述这个方法的执行流程 – 可选).<br/>
			* TODO(这里描述这个方法的使用方法 – 可选).<br/>
			* TODO(这里描述这个方法的注意事项 – 可选).<br/>
			*
			* @author Tony
			* @param request
			* @param response
			* @param arg2
			* @return 
			* @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		HttpSession session = request.getSession();
		
		//判断当前请求是否是ajax请求，如果是则不能直接跳转页面，需要返回一段js实现跳转
		String header = request.getHeader("X-Requested-With");  
	    boolean isAjax = "XMLHttpRequest".equals(header) ? true:false;  
		
		//http请求路径，如果请求url存在并且有权限访问，则同时将菜单实体放入request返回
		String path = request.getServletPath().substring(1);
		session.setAttribute("relPath", path);
		String param = request.getQueryString();
		if (null != param) {
			path = path + "?" + param;
		}
		String redirectPage = "/forward?page=404";
		/*
		 * 数据库中配置的菜单需要权限验证，没有配置的菜单不需要权限验证，直接放行
		 */
		HashMap<String,Menu> menuMap = AuthorUtil.getMenuMap();
		if(!menuMap.containsKey(path)){
			return true;
		} else {
			/*
			 * 如果当前url包含在配置的菜单中，则需要判断是否登录
			 * 如果用户已登录，需要判断当前用户所请求的url是否有权限访问
			 */
			//HttpSession session = request.getSession();
			if (null != session.getAttribute("user")) {
				//用户登录成功后，已经将拥有权限的菜单放到session中，所以可以从session中取出加以判断
				@SuppressWarnings("unchecked")
				List<Menu> userMenuList = (List<Menu>)session.getAttribute("menuList");
				if(hasMenuUrl(path,userMenuList,request)){
					return true;
				} else {
					//已经登录 但是没有访问权限
					if (isAjax) {
						HashMap<String,String> rsMap = new HashMap<String,String>();
						rsMap.put("status", "3");
						rsMap.put("info", "您没有操作权限，请联系管理员");
						rsMap.put("url", path);
						response.setCharacterEncoding("utf-8");  
				        PrintWriter out = response.getWriter();  
				        out.println(JSONObject.fromObject(rsMap).toString());  
				        out.flush();
				        out.close();
				        return false;
					}
					request.getRequestDispatcher(redirectPage).forward(request, response);
					return false;
				}
			} else {
				//没有登录 需要跳转到登录页面
				if (isAjax) {
					HashMap<String,String> rsMap = new HashMap<String,String>();
					rsMap.put("status", "2");
//					rsMap.put("info", "您没有权限访问URL:"+path);
					rsMap.put("info", "请重新登录");
					response.setCharacterEncoding("utf-8");  
			        PrintWriter out = response.getWriter();
			        out.println(JSONObject.fromObject(rsMap).toString());
			        //out.println("<script>window.location.href='system/login';</script>");  
			        out.flush();
			        out.close();
			        return false;
				}
				//如果没有登录则没有权限访问当前url,跳转到登录页面
				//request.getRequestDispatcher("/system/login").forward(request, response);
				response.sendRedirect(request.getContextPath() + "/system/login");
				return false;
			}
		}
		
		
		/*
		//白名单
		if("forward?page=404".equals(path)){
			return true;
		}
		
		
		System.out.println(path);
		//1.首先从菜单表中获取public公开的菜单
		MenuQuery menuQuery = new MenuQuery();
		menuQuery.setIsPublic(1);
		List<Menu> publicMenuList = menuService.queryMenu(menuQuery);
		*/
		
		/*
		//先判断当前请求的URL是否是公开的菜单URL,如果是则直接放行，否则说明当前URL需要权限，则继续判断用户是否登录，如果没登录，则拦截，如果登录了，则判断是否拥有相应的权限
		if (hasMenuUrl(path,publicMenuList,request,response,redirectPage)){
			return true;
		} else {
			//2.检查用户是否登录
			HttpSession session = request.getSession();
			if (null != session.getAttribute("user")) {
				//如果用户已登录，需要判断当前用户所请求的url是否有权限访问
				//用户登录成功后，已经将拥有权限的菜单放到session中，所以可以从session中取出加以判断
				List<Menu> userMenuList = (List<Menu>)session.getAttribute("menuList");
				if(hasMenuUrl(path,userMenuList,request,response,redirectPage)){
					return true;
				} else {
					request.getRequestDispatcher(redirectPage).forward(request, response);
					return false;
				}
			} else {
				request.getRequestDispatcher(redirectPage).forward(request, response);
				return false;
			}
		}
		*/
	}
	
	private boolean hasMenuUrl(String path, List<Menu> menuList,HttpServletRequest request) throws Exception {
		
		for(Menu menu : menuList){
			if (path.equals(menu.getLocation())) {
				request.setAttribute("currentMenu", menu);
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	

	

}
