package com.center.controller.cms;

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

import com.center.po.cms.Column;
import com.center.po.cms.ColumnQuery;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.cms.ColumnService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
* ClassName: ColumnController <br/>
* Function: 系统模块管理. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月28日 下午8:56:51 <br/>
*
* @author 叶宇航
* @version
 */
@Controller
@RequestMapping(value="/cms")
public class ColumnController {
	@Autowired
	ColumnService columnService;
	
	/**
	 * 
	* getColumns:进入模块管理页面. <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param request
	* @return
	 */
	@RequestMapping(value="/columnList",method=RequestMethod.GET)
	public String getColumns(HttpServletRequest request) throws Exception {
		return "cms/columnList";
	}
	
	@ResponseBody
	@RequestMapping(value="/queryColumnTree",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryColumnTree(@ModelAttribute("ColumnQuery") ColumnQuery columnQuery,HttpServletRequest request) throws Exception {

		// 调用service查找 数据库，查询用户
		List<Column> columnList = columnService.queryColumnTree(columnQuery);
		
		String data = JSONArray.fromObject(columnList).toString();
		return data;
	}
	
	/**
	 * 
	* queryColumn:(查询模块数据，不带分页功能). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param columnQuery
	* @param request
	* @return 返回List集合转换的json字符串
	* @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/queryColumn",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryColumn(@ModelAttribute("ColumnQuery") ColumnQuery columnQuery,HttpServletRequest request) throws Exception {

		// 调用service查找 数据库，查询用户
		List<Column> columnList = columnService.queryColumn(columnQuery);
		
		String data = JSONArray.fromObject(columnList).toString();
		return data;
	}
	
	/**
	 * 
	* showColumns:(查询模块数据，带分页功能). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param columnQuery
	* @param request
	* @param response
	* @return 返回datatable格式的json字符串
	* @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="queryColumnList", method=RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showColumns(@ModelAttribute("ColumnQuery") ColumnQuery columnQuery,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		DatatablesView<Column> dataTable = columnService.queryColumnList(columnQuery);
		dataTable.setDraw(columnQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		
		return data;
	}
	
	/**
	 * 
	* addColumn:(增加模块). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param column
	* @param request
	* @param response
	* @return
	* @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/addColumn",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addColumn(@ModelAttribute("column") Column column,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取已登录的用户Id
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		column.setCreateBy(user.getUserId());
		
		columnService.addColumn(column);
		HashMap<String,String> rsMap = new HashMap<String,String>();
		if (column.getColumnId() > 0) {
			rsMap.put("status", "1");
			rsMap.put("info", "增加成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "增加失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	/**
	 * 
	* deleteColumn:(删除模块). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param columnId
	* @param request
	* @return
	* @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/delColumn",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String deleteColumn(@ModelAttribute("columnId") int columnId,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = columnService.deleteColumnById(columnId);
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
	
	/**
	 * 
	* updateColumn:(修改模块). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param column
	* @param request
	* @return
	* @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/updateColumn",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateColumn(@ModelAttribute("column") Column column,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = columnService.updateColumn(column);
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
	/**
	 * 
	* updateColumn:(这里用一句话描述这个方法的作用). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param column
	* @param request
	* @return
	* @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/upSortColumn",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String upSortColumn(@ModelAttribute("columnId") int columnId, HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = columnService.upSortColumn(columnId);
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", "上移成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "上移失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/downSortColumn",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String downSortColumn(@ModelAttribute("columnId") int columnId, HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = columnService.downSortColumn(columnId);
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", "下移成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "下移失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
}

