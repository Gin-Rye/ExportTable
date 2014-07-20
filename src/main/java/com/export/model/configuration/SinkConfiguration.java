package com.export.model.configuration;

public abstract class SinkConfiguration extends Configuration {
	public SinkConfiguration(String type) {
		super(type);
	}
	
	@Override
	public SinkConfiguration clone() {
		return (SinkConfiguration) super.clone();
	}
}
