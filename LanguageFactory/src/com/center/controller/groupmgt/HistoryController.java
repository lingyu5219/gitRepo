package com.center.controller.groupmgt;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.groupmgt.History;
import com.center.po.groupmgt.HistoryQuery;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.HistoryService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/groupmgt")
public class HistoryController {

	@Autowired
	private HistoryService service;

	@RequestMapping(value = "/historyList", method = RequestMethod.GET)
	public ModelAndView historyList(HttpServletRequest request) throws Exception {
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("groupmgt/historyList");
		return modelAndView;
	}
	//add
	@ResponseBody
	@RequestMapping(value = "/addHistory", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addHistory(History history,HttpServletRequest request) throws Exception {
		boolean result = service.addHistory(history, request);
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
	
	@ResponseBody
	@RequestMapping(value="/queryHistoryById", method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public History queryHistoryById(int historyId) throws Exception {
		return service.queryHistoryById(historyId);
	}
	//query
	@ResponseBody
	@RequestMapping(value = "/queryHistoryList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String queryHistoryList(HistoryQuery historyQuery) throws Exception {
		DatatablesView<History> dataTable = service.queryHistoryList(historyQuery);
		dataTable.setDraw(historyQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	//update
	@RequestMapping(value = "/updateHistory", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String updateHistory(History history) throws Exception {
		boolean result = service.updateHistory(history);
		HashMap<String, String> rsMap = new HashMap<String, String>();

		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", "更新成功");
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "更新失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	//delete
	@ResponseBody
	@RequestMapping(value = "/delHistory", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String delHistory(int historyId) throws Exception {
		boolean result = service.delHistory(historyId);
		HashMap<String, String> rsMap = new HashMap<String, String>();
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
}
