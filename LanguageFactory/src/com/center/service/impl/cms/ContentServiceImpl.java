package com.center.service.impl.cms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.cms.ContentMapper;
import com.center.po.query.DatatablesView;
import com.center.po.cms.Content;
import com.center.po.cms.ContentQuery;
import com.center.po.system.User;
import com.center.service.cms.ContentService;
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentMapper contentMapper;
	
	@Override
	public Content queryContent(int contentId) {
		return contentMapper.queryContent(contentId);
	}

	@Override
	public DatatablesView<Content> queryContentList(ContentQuery contentQuery) {
		DatatablesView<Content> datatablesView = new DatatablesView<Content>();
		List<Content> contentList = contentMapper.queryContentList(contentQuery);
		Long count = contentMapper.queryContentCount(contentQuery);
		datatablesView.setData(contentList);
		datatablesView.setRecordsTotal(count);
		return datatablesView;
	}

	@Override
	public boolean addContent(HttpServletRequest request,Content content) {
		HttpSession session = request.getSession();
		User loginUser = (User)	session.getAttribute("user");
		content.setCreateBy(loginUser.getUserId());
		contentMapper.addContent(content);
		if(content.getContentId() > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delContent(int contentId) {
		int affectedRecords = contentMapper.delContent(contentId);
		if(affectedRecords > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateContent(Content content) {
		int affectedRecords = contentMapper.updateContent(content);
		
		if(affectedRecords > 0){
			return true;
		} else {
			return false;
		}
	}

}
