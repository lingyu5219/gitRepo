
/**
* Project Name:eduSystem
* File Name:SessionInterceptor.java
* Package Name:com.center.interceptor
* Date:2018年5月24日下午10:23:27
* Copyright (c) 2018, Tony All Rights Reserved.
*
*/

/**
* Project Name:eduSystem
* File Name:SessionInterceptor.java
* Package Name:com.center.interceptor
* Date:2018年5月24日下午10:23:27
* Copyright (c) 2018, Tony All Rights Reserved.
*
*/

package com.center.interceptor;
/**
* ClassName:SessionInterceptor <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年5月24日 下午10:23:27 <br/>
* @author Tony
* @version
* @see
*/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName: SessionInterceptor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2018年5月24日 下午10:23:27 <br/>
 *
 * @author Tony
 * @version
 */

public class SessionInterceptor implements HandlerInterceptor {

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      java.lang.Exception)
	 */

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

		// TODO Auto-generated method stub

	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.web.servlet.ModelAndView)
	 */

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

		// TODO Auto-generated method stub

	}

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	*/

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		HttpSession session=request.getSession(true); 
		//session中获取用户名信息 
		Object obj = session.getAttribute("frontColumns"); 
		if (obj == null || "".equals(obj.toString())) {
			
		}
		return true;
	}

}
