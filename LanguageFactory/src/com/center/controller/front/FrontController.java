package com.center.controller.front;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.center.po.cms.Column;
import com.center.po.cms.ColumnQuery;
import com.center.po.cms.Content;
import com.center.po.cms.ContentQuery;
import com.center.po.groupmgt.ClassType;
import com.center.po.groupmgt.Comments;
import com.center.po.groupmgt.EduProject;
import com.center.po.groupmgt.EduProjectQuery;
import com.center.po.groupmgt.History;
import com.center.po.groupmgt.HistoryQuery;
import com.center.po.groupmgt.Lesson;
import com.center.po.groupmgt.LessonQuery;
import com.center.po.groupmgt.LessonType;
import com.center.po.groupmgt.Media;
import com.center.po.groupmgt.MediaQuery;
import com.center.po.groupmgt.Message;
import com.center.po.groupmgt.MessageQuery;
import com.center.po.groupmgt.Stu;
import com.center.po.hrmgt.Staff;
import com.center.po.hrmgt.StaffQuery;
import com.center.po.orgmgt.Garten;
import com.center.po.orgmgt.GartenQuery;
import com.center.po.query.DatatablesView;
import com.center.service.cms.ColumnService;
import com.center.service.cms.ContentService;
import com.center.service.groupmgt.ClassTypeService;
import com.center.service.groupmgt.CommentsService;
import com.center.service.groupmgt.EduProjectService;
import com.center.service.groupmgt.HistoryService;
import com.center.service.groupmgt.LessonService;
import com.center.service.groupmgt.LessonTypeService;
import com.center.service.groupmgt.MediaService;
import com.center.service.groupmgt.MessageService;
import com.center.service.groupmgt.StuService;
import com.center.service.hrmgt.StaffService;
import com.center.service.orgmgt.GartenService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/front")
public class FrontController {
	@Autowired
	private ColumnService columnService;
	@Autowired
	private GartenService gartenService;
	@Autowired
	private ContentService contentService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private MediaService mediaService;
	@Autowired
	private CommentsService commentsService;
	@Autowired
	private StuService stuService;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private LessonTypeService lessonTypeService;
	@Autowired
	private EduProjectService eduProjectService;
	@Autowired
	private ClassTypeService classTypeService;
	
	
	@RequestMapping(value="/{page}")
	public String forward(@PathVariable String page,HttpServletRequest request) throws Exception {
		return "front/" + page;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryColumn",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryColumn(@ModelAttribute("columnQuery") ColumnQuery columnQuery,HttpServletRequest request) throws Exception {

		// 调用service查找 数据库，查询用户
		List<Column> columnList = columnService.queryColumnTree(columnQuery);
		
		String data = JSONArray.fromObject(columnList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryColumnById", method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryColumnById(@ModelAttribute("columnId")int columnId,HttpServletRequest request) throws Exception {
		Column column = columnService.queryColumnById(columnId);
		String data = JSONObject.fromObject(column).toString();
		return data;
	}
	
	@RequestMapping(value="/gartenSearch", method=RequestMethod.POST)
	public String gartenSearch(@ModelAttribute("gartenName")String gartenName,HttpServletRequest request,Model model) throws Exception {
		model.addAttribute("gartenName",gartenName);
		return "front/gartenList";
	}
	
	@ResponseBody
	@RequestMapping(value="/queryGartenList", method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryGartenList(@ModelAttribute("gartenQuery")GartenQuery gartenQuery,HttpServletRequest request) throws Exception {
		DatatablesView<Garten> dataTable= gartenService.queryGartenList(gartenQuery);
		dataTable.setDraw(gartenQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@RequestMapping(value="/content/{id}")
	public String content(@PathVariable int id,HttpServletRequest request,Model model) throws Exception {
		Content content = contentService.queryContent(id);
		if(null != content && 2 == content.getContentState()){
			model.addAttribute("content", content);
		}
		return "front/content";
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryContentById", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryContentById(@ModelAttribute("contentId") int contentId, HttpServletRequest request) throws Exception {
		
		Content content = contentService.queryContent(contentId);
		
		String data = JSONObject.fromObject(content).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryLessonTypeById", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryLessonTypeById(@ModelAttribute("lessonTypeId") int lessonTypeId, HttpServletRequest request) throws Exception {
		
		LessonType lessonType = lessonTypeService.queryLessonTypeById(lessonTypeId);
		
		String data = JSONObject.fromObject(lessonType).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryContentList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryContentList(@ModelAttribute("contentQuery") ContentQuery contentQuery, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DatatablesView<Content> dataTable = contentService.queryContentList(contentQuery);
		dataTable.setDraw(contentQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	
	// 分页查询
	@ResponseBody
	@RequestMapping(value = "/queryMessageList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryMessageList(@ModelAttribute("messageQuery") MessageQuery messageQuery, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 调用service查找 数据库，查询用户
		DatatablesView<Message> dataTable = messageService.queryMessageList(messageQuery);
		dataTable.setDraw(messageQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryHistory", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryHistoryList(@ModelAttribute("historyQuery") HistoryQuery historyQuery, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<History> historyList = historyService.queryHistory(historyQuery);
		
		String data = JSONArray.fromObject(historyList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryStaffs", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryStaffs(@ModelAttribute("staffQuery") StaffQuery staffQuery, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Staff> staffList = staffService.queryStaffs(staffQuery);
		
		String data = JSONArray.fromObject(staffList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryMediaList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryMediaList(@ModelAttribute("mediaQuery") MediaQuery mediaQuery, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DatatablesView<Media> dataTable = mediaService.queryMediaList(mediaQuery);
		dataTable.setDraw(mediaQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryMedia", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryMedia(@ModelAttribute("mediaQuery") MediaQuery mediaQuery, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Media> mediaList = mediaService.queryMedia(mediaQuery);
		
		String data = JSONArray.fromObject(mediaList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryLesson", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryLesson(@ModelAttribute("lessonQuery") LessonQuery lessonQuery, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Lesson> lessonList = lessonService.queryLesson(lessonQuery);
		
		String data = JSONArray.fromObject(lessonList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryLessonByGartenId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryLessonByGartenId(@ModelAttribute("gartenId") int gartenId, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Lesson> lessonList = lessonService.queryLessonByGartenId(gartenId);
		
		String data = JSONArray.fromObject(lessonList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryClassTypeByGartenId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryClassTypeByGartenId(@ModelAttribute("gartenId") int gartenId, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<ClassType> classTypeList = classTypeService.queryClassTypeByGartenId(gartenId);
		
		String data = JSONArray.fromObject(classTypeList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryEduProjects",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryEduProjects(@ModelAttribute("eduProjectQuery") EduProjectQuery eduProjectQuery,HttpServletRequest request) throws Exception {

		// 调用service查找 数据库，查询用户
		List<EduProject> eduProjectList = eduProjectService.queryEduProjects(eduProjectQuery);
		
		String data = JSONArray.fromObject(eduProjectList).toString();
		return data;
	}
	
	@RequestMapping(value="/msgDetail/{id}")
	public String msgDetail(@PathVariable int id,HttpServletRequest request,Model model) throws Exception {
		String page = "front/msgDetail";
		Message message = messageService.queryMessageById(id);
		model.addAttribute("message", message);
		if(message.getMsgPattern() == 2){
			page = "front/msgVideoDetail";
		}
		return page;
	}
	
	@RequestMapping(value="/gartenDetail/{id}")
	public String gartenDetail(@PathVariable int id,HttpServletRequest request,Model model) throws Exception {
		//根据校园ID获取校园信息
		Garten garten = gartenService.queryGartenById(id);
		//根据校园ID获取校园的新闻活动
		
		//根据校园ID获取园长的头像信息
		//根据校园ID获取校园有哪些课程
		
		//根据校园ID获取校园有哪些班级类型
		
		//根据校园ID获取校园的前端展示图片
		model.addAttribute("garten", garten);
		return "front/gartenDetail";
	}
	
	@ResponseBody
	@RequestMapping(value="/addComments",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addComments(@ModelAttribute("comments") Comments comments,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rs = commentsService.addComments(comments,request);
		
		HashMap<String,String> rsMap = new HashMap<String,String>();
		if (rs) {
			rsMap.put("status", "1");
			rsMap.put("info", "留言成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "留言失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/addStu", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addStu(Stu student,HttpServletRequest request) throws Exception {
		boolean result = stuService.addStu(student, request);
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
