package com.center.service.impl.groupmgt;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.groupmgt.MediaMapper;
import com.center.po.groupmgt.Media;
import com.center.po.groupmgt.MediaQuery;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.groupmgt.MediaService;
@Service
public class MediaServiceImpl implements MediaService {
	@Autowired
	MediaMapper mediaMapper;

	@Override
	public DatatablesView<Media> queryMediaList(MediaQuery mediaQuery) throws Exception  {
		DatatablesView<Media> dataView =new DatatablesView<Media>();
		Long count = mediaMapper.queryMediaCount(mediaQuery);
		List<Media> medias = mediaMapper.queryMediaList(mediaQuery); 
		dataView.setRecordsTotal(count);
		dataView.setData(medias);
		return dataView;
	}

	@Override
	public List<Media> queryMedia(MediaQuery mediaQuery) throws Exception  {
		return mediaMapper.queryMedia(mediaQuery);
	}

	@Override
	public boolean addMedia(Media media, HttpServletRequest request) throws Exception{
		//获取已登录的用户Id
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		media.setCreateBy(user.getUserId());
		mediaMapper.addMedia(media);
		
		return media.getMediaId() > 0;
	}
	
	@Override
	public boolean delMedia(int mediaId, String fileUrl) throws Exception {
		File file = new File(fileUrl);
		if(!file.exists()){
			return mediaMapper.delMedia(mediaId) > 0;
		}
		return file.delete() && (mediaMapper.delMedia(mediaId) > 0);
	}

	@Override
	public boolean updateMedia(Media media) throws Exception {
		//如果更新时，删除了原来上传的资源文件，并上传了新的资源文件，则将原来文件删除
		boolean mediaFileFlag = true;
		File mediaFile = new File(media.getMediaOriginalUrl());
		
		if(media.getMediaFileChanged() == 1 && mediaFile.exists()){
			mediaFileFlag = mediaFile.delete();
			//return file.delete() && (mediaMapper.updateMedia(media) > 0);
		}
		
		boolean coverFileFlag = true;
		File coverFile = new File(media.getMediaCoverOriginalUrl());
		if(media.getMediaCoverFileChanged() == 1 && coverFile.exists()
				&& !media.getMediaCoverOriginalUrl().contains("static/images/video_player_cover.png")){
			coverFileFlag = coverFile.delete();
		}
		
		boolean updateFlag = mediaMapper.updateMedia(media) > 0;
		
		return mediaFileFlag && coverFileFlag && updateFlag;
	}
}
