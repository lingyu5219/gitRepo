package com.center.service.system;

import java.util.List;

import com.center.po.query.DatatablesView;
import com.center.po.system.Module;
import com.center.po.system.ModuleQuery;

public interface ModuleService {
	
	public List<Module> queryModule(ModuleQuery moduleQuery) throws Exception;
	
	public DatatablesView<Module> queryModuleList(ModuleQuery moduleQuery) throws Exception;
	
	public void addModule(Module module) throws Exception; 
	
	public boolean deleteModuleById(int moduleId) throws Exception;
	
	public boolean updateModule(Module module) throws Exception;

}

