package com.center.controller.cms;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.cms.Content;
import com.center.po.cms.ContentQuery;
import com.center.po.query.DatatablesView;
import com.center.service.cms.ContentService;

import net.sf.json.JSONObject;
/**
 * 
* ClassName: ContentController <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月27日 上午1:19:24 <br/>
*
* @author donghao
* @version
 */
@Controller
@RequestMapping("/cms")
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value = "/contentList", method = RequestMethod.GET)
	public ModelAndView contentList(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cms/contentList");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryContentList",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryContentList(@ModelAttribute("contentQuery") ContentQuery contentQuery,HttpServletRequest request) throws Exception{
		DatatablesView<Content> dataTable = contentService.queryContentList(contentQuery);
		dataTable.setDraw(contentQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/addContent",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addContent(HttpServletRequest request, @ModelAttribute("content") Content content) throws Exception{
		boolean rs = contentService.addContent(request,content);
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
	@RequestMapping(value="/publishContent",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String publishContent(@ModelAttribute("content") Content content) throws Exception{
		boolean result = contentService.updateContent(content);
		HashMap<String,String> rsMap = new HashMap<String,String>();
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", "发布成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "发布失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateContent",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateContent(@ModelAttribute("content") Content content) throws Exception{
		HashMap<String,String> rsMap = new HashMap<String,String>();
		//只有公告状态为未发布1时，可以修改
		if(content.getContentState() == 1) {
			boolean result = contentService.updateContent(content);
			if (result) {
				rsMap.put("status", "1");
				rsMap.put("info", "更新成功");
			} else {
				rsMap.put("status", "0");
				rsMap.put("info", "更新失败");
			}
			String data = JSONObject.fromObject(rsMap).toString();
			return data;
		}else{
			rsMap.put("status", "0");
			rsMap.put("info", "当前内容已发布，不可修改");
			String data = JSONObject.fromObject(rsMap).toString();
			return data;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/delContent",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String delContent(@ModelAttribute("contentId") int contentId) throws Exception{
		HashMap<String,String> rsMap = new HashMap<String,String>();
		//只有公告状态为未发布1时，可以修改
		Content content = contentService.queryContent(contentId);
		if(content.getContentState() == 1) {
			boolean result = contentService.delContent(contentId);
			
			if (result) {
				rsMap.put("status", "1");
				rsMap.put("info", "删除成功");
			} else {
				rsMap.put("status", "0");
				rsMap.put("info", "删除失败");
			}
			String data = JSONObject.fromObject(rsMap).toString();
			return data;
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "当前公告已发布，不可删除");
			String data = JSONObject.fromObject(rsMap).toString();
			return data;
		}
	}
	
}
