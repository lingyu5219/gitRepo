
/**
* Project Name:trainingCenter
* File Name:DatatablesView.java
* Package Name:com.center.utils
* Date:2018年6月25日下午2:40:04
* Copyright (c) 2016, Tony All Rights Reserved.
*
*/

package com.center.po.query;

import java.util.List;

/**
* ClassName:DatatablesView <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2018年6月25日 下午2:40:04 <br/>
* @author Tony
* @version
* @see
*/
public class DatatablesView<T> {
	private List<T> data; //data 与datatales 加载的“dataSrc"对应  

    private Long recordsTotal;   

    private Long recordsFiltered;  

    private Integer draw;

    public DatatablesView() {  

    }

	public List<T> getData() {
	
		return data;
	}

	public void setData(List<T> data) {
	
		this.data = data;
	}

	public Long getRecordsTotal() {
	
		return recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
	
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsTotal;
	}

	public Long getRecordsFiltered() {
	
		return recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
	
		this.recordsFiltered = recordsFiltered;
	}

	public Integer getDraw() {
	
		return draw;
	}

	public void setDraw(Integer draw) {
	
		this.draw = draw;
	}

}

