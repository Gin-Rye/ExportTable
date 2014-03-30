package com.export.data.output;

import com.export.exception.BussinessException;

public class OutputServiceFactory {
	
	public static OutputService getOutputService(
			SinkConfiguration sinkConfiguration) throws BussinessException{
		SinkType sinkType = sinkConfiguration.getSinkType();
		switch(sinkType) {
		case FILE:
			return generateFileOutputService(sinkConfiguration);
		default:
			throw new BussinessException("Not found OutputService of " + sinkType);
		}
	}
	
	private static OutputService generateFileOutputService(
			SinkConfiguration sinkConfiguration) throws BussinessException{
		String fileFormat = sinkConfiguration.getProperties().get("file-format");
		String contentFormat = sinkConfiguration.getProperties().get("content-format");
		if(fileFormat.equalsIgnoreCase("XML")) {
			if(contentFormat.equalsIgnoreCase("H2")) {
				return new H2XmlFileOutputService(sinkConfiguration);
			}
		}
		throw new BussinessException("Unsupported output file format");
	}
}
