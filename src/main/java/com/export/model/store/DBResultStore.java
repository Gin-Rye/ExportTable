package com.export.model.store;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.export.base.exception.BussinessException;

public class DBResultStore implements ResultStore {
	private ResultSet resultSet;
	private List<String> columnNames = new ArrayList<String>();
	private List<Class<?>> columnClasses = new ArrayList<Class<?>>();
	private int size;
	
	public DBResultStore(ResultSet resultSet) throws BussinessException {
		try {
			this.resultSet = resultSet;
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			for(int column = 1; column <= resultSetMetaData.getColumnCount(); column++) {
				String columnName = resultSetMetaData.getColumnLabel(column);
				columnNames.add(columnName);
				String columnClassName = 
						resultSetMetaData.getColumnClassName(column);
				Class<?> columnClass = Class.forName(columnClassName);
				columnClasses.add(columnClass);
			}
			resultSet.last();
			size = resultSet.getRow();
			resultSet.beforeFirst();
		} catch(SQLException e) {
			throw new BussinessException(e);
		} catch(ClassNotFoundException e) {
			throw new BussinessException(e);
		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public String getColumnName(int column) {
		return columnNames.get(column - 1);
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return columnClasses.get(column - 1);
	}
	
	@Override
	public List<String> getColumnNames() {
		return new ArrayList<String>(columnNames);
	}
	
	@Override
	public List<Class<?>> getColumnClasses() {
		return new ArrayList<Class<?>>(columnClasses);
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void close() throws BussinessException {
		try {
			resultSet.close();
		} catch(SQLException e) {
			throw new BussinessException(e);
		}
	}

	@Override
	public boolean isClosed() throws BussinessException {
		try {
			return resultSet.isClosed();
		} catch(SQLException e) {
			throw new BussinessException(e);
		}
	}

	@Override
	public int getCursor() throws BussinessException {
		try {
			return resultSet.getRow();
		} catch(SQLException e) {
			throw new BussinessException(e);
		}
	}

	@Override
	public void setCursor(int row) throws BussinessException {
		try {
			resultSet.absolute(row);
		} catch(SQLException e) {
			throw new BussinessException(e);
		}
	}

	@Override
	public void moveBeforeFirst() throws BussinessException {
		try {
			resultSet.beforeFirst();
		} catch(SQLException e) {
			throw new BussinessException(e);
		}
	}

	@Override
	public void next() throws BussinessException {
		try {
			resultSet.next();
		} catch(SQLException e) {
			throw new BussinessException(e);
		}
	}

	@Override
	public boolean hasNext() throws BussinessException {
		try {
			if(size == 0) {
				return false;
			} else {
				return !resultSet.isLast();
			}
		} catch(SQLException e) {
			throw new BussinessException(e);
		}
	}

	@Override
	public Object getColumnData(int column) throws BussinessException {
		try {
			return resultSet.getObject(column);
		} catch(SQLException e) {
			throw new BussinessException(e);
		}
	}
	
	@Override
	public Object getColumnData(String columnName) throws BussinessException {
		try {
			return resultSet.getObject(columnName);
		} catch(SQLException e) {
			throw new BussinessException(e);
		}
	}
}
