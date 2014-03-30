package com.export.data.input;

import com.export.data.store.ResultStore;
import com.export.exception.BussinessException;

public abstract class InputService {
	protected SourceConfiguration sourceConfiguration;
	
	public InputService(SourceConfiguration sourceConfiguration) {
		this.sourceConfiguration = sourceConfiguration;
	}
	
	public SourceConfiguration getSourceConfiguration() {
		return sourceConfiguration.clone();
	}
	
	public abstract ResultStore inputData(String command) throws BussinessException;
}
