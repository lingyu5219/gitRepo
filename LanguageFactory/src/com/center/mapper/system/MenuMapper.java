package com.center.mapper.system;

import java.util.List;

import com.center.po.system.Menu;
import com.center.po.system.MenuQuery;

public interface MenuMapper {
	public List<Menu> queryMenu(MenuQuery menuQuery) throws Exception;
	public List<Menu> queryMenuList(MenuQuery menuQuery) throws Exception;
	public long queryMenuCount(MenuQuery menuQuery) throws Exception;
	public int  addMenu(Menu menu) throws Exception;
	public int  deleteMenu(int menuId) throws Exception;
	public int updateMenu(Menu menu) throws Exception;
}
