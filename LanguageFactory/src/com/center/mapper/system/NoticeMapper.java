package com.center.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
public interface NoticeMapper {
	public Notice queryNotice(int noticeId);
	
	public Long queryNoticeCount(NoticeQuery noticeQuery);
	
	public List<Notice> queryNoticeList(NoticeQuery noticeQuery);
	
	public void addNotice(Notice notice);
	
	public int delNotice(int noticeId);
	
	public int updateNotice(Notice notice);
	
}
