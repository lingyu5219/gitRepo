package com.center.mapper.groupmgt;

import java.util.List;

import com.center.po.groupmgt.History;
import com.center.po.groupmgt.HistoryQuery;

public interface HistoryMapper {

	public List<History> queryHistory(HistoryQuery historyQuery) throws Exception;
	
	public List<History> queryHistoryList(HistoryQuery historyQuery) throws Exception;
	
	public long queryHistoryCount(HistoryQuery historyQuery) throws Exception;
	
	public History queryHistoryById(int historyId) throws Exception;

	public int addHistory(History history) throws Exception;

	public int updateHistory(History history) throws Exception;

	public int delHistory(int historyId) throws Exception;

}
