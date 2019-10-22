package com.center.mapper.groupmgt;

import java.util.List;

import com.center.po.groupmgt.Media;
import com.center.po.groupmgt.MediaQuery;

public interface MediaMapper {
	
	public Long queryMediaCount(MediaQuery mediaQuery) throws Exception; 

	public List<Media> queryMediaList(MediaQuery mediaQuery) throws Exception;
	
	public List<Media> queryMedia(MediaQuery mediaQuery) throws Exception;
	
	public void addMedia(Media media) throws Exception;
	
	public int delMedia(int mediaId) throws Exception;
	
	public int updateMedia(Media media) throws Exception;
}
