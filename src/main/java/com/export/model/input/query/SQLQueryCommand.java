package com.export.model.input.query;

public class SQLQueryCommand extends QueryCommand {
	
	private String sql;
	
	public SQLQueryCommand(String tableName, String sql) {
		super(tableName);
		this.sql = sql;
	}
	
	public String getSql() {
		return sql;
	}
}
