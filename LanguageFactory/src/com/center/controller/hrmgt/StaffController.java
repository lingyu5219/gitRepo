
/**
* Project Name:trainingCenter
* File Name:StaffController.java
* Package Name:com.center.controller.personnel
* Date:2018年6月22日下午3:17:40
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.controller.hrmgt;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.hrmgt.Staff;
import com.center.po.hrmgt.StaffQuery;
import com.center.po.query.DatatablesView;
import com.center.service.hrmgt.StaffService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ClassName:StaffController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年6月22日 下午3:17:40 <br/>
 * 
 * @author 李逢杰
 * @version
 * @see
 */
@Controller
// 为了对url进行分类管理 ，可以在这里定义根路径，最终访问url是根路径+子路径
// 比如：查询用户：/staff/queryStaffById
@RequestMapping("/hrmgt")
public class StaffController {

	@Autowired
	private StaffService staffService;

	// 进入集团职工管理功能
	@RequestMapping(value = "/staffList", method = RequestMethod.GET)
	public ModelAndView staffList(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hrmgt/staffList");

		return modelAndView;

	}
	
	// 进入校园职工管理功能
	@RequestMapping(value = "/staffGartenList", method = RequestMethod.GET)
	public ModelAndView staffGartenList(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hrmgt/staffGartenList");

		return modelAndView;

	}

	@ResponseBody
	@RequestMapping(value = "/queryStaffByName", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryStaffByName(@ModelAttribute("staffName") String staffName, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 调用service查找 数据库，查询用户
		List<Staff> staffList = staffService.queryStaffByName(staffName);
		String data = JSONArray.fromObject(staffList).toString();
		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/queryStaffByPhone", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryStaffByPhone(@ModelAttribute("staffPhone") String staffPhone, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 调用service查找 数据库，查询用户
		List<Staff> staffList = staffService.queryStaffByPhone(staffPhone);
		String data = JSONArray.fromObject(staffList).toString();
		return data;
	}
	
	// 分页查询
	@ResponseBody
	@RequestMapping(value = "/queryStaffList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryStaffList(@ModelAttribute("staffQuery") StaffQuery staffQuery, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 调用service查找 数据库，查询用户
		DatatablesView<Staff> dataTable = staffService.queryStaffList(staffQuery);
		dataTable.setDraw(staffQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}

	// 新增教职工
	@ResponseBody
	@RequestMapping(value = "/addStaff", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addStaff(@ModelAttribute("staff") Staff staff, HttpServletRequest request) throws Exception {

		boolean rs = staffService.addStaff(staff, request);

		HashMap<String, String> rsMap = new HashMap<String, String>();
		if (rs) {
			rsMap.put("status", "1");
			rsMap.put("info", "增加成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "增加失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}

	// 删除教职工
	@ResponseBody
	@RequestMapping(value = "/delStaff", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String delStaff(@ModelAttribute("staffId") int staffId, HttpServletRequest request) throws Exception {
		HashMap<String, String> rsMap = new HashMap<String, String>();
		boolean rs = staffService.delStaff(staffId);
		if (rs) {
			rsMap.put("status", "1");
			rsMap.put("info", "删除成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "删除失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}

	// 修改教职工
	@ResponseBody
	@RequestMapping(value = "/updateStaff", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String updateStaff(@ModelAttribute("staff") Staff staff, HttpServletRequest request) throws Exception {
		HashMap<String, String> rsMap = new HashMap<String, String>();
		boolean rs = staffService.updateStaff(staff);

		if (rs) {
			//如果当前正好是登录用户修改的自己的信息，则需要更新session中的员工信息
			@SuppressWarnings("unchecked")
			HashMap<String, Object> personalInfo = (HashMap<String, Object>)request.getSession().getAttribute("personalInfo");
			Staff sessionStaff = (Staff)personalInfo.get("personal");
			if(sessionStaff.getStaffId() == staff.getStaffId()){
				Staff staffInfo = staffService.queryStaff(staff.getStaffId());
				personalInfo.put("personal", staffInfo);
			}
			rsMap.put("status", "1");
			rsMap.put("info", "更新成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "更新失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	//登录用户修改自己的员工信息
	@ResponseBody
	@RequestMapping(value = "/updateStaffInfo", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String updateStaffInfo(@ModelAttribute("staff") Staff staff, HttpServletRequest request) throws Exception {
		HashMap<String, String> rsMap = new HashMap<String, String>();
		
		ServletContext sContext = request.getSession().getServletContext();
		String root = sContext.getRealPath("/");
		staff.setStaffOriginalPic(root + staff.getStaffOriginalPic());
		
		boolean rs = staffService.updateStaff(staff);
		
		if (rs) {
			//员工信息修改成功后，更新session中员工的信息
			@SuppressWarnings("unchecked")
			HashMap<String, Object> personalInfo = (HashMap<String, Object>)request.getSession().getAttribute("personalInfo");
			Staff staffInfo = staffService.queryStaff(staff.getStaffId());
			personalInfo.put("personal", staffInfo);
			rsMap.put("status", "1");
			rsMap.put("info", "更新成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "更新失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/staffPhoneExistNotSelf",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String staffPhoneExistNotSelf(@ModelAttribute("staff") Staff staff,HttpServletRequest request) throws Exception {
		HashMap<String, Object> rsMap = new HashMap<String, Object>();
		boolean result = staffService.staffPhoneExistNotSelf(staff);
		rsMap.put("status", result);
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
}
