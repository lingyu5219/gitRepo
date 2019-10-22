package com.center.service.orgmgt;

import java.util.List;

import com.center.po.orgmgt.Region;
import com.center.po.orgmgt.RegionQuery;
import com.center.po.query.DatatablesView;

public interface RegionService {
	public List<Region> queryRegionTree(RegionQuery regionQuery) throws Exception;
	
	public List<Region> queryRegion(RegionQuery regionQuery) throws Exception;
	
	public DatatablesView<Region> queryRegionList(RegionQuery regionQuery) throws Exception;
	
	public void addRegion(Region region) throws Exception; 
	
	public boolean delRegion(int regionId) throws Exception;
	
	public boolean updateRegion(Region region) throws Exception;
	
}

