package com.center.service.groupmgt;

import java.util.List;

import com.center.po.groupmgt.Lesson;
import com.center.po.groupmgt.LessonQuery;
import com.center.po.query.DatatablesView;

public interface LessonService {

	boolean addLesson(Lesson c);

	DatatablesView<Lesson> getLessonList(LessonQuery cq);
	
	public List<Lesson> queryLesson(LessonQuery lessonQuery) throws Exception;	
	
	public List<Lesson> queryLessonByGartenId(int gartenId) throws Exception;

	Integer updateLesson(Lesson c);

	Integer delLessonById(String lessonId);

}
