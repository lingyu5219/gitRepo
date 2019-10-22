package com.center.service.impl.groupmgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.groupmgt.ClassMapper;
import com.center.po.groupmgt.Class;
import com.center.po.groupmgt.ClassQuery;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.ClassService;
@Service
public class ClassServiceImpl implements ClassService{

	@Autowired
	private ClassMapper dao;
	@Override
	public boolean addClass(Class c) {
		dao.addClass(c);
		if(c.getClassId()>0) {
			return true;
		}
		return false;
	}
	@Override
	public DatatablesView<Class> getClassList(ClassQuery cq) {
		DatatablesView<Class> dataView = new DatatablesView<>();
		List<Class> list = dao.getClassList(cq);
		long count = dao.count(cq);
		dataView.setRecordsTotal(count);
		dataView.setData(list);
		return dataView;
	}
	@Override
	public Integer updateClass(Class c) {
		return dao.updateClass(c);
	}
	@Override
	public Integer delClassById(String classId) {
		return dao.delClassById(classId);
	}

}
