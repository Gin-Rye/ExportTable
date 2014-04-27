package com.export.model.output;

import com.export.base.exception.BussinessException;
import com.export.model.configuration.Configuration;
import com.export.model.store.ResultStore;

public abstract class OutputService {
	
	protected Configuration sinkConfiguration;
	
	public OutputService(Configuration sinkConfiguration) {
		this.sinkConfiguration = sinkConfiguration;
	}
	
	public Configuration getSinkConfiguration() {
		return sinkConfiguration.clone();
	}

	public abstract void outputData(String tableName, ResultStore resultStore) throws BussinessException;
	
	public abstract void effectiveOutputData(String tableName, ResultStore resultStore) throws BussinessException;
}
