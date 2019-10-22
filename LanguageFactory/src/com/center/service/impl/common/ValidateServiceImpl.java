package com.center.service.impl.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.common.ValidateMapper;
import com.center.po.common.Validate;
import com.center.service.common.ValidateService;
@Service
public class ValidateServiceImpl implements ValidateService {
	@Autowired
	ValidateMapper validateMapper;
	
	@Override
	public boolean isUnique(Validate validate) throws Exception {
		long count = validateMapper.isUnique(validate);
		return !(count > 0);
	}
	
}
