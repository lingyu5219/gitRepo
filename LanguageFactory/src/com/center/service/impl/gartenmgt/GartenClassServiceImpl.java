package com.center.service.impl.gartenmgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.gartenmgt.GartenClassMapper;
import com.center.po.gartenmgt.GartenClass;
import com.center.po.gartenmgt.GartenClassQuery;
import com.center.po.query.DatatablesView;
import com.center.service.gartenmgt.GartenClassService;
@Service
public class GartenClassServiceImpl implements GartenClassService{

	@Autowired
	private GartenClassMapper dao;
	@Override
	public boolean addGartenClass(GartenClass c) {
		dao.addGartenClass(c);
		if(c.getClassId()>0) {
			return true;
		}
		return false;
	}
	@Override
	public DatatablesView<GartenClass> getGartenClassList(GartenClassQuery cq) {
		DatatablesView<GartenClass> dataView = new DatatablesView<>();
		List<GartenClass> list = dao.getGartenClassList(cq);
		long count = dao.count(cq);
		dataView.setRecordsTotal(count);
		dataView.setData(list);
		return dataView;
	}
	@Override
	public Integer updateGartenClass(GartenClass c) {
		return dao.updateGartenClass(c);
	}
	@Override
	public Integer delGartenClassById(String classId) {
		return dao.delGartenClassById(classId);
	}

}
