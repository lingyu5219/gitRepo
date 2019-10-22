package com.center.service.gartenmgt;

import com.center.po.gartenmgt.GartenClass;
import com.center.po.gartenmgt.GartenClassQuery;
import com.center.po.query.DatatablesView;

public interface GartenClassService {

	boolean addGartenClass(GartenClass c);

	DatatablesView<GartenClass> getGartenClassList(GartenClassQuery cq);

	Integer updateGartenClass(GartenClass c);

	Integer delGartenClassById(String classId);

}
