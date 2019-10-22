package com.center.controller.common;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.center.editor.BigDecimalEditor;
import com.center.editor.DateEditor;
import com.center.editor.DoubleEditor;
import com.center.editor.FloatEditor;
import com.center.editor.IntegerEditor;
import com.center.editor.LongEditor;

public class BaseController {

	  @InitBinder  
	  protected void initBinder(HttpServletRequest request,  
	                                ServletRequestDataBinder binder) throws Exception {  
	      //对于需要转换为Date类型的属性，使用DateEditor进行处理  
	      binder.registerCustomEditor(Date.class, new DateEditor()); 
	      binder.registerCustomEditor(int.class, new IntegerEditor()); 
	      binder.registerCustomEditor(double.class, new DoubleEditor());
	      binder.registerCustomEditor(float.class, new FloatEditor());
	      binder.registerCustomEditor(long.class, new LongEditor());
	      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
	  }
}
