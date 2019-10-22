package com.center.mapper.orgmgt;

import java.util.List;

import com.center.po.orgmgt.Garten;
import com.center.po.orgmgt.GartenQuery;

public interface GartenMapper {
	
	public Garten queryGartenById(int gartenId) throws Exception;

	public List<Garten> queryGarten(GartenQuery gartenQuery) throws Exception;
	
	public List<Garten> queryGartenList(GartenQuery gartenQuery) throws Exception;
	
	public Long queryGartenCount(GartenQuery gartenQuery) throws Exception;

	public void addGarten(Garten garten) throws Exception;
	
	public int delGarten(int gartenId) throws Exception;
	
	public int updateGarten(Garten garten) throws Exception;
}

