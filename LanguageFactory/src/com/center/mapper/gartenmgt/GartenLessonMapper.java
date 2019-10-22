package com.center.mapper.gartenmgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.center.po.groupmgt.LessonQuery;

public interface GartenLessonMapper {

	Integer deleteAllSelectedLesson(Integer gartenId);

	Integer addSelectedLesson(LessonQuery query);

	Integer deleteSelectedLesson(@Param("gartenId")int staffGartenId, @Param("lessonId")Integer lessonId);

	Integer addLesson2Selected(LessonQuery query);

}
