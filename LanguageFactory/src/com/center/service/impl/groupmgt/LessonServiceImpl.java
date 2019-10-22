package com.center.service.impl.groupmgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.groupmgt.LessonMapper;
import com.center.po.groupmgt.Lesson;
import com.center.po.groupmgt.LessonQuery;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.LessonService;
@Service
public class LessonServiceImpl implements LessonService{

	@Autowired
	private LessonMapper dao;
	@Override
	public boolean addLesson(Lesson c) {
		dao.addLesson(c);
		if(c.getLessonId()>0) {
			return true;
		}
		return false;
	}
	@Override
	public DatatablesView<Lesson> getLessonList(LessonQuery cq) {
		DatatablesView<Lesson> dataView = new DatatablesView<>();
		List<Lesson> list = dao.getLessonList(cq);
		long count = dao.count(cq);
		dataView.setRecordsTotal(count);
		dataView.setData(list);
		return dataView;
	}
	@Override
	public Integer updateLesson(Lesson c) {
		return dao.updateLesson(c);
	}
	@Override
	public Integer delLessonById(String lessonId) {
		return dao.delLessonById(lessonId);
	}
	
	@Override
	public List<Lesson> queryLesson(LessonQuery lessonQuery) throws Exception {
		
		return dao.queryLesson(lessonQuery);
	}
	
	@Override
	public List<Lesson> queryLessonByGartenId(int gartenId) throws Exception {
		
		return dao.queryLessonByGartenId(gartenId);
	}

}
