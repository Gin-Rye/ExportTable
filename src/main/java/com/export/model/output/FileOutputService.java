package com.export.model.output;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.export.base.exception.BusinessException;
import com.export.model.configuration.FileSinkConfiguration;
import com.export.model.store.ResultStore;

public abstract class FileOutputService extends OutputService {
	
	public FileOutputService(FileSinkConfiguration fileSinkConfiguration) {
		super(fileSinkConfiguration);
	}
	
	@Override
	protected OutputStream createOutputStream(ResultStore resultStore) throws BusinessException {
		try {
			String filePath = getOutputFilePath(resultStore.getTableName());
			OutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(filePath));
			return outputStream;
		} catch(IOException e) {
			throw new BusinessException(e);
		}
	}
	
	protected String getOutputFilePath(String tableName) throws BusinessException {
		FileSinkConfiguration fileSinkConfiguration = 
			(FileSinkConfiguration) sinkConfiguration;
		String destinationDir = fileSinkConfiguration.getDestinationDir();
		if(destinationDir == null) {
			throw new BusinessException("No destination directory");
		}
		String filePath = destinationDir + "/" + tableName + "." + getSuffix();
		return filePath;
	}
	
	protected abstract String getSuffix();
}
