package com.center.service.groupmgt;

import java.util.List;
import java.util.Map;

import com.center.po.groupmgt.LessonType;
import com.center.po.groupmgt.LessonTypeQuery;
import com.center.po.query.DatatablesView;

public interface LessonTypeService {

	boolean addLessonType(LessonType lessonType);

	public LessonType queryLessonTypeById(int lessonTypeId) throws Exception;
	
	DatatablesView<LessonType> getLessonTypeList(LessonTypeQuery LessonType);

	Integer updateLessonType(LessonType LessonType);

	Integer deleteLessonTypeById(String LessonTypeId);

	List<Map<String, Object>> getSelectList();
}
