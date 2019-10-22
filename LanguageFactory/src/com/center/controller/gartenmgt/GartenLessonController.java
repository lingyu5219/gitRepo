package com.center.controller.gartenmgt;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.groupmgt.Lesson;
import com.center.po.groupmgt.LessonQuery;
import com.center.po.hrmgt.Staff;
import com.center.po.query.DatatablesView;
import com.center.service.gartenmgt.GartenLessonService;
import com.center.service.groupmgt.LessonService;

import net.sf.json.JSONObject;

/**
 * 要选择课程，先要有课程列表
 * 一进页面，能看到以选课程，可以前端继续调集团的查询接口带上校园id条件，也可以新开接口后台读session里的校园id加上条件
 * 前端选了之后，传选了的课程id
 */
@Controller
@RequestMapping("/gartenmgt")
public class GartenLessonController {
	@Autowired
	private LessonService lessonService;
	@Autowired
	private GartenLessonService gartenLessonService;
	
	
	@RequestMapping(value = "/gartenLessonList", method = RequestMethod.GET)
	public ModelAndView lessonList(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("gartenmgt/gartenLessonList");
		return modelAndView;
	}

	/**查询已选的课程
	 * @param lq
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/queryGartenLessonList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String querySelectedLesson(LessonQuery lq,HttpSession session) {
		HashMap<String, Object> personalInfo = (HashMap<String, Object>) session.getAttribute("personalInfo");
		Staff staff = (Staff) personalInfo.get("personal");
		lq.setGartenId(staff.getStaffGartenId());
		lq.setSelectFlag(0);
		lq.setIsPage(0);
		DatatablesView<Lesson> dataTable = lessonService.getLessonList(lq);
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	/**未选的课程
	 * @param lq
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryGartenLessonListNoSelected", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String queryGartenLessonListNoSelected(LessonQuery lq,HttpSession session) {
		HashMap<String, Object> personalInfo = (HashMap<String, Object>) session.getAttribute("personalInfo");
		Staff staff = (Staff) personalInfo.get("personal");
		lq.setGartenId(staff.getStaffGartenId());
		lq.setSelectFlag(1);
		lq.setIsPage(0);
		DatatablesView<Lesson> dataTable = lessonService.getLessonList(lq);
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	/**删除已选课程
	 * @param lessonId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/deleteGartenLessonList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String deleteSelectedLesson(Integer lessonId,HttpSession session) {
		HashMap<String, Object> personalInfo = (HashMap<String, Object>) session.getAttribute("personalInfo");
		Staff staff = (Staff) personalInfo.get("personal");
		Integer result = gartenLessonService.deleteSelectedLesson(staff.getStaffGartenId(),lessonId);
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
	/**多选新增的话
	 * @param lessonId
	 * @return
	 */
	@RequestMapping(value="/addGartenLessonList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String addLesson2Selected(LessonQuery query,HttpSession session) {
		HashMap<String, Object> personalInfo = (HashMap<String, Object>) session.getAttribute("personalInfo");
		Staff staff = (Staff) personalInfo.get("personal");
		Integer gartenId = staff.getStaffGartenId();
		query.setGartenId(gartenId);
		Integer result = gartenLessonService.addLesson2Selected(query);
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
	@ResponseBody
	@RequestMapping(value = "/selectLesson", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String selectLesson(LessonQuery query,HttpSession session) {
		//获取session里的校园id
		HashMap<String, Object> personalInfo = (HashMap<String, Object>) session.getAttribute("personalInfo");
		Staff staff = (Staff) personalInfo.get("personal");
		Integer gartenId = staff.getStaffGartenId();
		//先删除
		//再新增
		query.setGartenId(gartenId);
		boolean result = gartenLessonService.selectLesson(query);
		HashMap<String, String> rsMap = new HashMap<String, String>();
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", "增加成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "增加失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
}
