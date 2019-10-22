package com.center.service.system;

import java.util.List;

import com.center.po.query.DatatablesView;
import com.center.po.system.Menu;
import com.center.po.system.MenuQuery;
import com.center.po.system.Module;

public interface MenuService {

	public List<Menu> queryMenu(MenuQuery menuQuery) throws Exception;
	
	public DatatablesView<Menu> queryMenuList(MenuQuery menuQuery) throws Exception;
	
	public boolean  addMenu(Menu menu) throws Exception;
	
	public boolean  deleteMenu(int menuId) throws Exception;
	
	public boolean updateMenu(Menu menu) throws Exception;
	/**
	 * 
	* menu2TreeByModuleAll:(将所有菜单按照模块转换成树形结构). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @return
	* @throws Exception
	 */
	public List<Module> queryMenuTree() throws Exception;
}
