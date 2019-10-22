package com.center.service.groupmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.center.po.groupmgt.Stu;
import com.center.po.groupmgt.StuQuery;
import com.center.po.query.DatatablesView;

public interface StuService {

	public List<Stu> queryStu(StuQuery studentQuery) throws Exception;

	public DatatablesView<Stu> queryStuList(StuQuery studentQuery) throws Exception;

	public boolean addStu(Stu student, HttpServletRequest request) throws Exception;

	public boolean updateStu(Stu student) throws Exception;

	public boolean delStu(int stuId) throws Exception;
	
	public boolean stuIdCardExistNotSelf(StuQuery studentQuery) throws Exception;

	public boolean stuIdCardExist(StuQuery studentQuery) throws Exception;

}
