
/**
* Project Name:trainingCenter
* File Name:PositionServiceImpl.java
* Package Name:com.center.service.impl.hrmgt
* Date:2018年6月26日下午3:15:31
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.service.impl.hrmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.hrmgt.PositionMapper;
import com.center.po.hrmgt.Position;
import com.center.po.hrmgt.PositionQuery;
import com.center.po.query.DatatablesView;
import com.center.po.system.Notice;
import com.center.po.system.User;
import com.center.service.hrmgt.PositionService;

/**
 * ClassName:PositionServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年6月26日 下午3:15:31 <br/>
 * 
 * @author 李逢杰
 * @version
 * @see
 */
@Service
public class PositionServiceImpl implements PositionService {
	@Autowired
	private PositionMapper positionMapper;

	@Override
	public List<Position> queryPosition(PositionQuery positionQuery) throws Exception {
		return positionMapper.queryPosition(positionQuery);
	}

	@Override
	public DatatablesView<Position> queryPositionList(PositionQuery positionQuery) throws Exception {
		DatatablesView<Position> datatablesView = new DatatablesView<Position>();
		List<Position> positionList = positionMapper.queryPositionList(positionQuery);
		Long count = positionMapper.queryPositionCount(positionQuery);
		datatablesView.setData(positionList);
		datatablesView.setRecordsTotal(count);
		return datatablesView;
	}

	@Override
	public boolean addPosition(HttpServletRequest request,Position position) throws Exception {
		HttpSession session = request.getSession();
		User loginUser = (User)	session.getAttribute("user");
		position.setCreateBy(loginUser.getUserId());
		positionMapper.addPosition(position);
		if(position.getPositionId() > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePosition(int positionId) throws Exception {
		int affectedRecords = positionMapper.delPosition(positionId);
		if(affectedRecords > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updatePosition(Position position) throws Exception {
		int affectedRecords = positionMapper.updatePosition(position);
		
		if(affectedRecords > 0){
			return true;
		} else {
			return false;
		}
	}

	

}
