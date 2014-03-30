package com.export.data.input;

import java.util.HashMap;
import java.util.Map;

public class SourceConfiguration implements Cloneable {
	
	protected SourceType sourceType;
	protected Map<String, String> properties = new HashMap<String, String>();

	@Override
	public SourceConfiguration clone() {
		try {
			return (SourceConfiguration) super.clone();
		} catch(CloneNotSupportedException e) {
			RuntimeException exception = new RuntimeException(e.getMessage());
			exception.setStackTrace(e.getStackTrace());
			throw exception;
		}
	}

	public SourceType getSourceType() {
		return sourceType;
	}

	public void setSourceType(SourceType sourceType) {
		this.sourceType = sourceType;
	}
	
	public Map<String, String> getProperties() {
		return properties;
	}
}
