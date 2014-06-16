package com.export.model.output;

import com.export.model.configuration.Configuration;
import com.export.model.output.export.Exporter;
import com.export.model.output.export.XLSExporter;

public class XLSFileOutputService extends FileOutputService {

	public XLSFileOutputService(Configuration sinkConfiguration) {
		super(sinkConfiguration);
	}
	
	@Override
	public Exporter createExporter() {
		Exporter exporter = new XLSExporter();
		return exporter;
	}

	@Override
	protected String getSuffix() {
		return "xls";
	}

}
