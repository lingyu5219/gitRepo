package com.center.mapper.groupmgt;

import java.util.List;

import com.center.po.groupmgt.Comments;
import com.center.po.groupmgt.CommentsQuery;

public interface CommentsMapper {
	
	public List<Comments> queryComments(CommentsQuery commentsQuery);

	public Long queryCommentsCount(CommentsQuery commentsQuery); 

	public List<Comments> queryCommentsList(CommentsQuery commentsQuery);
	
	public void addComments(Comments comments);
	
	public int delComments(int commentsId);
	
	public int updateComments(Comments comments);
}
