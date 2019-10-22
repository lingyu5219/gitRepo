package com.center.mapper.gartenmgt;

import java.util.List;

import com.center.po.gartenmgt.GartenClass;
import com.center.po.gartenmgt.GartenClassQuery;

public interface GartenClassMapper {

	void addGartenClass(GartenClass c);

	List<GartenClass> getGartenClassList(GartenClassQuery cq);

	long count(GartenClassQuery cq);

	Integer updateGartenClass(GartenClass c);

	Integer delGartenClassById(String classId);

}
