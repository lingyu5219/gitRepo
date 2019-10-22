/**
* Project Name:trainingCenter
* File Name:InitSystemParam.java
* Package Name:com.center.listener
* Date:2018年6月22日上午11:21:14
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.center.po.system.Param;
import com.center.utils.ParamUtil;

/**
* ClassName:InitSystemParam <br/>
* Function: 系统启动时从数据库加载系统参数进行初始化 <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月22日 上午11:21:14 <br/>
* @author Tony
* @version

* @see
*/
@Component
public class InitSystemParam implements ServletContextAware{

	@Autowired
	private ParamUtil paramUtil;
	
	@Override
	public void setServletContext(ServletContext app) {
		HashMap<String,Param> paramMap = paramUtil.getParamMap();
		app.setAttribute("paramMap", paramMap);
	}
	

}

	