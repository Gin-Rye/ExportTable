package com.export.model.input;

import com.export.base.exception.BussinessException;
import com.export.model.configuration.Configuration;
import com.export.model.store.ResultStore;

public abstract class InputService {
	protected Configuration sourceConfiguration;
	
	public InputService(Configuration sourceConfiguration) {
		this.sourceConfiguration = sourceConfiguration;
	}
	
	public Configuration getSourceConfiguration() {
		return sourceConfiguration.clone();
	}
	
	public abstract ResultStore inputData(String command) throws BussinessException;
}
