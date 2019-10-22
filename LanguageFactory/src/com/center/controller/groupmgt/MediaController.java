package com.center.controller.groupmgt;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.center.po.groupmgt.Media;
import com.center.po.groupmgt.MediaQuery;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.MediaService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/groupmgt")
public class MediaController {
	@Autowired
	MediaService mediaService;
	
	//集团媒体资源管理功能
	@RequestMapping(value="/mediaList",method=RequestMethod.GET)
	public String mediaList(HttpServletRequest request) throws Exception {
		return "groupmgt/mediaList";
	}
	
	@ResponseBody
	@RequestMapping(value="queryMediaList", method=RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryMediaList(@ModelAttribute("MediaQuery") MediaQuery mediaQuery,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		DatatablesView<Media> dataTable = mediaService.queryMediaList(mediaQuery);
		dataTable.setDraw(mediaQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/addMedia",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addMedia(@ModelAttribute("media") Media media,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rs = mediaService.addMedia(media,request);
		
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
	@RequestMapping(value="/delMedia",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String delMedia(@ModelAttribute("mediaId") int mediaId,@ModelAttribute("mediaUrl") String mediaUrl,
			HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		
		ServletContext sContext = request.getSession().getServletContext();
		String root = sContext.getRealPath("/");
		boolean result = mediaService.delMedia(mediaId,root + mediaUrl);
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
	@RequestMapping(value="/updateMedia",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateMedia(@ModelAttribute("media") Media media,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		
		ServletContext sContext = request.getSession().getServletContext();
		String root = sContext.getRealPath("/");
		media.setMediaOriginalUrl(root + media.getMediaOriginalUrl());
		media.setMediaCoverOriginalUrl(root + media.getMediaCoverOriginalUrl());
		
		boolean result = mediaService.updateMedia(media);
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

