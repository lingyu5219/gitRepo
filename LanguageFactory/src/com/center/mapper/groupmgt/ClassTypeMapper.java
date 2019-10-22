package com.center.mapper.groupmgt;

import java.util.List;
import java.util.Map;

import com.center.po.groupmgt.ClassType;
import com.center.po.groupmgt.ClassTypeQuery;

public interface ClassTypeMapper {

	void addClassType(ClassType classType);

	List<ClassType> getClassTypeList(ClassTypeQuery classType);

	long count(ClassType classType);

	Integer updateClassType(ClassType classType);

	Integer deleteClassTypeById(String classTypeId);

	List<Map<String, Object>> getSelectList();

	public List<ClassType> queryClassTypeByGartenId(int gartenId) throws Exception;
	
}
