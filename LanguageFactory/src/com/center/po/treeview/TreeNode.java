
/**
* Project Name:kindergarten
* File Name:TreeNode.java
* Package Name:com.center.po.treeview
* Date:2018年6月6日下午9:03:24
* Copyright (c) 2018, Tony All Rights Reserved.
*
*/

/**
* Project Name:kindergarten
* File Name:TreeNode.java
* Package Name:com.center.po.treeview
* Date:2018年6月6日下午9:03:24
* Copyright (c) 2018, Tony All Rights Reserved.
*
*/


package com.center.po.treeview;

import java.util.HashMap;
import java.util.List;

/**
* ClassName:TreeNode <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月6日 下午9:03:24 <br/>
* @author Tony
* @version
* @see
*/
public class TreeNode {
	private int id;
	private String text;
	private List<TreeNode> nodes;
	private HashMap<String,Boolean> state = new HashMap<String,Boolean>();
	
	public HashMap<String, Boolean> getState() {
	
		return state;
	}
	public void setState(HashMap<String, Boolean> state) {
	
		this.state = state;
	}
	public int getId() {
	
		return id;
	}
	public void setId(int id) {
	
		this.id = id;
	}
	public String getText() {
	
		return text;
	}
	public void setText(String text) {
	
		this.text = text;
	}
	public List<TreeNode> getNodes() {
	
		return nodes;
	}
	public void setNodes(List<TreeNode> nodes) {
	
		this.nodes = nodes;
	}
}

