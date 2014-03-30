package com.export.data.output;

public enum SinkType {
	FILE("FILE");
	
	private String type;
	
	private SinkType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public static SinkType getSinkType(String type) {
		try {
			return SinkType.valueOf(type);
		} catch(Exception e) {
			return null;
		}
	}
}
