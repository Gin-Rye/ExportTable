package com.export.control;

import com.export.base.exception.BusinessException;

public interface JobManager {
	
	void add(Job job) throws BusinessException;
	
	void remove(Job job) throws BusinessException;
	
	void start() throws BusinessException;
}
