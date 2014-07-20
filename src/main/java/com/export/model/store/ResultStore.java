package com.export.model.store;

import java.util.List;

import com.export.base.exception.BusinessException;

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
	
	public abstract void close() throws BusinessException;
	
	public abstract boolean isClosed() throws BusinessException;
	
	public abstract int getCursor() throws BusinessException;
	
	public abstract void setCursor(int row) throws BusinessException;
	
	public abstract void moveBeforeFirst() throws BusinessException;
	
	public abstract void next() throws BusinessException;
	
	public abstract boolean hasNext() throws BusinessException;
	
	public abstract Object getColumnData(int column) throws BusinessException;
	
	public abstract Object getColumnData(String columnName) throws BusinessException;
}
