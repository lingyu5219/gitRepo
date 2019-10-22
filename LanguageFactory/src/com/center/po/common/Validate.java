
package com.center.po.common;

import java.util.ArrayList;
import java.util.HashMap;

/**
* ClassName: Validate <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年7月10日 下午10:16:11 <br/>
*
* @author Tony
* @version 
*/

public class Validate {
	private String tableName;
	
	private ArrayList<HashMap<String,Object>> queryFieldList;

	public String getTableName() {
	
		return tableName;
	}

	public void setTableName(String tableName) {
	
		this.tableName = tableName;
	}

	public ArrayList<HashMap<String, Object>> getQueryFieldList() {
	
		return queryFieldList;
	}

	public void setQueryFieldList(ArrayList<HashMap<String, Object>> queryFieldList) {
	
		this.queryFieldList = queryFieldList;
	}
	
	
}

