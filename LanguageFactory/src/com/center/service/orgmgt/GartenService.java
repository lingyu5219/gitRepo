package com.center.service.orgmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.center.po.orgmgt.Garten;
import com.center.po.orgmgt.GartenQuery;
import com.center.po.query.DatatablesView;

/**
* ClassName:GartenService <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月22日 下午2:16:07 <br/>
* @author Tony
* @version
* @see
*/
public interface GartenService {

	public Garten queryGartenById(int gartenId) throws Exception;
	
	public List<Garten> queryGarten(GartenQuery gartenQuery) throws Exception;
	
	public DatatablesView<Garten> queryGartenList(GartenQuery gartenQuery) throws Exception;
	
	public boolean addGarten(Garten garten,HttpServletRequest request) throws Exception;
	
	public boolean delGarten(int gartenId) throws Exception;
	
	public boolean updateGarten(Garten garten) throws Exception;
	
}

