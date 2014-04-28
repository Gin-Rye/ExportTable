package com.export.model.output;

import java.util.Map;

import com.export.base.exception.BussinessException;
import com.export.model.configuration.Configuration;
import com.export.model.store.ResultStore;

public abstract class FileOutputService extends OutputService {
	
	public FileOutputService(Configuration sinkConfiguration) {
		super(sinkConfiguration);
	}

	@Override
	public void outputData(ResultStore resultStore) throws BussinessException {
		String tableName = resultStore.getTableName();
		String filePath = getOutputFilePath(tableName);
		outputToFile(filePath, resultStore);
	}

	@Override
	public void effectiveOutputData(ResultStore resultStore) throws BussinessException {
		String tableName = resultStore.getTableName();
		String filePath = getOutputFilePath(tableName);
		effectiveOutputToFile(filePath, resultStore);
	}

	public abstract void outputToFile(String filePath, ResultStore resultStore) throws BussinessException;
	
	public abstract void effectiveOutputToFile(String filePath, ResultStore resultStore) throws BussinessException;
	
	protected abstract String getSuffix();
	
	protected String getOutputFilePath(String tableName) throws BussinessException {
		Map<String, String> properties = sinkConfiguration.getProperties();
		String destinationDir = properties.get("destinationDir");
		if(destinationDir == null) {
			throw new BussinessException("No destination directory");
		}
		String filePath = destinationDir + "/" + tableName + "." + getSuffix();
		return filePath;
	}
}
