package com.export.model.configuration;

public abstract class SourceConfiguration extends Configuration {
	public SourceConfiguration(String type) {
		super(type);
	}
	
	@Override
	public SourceConfiguration clone() {
		return (SourceConfiguration) super.clone();
	}
}
