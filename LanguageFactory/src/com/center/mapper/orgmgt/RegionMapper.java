package com.center.mapper.orgmgt;

import java.util.List;

import com.center.po.orgmgt.Region;
import com.center.po.orgmgt.RegionQuery;

public interface RegionMapper {
	
	public List<Region> queryRegion(RegionQuery regionQuery);

	public Long queryRegionCount(RegionQuery regionQuery); 

	public List<Region> queryRegionList(RegionQuery regionQuery);
	
	public Region queryRegionById(int regionId);
	
	public void addRegion(Region region);
	
	public int delRegion(int regionId);
	
	public int updateRegion(Region region);
}
