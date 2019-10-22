package com.center.service.groupmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.center.po.groupmgt.Album;
import com.center.po.groupmgt.AlbumQuery;
import com.center.po.query.DatatablesView;

public interface AlbumService {
	
	public List<Album> queryAlbum(AlbumQuery albumQuery) throws Exception;
	
	public DatatablesView<Album> queryAlbumList(AlbumQuery albumQuery) throws Exception;
	
	public boolean addAlbum(Album album, HttpServletRequest request) throws Exception; 
	
	public boolean deleteAlbumById(int albumId) throws Exception;
	
	public boolean updateAlbum(Album album) throws Exception;

}

