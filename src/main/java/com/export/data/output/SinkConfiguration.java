package com.export.data.output;

import java.util.HashMap;
import java.util.Map;

public class SinkConfiguration implements Cloneable {
	
	protected SinkType sinkType;
	protected Map<String, String> properties = new HashMap<String, String>();
	
	@Override
	public SinkConfiguration clone() {
		try {
			return (SinkConfiguration) super.clone();
		} catch(CloneNotSupportedException e) {
			RuntimeException exception = new RuntimeException(e.getMessage());
			exception.setStackTrace(e.getStackTrace());
			throw exception;
		}
	}

	public SinkType getSinkType() {
		return sinkType;
	}

	public void setSinkType(SinkType sinkType) {
		this.sinkType = sinkType;
	}
	
	public Map<String, String> getProperties() {
		return properties;
	}
}
