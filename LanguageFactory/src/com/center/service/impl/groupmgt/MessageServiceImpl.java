package com.center.service.impl.groupmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.groupmgt.MessageMapper;
import com.center.po.groupmgt.Message;
import com.center.po.groupmgt.MessageQuery;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.groupmgt.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	MessageMapper messageMapper;
	
	@Override
	public DatatablesView<Message> queryMessageList(MessageQuery messageQuery) throws Exception {

		DatatablesView<Message> dataView =new DatatablesView<Message>();
		Long count = messageMapper.queryMessageCount(messageQuery);
		List<Message> messageList = messageMapper.queryMessageList(messageQuery);		
		
		dataView.setData(messageList);
		dataView.setRecordsTotal(count);
		return dataView;
	}

	@Override
	public boolean addMessage(Message message, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User loginUser = (User)	session.getAttribute("user");
		message.setCreateBy(loginUser.getUserId());
		
		messageMapper.addMessage(message);

		return message.getMsgId() > 0;
	}

	@Override
	public boolean updateMessage(Message message) throws Exception {

		return messageMapper.updateMessage(message) > 0;

	}

	@Override
	public boolean delMessage(int messageId) throws Exception {

		return messageMapper.delMessage(messageId) > 0;

	}

	@Override
	public Message queryMessageById(int messageId) throws Exception {
		
		return messageMapper.queryMessageById(messageId);
	}

}

