package com.export.model.store;

import java.util.List;

import com.export.base.exception.BussinessException;

public interface ResultStore {
	
	int getColumnCount();
	
	String getColumnName(int column);
	
	List<String> getColumnNames();
	
	Class<?> getColumnClass(int column);
	
	List<Class<?>> getColumnClasses();
	
	int getSize();
	
	void close() throws BussinessException;
	
	boolean isClosed() throws BussinessException;
	
	int getCursor() throws BussinessException;
	
	void setCursor(int row) throws BussinessException;
	
	void moveBeforeFirst() throws BussinessException;
	
	void next() throws BussinessException;
	
	boolean hasNext() throws BussinessException;
	
	Object getColumnData(int column) throws BussinessException;
	
	Object getColumnData(String columnName) throws BussinessException;
}
