package com.center.service.impl.gartenmgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.gartenmgt.GartenLessonMapper;
import com.center.po.groupmgt.LessonQuery;
import com.center.service.gartenmgt.GartenLessonService;

@Service
public class GartenLessonServiceImpl implements GartenLessonService{
	@Autowired
	private GartenLessonMapper dao;

	@Override
	public boolean selectLesson(LessonQuery query) {
		//先删除
		Integer delete = dao.deleteAllSelectedLesson(query.getGartenId());
		//后新增
		Integer add = dao.addSelectedLesson(query);
		if (add>0) {
			return true;
		}
		return false;
	}

	@Override
	public Integer deleteSelectedLesson(int staffGartenId, Integer lessonId) {
		return dao.deleteSelectedLesson(staffGartenId,lessonId);
	}

	@Override
	public Integer addLesson2Selected(LessonQuery query) {
		return dao.addLesson2Selected(query);
	}

	
}
