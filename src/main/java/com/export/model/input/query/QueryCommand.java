package com.export.model.input.query;

public class QueryCommand {
	private String tableName;
	
	public QueryCommand(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
