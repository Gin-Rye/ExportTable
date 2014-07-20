package com.export.model.output;

import java.io.IOException;
import java.io.OutputStream;

import com.export.base.exception.BusinessException;
import com.export.model.configuration.Configuration;
import com.export.model.configuration.SinkConfiguration;
import com.export.model.output.export.Exporter;
import com.export.model.store.ResultStore;

public abstract class OutputService {
	
	protected SinkConfiguration sinkConfiguration;
	
	public OutputService(SinkConfiguration sinkConfiguration) {
		this.sinkConfiguration = sinkConfiguration;
	}
	
	public Configuration getSinkConfiguration() {
		return sinkConfiguration.clone();
	}

	public void outputData(ResultStore resultStore) throws BusinessException {
		try {
			Exporter exporter = createExporter();
			OutputStream outputStream = createOutputStream(resultStore);
			resultStore.moveBeforeFirst();
			exporter.export(outputStream, resultStore);
			outputStream.close();
		} catch(IOException e) {
			throw new BusinessException(e);
		}
	}
	
	public void effectiveOutputData(ResultStore resultStore) throws BusinessException {
		try {
			Exporter exporter = createExporter();
			OutputStream outputStream = createOutputStream(resultStore);
			resultStore.moveBeforeFirst();
			exporter.effectiveExport(outputStream, resultStore);
			outputStream.close();
		} catch(IOException e) {
			throw new BusinessException(e);
		}
	}
	
	protected abstract OutputStream createOutputStream(ResultStore resultStore) throws BusinessException;
	
	protected abstract Exporter createExporter();
}
