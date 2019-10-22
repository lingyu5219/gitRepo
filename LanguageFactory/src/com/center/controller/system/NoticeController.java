package com.center.controller.system;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.center.po.query.DatatablesView;
import com.center.po.system.Notice;
import com.center.po.system.NoticeQuery;
import com.center.service.msgmgt.MsgService;
import com.center.service.system.NoticeService;
import com.leopie.plugins.websocket.ChatAnnotation;
import com.leopie.plugins.websocket.GetHttpSessionConfigurator;

import net.sf.json.JSONObject;
/**
 * 
* ClassName: NoticeController <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月27日 上午1:19:24 <br/>
*
* @author donghao
* @version
 */
@Controller
@RequestMapping("/system")
@ServerEndpoint(value = "/notice/ws",configurator=GetHttpSessionConfigurator.class)
public class NoticeController extends ChatAnnotation {
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private MsgService msgService;
	
	@RequestMapping(value = "/noticeList", method = RequestMethod.GET)
	public ModelAndView noticeList(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/noticeList");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryNoticeList",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryNoticeList(@ModelAttribute("noticeQuery") NoticeQuery noticeQuery,HttpServletRequest request) throws Exception{
		DatatablesView<Notice> dataTable = noticeService.queryNoticeList(noticeQuery);
		dataTable.setDraw(noticeQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/addNotice",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addNotice(HttpServletRequest request, @ModelAttribute("notice") Notice notice) throws Exception{
		boolean rs = noticeService.addNotice(request,notice);
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
	@RequestMapping(value="/publishNotice",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String publishNotice(@ModelAttribute("notice") Notice notice) throws Exception{
		boolean result = noticeService.updateNotice(notice);
		HashMap<String,String> rsMap = new HashMap<String,String>();
		if (result) {
			rsMap.put("status", "1");
			rsMap.put("info", "操作成功");
			//如果是发布公告， 则广播系统公告，如果是撤销公告，也需要广播，并在前端将该公告删除
			notice = noticeService.queryNotice(notice.getNoticeId());
			String data = JSONObject.fromObject(notice).toString();
			broadcast(data);
			//如果是撤销公告，需要清空改公告消息已读日志
			if(notice.getNoticeState() == 1){
				msgService.delMsgLog(notice.getNoticeId());
			}
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "操作失败");
		}
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateNotice",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String updateNotice(@ModelAttribute("notice") Notice notice) throws Exception{
		HashMap<String,String> rsMap = new HashMap<String,String>();
		//只有公告状态为未发布1时，可以修改
		if(notice.getNoticeState() == 1) {
			boolean result = noticeService.updateNotice(notice);
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
			rsMap.put("info", "当前公告已发布，不可修改");
			String data = JSONObject.fromObject(rsMap).toString();
			return data;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/delNotice",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String delNotice(@ModelAttribute("noticeId") int noticeId) throws Exception{
		HashMap<String,String> rsMap = new HashMap<String,String>();
		//只有公告状态为未发布1时，可以修改
		Notice notice = noticeService.queryNotice(noticeId);
		if(notice.getNoticeState() == 1) {
			boolean result = noticeService.delNotice(noticeId);
			
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
