package com.center.service.impl.groupmgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.groupmgt.EduProjectMapper;
import com.center.po.groupmgt.EduProject;
import com.center.po.groupmgt.EduProjectQuery;
import com.center.po.query.DatatablesView;
import com.center.service.groupmgt.EduProjectService;
@Service
public class EduProjectServiceImpl implements EduProjectService{

	@Autowired
	private EduProjectMapper dao;
	@Override
	public boolean addEduProject(EduProject c) {
		dao.addEduProject(c);
		if(c.getEduProjectId()>0) {
			return true;
		}
		return false;
	}
	@Override
	public DatatablesView<EduProject> getEduProjectList(EduProjectQuery cq) {
		DatatablesView<EduProject> dataView = new DatatablesView<>();
		List<EduProject> list = dao.getEduProjectList(cq);
		long count = dao.count(cq);
		dataView.setRecordsTotal(count);
		dataView.setData(list);
		return dataView;
	}
	@Override
	public Integer updateEduProject(EduProject c) {
		return dao.updateEduProject(c);
	}
	@Override
	public Integer delEduProjectById(String eduProjectId) {
		return dao.delEduProjectById(eduProjectId);
	}
	@Override
	public Integer deleteSeletedEduProject(int staffGartenId, Integer eduProjectId) {
		
		return dao.deleteSeletedEduProject(staffGartenId,eduProjectId);
	}
	@Override
	public Integer addEduProject2Selected(EduProjectQuery query) {
		return dao.addEduProject2Selected(query);
	}
	
	@Override
	public List<EduProject> queryEduProjects(EduProjectQuery cq) throws Exception {
		
		return dao.queryEduProjects(cq);
	}

}
