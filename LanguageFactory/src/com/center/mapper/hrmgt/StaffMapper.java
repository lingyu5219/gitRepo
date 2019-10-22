
/**
* Project Name:trainingCenter
* File Name:StaffMapper.java
* Package Name:com.center.mapper.personnel
* Date:2018年6月22日下午3:04:17
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.mapper.hrmgt;

import java.util.List;

import com.center.po.hrmgt.Staff;
import com.center.po.hrmgt.StaffQuery;

/**
 * ClassName:StaffMapper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年6月22日 下午3:04:17 <br/>
 * 
 * @author 李逢杰
 * @version
 * @see
 */
public interface StaffMapper {
	
	public Staff queryStaff(int staffId) throws Exception;

	public List<Staff> queryStaffs(StaffQuery staffQuery) throws Exception;
	
	public List<Staff> queryStaffByName(String staffName) throws Exception;
	
	public List<Staff> queryStaffByPhone(String staffPhone) throws Exception;
	
	public List<Staff> queryStaffList(StaffQuery staffQuery) throws Exception;

	public Long queryStaffCount(StaffQuery staffQuery) throws Exception;
	
	public Integer queryUserIdByName(String userName) throws Exception;
	
	public Integer isUserIdRelated(Integer userId) throws Exception;
	
	public int addStaff(Staff staff) throws Exception;
	
	public int delStaff(int staffId) throws Exception;
	
	public int updateStaff(Staff staff) throws Exception;

	public long staffPhoneExistNotSelf(Staff staff) throws Exception;
}
