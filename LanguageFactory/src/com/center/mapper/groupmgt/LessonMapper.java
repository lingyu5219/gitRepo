package com.center.mapper.groupmgt;

import java.util.List;

import com.center.po.groupmgt.Lesson;
import com.center.po.groupmgt.LessonQuery;

public interface LessonMapper {

	void addLesson(Lesson c);

	List<Lesson> getLessonList(LessonQuery cq);
	
	public List<Lesson> queryLesson(LessonQuery lessonQuery) throws Exception;

	public List<Lesson> queryLessonByGartenId(int gartenId) throws Exception;

	long count(LessonQuery cq);

	Integer updateLesson(Lesson c);

	Integer delLessonById(String lessonId);

}
