package com.center.service.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.center.po.query.DatatablesView;
import com.center.po.system.Author;
import com.center.po.system.AuthorQuery;
import com.center.po.system.Menu;
import com.center.po.system.Module;

public interface AuthorService {
	public DatatablesView<Author> queryAuthorList(AuthorQuery authorQuery) throws Exception;
	
	public  boolean addAuthor(Author author, HttpServletRequest request) throws Exception;
	
	public  boolean deleteAuthor(int authorId) throws Exception;
	
	public boolean updateAuthor(Author author, HttpServletRequest request) throws Exception;
	
	public List<Module> queryModuleByRole(int roleId) throws Exception;
	
	public List<Menu> queryMenuByRole(int roleId) throws Exception;
	
	public List<Menu> menu2Tree(List<Menu> menuList) throws Exception;
	
	/**
	 * 
	* menu2TreeByModule:(将枝干菜单按照模块转换成树形结构). <br/>
	*
	* @author Tony
	* @param moduleList
	* @param menuList
	* @return
	* @throws Exception
	 */
	public List<Module> menu2TreeByModule(List<Module> moduleList, List<Menu> menuList) throws Exception;
	
}
