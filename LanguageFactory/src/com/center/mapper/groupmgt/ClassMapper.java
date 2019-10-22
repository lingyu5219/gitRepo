package com.center.mapper.groupmgt;

import java.util.List;

import com.center.po.groupmgt.Class;
import com.center.po.groupmgt.ClassQuery;

public interface ClassMapper {

	void addClass(Class c);

	List<Class> getClassList(ClassQuery cq);

	long count(ClassQuery cq);

	Integer updateClass(Class c);

	Integer delClassById(String classId);

}
