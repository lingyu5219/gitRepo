package com.center.controller.orgmgt;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.center.po.orgmgt.Region;
import com.center.po.orgmgt.RegionQuery;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.orgmgt.RegionService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/orgmgt")
public class RegionController {
	@Autowired
	RegionService regionService;
	
	@RequestMapping(value="/regionList",method=RequestMethod.GET)
	public String regionList(HttpServletRequest request) throws Exception {
		return "orgmgt/regionList";
	}
	
	@ResponseBody
	@RequestMapping(value="/queryRegionTree",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryRegionTree(@ModelAttribute("RegionQuery") RegionQuery regionQuery,HttpServletRequest request) throws Exception {

		// 调用service查找 数据库，查询用户
		List<Region> regionList = regionService.queryRegionTree(regionQuery);
		
		String data = JSONArray.fromObject(regionList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryRegion",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryRegion(@ModelAttribute("RegionQuery") RegionQuery regionQuery,HttpServletRequest request) throws Exception {

		// 调用service查找 数据库，查询用户
		List<Region> regionList = regionService.queryRegion(regionQuery);
		
		String data = JSONArray.fromObject(regionList).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="queryRegionList", method=RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryRegionList(@ModelAttribute("RegionQuery") RegionQuery regionQuery,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		DatatablesView<Region> dataTable = regionService.queryRegionList(regionQuery);
		dataTable.setDraw(regionQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/addRegion",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addRegion(@ModelAttribute("region") Region region,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取已登录的用户Id
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		region.setCreateBy(user.getUserId());
		
		regionService.addRegion(region);
		HashMap<String,String> rsMap = new HashMap<String,String>();
		if (region.getRegionId() > 0) {
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
	@RequestMapping(value="/delRegion",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String delRegion(@ModelAttribute("regionId") int regionId,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = regionService.delRegion(regionId);
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
	@RequestMapping(value="/updateRegion",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateRegion(@ModelAttribute("region") Region region,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = regionService.updateRegion(region);
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

