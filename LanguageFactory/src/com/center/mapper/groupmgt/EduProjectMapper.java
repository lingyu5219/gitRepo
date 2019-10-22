package com.center.mapper.groupmgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.center.po.groupmgt.EduProject;
import com.center.po.groupmgt.EduProjectQuery;

public interface EduProjectMapper {

	void addEduProject(EduProject c);

	List<EduProject> getEduProjectList(EduProjectQuery cq);
	
	public List<EduProject> queryEduProjects(EduProjectQuery cq) throws Exception;

	long count(EduProjectQuery cq);

	Integer updateEduProject(EduProject c);

	Integer delEduProjectById(String eduProjectId);

	Integer deleteSeletedEduProject(@Param("staffGartenId")int staffGartenId, @Param("eduProjectId")Integer eduProjectId);

	Integer addEduProject2Selected(EduProjectQuery query);

}
