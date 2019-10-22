package com.center.mapper.groupmgt;

import java.util.List;

import com.center.po.groupmgt.Album;
import com.center.po.groupmgt.AlbumQuery;

public interface AlbumMapper {
	
	public List<Album> queryAlbum(AlbumQuery albumQuery) throws Exception;

	public Long queryAlbumCount(AlbumQuery albumQuery) throws Exception;

	public List<Album> queryAlbumList(AlbumQuery albumQuery) throws Exception;
	
	public void addAlbum(Album album) throws Exception;
	
	public int delAlbum(int albumId) throws Exception;
	
	public int updateAlbum(Album album) throws Exception;
}
