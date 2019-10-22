package com.center.service.groupmgt;

import java.util.List;
import java.util.Map;

import com.center.po.groupmgt.ClassType;
import com.center.po.groupmgt.ClassTypeQuery;
import com.center.po.query.DatatablesView;

public interface ClassTypeService {

	boolean addClassType(ClassType classType);

	DatatablesView<ClassType> getClassTypeList(ClassTypeQuery classType);

	Integer updateClassType(ClassType classType);

	Integer deleteClassTypeById(String classTypeId);

	List<Map<String, Object>> getSelectList();

	public List<ClassType> queryClassTypeByGartenId(int gartenId) throws Exception;
}
