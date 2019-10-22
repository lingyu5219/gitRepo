package com.center.controller.system;

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

import com.center.po.query.DatatablesView;
import com.center.po.system.Module;
import com.center.po.system.ModuleQuery;
import com.center.po.system.User;
import com.center.service.system.ModuleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
* ClassName: ModuleController <br/>
* Function: 系统模块管理. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月28日 下午8:56:51 <br/>
*
* @author 叶宇航
* @version
 */
@Controller
@RequestMapping(value="/system")
public class ModuleController {
	@Autowired
	ModuleService moduleService;
	
	/**
	 * 
	* getModules:进入模块管理页面. <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param request
	* @return
	 */
	@RequestMapping(value="/moduleList",method=RequestMethod.GET)
	public String getModules(HttpServletRequest request) throws Exception {
		return "system/moduleList";
	}
	
	/**
	 * 
	* queryModule:(查询模块数据，不带分页功能). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param moduleQuery
	* @param request
	* @return 返回List集合转换的json字符串
	* @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/queryModule",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryModule(@ModelAttribute("ModuleQuery") ModuleQuery moduleQuery,HttpServletRequest request) throws Exception {

		// 调用service查找 数据库，查询用户
		List<Module> moduleList = moduleService.queryModule(moduleQuery);
		
		String data = JSONArray.fromObject(moduleList).toString();
		return data;
	}
	
	/**
	 * 
	* showModules:(查询模块数据，带分页功能). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param moduleQuery
	* @param request
	* @param response
	* @return 返回datatable格式的json字符串
	* @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="queryModuleList", method=RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showModules(@ModelAttribute("ModuleQuery") ModuleQuery moduleQuery,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		DatatablesView<Module> dataTable = moduleService.queryModuleList(moduleQuery);
		dataTable.setDraw(moduleQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		
		return data;
	}
	
	/**
	 * 
	* addModule:(增加模块). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param module
	* @param request
	* @param response
	* @return
	* @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/addModule",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addModule(@ModelAttribute("module") Module module,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取已登录的用户Id
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		module.setCreateBy(user.getUserId());
		
		moduleService.addModule(module);
		HashMap<String,String> rsMap = new HashMap<String,String>();
		if (module.getModuleId() > 0) {
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
	* deleteModule:(删除模块). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param moduleId
	* @param request
	* @return
	* @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/delModule",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String deleteModule(@ModelAttribute("moduleId") int moduleId,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = moduleService.deleteModuleById(moduleId);
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
	* updateModule:(修改模块). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param module
	* @param request
	* @return
	* @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/updateModule",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateModule(@ModelAttribute("module") Module module,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		boolean result = moduleService.updateModule(module);
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

