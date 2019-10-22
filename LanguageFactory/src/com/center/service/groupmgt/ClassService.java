package com.center.service.groupmgt;

import com.center.po.groupmgt.Class;
import com.center.po.groupmgt.ClassQuery;
import com.center.po.query.DatatablesView;

public interface ClassService {

	boolean addClass(Class c);

	DatatablesView<Class> getClassList(ClassQuery cq);

	Integer updateClass(Class c);

	Integer delClassById(String classId);

}
