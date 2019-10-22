package com.center.service.msgmgt;

import javax.servlet.http.HttpServletRequest;

import com.center.po.query.DatatablesView;
import com.center.po.system.Notice;
import com.center.po.system.NoticeQuery;
/**
 * 
* ClassName: NoticeService <br/>
* Function: TODO ADD 文档见NoticeMapper <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月26日 下午9:20:19 <br/>
*
* @author donghao
* @version
 */
public interface MsgService {
	
	public DatatablesView<Notice> queryMsgList(NoticeQuery noticeQuery) throws Exception;
	
	public DatatablesView<Notice> queryMsg(NoticeQuery noticeQuery) throws Exception;
	
	public boolean addMsgLog(int noticeId, HttpServletRequest request) throws Exception;
	
	public boolean delMsgLog(int noticeId) throws Exception;
	
	public Long queryMsgLogCount(int noticeId, HttpServletRequest request) throws Exception;
}
