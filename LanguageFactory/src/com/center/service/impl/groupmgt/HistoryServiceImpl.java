package com.center.service.impl.groupmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.groupmgt.HistoryMapper;
import com.center.po.groupmgt.History;
import com.center.po.groupmgt.HistoryQuery;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.service.groupmgt.HistoryService;
@Service
public class HistoryServiceImpl implements HistoryService{

	@Autowired
	private HistoryMapper dao;

	@Override
	public List<History> queryHistory(HistoryQuery historyQuery) throws Exception{
		return dao.queryHistory(historyQuery);
	}
	
	@Override
	public History queryHistoryById(int historyId) throws Exception{
		return dao.queryHistoryById(historyId);
	}

	@Override
	public DatatablesView<History> queryHistoryList(HistoryQuery historyQuery) throws Exception{
		DatatablesView<History> dataView = new DatatablesView<History>();
		List<History> list = dao.queryHistoryList(historyQuery);
		long count = dao.queryHistoryCount(historyQuery);
		dataView.setRecordsTotal(count);
		dataView.setData(list);
		return dataView;
	}

	@Override
	public boolean addHistory(History history, HttpServletRequest request) throws Exception{
		//获取已登录的用户Id
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		history.setCreateBy(user.getUserId());
		
		dao.addHistory(history);
		
		return history.getHistoryId() > 0;
	}
	
	@Override
	public boolean updateHistory(History history) throws Exception{
		return dao.updateHistory(history) > 0;
	}

	@Override
	public boolean delHistory(int historyId) throws Exception{
		return dao.delHistory(historyId) > 0;
	}
}
