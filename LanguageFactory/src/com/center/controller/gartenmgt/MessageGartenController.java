package com.center.controller.gartenmgt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/gartenmgt")
public class MessageGartenController {
	
	//校园媒体资源管理功能
	@RequestMapping(value="/messageGartenList",method=RequestMethod.GET)
	public String messageGartenList(HttpServletRequest request) throws Exception {
		return "gartenmgt/messageGartenList";
	}
	
}

