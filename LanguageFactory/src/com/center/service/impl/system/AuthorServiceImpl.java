package com.center.service.impl.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.center.mapper.system.AuthorMapper;
import com.center.po.query.DatatablesView;
import com.center.po.system.Author;
import com.center.po.system.AuthorQuery;
import com.center.po.system.Menu;
import com.center.po.system.Module;
import com.center.po.system.User;
import com.center.service.system.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{
	@Autowired
	private AuthorMapper authorMapper;
	
	private boolean Judge(int rs){
		if(rs > 0)
			return true;
		else
			return false;
	} 
	
	@Override
	public DatatablesView<Author> queryAuthorList(AuthorQuery authorQuery) throws Exception {
		DatatablesView<Author> dataView = new DatatablesView<Author>();
		List<Author> authorList = authorMapper.queryAuthorList(authorQuery);
		long Count = authorMapper.queryAuthorCount(authorQuery);
		dataView.setData(authorList);
		dataView.setRecordsTotal(Count);
		return dataView;
	}
	
	@Override
	@Transactional
	public boolean addAuthor(Author author, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		author.setCreateBy(user.getUserId());
		String[] menuIds = new String[0];
		if(null != author.getMenuIds()){
			menuIds = author.getMenuIds().split(",");
		}
		int addCount = 0;
		for(String menuId: menuIds){
			author.setMenuId(Integer.parseInt(menuId));
			addCount += authorMapper.addAuthor(author);
		}
		
		return addCount == menuIds.length;
	}
	
	@Override
	public boolean deleteAuthor(int authorId) throws Exception {
		return Judge(authorMapper.deleteAuthor(authorId));
	}
	
	@Override
	@Transactional
	public boolean updateAuthor(Author author, HttpServletRequest request) throws Exception {
		//先根据角色ID删除该角色原来配置的菜单
		authorMapper.deleteAuthorByRole(author.getRoleId());
		//再将重新配置的菜单插入到权限表
		return addAuthor(author,request);
	}
	
	@Override
	public List<Module> queryModuleByRole(int roleId) throws Exception{
		List<Module> moduleList = authorMapper.queryModuleByRole(roleId);
		return moduleList;
	}
	
	@Override
	public List<Menu> queryMenuByRole(int roleId) throws Exception {
		List<Menu> menuList = authorMapper.queryMenuByRole(roleId);
		return menuList;
	}
	
	@Override
	public List<Menu> menu2Tree(List<Menu> menuList) throws Exception{
		//通过递归，将菜单组织成树形结构，方便页面生成菜单目录
		List<Menu> newMenuList = new ArrayList<Menu>();
		for(Menu menu : menuList){
			//如果当前菜单是枝干，说明该菜单还有子菜单，需要获取子菜单
			if (menu.getIsFork() == 1) {
				newMenuList.add(menu);
				List<Menu> subMenuList = getSubMenuList(menuList,menu.getMenuId());
				menu.setSubMenuList(subMenuList);
			}
		}
		
		return newMenuList;
	}
	
	public List<Menu> getSubMenuList(List<Menu> menuList, int menuId) throws Exception {
		List<Menu> subMenuList = new ArrayList<Menu>();
		for (Menu menu : menuList) {
			if (menu.getParentId() == menuId) {
				subMenuList.add(menu);
				if (menu.getIsFork() == 1) {
					List<Menu> subMenuList2 = getSubMenuList(menuList,menu.getMenuId());
					menu.setSubMenuList(subMenuList2);
				}
			}
			
		}
		return subMenuList;
	}

	@Override
	public List<Module> menu2TreeByModule(List<Module> moduleList, List<Menu> menuList) throws Exception {
		for(Module module : moduleList){
			if(null == module){
				continue;
			}
			List<Menu> subMenuList = new ArrayList<Menu>();
			for(Menu menu : menuList){
				if(null == menu){
					continue;
				}
				if(menu.getModuleId() == module.getModuleId() && menu.getIsFork() == 1){
					subMenuList.add(menu);
				}
			}
			if (subMenuList.size() > 0) {
				module.setSubMenuList(subMenuList);
			}
		}
		return moduleList;
	}
	
}
