package com.center.service.gartenmgt;

import java.util.List;

import com.center.po.groupmgt.LessonQuery;

public interface GartenLessonService {

	boolean selectLesson(LessonQuery query);

	Integer deleteSelectedLesson(int staffGartenId, Integer lessonId);

	Integer addLesson2Selected(LessonQuery query);

}
