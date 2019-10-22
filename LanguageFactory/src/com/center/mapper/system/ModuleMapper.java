package com.center.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.center.po.system.Module;
import com.center.po.system.ModuleQuery;

public interface ModuleMapper {
	
	public List<Module> queryModule(ModuleQuery moduleQuery);

	public Long queryModuleCount(ModuleQuery moduleQuery); 

	public List<Module> queryModuleList(ModuleQuery moduleQuery);
	
	public void addModule(Module module);
	
	public int delModule(int moduleId);
	
	public int updateModule(Module module);
}
