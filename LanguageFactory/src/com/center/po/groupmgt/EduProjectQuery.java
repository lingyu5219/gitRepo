package com.center.po.groupmgt;

import java.util.List;

public class EduProjectQuery extends EduProject{
	private Integer selectFlag;//表示是否已选，0是1不是
	private List<Integer> eduProjectList;
	public Integer getSelectFlag() {
		return selectFlag;
	}
	public void setSelectFlag(Integer selectFlag) {
		this.selectFlag = selectFlag;
	}
	public List<Integer> getEduProjectList() {
		return eduProjectList;
	}
	public void setEduProjectList(List<Integer> eduProjectList) {
		this.eduProjectList = eduProjectList;
	}
	
}
