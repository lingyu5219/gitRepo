package com.center.po.groupmgt;

import java.util.List;

public class LessonQuery extends Lesson {
	private Integer gartenId;
	private Integer selectFlag;//表示是否已选，0是1不是
	private Integer isPage;//是否分页。0是1不是
	private List<Integer> lessonIdList;
	public List<Integer> getLessonIdList() {
		return lessonIdList;
	}
	public void setLessonIdList(List<Integer> lessonIdList) {
		this.lessonIdList = lessonIdList;
	}
	public Integer getGartenId() {
		return gartenId;
	}
	public void setGartenId(Integer gartenId) {
		this.gartenId = gartenId;
	}
	
	public Integer getSelectFlag() {
		return selectFlag;
	}
	public void setSelectFlag(Integer selectFlag) {
		this.selectFlag = selectFlag;
	}
	public Integer getIsPage() {
		return isPage;
	}
	public void setIsPage(Integer isPage) {
		this.isPage = isPage;
	}
	
	
}
