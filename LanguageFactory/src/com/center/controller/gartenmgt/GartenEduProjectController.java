package com.center.controller.gartenmgt;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.groupmgt.EduProject;
import com.center.po.groupmgt.EduProjectQuery;
import com.center.po.hrmgt.Staff;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.EduProjectService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/gartenmgt")
public class GartenEduProjectController {
	
	@Autowired
	private EduProjectService eduProjectService;
	
	@RequestMapping(value="/gartenEduProjectList",method=RequestMethod.GET)
	public ModelAndView eduProjectList(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("gartenmgt/gartenEduProjectList");
		return modelAndView;
	}
	
	/**已经选的教育项目
	 * @param query
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/querySelectedEduProject", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String querySelectedEduProject(EduProjectQuery query,HttpSession session) {
		HashMap<String, Object> personalInfo = (HashMap<String, Object>) session.getAttribute("personalInfo");
		Staff staff = (Staff) personalInfo.get("personal");
		query.setGartenId(staff.getStaffGartenId());
		query.setSelectFlag(0);
		DatatablesView<EduProject> datatablesView = eduProjectService.getEduProjectList(query);
		String data = JSONObject.fromObject(datatablesView).toString();
		return data;
	}
	/**未选的教育项目
	 * @param query
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryGartenEduProjectNoSelected",method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String queryGartenEduProjectNoSelected(EduProjectQuery query,HttpSession session) {
		HashMap<String, Object> personalInfo = (HashMap<String, Object>) session.getAttribute("personalInfo");
		Staff staff = (Staff) personalInfo.get("personal");
		query.setGartenId(staff.getStaffGartenId());
		query.setSelectFlag(1);
		DatatablesView<EduProject> datatablesView = eduProjectService.getEduProjectList(query);
		String data = JSONObject.fromObject(datatablesView).toString();
		return data;
	}
	/**删除已选的
	 * @param eduProjectId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/deleteSeletedEduroject",method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String deleteSeletedEduproject(Integer eduProjectId,HttpSession session) {
		HashMap<String, Object> personalInfo = (HashMap<String, Object>) session.getAttribute("personalInfo");
		Staff staff = (Staff) personalInfo.get("personal");
		Integer result = eduProjectService.deleteSeletedEduProject(staff.getStaffGartenId(),eduProjectId);
		HashMap<String, String> rsMap = new HashMap<String, String>();
		if (result>0) {
			rsMap.put("status", "1");
			rsMap.put("info", "删除成功");
		}else {
			rsMap.put("status", "0");
			rsMap.put("info", "删除失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	@RequestMapping(value="/addGartenEduProjectList",method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String addEduProject2Selected(EduProjectQuery query,HttpSession session) {
		HashMap<String, Object> personalInfo = (HashMap<String, Object>) session.getAttribute("personalInfo");
		Staff staff = (Staff) personalInfo.get("personal");
		Integer gartenId = staff.getStaffGartenId();
		query.setGartenId(gartenId);
		Integer result = eduProjectService.addEduProject2Selected(query);
		HashMap<String, String> rsMap = new HashMap<String, String>();
		if (result>0) {
			rsMap.put("status", "1");
			rsMap.put("info", "新增成功");
		}else {
			rsMap.put("status", "0");
			rsMap.put("info", "新增失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
}
