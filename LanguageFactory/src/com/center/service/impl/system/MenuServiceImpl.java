package com.center.service.impl.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.system.MenuMapper;
import com.center.mapper.system.ModuleMapper;
import com.center.po.query.DatatablesView;
import com.center.po.system.Menu;
import com.center.po.system.MenuQuery;
import com.center.po.system.Module;
import com.center.service.system.MenuService;
@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private ModuleMapper moduleMapper;
	
	@Override
	public List<Menu> queryMenu(MenuQuery menuQuery) throws Exception {
  	
  		return menuMapper.queryMenu(menuQuery);
  	
	}
  
	@Override
	public DatatablesView<Menu> queryMenuList(MenuQuery menuQuery) throws Exception{
		DatatablesView<Menu> dataView = new DatatablesView<Menu>();
		List<Menu> menuList=menuMapper.queryMenuList(menuQuery);
		long Count=menuMapper.queryMenuCount(menuQuery);
		dataView.setRecordsTotal(Count);
	    dataView.setData(menuList);
	    return  dataView;
	
	}

	private boolean Judge(int num){
		if(num>0)
			return true;
		else
			return false;
	} 
  
  
	@Override
	public boolean addMenu(Menu menu) throws Exception {
		return Judge(menuMapper.addMenu(menu));
	}
	
	@Override
	public  boolean deleteMenu(int menuId) throws Exception {
		return Judge(menuMapper.deleteMenu(menuId));
	}
	
	@Override
	public boolean updateMenu(Menu menu) throws Exception {
		return Judge(menuMapper.updateMenu(menu));
	}
	
	@Override
	public List<Module> queryMenuTree() throws Exception {
		List<Module> moduleList = moduleMapper.queryModule(null);
		List<Menu> menuList = menuMapper.queryMenu(null);
		for(Module module : moduleList){
			List<Menu> subMenuList = new ArrayList<Menu>();
			for(Menu menu : menuList){
				if(menu.getModuleId() == module.getModuleId()){
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
