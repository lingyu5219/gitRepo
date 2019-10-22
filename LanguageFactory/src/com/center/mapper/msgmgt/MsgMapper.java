package com.center.mapper.msgmgt;

import java.util.List;

import com.center.po.system.Notice;
import com.center.po.system.NoticeQuery;
/**
 * 
* ClassName: NoticeMapper <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月26日 下午8:24:41 <br/>
*
* @author donghao
* @version
 */
public interface MsgMapper {
	
	public Long queryMsgCount(NoticeQuery noticeQuery) throws Exception;
	
	public List<Notice> queryMsgList(NoticeQuery noticeQuery) throws Exception;
	
	public List<Notice> queryMsg(NoticeQuery noticeQuery) throws Exception;
	
	public int addMsgLog(Notice notice) throws Exception;
	
	public int delMsgLog(int noticeId) throws Exception;

	public Long queryMsgLogCount(Notice notice) throws Exception;
}
