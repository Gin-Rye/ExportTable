package com.export.model.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.export.base.exception.BussinessException;
import com.export.model.configuration.Configuration;
import com.export.model.output.content.H2XmlUtils;
import com.export.model.store.ResultStore;

public class H2XmlFileOutputService extends FileOutputService {

	public H2XmlFileOutputService(Configuration sinkConfiguration) {
		super(sinkConfiguration);
	}
	
	@Override
	protected void outputToFile(String path, String tableName,
			ResultStore resultStore) throws BussinessException {
		try {
			Writer writer = new BufferedWriter(new FileWriter(path, false));
			H2XmlUtils.outputData(writer, tableName, resultStore);
			writer.close();
		} catch (IOException e) {
			throw new BussinessException(e);
		}
	}

	@Override
	protected void effectiveOutputToFile(String path, String tableName,
			ResultStore resultStore) throws BussinessException {
		try {
			Writer writer = new BufferedWriter(new FileWriter(path, false));
			H2XmlUtils.effectiveOutputData(writer, tableName, resultStore);
			writer.close();
		} catch (IOException e) {
			throw new BussinessException(e);
		}
	}
	
	@Override
	protected String getSuffix() {
		return "h2.xml";
	}
}
