
/**
* Project Name:trainingCenter
* File Name:StaffServiceImpl.java
* Package Name:com.center.service.impl.personnel
* Date:2018年6月22日下午3:13:23
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.service.impl.hrmgt;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.hrmgt.StaffMapper;
import com.center.po.hrmgt.Staff;
import com.center.po.hrmgt.StaffQuery;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.hrmgt.StaffService;

/**
 * ClassName:StaffServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年6月22日 下午3:13:23 <br/>
 * 
 * @author 李逢杰
 * @version
 * @see
 */
@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffMapper staffMapper;
	
	@Override
	public Staff queryStaff(int staffId) throws Exception {
		return staffMapper.queryStaff(staffId);
	}
	
	@Override
	public List<Staff> queryStaffs(StaffQuery staffQuery) throws Exception{
		return staffMapper.queryStaffs(staffQuery);
	}
	
	@Override
	public List<Staff> queryStaffByName(String staffName) throws Exception {
		return staffMapper.queryStaffByName(staffName);
	}

	@Override
	public List<Staff> queryStaffByPhone(String staffPhone) throws Exception {
		return staffMapper.queryStaffByPhone(staffPhone);
	}
	
	// 分页查询
	@Override
	public DatatablesView<Staff> queryStaffList(StaffQuery staffQuery) throws Exception {
		DatatablesView<Staff> dataView =new DatatablesView<Staff>();
		Long count = staffMapper.queryStaffCount(staffQuery);
		List<Staff> stafflist = staffMapper.queryStaffList(staffQuery);		
		
		// 调用changeBirthday方法把日期转换成年龄  并且 计数
//		for (Staff staff : stafflist) {
//			staff = changeBirthday(staff);	
//		}
		
		dataView.setData(stafflist);
		dataView.setRecordsTotal(count);
		return dataView;
	}

	// 计算年龄
	public Staff changeBirthday(Staff staff) {
		String bir = staff.getStaffBirthday();// 获取生日
		System.out.println(bir);
		bir = bir.substring(0, 4);// 获取前4位字符串--生日年份
		int b2 = Integer.parseInt(bir);// 讲生日转换成int类型

		Calendar c = Calendar.getInstance();// 创建时间格式化 方法
		c.setTime(new Date()); // 设置当前时间
		int b1 = c.get(Calendar.YEAR); // 取年份

		int b = b1 - b2;// 计算年龄

		String newbir = String.valueOf(b);// int转String

		staff.setStaffBirthday(newbir);
		return staff;
	}

	// 修改职工信息
	@Override
	public boolean updateStaff(Staff staff) throws Exception {
		//判断是否关联账号，根据账号获取userId
		/*
		String userName = staff.getUserName();
		if(null != userName && !"".equals(userName)){
			Integer userId = staffMapper.queryUserIdByName(userName);
			if (null != userId && userId > 0) {
				//检查该账号是否已经被他人关联，一个员工只能关联一个账号
				Integer relatedCount = staffMapper.isUserIdRelated(userId);
				//如果没有关联，则可以关联
				if(relatedCount <= 0){
					staff.setUserId(userId);
				}
			}
		}
		*/
		
		/*
		*/
		if(null != staff.getStaffOriginalPic() && !"".equals(staff.getStaffOriginalPic())){
			File file = new File(staff.getStaffOriginalPic());
			if(staff.getStaffPicChanged() == 1 
					&& file.exists() 
					&& !staff.getStaffOriginalPic().contains("static/images/man.png")
					){
				if(staff.getStaffPic() == null || "".equals(staff.getStaffPic())){
					staff.setStaffPic("static/images/man.png");
				}
				if(staff.getStaffPicName() == null || "".equals(staff.getStaffPicName())){
					staff.setStaffPicName("man.png");
				}
				if(staff.getStaffPicSize() == 0){
					staff.setStaffPicSize(7634);
				}
				return file.delete() && (staffMapper.updateStaff(staff) > 0);
			}
		}
		
		return staffMapper.updateStaff(staff) > 0;
		
		/*int affectedRecords = staffMapper.updateStaff(staff);
		if(affectedRecords > 0){
			return true;
		} else {
			return false;
		}*/
	}
	
	// 添加职工信息
	@Override
	public boolean addStaff(Staff staff, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User loginUser = (User)	session.getAttribute("user");
		staff.setCreateBy(loginUser.getUserId());
		
		//判断是否关联账号，根据账号获取userId
		/*
		String userName = staff.getUserName();
		if(null != userName && !"".equals(userName)){
			Integer userId = staffMapper.queryUserIdByName(userName);
			if (null != userId && userId > 0) {
				//检查该账号是否已经被他人关联，一个员工只能关联一个账号
				Integer relatedCount = staffMapper.isUserIdRelated(userId);
				//如果没有关联，则可以关联
				if(relatedCount <= 0){
					staff.setUserId(userId);
				}
			}
		}
		*/
		
		staffMapper.addStaff(staff);

		if(staff.getStaffId() > 0){
			return true;
		} else {
			return false;
		}

	}
	// 删除职工信息
	@Override
	public boolean delStaff(int staffId) throws Exception {
			
		int affectedRecords = staffMapper.delStaff(staffId);
		if(affectedRecords > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean staffPhoneExistNotSelf(Staff staff) throws Exception {
		long count = staffMapper.staffPhoneExistNotSelf(staff);
		return count > 0;
	}
}
