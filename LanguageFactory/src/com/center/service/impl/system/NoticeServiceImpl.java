package com.center.service.impl.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.system.NoticeMapper;
import com.center.po.query.DatatablesView;
import com.center.po.system.Notice;
import com.center.po.system.NoticeQuery;
import com.center.po.system.User;
import com.center.service.system.NoticeService;
@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public Notice queryNotice(int noticeId) {
		return noticeMapper.queryNotice(noticeId);
	}

	@Override
	public DatatablesView<Notice> queryNoticeList(NoticeQuery noticeQuery) {
		DatatablesView<Notice> datatablesView = new DatatablesView<Notice>();
		List<Notice> noticeList = noticeMapper.queryNoticeList(noticeQuery);
		Long count = noticeMapper.queryNoticeCount(noticeQuery);
		datatablesView.setData(noticeList);
		datatablesView.setRecordsTotal(count);
		return datatablesView;
	}

	@Override
	public boolean addNotice(HttpServletRequest request,Notice notice) {
		HttpSession session = request.getSession();
		User loginUser = (User)	session.getAttribute("user");
		notice.setCreateBy(loginUser.getUserId());
		noticeMapper.addNotice(notice);
		if(notice.getNoticeId() > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delNotice(int noticeId) {
		int affectedRecords = noticeMapper.delNotice(noticeId);
		if(affectedRecords > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateNotice(Notice notice) {
		int affectedRecords = noticeMapper.updateNotice(notice);
		
		if(affectedRecords > 0){
			return true;
		} else {
			return false;
		}
	}

}
