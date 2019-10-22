
/**
* Project Name:trainingCenter
* File Name:PositionService.java
* Package Name:com.center.service.hrmgt
* Date:2018年6月26日下午3:14:06
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.service.hrmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.center.po.hrmgt.Position;
import com.center.po.hrmgt.PositionQuery;
import com.center.po.query.DatatablesView;

/**
 * ClassName:PositionService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年6月26日 下午3:14:06 <br/>
 * 
 * @author 李逢杰
 * @version
 * @see
 */
public interface PositionService {

	public List<Position> queryPosition(PositionQuery positionQuery) throws Exception;
	// 查看全部或个人
	public DatatablesView<Position> queryPositionList(PositionQuery positionQuery) throws Exception;
	// 添加职位信息
	public boolean addPosition(HttpServletRequest request, Position position) throws Exception;
	// 删除职位信息
	public boolean deletePosition(int positionId) throws Exception;
	// 修改职位信息
	public boolean updatePosition(Position position)throws Exception;
	
}
