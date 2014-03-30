package com.export.data.output;

import java.util.List;

import com.export.data.store.ResultStore;
import com.export.exception.BussinessException;

public abstract class OutputService {
	
	protected SinkConfiguration sinkConfiguration;
	
	public OutputService(SinkConfiguration sinkConfiguration) {
		this.sinkConfiguration = sinkConfiguration;
	}
	
	public SinkConfiguration getSinkConfiguration() {
		return sinkConfiguration.clone();
	}

	public abstract void outputData(String tableName, ResultStore resultStore) throws BussinessException;
	
	public abstract void effectiveOutputData(String tableName, ResultStore resultStore) throws BussinessException;

	protected List<String> getColumnNames(ResultStore resultStore) {
		return resultStore.getColumnNames();
	}
	
	protected List<Class<?>> getColumnClasses(ResultStore resultStore) {
		return resultStore.getColumnClasses();
	}
}
