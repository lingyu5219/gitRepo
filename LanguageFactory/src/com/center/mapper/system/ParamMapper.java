
/**
* Project Name:trainingCenter
* File Name:ParmeterMapper.java
* Package Name:com.center.mapper.system
* Date:2018年6月29日下午3:21:58
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.mapper.system;


import java.util.List;

import com.center.po.system.Param;
import com.center.po.system.ParamQuery;

/**
* ClassName:ParmeterMapper <br/>
* Function: TODO ADD 系统参数 <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月29日 下午3:21:58 <br/>
* @author donghao
* @version
* @see
*/
public interface ParamMapper {
	
	public void addParam(Param param) throws Exception;
	
	public int updateParam(Param param) throws Exception;

	public int delParam(int paramId) throws Exception;
	
	public List<Param> queryParam(ParamQuery paramQuery) throws Exception;
	
	public List<Param> queryParamList(ParamQuery paramQuery) throws Exception;

	public Long queryParamCount(ParamQuery paramQuery) throws Exception;
}

