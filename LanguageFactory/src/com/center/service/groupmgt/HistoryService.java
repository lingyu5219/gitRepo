package com.center.service.groupmgt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.center.po.groupmgt.History;
import com.center.po.groupmgt.HistoryQuery;
import com.center.po.query.DatatablesView;

public interface HistoryService {

	public List<History> queryHistory(HistoryQuery historyQuery) throws Exception;

	public DatatablesView<History> queryHistoryList(HistoryQuery historyQuery) throws Exception;

	public History queryHistoryById(int historyId) throws Exception;

	public boolean addHistory(History history, HttpServletRequest request) throws Exception;

	public boolean updateHistory(History history) throws Exception;

	public boolean delHistory(int historyId) throws Exception;

}
