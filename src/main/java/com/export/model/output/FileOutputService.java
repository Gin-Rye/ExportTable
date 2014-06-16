package com.export.model.output;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.export.base.exception.BussinessException;
import com.export.model.configuration.Configuration;
import com.export.model.store.ResultStore;

public abstract class FileOutputService extends OutputService {
	
	public FileOutputService(Configuration sinkConfiguration) {
		super(sinkConfiguration);
	}
	
	@Override
	protected OutputStream createOutputStream(ResultStore resultStore) throws BussinessException {
		try {
			String filePath = getOutputFilePath(resultStore.getTableName());
			OutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(filePath));
			return outputStream;
		} catch(IOException e) {
			throw new BussinessException(e);
		}
	}
	
	protected String getOutputFilePath(String tableName) throws BussinessException {
		Map<String, String> properties = sinkConfiguration.getProperties();
		String destinationDir = properties.get("destinationDir");
		if(destinationDir == null) {
			throw new BussinessException("No destination directory");
		}
		String filePath = destinationDir + "/" + tableName + "." + getSuffix();
		return filePath;
	}
	
	protected abstract String getSuffix();
}
