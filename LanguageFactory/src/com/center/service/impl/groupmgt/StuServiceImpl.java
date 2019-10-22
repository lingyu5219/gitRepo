
package com.center.service.impl.groupmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.groupmgt.StuMapper;
import com.center.po.groupmgt.Stu;
import com.center.po.groupmgt.StuQuery;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.StuService;
@Service
public class StuServiceImpl implements StuService {

	@Autowired
	private StuMapper studentMapper;
	
	@Override
	public List<Stu> queryStu(StuQuery studentQuery) throws Exception {

		return studentMapper.queryStu(studentQuery);

	}

	@Override
	public DatatablesView<Stu> queryStuList(StuQuery studentQuery) throws Exception {

		DatatablesView<Stu> dataView = new DatatablesView<Stu>();
		Long count = studentMapper.queryStuCount(studentQuery);
		List<Stu> stuList = studentMapper.queryStuList(studentQuery);		
		
		dataView.setData(stuList);
		dataView.setRecordsTotal(count);
		return dataView;
	}

	@Override
	public boolean addStu(Stu student, HttpServletRequest request) throws Exception {
		
		studentMapper.addStu(student);;

		return student.getStuId() > 0;

	}

	@Override
	public boolean updateStu(Stu student) throws Exception {

		return studentMapper.updateStu(student) > 0;

	}

	@Override
	public boolean delStu(int stuId) throws Exception {

		return studentMapper.delStu(stuId) > 0;

	}

	@Override
	public boolean stuIdCardExistNotSelf(StuQuery studentQuery) throws Exception {

		return studentMapper.stuIdCardExistNotSelf(studentQuery) > 0;

	}

	@Override
	public boolean stuIdCardExist(StuQuery studentQuery) throws Exception {

		return studentMapper.stuIdCardExist(studentQuery) > 0;

	}

}

