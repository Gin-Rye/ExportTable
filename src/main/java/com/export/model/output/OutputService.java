package com.export.model.output;

import java.io.IOException;
import java.io.OutputStream;

import com.export.base.exception.BussinessException;
import com.export.model.configuration.Configuration;
import com.export.model.output.export.Exporter;
import com.export.model.store.ResultStore;

public abstract class OutputService {
	
	protected Configuration sinkConfiguration;
	
	public OutputService(Configuration sinkConfiguration) {
		this.sinkConfiguration = sinkConfiguration;
	}
	
	public Configuration getSinkConfiguration() {
		return sinkConfiguration.clone();
	}

	public void outputData(ResultStore resultStore) throws BussinessException {
		try {
			Exporter exporter = createExporter();
			OutputStream outputStream = createOutputStream(resultStore);
			resultStore.moveBeforeFirst();
			exporter.export(outputStream, resultStore);
			outputStream.close();
		} catch(IOException e) {
			throw new BussinessException(e);
		}
	}
	
	public void effectiveOutputData(ResultStore resultStore) throws BussinessException {
		try {
			Exporter exporter = createExporter();
			OutputStream outputStream = createOutputStream(resultStore);
			resultStore.moveBeforeFirst();
			exporter.effectiveExport(outputStream, resultStore);
			outputStream.close();
		} catch(IOException e) {
			throw new BussinessException(e);
		}
	}
	
	protected abstract OutputStream createOutputStream(ResultStore resultStore) throws BussinessException;
	
	protected abstract Exporter createExporter();
}
