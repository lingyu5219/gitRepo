package com.center.service.impl.groupmgt;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.center.mapper.groupmgt.ClassTypeMapper;
import com.center.po.groupmgt.ClassType;
import com.center.po.groupmgt.ClassTypeQuery;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.ClassTypeService;

@Service
@Transactional
public class ClassTypeServiceImpl implements ClassTypeService{

	@Autowired
	private ClassTypeMapper dao;

	@Override
	public boolean addClassType(ClassType classType) {
		dao.addClassType(classType);
		if(classType.getClasstypeId()>0) {
			return true;
		}
		return false;
	}

	@Override
	public DatatablesView<ClassType> getClassTypeList(ClassTypeQuery classType) {
		DatatablesView<ClassType> dataView = new DatatablesView<ClassType>();
		List<ClassType> list = dao.getClassTypeList(classType);
		long count = dao.count(classType);
		dataView.setRecordsTotal(count);
		dataView.setData(list);
		return dataView;
	}

	@Override
	public Integer updateClassType(ClassType classType) {
		return dao.updateClassType(classType);
	}

	@Override
	public Integer deleteClassTypeById(String classTypeId) {
		return dao.deleteClassTypeById(classTypeId);
	}

	@Override
	public List<Map<String, Object>> getSelectList() {
		return dao.getSelectList();
	}

	@Override
	public List<ClassType> queryClassTypeByGartenId(int gartenId) throws Exception {
		
		return dao.queryClassTypeByGartenId(gartenId);
	}
	
}
