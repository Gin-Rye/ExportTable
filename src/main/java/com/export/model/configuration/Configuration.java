package com.export.model.configuration;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
	protected String type;
	protected Map<String, String> properties = new HashMap<String, String>();

	@Override
	public Configuration clone() {
		try {
			return (Configuration) super.clone();
		} catch(CloneNotSupportedException e) {
			RuntimeException exception = new RuntimeException(e.getMessage());
			exception.setStackTrace(e.getStackTrace());
			throw exception;
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Map<String, String> getProperties() {
		return properties;
	}
}
