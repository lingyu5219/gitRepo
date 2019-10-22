package com.center.controller.wx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.center.po.wx.WxProp;
import com.center.po.wx.WxXml;
import com.center.utils.WxUtil;

@Controller
@RequestMapping("/wx")
public class WxController {
	@Autowired
	private WxUtil wxUtil;
	
	/**
	 * 微信接入
	 * @param wxProp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/wxReceiver", method = RequestMethod.GET)
	@ResponseBody
	public void connectServer(@ModelAttribute("wxProp") WxProp wxProp,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(WxUtil.checkSignature(wxProp.getSignature(), wxProp.getTimestamp(), wxProp.getNonce())){
			System.out.println("**********微信接入成功***********");
			PrintWriter out = response.getWriter();
			out.print(wxProp.getEchostr());
			out.flush();
			out.close();
			out = null;
		}
		
	}
	
	@RequestMapping(value = "/wxReceiver", method = RequestMethod.POST)
	@ResponseBody
	public void receiveMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Date d1 = new Date();
		System.out.println(d1.getTime() + request.getCharacterEncoding() + " " + response.getCharacterEncoding());
		PrintWriter out = response.getWriter();
		/** 读取接收到的xml消息 */
		StringBuffer sb = new StringBuffer();
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		String xml = sb.toString();	//次即为接收到微信端发送过来的xml数据
		System.out.println(xml);
		WxXml wxXml = WxUtil.getWxXml(xml);
		String rspXml = "";
		if(null != wxXml){
			//如果是文本消息，则回复文本
			if(WxUtil.MSGTYPE_TEXT.equals(wxXml.getMsgType())){
				rspXml = WxUtil.formatXmlAnswer(wxXml.getFromUserName(),wxXml.getToUserName(),  "你好" + wxXml.getContent());
				System.out.println(rspXml);
			} else if(WxUtil.MSGTYPE_EVENT.equals(wxXml.getMsgType())){
				//如果是事件推送
				switch(wxXml.getEvent()){
					case WxUtil.EVENTTYPE_CLICK:
						//如果是打卡菜单
						if("clock_in".equals(wxXml.getEventKey())){
							//获取当前用户的openID和地理位置
							HashMap<String,String> userMap = WxUtil.getUserMap(wxXml.getFromUserName());
							String msg = ""	;
							if(null != userMap){
								msg = "用户打卡："+ wxXml.getFromUserName() + " 位置-Lat:" + userMap.get("Latitude") + " 位置-Long:" + userMap.get("Longitude");
							} else {
								msg = "用户打卡：获取用户位置失败";
							}
							rspXml = WxUtil.formatXmlAnswer(wxXml.getFromUserName(),wxXml.getToUserName(), msg);
							System.out.println("用户打卡：" + rspXml);
						}
						break;
					case WxUtil.EVENTTYPE_LOCATION:
						//如果是上报地理位置事件,则更新当前用户的地理位置
						HashMap<String,String> userMap = WxUtil.getUserMap(wxXml.getFromUserName());
						if(null == userMap){
							userMap = new HashMap<String,String>();
							WxUtil.getUserList().add(userMap);
						}
						userMap.put("OpenID", wxXml.getFromUserName());
						userMap.put("Latitude", wxXml.getLatitude());
						userMap.put("Longitude", wxXml.getLongitude());
						rspXml = WxUtil.formatXmlAnswer(wxXml.getFromUserName(),wxXml.getToUserName(), "微信上报地理位置——用户："+ wxXml.getFromUserName() + " 位置-Lat:" + wxXml.getLatitude() + " 位置-Long:" + wxXml.getLongitude());
						System.out.println("微信上报地理位置：" + rspXml);
						break;
				}
			}
		}
		out.write(rspXml);
		out.flush();
		out.close();
		out = null;
		Date d2 = new Date();
		System.out.println(d2.getTime() + " " + (d2.getTime()-d1.getTime())/1000);
	}
}
