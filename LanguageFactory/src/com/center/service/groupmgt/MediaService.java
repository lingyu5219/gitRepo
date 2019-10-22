package com.center.service.groupmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.center.po.groupmgt.Media;
import com.center.po.groupmgt.MediaQuery;
import com.center.po.query.DatatablesView;

public interface MediaService {
	
	public DatatablesView<Media> queryMediaList(MediaQuery mediaQuery) throws Exception;

	public List<Media> queryMedia(MediaQuery mediaQuery) throws Exception;
	
	public boolean addMedia(Media media, HttpServletRequest request) throws Exception; 
	
	public boolean delMedia(int mediaId, String fileUrl) throws Exception;
	
	public boolean updateMedia(Media media) throws Exception;

}

