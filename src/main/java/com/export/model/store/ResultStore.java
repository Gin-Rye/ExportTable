package com.export.model.store;

import java.util.List;

import com.export.base.exception.BussinessException;

public abstract class ResultStore {
	
	private String tableName;
	
	public ResultStore() {}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public abstract int getColumnCount();
	
	public abstract String getColumnName(int column);
	
	public abstract List<String> getColumnNames();
	
	public abstract Class<?> getColumnClass(int column);
	
	public abstract List<Class<?>> getColumnClasses();
	
	public abstract int getSize();
	
	public abstract void close() throws BussinessException;
	
	public abstract boolean isClosed() throws BussinessException;
	
	public abstract int getCursor() throws BussinessException;
	
	public abstract void setCursor(int row) throws BussinessException;
	
	public abstract void moveBeforeFirst() throws BussinessException;
	
	public abstract void next() throws BussinessException;
	
	public abstract boolean hasNext() throws BussinessException;
	
	public abstract Object getColumnData(int column) throws BussinessException;
	
	public abstract Object getColumnData(String columnName) throws BussinessException;
}
