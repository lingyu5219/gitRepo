package com.center.mapper.groupmgt;

import java.util.List;
import java.util.Map;

import com.center.po.groupmgt.LessonType;
import com.center.po.groupmgt.LessonTypeQuery;

public interface LessonTypeMapper {

	void addLessonType(LessonType lessonType);

	
	List<LessonType> getLessonTypeList(LessonTypeQuery lessonType);

	long count(LessonType lessonType);

	Integer updateLessonType(LessonType lessonType);

	Integer deleteLessonTypeById(String lessonTypeId);

	List<Map<String, Object>> getSelectList();
	
	public LessonType queryLessonTypeById(int lessonTypeId) throws Exception;
}
