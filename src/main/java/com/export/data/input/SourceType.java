package com.export.data.input;

public enum SourceType {
	DB("DB"),
	HTTP("HTTP");
	
	private String type;
	
	private SourceType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public static SourceType getSourceType(String type) {
		try {
			return SourceType.valueOf(type);
		} catch(Exception e) {
			return null;
		}
	}
}
