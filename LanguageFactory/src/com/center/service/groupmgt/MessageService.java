package com.center.service.groupmgt;

import javax.servlet.http.HttpServletRequest;

import com.center.po.groupmgt.Message;
import com.center.po.groupmgt.MessageQuery;
import com.center.po.query.DatatablesView;

public interface MessageService {

	public Message queryMessageById(int messageId) throws Exception;
	
	//分页查询
	public DatatablesView<Message> queryMessageList(MessageQuery messageQuery) throws Exception;
	
	// 添加信息
	public boolean addMessage(Message message,HttpServletRequest request) throws Exception;

	// 修改信息
	public boolean updateMessage(Message message) throws Exception;
	
	// 删除信息
	public boolean delMessage(int messageId) throws Exception;

}

