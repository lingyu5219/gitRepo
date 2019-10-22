package com.center.service.impl.groupmgt;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.groupmgt.LessonTypeMapper;
import com.center.po.groupmgt.LessonType;
import com.center.po.groupmgt.LessonTypeQuery;
import com.center.po.groupmgt.LessonType;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.LessonTypeService;
@Service
public class LessonTypeServiceImpl implements LessonTypeService{

	@Autowired
	private LessonTypeMapper dao;
	@Override
	public boolean addLessonType(LessonType lessonType) {
		dao.addLessonType(lessonType);
		if(lessonType.getLessonTypeId()>0) {
			return true;
		}
		return false;
	}

	@Override
	public DatatablesView<LessonType> getLessonTypeList(LessonTypeQuery LessonType) {
		DatatablesView<LessonType> dataView = new DatatablesView<LessonType>();
		List<LessonType> list = dao.getLessonTypeList(LessonType);
		long count = dao.count(LessonType);
		dataView.setRecordsTotal(count);
		dataView.setData(list);
		return dataView;
	}

	@Override
	public Integer updateLessonType(LessonType LessonType) {
		return dao.updateLessonType(LessonType);
	}

	@Override
	public Integer deleteLessonTypeById(String LessonTypeId) {
		return dao.deleteLessonTypeById(LessonTypeId);
	}

	@Override
	public List<Map<String, Object>> getSelectList() {
		return dao.getSelectList();
	}

	@Override
	public LessonType queryLessonTypeById(int lessonTypeId) throws Exception {
		
		return dao.queryLessonTypeById(lessonTypeId);
		
	}
	
}
