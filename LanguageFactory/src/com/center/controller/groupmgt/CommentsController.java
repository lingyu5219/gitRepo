package com.center.controller.groupmgt;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.center.po.groupmgt.Comments;
import com.center.po.groupmgt.CommentsQuery;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.CommentsService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/groupmgt")
public class CommentsController {
	@Autowired
	CommentsService commentsService;
	
	@RequestMapping(value="/commentsList",method=RequestMethod.GET)
	public String commentsList(HttpServletRequest request) throws Exception {
		return "groupmgt/commentsList";
	}
	
	@ResponseBody
	@RequestMapping(value="/queryComments",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryComments(@ModelAttribute("commentsQuery") CommentsQuery commentsQuery,HttpServletRequest request) throws Exception {

		// 调用service查找 数据库，查询用户
		List<Comments> commentsList = commentsService.queryComments(commentsQuery);
		
		String data = JSONArray.fromObject(commentsList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="queryCommentsList", method=RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryCommentsList(@ModelAttribute("CommentsQuery") CommentsQuery commentsQuery,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		DatatablesView<Comments> dataTable = commentsService.queryCommentsList(commentsQuery);
		dataTable.setDraw(commentsQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/addComments",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addComments(@ModelAttribute("comments") Comments comments,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rs = commentsService.addComments(comments,request);
		
		HashMap<String,String> rsMap = new HashMap<String,String>();
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
	
	@ResponseBody
	@RequestMapping(value="/delComments",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String delComments(@ModelAttribute("commentsId") int commentsId,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = commentsService.delComments(commentsId);
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", "删除成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "删除失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateComments",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateComments(@ModelAttribute("comments") Comments comments,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = commentsService.updateComments(comments);
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", "修改成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "修改失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
}

