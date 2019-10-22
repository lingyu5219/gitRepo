package com.center.service.impl.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.center.mapper.cms.ColumnMapper;
import com.center.po.cms.Column;
import com.center.po.cms.ColumnQuery;
import com.center.po.query.DatatablesView;
import com.center.service.cms.ColumnService;
@Service
public class ColumnServiceImpl implements ColumnService {
	@Autowired
	ColumnMapper columnMapper;
	
	@Override
	public DatatablesView<Column> queryColumnList(ColumnQuery columnQuery) throws Exception  {
		DatatablesView<Column> dataView =new DatatablesView<Column>();
		Long count = columnMapper.queryColumnCount(columnQuery);
		List<Column> columns = columnMapper.queryColumnList(columnQuery); 
		dataView.setRecordsTotal(count);
		dataView.setData(columns);
		return dataView;
	}

	@Override
	public List<Column> queryColumn(ColumnQuery columnQuery) {
		List<Column> columnList = columnMapper.queryColumn(columnQuery);
		return columnList;
	}

	@Override
	public void addColumn(Column column) {
		columnMapper.addColumn(column);
	}
	
	@Override
	public boolean deleteColumnById(int columnId) {
		 return columnMapper.delColumn(columnId)>0?true:false;
	}

	@Override
	public boolean updateColumn(Column column) {
		return columnMapper.updateColumn(column)>0?true:false;
	}

	
	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.center.service.cms.ColumnService#queryColumnTree(com.center.po.cms.ColumnQuery)
	*/
	
	@Override
	public List<Column> queryColumnTree(ColumnQuery columnQuery) throws Exception {
		List<Column> columnList = columnMapper.queryColumn(columnQuery);
		for(int i = 0; i < columnList.size(); i++){
			ColumnQuery cq = new ColumnQuery();
			Column cm = columnList.get(i);
			cq.setColumnParentId(cm.getColumnId());
			if(columnQuery.getColumnEnable() != 0){
				cq.setColumnEnable(columnQuery.getColumnEnable());
			}
			List<Column> colList = queryColumnTree(cq);
			cm.setChildColumnList(colList);
		}
		return columnList;
		
	}

	
	/**
	* TODO 栏目排序上移.
	* @see com.center.service.cms.ColumnService#upSortColumn(com.center.po.cms.Column)
	*/
	
	@Override
	@Transactional
	public boolean upSortColumn(int columnId) throws Exception {
		//根据当前栏目ID获取前一条栏目
		Column preColumn = columnMapper.queryPreColumn(columnId);
		Column curColumn = columnMapper.queryColumnById(columnId);
		//暂存当前栏目的排序序号
		int curColumnSort = curColumn.getColumnSort();
		
		//将当前栏目的排序序号更改为前一条栏目的排序序号
		curColumn.setColumnSort(preColumn.getColumnSort());
		//更新当前栏目排序序号
		int updateColumnFlag = columnMapper.updateColumn(curColumn);
		
		//将前一条栏目的排序序号更改为当前栏目的排序序号，实现上移（即两者更换columnSort的值）
		preColumn.setColumnSort(curColumnSort);
		int updatePreColumnFlag = columnMapper.updateColumn(preColumn);
		
		return updateColumnFlag > 0 && updatePreColumnFlag > 0;
		
	}

	
	/**
	* TODO 栏目排序下移.
	* @see com.center.service.cms.ColumnService#downSortColumn(com.center.po.cms.Column)
	*/
	
	@Override
	@Transactional
	public boolean downSortColumn(int columnId) throws Exception {
		//根据当前栏目ID获取前一条栏目
		Column nextColumn = columnMapper.queryNextColumn(columnId);
		Column curColumn = columnMapper.queryColumnById(columnId);
		//暂存当前栏目的排序序号
		int curColumnSort = curColumn.getColumnSort();
		
		//将当前栏目的排序序号更改为前一条栏目的排序序号
		curColumn.setColumnSort(nextColumn.getColumnSort());
		//更新当前栏目排序序号
		int updateColumnFlag = columnMapper.updateColumn(curColumn);
		
		//将前一条栏目的排序序号更改为当前栏目的排序序号，实现上移（即两者更换columnSort的值）
		nextColumn.setColumnSort(curColumnSort);
		int updateNextColumnFlag = columnMapper.updateColumn(nextColumn);
		
		return updateColumnFlag > 0 && updateNextColumnFlag > 0;
	}
	
	@Override
	public Column queryColumnById(int columnId) throws Exception {
		
		return columnMapper.queryColumnById(columnId);
	}
}
