package com.center.mapper.system;

import java.util.List;

import com.center.po.system.Author;
import com.center.po.system.Menu;
import com.center.po.system.Module;

public interface AuthorMapper {
	
	public List<Author> queryAuthorList(Author author) throws Exception;
	 
	public long queryAuthorCount(Author author) throws Exception;
 
	public int addAuthor(Author author) throws Exception;

	public int deleteAuthor(int authorId) throws Exception;
	
	public int deleteAuthorByRole(int authorId) throws Exception;

	public int updateAuthor(Author author) throws Exception;
	
	public List<Menu> queryMenuByRole(int roleId) throws Exception;

	public List<Module> queryModuleByRole(int roleId) throws Exception;
}
