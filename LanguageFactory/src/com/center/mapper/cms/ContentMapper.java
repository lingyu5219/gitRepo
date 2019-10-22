package com.center.mapper.cms;

import java.util.List;

import com.center.po.cms.Content;
import com.center.po.cms.ContentQuery;
/**
 * 
* ClassName: ContentMapper <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月26日 下午8:24:41 <br/>
*
* @author donghao
* @version
 */
public interface ContentMapper {
	public Content queryContent(int contentId);
	
	public Long queryContentCount(ContentQuery contentQuery);
	
	public List<Content> queryContentList(ContentQuery contentQuery);
	
	public void addContent(Content content);
	
	public int delContent(int contentId);
	
	public int updateContent(Content content);
	
}
