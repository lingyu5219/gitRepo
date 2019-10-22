
/**
* Project Name:trainingCenter
* File Name:StaffService.java
* Package Name:com.center.service.personnel
* Date:2018年6月22日下午3:11:28
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.service.hrmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.center.po.hrmgt.Staff;
import com.center.po.hrmgt.StaffQuery;
import com.center.po.query.DatatablesView;

/**
* ClassName:StaffService <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月22日 下午3:11:28 <br/>
* @author 李逢杰
* @version
* @see
*/
public interface StaffService {

	public List<Staff> queryStaffs(StaffQuery staffQuery) throws Exception;

	public List<Staff> queryStaffByName(String staffName) throws Exception;

	public List<Staff> queryStaffByPhone(String staffPhone) throws Exception;
	
	public Staff queryStaff(int staffId) throws Exception;
	//分页查询
	public DatatablesView<Staff> queryStaffList(StaffQuery staffQuery) throws Exception;
	
	// 添加教职工信息
	public boolean addStaff(Staff staff,HttpServletRequest request) throws Exception;

	// 修改职工信息
	public boolean updateStaff(Staff staff) throws Exception;
	
	// 删除教职工信息
	public boolean delStaff(int staffId) throws Exception;
	
	//修改时检查电话号码是否已存在，需要排除自身
	public boolean staffPhoneExistNotSelf(Staff staff) throws Exception;

}

