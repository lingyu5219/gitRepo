package com.center.mapper.system;

import java.util.List;

import com.center.po.system.Log;
import com.center.po.system.LogQuery;

public interface LogMapper {
	public List<Log> queryLogList(LogQuery logQuery) throws Exception;
	
	public long queryLogCount(LogQuery logQuery) throws Exception;
	
	public int addLog(Log log) throws Exception;
	
}
