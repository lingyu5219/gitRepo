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

import com.center.po.groupmgt.Album;
import com.center.po.groupmgt.AlbumQuery;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.AlbumService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/groupmgt")
public class AlbumController {
	@Autowired
	AlbumService albumService;
	
	@RequestMapping(value="/albumList",method=RequestMethod.GET)
	public String getAlbums(HttpServletRequest request) throws Exception {
		return "groupmgt/albumList";
	}
	
	@ResponseBody
	@RequestMapping(value="/queryAlbum",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryAlbum(@ModelAttribute("albumQuery") AlbumQuery albumQuery,HttpServletRequest request) throws Exception {

		// 调用service查找 数据库，查询用户
		List<Album> albumList = albumService.queryAlbum(albumQuery);
		
		String data = JSONArray.fromObject(albumList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="queryAlbumList", method=RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryAlbumList(@ModelAttribute("AlbumQuery") AlbumQuery albumQuery,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		DatatablesView<Album> dataTable = albumService.queryAlbumList(albumQuery);
		dataTable.setDraw(albumQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/addAlbum",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addAlbum(@ModelAttribute("album") Album album,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		boolean rs = albumService.addAlbum(album,request);
		
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
	@RequestMapping(value="/delAlbum",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String delAlbum(@ModelAttribute("albumId") int albumId,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = albumService.deleteAlbumById(albumId);
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
	@RequestMapping(value="/updateAlbum",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateAlbum(@ModelAttribute("album") Album album,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = albumService.updateAlbum(album);
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

