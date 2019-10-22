
/**
* Project Name:kindergarten
* File Name:RegionServiceImpl.java
* Package Name:com.center.service.impl.orgmgt
* Date:2018年6月13日上午11:04:07
* Copyright (c) 2018, Tony All Rights Reserved.
*
*/

/**
* Project Name:kindergarten
* File Name:RegionServiceImpl.java
* Package Name:com.center.service.impl.orgmgt
* Date:2018年6月13日上午11:04:07
* Copyright (c) 2018, Tony All Rights Reserved.
*
*/


package com.center.service.impl.orgmgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.orgmgt.RegionMapper;
import com.center.po.orgmgt.Region;
import com.center.po.orgmgt.RegionQuery;
import com.center.po.query.DatatablesView;
import com.center.service.orgmgt.RegionService;
@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	RegionMapper regionMapper;
	
	@Override
	public List<Region> queryRegionTree(RegionQuery regionQuery) throws Exception {
		List<Region> regionList = regionMapper.queryRegion(regionQuery);
		for(int i = 0; i < regionList.size(); i++){
			RegionQuery rq = new RegionQuery();
			Region rg = regionList.get(i);
			rq.setRegionParentId(rg.getRegionId());
			List<Region> rgList = queryRegionTree(rq);
			rg.setChildRegionList(rgList);
		}
		return regionList;
		
	}
	
	@Override
	public List<Region> queryRegion(RegionQuery regionQuery) throws Exception {
		List<Region> regionList = regionMapper.queryRegion(regionQuery);
		return regionList;
	}

	@Override
	public DatatablesView<Region> queryRegionList(RegionQuery regionQuery) throws Exception {
		DatatablesView<Region> dataView =new DatatablesView<Region>();
		Long count = regionMapper.queryRegionCount(regionQuery);
		List<Region> regions = regionMapper.queryRegionList(regionQuery); 
		dataView.setRecordsTotal(count);
		dataView.setData(regions);
		return dataView;
	}

	@Override
	public void addRegion(Region region) throws Exception {
		regionMapper.addRegion(region);
	}

	@Override
	public boolean delRegion(int regionId) throws Exception {
		return regionMapper.delRegion(regionId) > 0 ? true : false;
	}

	@Override
	public boolean updateRegion(Region region) throws Exception {
		return regionMapper.updateRegion(region) > 0 ? true : false;
	}

}

