package com.center.controller.system;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.query.DatatablesView;
import com.center.po.system.Author;
import com.center.po.system.AuthorQuery;
import com.center.po.system.Menu;
import com.center.po.system.Module;
import com.center.po.system.User;
import com.center.service.system.AuthorService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/system")
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	private String getJason(boolean result,String info){
		HashMap<String,String> rsMap = new HashMap<String,String>();
		
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", info+"成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", info+"失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@RequestMapping(value = "/authorList", method = RequestMethod.GET)
	public ModelAndView userList(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/authorList");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/queryAuthorList",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryAuthorList(@ModelAttribute("authorQuery") AuthorQuery authorQuery,HttpServletRequest request) throws Exception {
		DatatablesView<Author> dataTable=authorService.queryAuthorList(authorQuery);
		dataTable.setDraw(authorQuery.getDraw());
		
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateAuthor",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateAuthor(@ModelAttribute("author") Author author,HttpServletRequest request) throws Exception {
		boolean rs = authorService.updateAuthor(author,request);
		if(rs){
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			//通过权限获取菜单
			List<Module> moduleList = authorService.queryModuleByRole(user.getRoleId());
			List<Menu> menuList = authorService.queryMenuByRole(user.getRoleId());
			session.setAttribute("menuList", menuList);
			session.setAttribute("menuTreeList", authorService.menu2TreeByModule(moduleList,menuList));
		}
		return getJason(rs,"更新");
	}
}
