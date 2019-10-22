package com.center.mapper.common;

import com.center.po.common.Validate;

public interface ValidateMapper {
	
	public long isUnique(Validate validate) throws Exception;
}
