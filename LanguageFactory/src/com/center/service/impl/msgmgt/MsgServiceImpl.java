
/**
* Project Name:eduSystem
* File Name:MsgServiceImpl.java
* Package Name:com.center.service.impl.msgmgt
* Date:2017年5月8日下午1:00:46
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package com.center.service.impl.msgmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.msgmgt.MsgMapper;
import com.center.po.query.DatatablesView;
import com.center.po.system.Notice;
import com.center.po.system.NoticeQuery;
import com.center.po.system.User;
import com.center.service.msgmgt.MsgService;

/**
* ClassName:MsgServiceImpl <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年5月8日 下午1:00:46 <br/>
* @author Tony
* @version
* @see
*/
@Service
public class MsgServiceImpl implements MsgService {

	@Autowired
	private MsgMapper msgMapper;
	
	@Override
	public DatatablesView<Notice> queryMsg(NoticeQuery noticeQuery) throws Exception{
		DatatablesView<Notice> datatablesView = new DatatablesView<Notice>();
		List<Notice> noticeList = msgMapper.queryMsg(noticeQuery);
		Long count = Long.parseLong(String.valueOf(noticeList.size()));
		datatablesView.setData(noticeList);
		datatablesView.setRecordsTotal(count);
		return datatablesView;
	}
	
	@Override
	public DatatablesView<Notice> queryMsgList(NoticeQuery noticeQuery) throws Exception{
		DatatablesView<Notice> datatablesView = new DatatablesView<Notice>();
		List<Notice> noticeList = msgMapper.queryMsgList(noticeQuery);
		Long count = msgMapper.queryMsgCount(noticeQuery);
		datatablesView.setData(noticeList);
		datatablesView.setRecordsTotal(count);
		return datatablesView;
	}

	@Override
	public boolean addMsgLog(int noticeId, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User loginUser = (User)	session.getAttribute("user");
		
		Notice notice = new Notice();
		notice.setNoticeId(noticeId);
		notice.setCreateBy(loginUser.getUserId());
		msgMapper.addMsgLog(notice);
		if(notice.getLogId() > 0){
			return true;
		}
		return false;
	}

	public Long queryMsgLogCount(int noticeId, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		User loginUser = (User)	session.getAttribute("user");
		
		Notice notice = new Notice();
		notice.setNoticeId(noticeId);
		notice.setCreateBy(loginUser.getUserId());
		return msgMapper.queryMsgLogCount(notice);
	}

	@Override
	public boolean delMsgLog(int noticeId) throws Exception {
		int affectedRecords = msgMapper.delMsgLog(noticeId);
		if(affectedRecords > 0){
			return true;
		} else {
			return false;
		}
		
	}
}

