package com.center.controller.msgmgt;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

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
import com.center.po.system.User;
import com.center.service.msgmgt.MsgService;
import com.center.service.system.NoticeService;

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
@RequestMapping("/msgmgt")
public class MsgController{
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private MsgService msgService;
	
	@RequestMapping(value = "/msgList", method = RequestMethod.GET)
	public ModelAndView msgList(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("msgmgt/msgList");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryMsg",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryMsg(@ModelAttribute("noticeQuery") NoticeQuery noticeQuery,HttpServletRequest request) throws Exception{
		User loginUser = (User)request.getSession().getAttribute("user");
		noticeQuery.setCreateBy(loginUser.getUserId());
		
		DatatablesView<Notice> dataTable = msgService.queryMsg(noticeQuery);
		dataTable.setDraw(noticeQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryMsgList",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String queryMsgList(@ModelAttribute("noticeQuery") NoticeQuery noticeQuery,HttpServletRequest request) throws Exception{
		User loginUser = (User)request.getSession().getAttribute("user");
		noticeQuery.setCreateBy(loginUser.getUserId());
		
		DatatablesView<Notice> dataTable = msgService.queryMsgList(noticeQuery);
		dataTable.setDraw(noticeQuery.getDraw());
		String data = JSONObject.fromObject(dataTable).toString();
		return data;
	}
	
	@RequestMapping(value = "/msgDetail", method = RequestMethod.POST)
	public ModelAndView msgDetail(@ModelAttribute("noticeId") int noticeId,HttpServletRequest request) throws Exception {
		//首先检测是否已经阅读该消息，未读的话，需要先记录阅读日志
		if(msgService.queryMsgLogCount(noticeId,request) <= 0){
			//没有日志数据，说明未读
			msgService.addMsgLog(noticeId, request);
		};
		
		Notice notice = noticeService.queryNotice(noticeId);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("notice", notice);
		modelAndView.setViewName("msgmgt/msgDetail");
		return modelAndView;
	}
	
}
