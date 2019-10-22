package com.center.service.cms;

import javax.servlet.http.HttpServletRequest;

import com.center.po.query.DatatablesView;
import com.center.po.cms.Content;
import com.center.po.cms.ContentQuery;
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
public interface ContentService {
	public Content queryContent(int contentId);
	
	public DatatablesView<Content> queryContentList(ContentQuery contentQuery);
	
	public boolean addContent(HttpServletRequest request,Content content);
	
	public boolean delContent(int contentId);
	
	public boolean updateContent(Content content);
	
}
