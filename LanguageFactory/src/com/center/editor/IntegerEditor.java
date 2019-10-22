package com.center.editor;

import java.beans.PropertyEditorSupport;

public class IntegerEditor  extends PropertyEditorSupport{

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(text==null||"".equals(text)){
			text = "0";
		}
		setValue(Integer.parseInt(text));
	}
	
	@Override
	public String getAsText() {
		return getValue().toString();
	}
}
