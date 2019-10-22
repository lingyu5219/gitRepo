
/**
* Project Name:trainingCenter
* File Name:ParmeterServiceImpl.java
* Package Name:com.center.service.impl.system
* Date:2018年6月29日下午3:42:56
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.service.impl.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.system.ParamMapper;
import com.center.po.query.DatatablesView;
import com.center.po.system.Param;
import com.center.po.system.ParamQuery;
import com.center.po.system.User;
import com.center.service.system.ParamService;

/**
* ClassName:ParmeterServiceImpl <br/>
* Function: TODO ADD 系统参数. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月29日 下午3:42:56 <br/>
* @author donghao
* @version
* @see
*/
@Service
public class ParamServiceImpl implements ParamService {

	@Autowired
	private ParamMapper paramMapper;
	
	@Override
	public void addParam(HttpServletRequest request, Param param) throws Exception{
		HttpSession session = request.getSession();
		User loginUser = (User)	session.getAttribute("user");
		param.setCreateBy(loginUser.getUserId());
		paramMapper.addParam(param);
	}

	@Override
	public boolean updateParam(HttpServletRequest request, Param param) throws Exception{
		int affectedRecords = paramMapper.updateParam(param);
		
		if(affectedRecords > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delParam(int paramId) throws Exception{
		int affectedRecords = paramMapper.delParam(paramId);
		if(affectedRecords > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public DatatablesView<Param> queryParamList(ParamQuery paramQuery) throws Exception{
		DatatablesView<Param> datatablesView = new DatatablesView<Param>();
		datatablesView.setData(paramMapper.queryParamList(paramQuery));
		datatablesView.setRecordsTotal(paramMapper.queryParamCount(paramQuery));
		return datatablesView;
	}

	@Override
	public List<Param> queryParam(ParamQuery paramQuery) throws Exception{
		return paramMapper.queryParam(paramQuery);
	}

}

