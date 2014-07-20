package com.export.model.configuration;

public class FileSinkConfiguration extends SinkConfiguration {
	protected String destinationDir;
	
	public FileSinkConfiguration(String type) {
		super(type);
	}

	public String getDestinationDir() {
		return destinationDir;
	}

	public void setDestinationDir(String destinationDir) {
		this.destinationDir = destinationDir;
	}
}
