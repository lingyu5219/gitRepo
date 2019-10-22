package com.center.service.groupmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.center.po.groupmgt.Comments;
import com.center.po.groupmgt.CommentsQuery;
import com.center.po.query.DatatablesView;

public interface CommentsService {
	
	public List<Comments> queryComments(CommentsQuery commentsQuery) throws Exception;
	
	public DatatablesView<Comments> queryCommentsList(CommentsQuery commentsQuery) throws Exception;
	
	public boolean addComments(Comments comments, HttpServletRequest request) throws Exception; 
	
	public boolean delComments(int commentsId) throws Exception;
	
	public boolean updateComments(Comments comments) throws Exception;

}

