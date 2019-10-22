package com.center.service.cms;

import java.util.List;

import com.center.po.cms.Column;
import com.center.po.cms.ColumnQuery;
import com.center.po.query.DatatablesView;

public interface ColumnService {
	public List<Column> queryColumnTree(ColumnQuery columnQuery) throws Exception;
	
	public List<Column> queryColumn(ColumnQuery columnQuery) throws Exception;
	
	public DatatablesView<Column> queryColumnList(ColumnQuery columnQuery) throws Exception;
	
	public void addColumn(Column column) throws Exception; 
	
	public boolean deleteColumnById(int columnId) throws Exception;
	
	public boolean updateColumn(Column column) throws Exception;
	
	public boolean upSortColumn(int columnId) throws Exception;
	
	public boolean downSortColumn(int columnId) throws Exception;

	public Column queryColumnById(int columnId) throws Exception;
}

