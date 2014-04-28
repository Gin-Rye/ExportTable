package com.export.model.input.query;

public class SQLQueryCommand extends QueryCommand {
	
	private String sql;
	
	public SQLQueryCommand(String sql) {
		this.sql = sql;
	}
	
	public String getSql() {
		return sql;
	}
}
