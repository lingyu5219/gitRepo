package com.center.service.groupmgt;

import java.util.List;

import com.center.po.groupmgt.EduProject;
import com.center.po.groupmgt.EduProjectQuery;
import com.center.po.query.DatatablesView;

public interface EduProjectService {

	boolean addEduProject(EduProject c);

	DatatablesView<EduProject> getEduProjectList(EduProjectQuery cq);

	Integer updateEduProject(EduProject c);

	Integer delEduProjectById(String eduProjectId);

	Integer deleteSeletedEduProject(int staffGartenId, Integer eduProjectId);

	Integer addEduProject2Selected(EduProjectQuery query);
	
	public List<EduProject> queryEduProjects(EduProjectQuery cq) throws Exception;

}
