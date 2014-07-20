package com.export.model.output;

import com.export.model.configuration.FileSinkConfiguration;
import com.export.model.output.export.Exporter;
import com.export.model.output.export.H2XmlExporter;

public class H2XmlFileOutputService extends FileOutputService {

	public H2XmlFileOutputService(FileSinkConfiguration fileSinkConfiguration) {
		super(fileSinkConfiguration);
	}
	
	@Override
	protected Exporter createExporter() {
		Exporter exporter = new H2XmlExporter();
		return exporter;
	}
	
	@Override
	protected String getSuffix() {
		return "h2.xml";
	}
}
