
/**
* Project Name:trainingCenter
* File Name:PositionMapper.java
* Package Name:com.center.mapper.hrmgt
* Date:2018年6月26日下午3:08:26
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.mapper.hrmgt;

import java.util.List;

import com.center.po.hrmgt.Position;
import com.center.po.hrmgt.PositionQuery;

/**
 * ClassName:PositionMapper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年6月26日 下午3:08:26 <br/>
 * 
 * @author 李逢杰
 * @version
 * @see
 */
public interface PositionMapper {

	public List<Position> queryPosition(PositionQuery positionQuery) throws Exception;
	
	public List<Position> queryPositionList(PositionQuery positionQuery) throws Exception;

	public Long queryPositionCount(PositionQuery positionQuery)throws Exception;
	
	public int addPosition(Position position) throws Exception;

	public int delPosition(int positionId) throws Exception;
	
	public int updatePosition(Position position) throws Exception;

}
