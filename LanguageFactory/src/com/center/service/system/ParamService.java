
/**
* Project Name:trainingCenter
* File Name:ParmeterService.java
* Package Name:com.center.service.system
* Date:2018年6月29日下午3:41:14
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.service.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.center.po.query.DatatablesView;
import com.center.po.system.Param;
import com.center.po.system.ParamQuery;

/**
* ClassName:ParmeterService <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月29日 下午3:41:14 <br/>
* @author donghao
* @version
* @see
*/
public interface ParamService {
	public List<Param> queryParam(ParamQuery paramQuery) throws Exception;
	
	public DatatablesView<Param> queryParamList(ParamQuery paramQuery) throws Exception;
	
	public void addParam(HttpServletRequest request, Param param) throws Exception;
	
	public boolean updateParam(HttpServletRequest request, Param param) throws Exception;
	
	public boolean delParam(int paramId) throws Exception;
}

