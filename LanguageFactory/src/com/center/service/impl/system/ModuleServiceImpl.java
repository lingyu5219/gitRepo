package com.center.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.system.ModuleMapper;
import com.center.po.query.DatatablesView;
import com.center.po.system.Module;
import com.center.po.system.ModuleQuery;
import com.center.service.system.ModuleService;
@Service
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	ModuleMapper moduleMapper;
	
	

	@Override
	public DatatablesView<Module> queryModuleList(ModuleQuery moduleQuery) throws Exception  {
		DatatablesView<Module> dataView =new DatatablesView<Module>();
		Long count = moduleMapper.queryModuleCount(moduleQuery);
		List<Module> modules = moduleMapper.queryModuleList(moduleQuery); 
		dataView.setRecordsTotal(count);
		dataView.setData(modules);
		return dataView;
	}

	@Override
	public List<Module> queryModule(ModuleQuery moduleQuery) {
		List<Module> moduleList = moduleMapper.queryModule(moduleQuery);
		return moduleList;
	}

	@Override
	public void addModule(Module module) {
		moduleMapper.addModule(module);
	}
	
	@Override
	public boolean deleteModuleById(int moduleId) {
		 return moduleMapper.delModule(moduleId)>0?true:false;
	}

	@Override
	public boolean updateModule(Module module) {
		return moduleMapper.updateModule(module)>0?true:false;
	}
}
