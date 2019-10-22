package com.center.service.system;

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
public interface NoticeService {
	public Notice queryNotice(int noticeId);
	
	public DatatablesView<Notice> queryNoticeList(NoticeQuery noticeQuery);
	
	public boolean addNotice(HttpServletRequest request,Notice notice);
	
	public boolean delNotice(int noticeId);
	
	public boolean updateNotice(Notice notice);
	
}
