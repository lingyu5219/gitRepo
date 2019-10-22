package com.center.mapper.groupmgt;

import java.util.List;

import com.center.po.groupmgt.Message;
import com.center.po.groupmgt.MessageQuery;

/**
 * ClassName:StaffMapper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年6月22日 下午3:04:17 <br/>
 * 
 * @author 李逢杰
 * @version
 * @see
 */
public interface MessageMapper {
	
	public Message queryMessageById(int messageId) throws Exception;
	
	public List<Message> queryMessageList(MessageQuery messageQuery) throws Exception;

	public Long queryMessageCount(MessageQuery messageQuery) throws Exception;
	
	public int addMessage(Message message) throws Exception;
	
	public int delMessage(int messageId) throws Exception;
	
	public int updateMessage(Message message) throws Exception;

}
