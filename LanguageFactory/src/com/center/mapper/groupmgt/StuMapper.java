package com.center.mapper.groupmgt;

import java.util.List;

import com.center.po.groupmgt.Stu;
import com.center.po.groupmgt.StuQuery;

public interface StuMapper {
	
	public List<Stu> queryStu(StuQuery studentQuery) throws Exception;

	public Long queryStuCount(StuQuery studentQuery) throws Exception;

	public List<Stu> queryStuList(StuQuery studentQuery) throws Exception;
	
	public void addStu(Stu student) throws Exception;
	
	public int delStu(int stuId) throws Exception;
	
	public int updateStu(Stu student) throws Exception;
	
	public Long stuIdCardExistNotSelf(StuQuery studentQuery) throws Exception;

	public Long stuIdCardExist(StuQuery studentQuery) throws Exception;
}
