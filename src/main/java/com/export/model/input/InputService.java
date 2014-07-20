package com.export.model.input;

import com.export.base.exception.BusinessException;
import com.export.model.configuration.SourceConfiguration;
import com.export.model.input.query.QueryCommand;
import com.export.model.store.ResultStore;

public abstract class InputService<T extends QueryCommand> {
	protected SourceConfiguration sourceConfiguration;
	
	public InputService(SourceConfiguration sourceConfiguration) {
		this.sourceConfiguration = sourceConfiguration;
	}
	
	public SourceConfiguration getSourceConfiguration() {
		return sourceConfiguration.clone();
	}
	
	public abstract ResultStore inputData(T command) throws BusinessException;
}
