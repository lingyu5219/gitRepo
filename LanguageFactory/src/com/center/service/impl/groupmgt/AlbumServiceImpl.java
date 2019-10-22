package com.center.service.impl.groupmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.groupmgt.AlbumMapper;
import com.center.po.groupmgt.Album;
import com.center.po.groupmgt.AlbumQuery;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.groupmgt.AlbumService;
@Service
public class AlbumServiceImpl implements AlbumService {
	@Autowired
	AlbumMapper albumMapper;

	@Override
	public DatatablesView<Album> queryAlbumList(AlbumQuery albumQuery) throws Exception  {
		DatatablesView<Album> dataView =new DatatablesView<Album>();
		Long count = albumMapper.queryAlbumCount(albumQuery);
		List<Album> albums = albumMapper.queryAlbumList(albumQuery); 
		dataView.setRecordsTotal(count);
		dataView.setData(albums);
		return dataView;
	}

	@Override
	public List<Album> queryAlbum(AlbumQuery albumQuery) throws Exception{
		List<Album> albumList = albumMapper.queryAlbum(albumQuery);
		return albumList;
	}

	@Override
	public boolean addAlbum(Album album, HttpServletRequest request) throws Exception{
		//获取已登录的用户Id
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		album.setCreateBy(user.getUserId());
		albumMapper.addAlbum(album);
		
		return album.getAlbumId() > 0;
	}
	
	@Override
	public boolean deleteAlbumById(int albumId) throws Exception{
		 return albumMapper.delAlbum(albumId) > 0;
	}

	@Override
	public boolean updateAlbum(Album album) throws Exception{
		return albumMapper.updateAlbum(album) > 0;
	}
}
