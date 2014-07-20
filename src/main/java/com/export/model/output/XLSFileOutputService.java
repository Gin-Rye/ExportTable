package com.export.model.output;

import com.export.model.configuration.FileSinkConfiguration;
import com.export.model.output.export.Exporter;
import com.export.model.output.export.XLSExporter;

public class XLSFileOutputService extends FileOutputService {

	public XLSFileOutputService(FileSinkConfiguration fileSinkConfiguration) {
		super(fileSinkConfiguration);
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
