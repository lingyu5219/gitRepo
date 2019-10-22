package com.center.editor;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

public class BigDecimalEditor extends PropertyEditorSupport{

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(text==null||"".equals(text)){
			text ="0";
		}
		BigDecimal bd = new BigDecimal(text);
		setValue(bd);
	}
	
	  @Override    
	  public String getAsText() {    
	      return getValue().toString();    
	  } 
}
