package com.center.mapper.cms;

import java.util.List;

import com.center.po.cms.Column;
import com.center.po.cms.ColumnQuery;

public interface ColumnMapper {
	
	public List<Column> queryColumn(ColumnQuery columnQuery);

	public Long queryColumnCount(ColumnQuery columnQuery); 

	public List<Column> queryColumnList(ColumnQuery columnQuery);
	
	public Column queryColumnById(int columnId);
	
	public Column queryPreColumn(int columnId);
	
	public Column queryNextColumn(int columnId);
	
	public void addColumn(Column column);
	
	public int delColumn(int columnId);
	
	public int updateColumn(Column column);
}
