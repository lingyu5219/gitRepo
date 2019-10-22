
/**
* Project Name:trainingCenter
* File Name:ParamUtil.java
* Package Name:com.center.utils
* Date:2017年3月2日上午10:07:34
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package com.center.utils;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.center.po.system.Param;
import com.center.service.system.ParamService;

/**
* ClassName:ParamUtil <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年3月2日 上午10:07:34 <br/>
* @author Tony
* @version
* @see
*/
@Component
public class ParamUtil {
	
	@Autowired
	private ParamService paramService;
	
	private static HashMap<String,Param> paramMap = null;

	public HashMap<String,Param> getParamMap(){
		if (null == paramMap) {
			paramMap = new HashMap<String,Param>();
			refreshParamMap();
		}
		return paramMap;
	}
	
	public void refreshParamMap(){
		try {
			paramMap.clear();
			List<Param> paramList = paramService.queryParam(null);
			for(Param param : paramList){
				paramMap.put(param.getParamCode(), param);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

