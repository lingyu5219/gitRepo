package com.center.service.impl.orgmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.orgmgt.GartenMapper;
import com.center.po.orgmgt.Garten;
import com.center.po.orgmgt.GartenQuery;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.orgmgt.GartenService;

@Service
public class GartenServiceImpl implements GartenService {

	@Autowired
	private GartenMapper gartenMapper;

	@Override
	public Garten queryGartenById(int gartenId) throws Exception {
		return gartenMapper.queryGartenById(gartenId);
	}
	
	@Override
	public List<Garten> queryGarten(GartenQuery gartenQuery) throws Exception {
		return gartenMapper.queryGarten(gartenQuery);
	}
	
	@Override
	public boolean addGarten(Garten garten, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User loginUser = (User)	session.getAttribute("user");
		//动态获取创建人名字 
		garten.setCreateBy(loginUser.getUserId());
		
		gartenMapper.addGarten(garten);
		if(garten.getGartenId() > 0){
			return true;
		}
		return false;
	}


	@Override
	public DatatablesView<Garten> queryGartenList(GartenQuery gartenQuery) throws Exception {
		DatatablesView<Garten> dataView = new DatatablesView<Garten>();
		
		Long count = gartenMapper.queryGartenCount(gartenQuery);
		List<Garten> gartenList = gartenMapper.queryGartenList(gartenQuery);
		
		dataView.setRecordsTotal(count);
	    dataView.setData(gartenList);
		return dataView ;
	}

	@Override
	public boolean delGarten(int gartenId) throws Exception {
		int affectedRecords = gartenMapper.delGarten(gartenId);
		if(affectedRecords > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateGarten(Garten garten) throws Exception {
		int affectedRecords = gartenMapper.updateGarten(garten);
		if(affectedRecords > 0){
			return true;
		} else {
			return false;
		}
	}

}

