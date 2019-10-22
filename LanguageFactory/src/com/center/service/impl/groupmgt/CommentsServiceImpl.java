package com.center.service.impl.groupmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.groupmgt.CommentsMapper;
import com.center.po.groupmgt.Comments;
import com.center.po.groupmgt.CommentsQuery;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.CommentsService;
import com.google.code.kaptcha.Constants;
@Service
public class CommentsServiceImpl implements CommentsService {
	@Autowired
	CommentsMapper commentsMapper;

	@Override
	public DatatablesView<Comments> queryCommentsList(CommentsQuery commentsQuery) throws Exception  {
		DatatablesView<Comments> dataView =new DatatablesView<Comments>();
		Long count = commentsMapper.queryCommentsCount(commentsQuery);
		List<Comments> commentss = commentsMapper.queryCommentsList(commentsQuery); 
		dataView.setRecordsTotal(count);
		dataView.setData(commentss);
		return dataView;
	}

	@Override
	public List<Comments> queryComments(CommentsQuery commentsQuery) {
		List<Comments> commentsList = commentsMapper.queryComments(commentsQuery);
		return commentsList;
	}

	@Override
	public boolean addComments(Comments comments, HttpServletRequest request) {
		String verifyCode = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
		if(verifyCode != null && verifyCode.equals(comments.getVerifyCode())){
			commentsMapper.addComments(comments);
		}
		return comments.getCommentsId() > 0;
	}
	
	@Override
	public boolean delComments(int commentsId) {
		 return commentsMapper.delComments(commentsId) > 0;
	}

	@Override
	public boolean updateComments(Comments comments) {
		return commentsMapper.updateComments(comments) > 0;
	}
}
