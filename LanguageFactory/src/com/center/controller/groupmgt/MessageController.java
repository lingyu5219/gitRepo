
package com.center.controller.groupmgt;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.groupmgt.Message;
import com.center.po.groupmgt.MessageQuery;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.MessageService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/groupmgt")
public class MessageController {

	@Autowired
	private MessageService messageService;

	// 进入职工管理功能
	@RequestMapping(value = "/messageList", method = RequestMethod.GET)
	public ModelAndView messageList(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("groupmgt/messageList");

		return modelAndView;
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

	// 新增教职工
	@ResponseBody
	@RequestMapping(value = "/addMessage", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addMessage(@ModelAttribute("message") Message message, HttpServletRequest request) throws Exception {

		boolean rs = messageService.addMessage(message, request);

		HashMap<String, String> rsMap = new HashMap<String, String>();
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

	// 删除教职工
	@ResponseBody
	@RequestMapping(value = "/delMessage", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String delMessage(@ModelAttribute("messageId") int messageId, HttpServletRequest request) throws Exception {
		HashMap<String, String> rsMap = new HashMap<String, String>();
		boolean rs = messageService.delMessage(messageId);
		if (rs) {
			rsMap.put("status", "1");
			rsMap.put("info", "删除成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "删除失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}

	// 修改教职工
	@ResponseBody
	@RequestMapping(value = "/updateMessage", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String updateMessage(@ModelAttribute("message") Message message, HttpServletRequest request) throws Exception {
		HashMap<String, String> rsMap = new HashMap<String, String>();
		boolean rs = messageService.updateMessage(message);

		if (rs) {
			rsMap.put("status", "1");
			rsMap.put("info", "更新成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "更新失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
}
